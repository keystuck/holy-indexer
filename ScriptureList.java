import java.io.*;
import java.util.ArrayList;
import java.util.regex.*;

/** ScriptureList.java
 * scans a document for paragraph numbers
 * and scriptural notations denoted by {}
 * makes a list of them for a human to edit into auto-indexable form
 * Emily Stuckey
 * 2/11/16
*/
public class ScriptureList extends ArrayList<ArrayList<String>>{

  /** Constructor takes a filename and does.... everything
   * @param fName The name of the file to scan for scriptural citations
   */
  public ScriptureList(String fName){
    int para = 0;
    ArrayList<String> current = new ArrayList<String>();
    current.add("0");     //Make the first list with para # 0
    add(current);       //Add the list to the list of lists

    try {
      BufferedReader fRead = new BufferedReader(new FileReader(fName));
      String nextL = fRead.readLine();

      while (nextL != null){
        nextL = nextL.trim();     //Get rid of those pesky tabs

        Pattern p = Pattern.compile("^(\\d+)\\.");

        //look for leading digits.  May be different for different books, e.g.
        //use "^[LXVI]*\\.?(\\d+)\\." for Pope Gregory
        Matcher m = p.matcher(nextL);

        if (m.find()){

          //new paragraph found -- start a new ArrayList
          current = new ArrayList<String>();

          current.add(m.group(1));    //add the paragraph number as the first
                                    //string in the new list

          add(current);             //add the list to the bigger list
        }

        //Now look for scriptural citations in curly brackets -- this may
        //change in other books; [] for Pope Gregory
        String opener = "{";
        String closer = "}";

        //look for beginning of citation
        int openBInd = nextL.indexOf(opener);

        while (openBInd != -1){
          //if you found it, find the end
          int closeBInd = nextL.indexOf(closer);

          if (closeBInd != -1 && closeBInd > openBInd){
            //there's a close brace after the open brace

            current.add(nextL.substring(openBInd+1, closeBInd));
            //add everything between the braces to the current list
          }
          else {
            //the close brace is missing, so just go to end of line
            current.add(nextL.substring(openBInd+1));

            //set closeBInd to end of line
            closeBInd = nextL.length()-2;
          }

          //cut off the citation & enclosing braces & everything before it
          nextL = nextL.substring(closeBInd+1);

          //look for the next citation
          openBInd = nextL.indexOf(opener);
        }

        //done with that paragraph; get the next
        nextL = fRead.readLine();
      }
    } catch (IOException e){
      System.out.println("error in ScriptureList constructor");
    }
  }

/** toString makes a String - each paragraph number and phrase on a separate line
 * @return The whole list of things inside braces, separated by line, marked
* by paragraph number
*/
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
