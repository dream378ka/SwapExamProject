
package com.scb.exam.springboot.bean;

public class SwapResponse {
	private String status;
	private String description;
	private DataBean input;
	private DataBean output;
	
	public SwapResponse() {
		input = new DataBean();
		output = new DataBean();
	}
	
	public SwapResponse(String status, String description, DataBean input, DataBean output) {
		this.status = status;
		this.description = description;
		this.input = input;
		this.output = output;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public DataBean getOutput() {
		return output;
	}

	public void setOutput(DataBean output) {
		this.output = output;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public DataBean getInput() {
		return input;
	}

	public void setInput(DataBean input) {
		this.input = input;
	}

}