package com.caforerof.calculator;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class CyxteraCalculatorApiApplicationTests {

	@Autowired
    private MockMvc mockMvc;

	@Test
	public void getSessionIdServiceSuccessfullResponseTest() throws Exception {
		this.mockMvc.perform(get("/calculatorApi/getSessionId"))
        .andDo(print()).andExpect(status().isOk())
        .andExpect(jsonPath("$.sessionId").exists());
	}
	
	@Test
	public void addOperandServiceSuccessfullResponseTest() throws Exception {
		double operand = 1.0;
		this.mockMvc.perform(get("/calculatorApi/addOperand/" + operand))
        .andDo(print()).andExpect(status().isOk())
        .andExpect(jsonPath("$.operand").value(operand));
	}
	
	@Test
	public void setOperatorServiceSuccessfullResponseTest() throws Exception {
		String operator = "suma";
		this.mockMvc.perform(get("/calculatorApi/setOperator/" + operator))
        .andDo(print()).andExpect(status().isOk())
        .andExpect(jsonPath("$.operator").value(operator));
	}
	
	@Test
	public void getSessionIdServiceFailBdTest() throws Exception {
		this.mockMvc.perform(get("/calculatorApi/getSessionId"))
        .andDo(print()).andExpect(status().is5xxServerError())
        .andExpect(jsonPath("$.sessionId").exists());
	}
	
	@Test
	public void addOperandServiceFailStringTest() throws Exception {
		String operand = "stringTest";
		this.mockMvc.perform(get("/calculatorApi/addOperand/" + operand))
        .andDo(print()).andExpect(status().is5xxServerError())
        .andExpect(jsonPath("$.operand").value(operand));
	}
	
	@Test
	public void setOperatorServiceFailNotStringTest() throws Exception {
		long operator = 1;
		this.mockMvc.perform(get("/calculatorApi/setOperator/" + operator))
        .andDo(print()).andExpect(status().is5xxServerError())
        .andExpect(jsonPath("$.operator").value(operator));
	}
	
	@SuppressWarnings("unused")
	@Test
	public void validateAddNumbers() throws Exception{
		double[] operand = {1.0, 2.0, 3.0};
		long sessionId = 0;
		this.mockMvc.perform(get("/calculatorApi/getSessionId"))
        .andDo(print()).andExpect(status().isOk())
        .andExpect(jsonPath("$.sessionId").exists());
		
		this.mockMvc.perform(get("/calculatorApi/addOperand/" + operand[0]))
        .andDo(print()).andExpect(status().isOk())
        .andExpect(jsonPath("$.operand").value(operand[0]));
		this.mockMvc.perform(get("/calculatorApi/addOperand/" + operand[1]))
        .andDo(print()).andExpect(status().isOk())
        .andExpect(jsonPath("$.operand").value(operand[1]));
		this.mockMvc.perform(get("/calculatorApi/addOperand/" + operand[2]))
        .andDo(print()).andExpect(status().isOk())
        .andExpect(jsonPath("$.operand").value(operand[2]));
	}

}