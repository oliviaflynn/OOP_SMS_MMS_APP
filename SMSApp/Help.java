import java.awt.BorderLayout;
import java.awt.event.*;
import java.awt.*;
import javax.swing.*;
import javax.swing.event.*;
import javax.swing.text.*;
import java.io.File;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.filechooser.FileSystemView;
/**
 * @(#)Help.java
 *
 * @author Olivia Flynn
 * @version 1.00 2019/3/26
 */


public class Help extends JFrame {

	/**
	 * Specifies the width and height of the frame in pixels.<br>
	 * FYI; high definition is 1920x1080 pixels
	 */
	private int frameWidth = 400;
	private int frameHeight = 210;
	/**
	 * Specifies the frame title
	 */
	private String frameTitle = "User Help Window";

	/**
	 * These are the variables used to REFERENCE the user
	 * interface component objects used within the application
	 */

	 private JLabel labelHelp;
	 private JTextArea textAreaHelp;
	 private String message1 = "1. Enter recipient's phone number.";
	 private String message2 = "2. Choose SMS or MMS type message.";
	 private String message3 = "3. If MMS, choose attachment of either audio, video or picture type.";
	 private String message4 = "4. Enter message body text.";
	 private String message5 = "5. Click send.";
     private String newline = "\n\r";

	/*
	 * The constructor is called when an object of a class
	 * is created. It is used to set the initial values or
	 * state of this objects attributes.<br>
	 */
    public Help() {

    	this.setSize(this.frameWidth, this.frameHeight);
		this.setTitle(frameTitle);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setLocationRelativeTo(null);
		this.getContentPane().add( createPanel() );
    }


     /**
     * Create the application main panel. This panel
     * contains all of the applications components and
     * sub-panels.
     */

     public  JPanel createPanel() {


     	javax.swing.border.Border componentBorder = BorderFactory.createEmptyBorder(5, 5, 5, 5);

     	// setup the text label
		labelHelp = new JLabel("Help");
		labelHelp.setBorder(componentBorder);

		//setup the text Area
		textAreaHelp = new JTextArea();
		textAreaHelp.setBorder(componentBorder);
		textAreaHelp.setBackground(Color.WHITE);

		textAreaHelp.setText(message1+newline+message2+newline+message3+newline+message4+newline+message5);

		textAreaHelp.setEditable(false);

		// add them to a vertical box layout
		JPanel verticalPane = new JPanel();
		verticalPane.setLayout(new BoxLayout(verticalPane, BoxLayout.PAGE_AXIS));
		verticalPane.setBorder(componentBorder);
		verticalPane.add(labelHelp);
		verticalPane.add(textAreaHelp);

		// layout the application components
		JPanel applicationPane = new JPanel( new BorderLayout() );
		applicationPane.add(verticalPane, BorderLayout.NORTH);

		return applicationPane;
     }

      public static void main(String[] args) {
			new Help().setVisible(true);
    }
    }
