package com.joyce.aprendaingls.Fragments;

import android.media.MediaPlayer;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import com.joyce.aprendaingls.R;


public class NumerosFragment extends Fragment implements View.OnClickListener {

    ImageButton um, dois, tres, quatro, cinco, seis;
    MediaPlayer mediaPlayer;

    public NumerosFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_numeros, container, false);

        um = view.findViewById(R.id.btn_um);
        dois = view.findViewById(R.id.btn_dois);
        tres = view.findViewById(R.id.btn_tres);
        quatro = view.findViewById(R.id.btn_quatro);
        cinco = view.findViewById(R.id.btn_cinco);
        seis = view.findViewById(R.id.btn_seis);

        um.setOnClickListener(this);
        dois.setOnClickListener(this);
        tres.setOnClickListener(this);
        quatro.setOnClickListener(this);
        cinco.setOnClickListener(this);
        seis.setOnClickListener(this);

        return view;
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){  //recupera o id do item que foi clicado
            case R.id.btn_um:
                mediaPlayer = MediaPlayer.create(getContext(), R.raw.one);
                tocarSom();
                break;
            case R.id.btn_dois:
                mediaPlayer = MediaPlayer.create(getContext(), R.raw.two);
                tocarSom();
                break;
            case R.id.btn_tres:
                mediaPlayer = MediaPlayer.create(getContext(), R.raw.three);
                tocarSom();
                break;
            case R.id.btn_quatro:
                mediaPlayer = MediaPlayer.create(getContext(), R.raw.four);
                tocarSom();
                break;
            case R.id.btn_cinco:
                mediaPlayer = MediaPlayer.create(getContext(), R.raw.five);
                tocarSom();
                break;
            case R.id.btn_seis:
                mediaPlayer = MediaPlayer.create(getContext(), R.raw.six);
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