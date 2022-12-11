/**
 * @file: Entry class, initializes window frame
 * Entry class, intializes window frame
 * 
 * @author: Kay Qiang
 * @author: qiangkj@cmu.edu
 */

package KTunnelGui;

import javax.swing.*;

import backend.*;

import java.awt.Toolkit;

public class Frame {
    
    public static JFrame frame;

    public Frame() {
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
}
