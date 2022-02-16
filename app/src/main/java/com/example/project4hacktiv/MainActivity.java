package com.example.project4hacktiv;

import static java.lang.String.*;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.icu.util.Calendar;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.project4hacktiv.API.ApiClient;
import com.example.project4hacktiv.API.ApiInterface;
import com.example.project4hacktiv.Model.kota.DataKotaItem;
import com.example.project4hacktiv.Model.kota.Kota;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    ApiInterface apiInterface;
    List<DataKotaItem> dataKotaItems;
    Button test;
    String id_kotaAsal, namaKotaAsal, id_kotaTujuan, namaKOtaTujuan,tanggal;
    String idUser = "1";
    Context mContext;
    AutoCompleteTextView ddAsal,ddTujuan, openCal;
    ArrayAdapter<String> adapterDropdown;
    Calendar myCalendar;

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mContext = this;

        ddAsal = findViewById(R.id.dropdownAsal);
        ddTujuan = findViewById(R.id.dropdownTujaun);
        openCal = findViewById(R.id.openCalendar);
        test = findViewById(R.id.button);

        getDataKota();
        myCalendar = Calendar.getInstance();

        openCal.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onClick(View view) {
                new DatePickerDialog(mContext, new DatePickerDialog.OnDateSetListener() {
                    @RequiresApi(api = Build.VERSION_CODES.N)
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        myCalendar.set(Calendar.YEAR, year);
                        myCalendar.set(Calendar.MONTH, month);
                        myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);

                        String formatTanggal = "dd-MM-yyyy";
                        SimpleDateFormat sdf = new SimpleDateFormat(formatTanggal);
                        openCal.setText(sdf.format(myCalendar.getTime()));
                    }
                },
                        myCalendar.get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });

        ddAsal.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                id_kotaAsal = valueOf(adapterView.getItemIdAtPosition(i)+1);
                namaKotaAsal = valueOf(adapterView.getItemAtPosition(i));
            }
        });

        ddTujuan.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                id_kotaTujuan = valueOf(adapterView.getItemIdAtPosition(i)+1);
                namaKOtaTujuan = valueOf(adapterView.getItemAtPosition(i));
            }
        });


        test.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /*tanggal = valueOf(openCal.getText());
                Result = "Asal : "+namaKotaAsal+" , " +"Tujuan : "+id_kotaTujuan+" , "+"Tanggal : "+tanggal;
                Toast.makeText(mContext, Result, Toast.LENGTH_SHORT).show();*/

                tanggal = openCal.getText().toString();
                Intent intent = new Intent(mContext, JadwalActivity.class);
                intent.putExtra("idInt",idUser);
                intent.putExtra("idAsalInt",id_kotaAsal);
                intent.putExtra("idTujuanInt",id_kotaTujuan);
                intent.putExtra("tanggalInt",tanggal);
                intent.putExtra("namaAsalInt",namaKotaAsal);
                intent.putExtra("namaTujuan",namaKOtaTujuan);
                mContext.startActivity(intent);
            }
        });
    }

    private void getDataKota(){
        apiInterface = ApiClient.getClient().create(ApiInterface.class);
        Call<Kota> dataKota = apiInterface.getKota();

        dataKota.enqueue(new Callback<Kota>() {
            @Override
            public void onResponse(Call<Kota> call, Response<Kota> response) {
                if (response.body().getMsg() != null){
                    dataKotaItems = response.body().getDataKota();

                    ArrayList<String> listSpinner = new ArrayList<String>();

                    for (int i = 0; i < dataKotaItems.size(); i++){
                        listSpinner.add(dataKotaItems.get(i).getNamaKota());
                    }
                    adapterDropdown = new ArrayAdapter<String>(mContext,R.layout.list_item,listSpinner);
                    ddTujuan.setAdapter(adapterDropdown);
                    ddAsal.setAdapter(adapterDropdown);
                }else{
                    Toast.makeText(MainActivity.this, response.body().getMsg(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Kota> call, Throwable t) {
                Toast.makeText(MainActivity.this, "GAGAL KONEK"+t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}