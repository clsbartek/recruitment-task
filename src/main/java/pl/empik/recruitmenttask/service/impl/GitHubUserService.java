package pl.empik.recruitmenttask.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import pl.empik.recruitmenttask.model.GitHubUser;
import pl.empik.recruitmenttask.service.ExternUserService;

@Service
public class GitHubUserService implements ExternUserService {
    private static final Logger log = LoggerFactory.getLogger(GitHubUserService.class);
    private final RestTemplate restTemplate;
    private final String gitHubApiUrl;

    public GitHubUserService(RestTemplate restTemplate,
                             @Value("${api.github.url}") String gitHubApiUrl) {
        this.restTemplate = restTemplate;
        this.gitHubApiUrl = gitHubApiUrl;
    }

    public GitHubUser getUser(String userName) {
        return restTemplate.getForObject(
                gitHubApiUrl + "/{userName}", GitHubUser.class, userName);
    }
}
