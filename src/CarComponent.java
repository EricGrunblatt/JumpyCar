import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.HashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;


public class CarComponent extends JComponent implements Runnable {
    private int ySpeed = -44; //Velocity
    private int xPos = 400; //xPosition for Car
    private int yPos = 400; //yPosition for Car
    private int acceleration = 4;
    private Car c = new Car(400);
    MovingParts parts = new MovingParts();
    private boolean isGameOver = false;
    private int score = 0; //Score starts at 0
    private boolean isJumping = false; //Jumping starts at false
    private boolean leaderboard = false;
    private boolean titleScreen = true;
    private HashMap<Integer, Integer> leaders = new HashMap<>(5);
    public CarComponent() {
        for(int i = 1; i <= leaders.size(); i++) {
            leaders.put(i, 0);
        }
    }

    public void paintComponent(Graphics g) {
        Font f = new Font(Font.SANS_SERIF, Font.PLAIN, 40);
        if(titleScreen) {
            TitleScreen titleScreen = new TitleScreen();
            titleScreen.drawTitleScreen(g);
        }
        if(!leaderboard() && !titleScreen) {
            g.setColor(Color.BLUE); //Sky
            g.fillRect(0, 0, 1000, 800); // Sky
            g.setColor(Color.YELLOW); //Sun
            g.fillOval(750, 50, 150, 150); //Sun
            g.setColor(Color.WHITE);
            //Cloud on right
            g.fillOval(520, 150, 100, 66);
            g.fillOval(480, 100, 100, 66);
            g.fillOval(440, 150, 100, 66);
            g.fillOval(400, 100, 100, 66);
            g.fillOval(360, 150, 100, 66);
            //Cloud on left
            g.fillOval(240, 150, 100, 66);
            g.fillOval(200, 100, 100, 66);
            g.fillOval(160, 150, 100, 66);
            g.fillOval(120, 100, 100, 66);
            g.fillOval(80, 150, 100, 66);
            //Mountains
            Color Green = new Color(45, 144, 21);
            Color darkGreen = new Color(20, 73, 11);
            g.setColor(darkGreen);
            g.fillOval(0, 200, 300, 800);
            g.fillOval(500, 250, 300, 800);
            g.fillOval(700, 200, 325, 825);
            g.setColor(Green);
            g.fillOval(50, 250, 325, 800);
            g.fillOval(550, 250, 500, 800);
            parts.drawObstacle(g);
            c.draw(g);
            g.setColor(Color.BLACK);
            g.fillRect(0, 475, 1000, 425); //Street
            g.setColor(Color.YELLOW);
            g.fillRect(0, 600, 200, 50);
            g.fillRect(400, 600, 200, 50);
            g.fillRect(800, 600, 200, 50);
            g.setColor(Color.RED);
            g.setFont(f);
            g.drawString("SCORE: " + score, 50, 50);
        }
        if(isGameOver()) {
            g.setColor(Color.GRAY);
            g.fillRect(250, 250, 500, 400);
            f = new Font(Font.SANS_SERIF, Font.PLAIN, 80);
            g.setFont(f);
            g.setColor(Color.RED);
            g.drawString("Game Over", 290, 350);
            g.setColor(Color.BLUE);
            f = new Font(Font.SANS_SERIF, Font.PLAIN, 60);
            g.setFont(f);
            g.drawString("Your Score: " + score, 290, 425);
        }
        if(leaderboard()) {
            g.setColor(Color.DARK_GRAY);
            g.fillRect(0, 0, 1000, 800);
            f = new Font(Font.SANS_SERIF, Font.PLAIN, 120);
            g.setFont(f);
            g.setColor(Color.WHITE);
            g.drawString("Leaderboard", 150, 100);
        }
    }

    public void isJumping(boolean b) {
        isJumping = b;
    }

    public boolean isGameOver() {
        return isGameOver;
    }

    public boolean leaderboard() {
        return leaderboard;
    }

    public void setTitleScreen(boolean b) { titleScreen = b; }

    public void resetGame() {
        ySpeed = -44; //Velocity
        xPos = 400; //xPosition for Car
        yPos = 400; //yPosition for Car
        acceleration = 4;
        c = new Car(400);
        parts = new MovingParts();
        boolean isGameOver = false;
        score = 0; //Score starts at 0
        isJumping = false; //Jumping starts at false
        leaderboard = false;
    }

