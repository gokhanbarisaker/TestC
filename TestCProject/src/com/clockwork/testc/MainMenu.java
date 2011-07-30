/*
 * To Do List;
 * 
 * - IMPROVE: For now handling configuration's locale change handled with if statement,
 *   which checks old and new configurations has any configuration differences,
 *   in future if possible it could be handled with onConfigurationChanged() method.
 * 
 * - IMPROVE: Application starts with the last localized settings, it might be changed in to start with system defaults
 * 
 * - FIX: setComponentListeners() invoking 2 times at start.
 *   1 from onCreate()-->setComponentListeners()
 *   1 from onCreate()-->setLocalization()-->updateLocalization()-->setComponentListeners()
 * 
 */

/*
 * ...No traveller returns, puzzles the will,
 *    And makes us rather bear those ills we have
 *    Than fly to others that we know not of?...
 * 
 * Indecisive Design;
 * 
 * - Restaurant menu and purchased order list are accessed via main menu, aren't they?
 * 
 * - setLocalization() or initializeLocalization(), or what!
 */




package com.clockwork.testc;

import java.util.Locale;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;

public class MainMenu extends Activity
{
	private final String LOCALIZATION_DATA_LABEL = "com.visionbetalabs.client.language";
	private final int LOCALIZATION_DATA_REQUEST_CODE = 0;
	private final int LOCALIZATION_ID_DEFAULT = R.id.language_en;
	
	//private boolean localeUpdated = false;
	
	protected void onCreate(Bundle savedInstanceState)
	{		
		super.onCreate(savedInstanceState);
		
		System.out.println("MainMenu class onCreate() invoked");
		
		setContentView(R.layout.mainmenu);
        setLocalization();
        setComponentListeners();
	}
	
	protected void onStart()
	{
		super.onStart();
		System.out.println("MainMenu class onStart() invoked");
	}
    
    protected void onRestart()
    {
    	super.onRestart();
    	System.out.println("MainMenu class onRestart() invoked");
    }

    protected void onResume()
    {
    	super.onResume();
    	System.out.println("MainMenu class onResume() invoked");
    }

    protected void onPause()
    {
    	super.onPause();
    	System.out.println("MainMenu class onPause() invoked");
    }

    protected void onStop()
    {
    	super.onStop();
    	System.out.println("MainMenu class onStop() invoked");
    }

    protected void onDestroy()
    {
    	super.onDestroy();
    	System.out.println("MainMenu class onDestroy() invoked");
    }
	
	protected void onActivityResult(int requestCode, int resultCode, Intent data)
	{		
		System.out.println("MainMenu class onActivityResult() invoked");
		
		switch(requestCode)
    	{
    		case LOCALIZATION_DATA_REQUEST_CODE:
    		{
    			if(resultCode == Activity.RESULT_OK)
    			{
    				int localizationID = data.getIntExtra(LOCALIZATION_DATA_LABEL, LOCALIZATION_ID_DEFAULT);
    				
    				updateLocalization(localizationID);
    			}
    			else
    			{
    				//Not handled................. yet!!!
    			}
    		}
    	}
	}
	
	public void onConfigurationChanged(Configuration newConfig)
	{
		super.onConfigurationChanged(newConfig);
		//setContentView(R.layout.mainmenu);
		System.out.println("MainMenu class onConfigurationChanged() invoked");
	}

	private void setLocalization() 
	{
		System.out.println("MainMenu class setLocalization() invoked");
		
		int languageResourceId;
		
		//Get language resource ID (Language button ID works just fine, since it is unique too)
		languageResourceId = getIntent().getIntExtra(LOCALIZATION_DATA_LABEL, LOCALIZATION_ID_DEFAULT);
		
		updateLocalization(languageResourceId);
	}

	private void updateLocalization(int languageResourceId) 
	{
		System.out.println("MainMenu class updateLocalization() invoked");
		
		if(languageResourceId > 1)
		{//If language provided from previous intent, update locale.
			
			//Create new configuration object. (Still invalid, we need to call setToDefaults method)
			Configuration newConfiguration = new Configuration();
			//Set object to system defaults. (Now valid...)
			newConfiguration.setToDefaults();
			
			//Identify resource id & set new configuration locale.
			switch(languageResourceId)
			{
				case R.id.language_en:
				{
					System.out.println("English! --> USA");
					newConfiguration.locale = new Locale("en_US");
					break;
				}
				case R.id.language_tr:
				{
					System.out.println("Türkçe!");
					newConfiguration.locale = new Locale("tr_TR");
					break;
				}
			}
			//No idea why???
			//Locale.setDefault(newConfiguration.locale);
			
			if(ActivityInfo.CONFIG_LOCALE == getBaseContext().getResources().getConfiguration().diff(newConfiguration))
			{//If locale updated (has a difference in class members)
				
				System.out.println("Locale configuration updated!");
				
				//Update current configuration with new configuration.
				getBaseContext().getResources().updateConfiguration(newConfiguration, getBaseContext().getResources().getDisplayMetrics());
				
				//Refresh user interface to display updated results
				refreshUserInterface();
			}
		}
		else
		{
			//Continue with current locale.
		}
	}
	
	private void refreshUserInterface()
	{
		//Reset layout, which causes it to reDraw
		this.setContentView(R.layout.mainmenu);
		//Since the member's of UI is recreated assign their component listeners
		this.setComponentListeners();
	}

	private void setComponentListeners()
	{
		System.out.println("MainMenu class setComponentListeners() invoked");
		
		View buttonMenu = findViewById(R.id.button_restaurant_menu);
		buttonMenu.setOnClickListener(new MainMenuOnClickListener(this));
		View buttonChangeLanguage = findViewById(R.id.button_change_language);
		buttonChangeLanguage.setOnClickListener(new MainMenuOnClickListener(this));
		View buttonExit = findViewById(R.id.button_exit);
		buttonExit.setOnClickListener(new MainMenuOnClickListener());
	}

	private class MainMenuOnClickListener implements OnClickListener
	{
		private Activity hostActivity = null;
		
		public MainMenuOnClickListener()
		{
			
		}
		
		public MainMenuOnClickListener(Activity hostActivity) 
		{
			this.hostActivity = hostActivity;
		}
		
		@Override
		public void onClick(View v)
		{
			switch(v.getId())
			{//Check which button clicked
				case R.id.button_restaurant_menu:
				{//If it is restaurant menu button,
					//Start restaurant menu activity
					//Intent restaurantMenu = new Intent(hostActivity, RestaurantMenu.class);
					
					//System.out.println(getWindowManager().getDefaultDisplay().toString());
					System.out.println("Width --> " + getWindowManager().getDefaultDisplay().getWidth() + "\nHeight --> " + getWindowManager().getDefaultDisplay().getHeight());
					break;
				}
				case R.id.button_change_language:
				{//If it is change language button,
					//Start language selector activity
					Intent chooseLanguage = new Intent(hostActivity, LanguageSelector.class);
			    	hostActivity.startActivityForResult(chooseLanguage, LOCALIZATION_DATA_REQUEST_CODE);
					break;
				}
				case R.id.button_exit:
				{
					finish();
				}
			}
		}
	}
}
