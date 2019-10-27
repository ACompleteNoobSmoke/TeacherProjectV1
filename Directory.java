import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

public class Directory{
    static File file = new File("Class");
    static Menu menu = new Menu();
    
    
    public void createFile(Teach teacher) throws IOException{
        String fileName = "Class/" + teacher.getID() + ".txt";
        try{
        
        File newFile = new File(fileName);
        file.mkdir();
        newFile.createNewFile();
        }catch(IOException e){
            System.out.println("File Not Created");
        }

        PrintWriter print = new PrintWriter(fileName);
        print.println(teacher.getID() + " " + teacher.getFirst() + " " + teacher.getLast() + " " + teacher.getGenderString());
        print.close();
        
        menu.message(teacher);    
    }

    public void searchFiles(int id){
        String stringid =Integer.toString(id);
        stringid += ".txt";
         boolean check = false;
        File[] listoFiles = file.listFiles();
        
        for(File f: listoFiles){
            if(f.getName().equals(stringid)){
                 check = true;
            }
        }

        if(check){
             System.err.print("\nLogging In...\n\n");
            test(stringid);
        }else{
            System.err.println("ID Not Recognized");
        
    }
  }

  public void test(String fileName){
      Teach teacher = new Teach();
      String newName = "Class/" + fileName;
      File _file = new File(newName);
      List <String> list = new ArrayList<String>();
      if(file.exists()){
          try{

            list = Files.readAllLines(_file.toPath(), Charset.defaultCharset());

          }catch(IOException e){
            e.printStackTrace();
          }
          if(list.isEmpty()){
              return;
          }
      }

      for(String line: list){
          String res[] = line.split("\\s+");
          int id = Integer.parseInt(res[0]);
          teacher.setID(id);
          teacher.setFirst(res[1]);
          teacher.setLast(res[2]);
          teacher.setGenderString(res[3]);
          if(res[3].equalsIgnoreCase("Male")){
              teacher.setGender(true);
          }else{
              teacher.setGender(false);
          }
      }

      menu.menu(teacher);
  }

  public void enterNames(int id){
      String fileName = "Class/" + Integer.toString(id) + ".txt";
      BufferedWriter buff = null;
      String info = 9111 + " " + "African" + " " + "King" + " " + "GOD";

      System.out.println(info);
     
      try{
          buff = new BufferedWriter(new FileWriter(new File(fileName), true));
          buff.write(info);
          System.out.println("I EAT ASS");
          buff.close();
      }catch(FileNotFoundException e){
          System.out.println(" FILE DOES NOT EXIST");
      }catch(IOException e){
        e.printStackTrace();
      }
  }
}