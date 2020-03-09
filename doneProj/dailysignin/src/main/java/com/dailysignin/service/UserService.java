package com.dailysignin.service;


import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;


import org.json.JSONObject;

import com.dailysignin.dao.DaoUser;
import com.dailysignin.model.MsgModel;
import com.dailysignin.model.ProModel;
import com.dailysignin.model.UserModel;

public class UserService extends BaseService
{
	DaoUser daoUser = new DaoUser();
	
	public UserService()
	{
	}
	
	
	public MsgModel pageData(UserModel userModel)
	{
		MsgModel msg = new MsgModel();
		
		//get user status
		UserModel userinfo = new UserModel();
		userinfo.setOpenid(userModel.getOpenid());
		
		JSONObject temp = getSignCombo(userinfo);
		JSONObject msgs = statusToMsg(temp);
		userinfo.setStatus(temp.optString("status"));
		userinfo.setHintMsg(msgs.optString("msg"));
		userinfo.setComboDays(temp.optString("combo"));
		userinfo.setBreakDate(temp.optString("combo"));
		
		if(userinfo.getStatus().equals("break"))
		{
			userinfo.setComboDays("0");
		}
		
		
		//get prolist
		ProModel proModel = new ProModel();
		List<ProModel> prolist = daoUser.getProlist(proModel);
		
		//get user unlock
		List<UserModel> unlocklist = daoUser.getUnlocklist(userModel);
		JSONObject unlockTemp = new JSONObject();
		for(UserModel item : unlocklist)
		{
			unlockTemp.put(item.getUnlock_proid(), "1");
		}
		
		//merge into prolist
		for(int i=0; i<prolist.size(); i++)
		{
			String isUnlock = unlockTemp.optString(prolist.get(i).getProID());
			if(isUnlock.equals(""))
			{
				prolist.get(i).setIsUnlock("0");
			}
			else
			{
				prolist.get(i).setIsUnlock("1");
			}
		}
		
		JSONObject finalObj = new JSONObject();
		JSONObject info = new JSONObject();
		info.put("status", userinfo.getStatus());
		info.put("hintMsg", userinfo.getHintMsg());
		info.put("comboDays", userinfo.getComboDays());
		info.put("breakDate", userinfo.getBreakDate());
		finalObj.put("userinfo", info.toString());
		finalObj.put("prolist", ListToJsonArray(prolist));
		
		msg.setResult("success");
		msg.setMsg(finalObj.toString());
		return msg;
	}
	
	
	public MsgModel signName(UserModel userModel)
	{
		MsgModel msg = new MsgModel();
		
		ProModel proModel = new ProModel();
		List<ProModel> prolist = daoUser.getProlist(proModel);
		
		//check sign combo
		JSONObject hint = getSignCombo(userModel);
		JSONObject msgObj = statusToMsg(hint);
		String status = msgObj.optString("status");
		String hintMsg = msgObj.optString("msg");
		String comboDays = msgObj.optString("combo");
		
		if(status.equals("signed"))
		{
			//not pass    today signed
			msg.setMsg(hintMsg);
		}
		else if(status.equals("break"))
		{
			//pass reset
			int flag = daoUser.resetCombo(userModel);
			if(flag>0)
			{
				String unlockPro = getUnlockProID(prolist, 1);
				if(unlockPro != null)
				{
					userModel.setDoUnlock("1");
					userModel.setProID(unlockPro);
				}
				flag = daoUser.insertSign(userModel);
				msg.simplePack(flag>0, "签到成功", "签到失败,请重试");
			}
			else
			{
				msg.setMsg("出现错误,请重试");
			}
		}
		else if(status.equals("incombo"))
		{
			//pass
			String unlockPro = getUnlockProID(prolist, Integer.parseInt(comboDays)+1);
			if(unlockPro != null)
			{
				userModel.setDoUnlock("1");
				userModel.setProID(unlockPro);
			}		
			int flag = daoUser.insertSign(userModel);
			msg.simplePack(flag>0, "签到成功", "签到失败,请重试");
		}
		else
		{
			//pass  start
			String unlockPro = getUnlockProID(prolist, 1);
			if(unlockPro != null)
			{
				userModel.setDoUnlock("1");
				userModel.setProID(unlockPro);
			}
			int flag = daoUser.insertSign(userModel);
			msg.simplePack(flag>0, "签到成功", "签到失败,请重试");
		}

		return msg;
	}
	
	
	public MsgModel exchange(UserModel userModel)
	{
		MsgModel msg = new MsgModel();
		msg.setMsg("请选择一个商品");
		
		int counting = checkExchange(userModel);
		if(counting>0)
		{
			msg.setMsg("已兑换过商品");
		}
		else 
		{
			ProModel proModel = new ProModel();
			List<ProModel> prolist = daoUser.getProlist(proModel);
			for(ProModel item : prolist)
			{
				if(userModel.getProID().equals(item.getProID()))
				{
					int remains = Integer.parseInt(item.getProRemains());
					if(remains>0)
					{
						int count = daoUser.checkExchange(userModel);
						if(count>0)
						{
							msg.setMsg("已兑换过商品");
						}
						else
						{
							//System.out.println(userModel.getProID());
							int flag = daoUser.exchange(userModel);
							msg.simplePack(flag>0, "兑换成功", "出现错误,请重试");
						}
					}
					else
					{
						msg.setMsg("商品已无库存");
						
					}
					break;
				}
			}
		}
		
		return msg;
	}
	
	
	
	
	
	
	
	
	
