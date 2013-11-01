package com.example.droidmote;

import java.util.ArrayList;

import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
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
	private ArrayList <CharSequence> m_ApplicationNameList = null;
	private static int m_cellId = 0;
	private static int m_AppIndex = 0;

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
		m_ImageButtonList = new ArrayList<ImageButton>();
		ImageButton ib1 = (ImageButton) findViewById(R.id.imageButton1);
		ImageButton ib2 = (ImageButton) findViewById(R.id.imageButton2);
		ImageButton ib3 = (ImageButton) findViewById(R.id.imageButton3);
		ImageButton ib4 = (ImageButton) findViewById(R.id.imageButton4);
		ImageButton ib5 = (ImageButton) findViewById(R.id.imageButton5);
		ImageButton ib6 = (ImageButton) findViewById(R.id.imageButton6);
		m_ImageButtonList.add(ib1);
		m_ImageButtonList.add(ib2);
		m_ImageButtonList.add(ib3);
		m_ImageButtonList.add(ib4);
		m_ImageButtonList.add(ib5);
		m_ImageButtonList.add(ib6);
		for (ImageButton ib: m_ImageButtonList)
			
		ib.setOnClickListener(new View.OnClickListener() {
					
			@Override
			public void onClick(View v) {
				int cellId = 0;
				for (int index = 0; index < m_ImageButtonList.size(); index++)
				{
					if (v.getId() == m_ImageButtonList.get(index).getId()) {
						cellId = index;
						m_cellId = cellId;
						break;
					}
					
				}
				if (m_IconMetaDataList.get(cellId).getCellState() != 0)
					m_UtilityHelper.startActivity(m_IconMetaDataList.get(cellId));
				else {
					m_AlterBuilder.show();
				}
						
				// pop the alter builder if the 
			}
		});
	}
	
	// create the alter builder.
	private void createAlterBuilder() {
		m_AlterBuilder = new AlertDialog.Builder(this);
		for (ApplicationInfo app: m_ApplicationInfoList)
			m_ApplicationNameList.add(app.name);
		final CharSequence[] items = (CharSequence[]) m_ApplicationNameList.toArray();
		m_AlterBuilder.setTitle("Choose an app").setItems(items, new DialogInterface.OnClickListener() {

			@Override
			public void onClick(DialogInterface dialog, int index) {
				m_AppIndex = index;
			}}).setPositiveButton("Ok", new DialogInterface.OnClickListener(){

				@Override
				public void onClick(DialogInterface dialog, int which) {
					IconMetaData metaData = new IconMetaData();
					ApplicationInfo info = m_ApplicationInfoList.get(m_AppIndex);
					metaData.setAppName(info.name);
					metaData.setPackageName(info.packageName);
					metaData.setAppMainActivity(info.className);
					metaData.setCellIndex(m_cellId);
					metaData.setCellState(1);
					m_IconMetaDataList.set(m_cellId, metaData);
					dialog.dismiss();
				}}).setNegativeButton("Cancel", new DialogInterface.OnClickListener(){

					@Override
					public void onClick(DialogInterface dialog, int which) {
						// TODO Auto-generated method stub
						dialog.dismiss();
					}});
		m_AlterBuilder.create();
	}
}
