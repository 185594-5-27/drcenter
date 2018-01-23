package com.hyll.sys.util;

import com.hyll.sys.entity.UserInfo;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/*
* 类描述：
* @auther linzf
* @create 2017/12/28 0028 
*/
@Component
public class UserCenterUtil {

    // 用户注册中心的地址
    private static String userCenterAddr;
    // 注册协议地址
    private static String goodluckAddress;

    @Value("${goodluck.address}")
    public void setGoodluckAddress(String goodluckAddress) {
        UserCenterUtil.goodluckAddress = goodluckAddress;
    }


    @Value("${userCenter.address}")
    public void setUserCenterAddr(String userCenterAddr) {
        UserCenterUtil.userCenterAddr = userCenterAddr;
    }

    // 验证用户手机号是否已经注册的地址
    private static final String CHECK_PHONE_REGISTER = "/api/checkPhoneRegister";
    // 获取手机验证码
    private static final String GET_PHONE_MSG = "/api/mobileCode";
    // 实现用户注册
    private static final String REGISTER_USER = "/api/mobileRegister";

    /**
     * 功能描述：获取注册协议的地址
     * @return
     */
    public static String getGoodluckAddress(){
        return goodluckAddress;
    }

    /**
     * 功能描述：实现用户注册功能
     * @param entity
     * @return
     */
    public static JSONObject mobileRegister(UserInfo entity){
        JSONObject requestJson = new JSONObject();
        requestJson.put("phoneNum", entity.getPhoneNum());
        requestJson.put("password", getMd5(entity.getPassword()));
        requestJson.put("inviterId", entity.getInviterId());
        requestJson.put("platform", entity.getPlatform());
        requestJson.put("state", entity.getState());
        requestJson.put("verifyCode", entity.getVerifyCode());
        String resultStr = HttpClientUtil.doPost(userCenterAddr + REGISTER_USER, requestJson);
        if(!StringUtils.isEmpty(resultStr)){
            JSONObject resultJson = JSONObject.fromObject(resultStr);
            return resultJson;
        }
        return null;
    }

    /**
     * 功能描述：获取手机验证码
     * @param phoneNum
     * @param serviceType
     * @param platform
     * @return 若获取成功则返回信息，反之则返回null
     */
    public static JSONObject getPhoneMsg(String phoneNum,String serviceType,String platform){
        JSONObject requestJson = new JSONObject();
        requestJson.put("phoneNum", phoneNum);
        requestJson.put("serviceType", serviceType);
        requestJson.put("platform", platform);
        String resultStr = HttpClientUtil.doPost(userCenterAddr + GET_PHONE_MSG, requestJson);
        if(!StringUtils.isEmpty(resultStr)){
            JSONObject resultJson = JSONObject.fromObject(resultStr);
            return resultJson;
        }
        return null;
    }

    /**
     * 功能描述：查看手机号是否已注册。
     * @param phoneNum
     * @param platfrom
     * @return 若已注册，返回对应UserId,否则-1
     */
    public static Integer checkPhoneRegister(String phoneNum,String platfrom){
        JSONObject requestJson = new JSONObject();
        requestJson.put("phoneNum", phoneNum);
        requestJson.put("platform", platfrom);
        String resultStr = HttpClientUtil.doPost(userCenterAddr + CHECK_PHONE_REGISTER, requestJson);
        if(!StringUtils.isEmpty(resultStr)){
            JSONObject resultJson = JSONObject.fromObject(resultStr);
            if(!StringUtils.isEmpty(resultJson)){
                if(resultJson.getBoolean("success")){
                    return resultJson.getInt("result");
                }
            }
        }
        return null;
    }


    /**
     * 功能描述：实现密码的加密
     * @param str
     * @return
     */
    public static String getMd5(String str) {
        if(str==null||"".equals(str)){
            return null;
        }
        StringBuffer buf = null;
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(str.getBytes());
            byte b[] = md.digest();
            int i;
            buf = new StringBuffer("");
            for (int offset = 0; offset < b.length; offset++) {
                i = b[offset];
                if (i < 0)
                    i += 256;
                if (i < 16)
                    buf.append("0");
                buf.append(Integer.toHexString(i));
            }
            return buf.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return null;

    }


}
