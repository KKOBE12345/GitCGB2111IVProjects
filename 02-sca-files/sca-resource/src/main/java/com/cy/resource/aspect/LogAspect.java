package com.cy.resource.aspect;


import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Slf4j
public class LogAspect {
    /**
     * 这个AOP用来统计文件上传的开始时间和结束时间
     * 1.切入点（执行拓展业务的入口）@PointCut
     * 2.通知方法（封装拓展业务逻辑）@Around
     * */
    @Pointcut("@annotation(com.cy.resource.aspect.RequiredLog)")
    //切入点表达式  表达式中要描述在哪些地方定义切入点  这里采用注解表达式
    public void doLog(){}

    /**
     * 在指定的切入点方法上执行@Around注解描述的方法
     * joinPoint连接点  封装了你要执行的执行链信息*/

    @Around("doLog()")
    public Object doAround(ProceedingJoinPoint joinPoint) throws Throwable {
        log.info("Around.Before {}", System.currentTimeMillis());
        Object result = joinPoint.proceed();//执行我们的目标执行方法
        log.info("Around.After {}", System.currentTimeMillis());

        return result;    //目标方法的返回值
    }
}
