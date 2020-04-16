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
import sanial.netheos.demoapi.api.dto.FaqTagCreation;
import sanial.netheos.demoapi.core.model.FaqTag;
import sanial.netheos.demoapi.core.service.FaqTagService;

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
public class FaqTagControllerTest {

    @Value("${api.admin.login}")
    private String apiAdminLogin;

    @Value("${api.admin.password}")
    private String apiAdminPass;

    @Value("${api.user.login}")
    private String apiUserLogin;

    @Value("${api.user.password}")
    private String apiUserPass;

    @Autowired
    MockMvc mockMvc;

    ObjectMapper mapper = new ObjectMapper();

    @Test
    public void FaqTagCreateOrUpdateOk() throws Exception {
        FaqTagService faqTagService = mock(FaqTagService.class);

        List<String> tagNameList = new ArrayList<>();
        tagNameList.add("tag1");
        tagNameList.add("tag2");
        FaqTagCreation faqTagCreation = new FaqTagCreation();
        faqTagCreation.setQuestion("question1");
        faqTagCreation.setAnswer("answer1");
        faqTagCreation.setTagNameList(tagNameList);

        List<FaqTag> tagList = new ArrayList<>();
        when(faqTagService.createFaqTag(any(FaqTagCreation.class))).thenReturn(tagList);

        mockMvc.perform(post("/api/faqtag/create")
                .with(httpBasic(apiAdminLogin, apiAdminPass))
                .content(mapper.writeValueAsString(faqTagCreation))
                .contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk());
    }

    //Test without auth
    @Test
    public void FaqTagCreateOrUpdateNotAuthorized() throws Exception {
        FaqTagService faqTagService = mock(FaqTagService.class);

        List<String> tagNameList = new ArrayList<>();
        tagNameList.add("tag1");
        tagNameList.add("tag2");
        FaqTagCreation faqTagCreation = new FaqTagCreation();
        faqTagCreation.setQuestion("question1");
        faqTagCreation.setAnswer("answer1");
        faqTagCreation.setTagNameList(tagNameList);

        List<FaqTag> tagList = new ArrayList<>();
        when(faqTagService.createFaqTag(any(FaqTagCreation.class))).thenReturn(tagList);

        mockMvc.perform(post("/api/faqtag/create")
                .content(mapper.writeValueAsString(faqTagCreation))
                .contentType(MediaType.APPLICATION_JSON)).andExpect(status().isUnauthorized());
    }

    //Test with bad credentials
    @Test
    public void FaqTagCreateOrUpdateBadCredentials() throws Exception {
        FaqTagService faqTagService = mock(FaqTagService.class);

        List<String> tagNameList = new ArrayList<>();
        tagNameList.add("tag1");
        tagNameList.add("tag2");
        FaqTagCreation faqTagCreation = new FaqTagCreation();
        faqTagCreation.setQuestion("question1");
        faqTagCreation.setAnswer("answer1");
        faqTagCreation.setTagNameList(tagNameList);

        List<FaqTag> tagList = new ArrayList<>();
        when(faqTagService.createFaqTag(any(FaqTagCreation.class))).thenReturn(tagList);

        mockMvc.perform(post("/api/faqtag/create")
                .with(httpBasic(apiUserLogin, apiUserPass))
                .content(mapper.writeValueAsString(faqTagCreation))
                .contentType(MediaType.APPLICATION_JSON)).andExpect(status().isForbidden());
    }

    //Test without mandatory field
    @Test
    public void FaqTagCreateOrUpdateMissingParam() throws Exception {
        FaqTagService faqTagService = mock(FaqTagService.class);

        List<String> tagNameList = new ArrayList<>();
        tagNameList.add("tag1");
        tagNameList.add("tag2");
        FaqTagCreation faqTagCreation = new FaqTagCreation();
        faqTagCreation.setAnswer("answer1");
        faqTagCreation.setTagNameList(tagNameList);

        List<FaqTag> tagList = new ArrayList<>();
        when(faqTagService.createFaqTag(any(FaqTagCreation.class))).thenReturn(tagList);

        mockMvc.perform(post("/api/faqtag/create")
                .with(httpBasic(apiAdminLogin, apiAdminPass))
                .content(mapper.writeValueAsString(faqTagCreation))
                .contentType(MediaType.APPLICATION_JSON)).andExpect(status().isBadRequest());
    }
}
