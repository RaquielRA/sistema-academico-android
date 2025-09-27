# 📚 Sistema Acadêmico – Gestão de Notas e Faltas

## 📌 Descrição
Este projeto implementa um sistema acadêmico simples em Java, baseado em orientação a objetos, para gerenciar usuários (alunos, professores e administrador), além de disciplinas, turmas e registros de notas/faltas.

O sistema permite que:

- Administrador cadastre alunos, professores e disciplinas, além de criar turmas.
- Professor lance notas e faltas dos alunos em suas disciplinas.
- Aluno consulte suas notas e faltas em cada disciplina/turma.

## 🏗️ Estrutura de Classes
O sistema é composto por 7 classes principais além da classe Main:

- **Usuário** → Classe mãe (atributos e métodos comuns).
- **Aluno** → Subclasse de Usuário; pode consultar notas e faltas.
- **Professor** → Subclasse de Usuário; pode lançar notas e faltas.
- **Administrador** → Subclasse de Usuário; cadastra usuários e disciplinas.
- **Disciplina** → Representa uma disciplina (nome, carga horária, etc.).
- **Turma** → Agrupa alunos, disciplinas e professor.
- **Registro** → Representa o desempenho de um aluno em uma disciplina/turma (nota e faltas).

## 🔗 Relacionamentos e Cardinalidades
- Administrador → Aluno/Professor/Disciplina: 1..N  
- Aluno ↔ Turma: N..N  
- Turma ↔ Disciplina: N..N  
- Professor → Disciplina: 1..N  
- Registro → (Aluno, Disciplina, Turma): 1..1 (cada registro pertence a um único aluno em uma disciplina/turma)

## ⚙️ Funcionalidades

**Administrador**
- cadastrarAluno()
- cadastrarProfessor()
- cadastrarDisciplina()
- criarTurma()

**Professor**
- lançarNota()
- lançarFalta()

**Aluno**
- consultarNotas()
- consultarFaltas()

**Turma**
- adicionarAluno()
- adicionarDisciplina()
- listarAlunos()
- listarDisciplinas()

**Registro**
- atualizarNota()
- atualizarFaltas()
- exibirRegistro()

- ## 📌 Possíveis Extensões Futuras
- Persistência em banco de dados.
- Geração de relatórios em PDF/Excel.
- Controle de permissões mais avançado.

## 👩‍💻 Autor
Desenvolvido por Raquiel Ribeiro Alexandre.


