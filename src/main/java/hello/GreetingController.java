package hello;

import java.util.Set;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.http.HttpHeaders;
//import org.springframework.http.HttpMethod;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RequestMethod;

@RestController
public class GreetingController {

    private static final String template = "Hello, %s!";
    private final AtomicLong counter = new AtomicLong();

    @RequestMapping("/greeting")
    public Greeting greeting(@RequestParam(value="name", defaultValue="World") String name) {
        return new Greeting(counter.incrementAndGet(),
                            String.format(template, name));
    }

    @RequestMapping(value="/callback", method=RequestMethod.POST)
    public String callbackListener(@RequestBody String name, @RequestHeader HttpHeaders headers) {
        Set<String> headerNames = headers.keySet();
        for(String aHeaderName: headerNames){
            System.out.println(aHeaderName + " = " + headers.get(aHeaderName));
        }
        System.out.println("RequestBody: " + name);
        return "<H1>Hello! OK!!</H1>";
    }
}
