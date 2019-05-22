package com.caforerof.calculator.models.services;

import java.util.List;

import com.caforerof.calculator.models.entity.Operand;
import com.caforerof.calculator.responses.CustomErrorCalcException;

public class UtilService {

	public Double operation(List<Operand> operands, String operation) {
		Double result = null;
		switch (operation) {
		case "suma":
			result = this.plus(operands);
			break;
		case "resta":
			result = this.subs(operands);
			break;
		case "multiplicacion":
			result = this.mult(operands);
			break;
		case "division":
			result = this.div(operands);
			break;
		case "potenciacion":
			result = this.pow(operands);
			break;
		default:
			throw new CustomErrorCalcException("Operador inválido", 4);
		}
		return result;
	}
		
	public Double plus(List<Operand> operands) {
		Double result = operands.get(0).getValue();
		for (int i = 1; i < operands.size(); i++) {
			result += operands.get(i).getValue();
		}
		return result;	
	}
	
	public Double subs(List<Operand> operands) {
		Double result = operands.get(0).getValue();
		for (int i = 1; i < operands.size(); i++) {
			result -= operands.get(i).getValue();
		}
		return result;		
	}
	
	public Double mult(List<Operand> operands) {
		Double result = operands.get(0).getValue();
		for (int i = 1; i < operands.size(); i++) {
			result *= operands.get(i).getValue();
		}
		return result;		
	}	
	
	public Double div(List<Operand> operands) {
		Double result = operands.get(0).getValue();
		for (int i = 1; i < operands.size(); i++) {
			result /= operands.get(i).getValue();			
		}
		return result;		
	}
	
	public Double pow(List<Operand> operands) {
		Double result = operands.get(0).getValue();
		for (int i = 1; i < operands.size(); i++) {
			result = Math.pow(result, operands.get(0).getValue());
		}
		return result;		
	}		
}
