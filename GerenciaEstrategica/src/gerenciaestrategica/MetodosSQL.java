/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gerenciaestrategica;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author COMPANY
 */
public class MetodosSQL {
    String cajero = "";
    public void EsCajero(String quien){
        
        cajero = quien;
    
            }
    public String DarCajero(){
        
        return cajero;
    
            }
    
public Connection ConexionBD() throws SQLException{
        ClsConexion conn1 = new ClsConexion();
        Connection conn = conn1.getConexion();
    return conn;
}
    
    
public Connection ConexionBD1() throws SQLException {
Connection conn = null;
try{
//Cargar driver
Class.forName("oracle.jdbc.driver.OracleDriver");
String url = "jdbc:oracle:thin:@172.17.37.44:1521:orcl";
conn = DriverManager.getConnection(url,"C##CUTOSB","1234");

}
catch(ClassNotFoundException e){
JOptionPane.showMessageDialog(null, "Error en driver");
}
catch (SQLException ex){
System.out.println("ERROR: Error al cargar la clase del Driver");
System.out.println("SQLException:" + ex.getMessage());
System.out.println("SQLState: " + ex.getSQLState());
System.out.println("Error G.: " + ex.getErrorCode());
}
        return conn;
    }
    
public Connection ConexionBDCLIENTE() throws SQLException {
Connection conn = null;
try{
//Cargar driver
Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
String url = "jdbc:sqlserver://localhost;databaseName = BASEDATOSCINE";
conn = DriverManager.getConnection(url,"login_3","enero2016");
}
catch(ClassNotFoundException e){
JOptionPane.showMessageDialog(null, "Error en driver");
}
catch (SQLException ex){
System.out.println("ERROR: Error al cargar la clase del Driver");
System.out.println("SQLException:" + ex.getMessage());
System.out.println("SQLState: " + ex.getSQLState());
System.out.println("Error G.: " + ex.getErrorCode());
}
        return conn;
    }


public Connection ConexionBDCajero() throws SQLException {
Connection conn = null;
try{
//Cargar driver
Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
String url = "jdbc:sqlserver://localhost;databaseName = BASEDATOSCINE";
conn = DriverManager.getConnection(url,"login_1","enero2016");
}
catch(ClassNotFoundException e){
JOptionPane.showMessageDialog(null, "Error en driver");
}
catch (SQLException ex){
System.out.println("ERROR: Error al cargar la clase del Driver");
System.out.println("SQLException:" + ex.getMessage());
System.out.println("SQLState: " + ex.getSQLState());
System.out.println("Error G.: " + ex.getErrorCode());
}
        return conn;
    }

