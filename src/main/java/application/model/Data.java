package application.model;

import javax.persistence.*;

@Entity
@Table(name = "data")
public class Data {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String fileName;

    @Column
    private String contentType;

    @Column
    private String answerFileName;

    @Column
    private String answerContentType;

    public Data() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    public String getAnswerFileName() {
        return answerFileName;
    }

    public void setAnswerFileName(String answerFileName) {
        this.answerFileName = answerFileName;
    }

    public String getAnswerContentType() {
        return answerContentType;
    }

    public void setAnswerContentType(String answerContentType) {
        this.answerContentType = answerContentType;
    }
}
