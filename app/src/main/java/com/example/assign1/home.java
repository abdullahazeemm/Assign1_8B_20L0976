package com.example.assign1;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class home extends AppCompatActivity {

    EditText etName;
    Button btnStart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_home);
        init();

        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = etName.getText().toString().trim();
                if (name.isEmpty()){
                    etName.setError("Name is Empty");
                } else{
                    Intent intent = new Intent(home.this, com.example.assign1.Quiz_screen.class);
                    intent.putExtra("name",name);
                    startActivity(intent);
                    finish();
                }
            }
        });
    }

    private void init(){
        etName = findViewById(R.id.etName);
        btnStart = findViewById(R.id.btnStart);
    }
}