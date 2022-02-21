package com.example.project4hacktiv;

import static android.content.ContentValues.TAG;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import com.example.project4hacktiv.API.ApiClient;
import com.example.project4hacktiv.API.ApiInterface;
import com.example.project4hacktiv.Adapter.AdapterJadwal;
import com.example.project4hacktiv.Model.jadwal.DataBusItem;
import com.example.project4hacktiv.Model.jadwal.ResponseJadwal;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class JadwalActivity extends AppCompatActivity {

    List<DataBusItem> listDataBus = new ArrayList<>();
    RecyclerView tampilJadwal;
    RecyclerView.Adapter adapter;
    RecyclerView.LayoutManager layoutManager;
    SwipeRefreshLayout swipeRefreshLayout;
    String idUser;
    String namaAsal;
    String namaTujuan;
    String idAsal;
    String idTujuan;
    String Tanggal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jadwal);

        getSupportActionBar().setTitle("Jadwal Ticket");

        Intent intent = getIntent();
        idUser = intent.getStringExtra("idInt");
        namaAsal = intent.getStringExtra("namaAsalInt");
        namaTujuan = intent.getStringExtra("namaTujuan");
        idAsal = intent.getStringExtra("idAsalInt");
        idTujuan = intent.getStringExtra("idTujuanInt");
        Tanggal = intent.getStringExtra("tanggalInt");

        swipeRefreshLayout = findViewById(R.id.swprf);
        tampilJadwal = findViewById(R.id.data_Jadwal);
        layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        tampilJadwal.setLayoutManager(layoutManager);

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                swipeRefreshLayout.setRefreshing(true);
                tampil_Jadwal();
                swipeRefreshLayout.setRefreshing(false);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        tampil_Jadwal();
    }


    private void tampil_Jadwal() {
        ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
        Call<ResponseJadwal> dataJadwal = apiInterface.getJadwal(idAsal,idTujuan);

        dataJadwal.enqueue(new Callback<ResponseJadwal>() {
            @Override
            public void onResponse(Call<ResponseJadwal> call, Response<ResponseJadwal> response) {
                if (response.body().getMsg() != null){
                    listDataBus = response.body().getDataBus();
                    adapter = new AdapterJadwal(JadwalActivity.this,listDataBus,idUser,namaAsal,namaTujuan,idAsal,idTujuan,Tanggal);
                    tampilJadwal.setAdapter(adapter);
                }else{
                    Toast.makeText(JadwalActivity.this, response.body().getMsg(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseJadwal> call, Throwable t) {
                Toast.makeText(JadwalActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}