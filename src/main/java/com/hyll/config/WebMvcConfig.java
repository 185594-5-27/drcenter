package com.hyll.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/*
* 类描述：
* @auther linzf
* @create 2017/12/28 0028 
*/
@Configuration
public class WebMvcConfig extends WebMvcConfigurerAdapter {

    /**
     * 重写方法描述：实现在url中输入相应的地址的时候直接跳转到某个地址
     * @param registry
     */
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        // 跳转到联车宝的下载页
        registry.addViewController("/uploadLcb").setViewName("uploadLcb");
        // 跳转到联货宝的下载页
        registry.addViewController("/uploadLhb").setViewName("uploadLhb");
    }

}
