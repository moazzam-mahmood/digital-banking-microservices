package com.demoproject.loans.entity;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

/*@MappedSuperclass is used to share common JPA mappings across entities.It does not create its own table
 and its fields are mapped into child entity tables. It’s ideal for base entities like audit fields.
❌ No table for the superclass
✅ Its fields appear in child entity tables
❌ Cannot be queried directly
❌ Cannot use @Entity on it
*/

/*@EntityListeners(AuditingEntityListener.class) enables automatic population of audit fields by listening to JPA lifecycle events.
Also define @Component("auditAwareImpl") in AuditAwareImpl class which is extending AuditorAware<String>
Without @EnableJpaAuditing(auditorAwareRef = "auditAwareImpl") in main application class → auditing will NOT work*/
@MappedSuperclass
@Getter
@Setter
@ToString
@EntityListeners(AuditingEntityListener.class)
public class BaseEntity {

    @CreatedDate
    @Column(updatable = false)
    private LocalDateTime createdAt;

    @CreatedBy
    @Column(updatable = false)
    private String createdBy;

    @LastModifiedDate
    @Column(insertable = false)
    private LocalDateTime updatedAt;

    @LastModifiedBy
    @Column(insertable = false)
    private String updatedBy;
}
