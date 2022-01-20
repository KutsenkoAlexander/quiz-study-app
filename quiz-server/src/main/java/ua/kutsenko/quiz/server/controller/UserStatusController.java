package ua.kutsenko.quiz.server.controller;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;
import ua.kutsenko.quiz.server.model.ActiveUserNumber;
import ua.kutsenko.quiz.server.service.UserStatusServiceImpl;

@Slf4j
@RestController
@AllArgsConstructor
@Transactional(propagation = Propagation.REQUIRES_NEW)
public class UserStatusController {

    private final UserStatusServiceImpl userStatusServiceImpl;

    @GetMapping("/api/init/{size}")
    public void initTable(@PathVariable int size) {
        log.info("INIT table for users count: {}", size);
        userStatusServiceImpl.deleteAll();

        for (int userId = 0; userId < size; userId++) {
            userStatusServiceImpl.createStatus(userId);
        }
    }

    @PutMapping("/api/{userId}")
    public void updateStatus(@PathVariable int userId) {
        userStatusServiceImpl.updateStatus(userId);
    }

    @GetMapping("/api/active")
    @Transactional(readOnly = true)
    public ActiveUserNumber updateStatus() {
        return userStatusServiceImpl.getActiveUsersNumber();
    }
}
