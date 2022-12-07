package network;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.util.Date;

import javax.management.InvalidAttributeValueException;

public class Message {
    public static enum MessageType {
        CMD,
        TXT,
        IMG,
        SND,
        REQ,
        END
    }

    public class Header {
        public String ip_sender; // 32 + 7 = 39
        public String ip_reader; // 39
    
        public UniqueID id_sender; // 32
        public UniqueID id_reader; // 32
    
        public long timestamp; // 16 (hex)
    
        public MessageType type; // 1 (int->str)
    
        public int msg_size; // 10 (int->str)
    
        public String msg_md5; // 32
    
        public Header(){};
        public Header(String buffer) {
            this.decode(buffer);
        }

        @Override
        public String toString() {
            StringBuffer sb = new StringBuffer();
            sb.append("Sender IP:" + ip_sender + "\n");
            sb.append("Reader IP:" + ip_reader + "\n");
            sb.append("Sender ID:" + id_sender.getUniqueID() + "\n");
            sb.append("Reader ID:" + id_reader.getUniqueID() + "\n");
            sb.append("Time:" + new Date(timestamp).toString() + "\n");
            sb.append("MsgType:" + type.name() + "\n");
            sb.append("Msg Size:" + Integer.toString(msg_size) + "\n");
            sb.append("MD5:" + msg_md5 + "\n");
            return sb.toString();
        }
    
        public String encode() {
            StringBuffer sb = new StringBuffer();
            sb.append(ip_sender);
            sb.append(ip_reader);
    
            sb.append(id_sender.getUniqueID());
            sb.append(id_reader.getUniqueID());
    
            sb.append(String.format("%16s", Long.toHexString(timestamp)).replace(" ", "0"));
    
            sb.append(Integer.toString(type.ordinal()));
            sb.append(String.format("%10d", msg_size).replace(" ", "0"));
    
            sb.append(msg_md5);
    
            return sb.toString();
        }

        public void decode(String buffer) {
            this.ip_sender = buffer.substring(0, 39);
            this.ip_reader = buffer.substring(39, 78);

            this.id_sender = new UniqueID(buffer.substring(78, 110));
            this.id_reader = new UniqueID(buffer.substring(110 , 142));

            BigInteger time = new BigInteger(buffer.substring(142, 158), 16);
            this.timestamp = time.longValue();

            this.type = MessageType.values()[Integer.valueOf(buffer.substring(158, 159))];

            this.msg_size = Integer.valueOf(buffer.substring(159, 169));

            this.msg_md5 = buffer.substring(169, 201);
        }
    }

    final int HeaderLength = 201;
    
    public Header header;
    private byte[] content;

    public Message() {
        this.header = new Header();
    }

    public Message(String headerBuffer) {
        this.header = new Header(headerBuffer);
    }

    public Message(byte[] msgBuffer) throws InvalidAttributeValueException, UnsupportedEncodingException {
        this.decode(msgBuffer);
    }

    public void updateTimeStamp() {
        long time = new Date().getTime();
        this.header.timestamp = time;
    }

    public void setContent(byte[] content) {
        this.content = content;
        this.header.msg_size = this.content.length;
        this.header.msg_md5 = UniqueID.getMD5(this.content);
    }

    public boolean isValid() {
        if (this.content.length != this.header.msg_size) {
            return false;
        }
        if (UniqueID.getMD5(this.content).compareTo(this.header.msg_md5) != 0) {
            return false;
        }
        return true;
    }

    public String getTextMessage() {return getTextMessage("UTF-8");}

    public String getTextMessage(String charset) {
        String res;
        try {
            res = new String(this.content, charset);
        } catch (UnsupportedEncodingException e) {
            res = new String(this.content);
        }
        return res;
    }

    public byte[] encode() throws IndexOutOfBoundsException{
        String header_str = this.header.encode();
        if (header_str.length() != HeaderLength) {
            throw new IndexOutOfBoundsException("Header length invalid");
        }
        byte[] header_b;
        try {
            header_b = header_str.getBytes("UTF-8");
        } catch (UnsupportedEncodingException e) {
            header_b = header_str.getBytes();
        }
        byte[] res = new byte[header_b.length + this.content.length];
        for (int i = 0; i < header_b.length + this.content.length; i++) {
            if (i < header_b.length) {
                res[i] = header_b[i];
            } else {
                res[i] = this.content[i - header_b.length];
            }
        }
        return res;
    }

    public void decode(byte[] buffer) throws InvalidAttributeValueException, UnsupportedEncodingException{
        if (buffer.length < HeaderLength) {
            throw new InvalidAttributeValueException("Length of msg buffer is less than required");
        }
        byte[] header = new byte[HeaderLength];
        byte[] content = new byte[buffer.length - HeaderLength];
        for (int i = 0; i < buffer.length; i++) {
            if (i < HeaderLength) {
                header[i] = buffer[i];
            } else {
                content[i - HeaderLength] = buffer[i];
            }
        }
        this.header = new Header(new String(header, "UTF-8"));
        this.content = content;
    }

    
}
