package Mykikker.kikkers.monsters.trivia;

import Mykikker.kikkers.monsters.trivia.DTO.ResponseRoot;
import Mykikker.kikkers.monsters.trivia.DTO.TriviaQuestionDTO;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TriviaShuffle {
    public List<TriviaQuestionDTO> createTrivia(Mono<ResponseRoot> response) {
        List<TriviaQuestionDTO> trivia = response
                .block()
                .results
                .stream()
                .map(result ->
                        new TriviaQuestionDTO(result.category,
                                result.type,
                                result.difficulty,
                                result.question,
                                result.correct_answer,
                                result.incorrect_answers,
                                shuffler(result.correct_answer, result.incorrect_answers)))
                .collect(Collectors.toList());
        return trivia;
    }

    public static String[] shuffler(String correct_answer,
                                    String[] incorrect_answers) {
        ArrayList<String> answers = new ArrayList<>();
        answers.add(correct_answer);
        answers.addAll(Arrays.stream(incorrect_answers).toList());
        Collections.shuffle(answers);
        return answers.toArray(String[]::new);
    }
}
