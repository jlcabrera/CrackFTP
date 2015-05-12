package crackftp;

import java.io.BufferedWriter;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class FicheroResultado {
	
	private String linea;
	private BufferedWriter bw;
	
	public FicheroResultado(String linea, BufferedWriter bw){
		this.linea = linea;
		this.bw = bw;
	}
	
	public synchronized void escribirLinea(){
            try {
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
