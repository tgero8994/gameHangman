public class RandomWord
{
   String[] randomWord;
   
   public RandomWord() {}
   
   public RandomWord(String[] word)
   {
      randomWord = word;
   }
   
   public String getWord()
   {
      return randomWord[0];
   }
}