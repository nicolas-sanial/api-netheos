package sanial.netheos.demoapi.api.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import sanial.netheos.demoapi.api.dto.SearchForm;
import sanial.netheos.demoapi.core.service.FaqService;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.httpBasic;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
public class FaqControllerTest {

    @Value("${api.user.login}")
    private String apiAdminLogin;

    @Value("${api.user.password}")
    private String apiAdminPass;

    @Value("${api.user.login}")
    private String apiUserLogin;

    @Value("${api.user.password}")
    private String apiUserPass;

    @Autowired
    MockMvc mockMvc;

    ObjectMapper mapper = new ObjectMapper();

    @Test
    public void getAllFaqContainsParamOk() throws Exception {
        FaqService faqService = mock(FaqService.class);
        List<String> listString = new ArrayList<>();
        listString.add("test");
        SearchForm search = new SearchForm();
        search.setToCompare("answer");

        when(faqService.findAllLikeQuestionOrAnswer(any(String.class))).thenReturn(listString);

        mockMvc.perform(post("/api/faq/search")
                .with(httpBasic(apiUserLogin, apiUserPass))
                .content(mapper.writeValueAsString(search))
                .contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk());
    }

    //Test without auth
    @Test
    public void getAllFaqContainsParamNotAuthorized() throws Exception {
        FaqService faqService = mock(FaqService.class);
        List<String> listString = new ArrayList<>();
        listString.add("test");
        SearchForm search = new SearchForm();
        search.setToCompare("answer");

        when(faqService.findAllLikeQuestionOrAnswer(any(String.class))).thenReturn(listString);

        mockMvc.perform(post("/api/faq/search")
                .content(mapper.writeValueAsString(search))
                .contentType(MediaType.APPLICATION_JSON)).andExpect(status().isUnauthorized());
    }

    //Test without mandatory field
    @Test
    public void getAllFaqContainsParamMissingParam() throws Exception {
        FaqService faqService = mock(FaqService.class);
        List<String> listString = new ArrayList<>();
        listString.add("test");
        SearchForm search = new SearchForm();
        search.setToCompare(" ");

        when(faqService.findAllLikeQuestionOrAnswer(any(String.class))).thenReturn(listString);

        mockMvc.perform(post("/api/faq/search")
                .with(httpBasic(apiAdminLogin, apiAdminPass))
                .content(mapper.writeValueAsString(search))
                .contentType(MediaType.APPLICATION_JSON)).andExpect(status().isBadRequest());
    }
}
