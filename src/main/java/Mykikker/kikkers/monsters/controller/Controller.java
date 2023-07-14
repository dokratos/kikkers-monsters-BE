package Mykikker.kikkers.monsters.controller;

import Mykikker.kikkers.monsters.user.Player;
import Mykikker.kikkers.monsters.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/")
public class Controller {
    UserService userService;

    public Controller(@Autowired UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public String runTheApi() {
        return "I am online, and I am deployed with ci/cd";
    }

    @GetMapping(path = "/users")
    public ResponseEntity<List<Player>> listUsers() {
        List<Player> users = userService.getUsers();
        return ResponseEntity.ok().body(users);
    }

    @PostMapping(path = "/users")
    public ResponseEntity<Player> postUser(@RequestBody String name) {
        return ResponseEntity.ok().body(userService.createUser(name));
    }
}
