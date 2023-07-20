package Mykikker.kikkers.monsters.openAI.DTO;

import java.util.List;

public record RequestDTO(String model, List<Message> messages, int n, double temperature) {
}
