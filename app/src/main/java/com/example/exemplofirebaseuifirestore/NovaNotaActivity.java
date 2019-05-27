package com.example.exemplofirebaseuifirestore;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.Toast;

import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;

public class NovaNotaActivity extends AppCompatActivity {
    private EditText edtTitulo, edtDescricao;
    private NumberPicker npPrioridade;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nova_nota);

        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_close);
        setTitle("Adicionar nota");

        edtTitulo = findViewById(R.id.edtTitulo);
        edtDescricao = findViewById(R.id.edtDescricao);
        npPrioridade = findViewById(R.id.npPrioridade);

        npPrioridade.setMinValue(1);
        npPrioridade.setMaxValue(10);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu_nova_nota, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.itSalvaNota:
                salvarNota();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void salvarNota() {
        String titulo = edtTitulo.getText().toString();
        String descricao = edtDescricao.getText().toString();
        int prioridade = npPrioridade.getValue();

        if (titulo.trim().isEmpty() || descricao.trim().isEmpty()) {
            Toast.makeText(getApplicationContext(), "Favor inserir título e  descrição!", Toast.LENGTH_SHORT).show();
            return;
        }

        CollectionReference notasCollectionReference = FirebaseFirestore.getInstance().collection("Notas");
        notasCollectionReference.add(new Nota(titulo, descricao, prioridade));
        Toast.makeText(getApplicationContext(), "Nota adicionada!", Toast.LENGTH_SHORT).show();
        finish();
    }

}
