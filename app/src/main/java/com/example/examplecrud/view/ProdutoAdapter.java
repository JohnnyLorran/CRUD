package com.example.examplecrud.view;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.OvalShape;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.ColorInt;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.examplecrud.R;
import com.example.examplecrud.model.Produto;

import java.util.List;

public class ProdutoAdapter extends RecyclerView.Adapter<ProdutoAdapter.ProdutoViewHolder>{

    private final List<Produto> produtos;

    ProdutoAdapter(List<Produto> produtos) {
        this.produtos = produtos;
    }

    public List<Produto> getProdutos(){
        return produtos;
    }

    @NonNull
    @Override
    public ProdutoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.rv_item_lista,parent, false);
        return new ProdutoViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProdutoViewHolder holder, int position) {
        Produto produto = produtos.get(position);
        holder.bind(produto);
    }

    @Override
    public int getItemCount() {
        return produtos.size();
    }

    class ProdutoViewHolder extends RecyclerView.ViewHolder{

        TextView id_banco, id_nome, id_campo2;

        public ProdutoViewHolder(@NonNull View itemView) {
            super(itemView);
            id_banco = itemView.findViewById(R.id.id_banco);
            id_nome = itemView.findViewById(R.id.id_nome);
            id_campo2 = itemView.findViewById(R.id.id_campo2);
        }

        public void bind(Produto produto) {
            id_banco.setText("ID: "+String.valueOf(produto.getId()));
            id_nome.setText("Nome: "+produto.getNome());
            id_campo2.setText("Fornecedor: "+produto.getFornecedor());
        }
    }

    private static ShapeDrawable oval(@ColorInt int color, View view){
        ShapeDrawable shapeDrawable = new ShapeDrawable(new OvalShape());
        shapeDrawable.setIntrinsicHeight(view.getHeight());
        shapeDrawable.setIntrinsicWidth(view.getWidth());
        shapeDrawable.getPaint().setColor(color);
        return shapeDrawable;
    }

}

