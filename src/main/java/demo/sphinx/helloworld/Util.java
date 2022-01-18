package demo.sphinx.helloworld;

import datenbankenf.Execute;
import datenbankenf.moderationids;
import datenbankenf.persConfig;
import datenbankenf.playcontent;
import java.net.URL;
import java.io.*;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.nio.file.Paths;
import javax.sound.sampled.*;
import static datenbankenf.idsystem.*;
import static datenbankenf.moderationids.*;
public class Util {
    public static void AUDIO(String path) {
        //spielt Audiofiles ab
        try {
            AudioInputStream stream_foraudio;
            AudioFormat format_foraudio;
            DataLine.Info info_foraudio;
            Clip clip_foraudio;
            String[] path2 = Util.class.getProtectionDomain().getCodeSource().getLocation().getPath().split("jarbor-client-1.1-final_Version.jar");
            System.out.println(path2[0]);
            // path wird getrackt
File f = new File(path2[0] +"/Systemfiles/"+path);
            System.out.println(f);
            stream_foraudio = AudioSystem.getAudioInputStream(f);
            format_foraudio = stream_foraudio.getFormat();
            info_foraudio = new DataLine.Info(Clip.class, format_foraudio);
            clip_foraudio = (Clip) AudioSystem.getLine(info_foraudio);
            clip_foraudio.open(stream_foraudio);
            clip_foraudio.start();
        }catch (Exception e) {
            e.printStackTrace();
        }

    }
    public static String  read(){
        //führt alle read methoden aus und gibt die id zurück
        Execute.executereadFromFile();
        playcontent.linksreadFromFile();
        persConfig.configreadFromFile();
        moderationids.configreadFromFile();
       return idreadandwrite();

    }
}


