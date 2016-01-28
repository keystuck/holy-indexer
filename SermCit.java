
import java.util.*;
/* SermCit
 * class to define citation objects to be used in an IndexEntry
 * with sermon, and paragraph
 * Emily Stuckey
 * 1-5-16
 */


 public class SermCit implements Comparable<SermCit>{

   /** The sermon in which the phrase occurs. */
   protected String sermon;

   /** The paragraph within the sermon containing the phrase. */
   protected int paragraph;

   /** Sermon list (SPECIFIC TO BOOK BEING INDEXED) for ordering purposes */
    protected List<String> Sermons = Arrays.asList("1", "2", "3", "4", "5", "6", "7",
   "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20");

   /** Initializes fields
   * @param serm Sermon where citation occurs
   * @param para paragraph within sermon
   */
   public SermCit(String serm, int para){
     sermon = serm;
     paragraph = para;
   }

   /** Retrieves information for fields from a string
    * @param altogether A string containing citation info: e.g. "1.7"
    */
   public SermCit(String altogether){
     sermon = altogether.substring(0, altogether.indexOf("."));
     paragraph = Integer.parseInt(altogether.substring(altogether.indexOf(".")+1));
   }

   @Override
   public String toString(){
     return sermon + "." + paragraph;
   }

   /** Compare by sermon, then by paragraph
    * @param other Other SermCit object to compare
    */
   @Override
   public int compareTo(SermCit other){
     if(!sermon.equals(other.sermon)){
       return Integer.compare(Sermons.indexOf(sermon), Sermons.indexOf(other.sermon));
     }
     else {
       return Integer.compare(paragraph, other.paragraph);
     }
   }
 }
