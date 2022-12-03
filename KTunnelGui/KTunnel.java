/**
 * @file: Entry class, initializes window frame
 * Entry class, intializes window frame
 * 
 * @author: Kay Qiang
 * @author: qiangkj@cmu.edu
 */

package KTunnelGui;

import javax.swing.*;
import java.awt.Toolkit;
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

    public static void main(String[] args) {
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
        System.out.println(msg.header.encode());
        System.out.println(msg.header.encode().length());
        System.out.println(msg.header.toString());
    }
}
