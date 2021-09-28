package com.joyce.aprendaingles2.Fragments.Fragments;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import androidx.fragment.app.Fragment;

import com.joyce.aprendaingles2.R;


public class BichosFragment extends Fragment implements View.OnClickListener {

    ImageButton cao, gato, ovelha, leao, vaca, macaco;
    MediaPlayer mediaPlayer;

    public BichosFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

       View view = inflater.inflate(R.layout.fragment_bichos, container, false);

       cao = view.findViewById(R.id.btn_dog);
       gato = view.findViewById(R.id.btn_gato);
       ovelha = view.findViewById(R.id.btn_ovelha);
       leao = view.findViewById(R.id.btn_leao);
       vaca = view.findViewById(R.id.btn_vaca);
       macaco = view.findViewById(R.id.btn_macaco);

       //quando temos muitos botoes, como é nosso caso, os eventos de clique é melhor fazer assim:
        // * Faça classe implementar View.onClickListener
        // * Ela vai fazer vc implementar um metodo onClick global, que vc vai conseguir diferenicr por id e dar o tratamento correto para cada um deles

        cao.setOnClickListener(this);       //esse this indica que a propria classe vai fazer o tratamento do evento de click
        gato.setOnClickListener(this);
        ovelha.setOnClickListener(this);
        leao.setOnClickListener(this);
        vaca.setOnClickListener(this);
        macaco.setOnClickListener(this);

       return view;
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){  //recupera o id do item que foi clicado
            case R.id.btn_dog:
                mediaPlayer = MediaPlayer.create(getContext(), R.raw.dog);
                tocarSom();
                break;
            case R.id.btn_gato:
                mediaPlayer = MediaPlayer.create(getContext(), R.raw.cat);
                tocarSom();
                break;
            case R.id.btn_ovelha:
                mediaPlayer = MediaPlayer.create(getContext(), R.raw.sheep);
                tocarSom();
                break;
            case R.id.btn_leao:
                mediaPlayer = MediaPlayer.create(getContext(), R.raw.lion);
                tocarSom();
                break;
            case R.id.btn_vaca:
                mediaPlayer = MediaPlayer.create(getContext(), R.raw.cow);
                tocarSom();
                break;
            case R.id.btn_macaco:
                mediaPlayer = MediaPlayer.create(getContext(), R.raw.monkey);
                tocarSom();
                break;
        }

    }

    public void tocarSom(){
        if(mediaPlayer != null){
            mediaPlayer.start();

            mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mp) {
                    mediaPlayer.release();
                }
            });
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if(mediaPlayer != null){
            mediaPlayer.release();
            mediaPlayer = null;
        }
    }
}