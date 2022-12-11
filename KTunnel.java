import KTunnelGui.*;

public class KTunnel {
    private boolean gui;

    public Frame guiInstance;

    public KTunnel() {
        this.gui = false;
    }

    public void enableGui() {
        this.gui = true;
        Frame frame = new Frame();
        frame.loadConfig(null);
    }

    public static void main(String args[]) {
        KTunnel program = new KTunnel();
        // if start GUI
        if (args.length > 0) {
            for (int i = 0; i < args.length; i++) {
                if (args[i].toLowerCase().compareTo("gui") == 0) {
                    program.enableGui();
                }
            }
        }


    }
}
