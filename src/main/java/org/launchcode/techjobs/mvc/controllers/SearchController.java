package org.launchcode.techjobs.mvc.controllers;

import org.launchcode.techjobs.mvc.models.Job;
import org.launchcode.techjobs.mvc.models.JobData;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static org.launchcode.techjobs.mvc.controllers.ListController.columnChoices;
import static org.launchcode.techjobs.mvc.controllers.ListController.tableChoices;


/**
 * Created by LaunchCode
 */
@Controller
@RequestMapping("search")
public class SearchController {

    @GetMapping(value = "")
    public String search(Model model) {
        model.addAttribute("columns", columnChoices);
        return "search";
    }

    // TODO #3 - Create a handler to process a search request and render the updated search view.


    @PostMapping("results")
    public String  displaySearchResults (Model model,@RequestParam (required = false) String searchType,
                                                @RequestParam(required = false) String searchTerm){

        ArrayList<Job> jobs=new ArrayList<>();

        model.addAttribute("searchType",searchType);
        model.addAttribute("searchTerm",searchTerm);
        model.addAttribute("columns",columnChoices);


        if(searchTerm.equalsIgnoreCase("all")||searchTerm.equalsIgnoreCase("")){
            jobs=JobData.findAll();

        }else{
            jobs=JobData.findByColumnAndValue(searchType,searchTerm);
        }

        model.addAttribute("values",jobs);

        return "search";

    }

}
