package com.sampleProject.util;

public class WxSharedInfo
{
	private static WxSharedInfo instance = null;
	
	public String appid = "wxa94fbe83acb237cf";
	public String secret = "b6c6fc24cbfcb6ed9279fe5bcd77e4c2";
	public String baseurl = "http://www.postsz.cn/zyxf/";
	public String imgPath = "http://www.postsz.cn/zyxf/upload/";
	//public String imgPath = "http://192.168.99.219:7650/WebRoot/productImg/";
	//http://218.4.142.147/weixinportal/bonusReg.html?openid=111111
	public String entranceBase = "https://open.weixin.qq.com/connect/oauth2/authorize?appid="+appid+"&redirect_uri=";
	public String entranceTail = "&response_type=code&scope=snsapi_base&state=123#wechat_redirect";
	
	private WxSharedInfo()
	{

	}
	
	public static WxSharedInfo GetInstance()
	{
		if(instance==null)
		{
			instance = new WxSharedInfo();
		}
		return instance;
	}
	
	
}
