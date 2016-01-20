//IndexMaker
//main class for SermonIndexer project
//handles i/o
//Emily Stuckey
//1-10-16
import java.util.Scanner;
import java.io.*;


public class IndexMaker {
  public IndexMaker(){
    Scanner input = new Scanner(System.in);
    System.out.print("Enter 1 to create a new Sermon index, 2 to create a new Scriptural index, 3 to merge indices, anything else to exit: ");
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
      //creating new scriptural index (automatically?)
    }
    else if (whatWant == 3){
      //merging indices
    }
    else {
      System.out.println("Goodbye.");
    }
  }



}
