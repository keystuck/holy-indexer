//IndexMaker
//main class for SermonIndexer project
//handles i/o
//Emily Stuckey
//1-10-16
import java.util.Scanner;
import java.io.*;
import java.util.ArrayList;


public class IndexMaker {
  public IndexMaker(){
    Scanner input = new Scanner(System.in);
    System.out.print("Enter 1 to create a new Sermon index, 2 to create a new ");
    System.out.print("scriptural index, 3 to merge indices, 4 to merge Scriptural ");
    System.out.print("indices, 5 to search a sermon for Scriptural indices ");
    System.out.print("(and make a scriptural index), anything else to exit: ");
    int whatWant = input.nextInt();
    if (whatWant == 1){
      boolean done = false;
notDone:      while (!done){
        //creating new index
        System.out.print("Enter number/title of sermon or EXIT to exit: ");
        String serm = input.next();
        if (serm.equalsIgnoreCase("exit")){
          done = true;
          break notDone;
        }
        System.out.print("Enter name of file (remember extension): ");
        String fName = input.next();
        if (fName.equalsIgnoreCase("exit")){
          done = true;
          break notDone;
        }
        try {
          //Open the given file
          Index sermIndex = new Index(serm, fName);
          //create a new DocumentIndex from the file
          BufferedWriter sermWriter = new BufferedWriter(new FileWriter(serm + "-index.txt"));
          //Create an output file for that sermon: e.g. "1-index.txt"
          //write the DocumentIndex to the output file

          sermWriter.write(sermIndex.toString());
          sermWriter.flush();
          sermWriter.close();
        }
        catch (IOException e){
          System.out.println("Error in IndexMaker: " + e.getMessage());
        }
    }
    }
    else if (whatWant == 2){
      boolean done = false;
notDone2: while (!done){
      System.out.print("Enter number/title of sermon or EXIT to exit: ");
      String serm = input.next();
      if (serm.equalsIgnoreCase("exit")){
        done = true;
        break notDone2;
      }
      System.out.print("Enter name of file (remember extension): ");
      String fName = input.next();
      if (fName.equalsIgnoreCase("exit")){
        done = true;
        break notDone2;
      }
      try {
        //Open the given file
        ScriptureIndex scriptIndex = new ScriptureIndex(serm, fName);
        //create a new DocumentIndex from the file
        BufferedWriter scriptWriter = new BufferedWriter(new FileWriter(serm + "-scr-index.txt"));
        //Create an output file for that sermon: e.g. "1-index.txt"
        //write the DocumentIndex to the output file

        scriptWriter.write(scriptIndex.toString());
        scriptWriter.flush();
        scriptWriter.close();
      }
      catch (IOException e){
        System.out.println("Error in IndexMaker: " + e.getMessage());
      }
    }
  }
    else if (whatWant == 3){
      //merging indices
      boolean done = false;
      ArrayList<String> filesToMerge = new ArrayList<String>();
      boolean moreFiles = true;
notDone3: while (!done){
  filesToGo: while (moreFiles){

        System.out.print("Enter name of next file to merge (remember extension) or DONE: ");
        String fName2 = input.next();
        if (fName2.equalsIgnoreCase("exit")){
          done = true;
          break notDone3;
        }
        if (fName2.equalsIgnoreCase("done")){
          moreFiles = false;
          break filesToGo;
        }
        filesToMerge.add(fName2);
      }
      System.out.print("Enter name of destination (remember extension): ");
      String fName3 = input.next();
      if (fName3.equalsIgnoreCase("exit")){
        done = true;
        break notDone3;
      }
      if (filesToMerge.size() < 2){
        System.out.println("Not enough files!");
        done = true;
        break notDone3;
      }
      try {
        String file1 = filesToMerge.remove(0);
        Index ind1 = new Index(new FileReader(file1));
        Index compIndex = new Index();
        while (filesToMerge.size() != 0){

          //Open the given files
          Index ind2 = new Index(new FileReader(filesToMerge.remove(0)));
          compIndex = new Index(ind1, ind2);
          //create a new DocumentIndex from the file
          ind1 = compIndex;
        }

        BufferedWriter compWriter = new BufferedWriter(new FileWriter(fName3));
        //Create an output file for that sermon: e.g. "1-index.txt"
        //write the DocumentIndex to the output file

        compWriter.write(compIndex.toString());
        compWriter.flush();
        compWriter.close();
      }
      catch (IOException e){
        System.out.println("Error in IndexMaker: " + e.getMessage());
      }
    }
  }
  else if (whatWant == 4){
    //merging indices
    boolean done = false;
    ArrayList<String> filesToMerge = new ArrayList<String>();
    boolean moreFiles = true;
notDone4: while (!done){
filesToGo: while (moreFiles){

      System.out.print("Enter name of next file to merge (remember extension) or DONE: ");
      String fName2 = input.next();
      if (fName2.equalsIgnoreCase("exit")){
        done = true;
        break notDone4;
      }
      if (fName2.equalsIgnoreCase("done")){
        moreFiles = false;
        break filesToGo;
      }
      filesToMerge.add(fName2);
    }
    System.out.print("Enter name of destination (remember extension): ");
    String fName3 = input.next();
    if (fName3.equalsIgnoreCase("exit")){
      done = true;
      break notDone4;
    }
    if (filesToMerge.size() < 2){
      System.out.println("Not enough files!");
      done = true;
      break notDone4;
    }
    try {
      String file1 = filesToMerge.remove(0);
      ScriptureIndex ind1 = new ScriptureIndex(new FileReader(file1));
      ScriptureIndex compIndex = new ScriptureIndex();
      while (filesToMerge.size() != 0){

        //Open the given files
        ScriptureIndex ind2 = new ScriptureIndex(new FileReader(filesToMerge.remove(0)));
        compIndex = new ScriptureIndex(ind1, ind2);
        //create a new DocumentIndex from the file
        ind1 = compIndex;
      }

      BufferedWriter compWriter = new BufferedWriter(new FileWriter(fName3));
      //Create an output file for that sermon: e.g. "1-index.txt"
      //write the DocumentIndex to the output file

      compWriter.write(compIndex.toString());
      compWriter.flush();
      compWriter.close();
    }
    catch (IOException e){
      System.out.println("Error in IndexMaker: " + e.getMessage());
    }
  }
}
    else if (whatWant == 5){
      boolean done = false;
notDone5:      while (!done){
/*      System.out.print("Enter number/title of sermon or EXIT to exit: ");
      String serm = input.next();
      if (serm.equalsIgnoreCase("exit")){
        done = true;
        break notDone5;
      }*/                 //probably not necessary for this
      System.out.print("Enter name of file (remember extension): ");
      String fName = input.next();
      if (fName.equalsIgnoreCase("exit")){
        done = true;
        break notDone5;
      }
      try {
        //Open the given file
        //create an index by scanning for the scriptural references
        ScriptureList scriptList = new ScriptureList(fName);
    //create a new ScriptureList from the file
    int extInd = fName.indexOf(".txt");
    if (extInd != -1){
      fName = fName.substring(0, extInd);
    }
        BufferedWriter listWriter = new BufferedWriter(new FileWriter(fName + "-scripture-list.txt"));
    //Create an output file for that file: e.g. "1-scripture-list.txt"
    //write the list to the output file
    //note: thie will still need to be reviewed by hand!

    listWriter.write(scriptList.toString());
    listWriter.flush();
    listWriter.close();
    System.out.println("List made; see " + fName + "-scripture-list.txt");
  }

  catch (IOException e){
    System.out.println("Error in IndexMaker: " + e.getMessage());
  }
}
}


    else {
      System.out.println("Goodbye.");
    }
  }



}
