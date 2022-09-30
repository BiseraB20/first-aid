package com.example.firstaid.web;

import com.example.firstaid.model.QuestionForm;
import com.example.firstaid.model.Result;
import com.example.firstaid.service.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class QuizController {
    @Autowired
    Result result;
    @Autowired
    QuizService qService;

    Boolean submitted = false;

    @ModelAttribute("result")
    public Result getResult() {
        return result;
    }

    @GetMapping("/quiz")
    public String quiz(HttpServletRequest request, Model model, RedirectAttributes ra) {
        String username = request.getRemoteUser();
        if(username.equals("")) {
            ra.addFlashAttribute("warning", "You must enter your name");
            return "redirect:/";
        }

        submitted = false;
        result.setUsername(username);

        QuestionForm qForm = qService.getQuestions();
        model.addAttribute("qForm", qForm);
        model.addAttribute("bodyContent", "quiz");

        return "master-template";
    }

    @PostMapping("/quiz/submit")
    public String submit(@ModelAttribute QuestionForm qForm, Model model) {
        if(!submitted) {
            result.setTotalCorrect(qService.getResult(qForm));
            qService.saveScore(result);
            submitted = true;
        }
        model.addAttribute("bodyContent", "result");

        return "master-template";

    }

    @GetMapping("/score")
    public String score(Model m) {
        List<Result> sList = qService.getTopScore();
        m.addAttribute("sList", sList);

        return "scoreboard.html";
    }
}

