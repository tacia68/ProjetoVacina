package br.fabrica.projeto.carteiravacinadigital;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import android.view.View;



public class LoginActivity extends AppCompatActivity {


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }

    public void painel(View view){
        Intent intent = new Intent(LoginActivity.this, ListarPessoasActitivity.class);
        startActivity(intent);
    }
    public void cadastro(View view){
        Intent intent = new Intent(LoginActivity.this, CadastroPrincipal.class);
        startActivity(intent);
    }
}
