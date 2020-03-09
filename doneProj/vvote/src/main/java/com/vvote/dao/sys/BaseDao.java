package com.vvote.dao.sys;

import com.vvote.dao.SQLgetter;

public class BaseDao
{
	protected MybatisConnection db = new MybatisConnection();
	protected SQLgetter sqls = SQLgetter.getInstance();
}
