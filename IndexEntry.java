//IndexEntry
//represents the index entry for a single word/phrase
//example:    Holy Spirit     1.2, 1.5, 4.7

import java.util.*;

public class IndexEntry{
  private String word;
  private List<SermCit> citations;

  public IndexEntry(String wd, List<SermCit> cits){
    word = wd;
    citations = cits;
  }

  public IndexEntry(String wd, String sermon, int para){
    this(wd, null);
    this.add(sermon, para);
  }

//for reading in an indexentry from a file
  public IndexEntry(String entry){
    citations = new ArrayList<SermCit>();
    word = entry.substring(0, entry.indexOf("\t"));
    entry = entry.substring(entry.lastIndexOf("\t")+1);
    int comm = entry.indexOf(",");
    while (comm != -1){
      System.out.println("passing in: " + entry.substring(0, comm));
      citations.add(new SermCit(entry.substring(0, comm)));
      System.out.println("okay: " + entry.substring(0, comm));
      entry = entry.substring(comm + 2);
      comm = entry.indexOf(",");
    }
    citations.add(new SermCit(entry));
  }

  public String getWord(){
    return word;
  }

  public List<SermCit> getCits(){
    return citations;
  }

  public void add(SermCit newCit){
    if (citations == null){
      citations = new ArrayList<SermCit>();
    }
    if (citations.isEmpty()){
      citations.add(newCit);
    }
    else {
      int i = 0;
      while (i < citations.size() && citations.get(i).compareTo(newCit)< 0){
        i++;
      }
      if (i < citations.size() && citations.get(i).compareTo(newCit) > 0){
        citations.add(i, newCit);
      }
      else if (i == citations.size() && citations.get(i-1).compareTo(newCit) < 0){
        citations.add(newCit);
      }
    }
  }

  public void add(List<SermCit> citList){
    for (SermCit x : citList){
      add(x);
    }
  }

  public void add(String sermon, int para){
    SermCit cit = new SermCit(sermon, para);
    this.add(cit);
  }

  public int compareTo(IndexEntry other){
    return word.compareTo(other.getWord());
  }

  public String toString(){
    String result = word + "\t";
    for (SermCit cit : citations){
      result += cit.toString() + ", ";
    }
    return result.substring(0, result.length() - 2); //get rid of last ", "
  }
}
