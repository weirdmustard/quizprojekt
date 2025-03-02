package quiz;

import javax.swing.*;
import java.awt.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.util.ArrayList;

public class QuizApp {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(QuizFrame::new);
    }
}

class QuizFrame extends JFrame {
  
	private static final long serialVersionUID = 1L;
	private JLabel questionLabel;
    private JLabel imageLabel; // Label to display the image
    private JButton[] answerButtons;
    private List<Question> questionList;
    private int currentQuestionIndex = 0;

    public QuizFrame() {
        setTitle("Quiz");
        setSize(1280, 960);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // Load questions from JSON
        questionList = loadQuestionsFromJson("src/res/questions.json");

        // Upper section: Question Label
        questionLabel = new JLabel("Loading question...", SwingConstants.CENTER);
        questionLabel.setFont(new Font("Arial", Font.BOLD, 24));
        add(questionLabel, BorderLayout.NORTH);

        // Upper section: Image Label
        imageLabel = new JLabel();
        imageLabel.setHorizontalAlignment(JLabel.CENTER);
        add(imageLabel, BorderLayout.CENTER); // Add image label in the center

        // Lower section: Answer Buttons
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(2, 2, 10, 10));
        answerButtons = new JButton[4];

        for (int i = 0; i < 4; i++) {
            answerButtons[i] = new JButton();
            answerButtons[i].setFont(new Font("Arial", Font.PLAIN, 18));
            buttonPanel.add(answerButtons[i]);
            int index = i;
            answerButtons[i].addActionListener(e -> checkAnswer(index));
        }

        add(buttonPanel, BorderLayout.SOUTH);

        // Display first question
        loadNextQuestion();

        setVisible(true);
    }

    public List<Question> loadQuestionsFromJson(String filePath) {
        try {
            String json = new String(Files.readAllBytes(Paths.get(filePath)));
            return new Gson().fromJson(json, new TypeToken<ArrayList<Question>>() {}.getType());
        } catch (Exception e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    private void loadNextQuestion() {
        if (currentQuestionIndex >= questionList.size()) {
            questionLabel.setText("Quiz finished!");
            imageLabel.setIcon(null); // Hide image when quiz is over
            return;
        }

        Question question = questionList.get(currentQuestionIndex);
        questionLabel.setText(question.question);

        // Dynamically load the image for each question using the id
        ImageIcon imageIcon = new ImageIcon(getClass().getClassLoader().getResource("res/images/question" + question.id + ".jpg"));
        imageLabel.setIcon(imageIcon);

        for (int i = 0; i < 4; i++) {
            answerButtons[i].setText(question.answers[i]);
            answerButtons[i].setBackground(null);
            answerButtons[i].setEnabled(true);
        }
    }
    
    public void checkAnswer(int index) {
        Question question = questionList.get(currentQuestionIndex);
        boolean isCorrect = (index == question.correct);

        answerButtons[index].setBackground(isCorrect ? Color.MAGENTA : Color.GRAY);

        for (JButton button : answerButtons) {
            button.setEnabled(false);
        }

        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                currentQuestionIndex++;
                SwingUtilities.invokeLater(() -> loadNextQuestion());
            }
        }, 3000);
    }
}
