//package com.senerunosoft.faturalarm.adapter;
//
//import android.annotation.SuppressLint;
//import android.content.Context;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.TextView;
//import androidx.annotation.NonNull;
//import androidx.annotation.Nullable;
//import androidx.recyclerview.widget.RecyclerView;
//import com.senerunosoft.faturalarm.R;
//import com.senerunosoft.faturalarm.models.Fatura;
//
//public class FaturaInputsAdapter extends ArrayAdapter<FaturaInputs> {
//
//    private final LayoutInflater inflater;
//    private final Context context;
//    private RecyclerView.ViewHolder holder;
//    private final ArrayList<FaturaInputs> faturaInputs;
//
//    public FaturaInputsAdapter(Context context, ArrayList<FaturaInputs> faturaInputs) {
//        super(context, 0, faturaInputs);
//        this.context = context;
//        this.faturaInputs = faturaInputs;
//        inflater = LayoutInflater.from(context);
//    }
//
//    @Override
//    public int getCount() {
//        return faturaInputs.size();
//    }
//
//    @Override
//    public FaturaInputs getItem(int position) {
//        return faturaInputs.get(position);
//    }
//
//    @Override
//    public long getItemId(int position) {
//        return position;
//    }
//
//    @Override
//    public int getItemViewType(int position) {
//        return position;
//    }
//
//    @SuppressLint("DefaultLocale")
//    @NonNull
//    @Override
//    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
//
//        ViewHolder holder;
//        if (convertView == null) {
//            convertView = inflater.inflate(R.layout.fatura_item, null);
//            holder = new ViewHolder();
//            holder.harcananKw = convertView.findViewById(R.id.harcanan_kwh_deger);
//            holder.tutar = convertView.findViewById(R.id.tutar_deger);
//            holder.faturaName = convertView.findViewById(R.id.txtFaturaId);
//            convertView.setTag(holder);
//        } else {
//            holder = (ViewHolder) convertView.getTag();
//        }
//        FaturaInputs selectedFatura = faturaInputs.get(position);
//        Fatura fatura = new Fatura(selectedFatura);
//        holder.harcananKw.setText(String.format("%.2f Kwh", (fatura.getTekZamanKw().getFarkKw())));
//        holder.tutar.setText(String.format("%.2f ₺", (fatura.getKdvliTutar(selectedFatura.getDusukKademeBirimFiyat(), selectedFatura.getYuksekKademeBirimFiyat()))));
//        holder.faturaName.setText(selectedFatura.getFaturaName());
//
//        return convertView;
//    }
//
//    private static class ViewHolder {
//        TextView harcananKw;
//        TextView tutar;
//        TextView faturaName;
//    }
//}
