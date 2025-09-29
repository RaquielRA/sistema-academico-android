package com.example.sistemaacademicoandroid;

import com.example.sistemaacademicoandroid.model.*;

import org.junit.Test;
import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

public class ExampleUnitTest {

    @Test
    public void testeCadastroEConsultaCompleto() {
        List<Aluno> todosAlunos = new ArrayList<>();
        List<Professor> todosProfessores = new ArrayList<>();
        List<Disciplina> todasDisciplinas = new ArrayList<>();
        List<Turma> todasTurmas = new ArrayList<>();

        Administrador admin = new Administrador(1, "Admin", "admin@email.com", "123456");

        Disciplina matematica = new Disciplina(1, "Matemática", 60);
        Disciplina portugues = new Disciplina(2, "Português", 40);
        todasDisciplinas.add(matematica);
        todasDisciplinas.add(portugues);

        Professor profJoao = new Professor(1, "João", "joao@email.com", "abcdef");
        Professor profMaria = new Professor(2, "Maria", "maria@email.com", "ghijkl");
        todosProfessores.add(profJoao);
        todosProfessores.add(profMaria);

        Aluno alunoAna = new Aluno(1, "Ana", "ana@email.com", "mnopqr");
        Aluno alunoBruno = new Aluno(2, "Bruno", "bruno@email.com", "stuvwx");
        todosAlunos.add(alunoAna);
        todosAlunos.add(alunoBruno);

        Turma turma1 = new Turma(1, "Turma A", matematica.getId(), profJoao.getId());
        Turma turma2 = new Turma(2, "Turma B", portugues.getId(), profMaria.getId());
        todasTurmas.add(turma1);
        todasTurmas.add(turma2);

        // Teste ajustado para refletir o retorno real
        assertEquals("Aluno Ana adicionado à turma Turma A", turma1.adicionarAluno(alunoAna));
        assertEquals("Aluno Bruno adicionado à turma Turma A", turma1.adicionarAluno(alunoBruno));
        assertEquals("Aluno Ana adicionado à turma Turma B", turma2.adicionarAluno(alunoAna));

        assertEquals("Aluno Ana adicionado à turma Turma A", alunoAna.adicionarTurma(turma1));
        assertEquals("Aluno Ana adicionado à turma Turma B", alunoAna.adicionarTurma(turma2));
        assertEquals("Aluno Bruno adicionado à turma Turma A", alunoBruno.adicionarTurma(turma1));

        assertEquals("Professor atribuído à turma Turma A", profJoao.adicionarTurma(turma1));
        assertEquals("Professor atribuído à turma Turma B", profMaria.adicionarTurma(turma2));

        assertEquals("Nota lançada com sucesso!", profJoao.lancarNota(matematica.getId(), alunoAna.getId(), 9.5));
        assertEquals("Faltas lançadas com sucesso!", profJoao.lancarFaltas(matematica.getId(), alunoAna.getId(), 1));

        assertEquals("Nota lançada com sucesso!", profJoao.lancarNota(matematica.getId(), alunoBruno.getId(), 7.0));
        assertEquals("Faltas lançadas com sucesso!", profJoao.lancarFaltas(matematica.getId(), alunoBruno.getId(), 3));

        assertEquals(List.of("Turma A", "Turma B"), alunoAna.verTurmas(todasTurmas));
        assertEquals(List.of("Matemática", "Português"), alunoAna.verDisciplinas(todasTurmas, todasDisciplinas));
        assertEquals(List.of("João", "Maria"), alunoAna.verProfessores(todasTurmas, todosProfessores));
    }
}
