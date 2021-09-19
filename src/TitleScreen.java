import java.awt.*;

public class TitleScreen {
    public void drawTitleScreen(Graphics g) {
        g.setColor(Color.BLUE); //Sky
        g.fillRect(0, 0, 1000, 800);
        g.setColor(Color.YELLOW); //Sun
        g.fillOval(750, 50, 150, 150);

        //Cloud on right
        g.setColor(Color.WHITE);
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
        g.setColor(Color.BLACK);
        g.fillRect(0, 475, 1000, 425); //Street
        g.setColor(Color.YELLOW);
        g.fillRect(0, 600, 200, 50);
        g.fillRect(400, 600, 200, 50);
        g.fillRect(800, 600, 200, 50);

        //Car
        g.setColor(Color.RED);
        g.fillRect(400,420,100,40);
        g.setColor(Color.cyan);
        g.fillRect(410,400,70,25);
        g.setColor(Color.white);
        g.fillRect(420,405,20,25);
        g.fillRect(450,405,20,25);
        g.setColor(Color.black);
        g.fillRect(455,410,10,20);
        g.fillOval(410,450,25,25);
        g.fillOval(460,450,25,25);
        g.setColor(Color.white);
        g.fillOval(415,455,10,10);
        g.fillOval(465,455,10,10);

        //Title
        g.setColor(Color.RED);
        Font font = new Font(Font.SANS_SERIF, Font.BOLD, 125);
        g.setFont(font);
        g.drawString("Jumpy Car", 160, 125);
    }
}
