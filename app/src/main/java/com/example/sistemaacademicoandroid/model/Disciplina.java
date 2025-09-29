package com.example.sistemaacademicoandroid.model;

import java.util.Objects;

public class Disciplina {

    private final int id;
    private final String nome;
    private final int cargaHoraria; // em horas

    public Disciplina(int id, String nome, int cargaHoraria) {
        if (nome == null || nome.trim().isEmpty()) {
            throw new IllegalArgumentException("Nome da disciplina inválido.");
        }
        if (cargaHoraria <= 0) {
            throw new IllegalArgumentException("Carga horária deve ser maior que 0.");
        }
        this.id = id;
        this.nome = nome;
        this.cargaHoraria = cargaHoraria;
    }

    // Getters
    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public int getCargaHoraria() {
        return cargaHoraria;
    }

    // equals e hashCode baseados no ID
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Disciplina other = (Disciplina) obj;
        return id == other.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Disciplina{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", cargaHoraria=" + cargaHoraria +
                '}';
    }
}
