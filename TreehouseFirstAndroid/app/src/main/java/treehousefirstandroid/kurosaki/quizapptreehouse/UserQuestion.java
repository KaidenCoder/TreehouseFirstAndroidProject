package treehousefirstandroid.kurosaki.quizapptreehouse;

public class UserQuestion {

    String formQuestion(int firstNumber, int secondNumber) {
        return "What is the sum of " + firstNumber + " + " + secondNumber + "?";
    }

    String formProgress(int correct, int totalCount) {
        return "Progress: " + correct + "/" + totalCount + " correct";
    }
}
