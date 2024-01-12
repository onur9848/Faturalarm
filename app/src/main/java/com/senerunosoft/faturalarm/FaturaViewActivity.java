package com.senerunosoft.faturalarm;

import android.view.View;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;
import com.senerunosoft.faturalarm.adapter.FaturaInputsAdapter;
import com.senerunosoft.faturalarm.adapter.FaturaInputsAdapterListener;
import com.senerunosoft.faturalarm.databinding.ActivityFaturaViewBinding;
import com.senerunosoft.faturalarm.enums.FirestoreTable;
import com.senerunosoft.faturalarm.models.Fatura;
import com.senerunosoft.faturalarm.models.FaturaInput;

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

        FaturaInputsAdapterListener listener = faturaInput -> {
            Toast.makeText(FaturaViewActivity.this, faturaInput.getFaturaName(), Toast.LENGTH_SHORT).show();
        };

        faturaList.sort((o1, o2) -> o2.getFaturaKayitTarihi().compareTo(o1.getFaturaKayitTarihi()));
        FaturaInputsAdapter adapter = new FaturaInputsAdapter(this, (ArrayList<FaturaInput>) faturaList, listener);
        binding.expandableList.setAdapter(adapter);


    }


    private void defVariable() {
        firestore = FirebaseFirestore.getInstance();
        smp = new SimpleDateFormat("dd/MM/yyyy");
        faturaList = new ArrayList<>();
    }
}
