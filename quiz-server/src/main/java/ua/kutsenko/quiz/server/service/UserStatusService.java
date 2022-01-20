package ua.kutsenko.quiz.server.service;

import ua.kutsenko.quiz.server.model.ActiveUserNumber;

public interface UserStatusService {
    void deleteAll();
    void createStatus(int userId);
    void updateStatus(int userId);
    ActiveUserNumber getActiveUsersNumber();
}
