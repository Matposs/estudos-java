/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.nsinova.desafio01.desafio01.Utils;

import br.com.nsinova.desafio01.desafio01.Models.Aluno;
import br.com.nsinova.desafio01.desafio01.Models.Endereco;
import br.com.nsinova.desafio01.desafio01.Models.Pessoa;
import br.com.nsinova.desafio01.desafio01.Models.Professor;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Calendar;

/**
 *
 * @author matheus.oliveira
 */
public class ConexaoBanco {

    public Connection conectar() throws Exception {
        Class.forName("org.postgresql.Driver").newInstance();
        Connection connection = DriverManager.getConnection(
                "jdbc:postgresql://IP:PORT/postgres?"
                + "user=USER&password=PASS");

        return connection;
    }

    @SuppressWarnings("UseSpecificCatch")
    public int inserir(Pessoa pessoa) throws Exception {
        StringBuilder textoSql = new StringBuilder();
        textoSql.append("INSERT INTO pessoa (cpf, nome, data_nascimento, tipo) VALUES (?,?,?,?)");
        Connection conexao = conectar();
        PreparedStatement comando = conexao.prepareStatement(textoSql.toString());

        comando.setString(1, pessoa.getCPF());
        comando.setString(2, pessoa.getNome());
        comando.setDate(3, new java.sql.Date(pessoa.getDataNascimento().getTimeInMillis()));

        if (pessoa instanceof Aluno) {
            comando.setString(4, "aluno");
        } else if (pessoa instanceof Professor) {
            comando.setString(4, "professor");
        } else {
            comando.setNull(4, 0);
        }
        try {
            int valor = comando.executeUpdate();
            conexao.close();
            return valor;
        } catch (Exception e) {
            return 0;
        }
    }

    @SuppressWarnings("UseSpecificCatch")
    public int inserir(Endereco endereco, String CPF) throws Exception {
        StringBuilder sql = new StringBuilder();
        sql.append("INSERT INTO endereco (logradouro, numero, municipio, uf, pessoa_cpf) VALUES (?,?,?,?,?)");
        Connection conexao = conectar();
        PreparedStatement comando = conexao.prepareStatement(sql.toString());

        comando.setString(1, endereco.getLogradouro());
        comando.setString(2, endereco.getNumero());
        comando.setString(3, endereco.getMunicipio());
        comando.setString(4, endereco.getUF());
        comando.setString(5, CPF);

        try {
            int valor = comando.executeUpdate();
            conexao.close();
            return valor;
        } catch (Exception e) {
            return 0;
        }
    }

    @SuppressWarnings("UseSpecificCatch")
    public int deletar(Pessoa pessoa) throws Exception {
        StringBuilder sql = new StringBuilder();
        sql.append("DELETE FROM pessoa WHERE (cpf) VALUES (?)");
        Connection conexao = conectar();
        PreparedStatement comando = conexao.prepareStatement(sql.toString());

        comando.setString(1, pessoa.getCPF());

        try {
            int valor = comando.executeUpdate();
            conexao.close();
            return valor;
        } catch (Exception e) {
            return 0;
        }
    }

    @SuppressWarnings("UseSpecificCatch")
    public Pessoa buscar(String cpf) throws Exception {

        StringBuilder sql = new StringBuilder();
        sql.append("SELECT * FROM pessoa left outer join professor p on p.pessoa_cpf = cpf left outer join alunos a on a.pessoa_cpf = cpf  left outer join endereco e on e.pessoa_cpf = cpfWHERE (cpf) = (?)");
        Connection conexao = conectar();

        PreparedStatement comando = conexao.prepareStatement(sql.toString());
        comando.setString(1, cpf);

        ResultSet result = comando.executeQuery();
        Pessoa pessoa;
        Long data = 0L;
        if (result.next()) {
            String tipo = result.getString("tipo");
            Endereco endereco = new Endereco();
            endereco.setLogradouro(result.getString("logradouro"));
            endereco.setMunicipio(result.getString("municipio"));
            endereco.setNumero(result.getString("numero"));
            endereco.setUF(result.getString("uf"));
            
            if (tipo != null) {
                if (tipo.equalsIgnoreCase("aluno")) {
                    pessoa = new Aluno();
                    pessoa.setCPF(cpf);
                    java.sql.Date date = result.getDate("data_nascimento");
                    if (date != null) {
                        data = date.getTime();
                    }
                    Calendar c = Calendar.getInstance();
                    c.setTimeInMillis(data);
                    pessoa.setDataNascimento(c);
                    pessoa.setNome(result.getString("nome"));
                    pessoa.setEndereco(endereco);
                    ((Aluno)pessoa).setNumeroMatricula(result.getString("matricula"));
                    return pessoa;
                } else if (tipo.equalsIgnoreCase("professor")) {
                    pessoa = new Professor();
                    pessoa.setCPF(cpf);
                    java.sql.Date date = result.getDate("data_nascimento");
                    if (date != null) {
                        data = date.getTime();
                    }
                    Calendar c = Calendar.getInstance();
                    c.setTimeInMillis(data);
                    pessoa.setDataNascimento(c);
                    pessoa.setNome(result.getString("nome"));
                    pessoa.setEndereco(endereco);
                    return pessoa;
                } else {
                    throw new Exception("Tipo da pessoa não declarada");
                }
            } else {
                throw new Exception("Tipo da pessoa retornada como null");
            }
        }
        throw new Exception("Pessoa não encontrada");
    }
    
     public Pessoa buscar(String cpf,String tipo) throws Exception {

        StringBuilder sql = new StringBuilder();
        sql.append("SELECT * FROM pessoa left outer join endereco e on e.pessoa_cpf = cpf WHERE (cpf) = (?)");
        Connection conexao = conectar();

        PreparedStatement comando = conexao.prepareStatement(sql.toString());
        comando.setString(1, cpf);

        ResultSet result = comando.executeQuery();
        Pessoa pessoa;
        Long data = 0L;
        if (result.next()) {
            String tipo = result.getString("tipo");
            if (tipo != null) {
                if (tipo.equalsIgnoreCase("aluno")) {
                    pessoa = new Aluno();
                    pessoa.setCPF(cpf);
                    java.sql.Date date = result.getDate("data_nascimento");
                    if (date != null) {
                        data = date.getTime();
                    }
                    Calendar c = Calendar.getInstance();
                    c.setTimeInMillis(data);
                    pessoa.setDataNascimento(c);
                    pessoa.setNome(result.getString("nome"));
                    return pessoa;

}
