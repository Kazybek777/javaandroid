package com.kazy.androidmessenger;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;
import com.kazy.androidmessenger.databinding.ActivityRegisterBinding;

import java.util.HashMap;

public class register extends AppCompatActivity {
    private ActivityRegisterBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityRegisterBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.singUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (binding.plainText1.getText().toString().isEmpty() ||binding.editText2.getText().toString().isEmpty()
                        || binding.editText3.getText().toString().isEmpty()){
                    Toast.makeText(getApplicationContext(),"Заполните поля", Toast.LENGTH_SHORT).show();
                }else {
                    FirebaseAuth.getInstance().createUserWithEmailAndPassword(binding.plainText1.getText().toString(), binding.editText3.getText().toString())
                            .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if (task.isSuccessful()){
                                        HashMap<String, String> userInfo = new HashMap<>();
                                        userInfo.put("email",binding.plainText1.getText().toString());
                                        userInfo.put("UserName",binding.editText2.getText().toString());
                                        FirebaseDatabase.getInstance().getReference().child("Users").child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                                .setValue(userInfo);
                                        startActivity(new Intent(register.this, MainActivity.class));

                                    }
                                }
                            });

                }
            }
        });
    }
}