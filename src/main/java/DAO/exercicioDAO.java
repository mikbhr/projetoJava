package DAO;

import java.sql.*;
import DTO.ExercicioDTO;
import java.util.ArrayList;
import javax.swing.JOptionPane;

public class exercicioDAO {

    ResultSet rs;
    Connection connection;
    PreparedStatement pstm;
    ArrayList<ExercicioDTO> lista = new ArrayList<>();

    public void cadastrarExercicio(ExercicioDTO objexerciciodto) {
        String sql = "insert into exercicio (nome, repeticoes, foco, series, tecnica) values (?,?,?,?,?) ";
        connection = new ConexaoDAO().ConectaBD();

        try {
            pstm = connection.prepareStatement(sql);
            pstm.setString(1, objexerciciodto.getNome());
            pstm.setInt(2, objexerciciodto.getRepeticoes());
            pstm.setString(3, objexerciciodto.getFoco());
            pstm.setInt(4, objexerciciodto.getSeries());
            pstm.setString(5, objexerciciodto.getTecnica());

            pstm.execute();
            pstm.close();

        } catch (Exception erro) {
            JOptionPane.showMessageDialog(null, "exercicioDAO: " + erro);
        }
    }

    public ArrayList<ExercicioDTO> PesquisarExercicio() {
        connection = new ConexaoDAO().ConectaBD();
        String sql = "select * from exercicio";
        try {

            pstm = connection.prepareStatement(sql);
            rs = pstm.executeQuery();

            while (rs.next()) {
                ExercicioDTO objexerciciodto = new ExercicioDTO();
                objexerciciodto.setRepeticoes(rs.getInt("repeticoes"));
                objexerciciodto.setFoco(rs.getString("foco"));
                objexerciciodto.setSeries(rs.getInt("series"));
                objexerciciodto.setNome(rs.getString("nome"));
                objexerciciodto.setTecnica(rs.getString("tecnica"));

                lista.add(objexerciciodto);
            }

        } catch (SQLException erro) {
            JOptionPane.showMessageDialog(null, "ExercicioDAO (Pesquisa): " + erro);
        }
        return lista;
    }

    public void ExcluirExercicio(ExercicioDTO objexerciciodto) {

        String sql = "delete from exercicio where nome = ?";
        connection = new ConexaoDAO().ConectaBD();

        try {
            pstm = connection.prepareStatement(sql);
            pstm.setString(1, objexerciciodto.getNome());

            pstm.execute();
            pstm.close();

        } catch (Exception erro) {
            JOptionPane.showMessageDialog(null, "ExercicioDAO excluir: " + erro);
        }
    }
}
