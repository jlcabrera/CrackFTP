/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package crackftp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ubuntuvirtual
 */
public class FTP {
    private String usuario;
    private String clave;
    private Socket conexion;
    private BufferedReader entrada;
    private PrintWriter salida;
    
    public FTP(String usuario, String clave){
        this.usuario = usuario;
        this.clave = clave;
        try {
            this.conexion = new Socket("localhost", 21);
            BufferedReader in = new BufferedReader(new InputStreamReader(conexion.getInputStream()));
            PrintWriter out = new PrintWriter(conexion.getOutputStream());
            
        } catch (IOException ex) {
            Logger.getLogger(FTP.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public boolean logar(){
        boolean logado = false;
        //escribirUsuario();
        //escribirContraseña();
        return logado;
    }
}
