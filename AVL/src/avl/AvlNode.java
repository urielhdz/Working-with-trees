/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package avl;

/**
 *
 * @author Uriel
 */
public class AvlNode {
    int v = 0;
    int balance = 0;
    AvlNode right;
    AvlNode padre;
    AvlNode left;
    public AvlNode(int v){
        this.v = v;
    }
    
}
