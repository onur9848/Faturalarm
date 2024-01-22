package com.senerunosoft.faturalarm.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;
import com.senerunosoft.faturalarm.R;
import com.senerunosoft.faturalarm.models.Fatura;
import com.senerunosoft.faturalarm.models.FaturaInput;

import java.util.ArrayList;
import java.util.Locale;

public class FaturaInputsAdapter extends ArrayAdapter<FaturaInput> {

    private final LayoutInflater inflater;
    private final Context context;
    private final ArrayList<FaturaInput> faturaInputs;
    OnItemClickListener listener;
    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }
    public interface OnItemClickListener {
        void getItemDetails(FaturaInput input);
    }
    public FaturaInputsAdapter(Context context, ArrayList<FaturaInput> faturaInputs ) {
        super(context, 0, faturaInputs);
        this.context = context;
        this.faturaInputs = faturaInputs;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return faturaInputs.size();
    }

    @Override
    public FaturaInput getItem(int position) {
        return faturaInputs.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getItemViewType(int position) {
        return faturaInputs.get(position).isThreeFaz() ? 1 : 0;
    }


    @SuppressLint("DefaultLocale")
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        ViewHolder holder;
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.fatura_item, null);
            holder = new ViewHolder();
            holder.harcananKw = convertView.findViewById(R.id.harcanan_kwh_deger);
            holder.tutar = convertView.findViewById(R.id.tutar_deger);
            holder.faturaName = convertView.findViewById(R.id.txtFaturaId);
            holder.infoBtn = convertView.findViewById(R.id.fatura_detay_btn);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        FaturaInput selectedFatura = faturaInputs.get(position);
        Fatura fatura = new Fatura(selectedFatura);
        float harcanankw = fatura.isThreeFaz() ? fatura.getUcFazKwh().getFarkKw() : fatura.getTekFazKwh().getTekFazSaatFarkKw();
        holder.harcananKw.setText(String.format(Locale.US, "%.2f Kwh", harcanankw));
        holder.tutar.setText(String.format(Locale.US, "%.2f ₺", (fatura.getKdvliTutar(selectedFatura.getDusukKademeBirimFiyat(), selectedFatura.getYuksekKademeBirimFiyat()))));
        holder.faturaName.setText(selectedFatura.getFaturaName());

        holder.infoBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.getItemDetails(selectedFatura);
            }
        });


        return convertView;
    }

    private static class ViewHolder {
        TextView harcananKw;
        TextView tutar;
        TextView faturaName;
        ImageButton infoBtn;
    }
}

