package com.example.sepiatask;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;


public class PetDetailsActivity extends AppCompatActivity {
    ImageView imageView;
    TextView title, description, dateAdded;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       setContentView(R.layout.activity_pet_details);
       initView();
       title.setText(getIntent().getStringExtra("title"));
       description.setText(getIntent().getStringExtra("content_url"));
       dateAdded.setText(getIntent().getStringExtra("date_added"));
        Glide.with(this)
                .load(getIntent().getStringExtra("image_url"))
                .centerInside()
                .into(imageView);
    }

    private void initView() {
        imageView = findViewById(R.id.detailPetImage);
        title =findViewById(R.id.title);
        description = findViewById(R.id.contentDescription);
        dateAdded =findViewById(R.id.dateAdded);
    }
}