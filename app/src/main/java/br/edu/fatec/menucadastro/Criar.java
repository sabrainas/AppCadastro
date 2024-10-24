package br.edu.fatec.menucadastro;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import br.edu.fatec.menucadastro.dao.AlunoDAO;
import br.edu.fatec.menucadastro.model.Aluno;
import br.edu.fatec.menucadastro.util.MaskEditUtil;

public class Criar extends AppCompatActivity {

    private EditText edtNome;
    private EditText edtCpf;
    private EditText edtTelefone;
    private Button btnSalvar;
    private Button btnVoltar;
    private AlunoDAO dao;
    private Aluno aluno;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_criar);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        edtNome = findViewById(R.id.edtNome);
        edtCpf = findViewById(R.id.edtCpf);
        edtTelefone = findViewById(R.id.edtTelefone);
        btnSalvar = findViewById(R.id.btnSalvar);
        btnVoltar = findViewById(R.id.btnVoltar);

        // Máscara para CPF (###.###.###-##)
        edtCpf.addTextChangedListener(MaskEditUtil.insert("###.###.###-##", edtCpf));

        // Máscara para Telefone ((##) #####-####)
        edtTelefone.addTextChangedListener(MaskEditUtil.insert("(##) #####-####", edtTelefone));

    }

    public void salvar(View view){
        //pegar os dados da tela
        aluno = new Aluno();
        aluno.setNome(edtNome.getText().toString());
        aluno.setCpf(edtCpf.getText().toString());
        aluno.setTelefone(edtTelefone.getText().toString());

        //abrir bd
        dao = new AlunoDAO(this);

        //salvar
        long id = dao.create(aluno);
        Toast.makeText(getApplicationContext(), "Aluno inserido com o ID "+id, Toast.LENGTH_LONG).show();
    }

    public void voltar(View view){
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(intent);
    }
}