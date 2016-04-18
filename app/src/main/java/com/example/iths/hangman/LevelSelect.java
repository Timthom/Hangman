package com.example.iths.hangman;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

public class LevelSelect extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_level_select);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setLogo(R.drawable.iconhangman);
        getSupportActionBar().setDisplayUseLogoEnabled(true);
        setContentView(R.layout.activity_level_select);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_my, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();


        switch (item.getItemId()) {
            case R.id.action_play:
                // Play action
                Intent i = new Intent(LevelSelect.this, LevelSelect.class);
                startActivity(i);
                return true;
            case R.id.info:
                // My presentation activity
                Intent j = new Intent(LevelSelect.this, Info.class);
                startActivity(j);
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }


    public void easyStart(android.view.View view) {
        Intent intent = new Intent(this, Play.class);
        intent.putExtra("level", 0);
        this.startActivity(intent);
    }


    public void hardStart(android.view.View view) {
        Intent intent = new Intent(this, Play.class);
        intent.putExtra("level", 1);
        this.startActivity(intent);
    }


    public void expertStart(android.view.View view) {
        Intent intent = new Intent(this, Play.class);
        intent.putExtra("level", 2);
        this.startActivity(intent);
    }
}
