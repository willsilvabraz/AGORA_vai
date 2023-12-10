package com.example.primeiroteste.pkgVendas;

import static com.example.primeiroteste.R.layout.activity_itens_venda;
import static com.example.primeiroteste.R.layout.activity_pdv;
import static com.example.primeiroteste.R.layout.item_lista_estoque;
import static com.example.primeiroteste.R.layout.item_lista_itemvenda;
import static com.example.primeiroteste.R.layout.item_lista_para_vendas;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.example.primeiroteste.R;
import com.example.primeiroteste.pkgEstoque.ListAdapter;
import com.example.primeiroteste.pkgEstoque.Listar;
import com.example.primeiroteste.pkgEstoque.Produto;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;
import java.util.zip.Inflater;

public class Venda extends AppCompatActivity {
    private ListView lista ;
    private List<Produto> produtoList = new ArrayList<>();
    private ArrayAdapter<Produto> adapterProduto;
    private EditText inputPesquisa ;
    private TextView valorcarrinho;
    private Carrinho carrinho = new Carrinho();
    private Button verCarrinho;
    private DatabaseReference ref = FirebaseDatabase.getInstance().getReference("Produtos");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_venda);

        inputPesquisa = findViewById(R.id.vendainputPesquisa);
        lista = findViewById(R.id.outputVendaLista);
        valorcarrinho = findViewById(R.id.outputValorCarrinho);
        verCarrinho = (Button) findViewById(R.id.verCarrinho);

        verCarrinho.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Venda.this,ItensVendas.class);
                startActivity(intent);

            }
        });
        listarProdutos();
    }

    public void listarProdutos(){
        Log.d("resultado", "Chegoou em listar/ ativouListar ");
        ref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Log.d("resultado", "encontrou algo");
                produtoList.clear();
                try{
                    for(DataSnapshot snapshotObj: snapshot.getChildren()){
                        Produto produto = snapshotObj.getValue(Produto.class);

                        produtoList.add(produto);
                        adapterProduto = new ListAdapterVendas(Venda.this, item_lista_para_vendas, produtoList);
                        lista.setAdapter(adapterProduto);
                    }
                }catch (Exception e){
                    Log.d("Resultado", "resultado " + e);
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.d("Resultado", "resultado" + error);
            }
        });
    }
}