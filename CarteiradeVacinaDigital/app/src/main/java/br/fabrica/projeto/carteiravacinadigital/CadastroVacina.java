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

import br.fabrica.projeto.carteiravacinadigital.DAO.VacinaDAO;
import br.fabrica.projeto.carteiravacinadigital.models.Vacina;

public class CadastroVacina extends AppCompatActivity {
    private EditText tipoVacina;
    private EditText data;
    private EditText lote;
    private EditText validade;
    private EditText responsavel;
    private EditText unidade;
    private EditText vacinaPessoaId;

    private VacinaDAO dao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_vacina);

        //Variaveis recebe os valores do xml
        tipoVacina = findViewById(R.id.textTipoVacina);
        data = findViewById(R.id.textData);
        lote = findViewById(R.id.textLote);
        validade = findViewById(R.id.textValidade);
        responsavel = findViewById(R.id.textResponsavel);
        unidade = findViewById(R.id.textUnidade);
        vacinaPessoaId = findViewById(R.id.textVacinaPessoaId);


        dao = new VacinaDAO(this);
    }

    //xml adicionar no botão adicionar
    public void adicionarVacina(View view) {
        Vacina v = new Vacina();
        v.setTipoVacina(tipoVacina.getText().toString());
        v.setData(data.getText().toString());
        v.setLote(lote.getText().toString());
        v.setValidade(validade.getText().toString());
        v.setResponsavel(responsavel.getText().toString());
        v.setUnidade(unidade.getText().toString());
        v.setVacinaPessoaId(vacinaPessoaId.getText().toString());
        long id = dao.addVacina(v);

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
                startActivity(new Intent(CadastroVacina.this, ListarVacinasActivity.class));
                finish();
            }
        }, 1000);
    }

}
