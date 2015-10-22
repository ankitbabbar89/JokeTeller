package com.ankitb.joketeller.main;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.ankitb.joketeller.showjoke.ShowJokeActivity;
import com.ankitb.joketeller.getjoke.Joker;



public class MainActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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

    public void tellJoke(View view){
        new FetchJokeTask().execute(MainActivity.this);

//        Joker joker = new Joker();
//        String joke = joker.getJoke();
//       // Toast.makeText(this, joke, Toast.LENGTH_SHORT).show();
//        Intent intent = new Intent(this, ShowJokeActivity.class);
//        intent.putExtra("KEY_JOKE",joke);
//        startActivity(intent);
    }


}
