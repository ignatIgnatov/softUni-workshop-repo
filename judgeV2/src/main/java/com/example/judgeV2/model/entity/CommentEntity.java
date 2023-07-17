package com.example.judgeV2.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "comments")
public class CommentEntity extends BaseEntity {

    @Column(name = "score")
    private Integer score;
    @Column(name = "text_content", columnDefinition = "TEXT")
    private String textContent;
    @ManyToOne
    private UserEntity author;
    @ManyToOne
    private HomeworkEntity homework;

    public CommentEntity() {
    }

    public Integer getScore() {
        return score;
    }

    public CommentEntity setScore(Integer score) {
        this.score = score;
        return this;
    }

    public String getTextContent() {
        return textContent;
    }

    public CommentEntity setTextContent(String textContent) {
        this.textContent = textContent;
        return this;
    }

    public UserEntity getAuthor() {
        return author;
    }

    public CommentEntity setAuthor(UserEntity author) {
        this.author = author;
        return this;
    }

    public HomeworkEntity getHomework() {
        return homework;
    }

    public CommentEntity setHomework(HomeworkEntity homework) {
        this.homework = homework;
        return this;
    }
}
