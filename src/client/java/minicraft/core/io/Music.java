package minicraft.core.io;

import java.io.File;
import java.util.Arrays;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import minicraft.core.io.FileHandler;
import java.util.Random;
public class Music{
    public static String filepath =FileHandler.getSystemGameDir() + "/playminicraft/mods/Minicraft_Plus/music/";
    public static File musiclist = new File(FileHandler.getSystemGameDir() + "/playminicraft/mods/Minicraft_Plus/music/");
    private static Clip clip;
    public static String theme= "Forest.wav";
    public static String[] songlist;




    public static void Playtheme(){
        PlayMusic(theme);
    }
    public static void PlayRandomMusic(){
        if(musiclist.list() ==null ) return;
        if(songlist==null){
            songlist = musiclist.list();
            if(songlist==null || songlist.length<=0 ) return;
            songlist = Arrays.stream(songlist).filter(name -> name.endsWith(".wav")|| name.endsWith(".mp3")).toArray(String[]::new);
        }
        
        if(songlist.length==1){
           PlayMusic(songlist[0]);
        }else{
        if(songlist!=null){
            Random r= new Random();
            PlayMusic(songlist[r.nextInt(songlist.length-1)]);
        }
        }

    }
    
    public static void PlayRandomMusicLoop(){
        
        
        if(songlist==null){
            songlist = musiclist.list();
            if(songlist==null || songlist.length<=0 ) return;
            songlist = Arrays.stream(songlist).filter(name -> name.endsWith(".wav")|| name.endsWith(".mp3")).toArray(String[]::new);
        }
        
        if(songlist.length==1){
             LoopMusic(songlist[0]);
        }else{
        if(songlist!=null){
            Random r= new Random();
            LoopMusic(songlist[r.nextInt(songlist.length-1)]);
        }
        }

    }
    
    public static void PlayMusic(String name){
        try{
            File musicpath = new File(filepath+name);
            if(musicpath.exists()){
                    AudioInputStream audioinput = AudioSystem.getAudioInputStream(musicpath);
                    clip = AudioSystem.getClip();
                    clip.open(audioinput);
                    clip.start();
            }else{
                System.out.println("Cant find file");
            }
        }catch(Exception e){
            System.out.println(e);

        }
    }

    public static void LoopMusic(String name){
     
            StopMusic();
            PlayMusic(name);
            clip.loop(Clip.LOOP_CONTINUOUSLY);
            
   
        
    }
    public static void StopMusic(){
        if(clip!=null){
            clip.stop();
            clip.close();
        }
    }
}
