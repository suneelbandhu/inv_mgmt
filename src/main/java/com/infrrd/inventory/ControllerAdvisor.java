package com.infrrd.inventory;

import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.infrrd.inventory.util.InventoryAlreadyExistsException;
import com.infrrd.inventory.util.InventoryNotFoundException;

@ControllerAdvice
public class ControllerAdvisor extends ResponseEntityExceptionHandler{

	@ExceptionHandler(NullPointerException.class)
	    public Map<String, Object> nullPointerException(NullPointerException ex) {
	        Map<String, Object> body = new LinkedHashMap<>();
	        body.put("timestamp", LocalDateTime.now());
	        body.put("error message", "Null pointer exception");
	        return body;
	    }
	
	@ExceptionHandler(InventoryNotFoundException.class)
	 public Map<String, Object> inventoryNotFoundException(InventoryNotFoundException ex){
	     Map<String, Object> body = new LinkedHashMap<>();
	        body.put("timestamp", LocalDateTime.now());
	        body.put("error message", "inventory not found exception");
	        return body;
	}
	
	@ExceptionHandler(InventoryAlreadyExistsException.class)
	 public Map<String, Object> inventoryAlreadyExistsException(InventoryAlreadyExistsException ex){
	     Map<String, Object> body = new LinkedHashMap<>();
	        body.put("timestamp", LocalDateTime.now());
	        body.put("error message", "inventory already exists exception");
	        return body;
	}
}
