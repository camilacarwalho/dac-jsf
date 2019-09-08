package br.edu.ifpb.web.jsf;

import br.edu.ifpb.domain.Pessoa;
import br.edu.ifpb.infra.dao.interfaces.PessoaDao;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;


@ViewScoped
@Named
public class ControladorDePessoas implements Serializable {

    private List<Pessoa> todasAsPessoas;
    private Pessoa pessoa;

    @Inject
    private PessoaDao service;

    @PostConstruct
    public void init(){
        pessoa = new Pessoa();
        todasAsPessoas = service.listar();
    }

    public String salvar() {
        this.service.salvar(pessoa);
        this.pessoa = new Pessoa();
        todasAsPessoas = service.listar();
        return null;
    }

    public String atualizar() {
        this.service.atualizar(pessoa);
        this.pessoa = new Pessoa();
        todasAsPessoas = service.listar();
        return null;
    }

    public String excluir(Pessoa pessoa) {
        this.service.remover(pessoa);
        todasAsPessoas = service.listar();
        return null;
    }

    public String editar(Pessoa pessoa) {
        this.pessoa = pessoa;
        todasAsPessoas = service.listar();
        return null;
    }

    public List<Pessoa> getTodasAsPessoas() {
        return todasAsPessoas;
    }

    public void setTodasAsPessoas(List<Pessoa> todasAsPessoas) {
        this.todasAsPessoas = todasAsPessoas;
    }

    public Pessoa getPessoa() {
        return pessoa;
    }

    public void setPessoa(Pessoa pessoa) {
        this.pessoa = pessoa;
    }


}
