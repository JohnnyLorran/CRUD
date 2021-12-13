package com.example.examplecrud.controller;

import android.content.ContentValues;
import android.content.Context;

import androidx.annotation.Nullable;

import com.example.examplecrud.datamodel.ClienteDataModel;
import com.example.examplecrud.datasource.AppDataBase;
import com.example.examplecrud.model.Cliente;

import java.util.ArrayList;
import java.util.List;

public class ClienteController extends AppDataBase implements ICrud<Cliente> {

    ContentValues dadosCliente;

    public ClienteController(@Nullable Context context) {
        super(context);
    }

    @Override
    public boolean incluir(Cliente obj) {
        dadosCliente = new ContentValues();

        dadosCliente.put(ClienteDataModel.NOME,obj.getNome());
        dadosCliente.put(ClienteDataModel.EMAIL,obj.getEmail());

        return insert(ClienteDataModel.TABELA,dadosCliente);
    }

    @Override
    public boolean alterar(Cliente obj) {
        dadosCliente = new ContentValues();

        dadosCliente.put(ClienteDataModel.ID,obj.getId());
        dadosCliente.put(ClienteDataModel.NOME,obj.getNome());
        dadosCliente.put(ClienteDataModel.EMAIL,obj.getEmail());

        return update(ClienteDataModel.TABELA,dadosCliente);
    }

    @Override
    public boolean excluir(int id) {
        return deleteByID(ClienteDataModel.TABELA,id);
    }

    @Override
    public List<Cliente> listar(){
        return getAllClientes(ClienteDataModel.TABELA);
    }

}
