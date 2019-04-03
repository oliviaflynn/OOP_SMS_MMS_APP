import java.io.*;
import java.util.ArrayList;
import javax.swing.JOptionPane;

public class FileManager {

    private static FileManager instance;

    private FileManager() {
    }

    public static FileManager getInstance(){
        if(instance == null){
            instance = new FileManager();
        }
        return instance;
    }

    public void SaveData(ArrayList<SMS_Message> messages){
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(new File("messagesList.dat"));
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);

            objectOutputStream.writeObject(messages);

            objectOutputStream.close();
            fileOutputStream.close();

        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        } catch (IOException e) {
            System.out.println("Error initializing stream");
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    public ArrayList<SMS_Message> LoadData(){
        ArrayList<SMS_Message> messages = new ArrayList<>();
        try {
            FileInputStream fileInputStream = new FileInputStream(new File("messagesList.dat"));
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);

            messages = (ArrayList<SMS_Message>)objectInputStream.readObject();

        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        } catch (IOException e) {
            System.out.println("Error initializing stream");
        } catch (Exception e){
            e.printStackTrace();
        }
        return messages;
    }
}
