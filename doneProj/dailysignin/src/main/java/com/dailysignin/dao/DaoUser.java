package com.dailysignin.dao;


import java.util.List;

import com.dailysignin.dao.sys.BaseDao;
import com.dailysignin.model.ProModel;
import com.dailysignin.model.UserModel;

public class DaoUser extends BaseDao
{	
	public List<UserModel> getSignList(UserModel userModel)
	{
		return db.selectList(sqls.select_user_getSignList, userModel);
	}

	public int resetCombo(UserModel userModel)
	{
		return db.update(sqls.update_user_resetCombo, userModel);
	}
	
	public int insertSign(UserModel userModel)
	{
		return db.insert(sqls.insert_user_insertSign, userModel);
	}
	
	public List<ProModel> getProlist(ProModel proModel)
	{
		return db.selectList(sqls.select_user_getProlist, proModel);
	}
	
	public int checkExchange(UserModel userModel)
	{
		return (Integer)db.selectOne(sqls.select_user_checkExchange, userModel);
	}
	
	public int exchange(UserModel userModel)
	{
		return db.insert(sqls.insert_user_exchange, userModel);
	}
	
	public List<UserModel> getUnlocklist(UserModel userModel)
	{
		return db.selectList(sqls.select_user_getUnlocklist, userModel);
	}
	
	public int addUnlock(UserModel userModel)
	{
		return db.update(sqls.update_user_resetCombo, userModel);
	}
	
}
