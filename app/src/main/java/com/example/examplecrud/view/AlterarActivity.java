package com.example.examplecrud.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.example.examplecrud.R;
import com.example.examplecrud.controller.ClienteController;
import com.example.examplecrud.controller.ProdutoController;
import com.example.examplecrud.datamodel.ClienteDataModel;
import com.example.examplecrud.model.Cliente;
import com.example.examplecrud.model.Produto;

import java.util.ArrayList;
import java.util.List;

public class AlterarActivity extends AppCompatActivity {

    List<Cliente> listC  = new ArrayList<>();
    List<Produto> listP = new ArrayList<>();
    ArrayList<String> lista = new ArrayList<>();
    ArrayList<Integer> listaID = new ArrayList<>();
    ClienteController clienteController;
    ProdutoController produtoController;
    Cliente cliente;
    Produto produto;
    Spinner spinner;
    TextView txt2;
    EditText edtNOME,edtCampo2;
    Button btnLimpar,btnAlterar,btnVoltar;
    ArrayAdapter adapter;
    boolean primeiravez = true;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alterar);


        spinner = findViewById(R.id.spinner);
        txt2 = findViewById(R.id.txt2);
        edtNOME = findViewById(R.id.edtNOME);
        edtCampo2 = findViewById(R.id.edtCampo2);
        btnLimpar = findViewById(R.id.btnLimpar);
        btnAlterar = findViewById(R.id.btnAlterar);
        btnVoltar = findViewById(R.id.btnVoltar);

        Bundle bundle = getIntent().getExtras();

        if (0 == bundle.getByte("cpA")) {
            txt2.setText("Selecione qual cliente deseja alterar");
            btnAlterar.setText("Alterar Cliente");
            clienteController = new ClienteController(this);

            for (Cliente c : clienteController.listar()){
                lista.add(c.getNome());
                listaID.add(c.getId());
                listC.add(c);
            }

            adapter = new ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item,lista);
            spinner.setAdapter(adapter);
        } else if (1 == bundle.getByte("cpA")) {
            txt2.setText("Selecione qual produto deseja alterar");
            btnAlterar.setText("Alterar Produto");
            produtoController = new ProdutoController(this);

            for (Produto p : produtoController.listar()){
                lista.add(p.getNome());
                listaID.add(p.getId());
                listP.add(p);
            }

            adapter = new ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item,lista);
            spinner.setAdapter(adapter);
        }

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (0 == bundle.getByte("cpA")) {
                    edtNOME.setText(listC.get(spinner.getSelectedItemPosition()).getNome());
                    edtCampo2.setText(listC.get(spinner.getSelectedItemPosition()).getEmail());
                } else if (1 == bundle.getByte("cpA")) {
                    edtNOME.setText(listP.get(spinner.getSelectedItemPosition()).getNome());
                    edtCampo2.setText(listP.get(spinner.getSelectedItemPosition()).getFornecedor());
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                edtNOME.setText("");
                edtCampo2.setText("");
            }
        });
    }


    public void onClick(View v) {
        Bundle bundle = getIntent().getExtras();
        boolean retorno = false;
        Intent trocarDeTela = new Intent(this, MainActivity.class);

        switch (v.getId()){
            case R.id.btnLimpar:
                edtNOME.setText("");
                edtCampo2.setText("");
                break;

            case R.id.btnAlterar:
                if (0 == bundle.getByte("cpA")) {
                     cliente = new Cliente();

                     cliente.setId(listaID.get(spinner.getSelectedItemPosition()));
                     cliente.setNome(edtNOME.getText().toString());
                     cliente.setEmail(edtCampo2.getText().toString());
                     clienteController = new ClienteController(getApplicationContext());

                     retorno = clienteController.alterar(cliente);

                     if (retorno){
                         Toast.makeText(this, "Cliente alterado com sucesso", Toast.LENGTH_SHORT).show();
                     }else{
                         Toast.makeText(this, "Cliente não alterado, entre em contato com o suporte !", Toast.LENGTH_SHORT).show();
                     }
                }else if (1 == bundle.getByte("cpA")) {
                    produto = new Produto();

                    produto.setId(listaID.get(spinner.getSelectedItemPosition()));
                    produto.setNome(edtNOME.getText().toString());
                    produto.setFornecedor(edtCampo2.getText().toString());
                    produtoController = new ProdutoController(getApplicationContext());

                    retorno = produtoController.alterar(produto);
                    if (retorno){
                        Toast.makeText(this, "Produto alterado com sucesso", Toast.LENGTH_SHORT).show();
                    }else{
                        Toast.makeText(this, "Produto não alterado, entre em contato com o suporte !", Toast.LENGTH_SHORT).show();
                    }
                }
                startActivity(trocarDeTela);
                finish();

                break;

            case R.id.btnVoltar:
                startActivity(trocarDeTela);
                finish();
                break;

            case R.id.edtNOME:
                edtNOME.setText("");
                break;

            case R.id.edtCampo2:
                edtCampo2.setText("");
                break;

            default:
                break;
        }



    }


}