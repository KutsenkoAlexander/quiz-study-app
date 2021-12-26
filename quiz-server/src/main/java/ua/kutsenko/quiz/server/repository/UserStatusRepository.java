package ua.kutsenko.quiz.server.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ua.kutsenko.quiz.server.model.UserStatus;

@Repository
public interface UserStatusRepository extends JpaRepository<UserStatus, Integer> {

    @Modifying
    @Query("update UserStatus us set us.active = :active where us.userId = :userId")
    void updateStatus(@Param("active") boolean active, @Param("userId") int userId);

    int countByActiveTrue();
}
