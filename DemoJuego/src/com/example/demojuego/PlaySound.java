package com.example.demojuego;

import android.media.MediaPlayer;

public class PlaySound {

	public void playSound(final MediaPlayer mp){
		
		Runnable tarea = new Runnable() {
			
			@Override
			public void run() {
				
				mp.start();
			}
		};
		
		new Thread(tarea).start();
		
	}

}
