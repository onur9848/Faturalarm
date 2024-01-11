package com.senerunosoft.faturalarm;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;
import com.senerunosoft.faturalarm.databinding.ActivityFaturaViewBinding;
import com.senerunosoft.faturalarm.enums.FirestoreTable;
import com.senerunosoft.faturalarm.models.Fatura;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class FaturaViewActivity extends AppCompatActivity {

    ActivityFaturaViewBinding binding;
    FirebaseFirestore firestore;
    SimpleDateFormat smp;
    private List<Fatura> faturaList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityFaturaViewBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        defVariable();

//        try {
//            firestore.collection(FirestoreTable.FATURA.getValue()).get().addOnCompleteListener(task -> {
//                if (task.isSuccessful()) {
//                    task.getResult().getDocuments().forEach(documentSnapshot -> {
//                        if (documentSnapshot.getData() != null) {
//                            faturaList.add(new Fatura((HashMap<String, Object>) documentSnapshot.getData()));
//                        }
//                    });
//                    setFaturaList();
//                }
//            });
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
//        firestore.collection(FirestoreTable.FATURAINPUTS.getValue()).get().addOnCompleteListener(task -> {
//            if (task.isSuccessful()) {
//                faturaInputs = task.getResult().toObjects(FaturaInputs.class);
//                dataControl(task.getResult());
//                setFaturaList();
//            }
//        });

    }

//    private void dataControl(QuerySnapshot result) {
//        // Id is ne columns in the data if Id is null then it is not in the data
//        result.getDocuments().forEach(documentSnapshot -> {
//            if (documentSnapshot.getData() != null) {
//                FaturaInputs faturaInputs = documentSnapshot.toObject(FaturaInputs.class);
//                faturaInputs.setId(documentSnapshot.getId());
//                firestore.collection(FirestoreTable.FATURAINPUTS.getValue()).document(documentSnapshot.getId()).set(faturaInputs);
//            }
//        });
//    }
//
//    private void setFaturaList() {
//        faturaInputs.sort((o1, o2) -> o2.getCreatedAt().compareTo(o1.getCreatedAt()));
//        FaturaInputsAdapter adapter = new FaturaInputsAdapter(this, (ArrayList<FaturaInputs>) faturaInputs);
//        binding.expandableList.setAdapter(adapter);
//
//    }

    private void defVariable() {
        firestore = FirebaseFirestore.getInstance();
        smp = new SimpleDateFormat("dd/MM/yyyy");
        faturaList = new ArrayList<>();
    }
}
