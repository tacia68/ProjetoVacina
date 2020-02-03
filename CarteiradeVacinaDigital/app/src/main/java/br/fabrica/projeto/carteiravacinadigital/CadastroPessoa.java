package br.fabrica.projeto.carteiravacinadigital;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;

import br.fabrica.projeto.carteiravacinadigital.DAO.PessoaDAO;
import br.fabrica.projeto.carteiravacinadigital.models.Pessoa;

public class CadastroPessoa extends AppCompatActivity {
    private EditText nome;
    private EditText tiposanguineo;
    private EditText sus;
    private EditText parentesco;
    private PessoaDAO dao;
    private Pessoa pessoa = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_pessoa);

        //Variaveis recebe os valores do xml
        nome = findViewById(R.id.textNome);
        tiposanguineo = findViewById(R.id.textTipo);
        sus = findViewById(R.id.textSus);
        parentesco = findViewById(R.id.textParentesco);
        dao = new PessoaDAO(this);

        // Se já existe uma pessoa então mostra esse aluno, função para poder atualizar depois
        Intent it = getIntent();
        if (it.hasExtra("pessoa")){
            pessoa = (Pessoa) it.getSerializableExtra("pessoa");
            nome.setText((pessoa.getNome()));
            tiposanguineo.setText((pessoa.getTiposanguineo()));
            sus.setText((pessoa.getSus()));
            parentesco.setText((pessoa.getParentesco()));

        }

    }

    //xml adicionar no botão adicionar
    public void adicionar(View view) {
        //Se não veio nenhum aluno da listagem, Então é cadastro
        if (pessoa == null) {
            pessoa = new Pessoa();
            pessoa.setNome(nome.getText().toString());
            pessoa.setTiposanguineo(tiposanguineo.getText().toString());
            pessoa.setSus(sus.getText().toString());
            pessoa.setParentesco(parentesco.getText().toString());
            long id = dao.inserir(pessoa);
            Snackbar.make(view,
                    "Cadastro realizado com Sucesso" + id, Snackbar.LENGTH_LONG)
                    .setTextColor(Color.WHITE)
                    .setActionTextColor(Color.WHITE)
                    .setBackgroundTint(Color.parseColor("#00B2C9"))
                    .setAction("Action", null).show();

            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    //Após os 1 segundo inicia uma nova atividade
                    startActivity(new Intent(CadastroPessoa.this, ListarPessoasActitivity.class));
                    finish();
                }
            }, 1000);
        }

           // Toast.makeText(this, "Pessoa inserido com id : " + id, Toast.LENGTH_SHORT).show();
// Se já está criado
         else{
            pessoa.setNome(nome.getText().toString());
            pessoa.setTiposanguineo(tiposanguineo.getText().toString());
            pessoa.setSus(sus.getText().toString());
            pessoa.setParentesco(parentesco.getText().toString());
            dao.atualizar(pessoa);

            Snackbar.make(view,
                    "Pessoa atualizada", Snackbar.LENGTH_LONG)
                    .setTextColor(Color.WHITE)
                    .setActionTextColor(Color.WHITE)
                    .setBackgroundTint(Color.parseColor("#00B2C9"))
                    .setAction("Action", null).show();
            //Toast.makeText(this, "Pessoa atualizada", Toast.LENGTH_SHORT).show();
        }


    }

}
