package com.baeldung.hibernate.booleanconverters.model;

public class QuestionBuilder {
    private Long id;
    private String content;
    private Boolean correctAnswer;
    private Boolean shouldBeAsked;
    private Boolean isEasy;
    private Boolean converterAutoApplies;

    public QuestionBuilder id(Long id) {
        this.id = id;
        return this;
    }

    public QuestionBuilder content(String content) {
        this.content = content;
        return this;
    }

    public QuestionBuilder correctAnswer(Boolean correctAnswer) {
        this.correctAnswer = correctAnswer;
        return this;
    }

    public QuestionBuilder shouldBeAsked(Boolean shouldBeAsked) {
        this.shouldBeAsked = shouldBeAsked;
        return this;
    }

    public QuestionBuilder isEasy(Boolean isEasy) {
        this.isEasy = isEasy;
        return this;
    }

    public QuestionBuilder converterAutoApplies(Boolean converterAutoApplies) {
        this.converterAutoApplies = converterAutoApplies;
        return this;
    }

    public Question build() {
        return new Question(id, content, correctAnswer, shouldBeAsked, isEasy, converterAutoApplies);
    }
}