package kg.attractor.istore.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

    @GetMapping("/iphones")
    public String getIphones(){
        return "iphone";
    }

    @GetMapping("/macs")
    public String getMacs(){
        return "mac";
    }

    @GetMapping("/airpods")
    public String getAirpods(){
        return "airpods";
    }

    @GetMapping("/ipads")
    public String getIpads(){
        return "ipad";
    }

    @GetMapping("/apple-watches")
    public String getAppleWatches(){
        return "apple_watch";
    }
}
