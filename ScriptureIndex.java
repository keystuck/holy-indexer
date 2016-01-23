//ScriptureIndex.java
//Emily Stuckey
//1-21-16
//Index.java adapted for scriptural indices

import java.util.*;
import java.util.regex.*;
import java.io.*;

public class ScriptureIndex extends ArrayList<ScriptureEntry>{



  public ScriptureIndex(){

  }

  public ScriptureIndex(String sermon, String fileName){
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
        //else add the scriptural reference on that line to the index
        else
        {
          ScriptureEntry indEnt = findOrAdd(new ScriptCit(nextL));
          indEnt.add(sermon, para);

        }
        nextL = sermReader.readLine();
      }
    } catch (IOException e){
      System.out.println("IO exception: " + e.getMessage());
    }
  }

  public ScriptureIndex(FileReader f1, FileReader f2){
    this();
    try {
      BufferedReader file1 = new BufferedReader(f1);
      BufferedReader file2 = new BufferedReader(f2);
      String nextL1 = file1.readLine();
      String nextL2 = file2.readLine();
      while (nextL1 != null){
        this.add(new ScriptureEntry(nextL1));
        nextL1 = file1.readLine();
      }
      while (nextL2 != null){
        this.add(new ScriptureEntry(nextL2));
        nextL2 = file2.readLine();
      }
    } catch (IOException e){
      System.out.println("problem in constructing merged index: " + e.getMessage());
    }
  }



  public ScriptureEntry findOrAdd(ScriptCit newEnt){

    if (this.size() == 0){
      add(new ScriptureEntry(newEnt, null));
      return this.get(0);
    }
    int i = 0;
    boolean found = false;
    ScriptureEntry cur = this.get(i);
    while (i < this.size() && !found){
      if (cur.getScript().compareTo(newEnt)==0){
        found = true;
      }
      else if (cur.getScript().compareTo(newEnt) < 0){
          i++;
          if (i < this.size()){
            cur = this.get(i);
          }
      }
      else {
        this.add(i, new ScriptureEntry(newEnt, null));
        cur = this.get(i);
        found = true;
      }
    }
    if (i == this.size()){
      this.add(i, new ScriptureEntry(newEnt, null));
      cur = this.get(i);
    }
    return cur;
  }

  public boolean add(ScriptureEntry newEnt){
    if (this.isEmpty()){
      return super.add(newEnt);
    }
    else {
      ScriptureEntry x = findOrAdd(newEnt.getScript());
      x.add(newEnt.getCits());
      return true;
    }
  }


  public String toString(){
    String result = "";
    String ender = "";
    for (ScriptureEntry blah : this){

      result += ender + blah.toString();
        ender = "\n";
    }
    return result;
  }

}
