package Mykikker.kikkers.monsters.unsplash;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class MemoryCardCreate {
    public static String[] images(String[] cards) {
        ArrayList<String> gameCards = new ArrayList<>();
        gameCards.addAll(Arrays.stream(cards).toList());
        gameCards.addAll(Arrays.stream(cards).toList());
        Collections.shuffle(gameCards);
        return gameCards.toArray(String[]::new);
     }
}
