package com.luge.controller;

import com.luge.domain.SysLog;
import com.luge.service.SysLogService;
import com.luge.utils.DateUtils;
import org.aopalliance.intercept.Joinpoint;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.lang.annotation.Annotation;
import java.lang.reflect.Executable;
import java.lang.reflect.Method;
import java.util.Date;

@Component
@Aspect
public class LogAop {
    private Date visitTime; //访问时间
    private long time; // 访问持续时间
    private Class executionClass; // 访问的类
    private Method executionMethod; // 访问的方法
    private String url; // 获取访问的url
    private String ip; // 获取ip
    private String username; // 获取用户名
    @Autowired
    private HttpServletRequest request;
    @Autowired
    private SysLogService sysLogService;
    // 前置通知
    @Before("execution(* com.luge.controller.*.*(..))")
    public void doBefore(JoinPoint jp) throws NoSuchMethodException {
        visitTime = new Date(); // 访问时间
        executionClass = jp.getTarget().getClass(); // 获取访问的类
        /**
         * 获取访问的方法
         */
        // 获取访问的方法名称
        String methodName = jp.getSignature().getName();
        // 获取访问的方法的参数
        Object[] args = jp.getArgs();
        // 判断方法是否有参数
        if (args == null || args.length == 0) {
            // 当前方法无参
            executionMethod = executionClass.getMethod(methodName);
        } else {
            // 当前方法带参
            Class[] classArgs = new Class[args.length]; // 创建Class类型数组
            for (int i = 0; i < args.length; i++) {
                classArgs[i] = args[i].getClass();
            }
            executionMethod = executionClass.getMethod(methodName, classArgs);
        }
    }

    // 后置通知
    @After("execution(* com.luge.controller.*.*(..))")
    public void doAfter(JoinPoint jp) {
        // 获取访问的持续时间
        time = new Date().getTime() - visitTime.getTime();
        // 获取访问的url
        if (executionClass!= null && executionMethod != null && executionClass != LogAop.class) {
            // 首先获取类上的@RequestMapping中的path值
            RequestMapping classAnnotation = (RequestMapping) executionClass.getAnnotation(RequestMapping.class);
            if (classAnnotation != null) {
                String[] classPaths = classAnnotation.path();
                String classPath = classPaths[0];
                // 然后获取方法上的@RequestMapping中的path值
                RequestMapping methodAnnotation = executionMethod.getAnnotation(RequestMapping.class);
                if (methodAnnotation != null) {
                    String[] methodPaths = methodAnnotation.path();
                    String methodPath = methodPaths[0];
                    url = classPath + methodPath; // 将两个path拼接即得到访问的url
                }
            }

        }
        // 获取访问的ip地址
        ip = request.getRemoteAddr();
        // 获取当前操作的用户
        SecurityContext context = (SecurityContext) request.getSession().getAttribute("SPRING_SECURITY_CONTEXT");
        User user = (User) context.getAuthentication().getPrincipal();
        username = user.getUsername();
        /**
         * 封装SysLog对象
         */
        SysLog sysLog = new SysLog();
        sysLog.setVisitTime(visitTime);
        sysLog.setVisitTimeStr(DateUtils.date2String(visitTime, "yyyy-MM-dd HH-mm-ss"));
        sysLog.setUsername(username);
        sysLog.setIp(ip);
        sysLog.setUrl(url);
        sysLog.setExecutionTime(time);
        sysLog.setMethod("[类名]：" + executionClass.getName() + "[方法名]：" + executionMethod.getName());
        /**
         * 调用service保存日志
         */
        sysLogService.save(sysLog);
    }
}
