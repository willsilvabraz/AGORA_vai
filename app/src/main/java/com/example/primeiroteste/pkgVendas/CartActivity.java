package com.example.primeiroteste.pkgVendas;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.primeiroteste.R;

public class CartActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        Toolbar toolbar = findViewById(R.id.toolbar_cart);
        setSupportActionBar(toolbar);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar_menu_cart, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.menuTb) {
            Toast.makeText(this, "Menu", Toast.LENGTH_SHORT).show();

        }
        if (id == R.id.procurarMenuTb) {
            Toast.makeText(this, "Procurar", Toast.LENGTH_SHORT).show();
        }
        if (id == R.id.iniciotb) {
            Toast.makeText(this, "Inicio", Toast.LENGTH_SHORT).show();
        }
        return true;
    }
}