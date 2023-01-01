package DAO;

import DTO.UsuarioDTO;
import javax.swing.JOptionPane;
import java.sql.*;

public class UsuarioDAO {

    Connection connection;

    public ResultSet autenticacaoUsuario(UsuarioDTO objusuariodto) {
        connection = new ConexaoDAO().ConectaBD();

        try {
            String sql = "Select * from usuario where nome_usuario = ? and senha = ?";
            PreparedStatement pstm = (PreparedStatement) connection.prepareStatement(sql);
            pstm.setString(1, objusuariodto.getNome_usuario());
            pstm.setString(2, objusuariodto.getSenha());

            ResultSet rs = pstm.executeQuery();
            return rs;
        } catch (SQLException erro) {
            JOptionPane.showMessageDialog(null, "UsuarioDAO" + erro);
            return null;
        }
    }

}
