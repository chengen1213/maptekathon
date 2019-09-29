package application.model;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class SolutionKey implements Serializable {

    @Column(name = "user_id")
    Long userId;

    @Column(name = "problem_id")
    Long problemId;

    public SolutionKey() {
    }

    public SolutionKey(Long userId, Long problemId) {
        this.userId = userId;
        this.problemId = problemId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getProblemId() {
        return problemId;
    }

    public void setProblemId(Long problemId) {
        this.problemId = problemId;
    }

    @Override
    public int hashCode() {
        return userId.hashCode() + problemId.hashCode();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SolutionKey that = (SolutionKey) o;

        if (!userId.equals(that.userId)) return false;
        return problemId.equals(that.problemId);
    }
}
