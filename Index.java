//Index.java
//Emily Stuckey
//1-8-16
//formerly DocumentIndex.java
//extends ArrayList to be a list of IndexEntries

import java.util.*;
import java.util.regex.*;
import java.io.*;

public class Index extends ArrayList<IndexEntry>{
  public Index(){

  }

  public Index(String sermon, String fileName){
    try {
      BufferedReader sermReader = new BufferedReader(new FileReader(fileName));
      int para = 0;
      String nextL = sermReader.readLine();
      while (nextL != null){
        Pattern p = Pattern.compile("^\\d+\\.?$");
        Matcher m = p.matcher(nextL);
        //if the only thing on the line is a digit
        //set the para to that digit and advance

        if (m.matches()){
          para = Integer.parseInt(nextL);
        }
        //else add the phrase on that line to the index
        else
        {
          System.out.println("nextL = " + nextL);
          nextL = nextL.substring(0, 1).toUpperCase() + nextL.substring(1);
          System.out.println("now it's " + nextL);
          IndexEntry indEnt = findOrAdd(nextL);
          indEnt.add(sermon, para);

        }
        nextL = sermReader.readLine();
      }
    } catch (IOException e){
      System.out.println("IO exception: " + e.getMessage());
    }
  }

  public IndexEntry findOrAdd(String newEnt){

    if (this.size() == 0){
      add(new IndexEntry(newEnt, null));
      return this.get(0);
    }
    int i = 0;
    boolean found = false;
    IndexEntry cur = this.get(i);
    while (i < this.size() && !found){
      if (cur.getWord().compareTo(newEnt)==0){
        found = true;
      }
      else if (cur.getWord().compareTo(newEnt) < 0){
          i++;
          if (i < this.size()){
            cur = this.get(i);
          }
      }
      else {
        this.add(i, new IndexEntry(newEnt, null));
        cur = this.get(i);
        found = true;
      }
    }
    if (i == this.size()){
      this.add(i, new IndexEntry(newEnt, null));
      cur = this.get(i);
    }
    return cur;
  }

  public boolean add(IndexEntry newEnt){
    if (this.isEmpty()){
      return super.add(newEnt);
    }
    else {
      findOrAdd(newEnt.getWord());
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
