package com.example.assign1;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class Final_result extends AppCompatActivity {
    TextView tvScore;
    TextView tvName;
    Button btnShare;
    Button btnHome;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_final_result);
        init();
        Intent i = getIntent();
        String name = i.getStringExtra("name");
        int result = i.getIntExtra("sum",0);
        tvScore.setText(result+"/10");
        tvName.setText(name);

        btnHome.setOnClickListener((v)->{
                Intent intent = new Intent(Final_result.this, com.example.assign1.home.class);
            startActivity(intent);
            finish();
        });

        btnShare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_SEND);
                intent.setType("text/plain");
                intent.putExtra(Intent.EXTRA_TEXT, "Quiz Score: " + result);
                Intent chooser = Intent.createChooser(intent, "Share Score via...");
                startActivity(chooser);
            }
        });
    }

    private void init(){
        tvScore = findViewById(R.id.tvScore);
        tvName = findViewById(R.id.tvName);
        btnHome = findViewById(R.id.btnPrev);
        btnShare = findViewById(R.id.btnShare);
    }
}
