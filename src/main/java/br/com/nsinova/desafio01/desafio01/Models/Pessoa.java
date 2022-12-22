/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.nsinova.desafio01.desafio01.Models;

import java.util.Calendar;
import br.com.nsinova.desafio01.desafio01.Utils.Console;


/**
 *
 * @author matheus.oliveira
 */
public abstract class Pessoa {

    
    private String CPF;
    private String Nome;
    private Calendar DataNascimento;
    private Endereco endereco;
    
    
    public String getCPF() {
        return CPF;
    }

    public void setCPF(String CPF) {
        this.CPF = CPF;
    }

    public String getNome() {
        return Nome;
    }

    public void setNome(String Nome) {
        this.Nome = Nome;
    }

    public Calendar getDataNascimento() {
        return DataNascimento;
    }

    public void setDataNascimento(Calendar DataNascimento) {
        this.DataNascimento = DataNascimento;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }

    
    
    public String toString() {
        return "Nome: " +Nome + " CPF: " + CPF + " Nascimento: " + DataNascimento.get(Calendar.DAY_OF_MONTH) + 
                "/" + DataNascimento.get(Calendar.MONTH) + "/" + DataNascimento.get(Calendar.YEAR);
    }
    
    
    
}
