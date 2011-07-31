package com.clockwork.testc;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

public class TestCProjectActivity extends Activity 
{
	private final String LOCALIZATION_DATA_LABEL = "com.visionbetalabs.client.language";	//When adding extra content into an intent
	private final int LOCALIZATION_DATA_REQUEST_CODE = 0;	//When waiting for a data return from an intent
	private final int LOCALIZATION_ID_DEFAULT = R.id.language_en;
	
    /** Called when the activity is first created. */
    @Override
    protected void onCreate(Bundle savedInstanceState) 
    {
        super.onCreate(savedInstanceState);
    }
    
    @Override
    protected void onStart()
    {
    	super.onStart();
    	
    	//Started application perfectly,
    	//now let the user choose the language,
    	//then redirect the user to the main menu
    	Intent chooseLanguage = new Intent(TestCProjectActivity.this, LanguageSelectorActivity.class);
    	startActivityForResult(chooseLanguage, LOCALIZATION_DATA_REQUEST_CODE);
    }
    
    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {    	
    	switch(requestCode)
    	{
    		case LOCALIZATION_DATA_REQUEST_CODE:
    		{
    			if(resultCode == Activity.RESULT_OK)
    			{
    				int localizationID = data.getIntExtra(LOCALIZATION_DATA_LABEL, LOCALIZATION_ID_DEFAULT);
    				
    				Intent mainMenu = new Intent(TestCProjectActivity.this, MainMenuActivity.class);
			        mainMenu.putExtra(LOCALIZATION_DATA_LABEL, localizationID);
			        startActivity(mainMenu);
    			}
    			else
    			{
    				//Not handled................. yet!!!
    			}
    			
    			finish();
    		}
    	}
    }
}