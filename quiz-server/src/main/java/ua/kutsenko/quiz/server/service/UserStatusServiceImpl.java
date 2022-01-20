package ua.kutsenko.quiz.server.service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ua.kutsenko.quiz.server.model.ActiveUserNumber;
import ua.kutsenko.quiz.server.model.UserStatus;
import ua.kutsenko.quiz.server.repository.UserStatusRepository;

@Service
@AllArgsConstructor
@Slf4j
public class UserStatusServiceImpl implements UserStatusService {

    private final UserStatusRepository userStatusRepository;

    @Override
    public void deleteAll() {
        userStatusRepository.deleteAll();
    }

    @Override
    public void createStatus(int userId) {
        UserStatus userStatus = UserStatus.builder()
                .userId(userId)
                .build();
        userStatusRepository.save(userStatus);
    }

    @Override
    public void updateStatus(int userId) {
        UserStatus userStatus = userStatusRepository.getByUserId(userId);
        UserStatus newStatus = userStatus.toBuilder()
                .active(true)
                .build();
        userStatusRepository.save(newStatus);

        try {
            Thread.sleep(1000);
        } catch (Exception ignore) {
        }

        int numberActive = userStatusRepository.countByActiveTrue();
        log.info("Active students: {}", numberActive);
    }

    @Override
    public ActiveUserNumber getActiveUsersNumber() {
        int numberActive = userStatusRepository.countByActiveTrue();
        return new ActiveUserNumber(numberActive);
    }
}
