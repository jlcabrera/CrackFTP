/*
Esta es la clase que gestionará los hilos 
*/
package crackftp;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ubuntuvirtual
 */
public class GestorHilos {
    private String direccion;
    private String puerto;
    private int hilos;
    
    public GestorHilos(String d, String p, int hilos){
        this.direccion = d;
        this.puerto = p;
        this.hilos = hilos;
    }
    
    //Este método inicia el crackeo con los diferentes hilos. Inicialmente implementamos el metodo para un unico hilo
    public void iniciarCrack(){
        BufferedReader br = null;
        try {
            //fichero de usuarios
            br = new BufferedReader(new FileReader("usuarios.txt"));
            
            //Creamos los hilos que ejecutan las conexiones y comprobaciones de diccionario
            String usuario = br.readLine();
            Thread t = new Thread(new Hilo(usuario, "claves.txt", this.direccion, this.puerto));
            t.run();
            
        } catch (FileNotFoundException ex) {
            System.out.println("El fichero no se ha encontrado");
        } catch (IOException ex) {
            Logger.getLogger(GestorHilos.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("");
        }finally{
            if(br != null){
                try {
                    br.close();
                } catch (IOException ex) {
                    Logger.getLogger(GestorHilos.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }  
}
