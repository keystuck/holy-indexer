/* SermCit
 * class to define citation objects
 * Emily Stuckey
 * 1-5-16
 */

 public class SermCit{
   private String phrase;
   private String sermon;
   private int paragraph;

   //constructor just initializes fields
   public SermCit(String wd, String serm, int para){
     phrase = wd;
     sermon = serm;
     paragraph = para;
   }

   public String toString(){
     return phrase + " " + sermon + "." + paragraph;
   }

   public int compareTo(SermCit other){
     if (!phrase.equals(other.phrase)){
       return phrase.compareTo(other.phrase);
     }
     else if(!sermon.equals(other.sermon)){
       return sermon.compareTo(other.sermon);
     }
     else {
       return paragraph - other.paragraph;
     }
   }
 }
