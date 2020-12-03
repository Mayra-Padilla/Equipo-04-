
package gestordeventas.Conexion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import java.sql.Statement;
        
public class conexion {
    static Connection contacto = null;
    
    public static Connection getConexion(){
        
        String url = "jdbc:sqlserver://DESKTOP-JH0QBD0:1433;databaseName=SACI;";
        try{               //com.microsoft.sqlserver.jdbc
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        } catch(ClassNotFoundException e){
            JOptionPane.showMessageDialog(null, "No se pudo establecer la conexion, REVISAR DRIVER " + e.getMessage(), 
                "Error de conexión", JOptionPane.ERROR_MESSAGE);
            }
        try{
            contacto = DriverManager.getConnection(url,"sa","root");
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null, "No se pudo establecer la conexion, REVISAR DRIVER " + e.getMessage(), 
                "Error de conexión", JOptionPane.ERROR_MESSAGE);
        }
        return contacto;
    }
    
   //MANDAR A PEDIR UNA CONSULTA DIRECTAMENTE A BD
    public static ResultSet Consulta(String consulta){
        Connection con = getConexion();
        Statement declara;
        try{
            declara = con.createStatement();
            ResultSet respuesta = declara.executeQuery(consulta);
            return respuesta;
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null, "No se pudo establecer la conexion, REVISAR DRIVER " + e.getMessage(), 
                "Error de conexión", JOptionPane.ERROR_MESSAGE);
            return null;
        }
    }
}
