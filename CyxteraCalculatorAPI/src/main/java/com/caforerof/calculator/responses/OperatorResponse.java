package com.caforerof.calculator.responses;

public class OperatorResponse extends Response {
	
	private String operator;
	private Double result;
	/**
	 * @return the operator
	 */
	public String getOperator() {
		return operator;
	}
	/**
	 * @param operator the operator to set
	 */
	public void setOperator(String operator) {
		this.operator = operator;
	}
	/**
	 * @return the result
	 */
	public Double getResult() {
		return result;
	}
	/**
	 * @param result the result to set
	 */
	public void setResult(Double result) {
		this.result = result;
	}
	

	
}
