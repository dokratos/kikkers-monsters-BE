package Mykikker.kikkers.monsters.controller;

import Mykikker.kikkers.monsters.unsplash.ImageFetcher;
import Mykikker.kikkers.monsters.user.Player;
import Mykikker.kikkers.monsters.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping()
public class Controller {
    UserService userService;

    @Autowired
    ImageFetcher unsplash;

    public Controller(@Autowired UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public String runTheApi() {
        return "hello yaml!";
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

    @GetMapping("/images")
    public ResponseEntity<List<String>> listImages(@RequestBody String query) {
        String[] urls = unsplash.fetchImage(query);
        return ResponseEntity.ok().body(Arrays.stream(urls).toList());
    }
}
