/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.nsinova.desafio01.desafio01.Controller;

import br.com.nsinova.desafio01.desafio01.Models.Endereco;
import br.com.nsinova.desafio01.desafio01.Utils.Console;

/**
 *
 * @author matheus.oliveira
 */
public class EnderecoController {

    Endereco endereco = new Endereco();

    public String getEnderecoCompleto(String Logradouro, String Numero, String Municipio, String UF) {
        String returnFinal = "";
        Logradouro = endereco.getLogradouro();
        Municipio = endereco.getMunicipio();
        Numero = endereco.getNumero();
        UF = endereco.getUF();
        if (Logradouro != null && !Logradouro.equals("")) {
            returnFinal = "Logradouro: " + Logradouro;
        }
        if (Municipio != null && !Municipio.equals("")) {
            returnFinal = returnFinal + " Municipio: " + Municipio;
        }
        if (Numero != null && !Numero.equals("")) {
            returnFinal = returnFinal + " Número: " + Numero;
        }
        if (UF != null && !UF.equals("")) {
            returnFinal = returnFinal + " UF: " + UF;
        }
        return returnFinal;
    }
    
    

    public Endereco setEnderecoCompleto() {
        endereco.setLogradouro(Console.readString("Informe o logradouro: "));
        endereco.setMunicipio(Console.readString("Informe o municipio: "));
        endereco.setNumero(Console.readString("Informe o número: "));
        endereco.setUF(Console.readString("Informe a UF: "));
        return endereco;
    }

}
