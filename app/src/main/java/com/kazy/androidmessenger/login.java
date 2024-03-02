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
import com.kazy.androidmessenger.databinding.ActivityLogin2Binding;

public class login extends AppCompatActivity {

    private ActivityLogin2Binding binding;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLogin2Binding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (binding.login.getText().toString().isEmpty() || binding.password.getText().toString().isEmpty()){
                    Toast.makeText(getApplicationContext(),"Заполните поля", Toast.LENGTH_SHORT).show();
                } else {
                    FirebaseAuth.getInstance().signInWithEmailAndPassword(binding.login.getText().toString(), binding.password.getText().toString())
                            .addOnCompleteListener(new OnCompleteListener<AuthResult>()
                            {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {

                                    if (task.isSuccessful()){
                                        startActivity(new Intent(login.this, MainActivity.class));
                                        binding.login.setText("");
                                        binding.password.setText("");
                                    }else {
                                        Toast.makeText(getApplicationContext(),"Введите данные провильно", Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });
                }
            }
        });
    }
    public void newActivity(View view){
        startActivity(new Intent(this, register.class));
    }

}