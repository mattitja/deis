package de.feuerwehraumuehle.feuerwehrapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

public class EmptyActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_empty);

        String msg = getIntent().getStringExtra("msg");
        TextView tv = (TextView) findViewById(R.id.msg);
        tv.setText(msg);
    }
}
