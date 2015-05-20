package galaxia;
import javax.swing.JFrame;
import java.io.*;
public class GalaxiaWindow extends JFrame {
    final int WINDOW_WIDTH = 400;              
    final int WINDOW_HEIGHT = 500;
    public GalaxiaWindow() throws IOException {
        super("Galaxia Window");                     //Titles Window 
        setSize(WINDOW_WIDTH, WINDOW_HEIGHT);        //set window size to WINDOW_WIDTH and WINDOW_HEIGHT
        setDefaultCloseOperation(EXIT_ON_CLOSE);     //default close operation
        
        add(new GraphicsPanel());                    //adds graphics panel to the frame
    }
}
