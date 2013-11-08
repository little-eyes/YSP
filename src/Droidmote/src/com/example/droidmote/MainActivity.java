package com.example.droidmote;

import java.io.*;
import java.util.ArrayList;

import android.os.Bundle;
import android.os.Environment;
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
	private void initIconMetaDataList() 
	{
		m_IconMetaDataList = new ArrayList<IconMetaData>();
		loadIconMetaData();
	}
	
	// retrieve the icon metadata from either the content provider or a file.
	private void loadIconMetaData() 
	{
		try
		{
			InputStream stream = new FileInputStream(new File(Environment.getExternalStorageDirectory(), "DroidmoteData.txt"));
			InputStreamReader inputReader = new InputStreamReader(stream);
			BufferedReader buffReader = new BufferedReader(inputReader);
			
			String line;
			
			for (int i = 0; i < 6; i++)
			{
				m_IconMetaDataList.add(new IconMetaData());
			}
			int index = 0;
			
			do 
			{
				line = buffReader.readLine();
				String [] data = line.split("=");
				if (data[0] == "index ")
				{
					index = Integer.valueOf(data[1]);
					m_IconMetaDataList.get(index).setCellIndex(index);
				}
				else if (data[0] == "app name ")
				{
					m_IconMetaDataList.get(index).setAppName(data[1].substring(1));
				}
				else if (data[0] == "cell state ")
				{
					m_IconMetaDataList.get(index).setCellState(Integer.valueOf(data[1].substring(1)));
				}
				else if (data[0] == "icon path ")
				{
					m_IconMetaDataList.get(index).setAppIconPath(data[1].substring(1));
				}
				else if (data[0] == "main activity ")
				{
					m_IconMetaDataList.get(index).setAppMainActivity(data[1].substring(1));
				}
				else if (data[0] == "package name ")
				{
					m_IconMetaDataList.get(index).setPackageName(data[1].substring(1));
				}
				else if (data[0] == "class name ")
				{
					m_IconMetaDataList.get(index).setClassName(data[1].substring(1));
				}
			} while (line != null);
			buffReader.close();
		}
		catch (Exception e){}
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
	
	private void saveMetaDataList()
	{
		File F = new File(Environment.getExternalStorageDirectory(), "DroidmoteData.txt");
		if (!F.exists())
		{
			try
			{
				F.createNewFile();
			}
			catch (Exception e){}
		}
		FileOutputStream fos = null;
		try 
		{
			fos = new FileOutputStream(F, true);
		}
		catch (Exception e){}
		int index = 0;
		for (IconMetaData meta: m_IconMetaDataList)
		{
			try
			{
			fos.write(("index = " + String.valueOf(index)).getBytes());
			fos.write(("\n").getBytes());
			fos.write(("app name = " + meta.getAppName()).getBytes());
			fos.write(("\n").getBytes());
			fos.write(("cell state = " + String.valueOf(meta.getCellState())).getBytes());
			fos.write(("\n").getBytes());
			fos.write(("icon path = " + meta.getAppIconPath()).getBytes());
			fos.write(("\n").getBytes());
			fos.write(("main activity = " + meta.getAppMainActivity()).getBytes());
			fos.write(("\n").getBytes());
			fos.write(("package name = " + meta.getPackageName()).getBytes());
			fos.write(("\n").getBytes());
			fos.write(("class name = " + meta.getClassName()).getBytes());
			fos.write(("\n").getBytes());
			}
			catch (Exception e){}
		}
		try
		{
			fos.close();
		}
		catch (Exception e){}
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
