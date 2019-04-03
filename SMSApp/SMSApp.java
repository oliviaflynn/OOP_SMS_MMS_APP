import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.awt.BorderLayout;
import java.awt.event.*;
import java.awt.*;
import javax.swing.*;
import javax.swing.event.*;
import javax.swing.text.*;
import java.io.File;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.filechooser.FileSystemView;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * This is the main class also known as the Controller
 * The purpose of the Controller is to act as a mediator
 * between the View (user interface) and the Model
 * (business logic). The Controller is responsible for
 * the communication between the model and the view.
 * The Controller does not define the business logic.
 *
 * @author Olivia Flynn
 * @version 0.1 Build 1 26th March 2019
 */

public class SMSApp extends JFrame implements ActionListener {

	/**
	 * Specifies the width and height of the frame in pixels.<br>
	 * FYI; high definition is 1920x1080 pixels
	 */
	private int frameWidth = 480;
	private int frameHeight = 700;
	/**
	 * Specifies the frame title
	 */
	private String frameTitle = "SMS & MMS Application";

	/**
	 * These are the variables used to REFERENCE the user
	 * interface component objects used within the application
	 */

	private ButtonGroup radiobuttonGroup;
	private JRadioButton radiobuttonSMS;
	private JRadioButton radiobuttonMMS;
	private JLabel labelPhoneNumber;
	private JTextField textfieldPhoneNumber;
	private JLabel labelMessage;
	private JTextArea textAreaMessage;
	private JButton buttonSend;
	private JButton buttonHelp;
	private JButton fileChooserButton;
	private JLabel labelFileName;

	private ArrayList<SMS_Message> messages;
	private boolean isSMS;


	/**
	 * The constructor is called when an object of a class
	 * is created. It is used to set the initial values or
	 * state of this objects attributes.<br>
	 * In this case the constructor is used to setup the
	 * application's main container frame:
     * <br>(1) Set the width and height of the frame
     * <br>(2) Set the frame title
     * <br>(3) Set the behaviour for when the frame close
     *         button is clicked
     * <br>(4) Center the frame vertically and horizontally
     *         on the screen
     * <br>(5) Add the main application panel to the frame
     */
	public SMSApp() {

		messages = new ArrayList<>();
		messages = FileManager.getInstance().LoadData();


		this.setSize(this.frameWidth, this.frameHeight); // (1)
		this.setTitle(frameTitle); // (2)
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // (3)
		this.setLocationRelativeTo(null); // (4)
		this.getContentPane().add( createPanel() ); // (5)
	}

