package quiz;

public class Question {
	public int id;
	public String question;
    public String[] answers; 
    public int correct;      

    public Question() {}

    public Question(int id, String question, String[] answers, int correct) {
    	this.id = id;
    	this.question = question;
        this.answers = answers;
        this.correct = correct;
    }

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getQuestion() {
		return question;
	}

	public void setQuestion(String question) {
		this.question = question;
	}

	public String[] getAnswers() {
		return answers;
	}

	public void setAnswers(String[] answers) {
		this.answers = answers;
	}

	public int getCorrect() {
		return correct;
	}

	public void setCorrect(int correct) {
		this.correct = correct;
	}

}