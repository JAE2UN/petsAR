package com.detect.petsar;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.detect.petsar.env.Logger;

public class Real_MainActivity extends AppCompatActivity implements View.OnClickListener {

    private static final Logger LOGGER = new Logger();
    private String pet_type, pet_kind;
    private TextView petType, petKind;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_real__main);

        Button wiki = (Button) findViewById(R.id.btnAR);
        Button tflite = (Button) findViewById(R.id.btnDetect);
        Button productList = (Button) findViewById(R.id.btnList);

        petType = (TextView) findViewById(R.id.petType);
        petKind = (TextView) findViewById(R.id.petKind);


        wiki.setOnClickListener(this);
        tflite.setOnClickListener(this);
        productList.setOnClickListener(this);
    }


    @Override
    public void onClick(View view) {
        Intent intent = null;
        if (view.getId() == R.id.btnAR) {
            intent = new Intent(getApplicationContext(), PetArActivity.class);
            intent.putExtra("pet_type", pet_type);
            startActivity(intent);
        } else if (view.getId() == R.id.btnDetect) {
            intent = new Intent(getApplicationContext(), DetectorActivity.class);
            startActivity(intent);
        } else if (view.getId() == R.id.btnList) {
            intent = new Intent(getApplicationContext(), ListActivity.class);
            intent.putExtra("pet_type", pet_type);
            startActivity(intent);
        }
    }

    @Override
    protected void onResume(){
        super.onResume();
        Intent intent = getIntent();
        try {
            pet_type = intent.getExtras().getString("pet_type");
            pet_kind = intent.getExtras().getString("pet_kind");
            petType.setText(pet_type);
            petKind.setText(pet_kind);
        } catch (final Exception e) {
            LOGGER.e(e, "Couldn't fonund type and kind of pet");
        }
    }

    @Override
    protected void onDestroy(){
        super.onDestroy();
    }

    @Override
    protected void onPause(){
        super.onPause();
    }
}