    /**
     * Create the application main panel. This panel
     * contains all of the applications components and
     * sub-panels.
     * <dt><b>Precondition:</b><dd> N/A
     * <dt><b>Postconditions:</b><dd> N/A
     * @return The main application panel, ready to be added
     *         to the frame.
     * @throws N/A
     */
	private JPanel createPanel() {

		javax.swing.border.Border componentBorder = BorderFactory.createEmptyBorder(5, 5, 5, 5);

		// setup the radio buttons
		radiobuttonSMS = new JRadioButton("SMS");
        radiobuttonSMS.setSelected(true);

        // There is no need to setup an actionListener for the radio buttons
        // because all we need is their selected state, either true or false
        radiobuttonSMS.setActionCommand("SMS");
        radiobuttonSMS.addActionListener(this);
		radiobuttonMMS = new JRadioButton("MMS");
		radiobuttonMMS.setSelected(false);
        radiobuttonMMS.setActionCommand("MMS");
        radiobuttonMMS.addActionListener(this);
        radiobuttonGroup = new ButtonGroup();
		radiobuttonGroup.add(radiobuttonSMS);
		radiobuttonGroup.add(radiobuttonMMS);

		//help button
		buttonHelp = new JButton("Help");
		buttonHelp.setBorder(componentBorder);
		buttonHelp.addActionListener(this);
		buttonHelp.setActionCommand("DISPLAY");

		// setup the phone number
		labelPhoneNumber = new JLabel("Phone No:");
		labelPhoneNumber.setBorder(componentBorder);
		textfieldPhoneNumber = new JTextField(15); // limit of 15 numbers
		textfieldPhoneNumber.setBorder(componentBorder);

		// setup the message/URL
		labelMessage = new JLabel("Message:");
		labelMessage.setBorder(componentBorder);

		textAreaMessage = new JTextArea();
		textAreaMessage.setBorder(componentBorder);
		textAreaMessage.addKeyListener(new KeyAdapter() {

		public void keyTyped(KeyEvent e) {
        	if (textAreaMessage.getText().length() >= 160 ) // limit text to 160 characters
        	{
        		e.consume();
        		JOptionPane.showMessageDialog(null, "Error Message 02: Message has reached character limit. Max characters = 160.");

        	}

            }
		});

		// setup the send button
		buttonSend = new JButton("Send");
		buttonSend.setBorder(componentBorder);
		buttonSend.setActionCommand("NUMBER");
		buttonSend.addActionListener(this);

		//setup file chooser
		fileChooserButton = new JButton("Attach File");
		fileChooserButton.setBorder(componentBorder);
		fileChooserButton.setActionCommand("FILE");
		fileChooserButton.addActionListener(this);
		fileChooserButton.setVisible(false);

		labelFileName = new JLabel("File Name");
		labelFileName.setBorder(componentBorder);
		labelFileName.setVisible(true);

		// flowlayout
		JPanel flowPane = new JPanel();
		flowPane.setLayout(new FlowLayout());
		flowPane.setBorder(componentBorder);
		flowPane.add(radiobuttonSMS);
		flowPane.add(radiobuttonMMS);
		flowPane.add(fileChooserButton);
		flowPane.add(labelFileName);

		// add them to a vertical box layout
		JPanel verticalPane = new JPanel();
		verticalPane.setLayout(new BoxLayout(verticalPane, BoxLayout.PAGE_AXIS));
		verticalPane.setBorder(componentBorder);

		verticalPane.add(labelPhoneNumber);
		verticalPane.add(textfieldPhoneNumber);
		verticalPane.add(radiobuttonSMS);
		verticalPane.add(radiobuttonMMS);
		verticalPane.add(fileChooserButton);
		verticalPane.add(labelFileName);
		verticalPane.add(labelMessage);
		verticalPane.add(textAreaMessage);
		verticalPane.add(buttonSend);
		verticalPane.add(buttonHelp);


		// layout the application components
		JPanel applicationPane = new JPanel( new BorderLayout() );
		applicationPane.add(flowPane, BorderLayout.WEST);
		applicationPane.add(verticalPane, BorderLayout.NORTH);
		applicationPane.add(createScrollablePanel(), BorderLayout.CENTER);

		return applicationPane;
	}

	/**
	 * Create a scrollable pane that can be added to the content pane
     * @return The scrollable panel
     * @see <a href="https://stackoverflow.com/questions/42486969/java-swing-scroll-able-jpanel-with-multiple-panels-on-frame">Scrollable JPanel</a>
     *Contains log of messages from file
     */
	private JScrollPane createScrollablePanel() {

		JPanel pane = new JPanel( new GridLayout(0,1) );

		JScrollPane scrollPane = new JScrollPane(pane);
		scrollPane.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setBounds(0, 0, 450, 300);


		for(SMS_Message message: messages){
			JPanel messagePanel = new JPanel();
            messagePanel.setLayout(new GridLayout(0,1));
            messagePanel.setBackground(Color.WHITE);

			if(message instanceof MMS_Message){
				MMS_Message mms = (MMS_Message)message;
				JLabel head = new JLabel("Sender: " + message.getSender()+ " Recipient: "
				 + message.getRecipient()+ " Sent: " + message.getStatusSent() + " Delivered: "
				 + message.getStatusDelivered());
            	head.setForeground(Color.GRAY);

            	JLabel body = new JLabel("Attachment: " + mms.getFileName()+ " Message: "
				 + message.getMessage());
            	body.setForeground(Color.GRAY);

				messagePanel.add(head);
            	messagePanel.add(body);
			}
			else {

				JLabel head = new JLabel("Sender: " + message.getSender()+ " Recipient: "
				 + message.getRecipient()+ " Sent: " + message.getStatusSent() + " Delivered: "
				 + message.getStatusDelivered());
				 head.setHorizontalAlignment(SwingConstants.RIGHT);
            	head.setForeground(Color.BLUE);

            	JLabel body = new JLabel("Message: " + message.getMessage());
            	body.setHorizontalAlignment(SwingConstants.RIGHT);
            	body.setForeground(Color.BLUE);

				messagePanel.add(head);
            	messagePanel.add(body);
			}
            pane.add(messagePanel);
		}
		return scrollPane;
	}

