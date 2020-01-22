package br.fabrica.projeto.carteiravacinadigital;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

public class PessoaDAO {

    private Conexao conexao;
    private SQLiteDatabase teste3;

    public PessoaDAO(Context context){
        conexao = new Conexao(context);
        teste3 = conexao.getWritableDatabase();
    }
    // devolve o id do numero que foi inserido
    public long inserir(Pessoa pessoa){
        ContentValues values = new ContentValues();
        values.put("nome", pessoa.getNome());
        values.put("tiposanguineo", pessoa.getTiposanguineo());
        values.put("sus", pessoa.getSus());
        values.put("parentesco", pessoa.getParentesco());
        return teste3.insert("pessoa", null, values);
    }
    /*
    *  //tabela Pessoa
    public static final String TBL_PESSOA = "pessoa";
    public static final String PESSOA_ID = "id";
    public static final String PESSOA_NOME = "nome";
    public static final String PESSOA_TIPOSANGUINEO = "tiposanguineo";
    public static final String PESSOA_SUS = "sus";
    public static final String PESSOA_PARENTESCO = "parentesco";*/
    public List<Pessoa> obterTodos(){
        List<Pessoa> pessoa = new ArrayList<>();
        Cursor cursor = teste3.query("pessoa", new String[]{"id","nome","tiposanguineo","sus", "parentesco"},
                null, null, null, null, null);

        while(cursor.moveToNext()){

            Pessoa p = new Pessoa();
            p.setId(cursor.getInt(0));
            p.setNome(cursor.getString(1));
            p.setTiposanguineo(cursor.getString(2));
            p.setSus(cursor.getString(3));
            p.setParentesco(cursor.getString(4));

            pessoa.add(p);
        }
        return pessoa;
    }
}
