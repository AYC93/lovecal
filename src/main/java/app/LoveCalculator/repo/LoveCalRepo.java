package app.LoveCalculator.repo;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import app.LoveCalculator.model.Calculator;

@Repository
public class LoveCalRepo {
    
    @Autowired
    RedisTemplate<String, String> template;

    public void save(Calculator cal){
        template.opsForValue().set(cal.getFormId(), cal.toJSON().toString());
    }

    // public String[] calResult(){
    //     List<Calculator> redsisArr = new LinkedList<>();
        
    // }
}
