package com.example.animation;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.motion.widget.MotionLayout;
import androidx.core.app.ActivityCompat;
import androidx.core.app.ActivityOptionsCompat;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    Adapter adapter;
    ImageView imageView;
    Button btn;

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.recyclerView);
        adapter = new Adapter();
        recyclerView.setAdapter(adapter);
        adapter.setListener((view) -> {
            Intent intent = new Intent(MainActivity.this,MainActivity2.class);
            String transitionName = getString ( R.string.transition_string);
            View viewStart = findViewById (R.id.image_view);
            ActivityOptionsCompat optionsCompat =

                    ActivityOptionsCompat. makeSceneTransitionAnimation ( this ,
                            view ,
                            transitionName
                    );
            // Запускаем Intent
            ActivityCompat. startActivity ( this , intent, optionsCompat.toBundle ());
            startActivity(intent);
        });
        fill();

    }

    public void fill(){
        ArrayList<String> strings = new ArrayList<>();
        for (int i = 0; i < 10 ; i++) {
            strings.add("Hello " + i);
        }
        adapter.setStrings(strings);
    }

}