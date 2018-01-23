package com.hyll.sys.controller;

import com.hyll.sys.entity.UserInfo;
import com.hyll.sys.util.UserCenterUtil;
import net.sf.json.JSONObject;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

/*
* 类描述：
* @auther linzf
* @create 2017/12/28 0028 
*/
@Controller
public class DrCenterController {

    /**
     * 功能描述：跳转到注册页面
     * @param entity
     * @return
     */
    @RequestMapping(value = "/toRegister",method = RequestMethod.GET,produces = MediaType.APPLICATION_JSON_VALUE)
    public String toRegister(UserInfo entity,Model model){
        model.addAttribute("entity",entity);
        model.addAttribute("address",UserCenterUtil.getGoodluckAddress());
        return "register";
    }


    /**
     * 功能描述：实现获取验证码
     * @return
     */
    @RequestMapping(value = "/getPhoneMsg",method = RequestMethod.POST,produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public Map<String,Object> getPhoneMsg(UserInfo entity){
        Map<String,Object> result = new HashMap<String,Object>();
        JSONObject jobj = UserCenterUtil.getPhoneMsg(entity.getPhoneNum(),"register","2");
        if(jobj==null){
            result.put("success",false);
            result.put("msg","获取手机验证码失败！");
        }
        result.put("success",jobj.getBoolean("success"));
        result.put("msg",jobj.getString("message"));
        return result;
    }


    /**
     * 功能描述：实现用户注册
     * @param entity
     * @return
     */
    @RequestMapping(value = "/mobileRegister",method = RequestMethod.POST,produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public Map<String,Object> mobileRegister(UserInfo entity){
        Map<String,Object> result = new HashMap<String,Object>();
        entity.setPlatform("2");
        entity.setState("1");
        JSONObject jobj =UserCenterUtil.mobileRegister(entity);
        if(jobj==null){
            result.put("success",false);
            result.put("msg","用户注册失败！");
        }
        result.put("success",jobj.getBoolean("success"));
        result.put("msg",jobj.getString("message"));
        return result;
    }


    /**
     * 功能描述：实现验证手机号码
     * @param entity
     * @return
     */
    @RequestMapping(value = "/checkPhoneRegister",method = RequestMethod.POST,produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public Map<String,Object> checkPhoneRegister(UserInfo entity){
        Map<String,Object> result = new HashMap<String,Object>();
       // UserCenterUtil.checkPhoneRegister("18559405027","1");
        System.out.println(UserCenterUtil.getPhoneMsg("18559405027","register","2").getString("message"));
        return result;
    }

}
