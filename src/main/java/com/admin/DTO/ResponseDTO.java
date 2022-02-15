package com.admin.DTO;


import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
public class ResponseDTO {
	
	
	public String message;
	public Object data;
	public String token;

	public ResponseDTO(String message) {
		super();
		this.message = message;
	}

	public ResponseDTO(String message, Object data) {
		super();
		this.message = message;
		this.data = data;
	}
	
	

}
