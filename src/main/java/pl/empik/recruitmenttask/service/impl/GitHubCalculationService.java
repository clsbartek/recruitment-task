package pl.empik.recruitmenttask.service.impl;

import org.springframework.stereotype.Service;
import pl.empik.recruitmenttask.exception.CalculationException;
import pl.empik.recruitmenttask.model.ExternUser;
import pl.empik.recruitmenttask.model.GitHubUser;
import pl.empik.recruitmenttask.service.CalculationService;

@Service
public class GitHubCalculationService implements CalculationService {
    public Double getCalculation(ExternUser externUser) {
        if (!(externUser instanceof GitHubUser gitHubUser))
            throw new CalculationException("Not supported user type");
        if (gitHubUser.getFollowers() == 0)
            throw new CalculationException("Not allowed followers count - dividing by zero");
        return 6.0 / gitHubUser.getFollowers() * (2 + gitHubUser.getPublicRepos());
    }
}
