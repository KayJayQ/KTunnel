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
    }
}
