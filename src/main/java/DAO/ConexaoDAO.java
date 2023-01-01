package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class ConexaoDAO {

    public Connection ConectaBD() {
        Connection connection = null;
        try {
            String url = "jdbc:mysql://localhost:3306/projeto?user=root&password=";
            connection = DriverManager.getConnection(url);
        } catch (SQLException erro) {
            JOptionPane.showMessageDialog(null, "ConexaoDAO" + erro.getMessage());
        }
        return connection;
    }

}
