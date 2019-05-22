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
		this.mockMvc.perform(get("/getSession"))
        .andDo(print()).andExpect(status().isOk())
        .andExpect(jsonPath("$.sessionId").exists());
	}
	
	@Test
	public void setOperatorServiceSuccessfullResponseTest() throws Exception {
		this.mockMvc.perform(get("/addOperator").param("value", "1.0"))
        .andDo(print()).andExpect(status().isOk())
        .andExpect(jsonPath("$.operator").value("1.0"));
	}
	
	@Test
	public void setOperandServiceSuccessfullResponseTest() throws Exception {
		this.mockMvc.perform(get("/addOperand").param("value", "+"))
        .andDo(print()).andExpect(status().isOk())
        .andExpect(jsonPath("$.operand").value("+"));
	}

}
