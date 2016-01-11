//Index.java
//Emily Stuckey
//1-8-16
//formerly DocumentIndex.java
//extends ArrayList to be a list of IndexEntries

import java.util.*;

public class Index extends ArrayList<IndexEntry>{

  public IndexEntry findOrAdd(IndexEntry newEnt){
    int i = 0;
    boolean found = false;
    IndexEntry cur = this.get(i);
    while (i < this.size() && !found){

      if (cur.getWord().compareTo(newEnt.getWord())==0){
        found = true;
        cur.add(newEnt.getCits());
      }
      else if (cur.compareTo(newEnt) < 0){
          i++;
          if (i < this.size()){
            this.get(i);
          }
      }
      else {
        this.add(i, newEnt);
        cur = newEnt;
        found = true;
      }
    }
    if (i == this.size()){
      this.add(i, newEnt);
      cur = newEnt;
    }
    return cur;
  }

  public boolean add(IndexEntry newEnt){
    if (this.isEmpty()){
      return super.add(newEnt);
    }
    else {
      findOrAdd(newEnt);
      return true;
    }
  }

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
