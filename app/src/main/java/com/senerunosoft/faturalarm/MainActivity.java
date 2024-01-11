package com.senerunosoft.faturalarm;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.firestore.FirebaseFirestore;
import com.senerunosoft.faturalarm.core.ViewFunc;
import com.senerunosoft.faturalarm.databinding.ActivityMainBinding;
import com.senerunosoft.faturalarm.models.Fatura;
import com.senerunosoft.faturalarm.models.FaturaInput;
import com.senerunosoft.faturalarm.models.FiyatSabitleri;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;
    SimpleDateFormat smp;
    SharedPreferences sharedPreferences;
    boolean isThreeFaz = true;
    FirebaseFirestore firestore;
    private Context context;
    private FiyatSabitleri sabitler;
    boolean changeValueOnSabit = false;
    private FiyatSabitleri changedSabit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        context = this;
        defVariables();

    }

    private void defVariables() {
        changedSabit = new FiyatSabitleri();
        sabitler = new FiyatSabitleri(1.34107f, 1.991154f, 10f, 5f, 8f, 0.858883f);
        binding.dusukKademeBirimFiyat.setPlaceholderText(String.format("%f", sabitler.getDusukKademeBirimFiyat()));
        binding.yuksekKademeBirimFiyat.setPlaceholderText(String.format("%f", sabitler.getYuksekKademeBirimFiyat()));
        smp = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
        sharedPreferences = getSharedPreferences("fatura", MODE_PRIVATE);
        setDefaultDate();
        clickOnDrawableIcon(binding.ilkOkumaTarihi);
        clickOnDrawableIcon(binding.sonOkumaTarihi);
        setToggleSelected();
        firestore = FirebaseFirestore.getInstance();
        binding.singleFaz.setOnClickListener(this::fazSelectionToggle);
        binding.threeFaz.setOnClickListener(this::fazSelectionToggle);
        binding.faturaKaydet.setOnClickListener(this::faturaKaydet);
        binding.receiptHistoryButton.setOnClickListener(this::receiptHistoryButton);
        binding.hesapla.setOnClickListener(this::hesapla);
    }

    private void setToggleSelected() {
        isThreeFaz = sharedPreferences.getBoolean("is_three_faz", true);
        if (isThreeFaz) {
            binding.threeFazView.setVisibility(View.VISIBLE);
            binding.tekFazView.setVisibility(View.GONE);
        } else {
            binding.threeFazView.setVisibility(View.GONE);
            binding.tekFazView.setVisibility(View.VISIBLE);
        }
        binding.tarifeSecim.check(isThreeFaz ? binding.threeFaz.getId() : binding.singleFaz.getId());

    }

    private void fazSelectionToggle(View view) {
        if (view == binding.threeFaz) {
            binding.tekFazView.setVisibility(View.GONE);
            binding.threeFazView.setVisibility(View.VISIBLE);
            isThreeFaz = true;
        } else {
            binding.tekFazView.setVisibility(View.VISIBLE);
            binding.threeFazView.setVisibility(View.GONE);
            isThreeFaz = false;
        }
        sharedPreferences.edit().putBoolean("is_three_faz", isThreeFaz).apply();
    }

    private void receiptHistoryButton(View view) {
        Intent intent = new Intent(context, FaturaViewActivity.class);
        startActivity(intent);
    }

    public void clickOnDrawableIcon(TextInputLayout layout) {
        layout.setStartIconOnClickListener(l -> {
            Calendar cal = Calendar.getInstance();
            DatePickerDialog dialog = new DatePickerDialog(
                    MainActivity.this,
                    android.R.style.Theme_Material_Dialog_MinWidth,
                    (view, year, month, dayOfMonth) -> {
                        cal.set(year, month, dayOfMonth);
                        String date = smp.format(cal.getTime());
                        layout.getEditText().setText(date);
                        if (layout == binding.ilkOkumaTarihi) {
                            sharedPreferences.edit().putString("ilk_okuma_tarihi", date).apply();
                        } else if (layout == binding.sonOkumaTarihi) {
                            sharedPreferences.edit().putString("son_okuma_tarihi", date).apply();
                        }
                    },
                    cal.get(Calendar.YEAR),
                    cal.get(Calendar.MONTH),
                    cal.get(Calendar.DAY_OF_MONTH)
            );
            dialog.show();
        });
    }

    private void faturaKaydet(View view) {
    }

    private void hesapla(View view) {
        FaturaInput input = getInputFromView();
        Fatura fatura = new Fatura(input);
        List<String> stringList = new ArrayList<>();
        binding.dusukKademeToplamFiyat.setText(String.format("%.2f ₺", fatura.getDusukKademeToplamFiyat()));
        binding.yuksekKademeToplamFiyat.setText(String.format("%.2f ₺", fatura.getYuksekKademeToplamFiyat()));
        binding.elekVeHvgTukVerFiyat.setText(String.format("%.2f ₺", fatura.getElktVeHvg()));
        binding.kdvFiyat.setText(String.format("%.2f ₺", fatura.getKdv()));
        binding.toplamFiyat.setText(String.format("%.2f ₺",fatura.getKdvliTutar()));

    }

    private FaturaInput getInputFromView() {
        FaturaInput faturaInput = new FaturaInput();
        try {
            faturaInput.setIlkOkumaTarihi(smp.parse(binding.ilkOkumaTarihi.getEditText().getText().toString()));
            faturaInput.setSonOkumaTarihi(smp.parse(binding.sonOkumaTarihi.getEditText().getText().toString()));
        } catch (ParseException e) {
            faturaInput.setIlkOkumaTarihi(Calendar.getInstance().getTime());
            faturaInput.setSonOkumaTarihi(Calendar.getInstance().getTime());
        }
        faturaInput.setThreeFaz(isThreeFaz);
        if (isThreeFaz) {
            faturaInput.setGunduzIlkOkuma(ViewFunc.getFloatView(binding.gunduzT1Ilk));
            faturaInput.setGunduzSonOkuma(ViewFunc.getFloatView(binding.gunduzT1Son));
            faturaInput.setPuantIlkOkuma(ViewFunc.getFloatView(binding.puantT2Ilk));
            faturaInput.setPuantSonOkuma(ViewFunc.getFloatView(binding.puantT2Son));
            faturaInput.setGeceIlkOkuma(ViewFunc.getFloatView(binding.geceT3Ilk));
            faturaInput.setGeceSonOkuma(ViewFunc.getFloatView(binding.geceT3Son));
        } else {
            faturaInput.setTekZamanIlkOkuma(ViewFunc.getFloatView(binding.ilkOkuma));
            faturaInput.setTekZamanSonOkuma(ViewFunc.getFloatView(binding.sonOkuma));
        }
        if (changeValueOnSabit) {
            faturaInput.setDusukKademeBirimFiyat(changedSabit.getDusukKademeBirimFiyat());
            faturaInput.setYuksekKademeBirimFiyat(changedSabit.getYuksekKademeBirimFiyat());
            faturaInput.setVergiOrani(changedSabit.getKdvOrani());
            faturaInput.setGunlukKullanimKw(changedSabit.getGunlukKullanimKw());
            faturaInput.setElkVeHvgTktmVer(changedSabit.getElkVeHvgTktmVer());
        }

        return faturaInput;
    }

    private void setDefaultDate() {
        String ilkOkumaTarihi = sharedPreferences.getString("ilk_okuma_tarihi", smp.format(Calendar.getInstance().getTime()));
        String sonOkumaTarihi = sharedPreferences.getString("son_okuma_tarihi", smp.format(Calendar.getInstance().getTime()));
        binding.ilkOkumaTarihi.getEditText().setText(ilkOkumaTarihi);
        binding.sonOkumaTarihi.getEditText().setText(sonOkumaTarihi);
    }
}
