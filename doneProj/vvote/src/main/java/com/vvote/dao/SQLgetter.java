package com.vvote.dao;

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
	
	public String select_user_list = "com.vvote.mapper.UserMapper.list";
	public String select_user_voteCounts = "com.vvote.mapper.UserMapper.voteCounts";
	public String insert_user_vote = "com.vvote.mapper.UserMapper.vote";
	public String select_user_voteCountsToday = "com.vvote.mapper.UserMapper.voteCountsToday";
	
	
}
