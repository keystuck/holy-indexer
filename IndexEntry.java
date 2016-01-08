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

  public void add(String sermon, int para){
    SermCit cit = new SermCit(sermon, para);
    this.add(cit);
  }

  public String toString(){
    String result = word + "\t";
    for (SermCit cit : citations){
      result += cit.toString() + ", ";
    }
    return result.substring(0, result.length() - 2); //get rid of last ", "
  }
}
