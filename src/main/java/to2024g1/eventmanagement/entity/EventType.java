package to2024g1.eventmanagement.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;
import to2024g1.eventmanagement.entity.base.BaseEntity;

@Entity
@Table(name = "EVENT_TYPES")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@ToString
public class EventType extends BaseEntity {
    @Column(name = "NAME", nullable = false)
    private String name;
}
