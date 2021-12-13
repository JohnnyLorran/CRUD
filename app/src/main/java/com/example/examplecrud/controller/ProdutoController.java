package com.example.examplecrud.controller;

import android.content.ContentValues;
import android.content.Context;

import androidx.annotation.Nullable;

import com.example.examplecrud.datamodel.ProdutoDataModel;
import com.example.examplecrud.datasource.AppDataBase;
import com.example.examplecrud.model.Produto;

import java.util.ArrayList;
import java.util.List;

public class ProdutoController extends AppDataBase implements ICrud<Produto>{

    ContentValues dadosProduto;

    public ProdutoController(@Nullable Context context) {
        super(context);
    }

    @Override
    public boolean incluir(Produto obj) {
        dadosProduto = new ContentValues();

        dadosProduto.put(ProdutoDataModel.NOME,obj.getNome());
        dadosProduto.put(ProdutoDataModel.FORNECEDOR,obj.getFornecedor());

        return insert(ProdutoDataModel.TABELA,dadosProduto);
    }

    @Override
    public boolean alterar(Produto obj) {
        dadosProduto = new ContentValues();

        dadosProduto.put(ProdutoDataModel.ID,obj.getId());
        dadosProduto.put(ProdutoDataModel.NOME,obj.getNome());
        dadosProduto.put(ProdutoDataModel.FORNECEDOR,obj.getFornecedor());

        return update(ProdutoDataModel.TABELA,dadosProduto);
    }

    @Override
    public boolean excluir(int id) {
        return deleteByID(ProdutoDataModel.TABELA,id);
    }

    @Override
    public List<Produto> listar(){
        return getAllProdutos(ProdutoDataModel.TABELA);

    }
}
