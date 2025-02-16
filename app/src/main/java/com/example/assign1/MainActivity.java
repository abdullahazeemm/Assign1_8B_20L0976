package com.example.assign1;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    Animation top_to_bottom, bottom_to_top;
    ImageView ivLogo;
    TextView tagline;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        tagline = findViewById(R.id.tagline);
        ivLogo = findViewById(R.id.logo);

        top_to_bottom = AnimationUtils.loadAnimation(this, R.anim.top_to_bottom);
        ivLogo.setAnimation(top_to_bottom);

        new Handler()
                .postDelayed(()->{
                    startActivity(new Intent(MainActivity.this, home.class));
                    finish();
                },  3000);

    }
}