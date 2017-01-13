package edu.buffalostate.cis425.sp16.exercises.kemp;

/*
 * File: Ball.java
 *
 * Description: This class defines a ball that bounces up and
 *  down within an app. The "bouncing" is effected by repeatedly
 *  drawing, and erasing and moving the ball in the run() method.
 *  Note the use class constants in the program and the use of XOR mode
 *  in the draw method.
 *
 * In this version of Ball, instead of starting every Ball in the center
 *  of the bounce area, each ball is given a random x-coordinate. So each
 *  bounces in its own vertical space.
 *
 * This version of Ball also keeps track of the location of the paddle.
 *  The applet is used to mediate the connection between the paddle and the ball.
 *  Whenever the ball hits the paddle, it bounces at a random angle.
 *
 *
 *
 * Assignment: See PongApp for more information
 *
 */

import javax.swing.*;
import java.awt.*;

public class Ball extends Thread implements Runnable {

    public static final int SIZE = 5;       // Diameter of the ball
    private static final int DX = 5;        // Number of pixels to move the ball
    private static final int DY = 5;        // Number of pixels to move the ball
    private static final int MINDY = -4;    // Used by randomizer: Vertical changes goes from
    private static final int MAXDY = 9;     //   -4 to +4, giving 9 discrete values

    private PongApp app;                    // Reference to the applet
    private int topWall, bottomWall;        // Boundaries
    private int leftWall, rightWall;        // Boundaries
    private int paddleLoc;                  // The horizontal location of the paddle

    private Point location;                 // Current location of the ball
    private int directionX = 1, directionY = 1;  // Ball's x- and y-direction (1 or -1)
    private int deltaY;                      // Change in the Y coordinate
    private Color ballcolor;
	private boolean KeepBallAlive;

    /**
     * Ball() constructor sets a pointer to the applet and initializes the ball's location
     *  at the center of the bouncing region
     */
    public Ball(PongApp a) {
        app = a;
        Dimension gameArea = app.getSize();       // Define bouncing region
        rightWall = gameArea.width - app.BORDER - SIZE;  // And location of walls
 	    leftWall = app.BORDER + 1;
        topWall = app.BORDER + 1;
        bottomWall = gameArea.height - app.BORDER - SIZE;
        paddleLoc = rightWall - app.BORDER;              // Initial paddle location

        int xLoc = app.BORDER + (gameArea.width - app.BORDER)/2;      // Pick a start xLoc
        location = new Point(xLoc, gameArea.height/2);        // Set initial location
        ballcolor = Color.black;
        KeepBallAlive=true;
    } // Ball()



    /**
     *  erase() erases the ball.
     */
    public void erase() {
		synchronized (app) {
        	Graphics g = app.getGraphics();
        	g.setColor(Color.white);
        	g.fillOval(location.x, location.y, SIZE, SIZE); //  erase
        	g.dispose();
		}
    } // erase()


    /**
     * draw() draws the ball.
     */
    public void draw() {
		synchronized (app) {
        	Graphics g = app.getGraphics();
        	g.setColor(ballcolor);
        	g.fillOval(location.x, location.y, SIZE, SIZE); //   draw
        	g.dispose();
		}
    } // draw()

    /**
     * move() changes the ball's vertical location (y-coordinate) by DY pixels.
     *  It then checks if the ball has reached a boundary and if so reverses direction
     */
     public void move() {
         location.x = location.x + DX * directionX; // Calculate a new location
         location.y = location.y + deltaY * directionY;

         if ( app.hitPaddle( location ))  {               // Check for paddle ricochet
             directionX = -directionX;                       // Change horizontal direction
             deltaY = MINDY + (int) (Math.random() * MAXDY); // Change vertical direction
             location.x = paddleLoc;                         // Adjust location
         }

         if (location.y > bottomWall) {    // If ricochet
             directionY = -directionY;      //  reverse direction
             location.y =  bottomWall;
         }
         if (location.y < topWall) {
             directionY = -directionY;       // Reverse direction
             location.y = topWall;
         }
         if (location.x > rightWall )  { 	 // HINT: hit right wall
             directionX = -directionX;
             location.x =  rightWall;
         }

         if ( location.x < leftWall ) {
             directionX = -directionX;
             deltaY = MINDY + (int) (Math.random() * MAXDY); // CHANGE VERTICAL DIRECTION
             location.x = leftWall;
         }
    } // move()

    /**
     * run() repeatedly draws, erases and moves the ball
     */
    public void run() {
        while (KeepBallAlive) {
            draw();                          // Draw
            try {
                sleep(50);
            }
            catch (InterruptedException e) {}
            erase();                        // Erase
            move();                         // Move
       } // while
       app.informGame();					// HINT: ball died
    } // run()
} // Ball
