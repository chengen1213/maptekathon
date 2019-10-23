package application.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "solution")
public class Solution implements Serializable {

    @EmbeddedId
    SolutionKey id;

    @JsonIgnore
    @ManyToOne
    @MapsId("user_id")
    @JoinColumn(name = "user_id")
    private User user;

    @JsonIgnore
    @ManyToOne
    @MapsId("problem_id")
    @JoinColumn(name = "problem_id")
    private Problem problem;

    @Column
    private String language;

    @Lob
    @Column
    private String code;

    @Column
    private double publicTimeComplexity;

    @Column
    private double publicSpaceComplexity;

    @Column
    private double timeComplexity;

    @Column
    private double spaceComplexity;

    public Solution() {

    }

    @Transient
    private Collection<Long> failedPublic;

    @Transient
    private Collection<Long> failedPrivate;

//    public Solution(){}
//
//    public Solution(User user, Problem problem, String code, double timeComplexity, double spaceComplexity) {
//        this.user = user;
//        this.problem = problem;
//        this.code = code;
//        this.timeComplexity = timeComplexity;
//        this.spaceComplexity = spaceComplexity;
//    }

    public SolutionKey getId() {
        return id;
    }

    public void setId(SolutionKey id) {
        this.id = id;
    }

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

    public double getPublicTimeComplexity() {
        return publicTimeComplexity;
    }

    public void setPublicTimeComplexity(double publicTimeComplexity) {
        this.publicTimeComplexity = publicTimeComplexity;
    }

    public double getPublicSpaceComplexity() {
        return publicSpaceComplexity;
    }

    public void setPublicSpaceComplexity(double publicSpaceComplexity) {
        this.publicSpaceComplexity = publicSpaceComplexity;
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

    public Collection<Long> getFailedPublic() {
        return failedPublic;
    }

    public void setFailedPublic(List<Long> failedPublic) {
        this.failedPublic = failedPublic;
    }

    public Collection<Long> getFailedPrivate() {
        return failedPrivate;
    }

    public void setFailedPrivate(List<Long> failedPrivate) {
        this.failedPrivate = failedPrivate;
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
