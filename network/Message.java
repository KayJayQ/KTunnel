package network;

import network.UniqueID;
import java.util.Date;

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
        public Header(String buffer) {}

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
    }
    
    public Header header;

    public Message() {
        this.header = new Header();
    }
}
