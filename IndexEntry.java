import java.util.*;

/** IndexEntry
 * represents the index entry for a single word/phrase
 * example:    Holy Spirit     1.2, 1.5, 4.7
 */
public class IndexEntry implements Comparable<IndexEntry>{
  /** Word or phrase to be indexed */
  private String word;
  /** List of citations */
  private List<SermCit> citations;

/** Constructor given phrase and list
 * @param wd Phrase to index
 * @param cits List of citations, comma-separated
 */
  public IndexEntry(String wd, List<SermCit> cits){
    word = wd;
    citations = cits;
  }

  /** Constructor given word, sermon, paragraph number for first reference
   * @param wd Phrase to index
   * @param sermon Sermon where reference occurs
   * @param para Paragraph within sermon
   */
  public IndexEntry(String wd, String sermon, int para){
    this(wd, null);   // Initialize citation list to null
    this.add(sermon, para);
  }

/** Constructor given a string containing phrase and one reference
 * @param entry String e.g. "Virgin Mary\t1.3"
 */
  public IndexEntry(String entry){
    citations = new ArrayList<SermCit>();
    word = entry.substring(0, entry.indexOf("\t"));   // Separate entry into phrase and everything else
    entry = entry.substring(entry.lastIndexOf("\t")+1);
    int comm = entry.indexOf(",");    // If there are many citations, loop through to add all
    while (comm != -1){
      citations.add(new SermCit(entry.substring(0, comm)));
      entry = entry.substring(comm + 2);
      comm = entry.indexOf(",");
    }
    citations.add(new SermCit(entry));  // There will be one citation after the last comma; add it
  }

 /** Return phrase
  * @return phrase
  */
  public String getWord(){
    return word;
  }

  /** Return list of citations
   * @return list of citations as SermCit objects
   */
  public List<SermCit> getCits(){
    return citations;
  }

 /** Add a new reference to a preexisting IndexEntry object
  * @param newCit the SermCit citation
  */
  public void add(SermCit newCit){
    if (citations == null){
      citations = new ArrayList<SermCit>();   // Create an empty list
    }
    if (citations.isEmpty()){
      citations.add(newCit);      // If empty list, add the citation
    }
    else {
      int i = 0;    //look for location to add citation
      while (i < citations.size() && citations.get(i).compareTo(newCit)< 0){
        i++;
      }
      //if there's more list to go, add it here
      if (i < citations.size() && citations.get(i).compareTo(newCit) > 0){
        citations.add(i, newCit);
      }
      //if you're at the end of the list, add it to the end of the list
      else if (i == citations.size() && citations.get(i-1).compareTo(newCit) < 0){
        citations.add(newCit);
      }
    }
  }

 /** Add a series of citations as SermCit objects
  * @param citList List of SermCit objects
  */
  public void add(List<SermCit> citList){
    for (SermCit x : citList){
      add(x);
    }
  }

  /** Add a citation in sermon, para form
   * @param sermon Sermon containing reference
   * @param para Paragraph within sermon
   */
  public void add(String sermon, int para){
    SermCit cit = new SermCit(sermon, para);
    this.add(cit);
  }

  /** Compare to another IndexEntry object
   * @param other Other IndexEntry to compare to
   * @return negative for this before other, 0 for equal, positive for this after other
   */
   @Override
  public int compareTo(IndexEntry other){
    return word.compareTo(other.getWord());
  }

/** Turn into string
 * @return IndexEntry in string form, e.g. "Virgin Mary   1.3, 2.7, 2.9"
 */
 @Override
  public String toString(){
    String result = word + "\t";
    for (SermCit cit : citations){
      result += cit.toString() + ", ";
    }
    return result.substring(0, result.length() - 2); //get rid of last ", "
  }
}
