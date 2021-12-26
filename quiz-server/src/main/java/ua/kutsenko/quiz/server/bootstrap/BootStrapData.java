package ua.kutsenko.quiz.server.bootstrap;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import ua.kutsenko.quiz.server.model.UserStatus;
import ua.kutsenko.quiz.server.repository.UserStatusRepository;
import java.util.ArrayList;
import java.util.List;

@Component
@AllArgsConstructor
@Slf4j
public class BootStrapData implements CommandLineRunner {

    private final UserStatusRepository userStatusRepository;

    @Override
    public void run(String... args) {
        log.warn("***** Bootstrap has been STARTED *****");

        List<UserStatus> userStatuses = new ArrayList<>();

        for (int i = 1; i <= 100; i++ ) {
            userStatuses.add(UserStatus.builder().userId(1000 + i).build());
        }

        userStatusRepository.saveAll(userStatuses);

        log.warn("***** Bootstrap has been FINISHED *****");
    }
}
