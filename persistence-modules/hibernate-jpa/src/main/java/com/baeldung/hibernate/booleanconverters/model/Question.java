package com.baeldung.hibernate.booleanconverters.model;

import org.hibernate.type.NumericBooleanConverter;
import org.hibernate.type.TrueFalseConverter;
import org.hibernate.type.YesNoConverter;

import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Question {

    @Id
    private Long id;
    private String content;

    @Convert(converter = YesNoConverter.class)

    private Boolean correctAnswer;
    @Convert(converter = TrueFalseConverter.class)

    private Boolean shouldBeAsked;
    @Convert(converter = NumericBooleanConverter.class)

    private Boolean isEasy;
    private Boolean converterAutoApplies;

    public Question() {
    }

    public Question(Long id, String content, Boolean correctAnswer, Boolean shouldBeAsked, Boolean isEasy, Boolean converterAutoApplies) {
        this.id = id;
        this.content = content;
        this.correctAnswer = correctAnswer;
        this.shouldBeAsked = shouldBeAsked;
        this.isEasy = isEasy;
        this.converterAutoApplies = converterAutoApplies;
    }

    public Long getId() {
        return id;
    }

    public String getContent() {
        return content;
    }

    public Boolean getCorrectAnswer() {
        return correctAnswer;
    }

    public Boolean getShouldBeAsked() {
        return shouldBeAsked;
    }

    public Boolean getEasy() {
        return isEasy;
    }

    public Boolean getConverterAutoApplies() {
        return converterAutoApplies;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setCorrectAnswer(Boolean correctAnswer) {
        this.correctAnswer = correctAnswer;
    }

    public void setShouldBeAsked(Boolean shouldBeAsked) {
        this.shouldBeAsked = shouldBeAsked;
    }

    public void setEasy(Boolean easy) {
        isEasy = easy;
    }

    public void setConverterAutoApplies(Boolean converterAutoApplies) {
        this.converterAutoApplies = converterAutoApplies;
    }
}
