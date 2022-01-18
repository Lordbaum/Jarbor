package datenbankenf;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Scanner;

public class playcontent {

    private static final HashMap<String, String> content = new HashMap<>();

    private static String prefix, path;

    public static void init(String defaultlink, String ContentFilePath){
        //setzt den pfad
        prefix = defaultlink;
        Path path2 = FileSystems.getDefault().getPath(".").toAbsolutePath();
        path = path2 + "/" + ContentFilePath;
    }

    public static boolean hasmPrefix(String guildID){
        return content.containsKey(guildID);
    }

    public static String getlink(String link){
        if(content.containsKey(link)){
            System.out.println("link:");
            System.out.println(content.get(link));
            return content.get(link);
        }else{
            System.out.println("no link found");
            return prefix;
        }
    }

    public static void setconfig(String setting, String setting2){
        content.put(setting, setting2);
        saveToFile();
    }

    public static void linksreadFromFile(){
        try {

            File file = new File(path);
            try {
                if (file.createNewFile()) {
                    System.out.println("File created: " + file.getName());
                } else {
                    System.out.println("File already exists.");
                }
            } catch (IOException error) {
                System.out.println("An error occurred.");
                error.printStackTrace();
            }
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                String data = scanner.nextLine();
                //System.out.println(data.split(":::")[0] + " : " + data.split(":::")[1]);

                content.put(data.split(":::")[0], data.split(":::")[1]);
            }

            System.out.println("links ausgelesen");


        }catch (FileNotFoundException d){
            d.printStackTrace();
        }
    }

    public static void saveToFile(){
        try {
            FileWriter fileWriter = new FileWriter(path);
            StringBuilder lines = new StringBuilder();
            for (HashMap.Entry<String, String> set : content.entrySet()) {
                lines.append(set.getKey()).append(":").append(set.getValue()).append("\n");
            }
            fileWriter.write(lines.toString());
            fileWriter.close();
            System.out.println("Config gespeichert");
        }catch (IOException d){
            d.printStackTrace();
        }
    }
}
