/*
 * Copyright 1999-2004 Carnegie Mellon University.
 * Portions Copyright 2004 Sun Microsystems, Inc.
 * Portions Copyright 2004 Mitsubishi Electric Research Laboratories.
 * All Rights Reserved.  Use is subject to license terms.
 *
 * See the file "license.terms" for information on usage and
 * redistribution of this file, and for a DISCLAIMER OF ALL
 * WARRANTIES.
 *
 */

package demo.sphinx.helloworld;

import datenbankenf.Execute;
import datenbankenf.moderationids;
import datenbankenf.persConfig;
import datenbankenf.playcontent;
import edu.cmu.sphinx.frontend.util.Microphone;
import edu.cmu.sphinx.recognizer.Recognizer;
import edu.cmu.sphinx.result.Result;
import edu.cmu.sphinx.util.props.ConfigurationManager;
import edu.cmu.sphinx.util.props.PropertyException;

import java.io.*;
import java.net.URL;
import java.awt.*;
import javax.swing.JFrame;


import static datenbankenf.idsystem.init;
import static datenbankenf.playcontent.*;
import static datenbankenf.Execute.*;
import static demo.sphinx.helloworld.GreetClient.send_to_server;
import static datenbankenf.moderationids.*;


/**
 * A simple HelloWorld demo showing a simple speech application
 * built using Sphinx-4. This application uses the Sphinx-4 endpointer,
 * which automatically segments incoming audio into utterances and silences.
 */
