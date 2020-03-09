package com.dailysignin.model;


public class UserModel extends BaseModel
{
	private String openid = "";
	private String proID = "";
	private String record_time = "";
	private String proName = "";
	private String proUnlock = "";
	private String proRemains = "";
	private String proImg = "";
	private String unlockDays = "";
	private String sign_date = "";
	private String combo_flag = "";
	
	private String isUnlock = "0";
	private String selected = "0";
	private String status = "start";
	private String hintMsg = "还未参与签到活动";
	private String comboDays = "0";
	private String breakDate = "";
	
	
	private String unlock_proid = "";
	private String isvalid = "";
	
	private String doUnlock = "0";
	
	
	
	public String getOpenid() {
		return openid;
	}
	public void setOpenid(String openid) {
		this.openid = openid;
	}
	public String getProID() {
		return proID;
	}
	public void setProID(String proID) {
		this.proID = proID;
	}
	public String getRecord_time() {
		return record_time;
	}
	public void setRecord_time(String record_time) {
		this.record_time = record_time;
	}
	public String getProName() {
		return proName;
	}
	public void setProName(String proName) {
		this.proName = proName;
	}
	public String getProUnlock() {
		return proUnlock;
	}
	public void setProUnlock(String proUnlock) {
		this.proUnlock = proUnlock;
	}
	public String getProRemains() {
		return proRemains;
	}
	public void setProRemains(String proRemains) {
		this.proRemains = proRemains;
	}
	public String getProImg() {
		return proImg;
	}
	public void setProImg(String proImg) {
		this.proImg = proImg;
	}
	public String getUnlockDays() {
		return unlockDays;
	}
	public void setUnlockDays(String unlockDays) {
		this.unlockDays = unlockDays;
	}
	public String getSign_date() {
		return sign_date;
	}
	public void setSign_date(String sign_date) {
		this.sign_date = sign_date;
	}
	public String getCombo_flag() {
		return combo_flag;
	}
	public void setCombo_flag(String combo_flag) {
		this.combo_flag = combo_flag;
	}
	public String getIsUnlock() {
		return isUnlock;
	}
	public void setIsUnlock(String isUnlock) {
		this.isUnlock = isUnlock;
	}
	public String getSelected() {
		return selected;
	}
	public void setSelected(String selected) {
		this.selected = selected;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getHintMsg() {
		return hintMsg;
	}
	public void setHintMsg(String hintMsg) {
		this.hintMsg = hintMsg;
	}
	public String getComboDays() {
		return comboDays;
	}
	public void setComboDays(String comboDays) {
		this.comboDays = comboDays;
	}
	public String getBreakDate() {
		return breakDate;
	}
	public void setBreakDate(String breakDate) {
		this.breakDate = breakDate;
	}
	public String getUnlock_proid()
	{
		return unlock_proid;
	}
	public void setUnlock_proid(String unlock_proid)
	{
		this.unlock_proid = unlock_proid;
	}
	public String getIsvalid()
	{
		return isvalid;
	}
	public void setIsvalid(String isvalid)
	{
		this.isvalid = isvalid;
	}
	public String getDoUnlock()
	{
		return doUnlock;
	}
	public void setDoUnlock(String doUnlock)
	{
		this.doUnlock = doUnlock;
	}

	
	
	
	
	
	
	
	
	
}
