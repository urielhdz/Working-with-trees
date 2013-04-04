/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hoffman;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

/**
 *
 * @author Uriel
 */
public class Read_Write {
    final static Charset ENCODING = StandardCharsets.UTF_8;
    public String readTextFile(String file_path) throws IOException{
        file_path.replace("\\" , "/");
        Path path = Paths.get(file_path);
        int nC;
        try (BufferedReader reader = Files.newBufferedReader(path, ENCODING)){
            String line = "";
            while((nC = reader.read())!= -1){
             line += new Character((char)nC).toString();
        }
        return line;
    }
    }
    public ArrayList<String> readTextLines(String file_path) throws IOException{
        Path path = Paths.get(file_path);
        ArrayList<String> answer = new ArrayList<>();
        try (BufferedReader reader = Files.newBufferedReader(path, ENCODING)){
            String line = null;
            while((line = reader.readLine())!= null){
                answer.add(line);
            }   
        }
        return answer;
    }
    public void writeTextFile(String file_path,String line) throws IOException{
        Path path = Paths.get(file_path);
        ArrayList<String> current_lines = new ArrayList<>();
        current_lines = this.readTextLines(file_path);
        
        try (BufferedWriter writer = Files.newBufferedWriter(path, ENCODING)){
            for(String c_line : current_lines){
                writer.write(c_line);
            }
            writer.write(line);
            //writer.newLine();
        }
    }
    public void clean(String file_path) throws IOException{
        Path path = Paths.get(file_path);
        ArrayList<String> current_lines = new ArrayList<>();
        current_lines = this.readTextLines(file_path);
        
        try (BufferedWriter writer = Files.newBufferedWriter(path, ENCODING)){
            writer.write("");
            //writer.newLine();
        }
    }
}
