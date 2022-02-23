package com.example.project4hacktiv;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.annotation.IdRes;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import java.text.NumberFormat;
import java.util.Locale;

public class DetailTicket extends AppCompatActivity {


    TextView idbookingT, noKursiT,namauserT,tanggalT, waktuAsalT, waktuTujuanT, hargaT, namaBusT, namaAsalT, namaTujaunT;
    String idbooking,namauser,namaAsal, namaTujuan,harga,waktuAsal,idBus,waktuTujuan,namaBus,Tanggal,noKursi;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_ticket);

        getPrevIntent();
        Locale localeID = new Locale( "in" , "ID" );
        NumberFormat formatRupiah = NumberFormat. getCurrencyInstance (localeID);

        setText(idbooking,idbookingT, R.id.bkid3);
        setText(namaBus,namaBusT, R.id.namaBusTicket3);
        setText(namauser,namauserT, R.id.namaUser);
        setText(namaAsal,namaAsalT, R.id.namaAsalTV3);
        setText(namaTujuan,namaTujaunT, R.id.namaTujuanTV3);
        setText(waktuAsal,waktuAsalT, R.id.waktuAsal3);
        setText(waktuTujuan,waktuTujuanT, R.id.waktuTujuanTV3);
        setText(Tanggal,tanggalT, R.id.tanggalTV3);
        setText(formatRupiah.format((double) Integer.valueOf(harga)),hargaT, R.id.hargaTV3);
        setText(noKursi,noKursiT, R.id.idkursiTV3);


    }



    private void setText(String intent, @NonNull TextView textView, @IdRes int intt){
        textView = findViewById(intt);
        textView.setText(intent);
    }

    private void getPrevIntent(){
        Intent intent = getIntent();
        idbooking = intent.getStringExtra("idbook");
        namauser = intent.getStringExtra("namaInt");
        namaAsal = intent.getStringExtra("namaAsalInt");
        namaTujuan = intent.getStringExtra("namaTujuanInt");
        Tanggal = intent.getStringExtra("tanggalInt");
        harga = intent.getStringExtra("hargaInt");
        waktuAsal = intent.getStringExtra("waktuAsalInt");
        waktuTujuan = intent.getStringExtra("waktuTujuanInt");
        namaBus = intent.getStringExtra("namaBusInt");
        noKursi = intent.getStringExtra("kursiInt");

    }


}