public class HelloWorld {
    public static void main(String[] args) {
/**
 * Main method for running the HelloWorld demo.
 */


        persConfig.init(null, "config.txt");
        playcontent.init(null, "music keys.txt");
        Execute.init("Executepaths.txt");
        moderationids.init("ids.txt");
        init("Clientid.txt");
        //initailizirung
       String id = "hi";
       id = Util.read();
       System.out.println("id: " + id);
        boolean started = false;
//read files

        try {

            if (args.length > 0) {
                URL url;
                url = new File(args[0]).toURI().toURL();
                System.out.println("case 1");
            } else {
                URL url;
                url = HelloWorld.class.getResource("/HelloWorld/fhelloworld.config.xml");
// api verknüpfung


Util.AUDIO("start.wav");
                System.out.println("Loading...");

                ConfigurationManager cm = new ConfigurationManager(url);

                Recognizer recognizer = (Recognizer) cm.lookup("recognizer");
                Microphone microphone = (Microphone) cm.lookup("microphone");
//api

                /* allocate the resource necessary for the recognizer */
                recognizer.allocate();

                /* the microphone will keep recording until the program exits */
                if (microphone.startRecording()) {

                    System.out.println
                            ("Say: System start ");



                    while (true) {
//start loop
                        System.out.println
                                ("Start speaking. Press Ctrl-C to quit.\n");

                        /*
                         * This method will return when the end of speech
                         * is reached. Note that the endpointer will determine
                         * the end of speech.
                         */

                        Result result = recognizer.recognize();

                        if (result != null) {
                            String resultText = result.getBestFinalResultNoFiller();
                            System.out.println("You said: " + resultText);
                            //eigen programierter code begint hier
                            if (resultText.equals("system start")) {
                                System.out.println("started system");
                                started = true;
                                Util.AUDIO("Hoertzu.wav");
                            //aktivirungs wort
                            }
                            while (started) {

                                Result result2 = recognizer.recognize();

                                if (result2 != null) {

                                    String resultText2 = result2.getBestFinalResultNoFiller();
                                    System.out.println("You said: " + resultText2);

                                    String[] words = resultText2.split(" ");
                                    // wörter die erkannt worden sind
                                    if (words[0].equals("discord")) {
                                        if (words[1].equals("play")) {
                                            if (words.length == 3) {
                                                //schaut nach 3 wörter
                                                String botdata = id + " " + getlink(words[2]);
                                                if (!botdata.contains("null")) {
                                                    //botdata ist das was zum Discordbot geschickt wird
                                                    System.out.println(botdata);
                                                    send_to_server(botdata);
                                                    started = false;
                                                    System.out.println("Sytem stopeted");
                                                    Util.AUDIO("succes.wav");
                                                    break;

                                                }

                                            } else if (words.length == 4) {
                                                //schaut nach 4 wörter
                                                String botdata = id + " " + getlink(words[2]+" "+words[3]);
                                                if (!botdata.contains("null")) {
                                                    //botdata ist das was zum Discordbot geschickt wird
                                                    System.out.println(botdata);
                                                    send_to_server(botdata);
                                                    started = false;
                                                    System.out.println("Sytem stopeted");
                                                    Util.AUDIO("succes.wav");
                                                    break;
                                                }
                                            }
                                        }
                                        if (words[1].equals("action")) {
                                            switch (words[2]) {
                                                case "skip":
                                                    String botdatas = id + " " + "skip";
                                                    if (!botdatas.contains("null")) {
                                                        //botdata ist das was zum Discordbot geschickt wird
                                                        System.out.println(botdatas);
                                                        send_to_server(botdatas);
                                                        started = false;
                                                        System.out.println("Sytsem stopeted");
                                                        Util.AUDIO("succes.wav");
                                                    }
                                                    break;
                                                case "clear":
                                                    String botdatac = id + " " + "clear";
                                                    if (!botdatac.contains("null")) {
                                                        //botdata ist das was zum Discordbot geschickt wird
                                                        System.out.println(botdatac);
                                                        send_to_server(botdatac);
                                                        started = false;
                                                        System.out.println("System stopped");
                                                        Util.AUDIO("succes.wav");
                                                    }break;
                                                case "kill":
                                                    String botdatak = id + " " + "kill"+ " "+ moderationids.getconfig(words[3]);
                                                    if (!botdatak.contains("null")) {
                                                        //botdata ist das was zum Discordbot geschickt wird
                                                    System.out.println(botdatak);
                                                    send_to_server(botdatak);
                                                    started = false;
                                                    System.out.println("System stopped");
                                                    Util.AUDIO("succes.wav");
                                                }
                                                break;
                                                case "move":
                                                    String botdataom = id + " " + "move"+ " "+ moderationids.getconfig(words[3]);
                                                    if (!botdataom.contains("null")) {
                                                        //botdata ist das was zum Discordbot geschickt wird
                                                        System.out.println(botdataom);
                                                        send_to_server(botdataom);
                                                        started = false;
                                                        System.out.println("System stopped");
                                                        Util.AUDIO("succes.wav");
                                                    }
                                                    break;

                                                case "deaf":
                                                    String botdatad = id + " " + "deaf"+ " "+ moderationids.getconfig(words[3]);
                                                    if (!botdatad.contains("null")) {
                                                        //botdata ist das was zum Discordbot geschickt wird
                                                        System.out.println(botdatad);
                                                        send_to_server(botdatad);
                                                        started = false;
                                                        System.out.println("System stopped");
                                                        Util.AUDIO("succes.wav");
                                                    }
                                                    break;
                                                case "mute":
                                                    String botdatamu = id + " " + "mute"+ " "+ moderationids.getconfig(words[3]);
                                                    if (!botdatamu.contains("null")) {
                                                        //botdata ist das was zum Discordbot geschickt wird
                                                        System.out.println(botdatamu);
                                                        send_to_server(botdatamu);
                                                        started = false;
                                                        System.out.println("System stopped");
                                                        Util.AUDIO("succes.wav");
                                                    }
                                                    break;
                                                case "disconnect":
                                                    String botdatadis = id + " " + "disconnect";
                                                    if (!botdatadis.contains("null")) {
                                                        //botdata ist das was zum Discordbot geschickt wird
                                                        System.out.println(botdatadis);
                                                        send_to_server(botdatadis);
                                                        started = false;
                                                        System.out.println("System stopped");
                                                        Util.AUDIO("succes.wav");
                                                    }
                                            }
                                        }
                                        }
                                        if (words[0].equals("system")) {
                                            switch (words[1]) {
                                                case "execute":
                                                 String[] ending =   getexecution(words[2]).split("\\.");
                                                 // schaut nach dem datei typ
                                                 switch (ending[1]) {
                                                     case "lnk":
                                                     case "url":
                                                     Process process1 = Runtime.getRuntime().exec("cmd /c start " + getexecution(words[2]));
                                                     //führt pfad in cmd aus
                                                     break;
                                                     case "exe":
                                                     Process process2 = new ProcessBuilder(getexecution(words[2])).start();
                                                     InputStream is = process2.getInputStream();
                                                     InputStreamReader isr = new InputStreamReader(is);
                                                     BufferedReader br = new BufferedReader(isr);
                                                         //führt pfad direkt aus
                                                     break;
                                                     case "jar":
                                                         Process process3 = Runtime.getRuntime().exec("cmd /c start " +"java -jar "+ getexecution(words[2]));
                                                         //führt pfad in cmd mit befehl java -jar aus
                                                         break;
                                                     default:
                                                         try {
                                                             Process process_try = Runtime.getRuntime().exec("cmd /c start " + getexecution(words[2]));
                                                             //versucht pfad in cmd auszuführen
                                                         } catch (Exception ex) {
                                                             System.err.println("can't use the File-type ." + ending[1]);
                                                         }
                                                         }
                                                    started = false;
                                                    Util.AUDIO("succes.wav");
                                                    break;


                                                case "read":
                                                    Util.read();
                                                    started = false;
                                                    Util.AUDIO("succes.wav");
                                                    break;
                                                case "end":
                                                    System.out.println("end jarbor");
                                                    Util.AUDIO("beendet.wav");
                                                    System.exit(69);
                                            }


                                        }

                                }
                                // und endet hier
                            }// wieder nur api kram
                        } else {
                            System.out.println("I can't hear what you said.\n");
                        }
                    }
                } else {
                    System.out.println("Cannot start microphone.");
                    recognizer.deallocate();
                    System.exit(1);
                }
            }
        } catch (IOException e) {
            System.err.println("Problem when loading HelloWorld: " + e);
            e.printStackTrace();
            System.exit(3);
        } catch (PropertyException e) {
            System.err.println("Problem configuring HelloWorld: " + e);
            e.printStackTrace();
        } catch (InstantiationException e) {
            System.err.println("Problem creating HelloWorld: " + e);
            e.printStackTrace();

        } catch (NullPointerException e) {
            e.printStackTrace();
            System.err.println("Problem runing HelloWorld: " + e);
            System.exit(2);
        }
    }


}
