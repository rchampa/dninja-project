package com.example.demojuego;

import android.content.Context;
import android.media.MediaPlayer;
import android.util.Log;

public class WorkerThread implements Runnable {

	private MediaPlayer mp;
	private int id;
	private Context context;

    public WorkerThread(Context context, int resID){
        this.id = resID;
        this.context = context;
    }

    @Override
    public void run() {
    	Log.d("demo", Thread.currentThread().getName());
    	mp = MediaPlayer.create(context,id);
    	mp.start();
    	mp.release();
    }

}