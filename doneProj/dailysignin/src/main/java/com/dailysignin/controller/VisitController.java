package com.dailysignin.controller;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.dailysignin.model.MsgModel;
import com.dailysignin.model.UserModel;
import com.dailysignin.service.UserService;
import com.dailysignin.util.RequestUtil;


@Controller
public class VisitController
{
	@RequestMapping("/visit.do")
	public String test(HttpServletRequest req, HttpServletResponse resp, HttpSession session) throws Exception
	{
		return "redirect:/wx/over.html";
	}

	
	@RequestMapping("/pagedata.do")
	public void pageData(UserModel userModel, HttpServletResponse resp, HttpSession session) throws Exception
	{
		MsgModel msg = new MsgModel();
		String openid = RequestUtil.getSessionInfo(session, "openid");
		if(!checkOpenid(session))
		{
			msg.setMsg("请从微信登录");
		}
		else 
		{
			UserService userService = new UserService();
			userModel.setOpenid(openid);
			msg = userService.pageData(userModel);
		}
		resp.getWriter().write(msg.toString());
	}
	
	@RequestMapping("/signname.do")
	public void signName(UserModel userModel, HttpServletResponse resp, HttpSession session) throws Exception
	{
		MsgModel msg = new MsgModel();
		String openid = RequestUtil.getSessionInfo(session, "openid");
		if(!checkOpenid(session))
		{
			msg.setMsg("请从微信登录");
		}
		else 
		{
			UserService userService = new UserService();
			userModel.setOpenid(openid);
			msg = userService.signName(userModel);
		}
		resp.getWriter().write(msg.toString());
	}
	
	@RequestMapping("/exchange.do")
	public void exchange(UserModel userModel, HttpServletResponse resp, HttpSession session) throws Exception
	{
		MsgModel msg = new MsgModel();
		String openid = RequestUtil.getSessionInfo(session, "openid");
		if(!checkOpenid(session))
		{
			msg.setMsg("请从微信登录");
		}
		else 
		{
			UserService userService = new UserService();
			userModel.setOpenid(openid);
			msg = userService.exchange(userModel);
		}
		resp.getWriter().write(msg.toString());
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	/////////////////////////////////////////////////////////
	
	private boolean checkOpenid(HttpSession session)
	{
		String openid = RequestUtil.getSessionInfo(session, "openid");
//		System.out.println("check:"+openid);
//		System.out.println("check2:"+openid.equals("none"));
		if(openid.equals("none"))
		{
			return false;
		}
		return true;
	}
	
}
