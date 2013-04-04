/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hoffman;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Dictionary;
import java.util.Enumeration;
import java.util.Hashtable;

/**
 *
 * @author Uriel
 */
public class Hoffman {

    /**
     * @param args the command line arguments
     */
    
    public static void main(String[] args) throws IOException {
        /*Hashtable f = new Hashtable();
        Arbol a = new Arbol();
        Arbol a2 = new Arbol();
        Read_Write rw = new Read_Write();
        String palabra = rw.readTextFile("texto.txt");
        String toC = "MaMe";
        String[] letras = palabra.split("(?!^)");
        for(int i=0;i<letras.length;i++){
            String c = letras[i];
            if(f.containsKey(c)){
                int v = Integer.parseInt(f.get(c).toString());
                v++;
                f.put(c,v);
            }
            else{
                f.put(c,1);
            }
        }
        a.file_path = "compreso.txt";
        Nodo n = a.construirArbol(f);        
        a.escribirMsj(toC);
        a2.file_path = "compreso.txt";
        Path path = Paths.get("compreso.txt");
        BufferedReader reader;
        reader = Files.newBufferedReader(path, a2.ENCODING);
        String s = a2.decodificar("compreso.txt");*/
       //System.out.println(":P "+n.der.der.left.l);
        interfazHuffman  f = new interfazHuffman();
        f.setBounds(0, 0, 850, 720);
        f.setVisible(true);
    }
   
}
