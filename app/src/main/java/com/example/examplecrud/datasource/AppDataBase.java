package com.example.examplecrud.datasource;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

import com.example.examplecrud.api.AppUtil;
import com.example.examplecrud.datamodel.ClienteDataModel;
import com.example.examplecrud.datamodel.ProdutoDataModel;
import com.example.examplecrud.model.Cliente;
import com.example.examplecrud.model.Produto;

import java.util.ArrayList;
import java.util.List;

public class AppDataBase extends SQLiteOpenHelper{

    public static final String DB_NAME = "CrudDB.sqlite";
    public static final int DB_VERSION = 1;

    SQLiteDatabase db;

    public AppDataBase(Context context) {
        super(context, DB_NAME, null, DB_VERSION);

        db = getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL(ClienteDataModel.criarTabela());
        db.execSQL(ProdutoDataModel.criarTabela());
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {


    }

    public boolean insert(String tabela, ContentValues dados){
        boolean retorno = false;
        db = getWritableDatabase();

        try{
            retorno = db.insert(tabela, null, dados) > 0;
        }catch (Exception e){
            Log.d(AppUtil.TAG,"Insert na tabela: " +tabela+ " falhou, erro: " +e.getMessage());
        }

        return retorno;
    }

    public boolean update(String tabela, ContentValues dados){
        boolean retorno = false;
        db = getWritableDatabase();

        try{
            retorno = db.update(tabela,dados, " id = ?", new String[] {String.valueOf(dados.get("id"))} ) > 0;
        }catch (Exception e){
            Log.d(AppUtil.TAG,"Insert na tabela: " +tabela+ " falhou, erro: " +e.getMessage());
        }

        return retorno;
    }

    public boolean deleteByID(String tabela, int id){
        boolean retorno = false;
        db = getWritableDatabase();

        try{
            retorno = db.delete(tabela,"id = ?", new String[] {String.valueOf(id)} ) > 0;
        }catch (Exception e){
            Log.d(AppUtil.TAG,"Delete na tabela: " +tabela+ "where ID = "+id+ " falhou, erro: " +e.getMessage());
        }

        return retorno;
    }

    @SuppressLint("Range")
    public List<Cliente> getAllClientes(String tabela){

        Cliente cliente;

        db = getWritableDatabase();

        List<Cliente> clientes = new ArrayList<>();

        String sql = "SELECT * FROM "+tabela;

        Cursor cursor;

        cursor = db.rawQuery(sql,null);

        if (cursor.moveToFirst()){
            do{
                cliente = new Cliente();

                cliente.setId(cursor.getInt(cursor.getColumnIndex(ClienteDataModel.ID)));
                cliente.setNome(cursor.getString(cursor.getColumnIndex(ClienteDataModel.NOME)));
                cliente.setEmail(cursor.getString(cursor.getColumnIndex(ClienteDataModel.EMAIL)));

                clientes.add(cliente);
            }while(cursor.moveToNext());
        }
        return clientes;
    }

    @SuppressLint("Range")
    public List<Produto> getAllProdutos(String tabela){

        Produto produto;

        db = getWritableDatabase();

        List<Produto> produtos = new ArrayList<>();

        String sql = "SELECT * FROM "+tabela;

        Cursor cursor;

        cursor = db.rawQuery(sql,null);

        if (cursor.moveToFirst()){
            do{
                produto = new Produto();

                produto.setId(cursor.getInt(cursor.getColumnIndex(ProdutoDataModel.ID)));
                produto.setNome(cursor.getString(cursor.getColumnIndex(ProdutoDataModel.NOME)));
                produto.setFornecedor(cursor.getString(cursor.getColumnIndex(ProdutoDataModel.FORNECEDOR)));

                produtos.add(produto);
            }while(cursor.moveToNext());
        }
        return produtos;
    }

}
