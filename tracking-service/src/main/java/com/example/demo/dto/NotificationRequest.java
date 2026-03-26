package com.example.demo.dto;

public class NotificationRequest {

    private String email;
    private String phone;
    private String message;

    public NotificationRequest() {}

    public NotificationRequest(String email, String phone, String message) {
        this.email = email;
        this.phone = phone;
        this.message = message;
    }

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

    
}
