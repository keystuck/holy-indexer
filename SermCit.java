
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
   "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21",
   "22", "23", "24", "25", "26", "27", "28", "29", "30", "31", "32", "33", "34",
   "35", "36", "37", "38", "39", "40", "41", "42", "43", "44", "45", "46", "47",
   "48", "49", "50", "51", "52", "53", "54", "55", "56", "57", "58", "59", "60",
   "61", "62", "63", "64", "65", "66", "67", "68", "69", "70", "71", "72", "73",
   "74", "75", "76", "77", "78", "79", "80", "81", "82", "83", "84", "85", "86",
   "87", "88", "89", "90", "91", "92", "93", "94", "95", "96", "97", "98", "99",
   "100", "101", "102", "103", "104", "105", "106", "107", "108", "109", "110",
   "111", "112", "113", "114", "115", "116", "117", "118", "119", "120", "121",
   "122", "123", "124", "125");

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
