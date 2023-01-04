package DAO;

import DTO.TreinoDTO;
import java.sql.*;
import java.util.ArrayList;
import javax.swing.JOptionPane;

public class TreinoDAO {

    Connection connection;
    PreparedStatement pstm;
    ArrayList<TreinoDTO> lista = new ArrayList<>();
    ResultSet rs;

    public void cadastrarTreino(TreinoDTO objtreinodto) {
        String sql = "insert into treino (nome, duracao, n_series,frequencia, publico_alvo) values (?,?,?,?,?) ";
        connection = new ConexaoDAO().ConectaBD();

        try {
            pstm = connection.prepareStatement(sql);
            pstm.setString(1, objtreinodto.getNome());
            pstm.setInt(2, objtreinodto.getDuracao());
            pstm.setInt(3, objtreinodto.getSeries());
            pstm.setInt(4, objtreinodto.getFrequencia());
            pstm.setString(5, objtreinodto.getPublico());

            pstm.execute();
            pstm.close();

        } catch (Exception erro) {
            JOptionPane.showMessageDialog(null, "TreinoDAO: " + erro);
        }
    }

    public ArrayList<TreinoDTO> PesquisarTreino() {
        connection = new ConexaoDAO().ConectaBD();
        String sql = "select * from treino";
        try {

            pstm = connection.prepareStatement(sql);
            rs = pstm.executeQuery();

            while (rs.next()) {
                TreinoDTO objtreinodto = new TreinoDTO();
                objtreinodto.setSeries(rs.getInt("n_series"));
                objtreinodto.setPublico(rs.getString("publico_alvo"));
                objtreinodto.setId(rs.getInt("ID"));
                objtreinodto.setNome(rs.getString("nome"));
                objtreinodto.setDuracao(rs.getInt("duracao"));
                objtreinodto.setFrequencia(rs.getInt("frequencia"));

                lista.add(objtreinodto);
            }

        } catch (SQLException erro) {
            JOptionPane.showMessageDialog(null, "TreinoDAO (Pesquisa): " + erro);
        }
        return lista;
    }

    public void ExcluirTreino(TreinoDTO objtreinodto) {

        String sql = "delete from treino where id = ?";
        connection = new ConexaoDAO().ConectaBD();

        try {
            pstm = connection.prepareStatement(sql);
            pstm.setInt(1, objtreinodto.getId());

            pstm.execute();
            pstm.close();

        } catch (Exception erro) {
            JOptionPane.showMessageDialog(null, "TreinoDAO excluir: " + erro);
        }
    }
    
    public void AlterarTreino (TreinoDTO objtreinodto){
        String sql = "Update treino set nome = ?, publico_alvo = ?, duracao = ?, n_series = ?, frequencia = ? where id = ?;";
        connection = new ConexaoDAO().ConectaBD();

        try {
            pstm = connection.prepareStatement(sql);
            pstm.setString(1, objtreinodto.getNome());
            pstm.setInt(3, objtreinodto.getDuracao());
            pstm.setInt(4, objtreinodto.getSeries());
            pstm.setInt(5, objtreinodto.getFrequencia());
            pstm.setString(2, objtreinodto.getPublico());
            pstm.setInt(6, objtreinodto.getId());

            pstm.execute();
            pstm.close();

        } catch (Exception erro) {
            JOptionPane.showMessageDialog(null, "TreinoDAO: " + erro);
        }
    }
}
