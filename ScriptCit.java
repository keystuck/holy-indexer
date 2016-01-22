// ScriptCit for Scriptural Citations
//Adapted from SermCit
//Emily Stuckey
//1-6-16
//redefined 1-21-16
//doesn't need info about sermon - just about bible citation

import java.util.*;

public class ScriptCit {
  private String book;
  private int chapter;
  private String verse;


  private List<String> Books = Arrays.asList("Gen", "Exod", "Lev", "Num", "Deut",
  "Josh", "Judg", "Ruth", "1 Sam", "2 Sam", "1 Kgs", "2 Kgs", "1 Chr", "2 Chr",
  "Ezra", "Neh", "Esth", "Job", "Ps", "Prov", "Eccl", "Song",
  "Isa", "Jer", "Lam", "Ezek", "Dan", "Hos", "Joel", "Amos", "Obad", "Jonah",
  "Mic", "Nah", "Hab", "Zeph", "Hag", "Zech", "Mal", "Tob", "Jdt", "Add Esth",
  "Wis", "Sir", "Bar", "1 Esdr", "2 Esdr", "Ep Jer", "Pr Azar", "Sus", "Bel",
  "1 Macc", "2 Macc", "3 Macc", "4 Macc", "Pr Man", "Matt", "Mark", "Luke", "John",
  "Acts", "Rom", "1 Cor", "2 Cor", "Gal", "Eph", "Phil", "Col", "1 Thess", "2 Thess",
  "1 Tim", "2 Tim", "Titus", "Phlm", "Heb", "Jas", "1 Pet", "2 Pet", "1 John",
  "2 John", "3 John", "Jude", "Rev");


  public ScriptCit(String altogether){
    int space = altogether.indexOf(" ");
    int colon = altogether.indexOf(":");
    if (space == -1 || colon == -1 || colon == altogether.length() - 1){
      System.out.println("Problem in ScriptCit constructor");
    }
    else {
      book = altogether.substring(0, space);
      chapter = Integer.parseInt(altogether.substring(space + 1, colon));
      verse = altogether.substring(colon + 1);
    }
  }

  public ScriptCit(String bk, int bChap, String bPara){
    book = bk;
    chapter = bChap;
    verse = bPara;
  }

  public String toString(){
    return book + " " + chapter + ":" + verse;
  }

  public int compareTo(ScriptCit other){
    int diff = Integer.compare(Books.indexOf(book), Books.indexOf(other.book));
    if (diff != 0){
      return diff;
    }
    else if (chapter != other.chapter){
      return Integer.compare(chapter, other.chapter);
    }
    int othFirst = -1;
    int othSecond = -1;
    int first = -1;
    int second = -1;
    if (other.verse.indexOf("-") != -1){
      othFirst = Integer.parseInt(other.verse.substring(0, other.verse.indexOf("-")));
      othSecond = Integer.parseInt(other.verse.substring(other.verse.indexOf("-")+1));
    }
    else {
      othFirst = Integer.parseInt(other.verse);
      othSecond = othFirst;
    }
    if (verse.indexOf("-") != -1){
      first = Integer.parseInt(verse.substring(0, verse.indexOf("-")));
      second = Integer.parseInt(verse.substring(verse.indexOf("-")+1));
    }
    else {
      first = Integer.parseInt(verse);
      second = first;
    }
    if (first != othFirst){
      return Integer.compare(first, othFirst);
    }
    else {
      return Integer.compare(second, othSecond);
    }
  }
}
