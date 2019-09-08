package br.edu.ifpb.web.jsf;

import br.edu.ifpb.domain.CPF;
import br.edu.ifpb.domain.Pessoa;
import br.edu.ifpb.infra.dao.interfaces.PessoaDao;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;

@Named
@RequestScoped
public class ControladorDeBusca implements Serializable {

    private String cpf;
    private Pessoa pessoaBuscada;

    @Inject
    private PessoaDao pessoaDao;

    @PostConstruct
    public void init(){
        pessoaBuscada = new Pessoa();
    }

    public String buscar(){
        pessoaBuscada = pessoaDao.buscar(new CPF(cpf));
        return null;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public Pessoa getPessoaBuscada() {
        return pessoaBuscada;
    }

    public void setPessoaBuscada(Pessoa pessoaBuscada) {
        this.pessoaBuscada = pessoaBuscada;
    }
}
