package pl.empik.recruitmenttask.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.Objects;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class GitHubUser extends ExternUser {
    private Long followers;
    @JsonProperty("public_repos")
    private Long publicRepos;


    public GitHubUser(Long id, String login, String name, String type, String avatarUrl, String createdAt, Long followers, Long publicRepos) {
        super(id, login, name, type, avatarUrl, createdAt);
        this.followers = followers;
        this.publicRepos = publicRepos;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof GitHubUser that)) return false;
        if (!super.equals(o)) return false;
        return Objects.equals(getFollowers(), that.getFollowers()) && Objects.equals(getPublicRepos(), that.getPublicRepos())
                && Objects.equals(getId(), that.getId()) && Objects.equals(getLogin(), that.getLogin())
                && Objects.equals(getName(), that.getName()) && Objects.equals(getType(), that.getType())
                && Objects.equals(getAvatarUrl(), that.getAvatarUrl()) && Objects.equals(getCreatedAt(), that.getCreatedAt());
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getFollowers(), getPublicRepos());
    }
}