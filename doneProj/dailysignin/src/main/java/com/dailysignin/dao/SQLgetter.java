package com.dailysignin.dao;

public class SQLgetter 
{	
	private static SQLgetter instance = null;
	
	private SQLgetter()
	{
	}
	
	public static SQLgetter getInstance()
	{
		if(instance == null)
		{
			instance = new SQLgetter();
		}
		return instance;
	}
	

	/////////////////////////////////////////////////////////////////////////////////////////
	
	public String select_user_getSignList = "com.dailysignin.mapper.UserMapper.getSignList";
	public String insert_user_insertSign = "com.dailysignin.mapper.UserMapper.insertSign";
	public String update_user_resetCombo = "com.dailysignin.mapper.UserMapper.resetCombo";
	public String select_user_checkExchange = "com.dailysignin.mapper.UserMapper.checkExchange";
	public String select_user_getProlist = "com.dailysignin.mapper.UserMapper.getProlist";
	public String select_user_getUnlocklist = "com.dailysignin.mapper.UserMapper.getUnlocklist";
	public String udpate_user_addUnlock = "com.dailysignin.mapper.UserMapper.addUnlock";
	public String insert_user_exchange = "com.dailysignin.mapper.UserMapper.exchange";
}
