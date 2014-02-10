package com.example.deadreckoning;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import Handlers.TTSHandler;

public class HelpActivity extends Activity {
	
	private Button buttonReturn; 
	private TTSHandler tts;
	private TextView helpText;
	@Override
	protected void onCreate(Bundle savedInstanceState) { 
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_help);
		
		buttonReturn = (Button)findViewById(R.id.button4);
		helpText = (TextView)findViewById(R.id.textViewHelp);
		tts = new TTSHandler(this);
		
		Thread speechTimer = new Thread() {
			public void run() {
					try{
						sleep(1000);
						tts.speakPhrase(helpText.getText().toString());
					}  
					catch(Exception e){
						e.printStackTrace();
					}
					finally{
						
					}
			}
		};
		speechTimer.start();
		
		buttonReturn.setOnClickListener(new OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				tts.speakPhrase(buttonReturn.getText().toString());
				tts.shutDownTTS();
				startActivity(new Intent(HelpActivity.this, MainActivity.class));
			}
		});
		
		buttonReturn.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) 
            {
            	tts.speakPhrase(buttonReturn.getText().toString());
                return false;
            }
        });		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	@Override
	protected void onDestroy()
	{
		Log.i("ss12", "on destroy");
		tts.shutDownTTS();
		super.onDestroy();
	}
}
