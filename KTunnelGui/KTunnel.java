/**
 * @file: Entry class, initializes window frame
 * Entry class, intializes window frame
 * 
 * @author: Kay Qiang
 * @author: qiangkj@cmu.edu
 */

package KTunnelGui;

import javax.management.InvalidAttributeValueException;
import javax.swing.*;
import java.awt.Toolkit;
import java.io.UnsupportedEncodingException;
import java.util.Date;

import network.*;

public class KTunnel {
    
    public static JFrame frame;

    public KTunnel() {
        frame = new JFrame("KTunnel");
        int screenW = (int) Toolkit.getDefaultToolkit().getScreenSize().getWidth();
        int screenH = (int) Toolkit.getDefaultToolkit().getScreenSize().getHeight();
        frame.setSize(screenW / 2, screenH / 2);
        frame.setTitle("KTunnel");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

    public void loadConfig(String configPath) {
        return;
    }

    public static void main(String[] args) throws UnsupportedEncodingException, InvalidAttributeValueException {
        KTunnel program = new KTunnel();
        program.loadConfig("null");

        Message msg = new Message();
        msg.header.ip_sender = "2001:0000:0000:0000:085b:3c51:f5ff:ffdb";
        msg.header.ip_reader = "2001:0000:0000:0000:085b:3c51:f5ff:ffdb";
        UniqueID senderID = new UniqueID(new Date().getTime(), "SMJ");
        UniqueID readerID = new UniqueID(new Date().getTime(), "SLK");
        msg.header.id_sender = senderID;
        msg.header.id_reader = readerID;
        msg.header.type = Message.MessageType.CMD;
        msg.header.timestamp = new Date().getTime();
        msg.header.msg_size = 3;
        msg.header.msg_md5 = UniqueID.getMD5("CLS");
        byte[] content = "IloveU".getBytes("UTF-8");
        msg.setContent(content);
        
        byte[] buffer = msg.encode();
        Message newMessage = new Message(buffer);
        System.out.println(newMessage.header);
        System.out.println(newMessage.getTextMessage());
        System.out.println(newMessage.isValid());
    }
}
