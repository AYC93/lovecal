package app.LoveCalculator.service;

import java.io.IOException;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import app.LoveCalculator.model.Calculator;

@Service
public class LoveCalService {
    
    @Value("${apiprac.love.calculator.url}")
    private String loveCalulatorUrl;

    @Value("${apiprac.love.calculator.api.key}")
    private String loveCalculatorApi;

    @Value("${apiprac.love.calculator.host}")
    private String loveCalculatorHost;
    

    public Optional<Calculator> getResult(String fname, String sname)throws IOException{
        String loveCalUrl = UriComponentsBuilder
                            .fromUriString(loveCalulatorUrl)
                            .path("getPercentage")
                            .queryParam("fname", fname)
                            .queryParam("sname", sname)
                            .toUriString();

        System.out.println(loveCalUrl);

        RequestEntity<Void> req = RequestEntity.get(loveCalUrl)
                        .header("x-rapidapi-host", loveCalculatorHost)
                        .header("x-rapidapi-key", loveCalculatorApi)
                        .accept(MediaType.APPLICATION_JSON)
                        .build();

        RestTemplate template = new RestTemplate();
        ResponseEntity<String> r = template.exchange(req, String.class);

        System.out.println(r.getBody());
        Calculator c = Calculator.create(r.getBody());
        
        if(c != null)
            return Optional.of(c);

        createCalForm();
        return Optional.empty();                       
    }

    public Calculator createCalForm(){
        // not secure but requested by assignment
        String formId = UUID.randomUUID().toString().substring(0, 8);
        Calculator c = new Calculator();
        c.setFormId(formId);
        return c;
    }
}
