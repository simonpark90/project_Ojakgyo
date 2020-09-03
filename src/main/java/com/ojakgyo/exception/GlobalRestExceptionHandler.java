package com.ojakgyo.exception;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;


@RestControllerAdvice
//@Slf4j
public class GlobalRestExceptionHandler {

	@ExceptionHandler(value = { RuntimeException.class })
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	public Map<String, Object> internalServerError(Exception e) {

		Map<String, Object> resultMap = new HashMap<>();
		resultMap.put("msg", e.getMessage());
		return resultMap;

	}

	@ResponseBody
	@ExceptionHandler(ArtworkNotFoundException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	String ArtworkNotFoundHandler(ArtworkNotFoundException ex) {
		return ex.getMessage();
	}

}
