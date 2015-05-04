/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author ubuntuvirtual
 */
public class TestHilos implements Runnable {
    
    private int num;
    private String nombre;

    public TestHilos(int Numero, String nombre){
        this.num = Numero;
        this.nombre = nombre;
    }
    @Override
    public void run() {
        for (int i = 0; i <= num; i++){
            System.out.println("Subproceso " + nombre + " " + i);
        }
    }
    
}
