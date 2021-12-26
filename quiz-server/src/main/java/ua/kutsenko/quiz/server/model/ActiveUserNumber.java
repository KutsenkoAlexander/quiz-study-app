package ua.kutsenko.quiz.server.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ActiveUserNumber {
    private final int activeCount;
}