    public void run() {
        setFocusable(true);
        setFocusTraversalKeysEnabled(false);
        while(true) {
            Rectangle car = new Rectangle(c.getX(), c.getY()+20, 100, 40);
            Rectangle obstacle = new Rectangle(parts.getX(), 475-parts.getHeight(), parts.getWidth(), parts.getHeight());
            if(car.intersects(obstacle)) {
                isGameOver = true;
                repaint();
                break;
            }
            try {
                score++;
                Thread.sleep(50);

                if(isJumping) { //Car falls
                    if(yPos < 150) {
                        yPos = 150;
                        ySpeed = 44; //ySpeed is updated
                        acceleration = -4;
                    }
                    if(yPos > 400) { //Car jumps
                        yPos = 400;
                        ySpeed = -44;
                        acceleration = 4;
                    }
                    ySpeed += acceleration; //Velocity decreases each time
                    yPos += ySpeed; //yPos gets velocity added
                    if(yPos > 400) {
                        c.setPos(400);
                        isJumping = false;
                    }
                    else {
                        c.setPos(yPos); //yPos is updated
                    }
                    repaint();

                }
                if(!c.isJumping()) { //Once car falls back down, it stays still
                    isJumping = false;
                }
            }
            catch (InterruptedException e) {
                System.out.println("Interrupted Exception");
            }
            repaint();
        }
    }

    public static void main(String []args) {
        AtomicBoolean exit = new AtomicBoolean(false);
        AtomicBoolean exitTitleScreen = new AtomicBoolean(false);
        do {
            JFrame frame = new JFrame();
            final int FRAME_WIDTH = 1000;
            final int FRAME_HEIGHT = 800;
            frame.setSize(FRAME_WIDTH, FRAME_HEIGHT);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            final CarComponent car = new CarComponent();
            JButton startGame = new JButton("Start Game");
            startGame.setBounds(125, 675, 250, 75);
            frame.add(startGame);
            JButton leaderboard = new JButton("Leaderboard");
            leaderboard.setBounds(375, 675, 250, 75);
            frame.add(leaderboard);
            JButton exitGame = new JButton("Exit");
            exitGame.setBounds(625, 675, 250, 75);
            frame.add(exitGame);
            frame.add(car, BorderLayout.CENTER);
            frame.setVisible(true);

            while(!exitTitleScreen.get()) {
                exitGame.addActionListener(e -> {
                    if(exitGame.isEnabled()) {
                        exit.set(true);
                        exitTitleScreen.set(true);
                        car.setTitleScreen(false);
                        frame.dispose();
                    }
                });
                startGame.addActionListener(e -> {
                    if(startGame.isEnabled()) {
                        car.resetGame();
                        exitTitleScreen.set(true);
                        car.setTitleScreen(false);
                        frame.dispose();
                    }
                });
                leaderboard.addActionListener(e -> {
                    if(leaderboard.isEnabled()) {
                        car.leaderboard = true;
                        car.repaint();
                    }
                });
            }
        } while(!exitTitleScreen.get());

        do {
            JFrame frame = new JFrame();
            final int FRAME_WIDTH = 1000;
            final int FRAME_HEIGHT = 800;
            frame.setSize(FRAME_WIDTH, FRAME_HEIGHT);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            final CarComponent car = new CarComponent();
            car.setTitleScreen(false);
            frame.add(car, BorderLayout.CENTER);
            frame.setVisible(true);
            car.repaint();
            //Jumps when space is pressed

            frame.addKeyListener(new KeyListener() {
                @Override
                public void keyTyped(KeyEvent e) {}

                @Override
                public void keyPressed(KeyEvent e) {
                    if(e.getKeyCode() == KeyEvent.VK_SPACE) {
                        car.isJumping(true);
                    }
                }

                @Override
                public void keyReleased(KeyEvent e) {}
            });
            frame.setFocusable(true);
            //Creates thread and runs game
            ExecutorService executor = Executors.newFixedThreadPool(1);
            executor.execute(car);
            executor.shutdown();
            try {
                executor.awaitTermination(Long.MAX_VALUE, TimeUnit.NANOSECONDS);
            } catch (InterruptedException e) {

            }

            JButton startGame = new JButton("Start Game");
            startGame.setBounds(375, 475, 250, 75);
            frame.add(startGame);
            JButton leaderboard = new JButton("Leaderboard");
            leaderboard.setBounds(250, 550, 250, 75);
            frame.add(leaderboard);
            JButton exitGame = new JButton("Exit");
            exitGame.setBounds(500, 550, 250, 75);
            frame.add(exitGame);
            frame.add(car, BorderLayout.CENTER);
            frame.setVisible(true);
            car.repaint();
            AtomicBoolean postGameMenu = new AtomicBoolean(false);
            while(!postGameMenu.get()) {
                //Checks if button is pressed and restarts game
                exitGame.addActionListener(e -> {
                    if(exitGame.isEnabled()) {
                        exit.set(true);
                        postGameMenu.set(true);
                        frame.dispose();
                    }
                });
                startGame.addActionListener(e -> {
                    if(startGame.isEnabled()) {
                        car.resetGame();
                        postGameMenu.set(true);
                        frame.dispose();
                    }
                });
                leaderboard.addActionListener(e -> {
                    if(leaderboard.isEnabled()) {
                        car.leaderboard = true;
                        car.repaint();
                    }
                });
            }
        } while(!exit.get());
    }
}
