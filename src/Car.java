import java.awt.Color;
import java.awt.Graphics;

public class Car {
    private int x;
    private int y;
    private boolean isJumping;

    public Car(int y) {
        x = 400;
        this.y = y;
    }

    public boolean isJumping()
    {
        return isJumping;
    }

    public void setPos(int p) {
        if (y <= 150) {
            isJumping = true;
            y = p;
        }
        else if(y > 150 && y <= 400) {
            isJumping = true;
            y = p;
        }
        else if(y > 400) {
            isJumping = false;
            y = 400;
        }
    }

    public void draw(Graphics g) {
        g.setColor(Color.RED);
        g.fillRect(x,y+20,100,40);
        g.setColor(Color.cyan);
        g.fillRect(x+10,y,70,25);
        g.setColor(Color.white);
        g.fillRect(x+20,y+5,20,25);
        g.fillRect(x+50,y+5,20,25);
        g.setColor(Color.black);
        g.fillRect(x+55,y+10,10,20);
        g.fillOval(x+10,y+50,25,25);
        g.fillOval(x+60,y+50,25,25);
        g.setColor(Color.white);
        g.fillOval(x+15,y+55,10,10);
        g.fillOval(x+65,y+55,10,10);
    }

    public int getX()
    {
        return x;
    }

    public int getY()
    {
        return y;
    }
}
