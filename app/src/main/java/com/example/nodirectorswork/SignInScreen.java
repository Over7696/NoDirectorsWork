package com.example.nodirectorswork;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class SignInScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in_screen);
    }
    public void toMainScreen(View view) {
        String login = ((TextView)findViewById(R.id.editTextTextEmailAddress)).getText().toString().trim();
        String password = ((TextView)findViewById(R.id.editTextTextPassword)).getText().toString().trim();

        JSONObject json = new JSONObject();
        try {
            json.put("email", login);
            json.put("password", password);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        String url = "https://food.madskill.ru/auth/login";

        RequestQueue requestQueue = Volley.newRequestQueue(SignInScreen.this);

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, url, json,
                response -> startActivity(new Intent(SignInScreen.this, MainScreen.class)),
                error -> {
                    Toast.makeText(this, "Неверные данные авторизации", Toast.LENGTH_LONG).show();
                });
        requestQueue.add(request);




    }

    public void toMessage(View view) {
        Toast.makeText(this,"Быстро вспомнил и заказал еду!",Toast.LENGTH_LONG).show();
        return;
    }

}



