package com.example.droidmote;

import java.util.ArrayList;

import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.pm.ApplicationInfo;
import android.view.Menu;
import android.view.View;
import android.widget.ImageButton;

public class MainActivity extends Activity
{
	private ArrayList <IconMetaData> m_IconMetaDataList = null;
	private UtilityHelper m_UtilityHelper = null;
	private AlertDialog.Builder m_AlterBuilder = null;
	private ArrayList <ImageButton> m_ImageButtonList = null;
	private ArrayList <ApplicationInfo> m_ApplicationInfoList = null;
	private ArrayList <String> m_ApplicationNameList = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		// initialize the utility helper.
		m_UtilityHelper = new UtilityHelper(this);
		
		// initialize the m_IconMetaDataList
		initIconMetaDataList();
		
		// create the alter builder
		createAlterBuilder();
		
		// create the image button list
		createImageButtonList();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	// initialize the icon meta data array
	private void initIconMetaDataList() {
		
	}
	
	// retrieve the icon metadata from either the content provider or a file.
	private void loadIconMetaData() {
		
	}
	
	// create all the image button list.
	private void createImageButtonList() {
		// create image button's listener
		ImageButton ib = (ImageButton) findViewById(R.id.imageButton1);
		ib.setOnClickListener(new View.OnClickListener() {
					
			@Override
			public void onClick(View v) {
				IconMetaData metadata = new IconMetaData();
				metadata.setPackageName("com.google.android.gm");
				m_UtilityHelper.startActivity(metadata);
						
				// pop the alter builder if the 
			}
		});
	}
	
	// create the alter builder.
	private void createAlterBuilder() {
		m_AlterBuilder = new AlertDialog.Builder(this);
	}
}
