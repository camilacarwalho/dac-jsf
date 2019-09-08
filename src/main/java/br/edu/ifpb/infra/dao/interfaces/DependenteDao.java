package br.edu.ifpb.infra.dao.interfaces;

import br.edu.ifpb.domain.Dependente;

import java.util.List;

public interface DependenteDao {

    void salvar(Dependente dependente);
    void remover(Dependente dependente);
    void atualizar(Dependente dependente);
    Dependente buscar(String uuid);
    List<Dependente> listar();

}
