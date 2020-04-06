package com.nostsa.androidproject;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;

public class DetailssActivity extends AppCompatActivity {

    int id;
    float lat, lng;
    String name, username, email, phone, website, street, suite, city, zipcode, company_name, company_catchPhrase, company_bs;
    EditText et_name, et_username, et_email, et_phone, et_website, et_street, et_suite, et_city, et_zipcode, et_lat, et_lng, et_company_name, et_company_catchPhrase, et_company_bs;
    Button btn;
    JSONObject postData = new JSONObject();

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detailss);

       initValues();

       initEditTexts();

       btn = (Button) findViewById(R.id.btn);

       btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {

                  putJSONObjects();

                    new SendDeviceDetails().execute("https://jsonplaceholder.typicode.com/users", postData.toString());

                } catch (JSONException e) {

                    e.printStackTrace();

                }
            }
        });

    }

    private String getString(String s){
        return getIntent().getExtras().getString(s);
    }

    private float getFloat(String v){
        return getIntent().getExtras().getFloat(v);
    }

    private void initValues(){
        id = getIntent().getExtras().getInt("user_id");
        name = getString("user_name");
        username = getString("user_username");
        email = getString("user_email");
        phone = getString("user_phone");
        website = getString("user_website");

        street = getString("address_street");
        suite = getString("address_suite");
        city = getString("address_city");
        zipcode = getString("address_zipcode");

        lat = getFloat("geo_lat");
        lng = getFloat("geo_lng");

        company_name = getString("company_name");
        company_catchPhrase = getString("company_catchPhrase");
        company_bs = getString("company_bs");
    }

    private void initEditTexts(){

        et_name = findViewById(R.id.name);
        et_username = findViewById(R.id.username);
        et_email = findViewById(R.id.email);
        et_phone = findViewById(R.id.phone);
        et_website = findViewById(R.id.website);


        et_street = findViewById(R.id.street);
        et_suite = findViewById(R.id.suite);
        et_city = findViewById(R.id.city);
        et_zipcode = findViewById(R.id.zipcode);


        et_lat = findViewById(R.id.lat);
        et_lng = findViewById(R.id.lang);

        et_company_name = findViewById(R.id.CompanyName);
        et_company_catchPhrase = findViewById(R.id.catchPhrase);
        et_company_bs = findViewById(R.id.bs);


        et_name.setText(name);
        et_username.setText(username);
        et_email.setText(email);
        et_phone.setText(phone);
        et_website.setText(website);
        et_street.setText(street);
        et_suite.setText(suite);
        et_city.setText(city);
        et_zipcode.setText(zipcode);

        et_company_name.setText(company_name);
        et_company_catchPhrase.setText(company_catchPhrase);
        et_company_bs.setText(company_bs);


        String string_lat = Float.toString(lat);
        String string_lng = Float.toString(lng);
        et_lat.setText(string_lat);
        et_lng.setText(string_lng);
    }

    private void putJSONObjects() throws JSONException {

        postData.put("name", makeEditTextToString(et_name));
        postData.put("username", makeEditTextToString(et_username));
        postData.put("email",  makeEditTextToString(et_email));
        postData.put("phone", makeEditTextToString(et_phone));
        postData.put("website", makeEditTextToString(et_website));

        postData.put("street", makeEditTextToString(et_street));
        postData.put("suite", makeEditTextToString(et_suite));
        postData.put("city", makeEditTextToString(et_city));
        postData.put("zippcode", makeEditTextToString(et_zipcode));

        postData.put("lat", makeEditTextToString(et_lat));
        postData.put("lng", makeEditTextToString(et_lng));

        postData.put("company name", makeEditTextToString(et_company_name));
        postData.put("catchPhrase", makeEditTextToString(et_company_catchPhrase));
        postData.put("bs", makeEditTextToString(et_company_bs));
    }

    private String makeEditTextToString(EditText e){
        return e.getText().toString();
    }

}
