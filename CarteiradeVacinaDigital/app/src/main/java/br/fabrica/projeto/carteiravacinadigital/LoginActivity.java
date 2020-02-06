package br.fabrica.projeto.carteiravacinadigital;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    Button btLogin;
    EditText cpf,senha;

    Conexao con = new Conexao(this);
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        btLogin = findViewById(R.id.btLogin);
        cpf = findViewById(R.id.textCpf);
        senha  = findViewById(R.id.textSenha);


        btLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {

                String c = cpf.getText().toString();
                String s = senha.getText().toString();

                if(TextUtils.isEmpty(cpf.getText().toString()))
                {
                    cpf.setError("*");
                    cpf.requestFocus();
                }
                if(TextUtils.isEmpty(senha.getText().toString()))
                {
                    senha.setError("*");
                    senha.requestFocus();
                }
                /*if(c.equals(""))
                {
                    Toast.makeText(LoginActivity.this,"Cpf nao inserido ou invalido, tente novamente!",Toast.LENGTH_SHORT).show();
                }
                else if(s.equals(""))
                {
                    Toast.makeText(LoginActivity.this,"Senha nao inserida ou invalida, tente novamente!",Toast.LENGTH_SHORT).show();
                }*/
                else
                {
                    //Tudo OK
                    String res = con.validarLogin(c, s);
                    try
                    {
                        if(res.equals("OK"))
                        {
                            Intent intent = new Intent(LoginActivity.this, PainelPessoaActivity.class);
                            startActivity(intent);
                        }
                    }
                    catch (Exception e)
                    {
                        Toast.makeText(LoginActivity.this,"Login incorreto, tente novamente!",Toast.LENGTH_SHORT).show();
                    }

                    /*if(res.equals("OK"))
                    {
                        Intent i = new Intent(LoginActivity.this, PainelPessoaActivity.class);
                        startActivity(i);
                    }
                    else
                    {
                        Toast.makeText(LoginActivity.this,"Login incorreto, tente novamente!",Toast.LENGTH_SHORT).show();
                    }*/
                }
                /*Intent i = new Intent(LoginActivity.this, PainelPessoaActivity.class);
                startActivity(i);*/
            }
        });

    }

    /*public void painel(View view){
        Intent intent = new Intent(LoginActivity.this, PainelPessoaActivity.class);
        startActivity(intent);
    }*/
    public void cadastro(View view){
        Intent intent = new Intent(LoginActivity.this, CadastroPrincipal.class);
        startActivity(intent);
    }
}
