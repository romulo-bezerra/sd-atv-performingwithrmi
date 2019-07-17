package br.edu.ifpb.clientrmi.dao;

import br.edu.ifpb.clientrmi.factory.ConFactoryPostgreSQL;
import br.edu.ifpb.clientrmi.domain.Usuario;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Logger;

public class UsuarioDao {

    private final Logger log = Logger.getLogger(UsuarioDao.class.getName());

    public void insert(Usuario usuario){
        try {
            Connection con = ConFactoryPostgreSQL.getConnectionPostgres();
            String sql = "INSERT INTO Usuario (id, nome, atualizando, deletando) VALUES (?,?,?,?)";
            PreparedStatement statement = null;
            statement = con.prepareStatement(sql);
            statement.setString(1, usuario.getId());
            statement.setString(2,usuario.getNome());
            statement.setBoolean(3,false);
            statement.setBoolean(4,false);
            statement.executeUpdate();
            statement.close();
            con.close();
            log.info("Usuário cadastrado");
        } catch (SQLException e) {
            log.warning("Falha ao inserir um novo usuário\n");
        }
    }

    public void update(String userId){
        try {
            Connection con = ConFactoryPostgreSQL.getConnectionPostgres();
            String sql = "UPDATE Usuario SET atualizando=? WHERE id=?";
            PreparedStatement statement = con.prepareStatement(sql);
            statement.setBoolean(1, true);
            statement.setString(2, userId);
            statement.executeUpdate();
            statement.close();
            con.close();
            log.info("Usuário atualizado");
        } catch (SQLException e) {
            log.warning("Falha ao atualizar usuário\n");
        }
    }

    public void delete(String id){
        try {
            Connection con = ConFactoryPostgreSQL.getConnectionPostgres();
            String sql = "UPDATE Usuario SET deletando=? WHERE id=?";
            PreparedStatement statement = null;
            statement = con.prepareStatement(sql);
            statement.setBoolean(1,true);
            statement.setString(2, id);
            statement.executeUpdate();
            statement.close();
            con.close();
            log.info("Usuário deletado");
        } catch (SQLException e) {
            log.warning("Falha ao deletar usuário\n");
        }
    }

    public boolean userIdUsed(String userId) {
        try {
            Connection con = ConFactoryPostgreSQL.getConnectionPostgres();
            String sql = "SELECT * FROM Usuario WHERE id = ?";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, userId);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                con.close();
                return true;
            } else {
                con.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

}
