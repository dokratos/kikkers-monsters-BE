package Mykikker.kikkers.monsters.openAI.DTO;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ChatResponse {

    private List<Choice> choices;

    public List<Choice> getChoices() {
        return choices;
    }

    public void setChoices(List<Choice> choices) {
        this.choices = choices;
    }

    public ChatResponse(List<Choice> choices) {
        this.choices = choices;
    }

    public ChatResponse() {
    }
}

