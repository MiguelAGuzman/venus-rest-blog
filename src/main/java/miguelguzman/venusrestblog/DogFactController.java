package miguelguzman.venusrestblog;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

public class DogFactController {
    @GetMapping
    @ResponseBody
    public String fetchDogFact() {
        return "Puppies are a pain in the butt!";
    }

    @GetMapping("/dogo/{id}")
    @ResponseBody
    public String fetchSingleDogFact (@PathVariable long id){
//fetch the particular dog fact w/matching id from database
        return FakeDogFactGateway.fetchDogFactById(id);
    }
}
