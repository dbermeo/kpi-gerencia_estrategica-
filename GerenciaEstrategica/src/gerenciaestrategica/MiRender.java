/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gerenciaestrategica;

/**
 *
 * @author Bryan Beltrán
 */
import java.awt.Component; 
import java.awt.Color; 
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JTable; 
import javax.swing.table. DefaultTableCellRenderer; 
import javax. Swing. *; 
import javax.swing.JLabel;

public class MiRender extends DefaultTableCellRenderer { 

@Override
public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) { 

JLabel cell = (JLabel) super. getTableCellRendererComponent (table, value, isSelected, hasFocus, row, column); 
if(value instanceof Float){
    float valor = (float) value;
    System.err.println("row" + row);
    
    int numeroFilas = (table.getModel().getRowCount() - 1);
    int numeroVector = table.getModel().getRowCount();
    Variacion [] vector = cargarVariaciones(numeroVector);
    
    for(int i = 0 ; i <= numeroFilas ; i++){
     if(row == i){
        if(column == 10){
            if(valor >= vector[i].inicioR && valor <= vector[i].finR){
                cell.setBackground(Color.red);
                cell.setForeground(Color.WHITE);
            }else{
                if(valor >= vector[i].inicioA && valor <= vector[i].finA){
                    cell.setBackground(Color.YELLOW);
                    cell.setForeground(Color.BLACK);
                }else{
                    if(valor >= vector[i].inicioV && valor <= vector[i].finV){
                        cell.setBackground(Color.GREEN);
                        cell.setForeground(Color.BLACK);
                    }
                }
            }
        }
        
    }   
    }
    
    
}
 
return cell; 
} 

public Variacion [] cargarVariaciones(int cantidad){
    ClsConexion conn2 = new ClsConexion();
    Variacion [] vector = new Variacion [cantidad];
    String sql = "select * from variacion where id_oa = 0 order by id_oe";
    ResultSet rs = conn2.Consulta(sql);
    int a = 0;
    try {
        while(rs.next()){
            Variacion var = new Variacion();
            var.inicioR = rs.getDouble(4);
            var.finR = rs.getDouble(5);
            var.inicioA = rs.getDouble(6);
            var.finA = rs.getDouble(7);
            var.inicioV = rs.getDouble(8);
            var.finV = rs.getDouble(9);
            vector[a] = var;
            a++;
        }
    } catch (SQLException ex) {
        Logger.getLogger(MiRender.class.getName()).log(Level.SEVERE, null, ex);
    }
    return vector;
}

} 