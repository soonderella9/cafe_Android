package com.example.gginiggini.Activity;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.Toast;

import com.example.gginiggini.R;

public class StarPopUp extends Activity{
    private Button scoreRegister;
    private Button cancel;
    private Context context;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_star_pop_up);
        scoreRegister = (Button) findViewById(R.id.button1);
        cancel = (Button) findViewById(R.id.button2);
        scoreRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                Toast.makeText(StarPopUp.this , "별점이 반영되었습니다", Toast.LENGTH_SHORT).show();
            }
        });
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
