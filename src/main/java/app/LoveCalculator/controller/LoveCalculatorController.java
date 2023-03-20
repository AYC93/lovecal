package app.LoveCalculator.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import app.LoveCalculator.model.Calculator;
import app.LoveCalculator.repo.LoveCalRepo;
import app.LoveCalculator.service.LoveCalService;

@Controller
@RequestMapping(path = "/")
public class LoveCalculatorController {
    
    @Autowired
    private LoveCalService lcSvc;

    @Autowired
    private LoveCalRepo lcr;

    @GetMapping
    public String index(Model model) {
        model.addAttribute("Calculator", new Calculator());
        return "index";
    }

    @GetMapping(path="getpercentage")
    public String getCal(@RequestParam(required=true) String fname, 
                    @RequestParam(required=true) String sname,
                    Model m) throws IOException{
                        System.out.println(">>>>");
        Calculator c = lcSvc.getResult(fname, sname);
        System.out.println("here>>>>" + c);
        
        //m.addAttribute("getpercentage", c.get());

        String formId = lcSvc.createCalForm();
        System.out.println("formId: " + formId);
        
        c.setFormId(formId);
        // "getpercentage" links back to html, not to the term defined in the html
        m.addAttribute("getpercentage", c);
        lcr.save(c);

        return "getpercentage";
    }
}