	/**
	 * Components that use the addActionListener method
	 * to register listeners trigger this method. These
	 * components are:<br>
	 * <code>button, check box, text field, file chooser</code><br>
	 * amongst others.
	 *
     * @param e The event generated by a component (such as a Button)
     * <dt><b>Precondition:</b><dd> N/A
     * <dt><b>Postconditions:</b><dd> N/A
     * @return void
     * @throws N/A
     * @see <a href="https://docs.oracle.com/javase/tutorial/uiswing/events/eventsandcomponents.html">Listeners Supported by Swing Components</a>
     * @see <a href="https://docs.oracle.com/javase/tutorial/uiswing/events/actionlistener.html">How to Write an Action Listener</a>
     */
	public void actionPerformed(ActionEvent e) {


		if (e.getActionCommand().equals("DISPLAY")) {

			new Help().setVisible(true);

		}


		else if (e.getActionCommand().equals("MMS")) {

			fileChooserButton.setVisible(true);
			isSMS = false;

		}


		else if (e.getActionCommand().equals("SMS")) {

			fileChooserButton.setVisible(false);
			labelFileName.setVisible(false);
			isSMS = true;
		}

		else if(e.getActionCommand().equals("NUMBER")) {
				ValidatePhone();

		}

		else if (e.getActionCommand().equals("FILE")) {

			JFileChooser fileChooser = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
        	fileChooser.setMultiSelectionEnabled(false);
        	fileChooser.setAcceptAllFileFilterUsed(false);

        	FileNameExtensionFilter imagesFilter = new FileNameExtensionFilter("Picture", "jpg", "jpeg", "png");
            FileNameExtensionFilter audioFilter = new FileNameExtensionFilter("Audio", "mp3", "wav");
            FileNameExtensionFilter videoFilter = new FileNameExtensionFilter("Video", "mpeg", "mp4", "avi");

            fileChooser.addChoosableFileFilter(imagesFilter);
            fileChooser.addChoosableFileFilter(audioFilter);
            fileChooser.addChoosableFileFilter(videoFilter);

	        int result = fileChooser.showOpenDialog(null);

	        if (result == JFileChooser.APPROVE_OPTION) {
	        File selectedFile = fileChooser.getSelectedFile();
	        String fileName = selectedFile.getName();
	        System.out.println(fileName + " Selected file: " + selectedFile.getAbsolutePath());
	        labelFileName.setText("File name: " + fileName);
	        labelFileName.setVisible(true);
			}
			else{
				System.out.println("Operation cancelled by user.");
			}

		}


    }

    public void ValidatePhone (){

			String regex = "^\\+(?:[0-9] ?){6,14}[0-9]$";
            Pattern pattern = Pattern.compile(regex);
            Matcher matcher = pattern.matcher(textfieldPhoneNumber.getText());

            if(matcher.matches()){

				if (textAreaMessage.getText().length()>0) {

					SMS_Message SMSTypeMSG = new SMS_Message();

					//setting up object with values from input fields
					SMSTypeMSG.setRecipient(textfieldPhoneNumber.getText());
					SMSTypeMSG.setStatusSent(true);
					SMSTypeMSG.setStatusDelivered(true);
					SMSTypeMSG.setMessage(textAreaMessage.getText());

					//Add Object to Array
					messages.add(SMSTypeMSG);
				}

				else if(isSMS = false){

					MMS_Message MMSTypeMSG = new MMS_Message();

					//setting up object with values from imput fields
					MMSTypeMSG.setRecipient(textfieldPhoneNumber.getText());
					MMSTypeMSG.setStatusSent(true);
					MMSTypeMSG.setStatusDelivered(true);
					MMSTypeMSG.setMessage(textAreaMessage.getText());
					MMSTypeMSG.setFileName(labelFileName.getText());
				}


				else {
				JOptionPane.showMessageDialog(null, "Error Message 03: Please fill out all fields in order to send a message.");
				}
				//save array
            	FileManager.getInstance().SaveData(messages);
            	JOptionPane.showMessageDialog(null, "Message Sent");
            }
            else {
            	JOptionPane.showMessageDialog(null, "Error Message 01: Invalid phone number.\n Please use + symbol and 14 number format.");
            }
    }

    /**
     * This is a Java application and the main() method
     * is the entry point into this application.
     *
     * @param args Used to make command-line arguments
     *             available to the application
     * <dt><b>Precondition:</b><dd> N/A
     * <dt><b>Postconditions:</b><dd> N/A
     * @return void
     * @throws N/A
     */

    public static void main(String[] args) {

			new SMSApp().setVisible(true);

    }

} // end of class MainClass