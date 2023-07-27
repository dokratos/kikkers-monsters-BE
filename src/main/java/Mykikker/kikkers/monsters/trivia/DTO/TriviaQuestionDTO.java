package Mykikker.kikkers.monsters.trivia.DTO;

public record TriviaQuestionDTO(String category,
                                String type,
                                String difficulty,
                                String question,
                                String correct_answer,
                                String[] incorrect_answers,
                                String[] shuffled_answer) {
}
