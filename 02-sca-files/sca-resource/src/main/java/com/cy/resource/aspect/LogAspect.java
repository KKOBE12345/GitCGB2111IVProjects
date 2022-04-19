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

    @Pointcut("@annotation(com.cy.resource.aspect.WorkLog)")
    //切入点表达式  表达式中要描述在哪些地方定义切入点  这里采用注解表达式
    public void doLog02(){}

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

    @Around("doLog02()")
    public Object doAround02(ProceedingJoinPoint joinPoint) throws Throwable {
        try{
            log.info("Around.Before {}", System.currentTimeMillis());
            Object result = joinPoint.proceed();//执行我们的目标执行方法
            log.info("Around.After {}", System.currentTimeMillis());
            log.info("么么哒！！！！！ {}", System.currentTimeMillis());

            return result;    //目标方法的返回值

        }catch(Throwable e){
            log.error("exception,time {}", System.currentTimeMillis());
            throw e;
        }


    }
}




















//package com.cy.resource.aspect;
//
//
//import com.cy.resource.pojo.Log;
//import lombok.extern.slf4j.Slf4j;
//import org.aspectj.lang.ProceedingJoinPoint;
//import org.aspectj.lang.Signature;
//import org.aspectj.lang.annotation.Around;
//import org.aspectj.lang.annotation.Aspect;
//import org.aspectj.lang.annotation.Pointcut;
//import org.aspectj.lang.reflect.MethodSignature;
//import org.codehaus.jackson.map.ObjectMapper;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.stereotype.Component;
//import org.springframework.web.context.request.RequestContextHolder;
//import org.springframework.web.context.request.ServletRequestAttributes;
//
//import javax.servlet.http.HttpServletRequest;
//import java.io.IOException;
//import java.lang.reflect.Method;
//
//@Aspect
//@Component
//@Slf4j
//public class LogAspect {
//    /**
//     * 这个AOP用来统计文件上传的开始时间和结束时间
//     * 1.切入点（执行拓展业务的入口）@PointCut
//     * 2.通知方法（封装拓展业务逻辑）@Around
//     * */
//    @Pointcut("@annotation(com.cy.resource.aspect.RequiredLog)")
//    //切入点表达式  表达式中要描述在哪些地方定义切入点  这里采用注解表达式
//    public void doLog(){}
//
//    @Pointcut("@annotation(com.cy.resource.aspect.WorkLog)")
//    //切入点表达式  表达式中要描述在哪些地方定义切入点  这里采用注解表达式
//    public void doLog02(){}
//
//    /**
//     * 在指定的切入点方法上执行@Around注解描述的方法
//     * joinPoint连接点  封装了你要执行的执行链信息*/
//
//    @Around("doLog()")
//    public Object doAround(ProceedingJoinPoint joinPoint) throws Throwable {
//        int status=1;//状态,初始值为1
//        long time=0;//耗时，默认值为0
//        String error="";
//        //1.获取方法开始执行的时间，并输出
//        long t1=System.currentTimeMillis();
//        log.debug("before.time {}",t1);
//        try {
//            //2.调用目标执行链，这个执行链的终点是你的切入点方法
//            //执行结果为切入点方法的执行结果
//            Object result = joinPoint.proceed();
//            //3.获取方法执行结束的数据，并输出。
//            long t2 = System.currentTimeMillis();
//            log.debug("after.time {}", t2);
//            time=t2-t1;
//            return result;
//        }catch (Throwable e){
//            long t3 = System.currentTimeMillis();
//            log.error("exception.time {}", t3);
//            time=t3-t1;
//            status=0;
//            error=e.getMessage();
//            throw e;
//        }finally{
//            //进行日志记录
//            //saveUserLog(time,status,error,joinPoint);//这里的保存是将日志最后传递给system工程
//        }
//    }
//
//    private void saveUserLog(Long time,int status,String error,ProceedingJoinPoint joinPoint) throws NoSuchMethodException, IOException {
//        //1.获取用户行为日志
//        //1.1获取ip地址(首先要获取请求对象，查spring中获取请求对象的方式)
//        //RequestContextHolder对象是当前线程中请求对象的持有者
//        ServletRequestAttributes requestAttributes =
//                (ServletRequestAttributes)RequestContextHolder.getRequestAttributes();
//        String ip=requestAttributes.getRequest().getRemoteAddr();
//        //1.2获取登录用户名(借助SecurityContextHolder,此对象中存储了用户的认证信息)
//        String username=(String)
//                SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//        //1.3获取用户操作(日志切入点方法上@RequiredLog注解中value的值)
//        //1.3.1获取代理对象对应的目标对象类型
//        Class<?> targetCls = joinPoint.getTarget().getClass();
//        //1.3.2获取目标类型中指定的方法(方法信息可通过joinPoint获取方法签名信息)
//        MethodSignature methodSignature=//方法签名-存储了方法信息的一个对象
//                (MethodSignature) joinPoint.getSignature();
//        Method targetMethod=
//                targetCls.getDeclaredMethod(methodSignature.getName(),
//                        methodSignature.getParameterTypes());
//        //1.3.3获取方法上的RequiredLog注解
//        RequiredLog requiredLog=
//                targetMethod.getAnnotation(RequiredLog.class);
//        //1.3.4获取方法上的RequiredLog注解中的操作名
//        String operation=requiredLog.value();
//        //1.4获取目标方法名(这里要求类全名+方法名)
//        String targetMethodName=
//                String.format("%s.%s",targetCls.getName(),
//                        targetMethod.getName());
//
//        //1.5获取请求参数(客户端传递的实际参数)
//        // String params=joinPoint.getArgs().toString();//不推荐
//        String params=//推荐将参数转换为json格式字符串
//                new ObjectMapper().writeValueAsString(joinPoint.getArgs());
//        //2.封装用户行为日志
//        Log userLog=new Log();
//        userLog.setIp(ip);
//        userLog.setUsername(username);
//        userLog.setCreatedTime(new java.util.Date());
//        userLog.setOperation(operation);
//        userLog.setMethod(targetMethodName);
//        userLog.setParams(params);
//        userLog.setTime(time);
//        userLog.setStatus(status);
//        userLog.setError(error);
//        //3.持久化用户行为日志
//        log.debug("user log: {}", userLog);
//        //remoteLogService.insertLog(userLog);
//    }
//    //@Autowired
//    //private RemoteLogService remoteLogService;
//
//
//    @Around("doLog02()")
//    public Object doAround02(ProceedingJoinPoint joinPoint) throws Throwable {
//        try{
//            log.info("Around.Before {}", System.currentTimeMillis());
//            Object result = joinPoint.proceed();//执行我们的目标执行方法
//            log.info("Around.After {}", System.currentTimeMillis());
//            log.info("么么哒！！！！！ {}", System.currentTimeMillis());
//
//            return result;    //目标方法的返回值
//
//        }catch(Throwable e){
//            log.error("exception,time {}", System.currentTimeMillis());
//            throw e;
//        }
//
//
//    }
//}
