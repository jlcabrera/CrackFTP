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
import java.net.ConnectException;
import java.net.Socket;
import java.net.SocketException;
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
    private static final String LOGIN_SUCCESFUL = "230";
    private static final String CONEXION_REALIZADA = "220";
    private static final String PETICION_PASS = "331";
    
    public Ftp(String usuario, String clave, String d, String p){
        this.usuario = usuario;
        this.clave = clave;
        this.direccion = d;
        this.puerto = Integer.valueOf(p);
        try {
            this.conexion = new Socket(this.direccion, this.puerto);
            in = new BufferedReader(new InputStreamReader(conexion.getInputStream()));
            out = new PrintWriter(conexion.getOutputStream());
            
        } catch(ConnectException e){
            System.out.println("La conexion fue rechazada");
            
        }catch(SocketException e){
            System.out.println("Ha ocurrido un problema con el socket");
        }catch (IOException ex) {
            Logger.getLogger(Ftp.class.getName()).log(Level.SEVERE, null, ex);
        }           
            
    }
    
    //Metodo para la autetiticacion del ftp
    public boolean autenticar(){
        boolean autenticado = false;
        try {
        if(this.in.readLine().contains(CONEXION_REALIZADA)){
            escribirUsuario();
            if(this.in.readLine().contains(PETICION_PASS)){
            escribirPass();
            }else{
                System.out.println("han ocurrido un error");
            }
            String respuesta = this.in.readLine();
                if(respuesta.contains(LOGIN_SUCCESFUL)){
                    autenticado = true;
                }else{
                    this.out.println("bye");
                    this.out.flush();
                }
            }    
        } catch (IOException ex) {
            Logger.getLogger(Ftp.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
        	cerrarConexion();
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
