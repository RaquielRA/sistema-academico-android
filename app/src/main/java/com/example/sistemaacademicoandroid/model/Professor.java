package com.example.sistemaacademicoandroid.model;

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
    public String adicionarTurma(Turma turma) {
        try {
            if (turma == null) throw new Exception("Turma inválida.");
            if (turmasIds.contains(turma.getId())) return "Professor já está nesta turma.";
            turmasIds.add(turma.getId());
            return "Professor atribuído à turma " + turma.getNome();
        } catch (Exception e) {
            return "Erro ao adicionar turma: " + e.getMessage();
        }
    }

    // Consultar turmas do professor
    public List<String> verTurmas(List<Turma> todasTurmas) {
        List<String> nomesTurmas = new ArrayList<>();
        for (Integer id : turmasIds) {
            for (Turma t : todasTurmas) {
                if (t.getId() == id) {
                    nomesTurmas.add(t.getNome());
                    break;
                }
            }
        }
        return nomesTurmas;
    }

    // Consultar alunos de uma turma específica (precisa passar lista de todos os alunos)
    public List<String> verAlunos(int turmaId, List<Turma> todasTurmas, List<Aluno> todosAlunos) {
        List<String> nomesAlunos = new ArrayList<>();
        for (Turma t : todasTurmas) {
            if (t.getId() == turmaId) {
                for (Integer alunoId : t.getAlunosIds()) {
                    for (Aluno a : todosAlunos) {
                        if (a.getId() == alunoId) {
                            nomesAlunos.add(a.getNome());
                            break;
                        }
                    }
                }
                break;
            }
        }
        return nomesAlunos;
    }

    // Lançar nota de um aluno em uma disciplina
    public String lancarNota(int disciplinaId, int alunoId, double nota) {
        try {
            if (!notas.containsKey(disciplinaId)) {
                notas.put(disciplinaId, new HashMap<Integer, Double>());
            }
            Map<Integer, Double> mapaNotas = notas.get(disciplinaId);
            mapaNotas.put(alunoId, nota);
            return "Nota lançada com sucesso!";
        } catch (Exception e) {
            return "Erro ao lançar nota: " + e.getMessage();
        }
    }

    // Lançar faltas de um aluno em uma disciplina
    public String lancarFaltas(int disciplinaId, int alunoId, int quantidade) {
        try {
            if (!faltas.containsKey(disciplinaId)) {
                faltas.put(disciplinaId, new HashMap<Integer, Integer>());
            }
            Map<Integer, Integer> mapaFaltas = faltas.get(disciplinaId);
            mapaFaltas.put(alunoId, quantidade);
            return "Faltas lançadas com sucesso!";
        } catch (Exception e) {
            return "Erro ao lançar faltas: " + e.getMessage();
        }
    }

    // Consultar nota de um aluno
    public Double verNota(int disciplinaId, int alunoId) {
        if (notas.containsKey(disciplinaId)) {
            Map<Integer, Double> mapaNotas = notas.get(disciplinaId);
            if (mapaNotas.containsKey(alunoId)) {
                return mapaNotas.get(alunoId);
            }
        }
        return null;
    }

    // Consultar faltas de um aluno
    public Integer verFaltas(int disciplinaId, int alunoId) {
        if (faltas.containsKey(disciplinaId)) {
            Map<Integer, Integer> mapaFaltas = faltas.get(disciplinaId);
            if (mapaFaltas.containsKey(alunoId)) {
                return mapaFaltas.get(alunoId);
            }
        }
        return null;
    }

    // Getters
    public List<Integer> getTurmasIds() { return turmasIds; }
    public Map<Integer, Map<Integer, Double>> getNotas() { return notas; }
    public Map<Integer, Map<Integer, Integer>> getFaltas() { return faltas; }
}
