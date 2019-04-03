/**
 * @(#)Errors.java
 *
 *
 * @author
 * @version 1.00 2019/3/21
 */


public class Errors {

	    public static void ValidatePhone (){

			String regex = "^\\+(?:[0-9] ?){6,14}[0-9]$";
            Pattern pattern = Pattern.compile(regex);
            Matcher matcher = pattern.matcher(textfieldPhoneNumber.getText());

            if(matcher.matches()){
            	JOptionPane.showMessageDialog(null, "Message Sent");
            }
            else {
            	JOptionPane.showMessageDialog(null, "Invalid phone number.\n Please use +343715373 format.");
            }


    }

    public Errors() {
    }




}