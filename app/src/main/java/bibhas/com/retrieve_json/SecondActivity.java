package bibhas.com.retrieve_json;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.TextView;

public class SecondActivity extends AppCompatActivity {

    TextView name2;
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);



        name2 = findViewById(R.id.name1);

        Intent intent = getIntent();
        String name1 = intent.getStringExtra("name");

//        name2.setText(name1);
        toolbar=findViewById(R.id.second_page_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(name1);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    }
}
