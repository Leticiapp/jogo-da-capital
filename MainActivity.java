package com.example.jogodacapital;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.Normalizer;
import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ArrayList<EstadoCapital> EstadoCapitais = criaListaEstados();

        Random random = new Random();
        EstadoCapital sorteado = EstadoCapitais.get(random.nextInt(EstadoCapitais.size()));

        TextView textEstado = findViewById(R.id.textViewEstado);
        textEstado.setText(sorteado.estado);

        TextView resultado = findViewById(R.id.textViewResultado);
        resultado.setText("Total de pontos:");

        TextView pontuacao = findViewById(R.id.textViewPontuacao);
        pontuacao.setText("0");

    }



    public void compara(View view){
        EditText textCapital = findViewById(R.id.editTextCapital);
        TextView textEstado = findViewById(R.id.textViewEstado);
        TextView resultado = findViewById(R.id.textViewResultado);
        TextView pontuacao = findViewById(R.id.textViewPontuacao);

        if (textCapital.length() == 0)
            Toast.makeText(this, "Forneça a capital!", Toast.LENGTH_SHORT).show();
        else {
            String comp = textCapital.getText().toString().toUpperCase();
            comp = removerAcentos(comp);
            String Estado = textEstado.getText().toString();
            int n = Integer.parseInt(pontuacao.getText().toString());

            EstadoCapital capital = BuscaCapital(Estado);

            String cap = capital.getCapital().toUpperCase();
            cap = removerAcentos(cap);

            if (cap.equals(comp)) {
                resultado.setText("Acertou! \nCapital informada: " + capital.getCapital() + "\n\nTotal de pontos:");
                n= n + 10;
                pontuacao.setText(String.valueOf(n));
            }
            else{
                resultado.setText("Errou! \nCapital correta: " + capital.getCapital() + "\n\nTotal de pontos:");
                pontuacao.setText(String.valueOf(n));
            }

            Button btn = (Button) findViewById(R.id.button);
            btn.setEnabled(false);

            Button btn3 = (Button) findViewById(R.id.button3);
            btn3.setEnabled(true);

            textCapital.setEnabled(false);
        }

    }

    int count = 0;

    public void prox(View view){
        count++;
        EditText textCapital = findViewById(R.id.editTextCapital);
        TextView resultado = findViewById(R.id.textViewResultado);
        TextView pontuacao = findViewById(R.id.textViewPontuacao);
        int n = Integer.parseInt(pontuacao.getText().toString());

        if (count == 5) {
            Button btn = (Button) findViewById(R.id.button);
            btn.setEnabled(false);

            Button btn3 = (Button) findViewById(R.id.button3);
            btn3.setEnabled(false);

            resultado.setText("Fim de jogo!\n\nTotal de pontos:");
            pontuacao.setText(String.valueOf(n));

            textCapital.setText("");
        }
        else{
            textCapital.setText("");
            textCapital.setEnabled(true);

            ArrayList<EstadoCapital> EstadoCapitais = criaListaEstados();

            Random random = new Random();
            EstadoCapital sorteado = EstadoCapitais.get(random.nextInt(EstadoCapitais.size()));

            TextView textEstado = findViewById(R.id.textViewEstado);
            textEstado.setText(sorteado.estado);

            resultado.setText("Total de pontos:");
            pontuacao.setText(String.valueOf(n));

            Button btn = (Button) findViewById(R.id.button);
            btn.setEnabled(true);

            Button btn3 = (Button) findViewById(R.id.button3);
            btn3.setEnabled(false);
        }

    }

    public EstadoCapital BuscaCapital(String Estado){

        ArrayList<EstadoCapital> EstadoCapitais = criaListaEstados();

        for (EstadoCapital capital : EstadoCapitais) {
            if (capital.getEstado().equals(Estado)) {
                return capital;
            }
        }
        return null;
    }

    public static String removerAcentos(String str) {
        return Normalizer.normalize(str, Normalizer.Form.NFD).replaceAll("[^\\p{ASCII}]", "");
    }

    public class EstadoCapital {
        String estado, capital;

        public String getEstado() {
            return estado;
        }

        public void setEstado(String estado) {
            this.estado = estado;
        }

        public String getCapital() {
            return capital;
        }

        public void setCapital(String capital) {
            this.capital = capital;
        }

        public EstadoCapital(String estado, String capital) {
            this.estado = estado;
            this.capital = capital;
        }

    }

    public ArrayList<EstadoCapital> criaListaEstados() {

        ArrayList<EstadoCapital> EstadoCapitais = new ArrayList<>();
        EstadoCapitais.add(new EstadoCapital("Acre","Rio Branco"));
        EstadoCapitais.add(new EstadoCapital("Alagoas","Maceió"));
        EstadoCapitais.add(new EstadoCapital("Amapá","Macapá"));
        EstadoCapitais.add(new EstadoCapital("Amazonas","Manaus"));
        EstadoCapitais.add(new EstadoCapital("Ceará","Fortaleza"));
        EstadoCapitais.add(new EstadoCapital("Espírito Santo","Vitória"));
        EstadoCapitais.add(new EstadoCapital("Goiás","Goiânia"));
        EstadoCapitais.add(new EstadoCapital("Maranhão","São luís"));
        EstadoCapitais.add(new EstadoCapital("Mato Grosso","Cuiabá"));
        EstadoCapitais.add(new EstadoCapital("Mato Grosso do Sul","Campo grande"));
        EstadoCapitais.add(new EstadoCapital("Minas Gerais","Belo Horizonte"));
        EstadoCapitais.add(new EstadoCapital("Pará","Belém"));
        EstadoCapitais.add(new EstadoCapital("Paraíba","João Pessoa"));
        EstadoCapitais.add(new EstadoCapital("Paraná","Curitiba"));
        EstadoCapitais.add(new EstadoCapital("Piauí","Teresina"));
        EstadoCapitais.add(new EstadoCapital("Rio de Janeiro","Rio de Janeiro"));
        EstadoCapitais.add(new EstadoCapital("Rio Grande do Norte","Natal"));
        EstadoCapitais.add(new EstadoCapital("Rio Grande do Sul","Porto Alegre"));
        EstadoCapitais.add(new EstadoCapital("Rondônia","Porto Velho"));
        EstadoCapitais.add(new EstadoCapital("Roraima","Boa vista"));
        EstadoCapitais.add(new EstadoCapital("Santa Catarina","Florianópolis"));
        EstadoCapitais.add(new EstadoCapital("São Paulo","São Paulo"));
        EstadoCapitais.add(new EstadoCapital("Sergipe","Aracaju"));
        EstadoCapitais.add(new EstadoCapital("Tocantins","Palmas"));
        EstadoCapitais.add(new EstadoCapital("Distrito Federal","Brasília"));

        return (EstadoCapitais);
    }



}