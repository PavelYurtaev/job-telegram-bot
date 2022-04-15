package ru.pavelyurtaev.jobbot.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class BotUser {
    @Id
    private Long id;
    private LocalDateTime createdAt;
    private Boolean isActive; // юзер вообще ничего не будет получать
    private String username;
    private String firstName;
    private String lastName;
}
