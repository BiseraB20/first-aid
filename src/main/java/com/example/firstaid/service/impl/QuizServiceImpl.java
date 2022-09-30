package com.example.firstaid.service.impl;

import com.example.firstaid.model.Question;
import com.example.firstaid.model.QuestionForm;
import com.example.firstaid.model.Result;
import com.example.firstaid.repository.QuestionRepositoryJPA;
import com.example.firstaid.repository.ResultRepositoryJPA;
import com.example.firstaid.service.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Service
public class QuizServiceImpl implements QuizService {
    @Autowired
    QuestionRepositoryJPA qRepo;
    @Autowired
    ResultRepositoryJPA rRepo;

    @Override
    public QuestionForm getQuestions() {
        //getting all questions from data base
        List<Question> allQues = qRepo.findAll();
        //making new list with 5 random questions from all of them
        List<Question> qList = new ArrayList<Question>();
        Random random = new Random();
        for(int i=0; i<5; i++) {
            int rand = random.nextInt(allQues.size());
            qList.add(allQues.get(rand));
            allQues.remove(rand);
        }
        QuestionForm qForm=new QuestionForm();
        qForm.setQuestions(qList);
        return qForm;
    }

    @Override
    public int getResult(QuestionForm qForm) {
        int correct = 0;

        for(Question q: qForm.getQuestions())
            if(q.getAns() == q.getChose())
                correct++;

        return correct;
    }

    @Override
    public void saveScore(Result result) {
        Result saveResult = new Result();
        saveResult.setUsername(result.getUsername());
        saveResult.setTotalCorrect(result.getTotalCorrect());
        rRepo.save(saveResult);
    }

    @Override
    public List<Result> getTopScore() {
        List<Result> sList = rRepo.findAll(Sort.by(Sort.Direction.DESC, "totalCorrect"));
        return sList;
    }
}
