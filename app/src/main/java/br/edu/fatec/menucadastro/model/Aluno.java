package br.edu.fatec.menucadastro.model;

public class Aluno {
    //atributos
    private Integer id;
    private String nome;
    private String cpf;
    private String telefone;

    public Aluno() {

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    @Override
    public String toString() {
        return "NOME: " +nome +"\nCPF: " +cpf+"\nTELEFONE: "+ telefone;
    }
}
