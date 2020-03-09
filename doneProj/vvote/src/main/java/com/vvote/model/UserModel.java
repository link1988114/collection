package com.vvote.model;


public class UserModel extends BaseModel
{
	private String openid = "";
	private String imgurl = "";
	private String noNum = "";
	private String initiator = "";
	private String title = "";
	private String likes = "";
	private String mylikes = "";
	private String videourl = "";
	
	
	
	
	public String getOpenid() {
		return openid;
	}
	public void setOpenid(String openid) {
		this.openid = openid;
	}
	public String getImgurl() {
		return imgurl;
	}
	public void setImgurl(String imgurl) {
		this.imgurl = imgurl;
	}
	public String getNoNum() {
		return noNum;
	}
	public void setNoNum(String noNum) {
		this.noNum = noNum;
	}
	public String getInitiator() {
		return initiator;
	}
	public void setInitiator(String initiator) {
		this.initiator = initiator;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getLikes() {
		return likes;
	}
	public void setLikes(String likes) {
		this.likes = likes;
	}
	public String getMylikes() {
		return mylikes;
	}
	public void setMylikes(String mylikes) {
		this.mylikes = mylikes;
	}
	public String getVideourl() {
		return videourl;
	}
	public void setVideourl(String videourl) {
		this.videourl = videourl;
	}
	
	
}
