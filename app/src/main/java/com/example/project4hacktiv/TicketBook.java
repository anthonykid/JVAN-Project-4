package com.example.project4hacktiv;

import static java.lang.String.valueOf;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.IdRes;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.project4hacktiv.API.ApiClient;
import com.example.project4hacktiv.API.ApiInterface;
import com.example.project4hacktiv.Model.seat.DataSeatItem;
import com.example.project4hacktiv.Model.seat.ResponseGetSeat;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TicketBook extends AppCompatActivity {
    Context mContext;

    ArrayList<String> defaultSeat = new ArrayList<String>();
    List<DataSeatItem> dataSeatItems;
    ArrayAdapter<String> adapterDropdown;
    AutoCompleteTextView pilihKursi;

    TextView idUserT, idBusT, tanggalT, waktuAsalT, waktuTujuanT, hargaT, namaBusT, namaAsalT, namaTujaunT;
    String idUser,namaAsal, namaTujuan,harga,waktuAsal,idBus,waktuTujuan,namaBus,Tanggal,noKursi;
    Button insert;
    ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ticket_book);
        mContext = this;

        getPrevIntent();
        getBookedSeat();

        setText(namaBus,namaBusT, R.id.namaBusTicket);
        setText(idUser,idUserT, R.id.idUserTV2);
        setText(idBus,idBusT, R.id.idBusTV2);
        setText(namaAsal,namaAsalT, R.id.namaAsalTV2);
        setText(namaTujuan,namaTujaunT, R.id.namaTujuanTV2);
        setText(waktuAsal,waktuAsalT, R.id.waktuAsal2);
        setText(waktuTujuan,waktuTujuanT, R.id.waktuTujuanTV2);
        setText(Tanggal,tanggalT, R.id.tanggalTV2);
        setText(harga,hargaT, R.id.hargaTV2);
        pilihKursi = findViewById(R.id.pilihKursi);
        insert = findViewById(R.id.pesanTiket);

        pilihKursi.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                noKursi = valueOf(adapterView.getItemAtPosition(i));
            }
        });

        insert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                insertSeat();
            }
        });
    }

    private void setText(String intent, @NonNull TextView textView, @IdRes int intt){
        textView = findViewById(intt);
        textView.setText(intent);
    }

    private void getPrevIntent(){
        Intent intent = getIntent();
        //idbooking = intent.getStringExtra("idbook");
        idUser = intent.getStringExtra("idInt");
        namaAsal = intent.getStringExtra("namaAsalInt");
        namaTujuan = intent.getStringExtra("namaTujuanInt");
        Tanggal = intent.getStringExtra("tanggalInt");
        harga = intent.getStringExtra("hargaInt");
        waktuAsal = intent.getStringExtra("waktuAsalInt");
        waktuTujuan = intent.getStringExtra("waktuTujuanInt");
        namaBus = intent.getStringExtra("namaBusInt");
        idBus = intent.getStringExtra("idBusInt");
    }

    private void makeDefaultSeat(){
        for(int i = 1; i <= 30;i++){
            defaultSeat.add(String.valueOf(i));
        }
    }

    private void getBookedSeat() {
        Call<ResponseGetSeat> getSeatCall = apiInterface.getBookedSeat(idBus,Tanggal);

        getSeatCall.enqueue(new Callback<ResponseGetSeat>() {
            @Override
            public void onResponse(Call<ResponseGetSeat> call, Response<ResponseGetSeat> response) {
                makeDefaultSeat();

                if (response.body().getStatus() == 1){
                    dataSeatItems = response.body().getDataSeat();
                    ArrayList<String> listSpinner = new ArrayList<String>();

                    for (int i = 0; i < dataSeatItems.size(); i++){
                        listSpinner.add(dataSeatItems.get(i).getNoKursi());
                    }

                    defaultSeat.removeAll(listSpinner);
                }else if(response.body().getStatus() == 2){
                    defaultSeat = defaultSeat;
                }else{
                    Toast.makeText(mContext, response.body().getMsg(), Toast.LENGTH_SHORT).show();
                }

                adapterDropdown = new ArrayAdapter<String>(mContext, R.layout.list_item,defaultSeat);
                pilihKursi.setAdapter(adapterDropdown);
            }

            @Override
            public void onFailure(Call<ResponseGetSeat> call, Throwable t) {
                Toast.makeText(mContext, "GAGAL KONEK"+t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void insertSeat(){
        Call<ResponseGetSeat> insertSeat = apiInterface.insertseat(idBus,idUser,noKursi,Tanggal);

        insertSeat.enqueue(new Callback<ResponseGetSeat>() {
            @Override
            public void onResponse(Call<ResponseGetSeat> call, Response<ResponseGetSeat> response) {
                if (response.body().getStatus() == 1){
                    Toast.makeText(mContext, response.body().getMsg(), Toast.LENGTH_SHORT).show();

                    Intent intent = new Intent(mContext, MainActivity.class);
                    intent.putExtra("idInt",idUser);
                    mContext.startActivity(intent);
                }else{
                    Toast.makeText(mContext, response.body().getMsg(), Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onFailure(Call<ResponseGetSeat> call, Throwable t) {
                Toast.makeText(mContext, "GAGAL KONEK"+t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}