package demo;

/*
 * File: LeftRightApp.java
 *
 * Description: This program creates a LeftRightPanel and
 *  adds it to the Frame's content pane and sets its size.
 *
 * Assignment: See LeftRightPanel.java
 * Author: Reed Kemp
 */
import javax.swing.*;

public class LeftRightApp extends JFrame
{
    public LeftRightApp()
    {
        JFrame frame = new JFrame("Frame");
        frame.getContentPane().add(new LeftRightPanel());
        //register 'Exit upon closing' as a default close operation
        setDefaultCloseOperation( EXIT_ON_CLOSE );

    } // LeftRightFrame() constructor


    public static void main(String args[]){
        LeftRightApp frame = new LeftRightApp();
        //I couldn't get the window to display properly when i sized, packed, and set it to visible here
    } // main()

} // LeftRightFrame class