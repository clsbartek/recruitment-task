package pl.empik.recruitmenttask.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.cloud.contract.verifier.messaging.boot.AutoConfigureMessageVerifier;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.MockMvc;
import pl.empik.recruitmenttask.model.GitHubUser;
import pl.empik.recruitmenttask.model.UserRequestCount;
import pl.empik.recruitmenttask.repository.UserRequestCountRepository;
import pl.empik.recruitmenttask.service.ExternUserService;

import java.util.Optional;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
@AutoConfigureMockMvc
@DirtiesContext
@AutoConfigureMessageVerifier
public class UserControllerTest {

    @MockBean
    ExternUserService externUserService;
    @Mock
    GitHubUser gitHubUser;
    @Autowired
    private MockMvc mvc;
    @MockBean
    private UserRequestCountRepository userRequestCountRepository;

    @BeforeEach
    public void setUp() {
        when(gitHubUser.getPublicRepos()).thenReturn(8L);
        when(gitHubUser.getFollowers()).thenReturn(2L);
        when(gitHubUser.getId()).thenReturn(583231L);
        when(gitHubUser.getName()).thenReturn("The Octocat");
        when(gitHubUser.getType()).thenReturn("User");
        when(gitHubUser.getLogin()).thenReturn("octocat");
        when(gitHubUser.getCreatedAt()).thenReturn("2011-01-25T18:44:36Z");
        when(gitHubUser.getAvatarUrl()).thenReturn("https://avatars.githubusercontent.com/u/583231?v=4");

        when(externUserService.getUser("octocat")).thenReturn(gitHubUser);
    }

    @Test
    void shouldReturnCorrectDataAndInsertNewRequestCountData() throws Exception {

        mvc.perform(get("/users/octocat"))
                .andExpect(status().isOk());

        verify(userRequestCountRepository).findById("octocat");
        verify(userRequestCountRepository).save(UserRequestCount.builder()
                .login("octocat")
                .requestCount(1L)
                .build());

    }

    @Test
    void shouldReturnCorrectDataAndIncrementRequestCountData() throws Exception {

        when(userRequestCountRepository.findById("octocat")).thenReturn(Optional.of(UserRequestCount.builder()
                .login("octocat")
                .requestCount(1L)
                .build()));

        mvc.perform(get("/users/octocat"))
                .andExpect(status().isOk());

        verify(userRequestCountRepository).findById("octocat");
        verify(userRequestCountRepository).save(UserRequestCount.builder()
                .login("octocat")
                .requestCount(2L)
                .build());

    }
}
