package com.example.firstaid.web;

import com.example.firstaid.service.InjuryService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping("/injury")
public class InjuryController {

private final InjuryService injuryService;

    public InjuryController(InjuryService injuryService) {
        this.injuryService = injuryService;
    }

    //this action is called by the admin/editor of the page
    @GetMapping("/form")
    public String getInjuryForm(@RequestParam(required = false) String error,Model model){
        if (error != null && error.isEmpty()) {
            model.addAttribute("hasError", true);
            model.addAttribute("error", error);
        }
        model.addAttribute("bodyContent", "injury-form");
        return "master-template";
    }
    @PostMapping("/form")
    public String addNewInjury(@RequestParam String injuryName,
                               @RequestParam String context,
                               @RequestParam MultipartFile urlImg,
                               @RequestParam Integer category, Model model){

        this.injuryService.saveInjury(injuryName,context,urlImg,category);
        model.addAttribute("bodyContent", "injury-form");
        return "master-template";
    }
}
