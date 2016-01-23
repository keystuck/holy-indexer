/* SermCit
 * class to define citation objects
 * Emily Stuckey
 * 1-5-16
 */

import java.util.*;

 public class SermCit{
//   protected String phrase;
   protected String sermon;
   protected int paragraph;

   //THIS SERMON LIST IS SPECIFIC TO THE BOOK BEING INDEXED
   protected List<String> Sermons = Arrays.asList("1", "2", "3", "4", "5", "6", "7",
   "8", "9", "10", "11");

   //constructor just initializes fields
   public SermCit(String serm, int para){
//     phrase = wd;
     sermon = serm;
     paragraph = para;
   }
   public SermCit(String altogether){
     sermon = altogether.substring(0, altogether.indexOf("."));
     paragraph = Integer.parseInt(altogether.substring(altogether.indexOf(".")+1));
   }

   public String toString(){
     return sermon + "." + paragraph;
   }

   public int compareTo(SermCit other){
     if(!sermon.equals(other.sermon)){
       return Integer.compare(Sermons.indexOf(sermon), Sermons.indexOf(other.sermon));
     }
     else {
       return Integer.compare(paragraph, other.paragraph);
     }
   }
 }
