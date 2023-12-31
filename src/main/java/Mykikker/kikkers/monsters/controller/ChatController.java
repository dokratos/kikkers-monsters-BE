package Mykikker.kikkers.monsters.controller;

import Mykikker.kikkers.monsters.openAI.ChatService;
import Mykikker.kikkers.monsters.openAI.DTO.ChatResponse;
import Mykikker.kikkers.monsters.openAI.DTO.Message;
import Mykikker.kikkers.monsters.openAI.DTO.RequestDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class ChatController {

    @Qualifier("openaiRestTemplate")
    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    ChatService service;

    @Value("${sources.openai.url}")
    private String apiUrl;

    @GetMapping("/chat")
    public String chat(@RequestParam String theme, @RequestParam String game) {
        // create a request
        RequestDTO request = service.createRequest(theme, game);

        // call the API
        ChatResponse response = restTemplate.postForObject(apiUrl, request, ChatResponse.class);

        if (response == null || response.getChoices() == null || response.getChoices().isEmpty()) {
            return "No response";
        }

        // return the first response
        return response.getChoices().get(0).getMessage().getContent();
    }
}
