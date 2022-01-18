package datenbankenf;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Scanner;

public class Execute {

    private static final HashMap<String, String> execute = new HashMap<>();

    private static String prefix, path;

    public static void init( String ConfigFilePath){
        //setzt den pfad
        prefix = null;
        Path path2 = FileSystems.getDefault().getPath(".").toAbsolutePath();
        path = path2 + "/" + ConfigFilePath;
    }

    public static boolean hasmPrefix(String guildID){
        return execute.containsKey(guildID);
    }

    public static String getexecution(String setting){
        if(execute.containsKey(setting)){
            System.out.println("Setting:");
            System.out.println(execute.get(setting));
            return execute.get(setting);
        }else{
            System.out.println("no execution");
            return prefix;
        }
    }

    public static void setconfig(String setting, String setting2){
        execute.put(setting, setting2);
        saveToFile();
    }

    public static void executereadFromFile(){
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
                execute.put(data.split(":::")[0], data.split(":::")[1]);
            }
            System.out.println("execution ausgelesen");


        }catch (FileNotFoundException d){
            d.printStackTrace();
        }
    }

    public static void saveToFile(){
        try {
            FileWriter fileWriter = new FileWriter(path);
            StringBuilder lines = new StringBuilder();
            for (HashMap.Entry<String, String> set : execute.entrySet()) {
                lines.append(set.getKey()).append("€").append(set.getValue()).append("\n");
            }
            fileWriter.write(lines.toString());
            fileWriter.close();
            System.out.println("execution gespeichert");
        }catch (IOException d){
            d.printStackTrace();
        }
    }
}
