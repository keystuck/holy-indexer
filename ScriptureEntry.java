//IndexEntry
//represents the index entry for a single word/phrase
//example:    Holy Spirit     1.2, 1.5, 4.7
//1-21-16: redo with ScriptCit

import java.util.*;

public class ScriptureEntry{

  private ScriptCit chapVerse;
  private List<SermCit> citations;
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

  public ScriptureEntry(ScriptCit cv){
    chapVerse = cv;
    citations = null;
  }

  public ScriptureEntry(ScriptCit cv, List<SermCit> cits){
    chapVerse = cv;
    citations = cits;
  }

  public ScriptureEntry(String bk, int chap, String vrs, List<SermCit> cits){
    this(new ScriptCit(bk, chap, vrs), cits);
  }

  public ScriptureEntry(String bk, int chap, String vrs){
    this(new ScriptCit(bk, chap, vrs), null);
  }

  public ScriptureEntry(String bk, int chap, String vrs, String sermon, int para){
    this(new ScriptCit(bk, chap, vrs), null);
    this.add(sermon, para);
  }

  public ScriptureEntry(String altogether){
    chapVerse = new ScriptCit(altogether.substring(0, altogether.indexOf("\t")));
    altogether = altogether.substring(altogether.lastIndexOf("\t")+1);
    int comm = altogether.indexOf(",");
    citations = new ArrayList<SermCit>();
    while (comm != -1){
      citations.add(new SermCit(altogether.substring(0, comm)));
      altogether = altogether.substring(comm+2);
      comm = altogether.indexOf(",");
    }
    citations.add(new SermCit(altogether));
  }

  public ScriptCit getScript(){
    return chapVerse;
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
      else {
        System.out.println("In what should be unreachable in ScriptureEntry");
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

  public int compareTo(ScriptureEntry other){
    return chapVerse.compareTo(other.chapVerse);
  }

  public String toString(){
    String result = chapVerse.toString() + "\t\t";
    String comm = "";
    for (SermCit blah : citations){
      result += comm + blah;
      comm = ", ";
    }
    return result;
  }
}
