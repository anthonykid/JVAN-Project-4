package com.example.project4hacktiv.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.project4hacktiv.DetailTicket;
import com.example.project4hacktiv.Model.History.DataDetailItem;
import com.example.project4hacktiv.R;

import java.util.List;

public class Adapterdetail extends RecyclerView.Adapter<Adapterdetail.HolderData> {
    Context context;
    List<DataDetailItem> listDataBus;
    String idUser;
    String namaUser;


    public Adapterdetail(Context context, List<DataDetailItem> listDataBus, String idUser ) {
        this.context = context;
        this.listDataBus = listDataBus;
        this.idUser = idUser;
    }

    @NonNull
    @Override
    public HolderData onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View layout = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_historyticket, parent, false);
        HolderData holder = new HolderData(layout);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull HolderData but, int position) {
        DataDetailItem dataBusItem = listDataBus.get(position);

        but.idUserH.setText(String.valueOf(dataBusItem.getIdUser()));
        but.idBkH.setText(String.valueOf(dataBusItem.getIdTransaksi()));
        but.idBusH.setText(String.valueOf(dataBusItem.getIdBus()));
        but.tanggalH.setText(String.valueOf(dataBusItem.getTanggal()));
        but.hargaH.setText(String.valueOf(dataBusItem.getHargaTicket()));
        but.namaBusH.setText(String.valueOf(dataBusItem.getNamaBus()));
        but.namaAsalH.setText(String.valueOf(dataBusItem.getNamaKotaasal()));
        but.namaTujaunH.setText(String.valueOf(dataBusItem.getNamaKotatujuan()));
        but.waktuAsalH.setText(String.valueOf(dataBusItem.getWaktuBerangkat()));
        but.waktuTujuanH.setText(String.valueOf(dataBusItem.getWaktuSampai()));
        but.kursiH.setText(String.valueOf(dataBusItem.getKursi()));
        namaUser = dataBusItem.getName();
    }

    @Override
    public int getItemCount() {return listDataBus.size();}

    public class HolderData extends RecyclerView.ViewHolder {
        TextView idUserH,idBkH,kursiH, idBusH, idAsalH, idTujaunH,tanggalH, waktuAsalH, waktuTujuanH, hargaH, namaBusH, namaAsalH, namaTujaunH;
        public HolderData(@NonNull View jem) {
            super(jem);

            //bagian cari textview di xml-nya
            idUserH = jem.findViewById(R.id.idUserTV2);
            idBkH = jem.findViewById(R.id.bkid);
            idBusH = jem.findViewById(R.id.idBusTV2);
            idAsalH = jem.findViewById(R.id.idAsalTV2);
            idTujaunH = jem.findViewById(R.id.idTujaunTV2);
            tanggalH = jem.findViewById(R.id.tanggalTV2);
            waktuAsalH = jem.findViewById(R.id.waktuAsal2);
            waktuTujuanH = jem.findViewById(R.id.waktuTujuanTV2);
            namaAsalH = jem.findViewById(R.id.namaAsalTV2);
            namaTujaunH = jem.findViewById(R.id.namaTujuanTV2);
            namaBusH = jem.findViewById(R.id.namaBusTV2);
            hargaH = jem.findViewById(R.id.hargaTV2);
            kursiH = jem.findViewById(R.id.idkursiTV2);
            jem.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    //bagian krim intent
                    Intent intent = new Intent(context, DetailTicket.class);
                    intent.putExtra("idInt",idUserH.getText().toString());
                    intent.putExtra("idbook",idBkH.getText().toString());
                    intent.putExtra("namaInt",namaUser);
                    intent.putExtra("tanggalInt",tanggalH.getText().toString());
                    intent.putExtra("namaAsalInt",namaAsalH.getText().toString());
                    intent.putExtra("namaTujuanInt",namaTujaunH.getText().toString());
                    intent.putExtra("idBusInt",idBusH.getText().toString());
                    intent.putExtra("waktuAsalInt",waktuAsalH.getText().toString());
                    intent.putExtra("waktuTujuanInt",waktuTujuanH.getText().toString());
                    intent.putExtra("hargaInt",hargaH.getText().toString());
                    intent.putExtra("namaBusInt",namaBusH.getText().toString());
                    intent.putExtra("kursiInt",kursiH.getText().toString());
                    context.startActivity(intent);
                }
            });

        }
    }
}

