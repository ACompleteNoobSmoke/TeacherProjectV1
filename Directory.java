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
    static ArrayList<Student> stu = new ArrayList<Student>();

    public void createFolder(){
        file.mkdir();
    }
    
    public boolean searchID(String fileID){
         boolean check = false;
         File[] listoFiles = file.listFiles();
         
         for(File f: listoFiles){
             if(f.getName().equals(fileID)){
                  check = true;
             }
         }
 
         if(check){
             System.err.println("\n!!ID NUMBER ALREADY EXISTS!!\n");
             return false;
         }else{
             return true;
         }
          
    }
    
    public void createFile(Teach teacher) throws IOException{
        String fileName = "Class/" + teacher.getID() + ".txt";
    
        try{
        File newFile = new File(fileName);
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
            test(stringid, id);
        }else{
            System.err.println("ID Not Recognized");
        
    }
  }

  public void test(String fileName, int tid){
      Teach teacher = new Teach();
      String newName = "Class/" + fileName;
      String t_id = Integer.toString(tid);
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
          if(res[0].contains(t_id)){
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
          break;
        }
      }

      menu.menu(teacher);
  }

  public void enterNames(Teach teacher, Student stu2){
      String fileName = "Class/" + Integer.toString(teacher.getID()) + ".txt";
      BufferedWriter buff = null;
      
      String info = "\n" + stu2.id + " " + stu2.fname + " " + stu2.lname + " " + stu2.dob
        + " " + stu2.age + " " + stu2.gender + " " + stu2.year + " " + stu2.status + " " + stu2.hobby  + "\n";


      try{
          buff = new BufferedWriter(new FileWriter(new File(fileName), true));
          buff.write(info);
          System.out.println(stu2.fname +"'s Information Has Been Stored Into The Database!\n");
          buff.close();
      }catch(FileNotFoundException e){
          System.out.println(" FILE DOES NOT EXIST");
      }catch(IOException e){
        e.printStackTrace();
      }
  }

  public String searchStudent(int tid, int sid ){
    Student stud = null;
    String newName = "Class/" + Integer.toString(tid) + ".txt";
    String s_id = Integer.toString(sid);
    File _file = new File(newName);
    List <String> list = new ArrayList<String>();
    boolean exist = false;
    if(file.exists()){
        try{

          list = Files.readAllLines(_file.toPath(), Charset.defaultCharset());

        }catch(IOException e){
          e.printStackTrace();
        }
        if(list.isEmpty()){
            return "ERROR!";
        }
    }

    for(String line: list){
        String res[] = line.split("\\s+");
        String gen = "";
        if(res[0].equals(s_id)){
        int id = Integer.parseInt(res[0]);
        int age = Integer.parseInt(res[4]);
        
        if(res[5].startsWith("M") || res[5].startsWith("m"))
        {
            gen = "Male";
        }else{
            gen = "Female";
        }
        
        stud = new Student(res[1], res[2], id, res[3], age, res[6], gen, res[7], res[8]);
        exist = true;
        break;
      }
    }

    if(exist){
    return stud.toString();
    }else{
      return "STUDENT INFORMATION DOES NOT EXIST!";
    }

  }
}