import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferStrategy;

public class Snake extends Canvas implements Runnable {
    private int width = 600;
    private int height = 600;
    private Thread thread;
    private BufferStrategy bs;
    private boolean isRunning;

    public Snake() {
        JFrame frame = new JFrame("SNAKE");
        this.setSize(width,height);
        frame.add(this);
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

    private void draw () {
        bs = getBufferStrategy();
        if(bs == null) {
            createBufferStrategy(3);
            return;
        }
    }
    public static void main(String[] args) {
        Snake painting = new Snake();
        painting.start();
    }

    public synchronized void start() {
        thread = new Thread(this);
        isRunning = true;
        thread.start();
    }

    public synchronized void stop() {
        isRunning = false;
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {

    }
}
