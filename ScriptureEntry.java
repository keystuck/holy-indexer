import java.util.*;

/** ScriptureEntry
 * represents the index entry for a single bible verse or series of verses
 * example:    John 3:2-7     1.2, 1.5, 4.7
*/
public class ScriptureEntry implements Comparable<ScriptureEntry>{

  private ScriptCit chapVerse;  /** ScriptCit object with book, chapter, verse */
  private List<SermCit> citations; /** List of SermCit objects -- 1.3, 2.7, etc. */

 /** Constructor with one biblical verse, no citations
  * @param cv The biblical verse - e.g. John 3:2-7
  */
  public ScriptureEntry(ScriptCit cv){
    chapVerse = cv;
    citations = null;
  }

  /** Constructor with verse and a list of citations
  * @param cv The biblical verse in ScriptCit form
  * @param cits The list of SermCit objects
  */
  public ScriptureEntry(ScriptCit cv, List<SermCit> cits){
    chapVerse = cv;
    citations = cits;
  }

  /** Constructor with verse in disassembled form and a list of citations
  * @param bk The Bible book
  * @param chap The Bible chapter
  * @param vrs The Bible verse or series of verses
  * @param cits The list of SermCit citations
  */
  public ScriptureEntry(String bk, int chap, String vrs, List<SermCit> cits){
    this(new ScriptCit(bk, chap, vrs), cits);
  }

  /** Constructor with verse in disassembled form, no citations yet
  * @param bk The Bible book
  * @param chap The Bible chapter
  * @param vrs The Bible verse or series of verses
  */
  public ScriptureEntry(String bk, int chap, String vrs){
    this(new ScriptCit(bk, chap, vrs), null);
  }

  /** Constructor with verse and one citation, both disassembled
  * @param bk The Bible book
  * @param chap The Bible chapter
  * @param vrs The Bible verse or series of verses
  * @param sermon The sermon where the reference occurs
  * @param para The pararaph within the sermon
  */
  public ScriptureEntry(String bk, int chap, String vrs, String sermon, int para){
    this(new ScriptCit(bk, chap, vrs), null);
    this.add(sermon, para);
  }

  /** Constructor with one String argument containing bible verse info and citation info
  * @param altogether The String e.g. "John 3:27-30\t1.3, 1.4, 7.3"
  */
  public ScriptureEntry(String altogether){

    //Up to the tab character is the biblical info
    chapVerse = new ScriptCit(altogether.substring(0, altogether.indexOf("\t")));

    //Cut off the biblical info to process the citations
    altogether = altogether.substring(altogether.lastIndexOf("\t")+1);

    //Citations are separated by commas
    int comm = altogether.indexOf(",");

    //Create a list for the citations
    citations = new ArrayList<SermCit>();
    while (comm != -1){
      //Make a SermCit out of the info before the first comma, then add to list
      citations.add(new SermCit(altogether.substring(0, comm)));

      //Cut off the citation you just added and the comma and space
      altogether = altogether.substring(comm+2);
      comm = altogether.indexOf(",");
    }
    //Create & add a citation from the info after the last comma
    citations.add(new SermCit(altogether));
  }

  /** Returns just the biblical information
  * @return ScriptCit eg "John 3.2"
  */
  public ScriptCit getScript(){
    return chapVerse;
  }

  /** Returns just the citation list
   * @return all sermon citations in a list eg "1.3, 2.7"
   */
  public List<SermCit> getCits(){
    return citations;
  }

  /** Adds a new citation to an existing entry
   * @param newCit SermCit citation eg "2.7"
   */
  public void add(SermCit newCit){
    if (citations == null){
      citations = new ArrayList<SermCit>();
    }
    if (citations.isEmpty()){
      citations.add(newCit);
    }
    else {
      int i = 0;

      //Find the place in the citation list for the new citation
      while (i < citations.size() && citations.get(i).compareTo(newCit)< 0){
        i++;
      }
      if (i < citations.size() && citations.get(i).compareTo(newCit) > 0){
        citations.add(i, newCit);
      }
      else if (i == citations.size() && citations.get(i-1).compareTo(newCit) < 0){
        citations.add(newCit);
      }
      //otherwise the citation already exists, so leave it be
    }
  }

  /** Adds a list of citations
  * @param citList List of SermCit objects
  */
  public void add(List<SermCit> citList){
    //Add one by one
    for (SermCit x : citList){
      add(x);
    }
  }

  /** Adds a new citation in disassembled form
  * @param sermon The sermon where the reference occurs
  * @param para The paragraph within the sermon
  */
  public void add(String sermon, int para){
    SermCit cit = new SermCit(sermon, para);
    this.add(cit);
  }

  /** Compare to another ScriptureEntry
  * @param other Another ScriptureEntry
  */
  @Override
  public int compareTo(ScriptureEntry other){
    return chapVerse.compareTo(other.chapVerse);
  }

  /** Return biblical info and string of sermon citations as a string
  * @return The String form of the biblical reference and all citations
  */
  @Override
  public String toString(){
    String result = chapVerse.toString() + "\t\t";
    String comm = "";
    for (SermCit blah : citations){
      result += comm + blah;
      comm = ", ";
    }
    return result;
  }
}
