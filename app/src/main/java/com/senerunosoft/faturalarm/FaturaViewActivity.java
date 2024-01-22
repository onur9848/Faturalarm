package com.senerunosoft.faturalarm;

import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.lifecycle.ViewModelProvider;
import com.google.firebase.firestore.FirebaseFirestore;
import com.senerunosoft.faturalarm.adapter.FaturaInputsAdapter;
import com.senerunosoft.faturalarm.databinding.ActivityFaturaViewBinding;
import com.senerunosoft.faturalarm.enums.BundleKeys;
import com.senerunosoft.faturalarm.enums.FirestoreTable;
import com.senerunosoft.faturalarm.models.FaturaInput;
import com.senerunosoft.faturalarm.viewmodels.FaturaInputViewModel;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class FaturaViewActivity extends AppCompatActivity {

    ActivityFaturaViewBinding binding;
    FirebaseFirestore firestore;
    SimpleDateFormat smp;
    private List<FaturaInput> faturaList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityFaturaViewBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        defVariable();

        firestore.collection(FirestoreTable.FATURAINPUTS.getValue()).get().addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                faturaList = task.getResult().toObjects(FaturaInput.class);
                setFaturaList();
            }
        });

    }


    private void setFaturaList() {


        faturaList.sort((o1, o2) -> o2.getFaturaKayitTarihi().compareTo(o1.getFaturaKayitTarihi()));
        FaturaInputsAdapter adapter = new FaturaInputsAdapter(this, (ArrayList<FaturaInput>) faturaList);
        binding.expandableList.setAdapter(adapter);

        adapter.setOnItemClickListener((input) -> {
            Bundle bundle = new Bundle();
            bundle.putString(BundleKeys.DOCID.getValue(), input.getId());
            Intent intent = new Intent(this, FaturaInputDetailActivity.class);
            intent.putExtras(bundle);
            startActivity(intent);
            onPause();
        });

    }


    private void defVariable() {
        firestore = FirebaseFirestore.getInstance();
        smp = new SimpleDateFormat("dd/MM/yyyy");
        faturaList = new ArrayList<>();
    }
}
