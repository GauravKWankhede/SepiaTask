package com.example.sepiatask;


import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initVar();
        String response = loadJsonFromAssets();
        String workingHours = getWorkingHours();
        String TAG = "MainActivity";
        Log.e(TAG, response);
        Log.e(TAG, workingHours);
        Calendar calendar = Calendar.getInstance();
        Log.e(TAG, String.valueOf("09:00".compareTo(calendar.get(Calendar.HOUR_OF_DAY)+":"+calendar.get(Calendar.MINUTE))<=0 && "18:00".compareTo(calendar.get(Calendar.HOUR_OF_DAY)+":"+calendar.get(Calendar.MINUTE))>=0 && calendar.get(Calendar.DAY_OF_WEEK)>1 && calendar.get(Calendar.DAY_OF_WEEK)<7 ) );
        if ("09:00".compareTo(calendar.get(Calendar.HOUR_OF_DAY)+":"+calendar.get(Calendar.MINUTE))<=0
                && "18:00".compareTo(calendar.get(Calendar.HOUR_OF_DAY)+":"+calendar.get(Calendar.MINUTE))>=0
                && calendar.get(Calendar.DAY_OF_WEEK)>1
                && calendar.get(Calendar.DAY_OF_WEEK)<7 ){
            recyclerView.setVisibility(View.VISIBLE);
        }
        else{
            textView.setVisibility(View.VISIBLE);
        }

        recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
        recyclerView.setAdapter(new PetAdapter(MainActivity.this, getListOfPets()));

    }

    private void initVar() {
        recyclerView =  findViewById(R.id.petRecyclerView);
        textView = findViewById(R.id.textView);
    }

    private String getWorkingHours() {
        String json = null;
        try {
            InputStream inputStream = getAssets().open("config.json");
            int size = inputStream.available();
            byte[] buffer = new byte[size];
            inputStream.read(buffer);
            inputStream.close();
            json = new String(buffer, StandardCharsets.UTF_8);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
        return json;
    }

    public String loadJsonFromAssets(){
        String json = null;
    try {
        InputStream inputStream = getAssets().open("pets_list.json");
        int size = inputStream.available();
        byte[] buffer = new byte[size];
        inputStream.read(buffer);
        inputStream.close();
        json = new String(buffer, StandardCharsets.UTF_8);
    } catch (IOException e) {
        e.printStackTrace();
        return null;
    }
    return json;
}

public ArrayList<PetInfo> getListOfPets(){
        ArrayList<PetInfo> petList = new ArrayList<>();
    try {
        JSONObject object = new JSONObject(loadJsonFromAssets());
        JSONArray array = object.getJSONArray("pets");

        for (int i=0; i<array.length();i++){
            JSONObject pet = array.getJSONObject(i);
            String image_url = pet.getString("image_url");
            String title = pet.getString("title");
            String content_url = pet.getString("content_url");
            String date_added = pet.getString("date_added");

            petList.add(new PetInfo(image_url,title,content_url,date_added));
        }
    } catch (JSONException e) {
        e.printStackTrace();
    }
    return petList;
}

}