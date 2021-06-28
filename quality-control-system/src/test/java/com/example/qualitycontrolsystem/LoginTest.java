package com.example.qualitycontrolsystem;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestBuilders.formLogin;
import static org.springframework.security.test.web.servlet.response.SecurityMockMvcResultMatchers.authenticated;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@TestPropertySource("/application-test.properties")
public class LoginTest {
	@Autowired
	private MockMvc mockMvc;
	
	@Test
	public void contextLoads() throws Exception{
		try {
			this.mockMvc.perform(get("/"))
				.andDo(print())
				.andExpect(status().isOk())
				.andExpect(content().string(containsString("Вход")));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
    public void loginTest() throws Exception {
        this.mockMvc.perform(get("/greeting"))
                .andDo(print())
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("http://localhost/login"));
    }
	
	@Test
    @Sql(value = {"/create-user-before.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(value = {"/create-user-after.sql"}, executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    public void correctLoginTest() throws Exception {
        this.mockMvc.perform(formLogin().user("a").password("1"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/"));
    }
	
	@Test
    public void invalidCredentials() throws Exception {
        this.mockMvc.perform(post("/login").param("username", "User"))
                .andDo(print())
                .andExpect(status().isForbidden());
    }
	
	@Test
	@WithUserDetails(value = "a")
    public void greetingPageTest() throws Exception {
        this.mockMvc.perform(get("/greeting"))
                .andDo(print())
                .andExpect(authenticated())
                .andExpect(xpath("/html/body/h3").string("Привет, a!"));
    }
}
