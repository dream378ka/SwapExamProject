
package com.scb.exam.springboot.bean;

public class SwapRequest {
	private String linklist;
	
	public SwapRequest() {

	}
	
	public SwapRequest(String linklist) {
		this.linklist = linklist;
	}

	public String getLinklist() {
		return linklist;
	}

	public void setLinklist(String linklist) {
		this.linklist = linklist;
	}

}