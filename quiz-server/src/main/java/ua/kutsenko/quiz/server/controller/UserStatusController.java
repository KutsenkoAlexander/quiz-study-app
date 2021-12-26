package ua.kutsenko.quiz.server.controller;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;
import ua.kutsenko.quiz.server.model.ActiveUserNumber;
import ua.kutsenko.quiz.server.service.UserStatusServiceImpl;

@RestController
@AllArgsConstructor
public class UserStatusController {

    private final UserStatusServiceImpl userStatusServiceImpl;

    @PutMapping("/api/{userId}")
    public ActiveUserNumber updateStatus(@PathVariable int userId) {
        return userStatusServiceImpl.updateStatus(userId);
    }

    @GetMapping("/api/active")
    public ActiveUserNumber updateStatus() {
        return userStatusServiceImpl.getActiveUsersNumber();
    }
}
