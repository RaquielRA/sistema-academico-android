package com.example.sistemaacademicoandroid.model;

import java.util.regex.Pattern;

public class Usuario {

    // Enum para diferenciar tipo de usuário
    public enum TipoUsuario {
        ADMIN, PROFESSOR, ALUNO
    }

    private int id;
    private String nome;
    private String login; // será email
    private String senha;
    private TipoUsuario tipoUsuario; // novo atributo

    private static final int TAMANHO_SENHA = 6; // tamanho fixo da senha
    private static final String EMAIL_REGEX = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";

    // Construtor atualizado
    public Usuario(int id, String nome, String login, String senha, TipoUsuario tipoUsuario) {
        this.id = id;
        this.nome = nome;
        setLogin(login);
        setSenha(senha);
        this.tipoUsuario = tipoUsuario;
    }

    // Getters e Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public String getLogin() { return login; }

    public String setLogin(String login) {
        if (login != null && Pattern.matches(EMAIL_REGEX, login)) {
            this.login = login;
            return "Login definido com sucesso!";
        } else {
            return "Erro: Login inválido. Deve estar no formato de email.";
        }
    }

    public String getSenha() { return senha; }

    public String setSenha(String senha) {
        if (senha != null && senha.length() == TAMANHO_SENHA) {
            this.senha = senha;
            return "Senha definida com sucesso!";
        } else {
            return "Erro: Senha deve ter exatamente " + TAMANHO_SENHA + " caracteres.";
        }
    }

    public TipoUsuario getTipoUsuario() { return tipoUsuario; }
    public void setTipoUsuario(TipoUsuario tipoUsuario) { this.tipoUsuario = tipoUsuario; }

    // Métodos
    public String autenticar(String login, String senha) {
        try {
            if (login == null || senha == null) {
                throw new Exception("Login ou senha não podem ser nulos.");
            }
            if (!Pattern.matches(EMAIL_REGEX, login)) {
                throw new Exception("Login inválido. Deve estar no formato de email.");
            }
            if (senha.length() != TAMANHO_SENHA) {
                throw new Exception("Senha inválida. Deve ter exatamente " + TAMANHO_SENHA + " caracteres.");
            }
            if (this.login.equals(login) && this.senha.equals(senha)) {
                return "Autenticação bem-sucedida!";
            } else {
                return "Login ou senha incorretos.";
            }
        } catch (Exception e) {
            String msg = (e.getMessage() != null) ? e.getMessage() : "Ocorreu um erro inesperado.";
            return "Erro na autenticação: " + msg;
        }
    }

    public String alterarSenha(String senhaAntiga, String novaSenha) {
        try {
            if (senhaAntiga == null || novaSenha == null) {
                throw new Exception("Senha antiga ou nova não podem ser nulas.");
            }
            if (novaSenha.length() != TAMANHO_SENHA) {
                throw new Exception("Nova senha deve ter exatamente " + TAMANHO_SENHA + " caracteres.");
            }
            if (!this.senha.equals(senhaAntiga)) {
                return "Senha antiga incorreta. Não foi possível alterar.";
            }
            this.senha = novaSenha;
            return "Senha alterada com sucesso!";
        } catch (Exception e) {
            String msg = (e.getMessage() != null) ? e.getMessage() : "Ocorreu um erro inesperado.";
            return "Erro ao alterar senha: " + msg;
        }
    }
}
