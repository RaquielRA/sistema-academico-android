package com.example.sistemaacademicoandroid.model;

import com.example.sistemaacademicoandroid.sistema.Sistema;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Professor extends Usuario {

    // Lista de IDs das turmas que o professor leciona
    private List<Integer> turmasIds;

    // Notas e faltas lançadas, organizadas por disciplinaId e alunoId
    private Map<Integer, Map<Integer, Double>> notas;   // disciplinaId -> (alunoId -> nota)
    private Map<Integer, Map<Integer, Integer>> faltas; // disciplinaId -> (alunoId -> faltas)

    public Professor(int id, String nome, String login, String senha) {
        super(id, nome, login, senha, TipoUsuario.PROFESSOR);
        this.turmasIds = new ArrayList<>();
        this.notas = new HashMap<>();
        this.faltas = new HashMap<>();
    }

    // Receber uma turma (chamado pelo Administrador)
    public String adicionarTurma(int turmaId) {
        Turma turma = Sistema.getInstancia().buscarTurmaPorId(turmaId);
        if (turma == null) return "Erro ao adicionar turma: Turma inválida.";
        if (turmasIds.contains(turmaId)) return "Professor já está nesta turma.";
        turmasIds.add(turmaId);
        return "Professor atribuído à turma " + turma.getNome();
    }

    // Consultar turmas do professor
    public List<String> verTurmas() {
        List<String> nomesTurmas = new ArrayList<>();
        for (Integer id : turmasIds) {
            Turma t = Sistema.getInstancia().buscarTurmaPorId(id);
            if (t != null) nomesTurmas.add(t.getNome());
        }
        return nomesTurmas;
    }

    // Consultar alunos de uma turma específica
    public List<String> verAlunos(int turmaId) {
        List<String> nomesAlunos = new ArrayList<>();
        Turma turma = Sistema.getInstancia().buscarTurmaPorId(turmaId);
        if (turma != null) {
            for (Integer alunoId : turma.getAlunosIds()) {
                Aluno aluno = Sistema.getInstancia().buscarAlunoPorId(alunoId);
                if (aluno != null) nomesAlunos.add(aluno.getNome());
            }
        }
        return nomesAlunos;
    }

    // Consultar disciplinas das turmas que o professor leciona
    public List<String> verDisciplinas() {
        List<String> nomesDisciplinas = new ArrayList<>();
        for (Integer turmaId : turmasIds) {
            Turma t = Sistema.getInstancia().buscarTurmaPorId(turmaId);
            if (t != null) {
                Disciplina d = Sistema.getInstancia().buscarDisciplinaPorId(t.getDisciplinaId());
                if (d != null && !nomesDisciplinas.contains(d.getNome())) {
                    nomesDisciplinas.add(d.getNome());
                }
            }
        }
        return nomesDisciplinas;
    }

    // Lançar nota de um aluno em uma disciplina
    public String lancarNota(int disciplinaId, int alunoId, double nota) {
        try {
            if (!notas.containsKey(disciplinaId)) {
                notas.put(disciplinaId, new HashMap<>());
            }
            notas.get(disciplinaId).put(alunoId, nota);
            return "Nota lançada com sucesso!";
        } catch (Exception e) {
            return "Erro ao lançar nota: " + e.getMessage();
        }
    }

    // Lançar faltas de um aluno em uma disciplina
    public String lancarFaltas(int disciplinaId, int alunoId, int quantidade) {
        try {
            if (!faltas.containsKey(disciplinaId)) {
                faltas.put(disciplinaId, new HashMap<>());
            }
            faltas.get(disciplinaId).put(alunoId, quantidade);
            return "Faltas lançadas com sucesso!";
        } catch (Exception e) {
            return "Erro ao lançar faltas: " + e.getMessage();
        }
    }

    // Consultar nota de um aluno
    public Double verNota(int disciplinaId, int alunoId) {
        if (notas.containsKey(disciplinaId)) {
            return notas.get(disciplinaId).get(alunoId);
        }
        return null;
    }

    // Consultar faltas de um aluno
    public Integer verFaltas(int disciplinaId, int alunoId) {
        if (faltas.containsKey(disciplinaId)) {
            return faltas.get(disciplinaId).get(alunoId);
        }
        return null;
    }

    // Getter
    public List<Integer> getTurmasIds() { return turmasIds; }
    public Map<Integer, Map<Integer, Double>> getNotas() { return notas; }
    public Map<Integer, Map<Integer, Integer>> getFaltas() { return faltas; }
}
