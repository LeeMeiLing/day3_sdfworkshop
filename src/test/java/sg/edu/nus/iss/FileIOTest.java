package sg.edu.nus.iss;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import org.junit.jupiter.api.Test;

public class FileIOTest {
    
    String dirPath = ".\\data2";
    String stringLogin = "write.txt";
    String filePathName = dirPath + File.separator + stringLogin;

    @Test
    public void createDirectory(){
         // instantiating a file/directory object
         File newDirectory = new File (dirPath);

         if (newDirectory.exists()) {
             System.out.println("Directory already exists");
         } else {
             newDirectory.mkdir();
         }
    }

    @Test
    public void appendDataUsingBufferWriter() throws IOException{

        String str = "The quick brown fox jump over the wall.";

        BufferedWriter bw = new BufferedWriter(new FileWriter(filePathName,true));
        bw.write("\n");
        bw.write(str);
        bw.close();
    
    }
}
