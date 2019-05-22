package com.caforerof.calculator;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.caforerof.calculator.controller.OperandController;
import com.caforerof.calculator.models.entity.Operand;
import com.caforerof.calculator.models.services.IOperandService;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class OperandControllerTests {

    @InjectMocks
    private OperandController testingObject;
	
	@Autowired
    private MockMvc mockMvc;

    @Mock
    private IOperandService operandService;

    @Before
    public void initMocks(){
        MockitoAnnotations.initMocks(this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(testingObject).build();
    }
    
	@Test
	public void setOperandServiceSuccessfullResponseTest() throws Exception {

		double operand = 1.0;
		long sessionId = 0;
        Operand mockOperand = Mockito.mock(Operand.class);
		Mockito.when(operandService.createOperand(sessionId, operand)).thenReturn(mockOperand);
		this.mockMvc.perform(get("/calculatorApi/addOperand/" + operand + "/" + sessionId))
        .andDo(print()).andExpect(status().isOk())
        .andExpect(jsonPath("$.responseCode").value(0));
	}
	
	@Test
	public void setOperandServiceFailureResponseTest() throws Exception {
		String operand = "suma";
		long sessionId = 0;
		this.mockMvc.perform(get("/calculatorApi/addOperand/" + operand + "/" + sessionId))
        .andDo(print()).andExpect(status().is4xxClientError());
	}
	
	@Test
	public void setOperandServiceNoSessionIdFailureResponseTest() throws Exception {
		String operand = "";
		this.mockMvc.perform(get("/calculatorApi/addOperand/" + operand))
        .andDo(print()).andExpect(status().is4xxClientError());
	}
}
