package com.example.project;

import com.example.project.application.shiro.ShiroDbRealm;
import com.example.project.application.shiro.ShiroSessionCheckFilter;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.filter.authc.AnonymousFilter;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.filter.DelegatingFilterProxy;

import javax.servlet.Filter;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

@Configuration
@EnableAutoConfiguration
@ComponentScan
@MapperScan(basePackages = "com.example.project.dao")
public class Application {

    public static void main(String[] args) throws Exception {
        SpringApplication.run(Application.class, args);
    }

    @Bean
    public ShiroDbRealm myRealm() {
        return new ShiroDbRealm();
    }

    @Bean
    public DefaultWebSecurityManager securityManager() {
        return new DefaultWebSecurityManager(myRealm());
    }

    @Bean
    public LifecycleBeanPostProcessor lifecycleBeanPostProcessor() {
        return new LifecycleBeanPostProcessor();
    }

//    @Bean
//    public DelegatingFilterProxy filter() {
//        DelegatingFilterProxy proxy = new DelegatingFilterProxy();
//        proxy.setTargetFilterLifecycle(true);
//        return proxy;
//    }
//
//    @Bean
//    public FilterRegistrationBean filterRegistrationBean() {
//        FilterRegistrationBean registration = new FilterRegistrationBean();
//        registration.setFilter(filter());
//        registration.addUrlPatterns("/*");
//        return registration;
//    }

    /**
     * TODO: 如果将自定义filter交给spring管理，出现shiro默认filter功能失效，原因待查
     *
     * @return
     */
//    @Bean
//    public ShiroSessionCheckFilter sessionCheckFilter() {
//        return new ShiroSessionCheckFilter();
//    }
    @Bean
    public ShiroFilterFactoryBean shiroFilterFactoryBean() {
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        shiroFilterFactoryBean.setSecurityManager(securityManager());
        Map<String, Filter> filterMap = new HashMap<>();
        filterMap.put("sessioncheck", new ShiroSessionCheckFilter());
        shiroFilterFactoryBean.setFilters(filterMap);
//        shiroFilterFactoryBean.getFilters().put("sessioncheck", sessionCheckFilter());
        Map<String, String> filterChainDefinitionMap = new LinkedHashMap<>();
        filterChainDefinitionMap.put("/sign/in", "anon");
        filterChainDefinitionMap.put("/blog/list", "anon");
        filterChainDefinitionMap.put("/blog/one/*", "anon");
        filterChainDefinitionMap.put("/blog/image/upload", "anon");  // 博客图片上传
        filterChainDefinitionMap.put("/blog/draft/save", "anon");     // 保存博客草稿
        filterChainDefinitionMap.put("/image/blog/*", "anon");
        filterChainDefinitionMap.put("/**", "sessioncheck");
        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);
        return shiroFilterFactoryBean;

    }

//    @Bean
//    public FilterRegistrationBean sessionCheckFilterRegistration() {
//        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
//        filterRegistrationBean.setFilter(sessionCheck());
//        filterRegistrationBean.setEnabled(true);
//        return filterRegistrationBean;
//    }


}