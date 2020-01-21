package br.fabrica.projeto.carteiravacinadigital;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {


    private EditText nome;
    private EditText tiposanguineo;
    private EditText sus;
    private EditText parentesco;
    private PessoaDAO dao;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Variaveis recebe os valores do xml
        nome = findViewById(R.id.textNome);
        tiposanguineo = findViewById(R.id.textTipo);
        sus = findViewById(R.id.textSus);
        parentesco = findViewById(R.id.textParentesco);
        dao = new PessoaDAO(this);
    }


    //xml adicionar no botão adicionar
    public void adicionar(View view) {
        Pessoa p = new Pessoa();
        p.setNome(nome.getText().toString());
        p.setTiposanguineo(tiposanguineo.getText().toString());
        p.setSus(sus.getText().toString());
        p.setParentesco(parentesco.getText().toString());
        long id = dao.inserir(p);
        Toast.makeText(this, "Pessoa inserido com id : " + id, Toast.LENGTH_SHORT).show();
    }
}
