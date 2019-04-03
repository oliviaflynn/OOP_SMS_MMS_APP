import java.awt.Component;
import java.io.*;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.JOptionPane;
import java.util.ArrayList;

/**
 * This class contains various general purpose static utility methods.
 * The class is declared final to preventing subclassing.
 * The constructor is declared private to prevent instantiation
 */

public final class Utils {

	/**
	 * Apply this filter when using the File|Open dialog for multimedia files
	 * e.g. .mov, .mp4, .mkv, .mpg, .avi, .mp3, .jpg, .gif	 *
	 */
	public static final FileFilter MEDIA_FILTER = new FileNameExtensionFilter("Media", "mov", "mp4", "mkv", "mpg", "avi", "mp3", "jpg", "gif");
	/**
	 * Apply this filter when using the File|Open dialog for CSV files
	 */
	public static final FileFilter CSV_FILTER = new FileNameExtensionFilter("CSV", "csv");

	/**
	 * The constructor is private, this prevents direct instantiation.
	 */
	private Utils( ) { }

	/**
	 * Tell the user about something that has happened using a message dialog.
	 * The message dialog will be displayed in the default Frame.
	 *
	 * @param message The message to be displayed in the dialog.
	 * @param messageType The type of the message to be displayed.
	 *                    It will have one of these values:<br>
	 *                    - JOptionPane.ERROR_MESSAGE - Used for error messages<br>
	 *                    - JOptionPane.INFORMATION_MESSAGE - Used for information messages<br>
	 *                    - JOptionPane.WARNING_MESSAGE - Used for warning messages<br>
	 *                    - JOptionPane.QUESTION_MESSAGE - Used for questions<br>
	 *                    - JOptionPane.PLAIN_MESSAGE - No icon is used<br>
	 */
	public static void displayMessageDialog(String message, int messageType) {
		Component parentComponent = null;
		String title = "";
		switch(messageType) {
			case JOptionPane.ERROR_MESSAGE:
				title = "Error Message";
				break;
			case JOptionPane.INFORMATION_MESSAGE:
				title = "Information Message";
				break;
			case JOptionPane.WARNING_MESSAGE:
				title = "Warning Message";
				break;
			case JOptionPane.QUESTION_MESSAGE:
				title = "Question";
				break;
			case JOptionPane.PLAIN_MESSAGE:
				title = "Message";
				break;
			default:
				title = "Message";
				break;
		}
		JOptionPane.showMessageDialog(parentComponent, message, title, messageType);
	}

	/**
	 * Calls the File | Open dialog.
	 * @param parent The parent component of the dialog.
	 * @param filter The FileNameExtensionFilter to be used to filter the file selection.
	 * @return The File object to be opened or null if the Open was cancelled.
	 */
	public static File fileOpen(Component parent, FileFilter filter) {
		File file = null;
		JFileChooser fc = new JFileChooser(getCurrentDirectory());
 		fc.addChoosableFileFilter(filter);
		int returnVal = fc.showOpenDialog(parent);
		if ( returnVal == JFileChooser.APPROVE_OPTION ) {
			file = fc.getSelectedFile();
		}
		return file;
	}

	/**
	 * Reads a sequential file from start to end.
	 * @param file A File object representing the file to be read.
	 * @return The entire contents of the file contained in an ArrayList of strings.
	 */
	public static ArrayList<String> readTextFile(File file) {
		FileReader fr = null;
		BufferedReader br = null;
		String line = null;
		ArrayList<String> records = new ArrayList<>();
		try {
			fr = new FileReader(file);
			br = new BufferedReader(fr);
			line = br.readLine();
			while(line != null) {
				records.add(line);
				line = br.readLine();
			}
			br.close();
			fr.close();
		} catch (IOException ex) {	}
		return records;
	}

	/**
	 * Get the current working directory.
	 * The method uses: <code>System.getProperty("user.dir")</code>
	 * this is also equivalent to: <code>new File("").getCanonicalPath()</code>	 *
	 * @return The current working directory.
	 */
	public static String getCurrentDirectory() {
		return System.getProperty("user.dir");
	}

} // end of class Utils