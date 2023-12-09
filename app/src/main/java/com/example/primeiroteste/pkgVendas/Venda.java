package com.example.primeiroteste.pkgVendas;

import static com.example.primeiroteste.R.layout.item_lista_estoque;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

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

public class Venda extends AppCompatActivity {
    private ListView lista ;
    private List<Produto> produtoList = new ArrayList<>();
    private ArrayAdapter<Produto> adapterProduto;
    private EditText inputPesquisa ;

    private DatabaseReference ref = FirebaseDatabase.getInstance().getReference("Produtos");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_venda);
        inputPesquisa = findViewById(R.id.vendainputPesquisa);
        lista = findViewById(R.id.outputVendaLista);
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
                    Log.d("etapa", "1");
                    for(DataSnapshot snapshotObj: snapshot.getChildren()){
                    Log.d("etapa", "2");
                        Log.d("Resultado", "resultado" + snapshotObj.getKey());
                    Log.d("etapa", "3");
                        Produto produto = snapshotObj.getValue(Produto.class);
                    Log.d("etapa", "4");

                            produtoList.add(produto);
                    Log.d("etapa", "5");
                            adapterProduto = new ListAdapterVendas(Venda.this, item_lista_estoque, produtoList);
                    Log.d("etapa", "6");
                            lista.setAdapter(adapterProduto);
                    Log.d("etapa", "7");
                            Log.d("Resultado", "ID encontrado" + produto.getId());
                            Log.d("Resultado", "nome encontrado" + produto.getNome());
                    Log.d("etapa", "8");

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