package analizadorsintactico;


import java.util.ArrayList;
import java.util.Collections;
import java.util.Queue;
import java.util.Stack;
import javax.swing.JOptionPane;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Uriel
 */
public class ShuntingYard {
    String s = "";
    ArrayList l;
    String [] numeros = {"0","1","2","3","4","5","6","7","8","9"};
    String [] o1 = {"+","-"};
    String [] o2 = {"*","/"};
    String [] seperadores = {"(",")"};
    ArrayList salida = new ArrayList();
    Stack op = new Stack();
    ShuntingYard(String s){
        l = new ArrayList();
        l = this.convertirALista(s);
    }
    public String aRPN(){
        String r = "";
        if(l == null){
            return r;
        }
        for(Object o : l){
            String s = o.toString();
            if(esNumero(s)){
                salida.add(s);
            }
            else if(esOperador2(s)){
                if(op.empty()) op.add(s);
                else{
                    while(esOperador2(op.peek().toString())){
                        String a = (String) op.pop();
                        salida.add(a);
                        if(op.empty()) break;
                    }
                    op.add(s);
                }
            }
            else if(esOperador1(s)){
                
                if(op.empty()) op.add(s);
                else{
                    while(esOperador2(op.peek().toString())){
                        String a = (String) op.pop();
                        salida.add(a);
                        if(op.empty()) break;
                    }
                    
                    op.add(s);
                }
            }
            else if(esSeperador(s)){
                if(s.equals("(")){
                    op.add(s);
                }
                else if(s.equals(")")){
                    boolean b = true;
                    while(b){
                        if(op.empty()){
                            JOptionPane.showMessageDialog(null, "Error de sintáxis");
                            break;
                        }
                        String ay = (String) op.pop();
                        if(ay.equals("(")){
                            b = false;
                        }
                        else{
                            salida.add(ay);
                        }
                    }
                }
            }
        }
        while(!op.empty()){
            String ay = (String) op.pop();
            if(ay.equals("(") || ay.equals(")")) {
                JOptionPane.showMessageDialog(null, "Error de sintáxis");
                return null;
            }
            salida.add(ay);
        }
        //Collections.reverse(salida);
        return arrayString(salida);
        
    }
    private String pasoDos(){
        String r = "";
        
        return r;
    }
    private String arrayString(ArrayList al){
        String r = "";
        for(Object o: al){
            String ay = (String) o;
            r += ay;
        }
        return r;
    }
    private boolean esNumero(String s){
        boolean b = false;
        for(String i : numeros){
            if(i.equals(s)) b = true;
        }
        return b;
    }
    private boolean esOperador1(String s){
        boolean b = false;
        for(String i : o1){
            if(i.equals(s)) b = true;
        }
        return b;
    }
    private boolean esOperador2(String s){
        boolean b = false;
        for(String i : o2){
            if(i.equals(s)) b = true;
        }
        return b;
    }
    private boolean esSeperador(String s){
        boolean b = false;
        for(String i : seperadores){
            if(i.equals(s)) b = true;
        }
        return b;
    }
    private ArrayList convertirALista(String s){
        ArrayList r = new ArrayList();
        String a;
        //char [] c = s.toCharArray();
        for(Character c : s.toCharArray()){
             a = c.toString();
            r.add(a);
        }
        return r;
    }
    
}
