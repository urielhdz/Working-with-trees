/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hoffman;

/**
 *
 * @author Uriel
 */
public class Nodo {
    Nodo der = null;
    Nodo left = null;
    Nodo padre = null;
    String l;
    int freq = 0;

    Nodo(String l,int f){
        this.l = l;
        this.freq = f;
    }

    Nodo(String l, int f, Nodo i, Nodo d) {
        this.l = l;
        this.freq = f;
        this.der = d;
        this.left = i;
    }
    public void setDer(Nodo n){
        this.der = n;
    }
    public void setLeft(Nodo n){
        this.left = n;
    }
    public void setValue(int n){
        this.freq = n;
    }
   public boolean esHoja(){
        return (this.left == null && this.der == null);
    }
    public Nodo getDer(){
        return this.der;
    }
    public Nodo getIzq(){
        return this.left;
    }
}
