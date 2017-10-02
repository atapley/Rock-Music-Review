import java.io.File;
import javax.sound.sampled.*;

public class Sound {

  private Clip clip;

  public Sound(String fileName) {
    // specify .wav file to play
    // catch exceptions in cases of:
    // -- file not found
    // -- audio system disabled
    try {
      File file = new File(fileName);
      if (file.exists()) {
        AudioInputStream sound = AudioSystem.getAudioInputStream(file);
        // load the sound into memory (a Clip)
        clip = AudioSystem.getClip();
        clip.open(sound);
      }
    }
    catch (Exception e) {
      System.out.println("Exception: " + e);
    }
  }

  // stop, reset timer, play clip
  public void play() {
    try {
      clip.stop();
      clip.setFramePosition(0);  // rewind!
      clip.start();
    }
    catch (Exception e) {
      // If it doesn't work, just don't play the sound
    }
  }

  // loop clip
  public void loop() {
    try {
      clip.loop(Clip.LOOP_CONTINUOUSLY);
    }
    catch (Exception e) {
      //if it breaks, just don't play the sound
    }
  }

  // stop clip
  public void stop() {
    try {
      clip.stop();
    }
    catch (Exception e) {
      // if it can't stop, then just play the entire sound
    }
  }

}