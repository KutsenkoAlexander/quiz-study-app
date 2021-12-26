package ua.kutsenko.quiz.server.service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.kutsenko.quiz.server.model.ActiveUserNumber;
import ua.kutsenko.quiz.server.repository.UserStatusRepository;

@Service
@AllArgsConstructor
@Slf4j
public class UserStatusServiceImpl implements UserStatusService {

    private final UserStatusRepository userStatusRepository;

    @Override
    @Transactional
    public ActiveUserNumber updateStatus(int userId) {
        userStatusRepository.updateStatus(true, userId);
        int numberActive = userStatusRepository.countByActiveTrue();
        return new ActiveUserNumber(numberActive);
    }

    @Override
    public ActiveUserNumber getActiveUsersNumber() {
        int numberActive = userStatusRepository.countByActiveTrue();
        return new ActiveUserNumber(numberActive);
    }
}
