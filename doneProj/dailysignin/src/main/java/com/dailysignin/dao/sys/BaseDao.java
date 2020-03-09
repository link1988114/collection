package com.dailysignin.dao.sys;

import com.dailysignin.dao.SQLgetter;

public class BaseDao
{
	protected MybatisConnection db = new MybatisConnection();
	protected SQLgetter sqls = SQLgetter.getInstance();
}
