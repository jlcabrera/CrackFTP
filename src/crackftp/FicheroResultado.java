package crackftp;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class FicheroResultado {
	
	private String fichero;
	private String linea;
	
	public FicheroResultado(String fichero, String linea){
		this.fichero = fichero;
		this.linea = linea;
	}
	
	public synchronized void escribirLinea(){
		
	}

}
