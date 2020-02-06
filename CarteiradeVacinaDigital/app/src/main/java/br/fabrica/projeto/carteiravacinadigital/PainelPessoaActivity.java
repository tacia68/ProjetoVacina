package br.fabrica.projeto.carteiravacinadigital;

import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.Menu;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;

import br.fabrica.projeto.carteiravacinadigital.adapters.AdapterPessoa;
import br.fabrica.projeto.carteiravacinadigital.models.Pessoa;

public class PainelPessoaActivity extends AppCompatActivity {
    private ArrayList<Pessoa> pessoa = new ArrayList<>();
    private RecyclerView rv;
    AdapterPessoa adapterPessoa;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_painel_pessoa);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        rv = findViewById(R.id.rView);
        rv.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        rv.setLayoutManager(layoutManager);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        for (int i = 0; i<pessoa.size(); i++){
            //pessoa.get(new Pessoa(nome,cpf,email,senha));

        }

        adapterPessoa = new AdapterPessoa(pessoa, this);
        rv.setAdapter(adapterPessoa);

}

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.painel_pessoa, menu);
        return true;
    }


}
