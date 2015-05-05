/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author ubuntuvirtual
 */
public class Main {
    public static void main(String args[]){
        //new Thread().start();
        //new Thread().start();
        
        Thread t = new Thread(new TestHilos(100000, "1"));
        t.start();
        
        Thread t1 = new Thread(new TestHilos(100000, "2"));
        t1.start();
    }
    
    //esto es una prueba de como hacer un comit desde eclipse... TY
}
