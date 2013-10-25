package com.example.droidmote;

public class IconMetaData 
{
	private String m_AppName = null;
	private int m_CellState = 0;
	private String m_AppIconPath = null;
	private String m_AppMainActivity = null;
	private int m_CellIndex = 0;
	private String m_PackageName = null;
	private String m_ClassName = null;

	/*
	 * The following code is used to set the meta data.
	 * */
	public void setAppName(String appName) {
		m_AppName = appName;
	}
	
	public void setCellState(int cellState) {
		m_CellState = cellState;
	}
	
	public void setAppIconPath(String iconPath) {
		m_AppIconPath = iconPath;
	}
	
	public void setAppMainActivity(String appMainActivity) {
		m_AppMainActivity = appMainActivity;
	}
	
	public void setCellIndex(int index) {
		m_CellIndex = index;
	}
	
	public void setPackageName(String packageName) {
		m_PackageName = packageName;
	}
	
	public void setClassName(String className) {
		m_ClassName = className;
	}
	
	/*
	 * The following code is used to retrieve the meta data.
	 * */
	public String getAppName() {
		
		return m_AppName;
	}
	
	public int getCellState() {
		
		return m_CellState;
	}
	
	public String getAppIconPath() {
		
		return m_AppIconPath;
	}
	
	public String getAppMainActivity() {
		
		return m_AppMainActivity;
	}
	
	public int getCellIndex() {
		
		return m_CellIndex;
	}
	
	public String getPackageName() {
		return m_PackageName;
	}
	
	public String getClassName() {
		return m_ClassName;
	}
}
