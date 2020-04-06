package com.nostsa.androidproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.ListView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity  {



    private static final String TAG = MainActivity.class.getSimpleName();

    private static final String url = "https://jsonplaceholder.typicode.com/users";
    private JsonArrayRequest mJsonArrayRequest;
    private RequestQueue mRequestQueue;
    private List<User> listUser;
    private List<Address> listAddress;
    private List<Geo> listGeo;
    private List<Company> listCompany;
    private RecyclerView recyclerview;
    private JSONObject jsonObject = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        listUser = new ArrayList<>();
        listAddress = new ArrayList<>();
        listCompany = new ArrayList<>();
        listGeo = new ArrayList<>();



        recyclerview = findViewById(R.id.recycleviewid);
        
        jsonrequest();


    }

    private void jsonrequest() {
        mJsonArrayRequest = new JsonArrayRequest(url, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {

                for(int i = 0; i < response.length(); i++){

                    try {

                        setJSONObjects(response, i);


                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }


                setuprecycleview(listUser, listAddress, listCompany, listGeo);


                }
            }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });


        mRequestQueue= Volley.newRequestQueue(MainActivity.this);
        mRequestQueue.add(mJsonArrayRequest);


    }

    private void setuprecycleview(List<User> listUser, List<Address> listAddress, List<Company> listCompany, List<Geo> listGeo) {

        RecycleViewAdapter myadapter= new RecycleViewAdapter(this, listUser, listAddress, listCompany, listGeo);
        recyclerview.setLayoutManager(new LinearLayoutManager(this));



        recyclerview.setAdapter(myadapter);

    }

    private void setJSONObjects(JSONArray response, int i) throws JSONException {
        jsonObject = response.getJSONObject(i);


        User mUser=new User();
        mUser.setId(jsonObject.getInt("id"));
        mUser.setName(jsonObject.getString("name"));
        mUser.setUsername(jsonObject.getString("username"));
        mUser.setEmail(jsonObject.getString("email"));
        mUser.setPhone(jsonObject.getString("phone"));
        mUser.setWebsite(jsonObject.getString("website"));


        JSONObject address = jsonObject.getJSONObject("address");
        JSONObject geo = address.getJSONObject("geo");

        Geo mGeo = new Geo();
        mGeo.setLat(geo.getLong("lat"));
        mGeo.setLng(geo.getLong("lng"));

        Address mAddress = new Address();
        mAddress.lat = mGeo.lat;
        mAddress.lng = mGeo.lng;
        mAddress.setStreet(address.getString("street"));
        mAddress.setSuite(address.getString("suite"));
        mAddress.setCity(address.getString("city"));
        mAddress.setZipcode(address.getString("zipcode"));



        JSONObject company = jsonObject.getJSONObject("company");

        Company mCompany = new Company();
        mCompany.setName(company.getString("name"));
        mCompany.setCatchPhrase(company.getString("catchPhrase"));
        mCompany.setBs(company.getString("bs"));


        mUser.setAddress(mAddress);
        mUser.setCompany(mCompany);



        listUser.add(mUser);
        listAddress.add(mAddress);
        listCompany.add(mCompany);
        listGeo.add(mGeo);

    }
}




