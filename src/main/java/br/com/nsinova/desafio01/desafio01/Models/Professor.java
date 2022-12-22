/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.nsinova.desafio01.desafio01.Models;

import java.util.ArrayList;
import java.util.Calendar;


/**
 *
 * @author matheus.oliveira
 */
public class Professor extends Pessoa {
    
    private ArrayList <String> conhecimentos = new ArrayList<>();

    private String Especialidade;

    public String getEspecialidade() {
        return Especialidade;
    }
    public void setEspecialidade(String especialidade) {
        this.Especialidade = especialidade;
    }

    public ArrayList<String> getConhecimentos() {
        return conhecimentos;
    }

    public void setConhecimentos(ArrayList<String> conhecimentos) {
        this.conhecimentos = conhecimentos;
    }
    //criar uma string de conhecimentos para serem mostrados
    public String getStringConhecimentos()
    {
       String stringConhecimentos = new String();
       if (this.conhecimentos!= null)
       {
           for (String conhecimento : conhecimentos)
           {
               if (stringConhecimentos.length()<= 0)
               {
                   stringConhecimentos = conhecimento;
               }
               else {
                   stringConhecimentos+=", " + conhecimento;
               }
           }      
       }
       return stringConhecimentos;
    }
      
    @Override
       public String toString()
       {
          return "Nome: " +this.getNome()+ " CPF: " + this.getCPF() + 
                  " Nascimento:" + this.getDataNascimento().get(Calendar.DAY_OF_MONTH) +
                  "/" + this.getDataNascimento().get(Calendar.MONTH) +
                  "/" + this.getDataNascimento().get(Calendar.YEAR) + " Especialidade: " +  Especialidade
                  + " Conhecimentos: " + getStringConhecimentos();
       }
}
