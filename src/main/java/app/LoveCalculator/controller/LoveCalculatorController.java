package app.LoveCalculator.controller;

import java.io.IOException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import app.LoveCalculator.model.Calculator;
import app.LoveCalculator.service.LoveCalService;

@Controller
public class LoveCalculatorController {
    
    @Autowired
    private LoveCalService lcSvc;

    @GetMapping(path="/")
    public String index(Model model) {
        model.addAttribute("Calculator", new Calculator());
        return "index";
    }

    @GetMapping(path="/getpercentage")
    public String getCal(@RequestParam(required=true) String fname, 
                    @RequestParam(required=true) String sname,
                    Model m) throws IOException{
                        System.out.println(">>>>");
        Optional<Calculator> c = lcSvc.getResult(fname, sname);
        System.out.println("here>>>>" + c.get());
        m.addAttribute("Calculator", c.get());
        return "getpercentage";
    }
}
