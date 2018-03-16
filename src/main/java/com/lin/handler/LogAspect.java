//package com.lin.handler;
//
//import java.lang.annotation.Annotation;
//import java.lang.reflect.Method;
//import java.util.Map;
//
//import org.apache.commons.lang3.StringUtils;
//import org.aspectj.lang.JoinPoint;
//import org.aspectj.lang.ProceedingJoinPoint;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.stereotype.Component;
//
//import com.google.common.collect.Maps;
//
//import cn.stu.edu.lin.model.log.AbstractLogBean;
//import cn.stu.edu.lin.model.log.DataAccessLogBean;
//import cn.stu.edu.lin.model.log.DataAccessType;
//import cn.stu.edu.lin.model.log.ServiceAccessLogBean;
//import cn.sut.edu.lin.annotation.Loggable;
//
///**
// * 
// * ClassName: LogAspect <br/>
// * Function: 日志切面 <br/>
// */
//@Component
//public class LogAspect {
//
//	private static final Map<Class<?>, Logger> loggerHolder = Maps.newConcurrentMap();
//
//	/**
//	 * 
//	 * around:根据日志注解类型为方法调用记录日志 <br/>
//	 */
//	public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
//		AbstractLogBean logBean = null;
//
//		Method method = null;
//		// method =
//		// MethodSignature.class.cast(joinPoint.getSignature()).getMethod();
//		Annotation[] annotations = method.getAnnotations();
//		for (Annotation annotation : annotations) {
//			if (annotation instanceof Loggable) {
//				Loggable loggable = Loggable.class.cast(annotation);
//				logBean = getLogBean(loggable.value(), joinPoint);
//				break;
//			}
//		}
//
//		Object retVal = null;
//		if (logBean == null) {
//			retVal = joinPoint.proceed(joinPoint.getArgs());
//		} else {
//			logBean.setEventName(StringUtils.isBlank(logBean.getEventName()) ? method.getName()
//					: logBean.getEventName() + "_" + method.getName());
//			logBean.setRequest(joinPoint.getArgs());
//			try {
//				retVal = joinPoint.proceed(joinPoint.getArgs());
//			} catch (Exception e) {
//				logBean.setE(e);
//				throw e;
//			} finally {
//				logBean.setResponse(retVal);
//				Logger logger = getLogger(joinPoint.getTarget().getClass());
//				if (logBean.getE() == null) {
//					logger.info(logBean.toString());
//				} else {
//					logger.error(logBean.toString());
//				}
//			}
//		}
//		return retVal;
//	}
//
//	private AbstractLogBean getLogBean(DataAccessType type, JoinPoint joinPoint) {
//		AbstractLogBean logBean = null;
//		switch (type.getValue()) {
//		case "MySQL":
//			logBean = DataAccessLogBean.newDataAccessMysqlLogBean();
//			break;
//		case "Http":
//			logBean = DataAccessLogBean.newDataAccessHttpLogBean();
//			break;
//		case "Redis":
//			logBean = DataAccessLogBean.newDataAccessRedisLogBean();
//			break;
//		default:
//			logBean = new ServiceAccessLogBean(joinPoint.getTarget().getClass().getSimpleName(), "TCP", null, null);
//		}
//		return logBean;
//	}
//
//	private Logger getLogger(Class<?> clazz) {
//		if (!loggerHolder.containsKey(clazz)) {
//			loggerHolder.put(clazz, LoggerFactory.getLogger(clazz));
//		}
//		return loggerHolder.get(clazz);
//	}
//}
