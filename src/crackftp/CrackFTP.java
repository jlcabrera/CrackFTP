/**
 * Clase principal donde realizar las diferentes pruebas
 */
package crackftp;


import java.io.File;
import java.io.IOException;
import java.net.NoRouteToHostException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CrackFTP {
 
	
	private static int hilos;

	public static void main(String[] args) {
            if (args != null && args.length >= 2 && args.length <= 3) {

                if (validarParametros(args)) {
                        
                    System.out.println("Parametros validos");
                    
                    //Creamos el objeto para crackear hilos
                    GestorHilos gh = new GestorHilos(args[0], args[1], CrackFTP.hilos);
                    long inicio = System.currentTimeMillis();
                    gh.iniciarCrack();
                    long fin = System.currentTimeMillis();
                    System.out.println("El programa ha tardado en terminar:as " + (fin - inicio) / 1000);
                } else {
                    System.out.println("Parametros no validos");
                }
            } else {
                    System.out.println("El numero de parametros no es el correcto");
            }
	}

	// metodo para validar los parametros que se pasen a través de la consola
	public static boolean validarParametros(String[] params) {
            
            String d = params[0], p = params[1], h = "";
            if (params.length == 3) {
                h = params[2];
            }
            validarDatosConexion(d, p);
            return validarFicheros() && validarNumeroHilos(h);
	}
        
	// metodo para validar los ficheros que nos deberan de pasar por parametros
	// cuando ejecuten la aplicacion
        
        public static boolean validarFicheros(){
            boolean validos = false;

		File fusuarios = new File("usuarios.txt");
		File fclaves = new File("claves.txt");
		if (fusuarios.exists() && fclaves.exists()) {
			validos = true;
		} else {
			System.out.println("Algunos de los ficheros (usuario o claves) no existe o no esta en la ubicacion correcta");
		}
                return validos;
        }
        
        // metodo que comprueba los datos de conexion, 
	public static void validarDatosConexion(String direccion, String puerto) {
            Socket s = null;
            try {
                //solo validamos el puerto ya que tiene un rango limitado la validación, dejamos que sea el socket que realice tal tarea a través de su excepcion

                s = new Socket(direccion, Integer.valueOf(puerto));
            }catch(UnknownHostException e){
                System.out.println("La direccion de la maquina es desconocido");
                System.exit(0);
            }catch(NoRouteToHostException e){
                System.out.println("No hay visibilidad hasta el servidor");
                System.exit(0);
            }catch (IOException ex) {
                Logger.getLogger(CrackFTP.class.getName()).log(Level.SEVERE, null, ex);
                System.exit(0);
            }finally{
                if(s != null){
                    try {
                        s.close();
                    } catch (IOException ex) {
                        Logger.getLogger(CrackFTP.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
            
	}

	// metodo para validar si nos han pasado un numero determinado de hilos o
	// no;
	public static boolean validarNumeroHilos(String hilos) {
		boolean valido = false;

		if (!hilos.equalsIgnoreCase("")) {
                    try {
                            CrackFTP.hilos = Integer.valueOf(hilos);
                            valido = true;
                    } catch (NumberFormatException e) {
                            System.out
                                            .println("El parámetro correspondiente al número de hilos no es correcto");
                    }
		} else {
			CrackFTP.hilos = Integer.MAX_VALUE;
			valido = true;
		}

		return valido;
	}
        

}
