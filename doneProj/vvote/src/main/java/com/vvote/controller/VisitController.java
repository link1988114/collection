package com.vvote.controller;



import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.vvote.model.MsgModel;
import com.vvote.model.UserModel;
import com.vvote.service.UserService;
import com.vvote.util.RequestUtil;


@Controller
public class VisitController
{
	@RequestMapping("/visit.do")
	public String test(HttpServletRequest req, HttpServletResponse resp) throws Exception
	{
		return "redirect:/wx/over.html";
	}

	
	@RequestMapping("/list.do")
	public void list(UserModel userModel, HttpServletResponse resp, HttpSession session) throws Exception
	{
		MsgModel msg = new MsgModel();
		String openid = RequestUtil.getSessionInfo(session, "openid");
//		System.out.println("openid:"+openid);
		if(!checkOpenid(session))
		{
			msg.setMsg("请从微信登录");
			//System.out.println("pls login by wx");
		}
		else 
		{
			UserService userService = new UserService();
			userModel.setOpenid(openid);
			msg = userService.list(userModel);
		}
		resp.getWriter().write(msg.toString());
	}
	
	@RequestMapping("/vote.do")
	public void vote(UserModel userModel, HttpServletResponse resp, HttpSession session) throws Exception
	{
		MsgModel msg = new MsgModel();
		String openid = RequestUtil.getSessionInfo(session, "openid");
		if(!checkOpenid(session))
		{
			msg.setMsg("请从微信登录");
			//System.out.println("pls login by wx");
		}
		else 
		{
			UserService userService = new UserService();
			userModel.setOpenid(openid);
			msg = userService.vote(userModel);
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
