package com.joyce.atmconsultoria.ui.sobre;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.joyce.atmconsultoria.R;

import mehdi.sakout.aboutpage.AboutPage;
import mehdi.sakout.aboutpage.Element;

public class SobreFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
       // return inflater.inflate(R.layout.fragment_sobre, container, false);

        String description = "A ATM Consultoria tem como missão apoiair" +
                "organizações que desejam alcançar o sucesso através da excelência em gestão " +
                "e de da busca pela qualidade";

        Element version = new Element();
        version.setTitle("Versão 1.0"); //Definindo versao

        return new AboutPage(getActivity())
                .setImage(R.drawable.logo)
                .setDescription(description)
                .addGroup("Entre em contato")
                .addEmail("atendimento@atmconsultoria.com", "Envie um email")
                .addGroup("Redes Sociais")
                .addFacebook("atm.facebook.com", "Facebook")
                .addInstagram("@atm_consult", "Instagram")
                .addItem(version)
                .create();

    }
}