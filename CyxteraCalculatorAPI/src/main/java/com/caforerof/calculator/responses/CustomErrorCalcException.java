package com.caforerof.calculator.responses;

public class CustomErrorCalcException extends RuntimeException {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Integer errNum;

	public CustomErrorCalcException () {

    }
	
	public CustomErrorCalcException (String message, Integer errNum) {
		super (message);
		this.setErrNum(errNum);
    }	

    public CustomErrorCalcException (String message) {
        super (message);
    }

    public CustomErrorCalcException (Throwable cause) {
        super (cause);
    }

    public CustomErrorCalcException (String message, Throwable cause) {
        super (message, cause);
    }

	public Integer getErrNum() {
		return errNum;
	}

	public void setErrNum(Integer errNum) {
		this.errNum = errNum;
	}

}