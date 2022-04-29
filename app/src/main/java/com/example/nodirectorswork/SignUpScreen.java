package com.example.nodirectorswork;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.HttpHeaderParser;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

public class SignUpScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up_screen);
    }

    public void toMainScreen(View view) {
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        String email = ((TextView)findViewById(R.id.editTextTextEmailAddress)).getText().toString().trim();
        String passwordFirst = ((TextView)findViewById(R.id.editTextTextPassword)).getText().toString().trim();
        String lastname = ((TextView)findViewById(R.id.editTextTextEmailAddress2)).getText().toString().trim();
        String phone = ((TextView)findViewById(R.id.editTextTextEmailAddress3)).getText().toString().trim();


        if (email.isEmpty() || passwordFirst.isEmpty()  || lastname.isEmpty() || phone.isEmpty()){
            Toast.makeText(this,"Не все поля заполнены",Toast.LENGTH_LONG).show();
            return;
        }

        if(!email.matches("^(?=.{1,64}@)[A-Za-z0-9_-]+(\\\\.[A-Za-z0-9_-]+)*@[^-][A-Za-z0-9-]+(\\\\.[A-Za-z0-9-]+)*(.[A-Za-z]{2,})$"))
        {
            Toast.makeText(this,"Некорректный адрес электронной почты",Toast.LENGTH_LONG).show();
            return;
        }

        String url = "https://food.madskill.ru/auth/register";
        JSONObject json = new JSONObject();

        try {
            json.put("email", email);
            json.put("password", passwordFirst);
            json.put("login", lastname);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        Intent intent = new Intent(this, MainScreen.class);

        final String requestBody = json.toString();
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.i("VOLLEY", response);
                startActivity(intent);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("VOLLEY", error.toString());
            }
        }) {
            @Override
            public String getBodyContentType() {
                return "application/json; charset=utf-8";
            }

            @Override
            public byte[] getBody() throws AuthFailureError {
                try {
                    return requestBody == null ? null : requestBody.getBytes("utf-8");
                } catch (UnsupportedEncodingException uee) {
                    VolleyLog.wtf("Unsupported Encoding while trying to get the bytes of %s using %s", requestBody, "utf-8");
                    return null;
                }
            }

            @Override
            protected Response<String> parseNetworkResponse(NetworkResponse response) {
                String responseString = "";
                if (response != null) {
                    responseString = String.valueOf(response.statusCode);
                    // can get more details such as response.headers
                }
                return Response.success(responseString, HttpHeaderParser.parseCacheHeaders(response));
            }
        };
        requestQueue.add(stringRequest);
    }

    public void Cancel(View view) {
        Intent intent6 = new Intent(this,OnBordingScreen2.class);
        startActivity(intent6);
    }
}


