package com.example.examplecrud.controller;

import java.util.List;

public interface ICrud<T> {

    //Métodos para persistência de dados no banco de dados.

    public boolean incluir(T obj);

    public boolean alterar(T obj);

    public boolean excluir(int id);

   public List<T> listar();

}
