/**
 * Esta clase es para la simulacion de la consola de ubuntu
 */
package crackftp;


public class Test {
    
    public static void main(String [] args){
        String p = "usuarios.txt claves.txtS";
        
        
        String[] parametros = p.split(" ");
        
        CrackFTP.main(parametros);
    }
}
