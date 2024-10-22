package com.leo.mensagens;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    private TextToSpeech textToSpeech;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        ListView listView = findViewById(R.id.listViewMensagens);

        // Lista de mensagens
        List<Mensagem> mensagens = new ArrayList<>();
        mensagens.add(new Mensagem("João", "Olá! Como você está?"));
        mensagens.add(new Mensagem("Maria", "Lembre-se da reunião hoje às 14h."));
        mensagens.add(new Mensagem("Carlos", "Segue o relatório solicitado."));

        // Adaptador para preencher a ListView com o nome do remetente
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1);
        for (Mensagem mensagem : mensagens) {
            adapter.add(mensagem.getRemetente());
        }
        listView.setAdapter(adapter);

        // Inicialização do TextToSpeech
        textToSpeech = new TextToSpeech(this, new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if (status == TextToSpeech.SUCCESS) {
                    // Configura a linguagem para português do Brasil
                    textToSpeech.setLanguage(new Locale("pt", "BR"));
                }
            }
        });

        // Evento de clique em um item da lista
        listView.setOnItemClickListener((parent, view, position, id) -> {
            Mensagem mensagem = mensagens.get(position);
            String conteudo = mensagem.getConteudo();
            textToSpeech.speak(conteudo, TextToSpeech.QUEUE_FLUSH, null);
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (textToSpeech != null) {
            textToSpeech.stop();
            textToSpeech.shutdown();
        }
    }
}
