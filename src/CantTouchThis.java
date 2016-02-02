/**
 Created by Andrea D'Olimpio on 14/10/2015
 Challenge 2 of Space Cadets
 This applet displays a "Can't touch this" image that
 cannot be touche by the mouse.
 Song playing in the background added.
 */
import java.applet.Applet;
import java.applet.AudioClip;
import java.awt.*;
import java.awt.event.*;
import java.util.Random;


public class CantTouchThis extends Applet{
    private Graphics graphics;
    private int x,y, mousex, mousey;
    private MouseMotionListener listener;
    private Image image;
    private AudioClip song;
    private Color bgcolor;

    public void start() {
        //setup variables
        bgcolor=Color.white;
        image = getImage(getCodeBase(),"logo.png");
        song = getAudioClip(getCodeBase(), "song.wav");
        graphics = getGraphics();
        mousex = 0;
        mousey = 0;
        x = getWidth()/2 - 60;
        y = getHeight()/2 -60;
        //play song
        song.loop();

        //if mouse moves start
        listener = new MouseMotionAdapter() {
            public void mouseMoved(MouseEvent event) {
                //get mouse coordinates
                mousex = event.getX();
                mousey = event.getY();
                paint(graphics);
                run();
            }
        };
        addMouseMotionListener(listener);
    }

    public void run() {
        //if mouse approaches image
        //building frame 15px around a 125x125 png
        if (mousex > x - 15 && mousex < x + 140 && mousey > y - 15
                && mousey < y + 140) {
            //move image to random place in applet
            Random rand = new Random();
            x = rand.nextInt(getWidth() - 125);
            y = rand.nextInt(getHeight() - 125);
            changeBG();
            repaint();
        }
    }

    public void changeBG() {
        Random rand = new Random();
        int i = rand.nextInt(6);
        switch (i) {
            case 0:
                bgcolor = Color.magenta;
                break;
            case 1:
                bgcolor = Color.green;
                break;
            case 2:
                bgcolor = Color.cyan;
                break;
            case 3:
                bgcolor = Color.yellow;
                break;
            case 4:
                bgcolor = Color.pink;
                break;
            case 5:
                bgcolor = Color.orange;
                break;
            default:
                bgcolor = Color.white;
                break;
        }
    }


    public void stop() {
        removeMouseMotionListener(listener);
    }

    public void paint(Graphics graphics) {
        //erase previous prints
        graphics.setColor(bgcolor);
        graphics.fillRect(0, 0, getWidth(), getHeight());
        //display image
        graphics.drawImage(image, x, y, this);

    }
}
