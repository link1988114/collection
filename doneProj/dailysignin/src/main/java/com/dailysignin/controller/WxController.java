package com.dailysignin.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.dailysignin.util.HttpClientUtil;
import com.dailysignin.util.RequestUtil;
import com.dailysignin.util.WxSharedInfo;


@Controller
@RequestMapping("/wx")
public class WxController
{
	@RequestMapping("/init.do")
	public String init(String code, String promote_code, HttpServletRequest req, HttpServletResponse resp, HttpSession session) throws Exception
	{
		RequestUtil.setSession(session, "reviseFlag", "0");
		
		String redirectURL = "";

		String appid = WxSharedInfo.GetInstance().appid;
		String secret = WxSharedInfo.GetInstance().secret;
		String getidURL = "https://api.weixin.qq.com/sns/oauth2/access_token";
		String param = "appid="+appid+"&secret="+secret+"&code="+code+"&grant_type=authorization_code";
			
		HttpClientUtil client = new HttpClientUtil("http");
		String jsonstr = client.getStr(getidURL, param);
		client.closeHttpClient();
		
		
		if(promote_code==null)
		{
			promote_code = "";
		}
		RequestUtil.setSession(session, "promoteCode", promote_code);
		
		try 
		{
			JSONObject json = new JSONObject(jsonstr);
			//System.out.println(json.optString("openid"));
			RequestUtil.setSession(session, "openid", json.optString("openid"));
		} 
		catch (Exception e) 
		{
			RequestUtil.setSession(session, "openid", "error");
		}
		
		//System.out.println(RequestUtil.getSessionInfo(session, "openid"));
		
		//String jsonstr = "{openid:'test123'}";
		//WxService wxService = new WxService();
		
		redirectURL = "/visit.do";

		return "redirect:"+redirectURL;
	}
	


}
