package com.example.examplecrud.view;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.examplecrud.R;
import com.example.examplecrud.controller.ClienteController;
import com.example.examplecrud.controller.ProdutoController;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.Collections;


public class ListarActivity extends AppCompatActivity {

    Toolbar toolbar;

    ClienteAdapter clienteAdapter;
    ClienteController clienteController;
    ProdutoAdapter produtoAdapter;
    ProdutoController produtoController;
    FloatingActionButton fb_back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listar);

        RecyclerView rv = findViewById(R.id.recycler_view_main);




        Bundle bundle = getIntent().getExtras();

        toolbar = findViewById(R.id.toolbar);
        fb_back = findViewById(R.id.fb_back);


        if (0 == bundle.getByte("cpL")) {
            toolbar.setTitle("Lista com todos os clientes !");
            clienteController = new ClienteController(getApplicationContext());
            clienteAdapter = new ClienteAdapter(new ArrayList<>(clienteController.listar()));
            rv.setAdapter(clienteAdapter);
        }else if (1 == bundle.getByte("cpL")){
            toolbar.setTitle("Lista com todos os produtos !");
            produtoController = new ProdutoController(getApplicationContext());
            produtoAdapter = new ProdutoAdapter(new ArrayList<>(produtoController.listar()));
            rv.setAdapter(produtoAdapter);
        }


      ItemTouchHelper helper = new ItemTouchHelper(
              new ItemTouchHandler(ItemTouchHelper.UP | ItemTouchHelper.DOWN, ItemTouchHelper.LEFT));
        helper.attachToRecyclerView(rv);

    }


    public void onClick(View v){
        switch (v.getId()){
            case R.id.fb_back:
                Intent trocarDeTela = new Intent(this, MainActivity.class);
                startActivity(trocarDeTela);
                finish();
        }
    }

    private class ItemTouchHandler extends ItemTouchHelper.SimpleCallback{
        public ItemTouchHandler(int dragDirs, int swipeDirs){
            super(dragDirs,swipeDirs);
        }

        @Override
        public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
            Bundle bundle = getIntent().getExtras();
            int from = viewHolder.getAdapterPosition();
            int to = target.getAdapterPosition();
            if (0 == bundle.getByte("cpL")) {
                Collections.swap(clienteAdapter.getClientes(), from, to);
                clienteAdapter.notifyItemMoved(from, to);
            }else if (1 == bundle.getByte("cpL")){
                Collections.swap(produtoAdapter.getProdutos(),from,to);
                produtoAdapter.notifyItemMoved(from,to);
            }
            return true;
        }

        @Override
        public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
            Bundle bundle = getIntent().getExtras();
            if (0 == bundle.getByte("cpL")) {
                clienteController.excluir(clienteAdapter.getClientes().get(viewHolder.getAdapterPosition()).getId());
                clienteAdapter.getClientes().remove(viewHolder.getAdapterPosition());
                clienteAdapter.notifyItemRemoved(viewHolder.getAdapterPosition());
            }else if (1 == bundle.getByte("cpL")){
                produtoController.excluir(produtoAdapter.getProdutos().get(viewHolder.getAdapterPosition()).getId());
                produtoAdapter.getProdutos().remove(viewHolder.getAdapterPosition());
                produtoAdapter.notifyItemRemoved(viewHolder.getAdapterPosition());
            }
        }
    }

}