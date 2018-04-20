package com.markzhengma.android.lifommunity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

public class FullPostActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_full_post);
    }

    //inflates the xml file
    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menu_main, menu);
//      return true;
        return super.onCreateOptionsMenu(menu);
    }

    //onOptionsItemSelected() handles menu item actions. Uses switch statement
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.share:
                Toast.makeText(this, "Shared", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.email:
                Toast.makeText(this,"Send email", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.Home_action:
                Intent homeIntent = new Intent(this, MainActivity.class);
                startActivity(homeIntent);
                return true;
            case R.id.Post_action:
                Intent postIntent = new Intent(this, PostActivity.class);
                startActivity(postIntent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}


