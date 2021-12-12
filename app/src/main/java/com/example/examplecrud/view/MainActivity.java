package com.example.examplecrud.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.examplecrud.R;
import com.example.examplecrud.datamodel.ClienteDataModel;
import com.example.examplecrud.datasource.AppDataBase;

public class MainActivity extends AppCompatActivity {

    AppDataBase appDataBase;

    Bundle bundle = new Bundle();

    Button btnICC, btnICP; // Botões de inclusão
    Button btnATC, btnATP; // Botões de alteração
    Button btnEXC, btnEXP; // Botões de exclusão
    Button btnLIC, btnLIP; // Botões de listagem

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //criamos o banco de dados com as tabelas
        appDataBase = new AppDataBase(getApplicationContext());


        //Fazendo a ligação dos botoes no layout com a nossa classe.

        btnICC = findViewById(R.id.btnICC);
        btnICP = findViewById(R.id.btnICP);

        btnATC = findViewById(R.id.btnATC);
        btnATP = findViewById(R.id.btnATP);

        btnEXC = findViewById(R.id.btnEXC);
        btnEXP = findViewById(R.id.btnEXP);

        btnLIC = findViewById(R.id.btnLIC);
        btnLIP = findViewById(R.id.btnLIP);

    }

    public void onClick(View v) {
        Class irPara = null;

       switch (v.getId()){
            //Clicks em relaçao ao CLIENTE
           
            // Botão para Incluir cliente
           case R.id.btnICC:
               bundle.putByte("cpI", (byte) 0);
               irPara = IncluirActivity.class;
               break;
           // Botão para Alterar cliente
           case R.id.btnATC:
               bundle.putByte("cpA", (byte) 0);
               irPara = AlterarActivity.class;
               break;
           // Botão para Excluir cliente
           case R.id.btnEXC:
               bundle.putByte("cpE", (byte) 0);
               irPara = ExcluirActivity.class;
               break;
           // Botão para listar clientes
           case R.id.btnLIC:
               bundle.putByte("cpL", (byte) 0);
               irPara = ListarActivity.class;
               break;
               
           //Clicks em relação ao PRODUTO    
               
           // Botão para Incluir Produto
           case R.id.btnICP:
               bundle.putByte("cpI", (byte) 1);
               irPara = IncluirActivity.class;
               break;
           // Botão para Alterar produto
           case R.id.btnATP:
               bundle.putByte("cpA", (byte) 1);
               irPara = AlterarActivity.class;
               break;
           // Botão para Excluir produto
           case R.id.btnEXP:
               bundle.putByte("cpE", (byte) 1);
               irPara = ExcluirActivity.class;
               break;
           // Botão para Listar produtos
           case R.id.btnLIP:
               bundle.putByte("cpL", (byte) 1);
               irPara = ListarActivity.class;
               break;
           default:
               break;
       }
        // Passamos para qual tela iremos que está dentro da variavel "irPara"
        Intent trocarDeTela = new Intent(this, irPara);
        // Aqui pegamos e guardamos cliente ou produto como Key.
        // O byte nos dirá como a Acitivity a ser aberta
        // Irá se comportar
        trocarDeTela.putExtras(bundle);
        startActivity(trocarDeTela);
        finish();

    }


}