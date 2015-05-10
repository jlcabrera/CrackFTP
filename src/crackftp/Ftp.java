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
public class Ftp {
    private String usuario;
    private String clave;
    private Socket conexion;
    private BufferedReader in;
    private PrintWriter out;
    private String direccion;
    private int puerto;
    
    public Ftp(String usuario, String clave, String d, String p){
        this.usuario = usuario;
        this.clave = clave;
        this.direccion = d;
        this.puerto = Integer.valueOf(p);
        try {
            this.conexion = new Socket(this.direccion, this.puerto);
            in = new BufferedReader(new InputStreamReader(conexion.getInputStream()));
            out = new PrintWriter(conexion.getOutputStream());
            
        } catch (IOException ex) {
            Logger.getLogger(Ftp.class.getName()).log(Level.SEVERE, null, ex);
        }                
            
    }
    
    //Metodo para la autetiticacion del ftp
    public boolean autenticar(){
        boolean autenticado = false;
        try {
        if(this.in.readLine().contains("220")){
            escribirUsuario();
            if(this.in.readLine().contains("331")){
            escribirPass();
            }else{
                System.out.println("han ocurrido un error");
            }
            String respuesta;

                respuesta = this.in.readLine();

                System.out.println(respuesta);
                if(respuesta.contains("230")){
                    autenticado = true;
                }else{
                    this.out.println("bye");
                    this.out.flush();
                }
            }    
        } catch (IOException ex) {
            Logger.getLogger(Ftp.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return autenticado;
    }
    
    
    //Metodo par aescribir el usuario 
    public void escribirUsuario(){
        this.out.println("user " + this.usuario);
        this.out.flush();
    }
    
    //Metodo para escribir la contraseña
    public void escribirPass(){
        this.out.println("pass " + this.clave);
        this.out.flush();
    }
        
    
    //Metodo para cerrar los buffers y la conexion;
    public void cerrarConexion(){
        
        //Cerramos el stram de entrada
        if(this.in != null){
            try {
                this.in.close();
            } catch (IOException ex) {
                Logger.getLogger(Ftp.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        //Cerrams el stream de salida
        if(this.out != null){
            this.out.close();
        }
        
        //Cerramos la conexion
        if(this.conexion != null){
            try {
                this.conexion.close();
            } catch (IOException ex) {
                Logger.getLogger(Ftp.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
            
    }
}
