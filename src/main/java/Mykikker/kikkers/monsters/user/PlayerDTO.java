package Mykikker.kikkers.monsters.user;

import com.fasterxml.jackson.annotation.JsonProperty;

public record PlayerDTO(@JsonProperty String name, @JsonProperty int score) {
}
