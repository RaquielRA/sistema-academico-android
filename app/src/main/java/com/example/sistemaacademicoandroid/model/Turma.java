package com.example.sistemaacademicoandroid.model;

import java.util.ArrayList;
import java.util.List;

public class Turma {

    private int id;
    private String nome;
    private int disciplinaId;       // ID da disciplina associada
    private int professorId;        // ID do professor que leciona
    private List<Integer> alunosIds; // IDs dos alunos matriculados

    public Turma(int id, String nome, int disciplinaId, int professorId) {
        this.id = id;
        this.nome = nome;
        this.disciplinaId = disciplinaId;
        this.professorId = professorId;
        this.alunosIds = new ArrayList<>();
    }

    // Adicionar aluno à turma
    public String adicionarAluno(Aluno aluno) {
        try {
            if (aluno == null) throw new Exception("Aluno inválido.");
            if (alunosIds.contains(aluno.getId())) return "Aluno já matriculado nesta turma.";
            alunosIds.add(aluno.getId());
            return "Aluno " + aluno.getNome() + " adicionado à turma " + this.nome;
        } catch (Exception e) {
            return "Erro ao adicionar aluno: " + e.getMessage();
        }
    }

    // Remover aluno da turma
    public String removerAluno(Aluno aluno) {
        try {
            if (aluno == null) throw new Exception("Aluno inválido.");
            if (!alunosIds.contains(aluno.getId())) return "Aluno não está nesta turma.";
            alunosIds.remove((Integer) aluno.getId());
            return "Aluno " + aluno.getNome() + " removido da turma " + this.nome;
        } catch (Exception e) {
            return "Erro ao remover aluno: " + e.getMessage();
        }
    }

    // Definir ou alterar professor da turma
    public String definirProfessor(Professor professor) {
        try {
            if (professor == null) throw new Exception("Professor inválido.");
            this.professorId = professor.getId();
            return "Professor " + professor.getNome() + " atribuído à turma " + this.nome;
        } catch (Exception e) {
            return "Erro ao definir professor: " + e.getMessage();
        }
    }

    // Métodos auxiliares para obter objetos a partir de IDs
    public List<Aluno> getAlunos(List<Aluno> todosAlunos) {
        List<Aluno> lista = new ArrayList<>();
        for (Integer id : alunosIds) {
            for (Aluno a : todosAlunos) {
                if (a.getId() == id) {
                    lista.add(a);
                    break;
                }
            }
        }
        return lista;
    }

    public Professor getProfessor(List<Professor> todosProfessores) {
        for (Professor p : todosProfessores) {
            if (p.getId() == this.professorId) return p;
        }
        return null;
    }

    public Disciplina getDisciplina(List<Disciplina> todasDisciplinas) {
        for (Disciplina d : todasDisciplinas) {
            if (d.getId() == this.disciplinaId) return d;
        }
        return null;
    }

    // Getters e Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public int getDisciplinaId() { return disciplinaId; }
    public void setDisciplinaId(int disciplinaId) { this.disciplinaId = disciplinaId; }

    public int getProfessorId() { return professorId; }
    public void setProfessorId(int professorId) { this.professorId = professorId; }

    public List<Integer> getAlunosIds() { return alunosIds; }
    public void setAlunosIds(List<Integer> alunosIds) { this.alunosIds = alunosIds; }

    // equals e hashCode baseados no ID
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Turma other = (Turma) obj;
        return id == other.id;
    }

    @Override
    public int hashCode() {
        return Integer.hashCode(id);
    }
}
