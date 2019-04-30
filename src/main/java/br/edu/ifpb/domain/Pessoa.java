package br.edu.ifpb.domain;

import java.util.Objects;

/**
 * @author Ricardo Job
 * @mail ricardo.job@ifpb.edu.br
 * @since 25/04/2019, 09:55:19
 */
public class Pessoa {

    private int id;
    private String nome;
    private CPF cpf;
    private Dependente dependente;

    public Pessoa() {

    }

    public Pessoa(String nome,String cpf,Dependente dependente) {
        this(nome,new CPF(cpf),dependente);
    }

    public Pessoa(String nome, CPF cpf, Dependente dependente) {
        this.cpf = cpf;
        this.nome = nome;
    }

    public CPF getCpf() {
        return cpf;
    }

    public String getNome() {
        return nome;
    }

    public void setCpf(CPF cpf) {
        this.cpf = cpf;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    @Override
    public String toString() {
        return "Pessoa{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", cpf=" + cpf +
                ", dependente=" + dependente +
                '}';
    }
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 17 * hash + Objects.hashCode(this.cpf);
        hash = 17 * hash + Objects.hashCode(this.nome);
        hash = 17 * hash + this.id;
        hash = 17 * hash + Objects.hashCode(this.dependente);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Pessoa other = (Pessoa) obj;
        if (this.id != other.id) {
            return false;
        }
        if (!Objects.equals(this.nome,other.nome)) {
            return false;
        }
        if (!Objects.equals(this.cpf,other.cpf)) {
            return false;
        }
        if (!Objects.equals(this.dependente,other.dependente)) {
            return false;
        }
        return true;
    }

}
