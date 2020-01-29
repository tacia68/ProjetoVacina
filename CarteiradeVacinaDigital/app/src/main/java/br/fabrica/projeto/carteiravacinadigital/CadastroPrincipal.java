package br.fabrica.projeto.carteiravacinadigital;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.android.material.snackbar.Snackbar;

public class CadastroPrincipal extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_principal);
        Button btCadastrar = findViewById(R.id.btCadastrar);
        
        btCadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Snackbar.make(view,
                        "Cadastro realizado com Sucesso", Snackbar.LENGTH_LONG)
                        .setTextColor(Color.WHITE)
                        .setActionTextColor(Color.WHITE)
                        .setBackgroundTint(Color.parseColor("#00B2C9"))
                        .setAction("Action", null).show();

                Intent intent = new Intent(CadastroPrincipal.this, LoginActivity.class);
                startActivity(intent);
            }
        });
     
        }
    }
