package com.example.jonathandorvilier.dondesang.model;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import com.example.jonathandorvilier.dondesang.MainActivity;
import com.example.jonathandorvilier.dondesang.R;

/**
 * Created by Jonathan Dorvilier on 9/1/2017.
 */

public class SplashScreen extends Activity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash);

        Thread timerThread = new Thread(){
            public void run(){
                try{
                    sleep(3500);
                }catch(InterruptedException e){
                    e.printStackTrace();
                }finally{
                    Intent intent = new Intent(SplashScreen.this,MainActivity.class);
                    startActivity(intent);
                }
            }
        };
        timerThread.start();
    }

    @Override
    protected void onPause() {
        // TODO Auto-generated method stub
        super.onPause();
        finish();
    }
}
