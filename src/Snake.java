import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferStrategy;
public class Snake extends Canvas implements Runnable {
    private int width = 680;
    private int height = 680;
    private Thread thread;
    private BufferStrategy bs;
    private boolean isRunning;
    private int fps = 30;
    private int snakeX,snakeY,snakeVX,snakeVY;

    public Snake() {
        JFrame frame = new JFrame("SNAKE");
        this.setSize(width,height);
        frame.add(this);
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.addKeyListener(new KL());
        frame.setVisible(true);

        snakeX = 100;
        snakeY = 100;
        snakeVX = 0;
        snakeVY = 0;
    }
    public void update() {
        snakeX += snakeVX;
        snakeY += snakeVY;
    }
    private void draw () {
        bs = getBufferStrategy();
        if(bs == null) {
            createBufferStrategy(3);
            return;
        }
        Graphics g = bs.getDrawGraphics();

        g.setColor(Color.GREEN);
        g.fillRect(0,0,width,height);
        drawSnake(g,snakeX,snakeY);
        g.dispose();
        bs.show();
    }

    private void drawSnake(Graphics g, int x, int y) {
        g.setColor(new Color(0,128,0));
        g.fillRect(40+x,40+y,40,40);
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
        double deltaT = 1000.0/fps;
        long lastTime = System.currentTimeMillis();

        while (isRunning) {
            long now = System.currentTimeMillis();
            if (now-lastTime > deltaT) {
                update();
                draw();
                lastTime = now;
            }

        }
        stop();
    }
    private class KL implements KeyListener {
        @Override
        public void keyTyped(KeyEvent keyEvent) {

        }

        @Override
        public void keyPressed(KeyEvent keyEvent) {
            if (keyEvent.getKeyChar() == 'a') {
                snakeVX = -5;
                snakeVY = 0;
            }
            if (keyEvent.getKeyChar() == 'd') {
                snakeVX = 5;
                snakeVY = 0;
            }
            if (keyEvent.getKeyChar() == 'w') {
                snakeVY = -5;
                snakeVX = 0;
            }
            if (keyEvent.getKeyChar() == 's') {
                snakeVY = 5;
                snakeVX = 0;
            }
        }

        @Override
        public void keyReleased(KeyEvent e) {

        }
    }
}
