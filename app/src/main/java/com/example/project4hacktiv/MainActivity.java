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
    List<DataKotaItem> ListKotaAsal, ListKotaTujuan;
    Button test, test2;
    String id_kotaAsal="0", namaKotaAsal, id_kotaTujuan="0", namaKOtaTujuan,tanggal="0";
    String idUser = "0";
    Context mContext;
    AutoCompleteTextView ddAsal,ddTujuan, openCal;
    ArrayAdapter<String> adapterDropdownAsal, adapterDropdownTujuan;
    Calendar myCalendar;

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mContext = this;

        Intent intent = getIntent();
        idUser =  intent.getStringExtra("idInt");

        ddAsal = findViewById(R.id.dropdownAsal);
        ddTujuan = findViewById(R.id.dropdownTujaun);
        openCal = findViewById(R.id.openCalendar);
        test = findViewById(R.id.button);
        test2 = findViewById(R.id.button2);

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
                        tanggal = openCal.getText().toString();
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
                if ((id_kotaAsal.equals(id_kotaTujuan))) {
                    Toast.makeText(mContext, "Maaf Isi Data Dengan Benar!", Toast.LENGTH_SHORT).show();
                }else if (id_kotaAsal == "0" || id_kotaTujuan == "0") {
                    Toast.makeText(mContext, "Maaf Isi Data Dengan Benar!", Toast.LENGTH_SHORT).show();
                }else{
                    if (tanggal == "0"){
                        Toast.makeText(mContext, "Tanggal pemesanan belum di atur!", Toast.LENGTH_SHORT).show();
                    }else{
                        Intent intent = new Intent(mContext, JadwalActivity.class);
                        intent.putExtra("idInt", idUser);
                        intent.putExtra("idAsalInt", id_kotaAsal);
                        intent.putExtra("idTujuanInt", id_kotaTujuan);
                        intent.putExtra("tanggalInt", tanggal);
                        intent.putExtra("namaAsalInt", namaKotaAsal);
                        intent.putExtra("namaTujuan", namaKOtaTujuan);
                        mContext.startActivity(intent);
                    }
                }
            }
        });

        test2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent asd = new Intent(mContext,Historyticket.class);
                asd.putExtra("idInt",idUser);
                startActivity(asd);
            }
        });
    }

    private void getDataKota(){
        apiInterface = ApiClient.getClient().create(ApiInterface.class);

        ArrayList<String> listSpinnerAsal = new ArrayList<String>();
        ArrayList<String> listSpinnerTujuan = new ArrayList<String>();

        Call<Kota> dataKotaAsal = apiInterface.getKotaAsal();
        Call<Kota> dataKotaTujuan = apiInterface.getKotaTujuan();

        dataKotaAsal.enqueue(new Callback<Kota>() {
            @Override
            public void onResponse(Call<Kota> call, Response<Kota> response) {
                if (response.body().getMsg() != null){
                    ListKotaAsal = response.body().getDataKota();

                    for (int i = 0; i < ListKotaAsal.size(); i++){
                        listSpinnerAsal.add(ListKotaAsal.get(i).getNamaKota());
                    }
                    adapterDropdownAsal = new ArrayAdapter<String>(mContext, R.layout.list_item,listSpinnerAsal);
                    ddAsal.setAdapter(adapterDropdownAsal);
                }else{
                    Toast.makeText(MainActivity.this, response.body().getMsg(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Kota> call, Throwable t) {
                Toast.makeText(MainActivity.this, "GAGAL KONEK"+t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

        dataKotaTujuan.enqueue(new Callback<Kota>() {
            @Override
            public void onResponse(Call<Kota> call, Response<Kota> response) {
                if (response.body().getMsg() != null){
                    ListKotaTujuan = response.body().getDataKota();

                    for (int i = 0; i < ListKotaTujuan.size(); i++){
                        listSpinnerTujuan.add(ListKotaTujuan.get(i).getNamaKota());
                    }
                    adapterDropdownTujuan = new ArrayAdapter<String>(mContext, R.layout.list_item,listSpinnerTujuan);
                    ddTujuan.setAdapter(adapterDropdownTujuan);
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