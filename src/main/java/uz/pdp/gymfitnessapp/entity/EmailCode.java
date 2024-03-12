package uz.pdp.gymfitnessapp.entity;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

import java.time.LocalDateTime;

@Data
@Builder
@RedisHash(value = "email_service", timeToLive = 86400)
@AllArgsConstructor
@NoArgsConstructor
public class EmailCode {

    @Id
    private String email;

    @Column(nullable = false)
    private int code;

    @CreationTimestamp
    @Column(columnDefinition = "timestamp default now()", updatable = false)
    private LocalDateTime sentTime;

    @Builder.Default
    @Column(columnDefinition = "timestamp default now()+INTERVAL '5 Minutes'")
    private LocalDateTime expirationTime = LocalDateTime.now().plusMinutes(5);

}
