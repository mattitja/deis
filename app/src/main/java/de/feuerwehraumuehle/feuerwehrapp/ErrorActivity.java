package de.feuerwehraumuehle.feuerwehrapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import de.feuerwehraumuehle.feuerwehrapp.helper.Utils;

public class ErrorActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_empty);
        Utils.setStatusBarColor(this);

        String msg = getIntent().getStringExtra("msg");
        String stacktrace = getIntent().getStringExtra("stacktrace");
        TextView tv = (TextView) findViewById(R.id.msg);
        tv.setText(msg);

        TextView stacktraceTv = (TextView) findViewById(R.id.stacktrace);
        stacktraceTv.setText(stacktrace);
    }
}
