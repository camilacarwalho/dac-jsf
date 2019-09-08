package br.edu.ifpb.infra.dao.interfaces;

import br.edu.ifpb.domain.CPF;
import br.edu.ifpb.domain.Pessoa;

import java.io.Serializable;
import java.util.List;

public interface PessoaDao extends Serializable {
 
    void salvar(Pessoa pessoa);
    void remover(Pessoa pessoa);
    void atualizar(Pessoa pessoa);
    List<Pessoa> listar() ;
    Pessoa buscar(CPF cpf);

}
