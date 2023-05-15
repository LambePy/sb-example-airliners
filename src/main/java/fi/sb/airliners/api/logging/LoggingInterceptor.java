package fi.sb.airliners.api.logging;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
class LoggingInterceptor implements HandlerInterceptor {
	private final Logger logger = LoggerFactory.getLogger(LoggingInterceptor.class);

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		logger.info("Received request: {} {}", request.getMethod(), request.getRequestURI());
		return true;
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		logger.info("Finished request: {} {} with status code: {}", request.getMethod(), request.getRequestURI(),
				response.getStatus());
	}
}
