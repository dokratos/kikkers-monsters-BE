package Mykikker.kikkers.monsters.controller;

import Mykikker.kikkers.monsters.unsplash.ImageFetcher;
import Mykikker.kikkers.monsters.unsplash.MemoryCardCreate;
import Mykikker.kikkers.monsters.user.Player;
import Mykikker.kikkers.monsters.user.PlayerDTO;
import Mykikker.kikkers.monsters.user.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
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
    public ResponseEntity<Player> postUser(@RequestBody @Valid PlayerDTO player, HttpServletRequest req) {
        Player newPlayer = userService.createUser(player);
        URI location = URI.create(String.format("%s/%s", req.getRequestURI(), newPlayer.getId()));
        return ResponseEntity.created(location).body(newPlayer);
    }

    @GetMapping("/images")
    public ResponseEntity<List<String>> listImages(@RequestParam @Valid String query, @RequestParam int amount) {
        String[] urls = unsplash.fetchImage(query, amount);
        String[] cards = MemoryCardCreate.images(urls);
        return ResponseEntity.ok().body(Arrays.stream(cards).toList());
    }
}
