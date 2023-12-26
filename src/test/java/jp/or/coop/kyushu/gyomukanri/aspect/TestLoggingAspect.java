package jp.or.coop.kyushu.gyomukanri.aspect;

import java.lang.reflect.Method;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.CodeSignature;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * <pre>
 * テスト用ログ出力処理
 * </pre>
 */
@Aspect
@Component
@Order(1)
public class TestLoggingAspect {

	/** ロガー  */
	private static final Logger LOGGER = LoggerFactory.getLogger(TestLoggingAspect.class);

	/** リクエスト */
	@Autowired
	private HttpServletRequest request;

	/** レスポンス */
	@Autowired
	private HttpServletResponse response;

	/** JSONマッパー */
	@Autowired
	private ObjectMapper objectMapper;

	/**
	 * 対象パターン（コントローラ）
	 */
	@Pointcut("execution(* coop.kyushu.gyomu_kanri.controller..*.*(..))")
	public void controllerTargetPattern() {
	}

	/**
	 * 除外パターン（コントローラ）
	 */
	@Pointcut("execution(* coop.kyushu.gyomu_kanri.controller..Olcn01010mController.*(..))"
			+ " || execution(* coop.kyushu.gyomu_kanri.controller.common..*.*(..))"
			+ " || execution(* coop.kyushu.gyomu_kanri.controller.components..*.*(..))")
	public void controllerIgnorePattern() {
	}

	@Before("controllerTargetPattern() && !controllerIgnorePattern()")
	public void requestLog(JoinPoint jp) {

		LOGGER.debug("[Request Log]=====================================");
		LOGGER.debug("URI : " + request.getRequestURI());

		String[] methodArgNames = ((CodeSignature) jp.getSignature()).getParameterNames();
		Object[] methodArgValues = jp.getArgs();

		for (int i = 0; i < methodArgNames.length; i++) {

			String json = "null";

			try {
				json = objectMapper.writeValueAsString(methodArgValues[i]);
			} catch (JsonProcessingException e1) {
				continue;
			}

			LOGGER.debug(methodArgNames[i] + " : " + json);
		}

		LOGGER.debug("==================================================");
	}

	@AfterReturning(pointcut = "controllerTargetPattern() && !controllerIgnorePattern()", returning = "ret")
	public void responseLog(JoinPoint jp, Object ret) {

		LOGGER.debug("[Response Log]====================================");

		String json = "null";

		try {
			json = objectMapper.writeValueAsString(ret);
		} catch (JsonProcessingException e1) {
		}

		LOGGER.debug("Return : " + json);

		ResponseStatus responseStatus;
		int statusCode = response.getStatus();

		try {
			Method method = ((MethodSignature) jp.getSignature()).getMethod();
			responseStatus = method.getAnnotation(ResponseStatus.class);

			if (responseStatus != null) {
				statusCode = responseStatus.value().value();
			}
		} catch (SecurityException e) {
		}

		LOGGER.debug("StatusCode : " + statusCode);
		LOGGER.debug("==================================================");
	}

	@After("execution(* coop.kyushu.gyomu_kanri.handler.GlobalExceptionHandler.*(..))")
	public void responseLogException(JoinPoint jp) {

		LOGGER.debug("[Exception Log]===================================");
		LOGGER.debug("StatusCode : " + response.getStatus());
		LOGGER.debug("==================================================");
	}

	/**
	 * 対象パターン（コントローラ）
	 */
	@Pointcut("execution(* coop.kyushu.gyomu_kanri.service..*rService.*(..))")
	public void serviceTargetPattern() {
	}

	@Before("serviceTargetPattern()")
	public void serviceParameterLog(JoinPoint jp) {

		LOGGER.debug("[Service Parameter Log]===========================");

		Object[] methodArgValues = jp.getArgs();
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
		DateFormat timestampFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss.SSS");

		for (int i = 0; i < methodArgValues.length; i++) {

			String json = "null";

			if (methodArgValues[i] != null) {
				if (methodArgValues[i] instanceof Date) {

					json = dateFormat.format(methodArgValues[i]);

				} else if (methodArgValues[i] instanceof Timestamp) {

					json = timestampFormat.format(methodArgValues[i]);

				} else {
					try {
						json = objectMapper.writeValueAsString(methodArgValues[i]);
					} catch (JsonProcessingException e1) {
						continue;
					}
				}
			}

			LOGGER.debug("arg" + i + " : " + json);
		}

		LOGGER.debug("==================================================");
	}

	@AfterReturning(pointcut = "serviceTargetPattern()", returning = "ret")
	public void serviceResultLog(JoinPoint jp, Object ret) {

		LOGGER.debug("[Service Result Log]==============================");

		String json = "null";

		try {
			json = objectMapper.writeValueAsString(ret);
		} catch (JsonProcessingException e1) {
		}

		LOGGER.debug("Return : " + json);

		LOGGER.debug("==================================================");
	}

}
