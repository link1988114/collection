package com.vvote.service;

import java.util.List;

import com.vvote.dao.DaoUser;
import com.vvote.model.MsgModel;
import com.vvote.model.UserModel;
import com.vvote.util.ProjectUtil;

public class UserService extends BaseService
{
	DaoUser daoUser = new DaoUser();
	
	public UserService()
	{
	}
	
	
	public MsgModel list(UserModel userModel)
	{
		MsgModel msg = new MsgModel();
		int voteRmain = ProjectUtil.voteRemain - daoUser.voteCounts(userModel);
		List<UserModel> list = daoUser.list(userModel);
		if(list != null)
		{			
			msg.setResult("success");
			msg.setMsg("{\"data\":"+ListToJsonstr(list)+", voteRemain:\""+voteRmain+"\"}");
		}
		else 
		{
			msg.setMsg("获取列表出现错误,请重试");
		}
		return msg;
	}
	
	public MsgModel vote(UserModel userModel)
	{
		MsgModel msg = new MsgModel();
		
		int remains = ProjectUtil.voteRemain - daoUser.voteCounts(userModel);
		int todayVote = daoUser.voteCountsToday(userModel);
		if(remains>0)
		{
			if(todayVote>0)
			{
				msg.setMsg("今天已投过, 明日再来");
			}
			else
			{
				int flag = daoUser.vote(userModel);
				msg.simplePack(flag>0, "投票成功", "投票出现错误请重试");
			}
		}
		else
		{
			msg.setMsg("投票次数已达上限");
		}
		
		return msg;
	}
	

}
