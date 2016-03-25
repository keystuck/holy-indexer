import java.util.*;
import java.util.regex.*;
import java.io.*;

/** Extends ArrayList to be a list of IndexEntry objects
 * Emily Stuckey
 * 1-8-16
 */
public class Index extends ArrayList<IndexEntry>{

  /** No-args constructor doesn't need to do anything */
  public Index(){
  }

  /** Constructor from sermon title and file containing sermon words to be indexed
   * @param sermon Sermon title to use when ordering index
   * @param fileName Name of file containing sermon words with paragraph numbers
   */
  public Index(String sermon, String fileName){

    // Open BufferedReader, start paragraph at zero, read in line
    try {
      BufferedReader sermReader = new BufferedReader(new FileReader(fileName));
      int para = 0;
      String nextL = sermReader.readLine();
      while (nextL != null){

        // If the only thing on the line is a digit, that's the paragraph number
        Pattern p = Pattern.compile("^\\d+\\.?$");
        Matcher m = p.matcher(nextL);
        if (m.matches()){
          para = Integer.parseInt(nextL);
        }

        // Otherwise it's a word or phrase to be indexed in the current paragraph
        else
        {
          // Make sure the first letter is capitalized
          nextL = nextL.substring(0, 1).toUpperCase() + nextL.substring(1);

          // Find the entry, if it already exists, or create an entry for the word or phrase
          IndexEntry indEnt = findOrAdd(nextL);

          // Add the current reference to the word or phrase's entry
          indEnt.add(sermon, para);
        }

        //load the next line
        nextL = sermReader.readLine();
      }
    } catch (IOException e){
      System.out.println("IO exception: " + e.getMessage());
    }
  }

  /** Merge constructor which takes two Index files
   * @param f1 File to merge into
   * @param f2 File to merge from
   */
  public Index(FileReader f1, FileReader f2){
    this();
    try {
      BufferedReader file1 = new BufferedReader(f1);
      BufferedReader file2 = new BufferedReader(f2);
      String nextL1 = file1.readLine();
      String nextL2 = file2.readLine();

      //alphabetizing is dealt with by IndexEntry, so add one whole list, then the other
      while (nextL1 != null){
        this.add(new IndexEntry(nextL1));
        nextL1 = file1.readLine();
      }
      while (nextL2 != null){
        this.add(new IndexEntry(nextL2));
        nextL2 = file2.readLine();
      }
    } catch (IOException e){
      System.out.println("problem in constructing merged index: " + e.getMessage());
    }
  }

  /** Constructor to merge two indices
   * @param ind1 The first Index
   * @param ind2 The second Index
   */
  public Index(Index ind1, Index ind2){
    this();
    for (IndexEntry ent : ind1){
      add(ent);
    }
    for (IndexEntry ent2 : ind2){
      add(ent2);
    }
  }

  /** Constructor to read an index from a file (used for merging files)
   * @param f FileReader object containing index in text form
   */
  public Index(FileReader f){
    this();
    try {
      BufferedReader file1 = new BufferedReader(f);
      String nextL = file1.readLine();
      while (nextL != null){
        add(new IndexEntry(nextL));
        nextL = file1.readLine();
      }
    } catch (IOException e){
      System.out.println("problem creating an index from a file: " + e.getMessage());
    }
  }

  /** Look up the word or phrase to be indexed -
  * if present, return the entry; if not, add a new one and return that
  * @param newEnt The word or phrase to look up
  * @return the IndexEntry for that word or phrase
  */
  public IndexEntry findOrAdd(String newEnt){

    //If the list is empty, create a new object for this entry, add it to the list, return it
    if (this.size() == 0){
      add(new IndexEntry(newEnt, null));
      return this.get(0);
    }

    //otherwise, search
    int i = 0;
    boolean found = false;
    IndexEntry cur = this.get(i);

    //look until you find it or you run out of list
    while (i < this.size() && !found){

      //if found, then we're done!
      if (cur.getWord().compareTo(newEnt)==0){
        found = true;
      }

      //otherwise, if this is before the entry, keep going
      else if (cur.getWord().compareTo(newEnt) < 0){
          i++;
          if (i < this.size()){
            cur = this.get(i);
          }
      }

      //if the current word comes after the entry you're looking for, add it here
      else {
        this.add(i, new IndexEntry(newEnt, null));
        cur = this.get(i);
        found = true;
      }
    } //Either the entry has been found or the end of the list has been reached

    //if you reach the end of the list, add the entry there
    if (i == this.size()){
      this.add(i, new IndexEntry(newEnt, null));
      cur = this.get(i);
    }
    return cur;
  }


  /** Add the entry to the list
  * @param newEnt the IndexEntry -- e.g. "Virgin\t3.7, 2.8"
  * @return true if added correctly
  */
  public boolean add(IndexEntry newEnt){
    //if the list is empty, add the entry
    if (this.isEmpty()){
      return super.add(newEnt);
    }

    //if not, use findOrAdd to find the right place to put it, then add the citation(s) to that entry
    else {
      IndexEntry x = findOrAdd(newEnt.getWord());
      x.add(newEnt.getCits());
      return true;
    }
  }

  /** Return the whole Index as a String object
  * @return The index as a string
  */
  @Override
  public String toString(){
    String result = "";
    String ender = "";
    for (IndexEntry blah : this){

      result += ender + blah.toString();
        ender = "\n";
    }
    return result;
  }

}
