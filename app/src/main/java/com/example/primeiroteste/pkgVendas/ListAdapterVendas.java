package com.example.primeiroteste.pkgVendas;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.primeiroteste.R;
import com.example.primeiroteste.pkgEstoque.Produto;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.List;

public class ListAdapterVendas extends ArrayAdapter<Produto> {
    private List<Produto> modeloFuncionario;
    private Context contexto;

    public ListAdapterVendas(@NonNull Context context, int resource, List<Produto> objects) {
        super(context, resource, objects);
        this.modeloFuncionario = objects;
        this.contexto = context;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view = convertView;

        if (view == null) {
            view = LayoutInflater.from(contexto).inflate(R.layout.item_lista_para_vendas, null);

        }
        Produto produto = modeloFuncionario.get(position);


        TextView textoNome = view.findViewById(R.id.textoNome);
        textoNome.setText(produto.getNome());

        TextView textoValorVenda = view.findViewById(R.id.textoValor);
        textoValorVenda.setText("Valor: " + String.valueOf(produto.getValor()) + " R$");

        TextView textoQuantidade = view.findViewById(R.id.textoquantidade);
        textoQuantidade.setText("Quantidade: " + String.valueOf(produto.getQuantidade()));

        Button buttonExcluir = view.findViewById(R.id.button);
        buttonExcluir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                excluirProduto(produto);

            }
        });

        return view;
    }

    private void excluirProduto(Produto produto) {
        try {
            DatabaseReference funcionarioRef = FirebaseDatabase.getInstance().getReference("Produtos").child(produto.getId());
            funcionarioRef.removeValue();

            modeloFuncionario.remove(produto);

            notifyDataSetChanged();
            Toast.makeText(getContext().getApplicationContext(), "Produto adicionado", Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            Log.d("Resultado", "Erro ao Adicionar ao Carrionho: " + e);
        }
    }
}
