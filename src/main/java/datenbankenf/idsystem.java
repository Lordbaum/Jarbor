package datenbankenf;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.util.UUID;

public class idsystem {
    private static String prefix, path;
    public static void init( String idFilePath) {
        //setzt den pfad
        Path path2 = FileSystems.getDefault().getPath(".").toAbsolutePath();
        path = path2 + "/" + idFilePath;
    }
    public static String idreadandwrite() {
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

            do {

                try {

                    String data = scanner.nextLine();
                    prefix = data;
                    String[] p =prefix.split("-");
                    if (p[2].equals("e6d9") && p[4].equals("qgy3")&& prefix.toCharArray().length ==46 ){
                        System.out.println("your client id for sync / ihre client-id zum syncronisren:");
                        System.out.println(prefix);

                    }else {
                        String[] aifajo = "a-d".split("-");
                        try {
                            String s =aifajo[3];
                        } catch (Exception e){
                            System.err.println(" an error occurred pleas reinstall the client");
                            e.printStackTrace();
                        System.exit(666);
                        }

                    }

                }catch (NoSuchElementException e) {
                    FileWriter fileWriter = new FileWriter(path);
                    String uniqueID = UUID.randomUUID().toString();
                    //5
                    String[] p =uniqueID.split("-");
                    String id = p[0] +"-" + p[1] +"-"+"e6d9"+"-" + p[2]+"-" + "qgy3" + "-" +p [3] +"-" + p[4];
                    fileWriter.write(id);
                    fileWriter.close();
                    Scanner scanner2 = new Scanner(file);
                   String data = scanner2.nextLine();
                    prefix =data;
                    idreadandwrite();
                }

                if(prefix.equals(null)|| prefix.equals("")||prefix.equals(" ")){
                }

            } while (scanner.hasNextLine());
            System.out.println("id ausgelesen");


        }catch ( IOException d){
            d.printStackTrace();
        }
    return prefix;
    }
}
