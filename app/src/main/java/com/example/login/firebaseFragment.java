package com.example.login;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.FirebaseApp;
import com.google.firebase.firestore.FirebaseFirestore;

public class firebaseFragment extends Fragment {
    private EditText SKS;
    private EditText namaMatakuliah;
    private EditText namaDosen;
    private Button buttonSimpan;
    private Button buttonHapus;
    private FirebaseFirestore firebaseFirestoreDb;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.firebase_fragment, container, false);
        SKS = view.findViewById(R.id.sks);
        namaMatakuliah = view.findViewById(R.id.namaMatakuliah);
        namaDosen = view.findViewById(R.id.namaDosen);
        buttonSimpan = view.findViewById(R.id.simpanButton);
        buttonHapus = view.findViewById(R.id.hapusButton);
        firebaseFirestoreDb = FirebaseFirestore.getInstance();
        buttonSimpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //sanity check
                if (!SKS.getText().toString().isEmpty() && !namaMatakuliah.getText().toString().isEmpty()&& !namaDosen.getText().toString().isEmpty()) {
                    tambahMatakuliah();
                } else {
                    Toast.makeText(requireActivity(), "SKS dan Nama Matakuliah tidak boleh kosong",
                            Toast.LENGTH_SHORT).show();
                }
            }
        });
        return view;
    }

    private void tambahMatakuliah() {
        Matakuliah mhs = new Matakuliah(Integer.parseInt(SKS.getText().toString()),
                namaMatakuliah.getText().toString(),
                namaDosen.getText().toString());

        firebaseFirestoreDb.collection("DaftarMhs").document().set(mhs)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Toast.makeText(requireActivity(), "Matakuliah berhasil didaftarkan",
                                Toast.LENGTH_SHORT).show();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(requireActivity(), "ERROR" + e.toString(),
                                Toast.LENGTH_SHORT).show();
                        Log.d("TAG", e.toString());
                    }
                });


    }
}

