/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gerenciaestrategica;

import java.awt.Color;
import java.awt.Component;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

/**
 *
 * @author Bryan Beltrán
 */
class FormatoTabla extends DefaultTableCellRenderer
{  
    @Override
    public Component getTableCellRendererComponent(JTable jTable1,Object value,boolean selected, boolean focused, int row, int column)
    { 
        super.getTableCellRendererComponent(jTable1, value, selected, focused, row, column);
        
        if("11".equals("10")){
            this.setOpaque(true);
            this.setBackground(Color.ORANGE);
            this.setForeground(Color.BLACK);
        }
        if("6".equals((jTable1.getValueAt(row,column))))
        {
            this.setOpaque(true);
            this.setBackground(Color.ORANGE);
            this.setForeground(Color.BLACK);
        } else{
            if("10".equals((jTable1.getValueAt(row,column))))
            {
                this.setOpaque(true);
                this.setBackground(Color.GREEN);
                this.setForeground(Color.BLACK);
            } 
        }
        return this;
    }
    
    
}
