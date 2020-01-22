package br.fabrica.projeto.carteiravacinadigital;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;

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

    //clicar no icone "+" chama a tela de cadastro de vacina
    public void cadastrar(MenuItem item){
        Intent it = new Intent(this, Vacina.class);
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
