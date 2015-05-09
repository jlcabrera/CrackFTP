/**
 * Clase principal donde realizar las diferentes pruebas
 */
package crackftp;

import java.io.File;

public class CrackFTP {

	private static String usuarios;
	private static String claves;
	private static int hilos;

	public static void main(String[] args) {
            if (args != null && args.length >= 2 && args.length <= 3) {

                if (validarParametros(args)) {
                        // crackeadro con hilos
                    System.out.println("Parametros validos");
                } else {
                    System.out.println("Parametros no validos");
                }
            } else {
                    System.out.println("El numero de parametros no es el correcto");
            }
	}

	// metodo para validar los parametros que se pasen a través de la consola
	public static boolean validarParametros(String[] params) {
            boolean paramsValidos = false;
            boolean ficherosValidos = false;
            boolean hilosValidos = false;

            String u = params[0], c = params[1], h = "";
            if (params.length == 3) {
                h = params[2];
            }

            // Empezamos comprobando si existen los ficheros que nos pasan por
            // parametros llamando a otro metodo
            ficherosValidos = validarFicheros(u, c);

            // Comprobamos con el siguiente metodo si el parametro numero de hilos
            // está o no, y si es así asignamos el numero de hilos
            hilosValidos = validarNumeroHilos(h);

            if (ficherosValidos && hilosValidos) {
                paramsValidos = true;
            }
            return paramsValidos;
	}

	// metodo para validar los ficheros que nos deberan de pasar por parametros
	// cuando ejecuten la aplicacion
	public static boolean validarFicheros(String usuarios, String claves) {
		boolean validos = false;

		File fusuarios = new File(usuarios);
		File fclaves = new File(claves);
		if (fusuarios.exists() && fclaves.exists()) {
			validos = true;
		} else {
			System.out.println("alguno de los ficheros no está bien escrito o no están en la ubicación correcta");
		}
		
		// Con esto comprobamos que los ficheros no sean iguales de ser así
		// mostramos un mensaje al usuario pero no interrumpimos su
		// comportamiento
		if (fusuarios.equals(fclaves)) {
			System.out.println("los ficheros pasado por parametros son iguales");
		}
		return validos;
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
