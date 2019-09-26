package application.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "solution")
public class Solution implements Serializable {
    @Id
    @ManyToOne
    @JoinColumn
    private User user;

    @Id
    @ManyToOne
    @JoinColumn
    private Problem problem;

    @Column
    private String language;

    @Column
    private String code;

    @Column
    private double timeComplexity;

    @Column
    private double spaceComplexity;

//    public Solution(){}
//
//    public Solution(User user, Problem problem, String code, double timeComplexity, double spaceComplexity) {
//        this.user = user;
//        this.problem = problem;
//        this.code = code;
//        this.timeComplexity = timeComplexity;
//        this.spaceComplexity = spaceComplexity;
//    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Problem getProblem() {
        return problem;
    }

    public void setProblem(Problem problem) {
        this.problem = problem;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public double getTimeComplexity() {
        return timeComplexity;
    }

    public void setTimeComplexity(double timeComplexity) {
        this.timeComplexity = timeComplexity;
    }

    public double getSpaceComplexity() {
        return spaceComplexity;
    }

    public void setSpaceComplexity(double spaceComplexity) {
        this.spaceComplexity = spaceComplexity;
    }

    @Override
    public int hashCode() {
        return Objects.hash(user.getId(), problem.getId());
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Solution)) return false;
        Solution that = (Solution) obj;
        return Objects.equals(user.getUsername(), that.user.getUsername()) &&
                Objects.equals(problem.getId(), that.problem.getId());
    }
}
