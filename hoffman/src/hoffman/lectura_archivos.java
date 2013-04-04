
package hoffman;

import java.util.HashMap;

/**
 *
 * @author Rigoberto Garcia
 */
import java.util.ArrayList;
public class lectura_archivos {
    
    private HashMap tabla = new HashMap();
    public String archivo;
    public char [] letritas;
    private ArrayList lista = new ArrayList();
    
    
    public lectura_archivos(String archivo){
        this.archivo = archivo+" ";//tiene una espacio al final por que en la busqueda  resto un caracter
        letritas = archivo.toCharArray();
    }
    
    public void tabla_frecuencias(){
        
        for(int x=0;x<letritas.length;x++){
             if(!tabla.containsKey(letritas[x])){
                String caracter = String.valueOf(letritas[x]);
                 tabla.put(letritas[x], buscar_palabra(caracter));
                 lista.add(letritas[x]);
            }else{
               //En caso de que la llave se encuentre 
             }   
        }
    }
    //Busca la palabra o letra 
    public int buscar_palabra(String Palabra){
    if (!(archivo.compareTo("")==0) && (!(Palabra.compareTo("")==0))){
			int contador=0;
			for(int x=0;x<(archivo.length()-Palabra.length());x++){
				if(archivo.substring(x,(x+Palabra.length())).compareTo(Palabra)==0){
				contador++;
				}
			}
			return contador;
		}//En caso se busque una letra en especifico o palabra 
		else{	System.out.println("No contiene nada :/");
                return 0;
                }
    }
    public void mostrar_tabla(){
    for(int x=0;x<lista.size();x++){
        System.out.println(tabla.get(lista.get(x)));
    }
    
    
    }
 public int getCantidad(){
     //Solo regresa la cantida de carcateres diferentes
     return lista.size();
 }
 
 public int getCantida_caracteres(){
    //regresa el tamaño del parrafo
     return letritas.length;
 }
 public void getTabla_frecuencias(){
     for(int x=0;x<lista.size();x++){
         System.out.print(lista.get(x)+"   "+tabla.get(lista.get(x))+"\n");
     }
     
 }
 public HashMap getTabla(){
     return tabla;
 }
 
}