	/////////////////////////////////////////////////////////
	
	private String getUnlockProID(List<ProModel> prolist, int combos)
	{
		JSONObject temp = new JSONObject("{proID:'-1',unlockDays:'0'}") ;
		for(ProModel item : prolist)
		{
			int unlockDays = Integer.parseInt(item.getUnlockDays());
			if(combos >= unlockDays)
			{
				if(unlockDays>temp.optInt("unlockDays"))
				{
					temp.put("proID", item.getProID());
					temp.put("unlockDays", item.getUnlockDays());
				}
			}
		}
		
		String proID = temp.optString("proID");
		proID = proID.equals("-1") ? null : proID;
		return proID;
	}
	
	
	
	private int checkExchange(UserModel userModel)
	{
		return daoUser.checkExchange(userModel);
	}
	

	
	
	private JSONObject getSignCombo(UserModel userModel)
	{
		JSONObject res = new JSONObject();
		res.put("combo", "0");
		res.put("status", "start");   //incombo break start signed
		
		try 
		{
			List<UserModel> list = daoUser.getSignList(userModel);
			if(list == null || list.size()<=0)
			{
				res.put("combo", "0");
				res.put("status", "start");
			}
			else 
			{
				DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
				String lastSign = list.get(list.size()-1).getSign_date();
				Calendar cd = Calendar.getInstance();
				cd.setTime(df.parse(lastSign));
				cd.add(Calendar.DATE, 1);
				String nextCombo = df.format(cd.getTime());
				
				Date now = new Date();
				long nextComboTime = df.parse(nextCombo).getTime();
				long nowTime = df.parse(df.format(now)).getTime();
				
				
				if(nextComboTime==nowTime)
				{
					res.put("status", "incombo");
					res.put("combo", list.size()+"");
				}
				else if(nextComboTime > nowTime)
				{
					res.put("status", "signed");
					res.put("combo", list.size()+"");
				}
				else
				{
					res.put("status", "break");
					res.put("combo", nextCombo);
				}
			}
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
			res.put("combo", "0");
			res.put("status", "start");
		}
		return res;
	}
	
	
	private JSONObject statusToMsg(JSONObject obj)
	{
		JSONObject res = new JSONObject();
		String status = obj.optString("status");
		String combo = obj.optString("combo");
		res.put("status", status);
		res.put("combo", combo);
		
		if(status.equals("incombo"))
		{
			res.put("msg", "已连续签到"+combo+"天");
		}
		else if(status.equals("break"))
		{
			res.put("msg", "签到在"+combo+"中断");
		}
		else if(status.equals("exchanged"))
		{
			res.put("msg", "已兑换商品,谢谢参与");
		}
		else if(status.equals("signed"))
		{
			res.put("msg", "今日已签到，已连续签到"+combo+"天");
		}
		else
		{
			//start
			res.put("msg", "还未参与签到活动");	
		}
		return res;
	}
	
	

}
