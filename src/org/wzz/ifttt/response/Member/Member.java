package org.wzz.ifttt.response.Member;

public class Member {
	private long authCode;
	private int memberCount;
	private String[] userName;
	private String[] nickName;
	private String[] mailAddress;
	private String[] userState;
	private String[] userLevel;
	private String[] score;
 	//and so on
	
	
	private static int MAXLENGTH = 100;
	
	public Member() {
		memberCount = 0;
		userName = new String[MAXLENGTH];
		nickName = new String[MAXLENGTH];
		mailAddress = new String[MAXLENGTH];
		userState = new String[MAXLENGTH];
		userLevel = new String[MAXLENGTH];
		score = new String[MAXLENGTH];
	}
	
	public int getMemberCountByAuthcode(long authcode) {
		memberCount = 1;//Wei
		return memberCount;
	}
	
	public void getDataByAuthcode(long authcode) {
		String[] tempSet = null;//Wei
		for(int i=0;i<memberCount;i++) {
			userName[i] = tempSet[0+6*i];
			nickName[i] = tempSet[1+6*i];
			mailAddress[i] = tempSet[2+6*i];
			userState[i] = tempSet[3+6*i];
			userLevel[i] = tempSet[4+6*i];
			score[i] = tempSet[5+6*i];		
		}
	}
	
	public String getUserName(int i) {
		return userName[i];
	}
	
	public String getNickName(int i) {
		return nickName[i];
	}
	
	public String getMailAddress(int i) {
		return mailAddress[i];
	}
	
	public String getUserState(int i) {
		return userState[i];
	}
	
	public String getUserLevel(int i) {
		return userLevel[i];
	}
	
	public String getScore(int i) {
		return score[i];
	}

	public long getAuthCode() {
		return authCode;
	}

	public void setAuthCode(long authCode) {
		this.authCode = authCode;
	}
}
