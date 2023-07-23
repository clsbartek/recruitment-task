package pl.empik.recruitmenttask.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Builder;
import lombok.Data;

import java.util.Objects;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@Builder
public class User {

    private Long id;
    private String login;
    private String name;
    private String type;
    private String avatarUrl;
    private String createdAt;
    private Double calculated;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User user)) return false;
        return Objects.equals(getId(), user.getId()) && Objects.equals(getLogin(), user.getLogin()) && Objects.equals(getName(), user.getName()) && Objects.equals(getType(), user.getType()) && Objects.equals(getAvatarUrl(), user.getAvatarUrl()) && Objects.equals(getCreatedAt(), user.getCreatedAt()) && Objects.equals(getCalculated(), user.getCalculated());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getLogin(), getName(), getType(), getAvatarUrl(), getCreatedAt(), getCalculated());
    }
}