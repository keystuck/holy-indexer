import java.io.*;
import java.util.ArrayList;
import java.util.regex.*;

/** ScriptureList.java
 * scans a document for paragraph numbers
 * and scriptural notations denoted by {}
 * makes a list of them for a human to edit into auto-indexable form
*/
public class ScriptureList extends ArrayList<ArrayList<String>>{

  public ScriptureList(String fName){
    int para = 0;
    ArrayList<String> current = new ArrayList<String>();
    current.add("0");
    add(current);
    try {
      BufferedReader fRead = new BufferedReader(new FileReader(fName));
      String nextL = fRead.readLine();
      while (nextL != null){
        nextL = nextL.trim();
        Pattern p = Pattern.compile("^(\\d+)\\.");
        //use "^[LXVI]*\\.?(\\d+)\\." for Pope Gregory
        Matcher m = p.matcher(nextL);
        if (m.find()){
          //new paragraph found -- start a new ArrayList, add the paragraph number,
          //add the list to the bigger list
          current = new ArrayList<String>();
          current.add(m.group(1));
          add(current);
        }
        //Change {} to [] in three places for Pope Gregory
        int openBInd = nextL.indexOf("{");
        while (openBInd != -1){
          int closeBInd = nextL.indexOf("}");
          if (closeBInd != -1 && closeBInd > openBInd){
            current.add(nextL.substring(openBInd+1, closeBInd));
          }
          else {
            current.add(nextL.substring(openBInd+1));
            closeBInd = nextL.length()-2;
          }
          nextL = nextL.substring(closeBInd+1);
          openBInd = nextL.indexOf("{");
        }
        nextL = fRead.readLine();
      }
    } catch (IOException e){
      System.out.println("error in ScriptureList constructor");
    }
  }

  @Override
  public String toString(){
    String result = "";
    for (ArrayList<String> listie : this){
      for (String element : listie){
        result += element + "\n";
      }
    }
    return result.substring(0, result.length()-1);
  }
}
