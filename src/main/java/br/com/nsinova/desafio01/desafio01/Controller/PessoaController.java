/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.nsinova.desafio01.desafio01.Controller;

import br.com.nsinova.desafio01.desafio01.Models.Aluno;
import br.com.nsinova.desafio01.desafio01.Models.Pessoa;
import br.com.nsinova.desafio01.desafio01.Models.Professor;
import br.com.nsinova.desafio01.desafio01.Utils.ConexaoBanco;
import br.com.nsinova.desafio01.desafio01.Utils.Console;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 *
 * @author matheus.oliveira
 */
public class PessoaController {

    Pessoa aluno;
    Pessoa professor;
    String tipoz;
    int anoAluno, mesesAluno, anoProf, mesesProf;
    private ArrayList<Pessoa> pessoas = new ArrayList<>();
    EnderecoController endereco = new EnderecoController();
    private ArrayList<String> conhecimentos = new ArrayList<>();

    public String getIdade(String tipo) {
        Calendar today = Calendar.getInstance();
        if (tipo.equalsIgnoreCase("aluno")) {
            int anoNasc = aluno.getDataNascimento().get(Calendar.YEAR);
            int mesNasc = aluno.getDataNascimento().get(Calendar.MONTH);
            anoAluno = today.get(Calendar.YEAR) - anoNasc;
            mesesAluno = today.get(Calendar.MONTH) - mesNasc + 1;
            aluno.getDataNascimento();
            return "A idade do aluno é de: " + anoAluno + " e: " + mesesAluno;
        } else if (tipo.equalsIgnoreCase("professor")) {
            int anoNascProf = professor.getDataNascimento().get(Calendar.YEAR);
            int mesNascProf = professor.getDataNascimento().get(Calendar.MONTH);
            anoProf = today.get(Calendar.YEAR) - anoNascProf;
            mesesProf = today.get(Calendar.MONTH) - mesNascProf + 1;
            professor.getDataNascimento();
            return "A idade do professor é de: " + anoProf + " e: " + mesesProf;
        } else {
            return "Tipo inválido, por favor digite Professor ou Aluno";
        }
    }

    public String getInformacao(String tipo, String nome) {
        if (tipo.equalsIgnoreCase("Aluno")) {
            for (Pessoa alunoCadastrado : pessoas) {
                if (alunoCadastrado.getNome().equalsIgnoreCase(nome)) {
                    getIdade(tipo);
                    String CPF = aluno.getCPF();
                    String Nome = aluno.getNome();
                    return "Nome:" + Nome + "|" + "CPF:" + CPF + "|" + "Idade:" + anoAluno + "|" + "anos e: " + mesesAluno + " meses";
                }
            }
        } else if (tipo.equalsIgnoreCase("Professor")) {
            for (Pessoa professorCadastrado : pessoas) {
                if (professorCadastrado.getNome().equalsIgnoreCase(nome)) {
                    getIdade(tipo);
                    String CPF = professor.getCPF();
                    String Nome = professor.getNome();
                    return "Nome:" + Nome + "|" + "CPF:" + CPF + "|" + "Idade: " + anoProf + "" + "anos e: " + mesesProf + " meses"
                            + "Especialidade: " + ((Professor) professor).getEspecialidade()
                            + "Conhecimentos: " + ((Professor) professor).getStringConhecimentos();
                }
            }
        }
        return "Tipo inválido!, por favor informe se é um Aluno ou Professor";
    }

    public List<Pessoa> getInformacao(String tipo, String nome, String CPF, int idade) throws Exception {
        List<Pessoa> pessoas = new ArrayList<>();
        if (tipo.equalsIgnoreCase("Aluno")) {
            for (Pessoa alunoCadastrado : this.pessoas) {
                Calendar today = Calendar.getInstance();
                int anos = today.get(Calendar.YEAR) - aluno.getDataNascimento().get(Calendar.YEAR);
                if (alunoCadastrado.getNome().equalsIgnoreCase(nome) && alunoCadastrado.getCPF().equals(CPF)
                        && idade == anos) {
                    getIdade(tipo);
                    pessoas.add(alunoCadastrado);

                }
            }
        } else if (tipo.equalsIgnoreCase("Professor")) {
            for (Pessoa professorCadastrado : this.pessoas) {
                Calendar today = Calendar.getInstance();
                int anos = today.get(Calendar.YEAR) - professor.getDataNascimento().get(Calendar.YEAR);
                if (professorCadastrado.getNome().equalsIgnoreCase(nome) && professorCadastrado.getCPF().equals(CPF)
                        && idade == anos) {
                    getIdade(tipo);
                    pessoas.add(professorCadastrado);
                }
            }
            return pessoas;
        }
        throw new Exception("Tipo inválido!, por favor informe se é um Aluno ou Professor");
    }

    public static Pessoa criar(String tipo) {
        if (tipo.equalsIgnoreCase("aluno")) {
            System.out.println("aluno criado com sucesso!");
            return new Aluno();
        } else if (tipo.equalsIgnoreCase("professor")) {
            System.out.println("professor criado com sucesso!");
            return new Professor();
        } else {
            try {
                throw new Exception("aa");
            } catch (Exception e) {
                System.out.println("\nTipo inválido, por favor retorne como professor ou aluno");
                return null;
            }
        }

    }

