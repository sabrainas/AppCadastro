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

public class Editar extends AppCompatActivity {

    private EditText edtId;
    private EditText edtNomeM;
    private EditText edtCpfM;
    private EditText edtTelefoneM;
    private Button btnAlterar;
    private Button btnExcluir;
    private Button btnVoltarM;
    private AlunoDAO dao;

    private int id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_editar);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        edtId = findViewById(R.id.edtId);
        edtNomeM = findViewById(R.id.edtEditarNome);
        edtCpfM = findViewById(R.id.edtEditarCpf);
        edtTelefoneM = findViewById(R.id.edtEditarTelefone);
        btnExcluir = findViewById(R.id.btnExcluir);
        btnAlterar = findViewById(R.id.btnAlterar);
        btnVoltarM = findViewById(R.id.btnVoltar);

        edtCpfM = findViewById(R.id.edtEditarCpf);
        edtTelefoneM = findViewById(R.id.edtEditarTelefone);

        // Máscara para CPF (###.###.###-##)
        edtCpfM.addTextChangedListener(MaskEditUtil.insert("###.###.###-##", edtCpfM));

        // Máscara para Telefone ((##) #####-####)
        edtTelefoneM.addTextChangedListener(MaskEditUtil.insert("(##) #####-####", edtTelefoneM));

        Intent i = getIntent();
        String nome = i.getStringExtra("nome");
        String cpf = i.getStringExtra("cpf");
        String telefone = i.getStringExtra("telefone");
        id = i.getIntExtra("id", -1);

        edtId.setText(String.valueOf(id));
        edtNomeM.setText(nome);
        edtCpfM.setText(cpf);
        edtTelefoneM.setText(telefone);
    }

    public void alterar(View view){
        try {
            Aluno a = new Aluno();
            a.setId(id);
            a.setNome(edtNomeM.getText().toString());
            a.setCpf(edtCpfM.getText().toString());
            a.setTelefone(edtTelefoneM.getText().toString());
            dao = new AlunoDAO(this);
            dao.update(a);
            Toast.makeText(getApplicationContext(), "Aluno alterado com sucesso! ", Toast.LENGTH_LONG).show();
        } catch (Exception e){
            Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    public void excluir(View view){
        Aluno a = new Aluno();
        a.setId(Integer.parseInt(edtId.getText().toString()));
        dao = new AlunoDAO(this);
        dao.delete(a);
        Toast.makeText(getApplicationContext(), "Aluno Excluido com sucesso! ", Toast.LENGTH_LONG).show();
    }
}