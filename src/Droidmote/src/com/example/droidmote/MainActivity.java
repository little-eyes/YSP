package com.example.droidmote;

import java.util.ArrayList;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;

public class MainActivity extends Activity
{
	private ArrayList <IconMetaData> m_IconMetaDataList = null;
	private UtilityHelper m_UtilityHelper = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		m_UtilityHelper = new UtilityHelper(this);
		ImageButton ib = (ImageButton) findViewById(R.id.imageButton1);
		ib.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				IconMetaData metadata = new IconMetaData();
				metadata.setPackageName("com.google.android.gm");
				m_UtilityHelper.startActivity(metadata);
				
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
}
