package com.example.app_api;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private RequestQueue requestQueue;
    private List<ProgramLanguages> programLanguagesList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerview);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        requestQueue = VolleySingleton.getInstance(this).getRequestQueue();

        fetchLangauges();
    }

    private void fetchLangauges() {

        String url = "https://ewinsutriandi.github.io/mockapi/bahasa_pemrograman.json";

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {

                for (int i = 0; i < response.length(); i++) {
                    try {
                        JSONObject jsonObject = response.getJSONObject(i);
                        String title = jsonObject.getString("title");
                        String desc = jsonObject.getString("description");
                        String logo = jsonObject.getString("logo");
                        String exmpl = jsonObject.getString("hello_world");
                        String url = jsonObject.getString("read_more");

                        ProgramLanguages programLanguages = new ProgramLanguages(title, logo, desc, exmpl, url);
                        programLanguagesList.add(programLanguages);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                    LangaugeAdapter adapter = new LangaugeAdapter(MainActivity.this, programLanguagesList);

                    recyclerView.setAdapter(adapter);
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(MainActivity.this, error.getMessage(), Toast.LENGTH_SHORT).show();
            } // onError
        });

        requestQueue.add(jsonArrayRequest);
    } // fectLanguage
} // block 1