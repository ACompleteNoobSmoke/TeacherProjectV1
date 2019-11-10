import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
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
    
    //Creates Folder -- GOOD//
    public void createFolder(){
        file.mkdir();
    }

    public ArrayList<Student> getWholeClass(int tid){
      Student stud = null;
      ArrayList<Student> studentsinclass = new ArrayList<Student>();
      String newName = "Class/" + Integer.toString(tid) + ".txt";
      File _file = new File(newName);
      List <String> list = new ArrayList<String>();
     
    if(file.exists()){
        try{

          list = Files.readAllLines(_file.toPath(), Charset.defaultCharset());

        }catch(IOException e){
          e.printStackTrace();
        }
        if(list.isEmpty()){
            return null;
        }
    }

    for(String line: list){
        String res[] = line.split("\\s+");
        String gen = "";
        if(!res[0].equals(Integer.toString(tid) ) && !res[0].equals("")){
        int id = Integer.parseInt(res[0]);
        String f_name = res[1];
        String l_name = res[2];
        String dob = res[3];
        int age = Integer.parseInt(res[4]);
        
        if(res[5].startsWith("M") || res[5].startsWith("m"))
        {
            gen = "Male";
        }else{
            gen = "Female";
        }

        String year =  res[6];
        String status = res[7];
        String description = res[8];
        
        stud = new Student(f_name, l_name, id , dob , age, year, gen, status, description);
        studentsinclass.add(stud);
      }
      }
    

    return studentsinclass;

  }

    
    //Security To Check If Professor Exists In Folder -- GOOD//
    public boolean searchID(String fileID){
         boolean check = false;
         File[] listoFiles = file.listFiles();
         
         for(File f: listoFiles){
             if(f.getName().equals(fileID)){
                  check = true; //If Professor ID already exists then sets boolean to true.
             }
         }
 
         if(check){
             System.err.println("\n!!ID NUMBER ALREADY EXISTS!!\n");
             return false; //Returns false to regTeacher method
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
            menu.mainmenu();
        
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
        + " " + stu2.age + " " + stu2.gender + " " + stu2.year + " " + stu2.status + " " + stu2.description;


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

  public boolean searchStuID(int tid, int sid ){
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
            return false;
        }
    }

    for(String line: list){
        String res[] = line.split("\\s+");
        if(res[0].equals(s_id)){
        exist = true;
        break;
      }
    }

    if(exist){
    System.err.println("\nStudent ID In Use!");
    return false;
    }else{
      return true;
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

  public void searchStudentForDeletion(int tid, int sid ){
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
            return;
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
      System.out.println(stud);
    deleteStudent(tid, stud);
    }

  }

  public void deleteStudent(int tid, Student deleteStu){
    

   
  String lineToRemove = deleteStu.id + " " + deleteStu.fname + " " + deleteStu.lname + " " + deleteStu.dob
  + " " + deleteStu.age + " " + deleteStu.gender + " " + deleteStu.year + " " + deleteStu.status + " " + deleteStu.description;
  String inputFileName = "Class/" + Integer.toString(tid) + ".txt";
String outputFileName = "Class/myTemp.txt";
 
// The traps any possible read/write exceptions which might occur
try {
    File inputFile = new File(inputFileName);
    File outputFile = new File(outputFileName);
    // Open the reader/writer, this ensure that's encapsulated
    // in a try-with-resource block, automatically closing
    // the resources regardless of how the block exists
    try (BufferedReader reader = new BufferedReader(new FileReader(inputFile));
             BufferedWriter writer = new BufferedWriter(new FileWriter(outputFile))) {
        // Read each line from the reader and compare it with
        // with the line to remove and write if required
        String line = null;
        while ((line = reader.readLine()) != null) {
            if (!line.equals(lineToRemove)) {
                writer.write(line);
                writer.newLine();
            }
        }

        writer.close();
        reader.close();
    }

    // This is some magic, because of the compounding try blocks
    // this section will only be called if the above try block
    // exited without throwing an exception, so we're now safe
    // to update the input file

    // If you want two files at the end of his process, don't do
    // this, this assumes you want to update and replace the 
    // original file

    // Delete the original file, you might consider renaming it
    // to some backup file

  
    if (inputFile.delete()) {
        // Rename the output file to the input filey
        System.out.println(deleteStu.fname.concat(" " + deleteStu.lname) + " Has Been Deleted\n");
        if (!outputFile.renameTo(inputFile)) {
            throw new IOException("Could not rename " + outputFileName + " to " + inputFileName);
        }
    } else {
        throw new IOException("Could not delete original input file " + inputFileName);
    }
} catch (IOException ex) {
    // Handle any exceptions
    ex.printStackTrace();
}
  }

  
}