    public void AdicionarDados(String tipoz) throws Exception {
        ConexaoBanco conexao = new ConexaoBanco();
        if (tipoz.equalsIgnoreCase("aluno")) {

            System.out.println("|||||||||| ALUNO ||||||||||");
            aluno = criar(tipoz);
            String nome = Console.readString("Informe o nome: ");
            String CPF = Console.readString("Informe o CPF: ");
            String matricula = Console.readString("Insira o número da matrícula: ");
            aluno.setNome(nome);
            aluno.setCPF(CPF);
            aluno.setDataNascimento(AdicionarData());
            aluno.setEndereco(endereco.setEnderecoCompleto());
            ((Aluno) aluno).setNumeroMatricula(matricula);
            pessoas.add(aluno);
            if (conexao.inserir(aluno) > 0) {
                System.out.println("Pessoa cadastrada com sucesso!");
            } else {
                System.out.println("Pessoa não cadastrada!");
            }
            if (conexao.inserir(aluno.getEndereco(), aluno.getCPF()) > 0) {
                System.out.println("Endereço cadastrado com sucesso!");
            } else {
                System.out.println("Endereço não cadastrado!");
            }

        } else if (tipoz.equalsIgnoreCase("professor")) {
            System.out.println("|||||||||| PROFESSOR ||||||||||");
            professor = criar(tipoz);
            String CPF = Console.readString("Informe o CPF: ");
            String Nome = Console.readString("Informe o nome: ");
            String especialidade = Console.readString("Informe a especialidade: ");
            professor.setNome(Nome);
            professor.setCPF(CPF);
            professor.setDataNascimento(AdicionarData());
            ((Professor) professor).setEspecialidade(especialidade);
            professor.setEndereco(endereco.setEnderecoCompleto());
            pessoas.add(professor);
            ((Professor) professor).setConhecimentos(adicionarConhecimentos(CPF, tipoz));
            if (conexao.inserir(professor) > 0) {
                System.out.println("Pessoa cadastrada com sucesso!");
            } else {
                System.out.println("Pessoa não cadastrada!");
            }
            if (conexao.inserir(professor.getEndereco(), professor.getCPF()) > 0) {
                System.out.println("Endereço cadastrado com sucesso!");
            } else {
                System.out.println("Endereço não cadastrado!");
            }
        } else {
            System.out.println("Informe se é um aluno ou um professor");
        }
    }

    public List<Pessoa> listar() {
        for (Pessoa pessoa : pessoas) {
            System.out.println(pessoas);
        }

        return pessoas;
    }

    public ArrayList adicionarConhecimentos(String CPF, String tipo) {
        for (Pessoa professorCadastrado : pessoas) {
            if (professorCadastrado.getCPF().equals(CPF) && tipo.equalsIgnoreCase("professor")) {
                int opcao = 0;
                @SuppressWarnings("LocalVariableHidesMemberVariable")
                ArrayList<String> conhecimentos = new ArrayList();
                do {
                    System.out.println("Adicione os conhecimentos : ");
                    String conhecimento = Console.readString("Informe qual conhecimento será adicionado: ");
                    conhecimentos.add(conhecimento);
                    opcao = Console.readInt("Digite 1 para continuar adicionando ou 0 para sair: ");

                } while (opcao != 0);
                ((Professor) professorCadastrado).setConhecimentos(conhecimentos);
                return conhecimentos;
            }
        }
        return null;
    }

    public List<String> ListarConhecimentos() {
        for (String conhecimento : conhecimentos) {
            System.out.println(conhecimentos);
        }
        return conhecimentos;
    }

    public Pessoa Deletar(String CPF) throws Exception {
        ConexaoBanco conexao = new ConexaoBanco();
        for (Pessoa pessoaCadastrada : pessoas) {
            if (pessoaCadastrada.getCPF().equals(CPF)) {
                if (conexao.deletar(pessoaCadastrada) > 0) {
                    System.out.println("Pessoa deletada com sucesso!");
                    pessoas.remove(pessoaCadastrada);
                    return pessoaCadastrada;
                } else {
                    System.out.println("Falha ao deletar!");
                    return null;

                }

            }

        }
        return null;
    }

    public Pessoa Editar(String CPF) {
        for (Pessoa pessoaCadastrada : pessoas) {
            if (pessoaCadastrada.getCPF().equals(CPF)) {
                int opcao = Console.readInt("Digite 1 para alterar o nome: \n"
                        + "Digite 2 para alterar o endereço : \n"
                        + "Digite 3 para alterar a data de nascimento: ");
                switch (opcao) {
                    case 1:
                        pessoaCadastrada.setNome(Console.readString("Insira o novo nome: "));
                        //update nome
                        break;
                    case 2:
                        pessoaCadastrada.setEndereco(endereco.setEnderecoCompleto());
                        //update endereco
                        break;
                    case 3:
                        pessoaCadastrada.setDataNascimento(AdicionarData());
                        //update nascimento
                        break;
                    default:
                        System.out.println("Opção inválida!");
                        break;
                }
            }
        }
        return null;
    }

    public Pessoa buscarPorCPF(String CPF) throws Exception {
        ConexaoBanco conexao = new ConexaoBanco();
        Pessoa pessoaCadastrada = conexao.buscar(CPF);
        if (pessoaCadastrada != null) {
            System.out.println(pessoaCadastrada);
            return pessoaCadastrada;
        } else {
            System.out.println("Falha ao buscar!");
            return null;
        }

    }

    public Calendar AdicionarData() {
        Calendar calendar = Calendar.getInstance();
        int ano = Console.readInt("Informe seu ano de nascimento AAAA: ");
        int mes = Console.readInt("Informe seu mes de nascimento MM: ");
        int dia = Console.readInt("Informe seu dia de nascimento DD: ");
        calendar.set(ano, mes, dia);
        return calendar;
    }

}
