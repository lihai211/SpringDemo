package com.demo.aop;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

@Aspect
public class ProjectMonitorAOP {
	private Logger log = LogManager.getLogger(ProjectMonitorAOP.class);

	@Pointcut(value = "execution(* com.demo.dao.*.*(..)) or execution(* com.demo.service.*.*(..)) or execution(* com.demo.web.controllers.*.*(..))")
	private void pointcut() {
	}

	public void beforeExecute(JoinPoint thisJoinPoint) {
		Object[] obs = thisJoinPoint.getArgs();
		String str = "";
		for (Object ob : obs) {
			str += String.valueOf(ob) + ",";
		}
		log.debug("进入" + thisJoinPoint.getTarget().getClass().getName() + "类"
				+ thisJoinPoint.getSignature().getName() + "方法，参数个数："
				+ thisJoinPoint.getArgs().length + ",参数列表：" + str);
	}

	@AfterReturning(pointcut = "pointcut()", returning = "retVal")
	public void afterReturnExecuet(JoinPoint thisJoinPoint, Object retVal) {
		log.debug(thisJoinPoint.getTarget().getClass().getName() + "类"
				+ thisJoinPoint.getSignature().getName() + "方法执行完毕,返回参数："
				+ retVal);
	}

	public void afterThrowing(JoinPoint thisJointPoint, Exception ex) {
		log.debug(thisJointPoint.getTarget().getClass().getName() + "类"
				+ thisJointPoint.getSignature().getName() + "方法抛出异常：" + ex);
	}

	public void afterExecute(JoinPoint thisJoinPoint) {
		log.debug("退出" + thisJoinPoint.getTarget().getClass().getName() + "类"
				+ thisJoinPoint.getSignature().getName() + "方法");
	}

	@Around("execution(* com.demo.dao.*.*(..)) or execution(* com.demo.service.*.*(..)) or execution(* com.demo.web.controllers.*.*(..))")
	public Object userOperate(ProceedingJoinPoint thisJoinPoint)
			throws Throwable {
		long begin = System.currentTimeMillis();
		Object obj = thisJoinPoint.proceed();// 执行被切的方法
		log.debug(thisJoinPoint.getTarget().getClass().getName() + "类"
				+ thisJoinPoint.getSignature().getName() + "方法执行效率为"
				+ (System.currentTimeMillis() - begin) + "ms");
		return obj;
	}

}
