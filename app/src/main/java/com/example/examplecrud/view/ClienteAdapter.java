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
import com.example.examplecrud.model.Cliente;

import java.util.List;

public class ClienteAdapter extends RecyclerView.Adapter<ClienteAdapter.ClienteViewHolder>{

    private final List<Cliente> clientes;

    ClienteAdapter(List<Cliente> clientes) {
        this.clientes = clientes;
    }

    public List<Cliente> getClientes(){
        return clientes;
    }

    @NonNull
    @Override
    public ClienteViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.rv_item_lista,parent, false);
        return new ClienteViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ClienteViewHolder holder, int position) {
        Cliente cliente = clientes.get(position);
        holder.bind(cliente);
    }

    @Override
    public int getItemCount() {
        return clientes.size();
    }

    class ClienteViewHolder extends RecyclerView.ViewHolder{

        TextView id_banco, id_nome, id_campo2;

        public ClienteViewHolder(@NonNull View itemView) {
            super(itemView);
            id_banco = itemView.findViewById(R.id.id_banco);
            id_nome = itemView.findViewById(R.id.id_nome);
            id_campo2 = itemView.findViewById(R.id.id_campo2);
        }

        public void bind(Cliente cliente) {
            id_banco.setText("ID: "+String.valueOf(cliente.getId()));
            id_nome.setText("Nome: "+cliente.getNome());
            id_campo2.setText("Email: "+cliente.getEmail());
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
