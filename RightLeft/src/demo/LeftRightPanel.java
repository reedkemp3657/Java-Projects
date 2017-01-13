/**
 * 
 */
package demo;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
/*
 * File: LeftRightPanel.java
 *	Author: Reed Kemp
 * Description: This class defines a GUI in a JPanel which contains
 * two JButtons with initial labels "Left" and "Right" and a
 * JTextField. Pressing a button causes the label of that button
 * to be printed into the textfield.
 *
 * Assignment: 1) Create a centerButton (label it "Center")
 *             2) Create another JPanel
 *             3) Add the Left,Center,Right Buttons to the JPanel 
 *                in step 2
 *             4) Change the Layout Manager of LeftRightPanel to 
 *                BorderLayout
 *             5) Place the JPanel of step 2 in the center position 
 *                of LeftRightPanel
 *             6) Place the JTextField( outField ) in the south 
 *                position of LeftRightPanel
 *             7) Change the ActionEvent code so the pressing the 
 *                centerButton displays "center' in the outField
 */
public class LeftRightPanel extends JPanel implements ActionListener
{
    private JButton leftButton;
    private JButton rightButton;
    private JButton centerButton;
    private JTextField outField;

    public LeftRightPanel()
    {
       JFrame frame = new JFrame("Frame");
        frame.setLayout(new BorderLayout());
        // Add Components
        JPanel panel = new JPanel();
            frame.add(panel, BorderLayout.CENTER);


        leftButton = new JButton("Left");
        leftButton.addActionListener(this);
        rightButton = new JButton("Right");
        rightButton.addActionListener(this);
        centerButton = new JButton("Center");
        centerButton.addActionListener(this);

// HINT: create Middle button, add event handler


        outField = new JTextField(10);

// HINT: add three buttons to the JPanel
        panel.add(leftButton);
        panel.add(centerButton);
        panel.add(rightButton);
        frame.add(outField,BorderLayout.SOUTH);
        frame.pack();
        frame.setSize(600,400);
        frame.setVisible(true);
        
// HINT: put everything together
//       JPanel goes in the CENTER position of LeftRightPanel
//       outField goes in the SOUTH position


        
    }

    public void actionPerformed(ActionEvent e)
    {
        JButton b = (JButton) e.getSource();
		outField.setText( b.getText() );

    } // actionPeformed()
} // LeftRightPanel class
