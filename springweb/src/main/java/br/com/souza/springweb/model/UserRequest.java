package br.com.souza.springweb.model;

public class UserRequest {

    private String nome;
    private Integer idade;

    public UserRequest() {
    }

    public UserRequest(String nome, Integer idade) {
        this.nome = nome;
        this.idade = idade;
    }

    public Integer getIdade() {
        return idade;
    }

    public void setIdade(Integer idade) {
        this.idade = idade;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}
