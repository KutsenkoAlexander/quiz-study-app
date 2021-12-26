package ua.kutsenko.quiz.server.service;

import ua.kutsenko.quiz.server.model.ActiveUserNumber;

public interface UserStatusService {
    ActiveUserNumber updateStatus(int userId);
    ActiveUserNumber getActiveUsersNumber();
}
