/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author ubuntuvirtual
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
public class TestFTP {
    public static void main(String [] args){
        
        try {
            Socket s = new Socket("localhost", 21);
            
            System.out.println("socket abierto");
            BufferedReader entrada = new BufferedReader(new InputStreamReader(s.getInputStream()));
            PrintWriter salida = new PrintWriter(s.getOutputStream());
            String mensaje = entrada.readLine();
            if(mensaje.contains("220")){
                System.out.println(mensaje);
                System.out.println("login de ftp ok");
                salida.println("user usuario");
                salida.flush();
                
                //System.out.println(entrada.readLine());
                salida.println("pass usuario");
                salida.flush();
                
                System.out.println(entrada.readLine());
//                
            }else{
                System.out.println("error login");
            }
           
            
            
        } catch (IOException ex) {
            Logger.getLogger(TestFTP.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("No se ha podido realizar la conexión");
        }
    }
}
