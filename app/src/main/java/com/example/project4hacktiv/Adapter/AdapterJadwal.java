package com.example.project4hacktiv.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.project4hacktiv.Model.jadwal.DataBusItem;
import com.example.project4hacktiv.R;

import java.util.List;

public class AdapterJadwal extends RecyclerView.Adapter<AdapterJadwal.HolderData> {
    Context context;
    List<DataBusItem> listDataBus;
    String idUser;
    String namaAsal;
    String namaTujuan;
    String idAsal;
    String idTujuan;
    String Tanggal;

    public AdapterJadwal(Context context, List<DataBusItem> listDataBus, String idUser, String namaAsal, String namaTujuan, String idAsal, String idTujuan, String Tanggal ) {
        this.context = context;
        this.listDataBus = listDataBus;
        this.idUser = idUser;
        this.namaAsal = namaAsal;
        this.namaTujuan = namaTujuan;
        this.idAsal = idAsal;
        this.idTujuan = idTujuan;
        this.Tanggal = Tanggal;
    }

    @NonNull
    @Override
    public HolderData onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View layout = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_ticket, parent, false);
        HolderData holder = new HolderData(layout);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull HolderData but, int position) {
        DataBusItem dataBusItem = listDataBus.get(position);

        but.idUserH.setText(idUser);
        but.idBusH.setText(String.valueOf(dataBusItem.getIdBus()));
        but.idAsalH.setText(idAsal);
        but.idTujaunH.setText(idTujuan);
        but.tanggalH.setText(Tanggal);
        but.hargaH.setText(String.valueOf(dataBusItem.getHargaTicket()));
        but.namaBusH.setText(String.valueOf(dataBusItem.getNamaBus()));
        but.namaAsalH.setText(namaAsal);
        but.namaTujaunH.setText(namaTujuan);
        but.waktuAsalH.setText(String.valueOf(dataBusItem.getWaktuBerangkat()));
        but.waktuTujuanH.setText(String.valueOf(dataBusItem.getWaktuSampai()));

    }

    @Override
    public int getItemCount() {return listDataBus.size();}

    public class HolderData extends RecyclerView.ViewHolder {
        TextView idUserH, idBusH, idAsalH, idTujaunH,tanggalH, waktuAsalH, waktuTujuanH, hargaH, namaBusH, namaAsalH, namaTujaunH;
        public HolderData(@NonNull View jem) {
            super(jem);

            idUserH = jem.findViewById(R.id.idUserTV);
            idBusH = jem.findViewById(R.id.idBusTV);
            idAsalH = jem.findViewById(R.id.idAsalTV);
            idTujaunH = jem.findViewById(R.id.idTujaunTV);
            tanggalH = jem.findViewById(R.id.tanggalTV);
            waktuAsalH = jem.findViewById(R.id.waktuAsal);
            waktuTujuanH = jem.findViewById(R.id.waktuTujuanTV);
            namaAsalH = jem.findViewById(R.id.namaAsalTV);
            namaTujaunH = jem.findViewById(R.id.namaTujuanTV);
            namaBusH = jem.findViewById(R.id.namaBusTV);
            hargaH = jem.findViewById(R.id.hargaTV);

            jem.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(context, idUserH.getText(), Toast.LENGTH_SHORT).show();
                }
            });

        }
    }
}

