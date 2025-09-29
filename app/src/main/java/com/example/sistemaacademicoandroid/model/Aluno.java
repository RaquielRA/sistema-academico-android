package com.example.sistemaacademicoandroid.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    public String adicionarTurma(Turma turma) {
        if (turma == null) return "Erro ao adicionar turma: Turma inválida.";
        if (turmasIds.contains(turma.getId())) return "Aluno já está nesta turma.";
        turmasIds.add(turma.getId());
        return "Aluno " + getNome() + " adicionado à turma " + turma.getNome();
    }

    // Consultar turmas
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

    // Consultar disciplinas
    public List<String> verDisciplinas(List<Turma> todasTurmas, List<Disciplina> todasDisciplinas) {
        List<String> nomesDisciplinas = new ArrayList<>();
        for (Integer turmaId : turmasIds) {
            for (Turma t : todasTurmas) {
                if (t.getId() == turmaId) {
                    for (Disciplina d : todasDisciplinas) {
                        if (d.getId() == t.getDisciplinaId() && !nomesDisciplinas.contains(d.getNome())) {
                            nomesDisciplinas.add(d.getNome());
                        }
                    }
                }
            }
        }
        return nomesDisciplinas;
    }

    // Consultar professores
    public List<String> verProfessores(List<Turma> todasTurmas, List<Professor> todosProfessores) {
        List<String> professores = new ArrayList<>();
        for (Integer turmaId : turmasIds) {
            for (Turma t : todasTurmas) {
                if (t.getId() == turmaId) {
                    Professor prof = t.getProfessor(todosProfessores);
                    String nomeProf = (prof != null) ? prof.getNome() : "Sem professor";
                    if (!professores.contains(nomeProf)) {
                        professores.add(nomeProf);
                    }
                }
            }
        }
        return professores;
    }

    // Consultar colegas
    public Map<String, List<String>> verColegas(List<Turma> todasTurmas, List<Aluno> todosAlunos) {
        Map<String, List<String>> colegasPorTurma = new HashMap<>();
        for (Integer turmaId : turmasIds) {
            for (Turma t : todasTurmas) {
                if (t.getId() == turmaId) {
                    List<String> colegas = new ArrayList<>();
                    for (Aluno a : t.getAlunos(todosAlunos)) {
                        if (!a.equals(this)) {
                            colegas.add(a.getNome());
                        }
                    }
                    colegasPorTurma.put(t.getNome(), colegas);
                }
            }
        }
        return colegasPorTurma;
    }

    // Consultar notas
    public Double verNota(int disciplinaId) {
        return (notas != null) ? notas.get(disciplinaId) : null;
    }

    // Consultar faltas
    public Integer verFaltas(int disciplinaId) {
        return (faltas != null) ? faltas.get(disciplinaId) : null;
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
