package br.fabrica.projeto.carteiravacinadigital;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SearchView;

import java.util.ArrayList;
import java.util.List;

public class ListarVacinasActivity extends AppCompatActivity {

    private ListView listView;
    private VacinaDAO dao;
    private List<Vacina> vacinas;
    private List<Vacina> vacinasFiltradas = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listar_vacinas);

        listView = findViewById(R.id.listar_vacinas);
        dao = new VacinaDAO(this);
        vacinas = dao.obterTodos();
        vacinasFiltradas.addAll(vacinas);
        //adaptador de vacina
        ArrayAdapter<Vacina> adaptador = new ArrayAdapter<Vacina>(this, android.R.layout.simple_list_item_1, vacinasFiltradas);

        listView.setAdapter(adaptador);
        registerForContextMenu(listView);

    }
    //Aplicar o menu principal na lista Vacinas
    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater i = getMenuInflater();
        i.inflate(R.menu.menu_principal, menu);

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
        listView.invalidateViews();
    }

    //clicar no icone "+" chama a tela de cadastro de vacina
    public void cadastrar(MenuItem item){
        Intent it = new Intent(this, VacinaActivity.class);
        startActivity(it);
    }


    // atualizar a lista de vacinas cadastradas quando voltar

    @Override
    public void onResume(){
        super.onResume();
        vacinas = dao.obterTodos();
        vacinasFiltradas.clear();
        vacinasFiltradas.addAll(vacinas);
        listView.invalidateViews();
    }
}
