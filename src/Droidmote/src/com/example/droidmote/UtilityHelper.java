package com.example.droidmote;

import java.util.ArrayList;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.graphics.drawable.Drawable;

public class UtilityHelper 
{
	private static Context m_AppEnvironment;
	public UtilityHelper(Context AppEnvironment) {
		m_AppEnvironment = AppEnvironment;
	}
	/*
	 * Call this function to start a new activity.
	 * */
	public void startActivity(IconMetaData metadata) {
		String packageName = metadata.getPackageName();
		Intent intent = m_AppEnvironment.getPackageManager().getLaunchIntentForPackage(packageName);
		m_AppEnvironment.startActivity(intent);
	}
	
	/*
	 * Call this function to save the meta data list.
	 * */
	public void saveMetaData(ArrayList <IconMetaData> metaDataList) {
		
	}
	
	/*
	 * Call this function to find the meta data icon.
	 * */
	public Drawable getAppIcon(IconMetaData metaData) {
		
		return null;
	}
	
	public ArrayList<ApplicationInfo> getAppList() {
		final PackageManager pm = m_AppEnvironment.getPackageManager();
		ArrayList<ApplicationInfo> packages = (ArrayList<ApplicationInfo>) pm.getInstalledApplications(PackageManager.GET_META_DATA);
		return packages;
	}
}
