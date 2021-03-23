import java.awt.*;
import java.awt.image.BufferStrategy;

public class Snake extends Canvas implements Runnable {
    private int width = 800;
    private int height = 600;
    private Thread thread;
    private BufferStrategy bs;


    private void draw () {
        bs = getBufferStrategy();
        if(bs == null) {
            createBufferStrategy(3);
            return;
        }
    }

    @Override
    public void run() {
        
    }
}
