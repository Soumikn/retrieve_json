package bibhas.com.retrieve_json;

import android.Manifest;
import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.KeyEvent;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final String URL = "https://api.myjson.com/bins/1a5vkk";
//    private static final String URL = "https://rlinfocommapk.000webhostapp.com/";

    List<Contact> contactList;

    RecyclerView recyclerView;

    MyAdapter adapter;

    ProgressDialog progressDialog;

    Toolbar toolbar;

    public boolean up,down;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = findViewById(R.id.main_page_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("JSON Fetch Data from URL");

        contactList = new ArrayList<>();


        recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        recyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));
        progressDialog = new ProgressDialog(this);


        loadInformation();


//        adapter=new MyAdapter(this,contactList);
//        recyclerView.setAdapter(adapter);
    }

    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_VOLUME_DOWN){
            down = true;
            Toast.makeText(getApplicationContext(),"Down..",Toast.LENGTH_SHORT).show();
        } else if(keyCode == KeyEvent.KEYCODE_VOLUME_UP){
            up = true;
            Toast.makeText(getApplicationContext(),"Up..",Toast.LENGTH_SHORT).show();
        }
        if(up && down) {
            // Two buttons pressed, call your function
        }
        return true;
    }

    public boolean onKeyUp(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_VOLUME_DOWN){
            down = false;
        } else if(keyCode == KeyEvent.KEYCODE_VOLUME_UP){
            up = false;
        }
        return true;
    }


    private void loadInformation() {
        progressDialog.setMessage("Loading....");
        progressDialog.show();

        StringRequest stringRequest = new StringRequest(Request.Method.GET, URL,
                new Response.Listener<String>() {


                    @Override
                    public void onResponse(String response) {
                        try {

                            JSONArray contacts = new JSONArray(response);

                            progressDialog.dismiss();
//                            Toast.makeText(getApplicationContext(), "Reached!!!", Toast.LENGTH_LONG).show();
                            for (int i = 0; i < contacts.length(); i++) {
                                JSONObject contactObject = contacts.getJSONObject(i);

                                String name = contactObject.getString("name");
                                String age = contactObject.getString("age");
                                String mob = contactObject.getString("mobile");
                                String home = "";
                                String home_no = "";



                                JSONArray jsonArray = contactObject.getJSONArray("address");
                                for (int j = 0; j < jsonArray.length(); j++) {
                                    JSONObject jsonObject = jsonArray.getJSONObject(j);


                                    home = jsonObject.getString("home");
                                    home_no = jsonObject.getString("home no");

                                }
                                Contact contact = new Contact(name, age, mob, home, home_no);
                                contactList.add(contact);
                            }
                            adapter = new MyAdapter(MainActivity.this, contactList);
                            recyclerView.setAdapter(adapter);
                            adapter.notifyDataSetChanged();
                        } catch (JSONException e) {

                            Log.d("XXXXX" ,e.getMessage());
                            e.printStackTrace();
                        }

                    }


                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                progressDialog.dismiss();
                Toast.makeText(MainActivity.this, "Error ", Toast.LENGTH_SHORT).show();
            }
        });
        Volley.newRequestQueue(this).add(stringRequest);


    }

}
