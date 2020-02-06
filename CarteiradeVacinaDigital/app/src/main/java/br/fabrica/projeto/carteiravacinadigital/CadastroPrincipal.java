package br.fabrica.projeto.carteiravacinadigital;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class CadastroPrincipal extends AppCompatActivity
{
    EditText txtNome, txtCpf, txtEmail, txtSenha, txtConfSenha;
    Button btCadastrar;

    Conexao con = new Conexao(this);
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_principal);

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
                String nome =txtNome.getText().toString();
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

                    Toast.makeText(CadastroPrincipal.this, "Pessoa cadastrada com sucesso!!!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
