package com.senerunosoft.faturalarm;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.core.text.HtmlCompat;
import androidx.lifecycle.ViewModelProvider;
import com.google.firebase.firestore.FirebaseFirestore;
import com.senerunosoft.faturalarm.core.ViewFunc;
import com.senerunosoft.faturalarm.databinding.ActivityFaturaInputDetailBinding;
import com.senerunosoft.faturalarm.enums.BundleKeys;
import com.senerunosoft.faturalarm.enums.FirestoreTable;
import com.senerunosoft.faturalarm.models.Fatura;
import com.senerunosoft.faturalarm.models.FaturaInput;
import com.senerunosoft.faturalarm.viewmodels.FaturaInputViewModel;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class FaturaInputDetailActivity extends AppCompatActivity {

    FirebaseFirestore firestore;

    ActivityFaturaInputDetailBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityFaturaInputDetailBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        defVariable();
        String docId = getIntent().getExtras().getString(BundleKeys.DOCID.getValue());
        firestore.collection(FirestoreTable.FATURAINPUTS.getValue()).document(docId).get().addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                FaturaInput faturaInput = task.getResult().toObject(FaturaInput.class);
//                List<String> faturaDetaylari = new ArrayList<>();
//                faturaDetaylari.add("Fatura No: " + faturaInput.getId());
//                faturaDetaylari.add("Fatura Kayıt Tarihi: " + faturaInput.getFaturaKayitTarihi());
//                faturaDetaylari.add("Fatura Adi: " + faturaInput.getFaturaName());
//                faturaDetaylari.add("T1-ilk Okuma: " + faturaInput.getGunduzIlkOkuma());
//                faturaDetaylari.add("T1-son Okuma: " + faturaInput.getGunduzSonOkuma());
//                faturaDetaylari.add("T2-Tüketim: " + faturaInput.getPuantIlkOkuma());
//                faturaDetaylari.add("T2-ilk Okuma: " + faturaInput.getPuantSonOkuma());
//                faturaDetaylari.add("T3-Tüketim: " + faturaInput.getGeceIlkOkuma());
//                faturaDetaylari.add("T3-ilk Okuma: " + faturaInput.getGeceSonOkuma());
//                faturaDetaylari.add("İlk Okuma Tarihi: " + faturaInput.getIlkOkumaTarihi());
//                faturaDetaylari.add("Son Okuma Tarihi: " + faturaInput.getSonOkumaTarihi());
//                faturaDetaylari.add("Kdv: " + faturaInput.getVergiOrani());
//                faturaDetaylari.add("Elkt ve Enerji Bedeli: " + faturaInput.getElkVeHvgTktmVer());
//                faturaDetaylari.add("Tüketim Bedeli: " + faturaInput.getDagitimBedeliOrani());
//                faturaDetaylari.add("Düşük Kademe Birim Fiyat: " + faturaInput.getDusukKademeBirimFiyat());
//                faturaDetaylari.add("Yüksek Kademe Birim Fiyat: " + faturaInput.getYuksekKademeBirimFiyat());
//                faturaDetaylari.add("Tek Zamanlı Tüketim: " + faturaInput.getTekZamanIlkOkuma());
//                faturaDetaylari.add("Tek Zamanlı Tüketim: " + faturaInput.getTekZamanSonOkuma());
//                binding.textView.setText(String.join("\n", faturaDetaylari));
                faturaViewSetter(faturaInput);
            }
        });


    }

    private void faturaViewSetter(FaturaInput faturaInput) {
        Fatura fatura = new Fatura(faturaInput);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
        binding.ilkOkumaTarihi.setText(simpleDateFormat.format(fatura.getFaturaTarih().getIlkOkumaTarihi()));
        binding.sonOkumaTarihi.setText(simpleDateFormat.format(fatura.getFaturaTarih().getSonOkumaTarihi()));
        binding.faturaAdi.setText(fatura.getFaturaName());
        binding.gunSayisi.setText(String.format(Locale.US,"%d Gün", fatura.getFaturaTarih().getGunSayisi()));

        binding.t1IlkOkuma.setText(ViewFunc.setEditText(fatura.getUcFazKwh().getGunduzSaat().getIlkKw()));
        binding.t1SonOkuma.setText(ViewFunc.setEditText(fatura.getUcFazKwh().getGunduzSaat().getSonKw()));
        binding.t1EndeksFark.setText(ViewFunc.setEditText(fatura.getUcFazKwh().getGunduzSaat().getFarkKw()));

        binding.t2IlkOkuma.setText(ViewFunc.setEditText(fatura.getUcFazKwh().getPuantSaat().getIlkKw()));
        binding.t2SonOkuma.setText(ViewFunc.setEditText(fatura.getUcFazKwh().getPuantSaat().getSonKw()));
        binding.t2EndeksFark.setText(ViewFunc.setEditText(fatura.getUcFazKwh().getPuantSaat().getFarkKw()));

        binding.t3IlkOkuma.setText(ViewFunc.setEditText(fatura.getUcFazKwh().getGeceSaat().getIlkKw()));
        binding.t3SonOkuma.setText(ViewFunc.setEditText(fatura.getUcFazKwh().getGeceSaat().getSonKw()));
        binding.t3EndeksFark.setText(ViewFunc.setEditText(fatura.getUcFazKwh().getGeceSaat().getFarkKw()));

        binding.toplamIlk.setText(ViewFunc.setEditText(fatura.getUcFazKwh().getTekFazKwh().getTekFazSaat().getIlkKw()));
        binding.toplamSon.setText(ViewFunc.setEditText(fatura.getUcFazKwh().getTekFazKwh().getTekFazSaat().getSonKw()));
        binding.toplamFark.setText(ViewFunc.setEditText(fatura.getUcFazKwh().getTekFazKwh().getTekFazSaatFarkKw()));

        String dusuKKademeString = String.format(Locale.US,"%.2f kWh \nx %.5f ₺", fatura.getDusukKademeKullanim(), faturaInput.getDusukKademeBirimFiyat());
        String yuksekKademeString = String.format(Locale.US,"%.2f kWh \nx %.5f ₺", fatura.getYuksekKademeKullanim(), faturaInput.getYuksekKademeBirimFiyat());
        binding.dusukKademeCarpim.setText(HtmlCompat.fromHtml(dusuKKademeString, HtmlCompat.FROM_HTML_MODE_LEGACY));
        binding.yuksekKademeCarpim.setText(HtmlCompat.fromHtml(yuksekKademeString, HtmlCompat.FROM_HTML_MODE_LEGACY));
        binding.dusukKademeFiyat.setText(ViewFunc.setEditTextWithTl(fatura.getDusukKademeToplamFiyat()));
        binding.yuksekKademeFiyat.setText(ViewFunc.setEditTextWithTl(fatura.getYuksekKademeToplamFiyat()));

        binding.elektrikVeHvgTuketimVergisi.setText(ViewFunc.setEditTextWithTl(fatura.getElktVeHvg()));
        binding.kdvFiyat.setText(ViewFunc.setEditTextWithTl(fatura.getKdv()));
        binding.toplamTutarFiyat.setText(ViewFunc.setEditTextWithTl(fatura.getKdvliTutar()));

    }

    private void defVariable() {
        firestore = FirebaseFirestore.getInstance();
    }
}
