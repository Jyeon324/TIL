// jakarta.persistence.*, org.springframework.data.annotation.*
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class) // JPA Auditing 활성화
@Getter
public abstract class BaseTimeEntity {

    @CreatedDate
    @Column(updatable = false)
    private LocalDateTime createdAt;

    @LastModifiedDate
    private LocalDateTime modifiedAt;
}