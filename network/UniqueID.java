package network;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class UniqueID {
    private String id;

    private final static String[] strHex = { "0", "1", "2", "3", "4", "5", 
        "6", "7", "8", "9", "a", "b", "c", "d", "e", "f" };
    
    public UniqueID() {
        this.id = "";
    }

    public UniqueID(String id) {
        this.id = id;
    }

    public UniqueID(long timestamp, String alias) {
        String hash = alias + String.valueOf(timestamp);
        this.id = getMD5(hash);
    }

    public static String getMD5(String msg) {
        StringBuffer sb = new StringBuffer();
        try {
            MessageDigest md = MessageDigest.getInstance("md5");
            byte[] str = msg.getBytes();
            byte[] buf = md.digest(str);
            for(int i = 0; i < buf.length; i++){
                int d = buf[i];
                if (d < 0) d += 256;
                int d1 = d / 16;
                int d2 = d % 16;
                sb.append(strHex[d1] + strHex[d2]);
            }
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return sb.toString();
    }

    public static String getMD5(byte[] msg) {
        StringBuffer sb = new StringBuffer();
        try {
            MessageDigest md = MessageDigest.getInstance("md5");
            byte[] buf = md.digest(msg);
            for(int i = 0; i < buf.length; i++){
                int d = buf[i];
                if (d < 0) d += 256;
                int d1 = d / 16;
                int d2 = d % 16;
                sb.append(strHex[d1] + strHex[d2]);
            }
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return sb.toString();
    }

    public String getUniqueID(){
        return this.id;
    }
    
}
