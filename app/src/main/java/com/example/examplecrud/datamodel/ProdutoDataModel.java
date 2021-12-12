package com.example.examplecrud.datamodel;

public class ProdutoDataModel {

    //Modelo Objeto Relacional, toda classe data model, tem esta estrutura

    //Atributo nome da tabela
    public static final String TABELA = "produto";

    //Atributos um para cada campo do Produto
    public static final String ID = "id";
    public static final String NOME = "nome";
    public static final String FORNECEDOR = "fornecedor";

    //Query para criar a tabela no banco de dados
    public static String queryCriarTabelaProduto= "";

    //Método para gerar o script que criará a tabela
    public static String criarTabela(){
        queryCriarTabelaProduto+="CREATE TABLE "+TABELA+" (" ;
        queryCriarTabelaProduto += ID+" integer primary key autoincrement, "; // int
        queryCriarTabelaProduto += NOME+" text, "; // text
        queryCriarTabelaProduto += FORNECEDOR+" text )"; // text
        return queryCriarTabelaProduto;
    }

}
