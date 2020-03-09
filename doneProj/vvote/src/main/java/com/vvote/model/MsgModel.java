package com.vvote.model;


public class MsgModel extends BaseModel
{
	private String result = "failed";
	private String msg = "";
	
	
	public void simplePack(boolean equation, String successStr, String failStr)
	{
		if(equation)
		{
			setResult("success");
			setMsg(successStr);
		}
		else
		{
			setResult("failed");
			setMsg(failStr);
		}
	}
	
	
	
	public String getResult()
	{
		return result;
	}
	public void setResult(String result)
	{
		this.result = result;
	}
	public String getMsg()
	{
		return msg;
	}
	public void setMsg(String msg)
	{
		this.msg = msg;
	}
	
	
	
	

}
