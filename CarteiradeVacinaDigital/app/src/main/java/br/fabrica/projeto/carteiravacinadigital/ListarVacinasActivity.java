package br.fabrica.projeto.carteiravacinadigital;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.SearchView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

import br.fabrica.projeto.carteiravacinadigital.DAO.VacinaDAO;
import br.fabrica.projeto.carteiravacinadigital.adapters.PessoaAdapter;
import br.fabrica.projeto.carteiravacinadigital.adapters.VacinaAdapter;
import br.fabrica.projeto.carteiravacinadigital.models.Vacina;

public class ListarVacinasActivity extends AppCompatActivity {
    private RecyclerView rv;
    VacinaAdapter adaptador;
    private VacinaDAO dao;
    private List<Vacina> vacinas;
    private List<Vacina> vacinasFiltradas = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_painel_vacina);
        if(android.os.Build.VERSION.SDK_INT >= 21){
            getWindow().setStatusBarColor(this.getResources().getColor(R.color.colorPrimaryDark));
        }

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        rv = findViewById(R.id.rView);
        rv.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        rv.setLayoutManager(layoutManager);

        dao = new VacinaDAO(this);
        vacinas = dao.obterTodos();
        vacinasFiltradas.addAll(vacinas);
        //adaptador de vacina
        adaptador = new VacinaAdapter(vacinasFiltradas, this);

        rv.setAdapter(adaptador);
        registerForContextMenu(rv);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                onBackPressed();

            }
        });
        cadastrar();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.painel_pessoa, menu);

        SearchView sv = (SearchView) menu.findItem(R.id.app_bar_search).getActionView();
        sv.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {

                return false;
            }

            //verifica as letras que o usuario digitar
            @Override
            public boolean onQueryTextChange(String s) {
                procurarVacina(s);
                return false;
            }
        });
        return true;
    }

    //Buscar os valores digitados
    public void procurarVacina(String tipoVacina){
        vacinasFiltradas.clear();
        for(Vacina v : vacinas){
            if (v.getTipoVacina().toLowerCase().contains(tipoVacina.toLowerCase())){
                vacinasFiltradas.add(v);
            }

        }
        rv.invalidate();
    }

    //clicar no icone "+" chama a tela de cadastro de vacina
    public void cadastrar(){
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ListarVacinasActivity.this, CadastroVacina.class));
            }
        });
    }


    // atualizar a lista de vacinas cadastradas quando voltar

    @Override
    public void onResume(){
        super.onResume();
        vacinas = dao.obterTodos();
        vacinasFiltradas.clear();
        vacinasFiltradas.addAll(vacinas);
        rv.invalidate();
    }
}
