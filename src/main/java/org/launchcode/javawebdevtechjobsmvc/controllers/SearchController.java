package org.launchcode.javawebdevtechjobsmvc.controllers;

import org.launchcode.javawebdevtechjobsmvc.models.Job;
import org.launchcode.javawebdevtechjobsmvc.models.JobData;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

import static org.launchcode.javawebdevtechjobsmvc.controllers.ListController.columnChoices;

/**
 * Created by LaunchCode
 */
@Controller
@RequestMapping("search")
public class SearchController {

    @RequestMapping(value = "")
    public String search(Model model) {
        model.addAttribute("columns", columnChoices);
        return "search";
    }

    // TODO #3 - Create a handler to process a search request and render the updated search view.

    @RequestMapping(value = "results") //@RequestMapping with @RequestParam to retrieve the URL parameter in search.html and map it to the method argument

    public String displaySearchResults(Model model, @RequestParam String searchType, @RequestParam String searchTerm) {
        //requesting searchType and searchTerm data from HTML

        ArrayList<Job> jobs; //declaring array list "jobs"

        jobs = JobData.findByColumnAndValue(searchType, searchTerm);
        //populating array list with info from RequestParam

        model.addAttribute("columns", columnChoices);
        //assigning Hashmap columnChoices the name "columns" and adding to the model. Then html displays info

        model.addAttribute("title", "Search Condition: " + columnChoices.get(searchType) + " Search Term: " + searchTerm);
        //displaying the entered searchType and searchTerm to the view

        model.addAttribute("jobs", jobs); //assigning arraylist to the model
        return "search.html";
    }


}
