package br.edu.fatec.menucadastro;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.List;

import br.edu.fatec.menucadastro.dao.AlunoDAO;
import br.edu.fatec.menucadastro.model.Aluno;
import br.edu.fatec.menucadastro.util.ConnectionFactory;

public class MainActivity extends AppCompatActivity {

    private List<Aluno> alunos;
    private ListView lvDados;
    private ConnectionFactory connectionFactory;
    private AlunoDAO dao;
    private Aluno aluno;
    private ArrayAdapter<Aluno> adaptador;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        //cf = new ConnectionFactory(this);
        dao = new AlunoDAO(this);
        lvDados = findViewById(R.id.lvDados);

        alunos = dao.obterTodos();

        //Adaptador do ListView
        adaptador = new ArrayAdapter<Aluno>(this, R.layout.list_item, R.id.tvAlunos, alunos);

        //Relacionando o adaptador ao componente ListView
        lvDados.setAdapter(adaptador);

        //implementacao do evento de click do item
        lvDados.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Aluno selecionado = (Aluno) parent.getItemAtPosition(position);
                // recebe o nome da lista
                Intent intent = new Intent(getApplicationContext(), Editar.class);
                intent.putExtra("id", selecionado.getId());
                intent.putExtra("nome", selecionado.getNome());
                intent.putExtra("cpf", selecionado.getCpf());
                intent.putExtra("telefone", selecionado.getTelefone());
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onResume(){
        super.onResume();
        atualizarLista();
    }
    private void atualizarLista(){
        List<Aluno> alunos = dao.obterTodos();
        adaptador.clear();
        adaptador.addAll(alunos);
        adaptador.notifyDataSetChanged();
    }

    //abre o menu
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater i = getMenuInflater();
        i.inflate(R.menu.menu_principal,menu);

        return true;
    }

    public void sair(MenuItem item){
        finishAffinity();
    }

    public void novo(MenuItem item){
        Intent intent = new Intent(getApplicationContext(), Criar.class);
        startActivity(intent);
    }
}