     public java.sql.Statement Sentecia(Connection conn)
    {
        java.sql.Statement estSQL1 = null;
        try {
            if(conn!=null){
               // JOptionPane.showMessageDialog(null, "Conexion establecida");
                estSQL1 = conn.createStatement();
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error en la c");
            e.printStackTrace();
        }catch(Exception e1){
            JOptionPane.showMessageDialog(null,"Error.....");
            e1.printStackTrace();
        }
        return estSQL1;
    }
    
    
    
   public  void Insercion(String cadsql1, java.sql.Statement estSQL){
        int num = 0;
        try {
            num = estSQL.executeUpdate(cadsql1);
            JOptionPane.showMessageDialog(null, "Ingreso satisfactorio \n Filas ingresadas" + num);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error en sentencia!!");
            e.printStackTrace();
        }catch (Exception e1){
            JOptionPane.showMessageDialog(null, "Error!!....");
            e1.printStackTrace();
        }
    }
   public  void Modificar(String cadsql1, java.sql.Statement estSQL){
        int num = 0;
        try {
            num = estSQL.executeUpdate(cadsql1);
            JOptionPane.showMessageDialog(null, "Nro de Filas afectadas " + num);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error en sentencia!!");
            e.printStackTrace();
        }catch (Exception e1){
            JOptionPane.showMessageDialog(null, "Error!!....");
            e1.printStackTrace();
        }
    }

    public void COMENZAR(String cadsql1, java.sql.Statement estSQL){
        ResultSet resul = null;
        try {
            estSQL.executeQuery(cadsql1);
            
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error en sentencia!!");
            e.printStackTrace();
        }catch (Exception e1){
            JOptionPane.showMessageDialog(null, "Error!!....");
            e1.printStackTrace();
        }
        
    }
   
   
   
   public ResultSet RESULT(String cadsql1, java.sql.Statement estSQL){
        ResultSet resul = null;
        try {
            resul = estSQL.executeQuery(cadsql1);
            
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error en sentencia!!");
            e.printStackTrace();
        }catch (Exception e1){
            JOptionPane.showMessageDialog(null, "Error!!....");
            e1.printStackTrace();
        }
        return resul;
    }
   
   private void limpiarTabla(DefaultTableModel model){
        model.setRowCount(0);
    }
  
   
    public DefaultTableModel procesarRSOE (ResultSet resul, DefaultTableModel modelo)throws SQLException{
        ResultSetMetaData metadatos= resul.getMetaData();
        int numeroColumnas= metadatos .getColumnCount();
         DefaultTableModel model = (DefaultTableModel) modelo;
        this.limpiarTabla(model);
        
        while(resul.next()){
            Object[] fila= new Object[numeroColumnas];
            fila[0] = resul.getString(1);
            fila[1] = resul.getString(2);
            fila[2] = resul.getString(3);
            fila[3] = resul.getString(4);
            fila[4] = resul.getString(5);
            fila[5] = resul.getString(6);
            fila[6] = resul.getString(7);
            fila[7] = resul.getString(8);
            fila[8] = resul.getString(9);
            fila[9] = resul.getString(10);
            fila[10] = resul.getFloat(11);
            model.addRow(fila);
        }
        
        return model;
    }
   
       public DefaultTableModel procesarRS (ResultSet resul, DefaultTableModel modelo)throws SQLException{
        ResultSetMetaData metadatos= resul.getMetaData();
        int numeroColumnas= metadatos .getColumnCount();
           System.err.println("" + metadatos.getColumnTypeName(numeroColumnas));
        Object[] etiquetas= new Object[numeroColumnas];
        
        for(int i=0; i<numeroColumnas;i++){
            etiquetas[i]=metadatos.getColumnLabel(i+1);
        }
        modelo.setColumnIdentifiers(etiquetas);
        while(resul.next()){
            Object[] fila= new Object[numeroColumnas];
            for(int i=0;i<numeroColumnas;i++){
                fila[i]= resul.getObject(i+1);
                System.out.println(fila[i]);
            }
            modelo.addRow(fila);
        }
        
        return modelo;
    }
       
       public  Object [] Buscar(ResultSet resul) throws SQLException{
          
          
           Object[] fila= new Object[5];
           while(resul.next()){
            
            for(int i=0;i<5;i++){
                fila[i]= resul.getObject(i+1);
                System.out.println(fila[i]);
            }
           
        }
        return fila; 
           
       }
        
       public  Object [] Buscar3(ResultSet resul) throws SQLException{
          
          
           Object[] fila= new Object[5];
           while(resul.next()){
            
            for(int i=0;i<5;i++){
                fila[i]= resul.getObject(i+1);
                System.out.println(fila[i]);
            }
           
        }
        return fila; 
           
       }
       
       public  Object [] Buscar4(ResultSet resul) throws SQLException{
          
          
           Object[] fila= new Object[4];
           while(resul.next()){
            
            for(int i=0;i<4;i++){
                fila[i]= resul.getObject(i+1);
                System.out.println(fila[i]);
            }
           
        }
        return fila; 
           
       }
       
        public  Object [] BuscarUno(ResultSet resul) throws SQLException{
          
          
           Object[] fila= new Object[1];
           while(resul.next()){
            
            for(int i=0;i<1;i++){
                fila[i]= resul.getObject(i+1);
                System.out.println(fila[i]);
            }
           
        }
        return fila; 
           
       }
         public  int DarFilas(ResultSet resul) throws SQLException{
          
          int numFila = 0;
           Object[] fila= new Object[1];
           while(resul.next()){
            
            for(int i=0;i<1;i++){
                fila[i]= resul.getObject(i+1);
                numFila = (int) fila[i];
                System.out.println(fila[i]);
            }
           
        }
        return numFila; 
           
       }
         
         public  Object [] BuscarDOS(ResultSet resul) throws SQLException{
          
          
           Object[] fila= new Object[2];
           while(resul.next()){
            
            for(int i=0;i<2;i++){
                fila[i]= resul.getObject(i+1);
                System.out.println(fila[i]);
            }
           
        }
        return fila; 
           
       }
       
       public  Object [] Buscar2(ResultSet resul) throws SQLException{
          
          
           Object[] fila= new Object[4];
           while(resul.next()){
            
            for(int i=0;i<4;i++){
                fila[i]= resul.getObject(i+1);
                System.out.println(fila[i]);
            }
           
        }
        return fila; 
           
       }
       
        public  Object [] BuscarTodos(ResultSet resul , int cant) throws SQLException{
          
          
           Object[] fila= new Object[cant];
           int i = 0;
           while(resul.next()){
            
            //for(int i=0;i<1;i++){
                fila[i]= resul.getObject(1);
                System.out.println(fila[i]);
                i++;
           // }
           
        }
        return fila; 
           
       }
       
        public String [] LlenarCombo(Connection conn,JComboBox daf, ResultSet rs) {
        String[] combo={};
        try {

            DatabaseMetaData dbmd = conn.getMetaData();
            //ResultSet rs = dbmd.getCatalogs();
            ResultSetMetaData rsmd = rs.getMetaData();

            // Display the result set data.
            int cols = rsmd.getColumnCount();
            combo = new String[cols];
            while (rs.next()) {
                for (int i = 1; i <= cols; i++) {
                    //System.out.println(rs.getString(i));
                    combo[i - 1] = rs.getString(i);
                    daf.addItem(rs.getString(i));
                   // System.out.println(combo[i-1]);
                }
            }
            rs.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return combo;
}
       
         public String [] DarArreglo(Connection conn, ResultSet rs) {
        String[] combo={};
        try {

            DatabaseMetaData dbmd = conn.getMetaData();
            //ResultSet rs = dbmd.getCatalogs();
            ResultSetMetaData rsmd = rs.getMetaData();

            // Display the result set data.
            int cols = rsmd.getColumnCount();
            combo = new String[cols];
            while (rs.next()) {
                for (int i = 1; i <= cols; i++) {
                    //System.out.println(rs.getString(i));
                    combo[i - 1] = rs.getString(i);
                  
                   // System.out.println(combo[i-1]);
                }
            }
            rs.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return combo;
}
}
