package com.example.project4hacktiv;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.example.project4hacktiv.API.ApiClient;
import com.example.project4hacktiv.API.ApiInterface;
import com.example.project4hacktiv.Adapter.Adapterdetail;
import com.example.project4hacktiv.Model.History.DataDetailItem;
import com.example.project4hacktiv.Model.History.ResponseHistory;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Historyticket extends AppCompatActivity {

    List<DataDetailItem> listDataBus = new ArrayList<>();

    RecyclerView tampildetail;
    RecyclerView.Adapter adapter;
    RecyclerView.LayoutManager layoutManager;
    SwipeRefreshLayout swipeRefreshLayout;
    String idUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);

        Intent intent = getIntent();
        idUser = intent.getStringExtra("idInt");

        swipeRefreshLayout = findViewById(R.id.swprf2);
        tampildetail = findViewById(R.id.data_history);

        layoutManager = new LinearLayoutManager(Historyticket.this, LinearLayoutManager.VERTICAL, false);
        tampildetail.setLayoutManager(layoutManager);


        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
            swipeRefreshLayout.setRefreshing(true);
            tampil_detail();
            swipeRefreshLayout.setRefreshing(false);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        tampil_detail();
    }


    private void tampil_detail() {
        ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
        Call<ResponseHistory> dataHistory = apiInterface.tampildetail(idUser);

        dataHistory.enqueue(new Callback<ResponseHistory>() {
            @Override
            public void onResponse(Call<ResponseHistory> call, Response<ResponseHistory> response) {
                if (response.body().getStatus() == 1){
                    listDataBus = response.body().getDataDetail();
                    adapter = new Adapterdetail(Historyticket.this,listDataBus,idUser);
                    tampildetail.setAdapter(adapter);
                }else{
                    Toast.makeText(Historyticket.this, response.body().getMsg(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseHistory> call, Throwable t) {

            }
        });
    }
}