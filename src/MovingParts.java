import java.awt.Graphics;
import java.awt.Color;

public class MovingParts {
    private int obstacleX, obstacleY, obstacleH, obstacleW, obstacleS = 15;
    private Car c = new Car(400);

    public MovingParts() {
        obstacleX = 1050;
        obstacleH = (int)(100*Math.random()+50);
        obstacleW = (int)(10*(Math.random() + 5));
    }

    public void drawObstacle(Graphics g) {
        if(obstacleX < 0) {
            obstacleH = (int)(100*Math.random()+50);
            obstacleW = (int)(10*(Math.random() + 5));
            obstacleX = 1050;
            if(obstacleS < 40) {
                obstacleS += 1;
            }

        }
        obstacleY = 475 - obstacleH;
        g.setColor(Color.BLACK);
        g.fillRect(obstacleX, 475-obstacleH, obstacleW, obstacleH);
        obstacleX -= obstacleS;
    }

    public int getX() {
        return obstacleX;
    }

    public int getHeight() {
        return obstacleH;
    }

    public int getWidth() {
        return obstacleW;
    }

    public int getSpeed() {
        return obstacleS;
    }
}

