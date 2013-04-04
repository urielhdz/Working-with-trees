/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hoffman;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;
import java.util.Hashtable;
import sun.misc.IOUtils;

/**
 *
 * @author Uriel
 */
public class Arbol{
    Nodo raiz;
    String cadenaArbol = "";
    String file_path;
    String patron = "1010110010101100";
    final static Charset ENCODING = StandardCharsets.UTF_8;
    Hashtable equivalencias = new Hashtable();
    Hashtable binary = new Hashtable();
     public Nodo construirArbol(Hashtable f){
        ArrayList l =  ordenarHash(f);
        ArrayList a = new ArrayList();
        for(int i =0;i<l.size();i++){
            ArrayList arr = (ArrayList) l.get(i);
            String key = arr.get(1).toString();
            int freq = Integer.parseInt(arr.get(0).toString());
            Nodo n = new Nodo(key,freq);
            a.add(n);
        }
        while(a.size() > 1){
            Nodo izq = (Nodo) a.remove(getKeyMin(a));
            Nodo der = (Nodo) a.remove(getKeyMin(a));
            Nodo padre = new Nodo("0",izq.freq+der.freq);
            padre.setDer(der);
            padre.setLeft(izq);
            a.add(padre);
        }
        raiz = (Nodo) a.get(0);
        escribirArbol(raiz);
        this.escribirTablaEq(raiz, "");
        return raiz;
    }
    public String decodificar(String fp) throws IOException{
        String s = "";
        Path path = Paths.get(fp);
        Read_Write rw = new Read_Write();
        String r = rw.readTextFile(fp);
        String[] str_array = r.split(this.patron);
        String arbol = str_array[0]; 
        String mensaje = str_array[1];
        System.out.println(arbol);
        System.out.println(mensaje);
        rw.clean(fp);
        rw.writeTextFile(fp, arbol);
        BufferedReader reader;
        reader = Files.newBufferedReader(path, Arbol.ENCODING);
        Nodo root = leerArbol(reader);
        this.escribirTablaEq(root, "");
        rw.clean(fp);
        rw.writeTextFile(fp,r);
        return this.leerMensaje(mensaje);
    }
    public Nodo leerArbol(BufferedReader reader) throws IOException{
        Nodo r = null;        
        int nC;
        int oC;
        nC = reader.read();
        if(nC == 49){        
            int contador = 0;
            String s = "";
            while(contador < 8){
                oC = reader.read();
                if(oC == 49) s += "1";
                if(oC == 48) s += "0";
                contador++;
            }
            char c = (char) Integer.parseInt(s,2);
            s = c + "";
            return new Nodo(s, -5, null, null);
        }else{
            return new Nodo("0", -1, leerArbol(reader),leerArbol(reader));
        }
        
    }
    public void escribirArbol(Nodo r){
        if(r.esHoja()){
            this.cadenaArbol += "1";
            String s = Integer.toBinaryString(r.l.charAt(0));
            while(s.length() <8){
                s = '0'+s;
            }
            this.cadenaArbol += s;
            return;
        }
        this.cadenaArbol += "0";
        escribirArbol(r.left);
        escribirArbol(r.der);
    }
    public String leerMensaje(String m){
        int s = maySize();
        String answ = "";
        String[] letras = m.split("(?!^)");
        for(int i=0;i<letras.length;i++){
            String r = "";
            int contador = i+s;
            
            while(i<contador && i<m.length()){
                String c = letras[i];
                r+= c;
                
                if(this.binary.containsKey(r)){
                    answ += this.binary.get(r);                    
                    break;                    
                }
                i++;
            }            
        }
        return answ;
    }
    public int maySize(){
        int r = -1;
        Enumeration e = this.equivalencias.keys();
        while(e.hasMoreElements()){
            String s = (String) this.equivalencias.get(e.nextElement());
            if(s.length() > r) r = s.length();
        }
        return r;
    }
     public void escribirTablaEq(Nodo r,String resultado){
        if(!r.esHoja()){
            escribirTablaEq(r.left,resultado+'0');
            escribirTablaEq(r.der,resultado+'1');
        }
        else{
            this.equivalencias.put(r.l, resultado);
            this.binary.put(resultado, r);
            this.binary.put(resultado, r.l);
        }
    }
    public void escribirMsj(String s) throws IOException{
        String a = "";
        String[] letras = s.split("(?!^)");
        for(int i=0;i<letras.length;i++){
            String c = letras[i];
            a = a + this.equivalencias.get(c).toString();
        }
        Read_Write rw = new Read_Write();
        rw.clean(this.file_path);
        rw.writeTextFile(this.file_path,this.cadenaArbol);
        rw.writeTextFile(file_path,patron );
        rw.writeTextFile(this.file_path,a);
    }
    public int getKeyMin(ArrayList l){
        int resultado = 0;
        int menor = 0;
        Nodo fr = (Nodo) l.get(0);
        menor = fr.freq;
        for(int i =0;i<l.size();i++){
            Nodo a = (Nodo) l.get(i);
            if(a.freq < menor) {
                menor = a.freq;
                resultado = i;
            }
        }
        return resultado;
    }
    public ArrayList ordenarHash(Hashtable f){
        ArrayList l = new ArrayList();
        ArrayList vals = new ArrayList();
        Enumeration e = f.keys();
        while(e.hasMoreElements()){
            String el = e.nextElement().toString();
            int v = Integer.parseInt(f.get(el).toString());
            ArrayList a = new ArrayList();
            a.add(v);
            vals.add(v);
            a.add(el);
            l.add(a);
        }        
        
        Collections.sort(vals);
        return l;
    }
    
    public int valorRaiz(Hashtable f){
        int resultado = 0;
        Enumeration e = f.keys();
        while(e.hasMoreElements()){
            int v = Integer.parseInt(e.nextElement().toString());
            resultado += v;
        }
        return resultado;
    }
    public ArrayList bubbleSort(ArrayList arr) {
      boolean swapped = true;
      int j = 0;
      ArrayList tmp;
      while (swapped) {
            swapped = false;
            j++;
            for (int i = 0; i < arr.size() - j; i++) {                                       
                ArrayList a = (ArrayList) arr.get(i);
                ArrayList a2 = (ArrayList) arr.get(i+1);
                  if (Integer.parseInt(a.get(0).toString()) > Integer.parseInt(a.get(0).toString())) {                          
                        tmp = a;
                        arr.set(i,a2);
                        arr.set(i+1,a);
                        swapped = true;
                  }
            }                
      }
      return arr;
    }
}
