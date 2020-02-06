package br.fabrica.projeto.carteiravacinadigital;

public class Administrador
{
    private String nome;
    private String cpf;
    private String email;
    private String senha;



    public Administrador(String _nome, String _cpf, String _email, String _senha)
    {
        this.nome = _nome;
        this.cpf = _cpf;
        this.email = _email;
        this.senha = _senha;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    @Override
    public String toString(){
        return nome;
    }

}
