package application.dto;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;

public class ProblemDto {

    @NotEmpty
    private String title;

    @NotEmpty
    private String description;

    @NotEmpty
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date expirationDate;

    @NotNull
    private List<Long> publicDataset;

    @NotNull
    private List<Long> privateDataset;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(Date expirationDate) {
        this.expirationDate = expirationDate;
    }

    public List<Long> getPublicDataset() {
        return publicDataset;
    }

    public void setPublicDataset(List<Long> publicDataset) {
        this.publicDataset = publicDataset;
    }

    public List<Long> getPrivateDataset() {
        return privateDataset;
    }

    public void setPrivateDataset(List<Long> privateDataset) {
        this.privateDataset = privateDataset;
    }
}
