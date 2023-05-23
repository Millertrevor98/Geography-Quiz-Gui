package com.example.geographyquizgui;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class GeographyQuizGui extends Application {
    private List<Question> questions;
    private int currentQuestionIndex;
    private Label questionLabel;
    private TextField answerField;
    private Button submitButton;
    private Label resultLabel;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Geography Quiz");

        // Create a list of questions and answers
        questions = new ArrayList<>();
        questions.add(new Question("What is the capital of France?", "Paris"));
        questions.add(new Question("Which country is known as the Land of the Rising Sun?", "Japan"));
        questions.add(new Question("What is the largest country in the world by land area?", "Russia"));
        // Add more questions here...

        // Shuffle the questions
        Collections.shuffle(questions);

        // Initialize variables
        currentQuestionIndex = 0;

        // Create GUI components
        questionLabel = new Label();
        questionLabel.setFont(Font.font(18));
        answerField = new TextField();
        submitButton = new Button("Submit");
        resultLabel = new Label();
        resultLabel.setWrapText(true);

        // Set up event handlers
        submitButton.setOnAction(event -> checkAnswer());

        // Set up layout
        VBox layout = new VBox(10);
        layout.setPadding(new Insets(20));
        layout.setAlignment(Pos.CENTER);
        layout.getChildren().addAll(questionLabel, answerField, submitButton, resultLabel);

        // Display the first question
        displayQuestion();

        primaryStage.setScene(new Scene(layout, 400, 300));
        primaryStage.show();
    }

    private void displayQuestion() {
        if (currentQuestionIndex < questions.size()) {
            Question currentQuestion = questions.get(currentQuestionIndex);
            questionLabel.setText("Question " + (currentQuestionIndex + 1) + ": " + currentQuestion.getQuestion());
            answerField.clear();
            resultLabel.setText("");
        } else {
            // Quiz finished
            submitButton.setDisable(true);
            questionLabel.setText("Quiz finished! Your score is: " + getScore() + "/" + questions.size());
        }
    }

    private void checkAnswer() {
        Question currentQuestion = questions.get(currentQuestionIndex);
        String userAnswer = answerField.getText();

        if (userAnswer.equalsIgnoreCase(currentQuestion.getAnswer())) {
            resultLabel.setText("Correct!");
            currentQuestion.setAnsweredCorrectly(true);
        } else {
            resultLabel.setText("Wrong! The correct answer is: " + currentQuestion.getAnswer());
        }

        currentQuestionIndex++;
        displayQuestion();
    }

    private int getScore() {
        int score = 0;
        for (Question question : questions) {
            if (question.isAnsweredCorrectly()) {
                score++;
            }
        }
        return score;
    }
}

class Question {
    private String question;
    private String answer;
    private boolean answeredCorrectly;

    public Question(String question, String answer) {
        this.question = question;
        this.answer = answer;
        this.answeredCorrectly = false;
    }

    public String getQuestion() {
        return question;
    }

    public String getAnswer() {
        return answer;
    }

    public boolean isAnsweredCorrectly() {
        return answeredCorrectly;
    }

    public void setAnsweredCorrectly(boolean answeredCorrectly) {
        this.answeredCorrectly = answeredCorrectly;
    }
}
