package br.edu.ifpb.web.jsf;

import br.edu.ifpb.domain.Dependente;
import br.edu.ifpb.infra.dao.interfaces.DependenteDao;

import javax.inject.Named;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import java.io.Serializable;
import javax.inject.Inject;

@ViewScoped
@Named
public class ControladorDeDependentes  implements Serializable {

    private List<Dependente> todosOsDependentes;
    private Dependente dep;

    @Inject
    private DependenteDao dao;

    @PostConstruct
    public void init(){
        dep = new Dependente();
        todosOsDependentes = dao.listar();
    }

    public String salvar(){
        dao.salvar(dep);
        dep = new Dependente();
        todosOsDependentes = dao.listar();
        return null;
    }

    public String atualizar(){
        dao.atualizar(dep);
        todosOsDependentes = dao.listar();
        return null;
    }

    public String editar(Dependente dependente){
        this.dep = dependente;
        return null;

    }

    public String excluir(Dependente dependente){
        dao.remover(dependente);
        todosOsDependentes = dao.listar();
        return "";
    }

    public List<Dependente> getTodosOsDependentes() {
        return todosOsDependentes;
    }

    public void setTodosOsDependentes(List<Dependente> todosOsDependentes) {
        this.todosOsDependentes = todosOsDependentes;
    }

    public Dependente getDep() {
        return dep;
    }

    public void setDep(Dependente dep) {
        this.dep = dep;
    }
}
