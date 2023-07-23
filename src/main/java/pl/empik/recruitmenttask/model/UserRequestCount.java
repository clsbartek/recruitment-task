package pl.empik.recruitmenttask.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Objects;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserRequestCount {

    @Id
    @Column(name = "LOGIN")
    private String login;

    @Column(name = "REQUEST_COUNT")
    private Long requestCount;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof UserRequestCount that)) return false;
        return Objects.equals(getLogin(), that.getLogin()) && Objects.equals(getRequestCount(), that.getRequestCount());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getLogin(), getRequestCount());
    }
}
