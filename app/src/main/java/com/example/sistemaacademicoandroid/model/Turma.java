package com.example.sistemaacademicoandroid.model;

import com.example.sistemaacademicoandroid.sistema.Sistema;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Turma {

    private int id;
    private String nome;
    private int disciplinaId;        // ID da disciplina associada
    private Integer professorId;     // ID do professor que leciona (pode ser null)
    private List<Integer> alunosIds; // IDs dos alunos matriculados

    public Turma(int id, String nome, int disciplinaId, Integer professorId) {
        if (nome == null || nome.trim().isEmpty()) {
            throw new IllegalArgumentException("Nome da turma inválido.");
        }
        this.id = id;
        this.nome = nome;
        this.disciplinaId = disciplinaId;
        this.professorId = professorId;
        this.alunosIds = new ArrayList<>();
    }

    // Adicionar aluno à turma
    public String adicionarAluno(Aluno aluno) {
        if (aluno == null) return "Aluno inválido.";
        if (alunosIds.contains(aluno.getId())) return "Aluno já matriculado nesta turma.";
        alunosIds.add(aluno.getId());
        return "Aluno " + aluno.getNome() + " adicionado à turma " + this.nome;
    }

    // Remover aluno da turma
    public String removerAluno(Aluno aluno) {
        if (aluno == null) return "Aluno inválido.";
        if (!alunosIds.contains(aluno.getId())) return "Aluno não está nesta turma.";
        alunosIds.remove((Integer) aluno.getId());
        return "Aluno " + aluno.getNome() + " removido da turma " + this.nome;
    }

    // Definir ou alterar professor da turma
    public String definirProfessor(Professor professor) {
        if (professor == null) return "Professor inválido.";
        this.professorId = professor.getId();
        return "Professor " + professor.getNome() + " atribuído à turma " + this.nome;
    }

    // Obter objetos a partir de IDs usando o Sistema
    public List<Aluno> getAlunos() {
        List<Aluno> lista = new ArrayList<>();
        for (Integer id : alunosIds) {
            Aluno a = Sistema.getInstancia().buscarAlunoPorId(id);
            if (a != null) lista.add(a);
        }
        return lista;
    }

    public Professor getProfessor() {
        if (professorId == null) return null;
        return Sistema.getInstancia().buscarProfessorPorId(professorId);
    }

    public Disciplina getDisciplina() {
        return Sistema.getInstancia().buscarDisciplinaPorId(disciplinaId);
    }

    // Getters e Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public int getDisciplinaId() { return disciplinaId; }
    public void setDisciplinaId(int disciplinaId) { this.disciplinaId = disciplinaId; }

    public Integer getProfessorId() { return professorId; }
    public void setProfessorId(Integer professorId) { this.professorId = professorId; }

    public List<Integer> getAlunosIds() { return alunosIds; }
    public void setAlunosIds(List<Integer> alunosIds) { this.alunosIds = alunosIds; }

    // equals e hashCode baseados no ID
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Turma)) return false;
        Turma other = (Turma) obj;
        return id == other.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return nome;
    }
}
