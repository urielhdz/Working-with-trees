/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package analizadorsintactico;

/**
 *
 * @author Uriel
 */
public class Nodo {
    String v = "";
    Nodo i;
    Nodo d;
    Nodo p;
    Nodo(String v){
        i = d = p = null;
        this.v = v;
    }
    
}
