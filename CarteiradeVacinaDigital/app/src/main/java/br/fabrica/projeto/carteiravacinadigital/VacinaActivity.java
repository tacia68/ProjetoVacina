package br.fabrica.projeto.carteiravacinadigital;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class VacinaActivity extends AppCompatActivity {

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
        setContentView(R.layout.activity_vacina);
      //  textTipoVacina, textData

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
        Toast.makeText(this, "Vacina inserido com id : " + id, Toast.LENGTH_SHORT).show();
    }
}
