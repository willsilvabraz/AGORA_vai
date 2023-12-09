package com.example.primeiroteste;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.primeiroteste.pkgEstoque.Estoque;
import com.example.primeiroteste.pkgFuncionarios.RH;
import com.example.primeiroteste.pkgVendas.Venda;

public class MainActivity extends AppCompatActivity {

    private Button bt_pdv, bt_estoque, bt_rh;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        inicializarComponentes();
        Log.d("resultado", "inicializou");

        bt_estoque.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Estoque.class);
                startActivity(intent);
            }
        });

        bt_rh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, RH.class);
                startActivity(intent);
            }
        });

        bt_pdv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Venda.class);
                startActivity(intent);
            }
        });
    }

    public void inicializarComponentes(){
        Log.d("resultado", "inicializou");
        bt_pdv = (Button) findViewById(R.id.bt_pdv);
        bt_estoque = (Button) findViewById(R.id.bt_estoque);
        bt_rh = (Button) findViewById(R.id.bt_rh);
    }
}