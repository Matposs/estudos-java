/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Project/Maven2/JavaApp/src/main/java/${packagePath}/${mainClassName}.java to edit this template
 */

package br.com.nsinova.desafio01.desafio01.Views;

import br.com.nsinova.desafio01.desafio01.Controller.PessoaController;
import br.com.nsinova.desafio01.desafio01.Utils.ConexaoBanco;
import br.com.nsinova.desafio01.desafio01.Utils.Console;


/**
 *
 * @author matheus.oliveira
 */
public class Desafio01 {

    public static void main(String[] args) throws Exception {

        
        //JDK vs JVM -> JVM seria a máquina virtual Java, usada para rodar as aplicações Java em qualquer sistema operacional
        // JDK são as ferramentas usadas pelo desenvolvedor na hora de construir ou editar uma aplicação Java
        // O Java é um linguagem focada na orientação a objeto, fortemente tipada, focada em classes e objetos
        // Além disso, são utilizados métodos e funções para a criação de projetos e seu bom funcionamento
        
        
        
        PessoaController controller = new PessoaController();
        int opcao2 = 1;
        ConexaoBanco conexao = new ConexaoBanco();
        do {
            System.out.println("||||||||| Desafio Escola |||||||||");
            System.out.println("1 - Criar Pessoa");
            System.out.println("2 - Listar Pessoas");
            System.out.println("3 - Buscar Pessoa");
            System.out.println("4 - Editar Pessoa");
            System.out.println("5 - Deletar Pessoa");
            System.out.println("6 - Mais informações da Pessoa");
            System.out.println("0 - Sair");
            int opcao = Console.readInt("Digite uma opção : ");
            
        switch(opcao)
        {
            case 0:  
                opcao2 = 0;
                System.out.println("|||||||||| SAINDO ||||||||||");
            break;
            case 1:
                String tipoz = Console.readString("Informe se é um aluno com a palavra aluno: \n " +
                "Informe se é um professor com a palavara professor: "); 
                controller.AdicionarDados(tipoz);
                break;
            case 2:
                String tipo02 = Console.readString("Informe se é um aluno com a palavra aluno: \n " +
                 "Informe se é um professor com a palavara professor: ");
                String nome2 = Console.readString("Insira o nome da pessoa que deseja buscar: ");
                String CPFf = Console.readString("Insira o CPF da pessoa: ");
                int idade = Console.readInt("Informe a idade: ");
                try {
                    System.out.println(controller.getInformacao(tipo02, nome2, CPFf, idade));
                } catch (Exception e)
                {
                    System.out.println(e);
                }
            break;
            case 3:
                String cpf = Console.readString("Informe o CPF da pessoa que deseja buscar: ");
                controller.buscarPorCPF(cpf);
            break;
            case 4:
                String CPFF = Console.readString("Informe o CPF da pessoa que deseja editar: ");
                controller.Editar(CPFF);
            break;
            case 5:
                String CPF = Console.readString("Informe o CPF da pessoa que será deletada: ");
                controller.Deletar(CPF);
                
            break;
            case 6:
                String tipo2 = Console.readString("Informe se é um aluno com a palavra aluno: \n " +
                 "Informe se é um professor com a palavara professor: ");
                String nome = Console.readString("Insira o nome da pessoa que deseja buscar: ");
                System.out.println(controller.getInformacao(tipo2, nome));
            break;


        }
        } while (opcao2 != 0);
     
        
        
        
        
        
    }
}
