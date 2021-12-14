package org.launchcode.techjobs.mvc.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.launchcode.techjobs.mvc.models.Job;
import org.launchcode.techjobs.mvc.models.JobData;
import java.util.ArrayList;

import static org.launchcode.techjobs.mvc.controllers.ListController.columnChoices;



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
    //Creating a handler (function method) to process data/messages
    //imported PostMapping/RequestMapping(method = RequestMethod.POST)

    @PostMapping("results")
    //PostMapping is a shortcut/annotation to RequestMapping
    //Handle incoming request method

    public String displaySearchResults(Model model, @RequestParam String searchType, @RequestParam String searchTerm) {
        ArrayList<Job> jobs;
        //@RequestParm an annotation /binding web request to M() Parameter


        if (searchTerm == "all" || searchTerm == "") {
            jobs = JobData.findAll();
        } else {
            jobs = JobData.findByColumnAndValue(searchType, searchTerm);
        }
        //purose if using .findAll() return entities/ save in dbase
        model.addAttribute("columns", columnChoices);
        model.addAttribute("searchType", searchType);
        model.addAttribute("searchTerm", searchTerm);
        model.addAttribute("title", "Jobs With " + columnChoices.get(searchType) + ": "  + searchTerm);
        model.addAttribute("jobs", jobs);
//purpose of model.addAttribute is to give value of the model in order to be identified
        return "search";
    }

}

