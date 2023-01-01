package DAO;

import DTO.UsuarioDTO;
import java.sql.*;
import java.util.ArrayList;
import javax.swing.JOptionPane;

public class CadUsuarioDAO {

    ArrayList<UsuarioDTO> lista = new ArrayList<>();
    Connection connection;
    PreparedStatement pstm;
    ResultSet rs;

    public void cadastrarUsuario(UsuarioDTO objusuariodto) {
        String sql = "insert into usuario (nome_usuario, modalidade, senha,nome) values (?,?,?,?) ";
        connection = new ConexaoDAO().ConectaBD();

        try {
            pstm = connection.prepareStatement(sql);
            pstm.setString(1, objusuariodto.getNome_usuario());
            pstm.setString(2, objusuariodto.getModalidade());
            pstm.setString(3, objusuariodto.getSenha());
            pstm.setString(4, objusuariodto.getNome());

            pstm.execute();
            pstm.close();

        } catch (Exception erro) {
            JOptionPane.showMessageDialog(null, "CadUsuarioDAO: " + erro);
        }
    }

    public ArrayList<UsuarioDTO> PesquisarUsuario() {
        String sql = "Select * from usuario";

        try {

            pstm = connection.prepareStatement(sql);
            rs = pstm.executeQuery();

            while (rs.next()) {
                UsuarioDTO objusuariodto = new UsuarioDTO();
                objusuariodto.setId_usuario(rs.getInt("Id"));
                objusuariodto.setNome_usuario(rs.getString("nome_usuario"));
                objusuariodto.setModalidade(rs.getString("modalidade"));
                objusuariodto.setSenha(rs.getString("senha"));
                objusuariodto.setNome(rs.getString("nome"));

                lista.add(objusuariodto);
            }

        } catch (SQLException erro) {
            JOptionPane.showMessageDialog(null, "UsuarioDAO (Pesquisa): " + erro);
        }
        return lista;
    }

}
