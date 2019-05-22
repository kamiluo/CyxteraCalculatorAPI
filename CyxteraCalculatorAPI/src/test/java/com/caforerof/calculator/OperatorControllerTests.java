package com.caforerof.calculator;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.caforerof.calculator.controller.OperatorController;
import com.caforerof.calculator.models.dao.IOperandDao;
import com.caforerof.calculator.models.dao.ISessionIdDao;
import com.caforerof.calculator.models.entity.Operand;
import com.caforerof.calculator.models.entity.Operator;
import com.caforerof.calculator.models.entity.SessionId;
import com.caforerof.calculator.models.services.ISessionIdService;
import com.caforerof.calculator.models.services.OperandServiceImpl;
import com.caforerof.calculator.models.services.OperatorServiceImpl;;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class OperatorControllerTests {

    @InjectMocks
    private OperatorController testingObject;
    
	@Autowired
    private MockMvc mockMvc;

    @Mock
    private ISessionIdService sessionIdService;
    @Mock
    private OperandServiceImpl operandService;
    @Mock
    private OperatorServiceImpl operatorService;
    @Mock
    private IOperandDao operandDao;
    @Mock
    private ISessionIdDao sessionDao;
    
    
    @Before
    public void setUp() throws SQLException {

    	
        MockitoAnnotations.initMocks(this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(testingObject).build();

        Mockito.when(operandDao.save(Mockito.any(Operand.class))).thenReturn(null);
        
        ReflectionTestUtils.setField(operatorService, "operandService", operandService);
        ReflectionTestUtils.setField(operandService, "operandDao", operandDao);
        ReflectionTestUtils.setField(operandService, "sessionDao", sessionDao);
    }
    
    
	@Test
	public void setOperatorServiceFailureResponseTest() throws Exception {
		double operator = 1.0;
		long sessionId = 0;
		this.mockMvc.perform(get("/calculatorApi/setOperator/" + operator + "/" + sessionId))
        .andDo(print()).andExpect(status().isOk())
        .andExpect(jsonPath("$.responseCode").value(3));
	}

	@Test
	public void setOperatorServiceNoSessionIdFailureResponseTest() throws Exception {
		double operator = 1.0;
		this.mockMvc.perform(get("/calculatorApi/setOperator/" + operator))
        .andDo(print()).andExpect(status().is4xxClientError());
	}
	
	@Test
	public void setOperatorServiceAddSuccessfullResponseTest() throws Exception {

		long sessionId = 0;
		String operator = "suma";
		setEnvironment(operator, 6.0, 3.0);

		this.mockMvc.perform(get("/calculatorApi/setOperator/" + operator + "/" + sessionId))
        .andDo(print()).andExpect(status().isOk())
        .andExpect(jsonPath("$.responseCode").value(200)).andExpect(jsonPath("$.result").value(9.0));
	}

	@Test
	public void setOperatorServiceSubstractSuccessfullResponseTest() throws Exception {
		
		long sessionId = 0;
		String operator = "resta";
		setEnvironment(operator, 6.0, 3.0);
		
		this.mockMvc.perform(get("/calculatorApi/setOperator/" + operator + "/" + sessionId))
        .andDo(print()).andExpect(status().isOk())
        .andExpect(jsonPath("$.responseCode").value(200)).andExpect(jsonPath("$.result").value(3.0));
	}
	
	@Test
	public void setOperatorServiceMultiplySuccessfullResponseTest() throws Exception {
		
		long sessionId = 0;
		String operator = "multiplicacion";
		setEnvironment(operator, 6.0, 3.0);
		
		this.mockMvc.perform(get("/calculatorApi/setOperator/" + operator + "/" + sessionId))
        .andDo(print()).andExpect(status().isOk())
        .andExpect(jsonPath("$.responseCode").value(200)).andExpect(jsonPath("$.result").value(18.0));
	}
	
	@Test
	public void setOperatorServiceDivideSuccessfullResponseTest() throws Exception {
		
		long sessionId = 0;
		String operator = "division";
		setEnvironment(operator, 6.0, 3.0);
		
		this.mockMvc.perform(get("/calculatorApi/setOperator/" + operator + "/" + sessionId))
        .andDo(print()).andExpect(status().isOk())
        .andExpect(jsonPath("$.responseCode").value(200)).andExpect(jsonPath("$.result").value(2.0));
	}
	
	private void setEnvironment(String operator, double firstOperand, double secondOperand) throws SQLException {
		
		SessionId mockedSessionId = Mockito.mock(SessionId.class);
        Mockito.when(sessionDao.findById(Mockito.anyLong())).thenReturn(Optional.of(mockedSessionId));
    	Operand mockedOperand = Mockito.mock(Operand.class);
    	Operand mockedOperand2 = Mockito.mock(Operand.class);
    	
    	List<Operand> listOperand = new ArrayList<Operand>();
    	Mockito.when(mockedOperand.getValue()).thenReturn(firstOperand);
    	Mockito.when(mockedOperand2.getValue()).thenReturn(secondOperand);    	
    	listOperand.add(mockedOperand);
    	listOperand.add(mockedOperand2);    	
    	Mockito.when(mockedSessionId.getOperands()).thenReturn(listOperand);

    	Operator mockedOperator = Mockito.mock(Operator.class);
    	Mockito.when(mockedOperator.getValue()).thenReturn(operator);
    	
    	
    	Mockito.when(mockedSessionId.getId()).thenReturn(new Long(0));
		
    	List<Operator> listOperator = new ArrayList<Operator>();
    	listOperator.add(mockedOperator);
    	
		Mockito.when(sessionIdService.createSessionId()).thenReturn(mockedSessionId);
		
		Mockito.when(operandService.createOperand(new Long(0), firstOperand)).thenReturn(mockedOperand);
		Mockito.when(operandService.createOperand(new Long(0), secondOperand)).thenReturn(mockedOperand2);
		Mockito.when(operandService.createOperand(Mockito.anyLong(), Mockito.anyDouble())).thenCallRealMethod();
		
		Mockito.when(operandService.findActiveBySessionId(Mockito.anyLong())).thenReturn(listOperand);
		
		Mockito.when(operatorService.createOperator(new Long(0), operator)).thenReturn(mockedOperator);
		
		Mockito.when(operatorService.findActiveBySessionId(Mockito.anyLong())).thenReturn(listOperator);
		Mockito.when(operatorService.execResult(Mockito.any(Operator.class), Mockito.anyLong())).thenCallRealMethod();
		

	}
	
}
