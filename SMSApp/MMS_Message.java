import java.awt.event.*;
import java.awt.*;
import javax.swing.*;
import java.io.Serializable;
/**
 * @(#)MMS_Message.java
 *
 *
 * @author Olivia Flynn
 * @version 1.00 2019/3/26
 */
public class MMS_Message extends SMS_Message implements Serializable{

	//Instance Variables
	private boolean picture;
	private boolean audio;
	private boolean video;
	private String fileName;

	//Constructor
	public MMS_Message(){
	}

	//Setters and Getters
	public void setPicture(boolean value)
	{
		picture = value;
	}

	public boolean getPicture()
	{
		return picture;
	}

	public void setAudio(boolean value)
	{
		audio = value;
	}

	public boolean getAudio()
	{
		return audio;
	}

	public void setVideo(boolean value)
	{
		video = value;
	}

	public boolean getVideo()
	{
		return video;
	}

	public void setFileName(String value)
	{
		fileName = value;
	}

	public String getFileName()
	{
		return fileName;
	}



}