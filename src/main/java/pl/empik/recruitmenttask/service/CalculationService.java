package pl.empik.recruitmenttask.service;

import pl.empik.recruitmenttask.model.ExternUser;

public interface CalculationService {
    Double getCalculation(ExternUser gitHubUser);
}
