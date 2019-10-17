package IN2;
import java.io.*;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class FileRederDemo {
    public static void main(String[] args) {
        String lineText ;
        BufferedReader bufferedReader = null;
        try {
            String dir = System.getProperty("user.dir");

            bufferedReader =  new BufferedReader(new FileReader(dir+"/src/file.txt"));
            while ((lineText = bufferedReader.readLine()) != null){
                System.out.println(lineText);
            }
        }catch (FileNotFoundException ex){
            ex.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

