/*
Esta es la clase que gestionará los hilos 
 */
package crackftp;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
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
//	private int contadorHilos
	private  List<Hilo> hilosEjecucion = new ArrayList<Hilo>();

	public GestorHilos(String d, String p, int hilos) {
		this.direccion = d;
		this.puerto = p;
		this.hilos = hilos;
//		this.contadorHilos = 0;
	}

	// Este método inicia el crackeo con los diferentes hilos. Inicialmente
	// implementamos el metodo para un unico hilo
	public void iniciarCrack() {
		BufferedReader br = null;
		try {
			// fichero de usuarios
			br = new BufferedReader(new FileReader("usuarios.txt"));

			// Creamos los hilos que ejecutan las conexiones y comprobaciones de
			// diccionario
			while (this.hilosEjecucion.size() <= this.hilos) {
				String usuario = br.readLine();
				if (usuario == null) {
					break;
				}
				bloquearHilos();
				
				Hilo h = new Hilo(usuario, "claves.txt",this.direccion, this.puerto, this);
				Thread t = new Thread(h);
				t.start();
				añadirHilo(h);
                               
			}
                                                 
		} catch (FileNotFoundException ex) {
			System.out.println("El fichero no se ha encontrado");
		} catch (IOException ex) {
			Logger.getLogger(GestorHilos.class.getName()).log(Level.SEVERE,
					null, ex);
		} finally {
                    hilosTerminados();
			// Cerramos el fichero para leer el usuario.
			if (br != null) {
				try {
					br.close();
				} catch (IOException ex) {
					Logger.getLogger(GestorHilos.class.getName()).log(
							Level.SEVERE, null, ex);
				}
			}
		}
	}

	// Metodo para incrementar el contador de los hilos en ejecución
	public synchronized void añadirHilo(Hilo hilo){
		this.hilosEjecucion.add(hilo);
	}

	// Metodo para disminuir en contador de los hilos en ejecución
	public synchronized void quitarHilo(Hilo hilo){
		this.hilosEjecucion.remove(hilo);
	}
	// Metodo para desbloquear el bucle que genera hilos
	public synchronized void desbloquearHilos() {
		this.notifyAll();
	}

	// Metodo para bloquear la creación de hilos
	public synchronized void bloquearHilos() {
		while (this.hilosEjecucion.size() == this.hilos) {
			try {
				System.out
						.println("Esperando a que termine uno de los hilos en ejecucion termine");
				this.wait();
			} catch (InterruptedException ex) {
				Logger.getLogger(GestorHilos.class.getName()).log(Level.SEVERE,
						null, ex);
			}
		}
	}
	
	public synchronized void hilosTerminados(){
		while(this.hilosEjecucion.size() != 0){
			try {
				this.wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
                        }
		}
                System.out.println("ejecucion terminada");
	}
}
