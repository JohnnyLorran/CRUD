package com.example.examplecrud.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.examplecrud.R;
import com.example.examplecrud.controller.ClienteController;
import com.example.examplecrud.controller.ProdutoController;
import com.example.examplecrud.model.Cliente;
import com.example.examplecrud.model.Produto;

public class IncluirActivity extends AppCompatActivity {

    Cliente cliente;
    ClienteController clienteController;
    Produto produto;
    ProdutoController produtoController;

    TextView txtINC;
    EditText edtNOME,edtCampo2;
    Button btnLimpar,btnCadastrar,btnVoltar;
    //Recuperamos os dados que passamos da outra activity

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_incluir);

        txtINC = findViewById(R.id.txtINC);
        edtNOME = findViewById(R.id.edtNOME);
        edtCampo2 = findViewById(R.id.edtCampo2);
        btnLimpar = findViewById(R.id.btnLimpar);
        btnCadastrar = findViewById(R.id.btnCadastrar);
        btnVoltar = findViewById(R.id.btnVoltar);


        Bundle bundle = getIntent().getExtras();

        //Vamos verificar o bundle, se é 0 ou 1
        //Apartir daqui, controlaremos nosso elementos na tela
        //Para que ela possa ser usada por Cliente ou Produto
        if (0 == bundle.getByte("cpI")){
            txtINC.setText("Do cliente preencha os dados abaixo:");
            edtNOME.setText("Digite o nome do cliente");
            edtCampo2.setText("Digite o melhor E-mail do cliente");
            btnCadastrar.setText("Cadastar Cliente");
        }else if (1 == bundle.getByte("cpI")){
            txtINC.setText("Do produto preencha os dados abaixo:");
            edtNOME.setText("Digite o nome do produto");
            edtCampo2.setText("Digite o nome do fornecedor");
            btnCadastrar.setText("Cadastar Produto");
        }
    }


    public void onClick(View v) {
        Bundle bundle = getIntent().getExtras();
        switch (v.getId()){
            case R.id.btnLimpar:
                if (0 == bundle.getByte("cpI")){
                    edtNOME.setText("Digite o nome do cliente");
                    edtCampo2.setText("Digite o melhor E-mail do cliente");
                }else if (1 == bundle.getByte("cpI")){
                    edtNOME.setText("Digite o nome do produto");
                    edtCampo2.setText("Digite o nome do fornecedor");
                }
                break;
            case R.id.btnCadastrar:
                boolean retorno = false;
                if (0 == bundle.getByte("cpI")) {
                    if ((String.valueOf(edtNOME.getText()) != "Digite o nome do cliente") || (String.valueOf(edtCampo2.getText()) != "Digite o melhor E-mail do cliente")){
                        cliente = new Cliente();

                        cliente.setNome(String.valueOf(edtNOME.getText()));
                        cliente.setEmail(String.valueOf(edtCampo2.getText()));

                        clienteController = new ClienteController(getApplicationContext());
                        retorno = clienteController.incluir(cliente);
                    }else{
                        Toast.makeText(this, "Altere os campos!",Toast.LENGTH_LONG).show();
                        break;
                    }
                    if (retorno) {
                        Toast.makeText(this, "Cliente cadastrado com sucesso", Toast.LENGTH_SHORT).show();
                    }else{
                        Toast.makeText(this, "Cliente não cadastrado, entre em contato com o suporte !", Toast.LENGTH_SHORT).show();
                    }
                }else if (1 == bundle.getByte("cpI")){
                    if ((String.valueOf(edtNOME.getText()) != "Digite o nome do produto") || (String.valueOf(edtCampo2.getText()) != "Digite o nome do fornecedor")){
                        produto = new Produto();

                        produto.setNome(String.valueOf(edtNOME.getText()));
                        produto.setFornecedor(String.valueOf(edtCampo2.getText()));

                        produtoController = new ProdutoController(getApplicationContext());
                        retorno = produtoController.incluir(produto);
                    }else{
                        Toast.makeText(this, "Altere os campos!",Toast.LENGTH_LONG).show();
                        break;
                    }
                    if (retorno) {
                        Toast.makeText(this, "Produto cadastrado com sucesso", Toast.LENGTH_SHORT).show();
                    }else{
                        Toast.makeText(this, "Produto não cadastrado, entre em contato com o suporte !", Toast.LENGTH_SHORT).show();
                    }
                }
                edtNOME.setText("");
                edtCampo2.setText("");
                edtNOME.setFocusable(true);
                break;

            case R.id.btnVoltar:
                Intent trocarDeTela = new Intent(this, MainActivity.class);
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