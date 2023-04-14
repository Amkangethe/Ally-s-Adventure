package main;

import java.awt.event.KeyEvent;
import java.net.URL;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
//import javax.sound.sampled.FloatControl;

public class Sound {

	Clip clip;
	URL soundURL[] = new URL[30];
	GamePanel gp;
	
	public FloatControl gainControl;
	float previousVolume = 0;
	float currentMusicVolume = -57.799995f;
	float currentSoundVolume = -13.399992f;
	boolean mute = false;

	
	public Sound(GamePanel gp)
	{
		this.gp = gp;
		soundURL[0] = getClass().getResource("/sound/DaBaby - BALL IF I WANT TO (Official Video).wav");
		soundURL[1] = getClass().getResource("/sound/Parents Yelling.wav");
		soundURL[2] = getClass().getResource("/sound/MenuButton.wav");
		soundURL[3] = getClass().getResource("/sound/M_OutsideToday-YoungBoy.wav");
		soundURL[4] = getClass().getResource("/sound/OneShot.wav");
		soundURL[5] = getClass().getResource("/sound/Parents Yelling.wav");


		//FloatControl gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
		
		
	}
	
	public void setFile(int i)
	{
		try
		{
			AudioInputStream ais = AudioSystem.getAudioInputStream(soundURL[i]);
			clip = AudioSystem.getClip();
			clip.open(ais);
			gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
			//Create Slider Key for volume in Pause Menu
		//	FloatControl gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
			//gainControl.setValue(-10.0f); // Reduce volume by 10 decibels.
		
		}
		catch(Exception e)
		{
		}
	}

	public void musicPlay()
	{
		clip.start();
		gainControl.setValue(currentMusicVolume);

	}
	public void soundPlay()
	{
		clip.start();
		gainControl.setValue(currentSoundVolume);

	}
	public void musicVolumeUp()
	{
		currentMusicVolume += 7.4f;
		if(currentMusicVolume >= 1.4000082f)
		{
			currentMusicVolume = 1.4000082f;
		}
		gainControl.setValue(currentMusicVolume);
	}
	public void musicVolumeDown()
	{
		currentMusicVolume -= 7.4f;
		if(currentMusicVolume < -80.0f)
		{
			currentMusicVolume = -80.0f;
		}
		gainControl.setValue(currentMusicVolume);
	}
	public void soundVolumeUp()
	{
		currentSoundVolume += 7.4f;
		if(currentSoundVolume >= 1.4000082f)
		{
			currentSoundVolume = 1.4000082f;
		}
		gainControl.setValue(currentSoundVolume);
	}
	public void soundVolumeDown()
	{
		currentSoundVolume -= 7.4f;
		if(currentSoundVolume < -80.0f)
		{
			currentSoundVolume = -80.0f;
		}
		gainControl.setValue(currentSoundVolume);
	}
	public void loop()
	{
		clip.loop(Clip.LOOP_CONTINUOUSLY);
	}
	
	public void stop()
	{
		clip.stop();
	}
	
	
	
	
}
