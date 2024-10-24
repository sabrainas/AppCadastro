package br.edu.fatec.menucadastro.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import br.edu.fatec.menucadastro.model.Aluno;
import br.edu.fatec.menucadastro.util.ConnectionFactory;

public class AlunoDAO {
    private ConnectionFactory conexao;
    private SQLiteDatabase banco;

    public AlunoDAO(Context context){
        conexao = new ConnectionFactory(context, "banco.db", null, 1);
        banco = conexao.getWritableDatabase();
    }

    //metodo create
    public long create(Aluno aluno){
        ContentValues values = new ContentValues();
        values.put("nome", aluno.getNome());
        values.put("cpf", aluno.getCpf());
        values.put("telefone", aluno.getTelefone());
        return(banco.insert("aluno", null, values));
    }

    //metodo read
    public Aluno read(Integer id) {
        String args[] = {String.valueOf(id)};
        Cursor cursor = banco.query("aluno", new String[]{"id", "nome", "cpf", "telefone"},
                "id=?", args, null, null, null);
        cursor.moveToFirst();
        Aluno aluno = new Aluno();
        if(cursor.getCount() > 0){
            aluno.setId(cursor.getInt(0));
            aluno.setNome((cursor.getString(1)));
            aluno.setCpf((cursor.getString(2)));
            aluno.setTelefone((cursor.getString(3)));
        }
        return aluno;
    }

    //metodo update
    public void update(Aluno aluno){
        ContentValues values = new ContentValues();
        values.put("nome", aluno.getNome());
        values.put("cpf", aluno.getCpf());
        values.put("telefone", aluno.getTelefone());
        String args[] = {aluno.getId().toString()};
        banco.update("aluno", values,"id=?",args);
    }

    //metodo delete
    public void delete(Aluno aluno){
        String args[] = {aluno.getId().toString()};
        banco.delete("aluno","id=?",args);
    }

    public List<Aluno> obterTodos() {
        List<Aluno> alunos = new ArrayList<>();
        Cursor cursor = banco.query("aluno", new String[]{"id", "nome", "cpf",
                        "telefone"},
                null, null, null, null, null);
        while (cursor.moveToNext()) {
            Aluno a = new Aluno();
            a.setId(cursor.getInt(0));
            a.setNome((cursor.getString(1)));
            a.setCpf((cursor.getString(2)));
            a.setTelefone((cursor.getString(3)));
            alunos.add(a);
        }
        cursor.close();
        return alunos;
    }
}
