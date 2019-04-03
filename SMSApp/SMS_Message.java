import java.awt.event.*;
import java.awt.*;
import javax.swing.*;
import java.io.Serializable;

/**
 * @(#)SMS_Message.java
 *
 *
 * @author Olivia Flynn
 * @version 1.00 2019/3/26
 */
public class SMS_Message implements Serializable{
	//Variables
	private static String sender = "+6453748593";
	private String recipient;
	private boolean statusSent;
	private boolean statusDelivered;
	private static boolean group = false; //160 characters doesn't allow group msgs
	private String message;

	//Constructor
	public SMS_Message(){
	}

	//Setters and Getters
    public String getSender()
    {
    	return sender;
    }

	public String getRecipient()
    {
    	return recipient;
    }

    public void setRecipient(String value)
    {
    	recipient = value;
    }

    public boolean getStatusSent()
    {
    	return statusDelivered;
    }

    public void setStatusSent(boolean value)
    {
    	statusSent = value;
    }

    public boolean getStatusDelivered()
    {
		return statusDelivered;
    }

    public void setStatusDelivered(boolean value)
    {
    	statusDelivered = value;
    }

	public String getMessage()
	{
		return message;
	}

	public void setMessage(String value)
	{
		message = value;
	}

	public boolean getGroup ()
	{
		return group;
	}


}



