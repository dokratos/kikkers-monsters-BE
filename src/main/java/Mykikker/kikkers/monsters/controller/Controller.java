package Mykikker.kikkers.monsters.controller;

import Mykikker.kikkers.monsters.trivia.DTO.ResponseRoot;
import Mykikker.kikkers.monsters.unsplash.ImageFetcher;
import Mykikker.kikkers.monsters.unsplash.MemoryCardCreate;
import Mykikker.kikkers.monsters.user.Player;
import Mykikker.kikkers.monsters.user.PlayerDTO;
import Mykikker.kikkers.monsters.user.UserService;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.net.URI;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping()
public class Controller {
    UserService userService;

    @Autowired
    ImageFetcher unsplash;
    @Resource
    private WebClient webClient;

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
        try {
            String[] urls = unsplash.fetchImage(query, amount);
            String[] cards = MemoryCardCreate.images(urls);
            return ResponseEntity.ok().body(Arrays.stream(cards).toList());
        } catch (Exception ex) {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/trivia")
    public Mono<ResponseRoot> getCustomer(@RequestParam @Valid String amount,
                                          @RequestParam @Valid String category,
                                          @RequestParam @Valid String difficulty) {

        return webClient.get()
                .uri("?amount={amount}&category={category}&difficulty={difficulty}", amount, category, difficulty)
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .retrieve()
                .bodyToMono(ResponseRoot.class);
    }
}
