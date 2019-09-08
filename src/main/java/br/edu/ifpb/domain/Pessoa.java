package br.edu.ifpb.domain;

import java.util.Objects;

public class Pessoa {

    private int id;
    private String nome;
    private CPF cpf;
    private Dependente dependente;

    public Pessoa() {
    }


    public Pessoa(String nome, String cpf, Dependente dependente){
        this(nome, new CPF(cpf), dependente);
    }

    public Pessoa(int id, String nome, String cpf, Dependente dependente) {
        this(nome, cpf, dependente);
        this.id = id;
    }



    public Pessoa(String nome, CPF cpf, Dependente dependente){
        this.nome = nome;
        this.cpf = cpf;
        this.dependente = dependente;
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

    public Dependente getDependente() {
        return dependente;
    }

    public void setDependente(Dependente dependente) {
        this.dependente = dependente;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pessoa pessoa = (Pessoa) o;
        return Objects.equals(nome, pessoa.nome) &&
                Objects.equals(cpf, pessoa.cpf) &&
                Objects.equals(dependente, pessoa.dependente);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nome, cpf, dependente);
    }

}

