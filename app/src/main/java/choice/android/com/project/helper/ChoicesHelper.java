package choice.android.com.project.helper;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;
public class ChoicesHelper
{
    public static int[] getShuffleChoice(int correctIndex, int choiceSize, int requestSize)
    {
        List<Integer> TempFullSize = new LinkedList<>();
        Random        random       = new Random();
        int[]         Choices      = new int[requestSize];
        Choices[0] = correctIndex;
        for (int i = 0; i < choiceSize; i++)
        {
            TempFullSize.add(new Integer(i));
        }
        TempFullSize.remove(new Integer(correctIndex));
        for (int i = 1; i < requestSize; i++)
        {
            int generateIndex = random.nextInt(TempFullSize.size());
            Choices[i] = TempFullSize.get(generateIndex);
            TempFullSize.remove(generateIndex);
        }
        shuffleArray(Choices);
        return Choices;
    }
    private static void shuffleArray(int[] ar)
    {
        Random rnd = new Random();
        for (int i = ar.length - 1; i > 0; i--)
        {
            int index = rnd.nextInt(i + 1);
            int a     = ar[index];
            ar[index] = ar[i];
            ar[i] = a;
        }
    }

    private static void removeValueInArray(List<Integer> values , int removeValue){
        int removeIndex = -1;
        for(int i = 0 ; i < values.size() ; i++){
            if(values.get(i) == removeIndex){
                removeIndex = i;
                break;
            }
        }
        values.remove(removeIndex);
    }

}
