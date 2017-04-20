package world.model;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.net.URLDecoder;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class MusicBox implements Runnable, Serializable
{
	private transient Thread thread;
	private String fileName;
	private transient Clip clip;
	boolean loop;
	float dc;

	public MusicBox(String fileName, boolean loop)
	{
		this.fileName = fileName;
		this.loop = loop;
		dc = 0;
	}
	public MusicBox(String fileName, boolean loop,float dc)
	{
		this.fileName = fileName;
		this.loop = loop;
		this.dc = dc;
	}

	public void deadChangeDc(float dc)
	{
		this.dc = dc;
	}
	public void liveChangeDc(float dc)
	{
		
		if (clip!=null)
		{FloatControl gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
			gainControl.setValue(dc);
		} else
		{
			Thread volumeThread = new Thread()
			{
				public void run()
				{
					
					while (clip == null)
					{
						try
						{
							Thread.sleep(1000);
						} catch (InterruptedException e)
						{
							// TODO Auto-generated catch block
							e.printStackTrace();
						}

					}
					FloatControl gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
					gainControl.setValue(dc);
				}
			};
			volumeThread.start();
		}
	}

	public void run()
	{

		AudioInputStream audio;
		try
		{
			//InputStream input = this.getClass().getResourceAsStream("resources/" + fileName);
			//String file = URLDecoder.decode(childPath.getFile(), "UTF-8");
			audio = AudioSystem.getAudioInputStream(this.getClass().getResource("resources/" + fileName));
			Clip sound = AudioSystem.getClip();
			clip = sound;
			sound.open(audio);
			FloatControl gainControl = (FloatControl) sound.getControl(FloatControl.Type.MASTER_GAIN);
			gainControl.setValue(-50.0f);
			sound.start();
			gainControl = (FloatControl) sound.getControl(FloatControl.Type.MASTER_GAIN);
			gainControl.setValue(dc);
			if (loop)
			{
				sound.loop(sound.LOOP_CONTINUOUSLY);
			}

		} catch (UnsupportedAudioFileException | IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (LineUnavailableException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void startThread()
	{
		thread = new Thread(this, "MusicBox");
		thread.start();
	}
}
