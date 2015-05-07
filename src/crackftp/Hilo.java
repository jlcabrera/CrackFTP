/*
 Esta clase tiene el comportamiento de cada hilo
 */
package crackftp;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Hilo implements Runnable{
    
    private String usuario;
    private BufferedReader claves;
    
    public Hilo(String usuario, String fclaves){
        this.usuario = usuario;
        try {
            this.claves = new BufferedReader(new FileReader(fclaves));
        } catch (FileNotFoundException ex) {
            System.out.println("No se ha encontrado el fichero indicado");
            Logger.getLogger(Hilo.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    

    @Override
    public void run() {

        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    public void realizarComprobacion(String clave){
        
        FTP conexion = new FTP(this.usuario, clave);
    }
}
