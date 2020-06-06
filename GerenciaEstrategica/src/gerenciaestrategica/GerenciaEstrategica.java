/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.}
MiRender s = new MiRender();
        Variacion [] vector = s.cargarVariaciones(4);
        for(int i = 0 ; i < 4 ; i++){
            System.out.println(vector[i].inicioR + " - "  + vector[i].finR + " - "  + vector[i].inicioA + " - " + vector[i].finA +  " - " + vector[i].inicioV + " - " + vector[i].finV +  " - ");
        }
 */
package gerenciaestrategica;

/**
 *
 * @author Bryan Beltrán
 */
public class GerenciaEstrategica {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        Principal obj = new Principal();
        obj.setVisible(true);
        
    }
    
}
