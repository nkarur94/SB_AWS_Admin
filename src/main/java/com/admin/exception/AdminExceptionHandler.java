package com.admin.exception;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.admin.DTO.ResponseDTO;

@ControllerAdvice
public class AdminExceptionHandler {

	@ExceptionHandler(AdminCustomException.class)
	public ResponseEntity<ResponseDTO> handleCustomException(AdminCustomException exception){
		ResponseDTO response = new ResponseDTO("error while running API",exception.getMessage());
		return new ResponseEntity<ResponseDTO>(response,HttpStatus.BAD_REQUEST);
	}
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<ResponseDTO> handleMethodNotValidException(MethodArgumentNotValidException exception){
		List<ObjectError> errorList = exception.getBindingResult().getAllErrors();
		List<String> errorMessage = errorList.stream().map(objerr -> objerr.getDefaultMessage())
				.collect(Collectors.toList());
		ResponseDTO responseDTO = new ResponseDTO("Exception while processing REST request", errorMessage);
		return new ResponseEntity<ResponseDTO>(responseDTO, HttpStatus.BAD_REQUEST);
	}
	
}
