package com.caforerof.calculator;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.sql.SQLException;

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

import com.caforerof.calculator.controller.SessionIdController;
import com.caforerof.calculator.models.entity.CyxteraLog;
import com.caforerof.calculator.models.entity.SessionId;
import com.caforerof.calculator.models.services.ICyxteraLogService;
import com.caforerof.calculator.models.services.ISessionIdService;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class SessionControllerTests {

	@Autowired
    private MockMvc mockMvc;
	
    @InjectMocks
    private SessionIdController testingObject;
	
    @Mock
    private ISessionIdService sessionIdService;
	
    @Mock
    private ICyxteraLogService cyxteraLogService;
	
    @Before
    public void setUp() throws SQLException {
        MockitoAnnotations.initMocks(this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(testingObject).build();
    }
    
	@Test
	public void getSessionIdServiceSuccessfullResponseTest() throws Exception {
		
    	SessionId mockedSessionId = Mockito.mock(SessionId.class);
		Mockito.when(sessionIdService.createSessionId()).thenReturn(mockedSessionId);
		Mockito.when(cyxteraLogService.log(Mockito.any(long.class), Mockito.any(String.class),
				Mockito.any(String.class), Mockito.any(String.class), Mockito.any(String.class), 
				Mockito.any(String.class))).thenReturn(new CyxteraLog());
		
		this.mockMvc.perform(get("/calculatorApi/getSessionId"))
        .andDo(print()).andExpect(status().isOk())
        .andExpect(jsonPath("$.responseCode").value(200));
	}

	@Test
	public void getSessionIdServiceFailureResponseTest() throws Exception {
		
		Mockito.when(sessionIdService.createSessionId()).thenThrow(new SQLException("Error al crear el sessionId"));
		Mockito.when(cyxteraLogService.log(Mockito.any(long.class), Mockito.any(String.class),
				Mockito.any(String.class), Mockito.any(String.class), Mockito.any(String.class), 
				Mockito.any(String.class))).thenReturn(new CyxteraLog());
		
		this.mockMvc.perform(get("/calculatorApi/getSessionId"))
        .andDo(print()).andExpect(status().isOk())
        .andExpect(jsonPath("$.responseCode").value(500));
	}
	
}
