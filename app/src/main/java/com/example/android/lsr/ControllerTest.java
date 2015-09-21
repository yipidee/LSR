package com.example.android.lsr;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;

/* Test Activity to confirm behaviour of analogue controller.
 *
 * Of no particular interest, will not feature in final App.
 */

public class ControllerTest extends AppCompatActivity implements View.OnTouchListener{

    TextView tv;
    AnalogueController controller;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_controller_test);
        tv = (TextView)findViewById(R.id.textview);
        controller = (AnalogueController)findViewById(R.id.controller);
        controller.setOnTouchListener(this);

        tv.setText(Vector2D.ZERO.toString());
    }

    public boolean onTouch(View v, MotionEvent e){
        boolean res=false;
        switch(v.getId()){
            case R.id.controller:
                controller.onTouch(e);
                tv.setText(controller.getOutput().toString());
                res=true;
                break;
        }
        return res;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_controller_test, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
