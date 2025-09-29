package com.example.sistemaacademicoandroid.model;

import java.util.ArrayList;
import java.util.List;

public class Administrador extends Usuario {

    private List<Aluno> alunos;
    private List<Professor> professores;
    private List<Disciplina> disciplinas; // agora é lista de objetos
    private List<Turma> turmas;           // lista de objetos Turma

    public Administrador(int id, String nome, String login, String senha) {
        super(id, nome, login, senha, TipoUsuario.ADMIN);
        this.alunos = new ArrayList<>();
        this.professores = new ArrayList<>();
        this.disciplinas = new ArrayList<>();
        this.turmas = new ArrayList<>();
    }

    // Cadastrar aluno
    public String cadastrarAluno(Aluno aluno) {
        try {
            if (aluno == null) throw new Exception("Aluno inválido.");
            for (Aluno a : alunos) {
                if (a.getId() == aluno.getId()) return "Aluno já cadastrado.";
            }
            alunos.add(aluno);
            return "Aluno " + aluno.getNome() + " cadastrado com sucesso!";
        } catch (Exception e) {
            return "Erro ao cadastrar aluno: " + e.getMessage();
        }
    }

    // Cadastrar professor
    public String cadastrarProfessor(Professor professor) {
        try {
            if (professor == null) throw new Exception("Professor inválido.");
            for (Professor p : professores) {
                if (p.getId() == professor.getId()) return "Professor já cadastrado.";
            }
            professores.add(professor);
            return "Professor " + professor.getNome() + " cadastrado com sucesso!";
        } catch (Exception e) {
            return "Erro ao cadastrar professor: " + e.getMessage();
        }
    }

    // Criar disciplina
    public String criarDisciplina(Disciplina disciplina) {
        try {
            if (disciplina == null) throw new Exception("Disciplina inválida.");
            for (Disciplina d : disciplinas) {
                if (d.getId() == disciplina.getId()) return "Disciplina já cadastrada.";
            }
            disciplinas.add(disciplina);
            return "Disciplina '" + disciplina.getNome() + "' criada com sucesso!";
        } catch (Exception e) {
            return "Erro ao criar disciplina: " + e.getMessage();
        }
    }

    // Criar turma
    public String criarTurma(Turma turma) {
        try {
            if (turma == null) throw new Exception("Turma inválida.");
            // verifica duplicidade por ID
            for (Turma t : turmas) {
                if (t.getId() == turma.getId()) return "Turma já existente.";
            }
            // verifica se a disciplina existe
            boolean disciplinaValida = false;
            for (Disciplina d : disciplinas) {
                if (d.getId() == turma.getDisciplinaId()) {
                    disciplinaValida = true;
                    break;
                }
            }
            if (!disciplinaValida) return "Disciplina não cadastrada.";
            turmas.add(turma);
            return "Turma '" + turma.getNome() + "' criada com sucesso!";
        } catch (Exception e) {
            return "Erro ao criar turma: " + e.getMessage();
        }
    }

    // Adicionar aluno a turma
    public String adicionarAlunoTurma(Aluno aluno, int turmaId) {
        try {
            if (aluno == null) throw new Exception("Aluno inválido.");
            for (Turma t : turmas) {
                if (t.getId() == turmaId) {
                    return t.adicionarAluno(aluno);
                }
            }
            return "Turma não encontrada.";
        } catch (Exception e) {
            return "Erro ao adicionar aluno à turma: " + e.getMessage();
        }
    }

    // Atribuir professor a turma
    public String atribuirProfessorTurma(Professor professor, int turmaId) {
        try {
            if (professor == null) throw new Exception("Professor inválido.");
            for (Turma t : turmas) {
                if (t.getId() == turmaId) {
                    return t.definirProfessor(professor);
                }
            }
            return "Turma não encontrada.";
        } catch (Exception e) {
            return "Erro ao atribuir professor à turma: " + e.getMessage();
        }
    }

    // Getters
    public List<Aluno> getAlunos() { return alunos; }
    public List<Professor> getProfessores() { return professores; }
    public List<Disciplina> getDisciplinas() { return disciplinas; }
    public List<Turma> getTurmas() { return turmas; }
}
