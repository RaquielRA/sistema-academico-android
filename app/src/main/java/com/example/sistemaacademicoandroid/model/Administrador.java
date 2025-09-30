package com.example.sistemaacademicoandroid.model;

import com.example.sistemaacademicoandroid.sistema.Sistema;

public class Administrador extends Usuario {

    public Administrador(int id, String nome, String login, String senha) {
        super(id, nome, login, senha, TipoUsuario.ADMIN);
    }

    // Cadastrar aluno
    public String cadastrarAluno(Aluno aluno) {
        try {
            if (aluno == null) throw new Exception("Aluno inválido.");
            if (Sistema.getInstancia().buscarAlunoPorId(aluno.getId()) != null)
                return "Aluno já cadastrado.";
            Sistema.getInstancia().adicionarAluno(aluno);
            return "Aluno " + aluno.getNome() + " cadastrado com sucesso!";
        } catch (Exception e) {
            return "Erro ao cadastrar aluno: " + e.getMessage();
        }
    }

    // Cadastrar professor
    public String cadastrarProfessor(Professor professor) {
        try {
            if (professor == null) throw new Exception("Professor inválido.");
            if (Sistema.getInstancia().buscarProfessorPorId(professor.getId()) != null)
                return "Professor já cadastrado.";
            Sistema.getInstancia().adicionarProfessor(professor);
            return "Professor " + professor.getNome() + " cadastrado com sucesso!";
        } catch (Exception e) {
            return "Erro ao cadastrar professor: " + e.getMessage();
        }
    }

    // Criar disciplina
    public String criarDisciplina(Disciplina disciplina) {
        try {
            if (disciplina == null) throw new Exception("Disciplina inválida.");
            if (Sistema.getInstancia().buscarDisciplinaPorId(disciplina.getId()) != null)
                return "Disciplina já cadastrada.";
            Sistema.getInstancia().adicionarDisciplina(disciplina);
            return "Disciplina '" + disciplina.getNome() + "' criada com sucesso!";
        } catch (Exception e) {
            return "Erro ao criar disciplina: " + e.getMessage();
        }
    }

    // Criar turma
    public String criarTurma(Turma turma) {
        try {
            if (turma == null) throw new Exception("Turma inválida.");
            if (Sistema.getInstancia().buscarTurmaPorId(turma.getId()) != null)
                return "Turma já existente.";
            if (Sistema.getInstancia().buscarDisciplinaPorId(turma.getDisciplinaId()) == null)
                return "Disciplina não cadastrada.";
            Sistema.getInstancia().adicionarTurma(turma);
            return "Turma '" + turma.getNome() + "' criada com sucesso!";
        } catch (Exception e) {
            return "Erro ao criar turma: " + e.getMessage();
        }
    }

    // Adicionar aluno a turma
    public String adicionarAlunoTurma(Aluno aluno, int turmaId) {
        try {
            if (aluno == null) throw new Exception("Aluno inválido.");
            Turma t = Sistema.getInstancia().buscarTurmaPorId(turmaId);
            if (t == null) return "Turma não encontrada.";
            return t.adicionarAluno(aluno);
        } catch (Exception e) {
            return "Erro ao adicionar aluno à turma: " + e.getMessage();
        }
    }

    // Atribuir professor a turma
    public String atribuirProfessorTurma(Professor professor, int turmaId) {
        try {
            if (professor == null) throw new Exception("Professor inválido.");
            Turma t = Sistema.getInstancia().buscarTurmaPorId(turmaId);
            if (t == null) return "Turma não encontrada.";
            return t.definirProfessor(professor);
        } catch (Exception e) {
            return "Erro ao atribuir professor à turma: " + e.getMessage();
        }
    }
}
