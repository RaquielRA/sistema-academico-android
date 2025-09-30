package com.example.sistemaacademicoandroid.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.example.sistemaacademicoandroid.sistema.Sistema;

public class Aluno extends Usuario {

    private final List<Integer> turmasIds;
    private final Map<Integer, Double> notas;    // disciplinaId -> nota
    private final Map<Integer, Integer> faltas;  // disciplinaId -> faltas

    public Aluno(int id, String nome, String login, String senha) {
        super(id, nome, login, senha, TipoUsuario.ALUNO);
        this.turmasIds = new ArrayList<>();
        this.notas = new HashMap<>();
        this.faltas = new HashMap<>();
    }

    // Receber uma turma
    public String adicionarTurma(int turmaId) {
        Turma turma = Sistema.getInstancia().buscarTurmaPorId(turmaId);
        if (turma == null) return "Erro ao adicionar turma: Turma inválida.";
        if (turmasIds.contains(turmaId)) return "Aluno já está nesta turma.";
        turmasIds.add(turmaId);
        return "Aluno " + getNome() + " adicionado à turma " + turma.getNome();
    }

    // Consultar turmas do aluno
    public List<String> verTurmas() {
        List<String> nomesTurmas = new ArrayList<>();
        for (Integer id : turmasIds) {
            Turma t = Sistema.getInstancia().buscarTurmaPorId(id);
            if (t != null) nomesTurmas.add(t.getNome());
        }
        return nomesTurmas;
    }

    // Consultar disciplinas
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

    // Consultar professores
    public List<String> verProfessores() {
        List<String> professores = new ArrayList<>();
        for (Integer turmaId : turmasIds) {
            Turma t = Sistema.getInstancia().buscarTurmaPorId(turmaId);
            if (t != null && t.getProfessorId() != null) {
                Professor p = Sistema.getInstancia().buscarProfessorPorId(t.getProfessorId());
                String nomeProf = (p != null) ? p.getNome() : "Sem professor";
                if (!professores.contains(nomeProf)) professores.add(nomeProf);
            }
        }
        return professores;
    }

    // Consultar colegas
    public Map<String, List<String>> verColegas() {
        Map<String, List<String>> colegasPorTurma = new HashMap<>();
        for (Integer turmaId : turmasIds) {
            Turma t = Sistema.getInstancia().buscarTurmaPorId(turmaId);
            if (t != null) {
                List<String> colegas = new ArrayList<>();
                for (Integer alunoId : t.getAlunosIds()) {
                    if (!alunoId.equals(this.getId())) {
                        Aluno a = Sistema.getInstancia().buscarAlunoPorId(alunoId);
                        if (a != null) colegas.add(a.getNome());
                    }
                }
                colegasPorTurma.put(t.getNome(), colegas);
            }
        }
        return colegasPorTurma;
    }

    // Consultar nota de uma disciplina
    public Double verNota(int disciplinaId) {
        return notas.get(disciplinaId);
    }

    // Consultar faltas de uma disciplina
    public Integer verFaltas(int disciplinaId) {
        return faltas.get(disciplinaId);
    }

    // Métodos para o professor lançar notas/faltas
    public void setNota(int disciplinaId, double nota) {
        notas.put(disciplinaId, nota);
    }

    public void setFaltas(int disciplinaId, int quantidade) {
        faltas.put(disciplinaId, quantidade);
    }

    // Getter
    public List<Integer> getTurmasIds() {
        return turmasIds;
    }
}
