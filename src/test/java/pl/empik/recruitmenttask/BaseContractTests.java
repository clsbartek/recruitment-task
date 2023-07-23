package pl.empik.recruitmenttask;

import io.restassured.module.mockmvc.RestAssuredMockMvc;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.cloud.contract.verifier.messaging.boot.AutoConfigureMessageVerifier;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.test.web.servlet.setup.StandaloneMockMvcBuilder;
import pl.empik.recruitmenttask.controller.UserController;
import pl.empik.recruitmenttask.exception.UserNotFoundException;
import pl.empik.recruitmenttask.model.GitHubUser;
import pl.empik.recruitmenttask.repository.UserRequestCountRepository;
import pl.empik.recruitmenttask.service.ExternUserService;

import static org.mockito.Mockito.when;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
@DirtiesContext
@AutoConfigureMessageVerifier
public class BaseContractTests {

    @Autowired
    UserController userController;
    @MockBean
    ExternUserService externUserService;
    @MockBean
    private UserRequestCountRepository userRequestCountRepository;

    @BeforeEach
    public void setup() {
        StandaloneMockMvcBuilder standaloneMockMvcBuilder
                = MockMvcBuilders.standaloneSetup(userController);
        RestAssuredMockMvc.standaloneSetup(standaloneMockMvcBuilder);

        when(externUserService.getUser("octocat")).thenReturn(new GitHubUser(
                583231L,
                "octocat",
                "The Octocat",
                "User",
                "https://avatars.githubusercontent.com/u/583231?v=4",
                "2011-01-25T18:44:36Z",
                9823L,
                8L));

        when(externUserService.getUser("octocat_empty_followers")).thenReturn(new GitHubUser(
                583231L,
                "octocat_empty_followers",
                "The Octocat",
                "User",
                "https://avatars.githubusercontent.com/u/583231?v=4",
                "2011-01-25T18:44:36Z",
                0L,
                8L));

        when(externUserService.getUser("octocat_not_found")).thenThrow(new UserNotFoundException("Request resource has not been found"));

    }
}