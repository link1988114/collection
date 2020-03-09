package com.vvote.dao;


import java.util.List;

import com.vvote.dao.sys.BaseDao;
import com.vvote.model.UserModel;

public class DaoUser extends BaseDao
{	
	public List<UserModel> list(UserModel userModel)
	{
		return db.selectList(sqls.select_user_list, userModel);
	}
	
	public int voteCounts(UserModel userModel)
	{
		Object obj = db.selectOne(sqls.select_user_voteCounts, userModel);
		if(obj != null)
		{
			return (Integer)obj;
		}
		else
		{
			return 0;
		}
	}
	
	public int voteCountsToday(UserModel userModel)
	{
		Object obj = db.selectOne(sqls.select_user_voteCountsToday, userModel);
		if(obj != null)
		{
			return (Integer)obj;
		}
		else
		{
			return 0;
		}
	}
	
	public int vote(UserModel userModel)
	{
		return db.insert(sqls.insert_user_vote, userModel);
	}
	
	
}
