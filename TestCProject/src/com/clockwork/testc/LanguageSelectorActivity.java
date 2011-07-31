package com.clockwork.testc;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;

public class LanguageSelectorActivity extends Activity
{
	private final String LOCALIZATION_DATA_LABEL = "com.visionbetalabs.client.language";
	
	public void onCreate(Bundle savedInstanceState) 
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.languageselector);    
        setComponentListeners();
    }
	
	private void setComponentListeners()
	{
		View language1 = findViewById(R.id.language_en);
		language1.setOnClickListener(new LanguageOnClickListener());
		View language2 = findViewById(R.id.language_tr);
		language2.setOnClickListener(new LanguageOnClickListener());
	}
	
	private class LanguageOnClickListener implements OnClickListener
	{
		@Override
		public void onClick(View v) 
		{
			//Create an empty intent working as a data carrier
			Intent outputIntent = new Intent();
			
			switch(v.getId())
			{
				case R.id.language_en:
				{
					//Put data into intent
					outputIntent.putExtra(LOCALIZATION_DATA_LABEL, v.getId());
					//Set the return status and intent(which carries data)
					setResult(Activity.RESULT_OK, outputIntent);
					
					break;
				}
				case R.id.language_tr:
				{
					//Put data into intent
					outputIntent.putExtra(LOCALIZATION_DATA_LABEL, v.getId());
					//Set the return status and intent(which carries data)
					setResult(Activity.RESULT_OK, outputIntent);
					
					break;
				}
			}
			
			finish();
		}
	}
}
