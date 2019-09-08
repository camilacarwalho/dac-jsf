package br.edu.ifpb.domain;

import java.time.LocalDate;
import java.util.Objects;

public class Dependente {

    private String uuid;
    private String nome;
    private LocalDate nascimento;

    public Dependente() {

    }

    public Dependente(String uuid, String nome, LocalDate nascimento) {
        this(nome, nascimento);
        this.uuid = uuid;
    }

    public Dependente(String nome, LocalDate nascimento) {
        this.nome = nome;
        this.nascimento = nascimento;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public LocalDate getNascimento() {
        return nascimento;
    }

    public void setNascimento(LocalDate nascimento) {
        this.nascimento = nascimento;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Dependente)) return false;
        Dependente that = (Dependente) o;
        return Objects.equals(getUuid(), that.getUuid()) &&
                Objects.equals(getNome(), that.getNome()) &&
                Objects.equals(getNascimento(), that.getNascimento());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getUuid(), getNome(), getNascimento());
    }

    @Override
    public String toString() {
        return "Dependente{" +
                "uuid='" + uuid + '\'' +
                ", nome='" + nome + '\'' +
                ", dataDeNascimento=" + nascimento +
                '}';
    }
}


