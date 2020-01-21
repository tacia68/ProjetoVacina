package br.fabrica.projeto.carteiravacinadigital;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

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
}
