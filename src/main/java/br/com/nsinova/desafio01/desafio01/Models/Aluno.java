/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.nsinova.desafio01.desafio01.Models;

import java.util.Calendar;


/**
 *
 * @author matheus.oliveira
 */
public class Aluno extends Pessoa {
    
    private String numeroMatricula;

   
    public String getNumeroMatricula() {
        return numeroMatricula;
    }
    public void setNumeroMatricula(String numeroMatricula) {
        this.numeroMatricula = numeroMatricula;
    }
    
    @Override
       public String toString()
       {
          return "Nome: " +this.getNome()+ " CPF: " + this.getCPF() + 
                  " Nascimento: " + this.getDataNascimento().get(Calendar.DAY_OF_MONTH) +
                  "/" + this.getDataNascimento().get(Calendar.MONTH) +
                  "/" + this.getDataNascimento().get(Calendar.YEAR) + "Número da matricula: " +  numeroMatricula;
       }
    
}
