package com.example.firstaid.service;

import com.example.firstaid.model.QuestionForm;
import com.example.firstaid.model.Result;

import java.util.List;

public interface QuizService {
     QuestionForm getQuestions();
     int getResult(QuestionForm qForm);
     void saveScore(Result result);
     List<Result> getTopScore();
}
