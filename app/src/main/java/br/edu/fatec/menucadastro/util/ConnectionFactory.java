package br.edu.fatec.menucadastro.util;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class ConnectionFactory extends SQLiteOpenHelper {
    public ConnectionFactory(@Nullable Context context,
                             @Nullable String name,
                             @Nullable SQLiteDatabase.CursorFactory factory,
                             int version){
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE aluno(ID INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "nome VARCHAR(50), cpf VARCHAR(50), telefone VARCHAR(50))");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String sql = "DROP TABLE IF EXISTS aluno";
        db.execSQL(sql);
        onCreate(db);
    }


}
