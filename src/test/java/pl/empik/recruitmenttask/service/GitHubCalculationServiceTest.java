package pl.empik.recruitmenttask.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import pl.empik.recruitmenttask.exception.CalculationException;
import pl.empik.recruitmenttask.model.GitHubUser;
import pl.empik.recruitmenttask.service.impl.GitHubCalculationService;

import static org.mockito.Mockito.when;

@SpringBootTest
public class GitHubCalculationServiceTest {

    @Mock
    GitHubUser gitHubUser;
    @Autowired
    private GitHubCalculationService calculationService;

    @BeforeEach
    public void setUp() {
        when(gitHubUser.getFollowers()).thenReturn(3L);
        when(gitHubUser.getPublicRepos()).thenReturn(10L);
    }

    @Test
    void shouldReturnCorrectCalculation() {

        Double result = calculationService.getCalculation(gitHubUser);

        Assertions.assertEquals(24.0, result);
    }

    @Test
    void shouldThrowExceptionForWrongInputData() {
        when(gitHubUser.getFollowers()).thenReturn(0L);

        Assertions.assertThrows(CalculationException.class, () -> calculationService.getCalculation(gitHubUser));
    }

    @TestConfiguration
    static class GitHubCalculationServiceTestContextConfiguration {

        @Bean
        public GitHubCalculationService calculationService() {
            return new GitHubCalculationService();
        }
    }
}
