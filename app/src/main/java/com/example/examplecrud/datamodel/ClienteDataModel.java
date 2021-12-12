package com.example.examplecrud.datamodel;

public class ClienteDataModel {

    //Modelo Objeto Relacional, toda classe data model, tem esta estrutura


    //Atributo nome da tabela
    public static final String TABELA = "cliente";

    //Atributos um para cada campo do cliente
    public static final String ID = "id";
    public static final String NOME = "nome";
    public static final String EMAIL = "email";

    //Query para criar a tabela no banco de dados
    public static String queryCriarTabelaCliente = "";

    //Método para gerar o script que criará a tabela
    public static String criarTabela(){
        queryCriarTabelaCliente +="CREATE TABLE "+TABELA+" (" ;
        queryCriarTabelaCliente += ID+" integer primary key autoincrement, "; // int
        queryCriarTabelaCliente += NOME+" text, "; // text
        queryCriarTabelaCliente += EMAIL+" text )"; // text
        return queryCriarTabelaCliente;
    }

}
