package abb;

import javax.swing.JOptionPane;


/**
 *
 * @author LordOfWolvez
 */
public class ABB {
    Integer raiz; 
    ABB subABizq; 
    ABB subABder; 
    static String numeros;
    static String numerosPostOrden;
    static String numerosPreOrden;
    
    public ABB() { 
        raiz = null; 
        subABizq = null; 
        subABder = null; 
        numeros="";
        numerosPostOrden="";
        numerosPreOrden="";
    } 
     

    public ABB( Integer raiz ) {
        this.raiz = new Integer( raiz ); 
        subABizq = null; 
        subABder = null; 
        numeros="";
        numerosPostOrden="";
        numerosPreOrden="";
    } 
     
    private boolean esVacio() { 
        boolean vacio = true; 
         
        if ( raiz != null ) 
            vacio = false; 
         
        return vacio; 
    } 
     
    public void insertar( Integer nuevo ) { 
        if( esVacio() ) { 
            raiz = new Integer( nuevo ); 
            subABizq = new ABB(); 
            subABder = new ABB(); 
            balancear(this);
        } else { 
            if( nuevo < raiz ) { 
                subABizq.insertar(nuevo); 
            } else if ( nuevo > raiz ) { 
                subABder.insertar(nuevo); 
            }
        }
    } 
    public void balancear(ABB t ){
        
    }
    public ABB buscar( Integer x ) { 
        ABB buscado = null; 
         
        if( !esVacio() ) { 
            if( x < raiz ) { 
                buscado = subABizq.buscar(x); 
            } else if ( x > raiz ) { 
                buscado = subABder.buscar(x); 
            } else { 
                return this; 
            } 
        } 
         
        return buscado; 
    } 
     
    private ABB buscarMin() { 
        ABB arbolActual = this; 
         
        while( !arbolActual.subABizq.esVacio() ) { 
            arbolActual = arbolActual.subABizq; 
        } 
         
        return arbolActual; 
    } 
     
    private boolean esHoja() { 
        boolean hoja = false; 
         
        if( subABizq.esVacio() && subABder.esVacio() ) { 
            hoja = true; 
        } 
         
        return hoja; 
    } 
     
    public void eliminar( Integer a ) { 
        ABB aEliminar = buscar(a); 

        if (aEliminar != null) { 
            if( aEliminar.esHoja() ) { 
                aEliminar.raiz = null; 
            } else { 
                ABB min =  aEliminar.subABder.buscarMin(); 
                aEliminar.raiz = min.raiz; 
                min.eliminar(min.raiz); 
            } 
        } 
    } 
     
    public void preOrden() {
        
        if( !esVacio() ) {
            //JOptionPane.showMessageDialog(null, raiz ); 
            hacerPreOrden(raiz);
            subABizq.preOrden(); 
            subABder.preOrden(); 
        }
    } 
     
    public void enOrden() {
        if( !esVacio() ) { 
            subABizq.enOrden(); 
            //JOptionPane.showMessageDialog(null, raiz ); 
            hacerInOrden(raiz);
            subABder.enOrden(); 
        }
    }
     
    public void postOrden() {
        if( !esVacio() ) { 
            subABizq.postOrden(); 
            subABder.postOrden(); 
            //JOptionPane.showMessageDialog(null, raiz ); 
            hacerPostOrden(this.raiz);
        }
    }
    
    private void hacerInOrden(Integer x){
        numeros = numeros + x.toString() +", ";
    }
    
    private void hacerPostOrden(Integer x){
        numerosPostOrden = numerosPostOrden + x.toString() + ", ";
    }
    private void hacerPreOrden(Integer x){
        numerosPreOrden = numerosPreOrden + x.toString() + ", ";
    }
    
    public String getInOrden(){
        numeros = "";
        enOrden();
        System.out.println("Regresando Inorden " + numeros);
        return numeros;
    }
    
    public String getPostOrden(){
        numerosPostOrden="";
        postOrden();
        System.out.println("Regresando Postorden" + numerosPostOrden);
        return numerosPostOrden;
    }
    
    public String getPreOrden(){
        numerosPreOrden="";
        preOrden();
        System.out.println("Regresando Preorden" + numerosPreOrden);
        return numerosPreOrden;
    }
}