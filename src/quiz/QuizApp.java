package quiz;

import javax.swing.*;
import java.awt.*;

public class QuizApp {
	
	public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new QuizFrame();
        });
    }
}

class QuizFrame extends JFrame {
    public QuizFrame() {
        setTitle("Quiz");
        setSize(1280, 960);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // Oberer Bereich mit Bild
        JLabel imageLabel = new JLabel();
        ImageIcon imageIcon = new ImageIcon("quiz_image.jpg"); // Pfad anpassen
        imageLabel.setIcon(imageIcon);
        imageLabel.setHorizontalAlignment(JLabel.CENTER);
        add(imageLabel, BorderLayout.CENTER);

        // Unterer Bereich mit Antwortbuttons
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(2, 2, 10, 10));

        for (int i = 1; i <= 4; i++) {
            JButton button = new JButton("Antwort " + i);
            buttonPanel.add(button);
        }

        add(buttonPanel, BorderLayout.SOUTH);

        setVisible(true);
    }
	

}
