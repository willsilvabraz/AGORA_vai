package com.example.primeiroteste.pkgVendas;

import static com.example.primeiroteste.R.layout.item_lista_estoque;
import static com.example.primeiroteste.R.layout.item_lista_itemvenda;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.primeiroteste.R;
import com.example.primeiroteste.pkgEstoque.Produto;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class ItensVendas extends AppCompatActivity {
    private ListView lista;
    private DatabaseReference ref = FirebaseDatabase.getInstance().getReference("carrinho");
    private List<Produto> produtoList = new ArrayList<>();
    private ArrayAdapter<Produto> adapterProduto;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_itens_venda);
        lista = findViewById(R.id.itemVendasLista);
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
                        adapterProduto = new ListAdapterVendas(ItensVendas.this, item_lista_itemvenda, produtoList);
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