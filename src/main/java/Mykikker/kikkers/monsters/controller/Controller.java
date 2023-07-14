package Mykikker.kikkers.monsters.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class Controller {

    @GetMapping
    public String runTheApi() {
        return "I am online, and I am deployed with ci/cd";
    }
}
