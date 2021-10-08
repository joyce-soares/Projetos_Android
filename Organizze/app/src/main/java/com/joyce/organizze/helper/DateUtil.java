package com.joyce.organizze.helper;

import java.text.SimpleDateFormat;

public class DateUtil {

    public static String dataAtual(){
        long date = System.currentTimeMillis();
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        return sdf.format(date);
    }

    public static String mesAnoDataEscolhida(String data){
        String retornaData[] = data.split("/");
        String dia = retornaData[1];
        String ano = retornaData[2];
        return   dia + ano;
    }
}
