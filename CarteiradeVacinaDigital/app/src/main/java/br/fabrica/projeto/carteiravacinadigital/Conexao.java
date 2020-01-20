package br.fabrica.projeto.carteiravacinadigital;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class Conexao extends SQLiteOpenHelper {

    private static final String name = "teste.db";
    private static final int version = 1;

    public Conexao(Context context) {
        super(context, name, null, version);
    }

    //tabela Login
    public static final String TBL_LOGIN = "login";
    public static final String LOGIN_ID = "id";
    public static final String LOGIN_NOME = "nome";
    public static final String LOGIN_CPF = "cpf";
    public static final String LOGIN_EMAIL = "email";
    public static final String LOGIN_SENHA = "senha";

    //tabela Pessoa
    public static final String TBL_PESSOA = "pessoa";
    public static final String PESSOA_ID = "id";
    public static final String PESSOA_NOME = "nome";
    public static final String PESSOA_TIPOSANGUINEO = "tiposanguineo";
    public static final String PESSOA_SUS = "sus";
    public static final String PESSOA_PARENTESCO = "parentesco";

    //tabela Vacina
    public static final String TBL_VACINA = "vacina";
    public static final String VACINA_ID = "id";
    public static final String VACINA_TIPO = "tipo";
    public static final String VACINA_DATA = "data";
    public static final String VACINA_LOTE = "lote";
    public static final String VACINA_VALIDADE = "validade";
    public static final String VACINA_RESPONSAVEL = "responsavel";
    public static final String VACINA_UNIDADE = "unidade";

    //Estrutura das tabelas
    private static final String DATABASE_CREATE_1 = "create table " +
            TBL_LOGIN + "( " +
            LOGIN_ID + " integer primary key autoincrement, " +
            LOGIN_NOME + " text, " +
            LOGIN_CPF + " text, " +
            LOGIN_EMAIL + " text, " +
            LOGIN_SENHA + " text )";

    private static final String DATABASE_CREATE_2 = "create table " +
            TBL_PESSOA + "( " +
            PESSOA_ID + " integer primary key autoincrement, " +
            PESSOA_NOME + " text, " +
            PESSOA_TIPOSANGUINEO + " text, " +
            PESSOA_SUS + " text, " +
            PESSOA_PARENTESCO + " text )";

    private static final String DATABASE_CREATE_3 = "create table " +
            TBL_VACINA + "( " +
            VACINA_ID + " integer primary key autoincrement, " +
            VACINA_TIPO + " text, " +
            VACINA_DATA + " text, " +
            VACINA_LOTE + " text, " +
            VACINA_VALIDADE + " text, " +
            VACINA_RESPONSAVEL + " text, " +
            VACINA_UNIDADE + " text, FOREIGN KEY ( vacina_pessoa ) REFERENCES " +
            "pessoa ( id ) ON DELETE RESTRICT ON UPDATE CASCADE);";





    @Override
    public void onCreate(SQLiteDatabase database) {
        database.execSQL(DATABASE_CREATE_1);
        database.execSQL(DATABASE_CREATE_2);
        database.execSQL(DATABASE_CREATE_2);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
