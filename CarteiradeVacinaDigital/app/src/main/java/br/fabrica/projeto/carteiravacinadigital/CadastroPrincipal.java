package br.fabrica.projeto.carteiravacinadigital;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;

import br.fabrica.projeto.carteiravacinadigital.models.Administrador;

public class CadastroPrincipal extends AppCompatActivity {
    EditText txtNome, txtCpf, txtEmail, txtSenha, txtConfSenha;
    Button btCadastrar;

    Conexao con = new Conexao(this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_principal);

        btCadastrar = findViewById(R.id.btCadastrar);
        txtNome = findViewById(R.id.txtNome);
        txtCpf = findViewById(R.id.txtCpf);
        txtEmail = findViewById(R.id.txtEmail);
        txtSenha = findViewById(R.id.txtSenhaCadastro);
        txtConfSenha = findViewById(R.id.txtConfSenha);
        btCadastrar = findViewById(R.id.btCadastrar);

        //Outro salvar
        btCadastrar.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                String nome = txtNome.getText().toString();
                String cpf = txtCpf.getText().toString();
                String email = txtEmail.getText().toString();
                String senha = txtSenha.getText().toString();

                if(nome.isEmpty())
                {
                    txtNome.setError("Este campo é obrigatorio!");
                }
                else
                {
                    con.addAdministrador(new Administrador(nome,cpf,email,senha));

                    Snackbar.make(v,
                            "Cadastro realizado com Sucesso", Snackbar.LENGTH_LONG)
                            .setTextColor(Color.WHITE)
                            .setActionTextColor(Color.WHITE)
                            .setBackgroundTint(Color.parseColor("#00B2C9"))
                            .setAction("Action", null).show();

                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            //Após os 1 segundo inicia uma nova atividade
                            Intent intent = new Intent(CadastroPrincipal.this, LoginActivity.class);
                            startActivity(intent);
                            finish();
                        }
                    }, 1000);


                }
            }
        });
    }
        


    }
