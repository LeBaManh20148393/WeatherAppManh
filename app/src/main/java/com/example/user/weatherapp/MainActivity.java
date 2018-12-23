package com.example.user.weatherapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.json.JSONException;
import org.json.JSONObject;


public class MainActivity extends AppCompatActivity {
    EditText EdSearch;
    Button BtnOK, NextDay;
    TextView City,Country,Status, Humidity,Cloud,Wild, Days;
    ImageView imgIcon;
    DatabaseReference mData;
    ToaDo toaDo;
    String kinhdo;
    String vido;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        addView();
        toaDo = new ToaDo();
        //test firebase
//        FirebaseDatabase database = FirebaseDatabase.getInstance();
//        DatabaseReference myRef = database.getReference("message");
//        myRef.setValue("Hello, World!");


        BtnOK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String city = EdSearch.getText().toString();
                GetCurrentWeatherData(city);

            }
        });
    }
    public void GetCurrentWeatherData(String data){
        RequestQueue requestQueue = Volley.newRequestQueue(MainActivity.this);
        String url = "https://api.openweathermap.org/data/2.5/weather?q="+data+"&units=metric&appid=84edacfb80ba6ea8e4aa8d1c20c4e561";
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            Log.d("ketqua",response);
                            JSONObject jsonObject = new JSONObject(response);
                           JSONObject jsonObjectcoord = jsonObject.getJSONObject("coord");
                             kinhdo = jsonObjectcoord.getString("lon");
                             vido = jsonObjectcoord.getString("lat");
                            Toast.makeText(MainActivity.this,kinhdo,Toast.LENGTH_SHORT).show();
                            Toast.makeText(MainActivity.this,vido,Toast.LENGTH_SHORT).show();
                            mData = FirebaseDatabase.getInstance().getReference();
                            toaDo.setKinhDo(kinhdo);
                            toaDo.setViDo(vido);
                            mData.child("Tọa Độ").setValue(toaDo);

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                });
        requestQueue.add(stringRequest);
    }

    private void addView() {
        EdSearch = (EditText)findViewById(R.id.edtext_search);
        BtnOK = (Button)findViewById(R.id.btn_search);
        NextDay = (Button)findViewById(R.id.btn_nextday);
        City = (TextView)findViewById(R.id.tv_nameCity);
        Country = (TextView)findViewById(R.id.tv_nameCountry);
        Status = (TextView)findViewById(R.id.tv_status);
        Humidity = (TextView)findViewById(R.id.tv_humidity);
        Cloud = (TextView)findViewById(R.id.tv_cloud);
        Wild = (TextView)findViewById(R.id.tv_wildmill);
        Days = (TextView)findViewById(R.id.tv_day);
        imgIcon = (ImageView) findViewById(R.id.image_icon);
    }
}
