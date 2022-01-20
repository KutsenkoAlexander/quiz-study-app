package ua.kutsenko.quiz.server.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ua.kutsenko.quiz.server.model.UserStatus;

@Repository
public interface UserStatusRepository extends JpaRepository<UserStatus, Integer> {

    UserStatus getByUserId(int userId);

    @Transactional(readOnly = true)
    int countByActiveTrue();

}
