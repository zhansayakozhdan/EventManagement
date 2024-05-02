package to2024g1.eventmanagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import to2024g1.eventmanagement.entity.EventType;

@Repository
public interface EventTypeRepository extends JpaRepository<EventType, Long> {
}
