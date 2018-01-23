package com.hyll.sys.entity;

/*
* 类描述：
* @auther linzf
* @create 2017/12/28 0028 
*/
public class UserInfo {

    // 手机号
    private String phoneNum;
    // 密码
    private String password;
    // 推荐人id
    private String inviterId;
    // 应用类型(1:联货宝、2:车主、3:司机)
    private String platform;
    // 用户状态(1:启用、2:冻结)
    private String state;
    // 手机验证码
    private String verifyCode;

    public String getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getInviterId() {
        return inviterId;
    }

    public void setInviterId(String inviterId) {
        this.inviterId = inviterId;
    }

    public String getPlatform() {
        return platform;
    }

    public void setPlatform(String platform) {
        this.platform = platform;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getVerifyCode() {
        return verifyCode;
    }

    public void setVerifyCode(String verifyCode) {
        this.verifyCode = verifyCode;
    }
}
