package Mykikker.kikkers.monsters.openAI;

import Mykikker.kikkers.monsters.openAI.DTO.Message;
import Mykikker.kikkers.monsters.openAI.DTO.RequestDTO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ChatService {
    @Value("${sources.openai.model}")
    private String model;

    public RequestDTO createRequest(String theme, String game) {
        return new RequestDTO(model,
                new ArrayList<>(List.of(
                        new Message("user", "Congratulate me for winning the " + game + " game with theme " + theme))),
                1,
                0.5);
    }
}
