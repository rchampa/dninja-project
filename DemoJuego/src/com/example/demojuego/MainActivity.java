package com.example.demojuego;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.media.AudioManager;
import android.media.SoundPool;
import android.media.SoundPool.OnLoadCompleteListener;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.webkit.WebView;
import android.widget.Toast;

public class MainActivity extends Activity {
	
	private SoundPool soundPool;
	private int[] sounds = {R.raw.green,R.raw.red,R.raw.yellow,R.raw.blue};
	private boolean[] loaded = { false, false, false, false};
	private int[] soundIDs = { 0,0,0,0};
	
	WebView myBrowser;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		
		
		myBrowser = (WebView)findViewById(R.id.mybrowser);
		
		soundPool = new SoundPool(4, AudioManager.STREAM_MUSIC, 0);
		
		final MyJavaScriptInterface myJavaScriptInterface = new MyJavaScriptInterface(this);
	    myBrowser.addJavascriptInterface(myJavaScriptInterface, "AndroidFunction");
	    
	    myBrowser.getSettings().setJavaScriptEnabled(true);  
	    myBrowser.getSettings().setBuiltInZoomControls(false);
	    myBrowser.loadUrl("file:///android_asset/index.html");
	}
	
	@Override
	public void onResume(){
		new MyAsyncTask().execute();
		super.onResume();
	}

	
	public class MyJavaScriptInterface {
		Context mContext;

	    MyJavaScriptInterface(Context c) {
	        mContext = c;
	    }
	    
	    public void showToast(String toast){
	        Toast.makeText(mContext, toast, Toast.LENGTH_SHORT).show();
	    }
	    
	    public void onClickButton(int color){
	    	//Log.d("demo", ""+color);
	    	
	    	AudioManager audioManager = (AudioManager) getSystemService(AUDIO_SERVICE);
	        float actualVolume = (float) audioManager.getStreamVolume(AudioManager.STREAM_MUSIC);
	        float maxVolume = (float) audioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC);
	        float volume = actualVolume / maxVolume;
	 
	    	
	    	switch(color){
	    		case 1: 
	    			//worker = new WorkerThread(mContext,R.raw.green);
	    			soundPool.play(soundIDs[0], volume, volume, 1, 0, 1f);
	    			break;
	    		case 2: 
	    			//worker = new WorkerThread(mContext,R.raw.red);
	    			soundPool.play(soundIDs[1], volume, volume, 1, 0, 1f);
	    			break;
	    		case 3: 
	    			//worker = new WorkerThread(mContext,R.raw.yellow);
	    			soundPool.play(soundIDs[2], volume, volume, 1, 0, 1f);
	    			break;
	    		case 4: 
	    			//worker = new WorkerThread(mContext,R.raw.blue);
	    			soundPool.play(soundIDs[3], volume, volume, 1, 0, 1f);
	    			break;
	    		
	    	}
	    	
	    }

	}
	
	
	private class MyAsyncTask extends AsyncTask<Integer, Integer, Integer>{
	
		ProgressDialog progressDialog;
		Context context;
		@Override
    	protected void onPreExecute() {
			context = MainActivity.this;
    		progressDialog = new ProgressDialog(context);
    		progressDialog.setMessage("Loading sounds....");
    		progressDialog.setIndeterminate(false);
    		progressDialog.setCancelable(false);
    		progressDialog.show();
    	}

	   @Override
	   protected Integer doInBackground(Integer... params) {
	       
			soundPool.setOnLoadCompleteListener(new OnLoadCompleteListener() {
						    @Override
						    public void onLoadComplete(SoundPool soundPool, int sampleId, int status) {
						    	
						    	if(soundIDs[0]==sampleId)
						    		loaded[0]=true;
						    	else if(soundIDs[1]==sampleId)
						    		loaded[1]=true;
						    	else if(soundIDs[2]==sampleId)
						    		loaded[2]=true;
						    	else if(soundIDs[3]==sampleId)
						    		loaded[3]=true;
						    	
						    }
						});
			soundIDs[0]=soundPool.load(context, sounds[0], 1);
	        soundIDs[1]=soundPool.load(context, sounds[1], 1);
	        soundIDs[2]=soundPool.load(context, sounds[2], 1);
	        soundIDs[3]=soundPool.load(context, sounds[3], 1);
			
			// New Code so we wait until the load is complete
			while( !(loaded[0] && loaded[1] && loaded[2] && loaded[3]) ) {
			    try {
					Thread.sleep(200);
				} catch (InterruptedException e) {
					e.printStackTrace();
				} // 0.2 second (change as you feel fit)
			}
			return 0;
	   }

	   @Override
	   protected void onPostExecute(Integer result) {
		   progressDialog.dismiss();// ocultamos progess dialog.
	   }
	}
}
