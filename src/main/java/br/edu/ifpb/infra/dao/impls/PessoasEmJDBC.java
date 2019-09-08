package br.edu.ifpb.infra.dao.impls;

import br.edu.ifpb.domain.CPF;
import br.edu.ifpb.domain.Dependente;
import br.edu.ifpb.domain.Pessoa;
import br.edu.ifpb.infra.dao.interfaces.PessoaDao;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Stateless
public class PessoasEmJDBC implements PessoaDao {

    @Resource(name = "java:app/jdbc/jsf_dac")
    private DataSource dataSource;
    private Connection connection;

    @PostConstruct
    private void init(){
        try{
            connection = dataSource.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void salvar(Pessoa p) {
        try {
            PreparedStatement ps = connection.prepareStatement("INSERT INTO pessoa (cpf, nome, depend) VALUES (?, ?, ?)");
            ps.setString(1, p.getCpf().valor());
            ps.setString(2, p.getNome());
            if(p.getDependente() != null) {
                ps.setString(3, p.getDependente().getUuid());
            }else{
                ps.setString(3, null);
            }
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Pessoa> listar(){
        try {
            List<Pessoa> pessoas = new ArrayList<>();
            PreparedStatement ps = connection.prepareStatement("SELECT * FROM pessoa");
            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                pessoas.add(create(rs));
            }
            return pessoas;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void remover(Pessoa p) {
        try {
            PreparedStatement ps = connection.prepareStatement("DELETE FROM pessoa WHERE cpf = ?");
            ps.setString(1, p.getCpf().valor());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void atualizar(Pessoa p) {
        try {
            PreparedStatement ps = connection.prepareStatement("UPDATE pessoa SET nome = ?, depend = ? WHERE cpf = ?");
            ps.setString(1, p.getNome());
            ps.setString(3, p.getCpf().valor());
            if(p.getDependente() != null){
                ps.setString(2, p.getDependente().getUuid());
            }else {
                ps.setString(2, null);
            }
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Pessoa buscar(CPF cpf) {
        String sql = "SELECT * FROM pessoa WHERE cpf = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, cpf.valor());
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                return create(rs);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }


    private Pessoa create(ResultSet resultSet) throws SQLException {
        Pessoa pessoa = new Pessoa();
        pessoa.setNome(resultSet.getString("nome"));
        pessoa.setDependente(buscarDep(resultSet.getString("depend")));
        pessoa.setCpf(new CPF(resultSet.getString("cpf")));
        return pessoa;
    }

    private Dependente buscarDep(String uuid) throws SQLException {
        PreparedStatement ps = connection.prepareStatement("SELECT * FROM dependente WHERE uuid = ?");
        ps.setString(1, uuid);
        ResultSet resultSet = ps.executeQuery();
        if(resultSet.next()){
            return new Dependente(
                resultSet.getString("uuid"),
                resultSet.getString("nome"),
                resultSet.getDate("nascimento").toLocalDate()
            );
        }
        return null;
    }
}
