/** ScriptCit for Scriptural Citations
 * Adapted from SermCit
 * Emily Stuckey
 * 1-6-16
 * redefined 1-21-16
 * doesn't need info about sermon - just about bible citation
 */
import java.util.*;

public class ScriptCit implements Comparable<ScriptCit> {
  /* Example: book = "John", chapter = 5, verse = "1-3" for John 5:1-3 */
  private String book;
  private int chapter;
  private String verse;

/* Order of Books of the Bible with appropriate abbreviations */

  private List<String> Books = Arrays.asList("Gen", "Exod", "Lev", "Num", "Deut",
  "Josh", "Judg", "Ruth", "1 Sam", "2 Sam", "1 Kgs", "2 Kgs", "1 Chr", "2 Chr",
  "Ezra", "Neh", "Esth", "Job", "Ps", "Prov", "Eccl", "Song",
  "Isa", "Jer", "Lam", "Ezek", "Dan", "Hos", "Joel", "Amos", "Obad", "Jonah",
  "Mic", "Nah", "Hab", "Zeph", "Hag", "Zech", "Mal", "Tob", "Jdt", "Add Esth",
  "Wis", "Sir", "Bar", "1 Esdr", "2 Esdr", "Ep Jer", "Pr Azar", "Sus", "Bel",
  "1 Macc", "2 Macc", "3 Macc", "4 Macc", "Pr Man", "Matt", "Mark", "Luke", "John",
  "Acts", "Rom", "1 Cor", "2 Cor", "Gal", "Eph", "Phil", "Col", "1 Thess", "2 Thess",
  "1 Tim", "2 Tim", "Titus", "Phlm", "Heb", "Jas", "1 Pet", "2 Pet", "1 John",
  "2 John", "3 John", "Jude", "Rev");


  /** constructor from a String
   * used when you have "John 5:1-3" and need to split it up
   * @param altogether the complete String
   */
  public ScriptCit(String altogether){
    int space = altogether.lastIndexOf(" ");
    int colon = altogether.indexOf(":");
    if (space == -1 || colon == -1 || colon == altogether.length() - 1){
      System.out.println("Problem in ScriptCit constructor on " + altogether);
    }
    else if (colon < space){
        System.out.println("colon-space problem at " + altogether);
    }
    else {
      book = altogether.substring(0, space);
      chapter = Integer.parseInt(altogether.substring(space + 1, colon));
      verse = altogether.substring(colon + 1);
    }
  }

  /** constructor from individual pieces
   * @param bk Bible book (e.g. "John")
   * @param bChap chapter (e.g. 1)
   * @param bPara the verse or verses as a string
   */
  public ScriptCit(String bk, int bChap, String bPara){
    book = bk;
    chapter = bChap;
    verse = bPara;
  }

  @Override
  public String toString(){
    return book + " " + chapter + ":" + verse;
  }

  /** compareTo checks order of books, then chapters, then verses
   * @param other Other scritpural citation
   */
   @Override
  public int compareTo(ScriptCit other){
    //compare books
    int diff = Integer.compare(Books.indexOf(book), Books.indexOf(other.book));
    if (diff != 0){
      return diff;
    }
    //compare chapters
    else if (chapter != other.chapter){
      return Integer.compare(chapter, other.chapter);
    }

    //to compare verses, must consider that the reference might be annotated
    // "Vulg" (Vulgate) or "LXX" (Septuagint) which should be separate
    String thisverse = verse;
    String otherverse = other.verse;
    //replace "Vulg" with "0000" and "LXX" with "1111" to separate
      //as of 5-27-17 -- also "Var" with "2222"
    if (verse.contains("Vulg")){
      thisverse = thisverse.substring(0, thisverse.indexOf("Vulg")) + "0000";
    }
    else if (verse.contains("LXX")){
      thisverse = thisverse.substring(0, thisverse.indexOf("LXX")) + "1111";
    }
    else if (verse.contains("Var")){
        thisverse = thisverse.substring(0, thisverse.indexOf("Var")) + "2222";
    }
    //do same for other citation
    if (otherverse.contains("Vulg")){
      otherverse = otherverse.substring(0, otherverse.indexOf("Vulg")) + "0000";
    }
    else if (otherverse.contains("LXX")){
      otherverse = otherverse.substring(0, otherverse.indexOf("LXX")) + "1111";
    }
    else if (otherverse.contains("Var")){
        otherverse = otherverse.substring(0, otherverse.indexOf("Var")) + "2222";
    }

    //must also consider possibility of range of verses - either citation could
    //be "John 5:1-4" or "John 1:4"
    //first & second will hold beginning & end of range for this citation
    // and othFirst & othSecond for other
    //if there is no range, first will be the same as second, etc.
    int othFirst = -1;
    int othSecond = -1;
    int first = -1;
    int second = -1;
    if (otherverse.indexOf("-") != -1){
      othFirst = Integer.parseInt(otherverse.substring(0, otherverse.indexOf("-")));
      othSecond = Integer.parseInt(otherverse.substring(otherverse.indexOf("-")+1));
    }
    else {
      othFirst = Integer.parseInt(otherverse);
      othSecond = othFirst;
    }
    if (thisverse.indexOf("-") != -1){
      first = Integer.parseInt(thisverse.substring(0, verse.indexOf("-")));
      second = Integer.parseInt(thisverse.substring(verse.indexOf("-")+1));
    }
    else {
      first = Integer.parseInt(thisverse);
      second = first;
    }
    if (first != othFirst){
      return Integer.compare(first, othFirst);
    }
    else {
      return Integer.compare(second, othSecond);
    }
  }
}
