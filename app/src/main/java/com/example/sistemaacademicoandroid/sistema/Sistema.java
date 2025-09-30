package com.example.sistemaacademicoandroid.sistema;

import com.example.sistemaacademicoandroid.model.Administrador;
import com.example.sistemaacademicoandroid.model.Aluno;
import com.example.sistemaacademicoandroid.model.Disciplina;
import com.example.sistemaacademicoandroid.model.Professor;
import com.example.sistemaacademicoandroid.model.Turma;
import com.example.sistemaacademicoandroid.model.Usuario;

import java.util.ArrayList;
import java.util.List;

public class Sistema {

    private static Sistema instancia;

    private List<Aluno> alunos;
    private List<Professor> professores;
    private List<Administrador> administradores;
    private List<Disciplina> disciplinas;
    private List<Turma> turmas;

    // Construtor privado para singleton
    private Sistema() {
        alunos = new ArrayList<>();
        professores = new ArrayList<>();
        administradores = new ArrayList<>();
        disciplinas = new ArrayList<>();
        turmas = new ArrayList<>();
        inicializarDadosExemplo(); // opcional: criar alguns dados de teste
    }

    // Singleton
    public static Sistema getInstancia() {
        if (instancia == null) {
            instancia = new Sistema();
        }
        return instancia;
    }

    private void inicializarDadosExemplo() {
        // Exemplo: criar um administrador
        Administrador admin = new Administrador(1, "Admin Teste", "admin@teste.com", "123456");
        administradores.add(admin);

        // Exemplo: criar um professor
        Professor prof = new Professor(1, "Professor Teste", "prof@teste.com", "123456");
        professores.add(prof);

        // Exemplo: criar um aluno
        Aluno aluno = new Aluno(1, "Aluno Teste", "aluno@teste.com", "123456");
        alunos.add(aluno);
    }

    // Autenticar usuário
    public Usuario autenticarUsuario(String email, String senha) {
        for (Aluno a : alunos) {
            if (a.getLogin().equals(email) && a.getSenha().equals(senha)) return a;
        }
        for (Professor p : professores) {
            if (p.getLogin().equals(email) && p.getSenha().equals(senha)) return p;
        }
        for (Administrador ad : administradores) {
            if (ad.getLogin().equals(email) && ad.getSenha().equals(senha)) return ad;
        }
        return null;
    }

    // Getters
    public List<Aluno> getAlunos() { return alunos; }
    public List<Professor> getProfessores() { return professores; }
    public List<Administrador> getAdministradores() { return administradores; }
    public List<Disciplina> getDisciplinas() { return disciplinas; }
    public List<Turma> getTurmas() { return turmas; }

    // Métodos para adicionar novos dados
    public void adicionarAluno(Aluno aluno) { if(aluno != null) alunos.add(aluno); }
    public void adicionarProfessor(Professor professor) { if(professor != null) professores.add(professor); }
    public void adicionarAdministrador(Administrador administrador) { if(administrador != null) administradores.add(administrador); }
    public void adicionarDisciplina(Disciplina disciplina) { if(disciplina != null) disciplinas.add(disciplina); }
    public void adicionarTurma(Turma turma) { if(turma != null) turmas.add(turma); }

    // ===========================
    // Métodos de busca por ID
    // ===========================

    public Aluno buscarAlunoPorId(int id) {
        for (Aluno a : alunos) {
            if (a.getId() == id) return a;
        }
        return null;
    }

    public Professor buscarProfessorPorId(int id) {
        for (Professor p : professores) {
            if (p.getId() == id) return p;
        }
        return null;
    }

    public Administrador buscarAdministradorPorId(int id) {
        for (Administrador ad : administradores) {
            if (ad.getId() == id) return ad;
        }
        return null;
    }

    public Disciplina buscarDisciplinaPorId(int id) {
        for (Disciplina d : disciplinas) {
            if (d.getId() == id) return d;
        }
        return null;
    }

    public Turma buscarTurmaPorId(int id) {
        for (Turma t : turmas) {
            if (t.getId() == id) return t;
        }
        return null;
    }
}
