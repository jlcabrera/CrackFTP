package crackftp;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class FicheroResultado {
	
	private String fichero;
	private String linea;
	
	public FicheroResultado(String fichero, String linea){
		this.fichero = fichero;
		this.linea = linea;
	}
	
	public synchronized void escribirLinea(){
             BufferedWriter bw = null;
            try {
                bw = new BufferedWriter(new FileWriter(this.fichero, true));
                bw.write(this.linea);
                bw.newLine();
                bw.flush();
            } catch (IOException ex) {
                Logger.getLogger(FicheroResultado.class.getName()).log(Level.SEVERE, null, ex);
            }finally{
                if(bw != null){
                    try {
                        bw.close();
                    } catch (IOException ex) {
                        Logger.getLogger(FicheroResultado.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
	}

}
