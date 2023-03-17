package app.LoveCalculator.model;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLDecoder;

import jakarta.json.Json;
import jakarta.json.JsonObject;
import jakarta.json.JsonReader;

public class Calculator {
    
    private String fname;
    private String sname;
    private Integer percentage;
    private String result;
    private String formId;

    public String getFname() {return fname;}
    public void setFname(String fname) {this.fname = fname;}
    
    public String getSname() {return sname;}
    public void setSname(String sname) {this.sname = sname;}
    
    public Integer getPercentage() {return percentage;}
    public void setPercentage(Integer percentage) {this.percentage = percentage;}
    
    public String getResult() {return result;}
    public void setResult(String result) {this.result = result;}

    public String getFormId() {return formId;}
    public void setFormId(String formId) {this.formId = formId;}
    
    public static Calculator create(String json) throws IOException{
        System.out.println(json);
        System.out.println("Accessing Calculator create");
        Calculator c = new Calculator();
        try(InputStream is = new ByteArrayInputStream(json.getBytes())){
        JsonReader r = Json.createReader(is);
        // JsonReader r = new JsonReader(new StringReader(json));
        System.out.println("JsonReader used");
        JsonObject o = r.readObject();
        // remove encoding chars from API
        String person1name = URLDecoder.decode(o.getString("fname"), "UTF-8");
        String person2name = URLDecoder.decode(o.getString("sname"), "UTF-8");
        
        c.setFname(person1name);
        c.setSname(person2name);
        c.setPercentage(Integer.parseInt(o.getString("percentage")));
        c.setResult(o.getString("result"));
        }
        System.out.println(c);
        return c;
    }
  
}
