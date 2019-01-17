package treehousefirstandroid.kurosaki.quizapptreehouse;

import java.util.Random;

public class QuestionBank {

    public int getLeft(){
        Random randomGenerator = new Random();

        int randomNumber = randomGenerator.nextInt(80 );
        return randomNumber;
    }

    public int getRight(){
        Random randomGenerator = new Random();
        int randomNumber = randomGenerator.nextInt(80);

        return randomNumber;
    }

}
