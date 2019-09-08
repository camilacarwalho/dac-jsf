package br.edu.ifpb.infra.dao.impls;

import br.edu.ifpb.domain.Dependente;
import br.edu.ifpb.infra.dao.interfaces.DependenteDao;
import br.edu.ifpb.infra.util.GeradorDeUUID;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Stateless
public class DependentesEmJDBC implements DependenteDao {

    @Resource(name = "java:app/jdbc/jsf_dac")
    private DataSource dataSource;


    private Connection connection;

    @PostConstruct
    private void init() {
        try {
            connection = dataSource.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void salvar(Dependente d) {
        try {
            String novoUUID = GeradorDeUUID.gerarUUID();
            PreparedStatement ps = connection.prepareStatement("INSERT INTO dependente (uuid, nome, nascimento) VALUES (?, ?, ?)");
            ps.setString(1, novoUUID);
            ps.setString(2, d.getNome());
            ps.setDate(3, Date.valueOf(d.getNascimento()));
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void remover(Dependente d) {
        String sql = "DELETE FROM dependente WHERE uuid = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, d.getUuid());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void atualizar(Dependente d) {
        try {
            PreparedStatement ps = connection.prepareStatement("UPDATE dependente SET nome = ?, nascimento = ? WHERE uuid = ?");
            ps.setString(1, d.getNome());
            ps.setDate(2, Date.valueOf(d.getNascimento()));
            ps.setString(3, d.getUuid());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Dependente> listar(){
        try {
            List<Dependente> dependentes = new ArrayList<>();
            PreparedStatement ps = connection.prepareStatement("SELECT * FROM dependente");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                dependentes.add(create(rs));
            }
            return dependentes;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Dependente buscar(String uuid) {
        String sql = "SELECT * FROM dependente WHERE uuid = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, uuid);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return create(rs);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    private Dependente create(ResultSet resultSet) throws SQLException {
        Dependente dependente = new Dependente();
        dependente.setNome(resultSet.getString("nome"));
        dependente.setUuid(resultSet.getString("uuid"));
        dependente.setNascimento(resultSet.getDate("nascimento").toLocalDate());
        return dependente;
    }


}
