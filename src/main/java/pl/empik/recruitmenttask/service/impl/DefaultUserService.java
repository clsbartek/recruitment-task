package pl.empik.recruitmenttask.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.empik.recruitmenttask.domain.User;
import pl.empik.recruitmenttask.model.ExternUser;
import pl.empik.recruitmenttask.model.UserRequestCount;
import pl.empik.recruitmenttask.repository.UserRequestCountRepository;
import pl.empik.recruitmenttask.service.CalculationService;
import pl.empik.recruitmenttask.service.ExternUserService;
import pl.empik.recruitmenttask.service.UserService;

@Service
@RequiredArgsConstructor
public class DefaultUserService implements UserService {

    private final ExternUserService externUserService;
    private final UserRequestCountRepository userRequestCountRepository;
    private final CalculationService calculationService;

    public User getUser(String name) {
        ExternUser externUser = externUserService.getUser(name);
        userRequestCountRepository.findById(name).ifPresentOrElse(user -> {
                    user.setRequestCount(user.getRequestCount() + 1);
                    userRequestCountRepository.save(user);
                },
                () -> userRequestCountRepository.save(
                        UserRequestCount.builder()
                                .login(name)
                                .requestCount(1L).build()));

        return User.builder()
                .login(externUser.getLogin())
                .id(externUser.getId())
                .type(externUser.getType())
                .createdAt(externUser.getCreatedAt())
                .avatarUrl(externUser.getAvatarUrl())
                .name(externUser.getName())
                .calculated(calculationService.getCalculation(externUser))
                .build();
    }
}
