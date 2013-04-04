/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package avl;

import javax.swing.JOptionPane;

/**
 *
 * @author Uriel
 */
public class AvlTree {
    AvlNode raiz = null;
    String respuesta = "";
    public void insertar(int v){
        AvlNode n = new AvlNode(v);
        insertarNodo(this.raiz,n);
    }
    public void insertarNodo(AvlNode padre, AvlNode actual){
        if(padre == null){
            this.raiz = actual;
        }
        else{
            if(actual.v < padre.v){
                if(padre.left == null){
                    padre.left = actual;
                    actual.padre = padre;
                    balancear(padre);
                }
                else{
                    insertarNodo(padre.left,actual);
                }
            }
            else if(actual.v > padre.v){
                if(padre.right == null){
                    padre.right = actual;
                    actual.padre = padre;
                    balancear(padre);
                }
                else{
                    insertarNodo(padre.right, actual);
                }
            }
            else{
                JOptionPane.showMessageDialog(null,"El nodo ya existe");
            }
        }
    }
    public void remover(int i){
        removerNodo(this.raiz,i);
    }
    public void removerNodo(AvlNode n,int i){
        if(n == null) {
            JOptionPane.showMessageDialog(null, "El nodo no se encontró");
            return;
        } 
        else{
            if(n.v>i) removerNodo(n.left,i);
            else if(n.v<i) removerNodo(n.right,i);
            else if(n.v == i) removerNodoEncontrado(n);
        }
    }
    public void buscarNodo(AvlNode n,int i){
        if(n == null) {
            JOptionPane.showMessageDialog(null, "El nodo no se encontró");
            return;
        } 
        else{
            if(n.v>i) buscarNodo(n.left,i);
            else if(n.v<i) buscarNodo(n.right,i);
            else if(n.v == i) JOptionPane.showMessageDialog(null,"El nodo se ha encontrado");
        }
    }
    public void removerNodoEncontrado(AvlNode n){
        AvlNode r;
        if(n.left ==null || n.right == null){
            if(n.padre == null){
                this.raiz = null;
                n = null;
                return;
            }
            r=n;
        }
        else{
            r = sucesor(n);
            n.v = r.v;
        }
        AvlNode p;
        if(r.left != null){
            p = r.left;
        }
        else{
            p = r.right;
        }
        if(p!=null){
            p.padre = r.padre;
        }
        if(r.padre == null){
            this.raiz = p;
        }
        else{
            if(r == r.padre.left){
                r.padre.left = p;
            }
            else{
                r.padre.right = p;
            }
            balancear(r.padre);
        }
        r = null;
    }
    public AvlNode sucesor(AvlNode n){
        if(n.right != null){
            AvlNode r= n.right;
            while(r.left!=null){
                r = r.left;
            }
            return r;
        }
        else{
            AvlNode p = n.padre;
            while(p!=null && n==p.right){
                n = p;
                p = n.padre;
            }
            return p;
        }
    }
    public void ArbolString(AvlNode n){
        
        if(n == null) return;
        ArbolString(n.left);
        ArbolString(n.right);
        respuesta += n.v +", ";
    }
    public void ArbolString2(AvlNode n){
        
        if(n == null) return;
        ArbolString2(n.left);
        hacerInOrden(n);
        ArbolString2(n.right);
        
    }
    public void hacerInOrden(AvlNode n){
        respuesta += n.v +", ";
    }
    public void balancear(AvlNode n){
        definirBalance(n);
        int balance = n.balance;
        if(balance ==-2){
            if(alturaArbol(n.left.left)>=alturaArbol(n.left.right)){
                System.out.println("Girar Derecha");
                n = girarDerecha(n);
            }
            else{
                if(n.padre.left != null) n.padre.left = dobleGiroIzquierdaDerecha(n);
                else if(n.padre.right != null) n.padre.right = dobleGiroDerechaIzquierda(n);
                System.out.println(n.v+" <- Valor que retorna Giro Izquierda Derecha");
                System.out.println(n.padre.v+" <- Valor del padre que retorna Giro Izquierda Derecha");
                this.respuesta = "";
                this.ArbolString2(this.raiz);
                System.out.println(this.respuesta);
            }
        }
        else if(balance == 2){
            if(alturaArbol(n.right.right)>=alturaArbol(n.right.left)){
                System.out.println("Girar Izquierda");
                n = girarIzquierda(n);
            }
            else{
                n = dobleGiroDerechaIzquierda(n);                

            }
        }
        if(n.padre != null){
            balancear(n.padre);
        }
        else{
            this.raiz = n;
            //JOptionPane.showMessageDialog(null,"Balance terminado");
        }
    }
    public void definirBalance(AvlNode n){
        n.balance = alturaArbol(n.right)-alturaArbol(n.left);
    }

    private AvlNode dobleGiroDerechaIzquierda(AvlNode n) {
        n.right = girarDerecha(n.right);
        return girarIzquierda(n);
    }

    private AvlNode girarIzquierda(AvlNode n) {
        AvlNode v = n.right;
        v.padre = n.padre;
        n.right = v.left;
        if(n.right != null){
            n.right.padre = n;
        }
        v.left = n;
        n.padre = v;
        if(v.padre != null){
            if(v.padre.right == n){
                v.padre.right = v;
            }
            else if(v.padre.left==n){
                v.padre.left = n;
            }
        }
        definirBalance(n);
        definirBalance(v);
        return v;
    }

    private AvlNode dobleGiroIzquierdaDerecha(AvlNode n) {
        System.out.println("Girar Izquierda Derecha");
        System.out.println(n.left.v+" <- Derecha");
        System.out.println(n.v+" <- Nodo actual");
        n.left = girarIzquierda(n.left);
        this.respuesta = "";
        this.ArbolString2(this.raiz);
        System.out.println(this.respuesta);
        AvlNode ay =  girarDerecha(n);
        System.out.println(ay.v+"<- Despues del cambio");
        this.respuesta = "";
        this.ArbolString2(this.raiz);
        System.out.println(this.respuesta);
        return ay;
    }

    private AvlNode girarDerecha(AvlNode n) {
        AvlNode v = n.left;
        v.padre = n.padre;
        n.left = v.right;
        if(n.left != null){
            n.left.padre = n;
        }
        v.right = n;
        n.padre = v;
        if(v.padre != null){
            if(v.padre.right == n){
                v.padre.right = v;
            }
            else if(v.padre.left==n){
                v.padre.left = n;
            }
        }
        definirBalance(n);
        definirBalance(v);
        return v;
    }
    private int  alturaArbol(AvlNode n) {
        if(n==null){
            return -1;
        }
        if(n.left==null && n.right == null){
            return 0;
        }
        else if(n.left == null){
            return 1+alturaArbol(n.right);
        }
        else if(n.right == null){
            return 1+alturaArbol(n.left);
        }
        else{
            return 1+maximoEnteros(alturaArbol(n.left),alturaArbol(n.right));
        }
    }

    private int maximoEnteros(int alturaArbol, int alturaArbol0) {
        return (alturaArbol > alturaArbol0) ? alturaArbol : alturaArbol0;
    }
}
