package choice.android.com.project.manager;

import android.content.Context;
import android.content.res.TypedArray;

import choice.android.com.project.R;
import choice.android.com.project.helper.ChoicesHelper;

public class GameManager
{
    private int currentChoiceIndex = 0;
    private Context    currentContext;
    private TypedArray Questions;
    private TypedArray Answers;
    private int[]      CurrentAnswerSet;
    private int currentCorrectIndex = -1;
    private int requestChoiceSize = 8;

    public static GameManager newInstance(Context context, Language language)
    {
        return new GameManager(context, language);
    }

    public GameManager(Context context, Language language)
    {
        currentContext = context;
        switch (language)
        {
            case TH:
                Questions = context.getResources().obtainTypedArray(R.array.question_th);
                Answers = context.getResources().obtainTypedArray(R.array.answer_th);
                requestChoiceSize = 8;
                break;
            case EN:
                Questions = context.getResources().obtainTypedArray(R.array.question_en);
                Answers = context.getResources().obtainTypedArray(R.array.answer_en);
                requestChoiceSize = 4;
                break;
        }
        initData();
    }

    private void initData()
    {
        CurrentAnswerSet = ChoicesHelper.getShuffleChoice(currentChoiceIndex, 10, requestChoiceSize);
    }

    public int getCurrentChoiceResource()
    {
        return Questions.getResourceId(currentChoiceIndex,-1);
    }

    public int[] getCurrentResurceAnswer()
    {
        int[] residarray = new int[getCurrentAnswer().length];
        for (int i = 0; i < residarray.length; i++)
        {
            residarray[i] = Answers.getResourceId(getCurrentAnswer()[i],-1);
            if(getCurrentAnswer()[i] == currentChoiceIndex){
                currentCorrectIndex = i;
            }
        }
        return residarray;
    }

    public int[] getCurrentAnswer()
    {
        return CurrentAnswerSet;
    }

    public int getCurrentCorrectIndex(){
        return currentCorrectIndex;
    }

    public int getCurrentChoiceIndex()
    {
        return currentChoiceIndex;
    }

    public void nextChoice()
    {
        currentChoiceIndex++;
        initData();
    }

    public int getQuestionSize(){
        return Questions.length();
    }

    public enum Language
    {
        TH, EN;
    }
}
