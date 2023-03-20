package com.detect.petsar;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.detect.petsar.env.Logger;

import java.util.ArrayList;

public class ListActivity extends AppCompatActivity {

    private static final Logger LOGGER = new Logger();
    private ArrayList<ProductItem> data = null; // 목록
    private String pet_type;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        Intent intent = getIntent();
        try {
            pet_type = intent.getExtras().getString("pet_type");
            LOGGER.i("pet type : %s", pet_type);
        } catch (final Exception e) {
            LOGGER.e(e, "Couldn't fonund type and kind of pet");
        }

        ListView list = (ListView) findViewById(R.id.list01);

        data = new ArrayList<>();
        ProductItem product1 = new ProductItem(R.drawable.p1, "상품01", "10,000원");
        ProductItem product2 = new ProductItem(R.drawable.p2, "상품02", "7,000원");
        data.add(product1);
        data.add(product2);

        ProductAdapter adapter = new ProductAdapter(this, R.layout.list_item, data);
        list.setAdapter(adapter);
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getApplicationContext(), ProductActivity.class);

                intent.putExtra("img", Integer.toString(data.get(position).getImg()));
                intent.putExtra("title", data.get(position).getTitle());
                intent.putExtra("price", data.get(position).getPrice());

                startActivity(intent);
            }
        });
    }

    public void onClick(View v){
    }
}