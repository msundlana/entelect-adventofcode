package main.java.com.adventofcode2022;

import main.java.utilities.Utility;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Day7 {

    public static void main(String[] args) {
        System.out.println("Reading input file");
        List<String> inputList = null;
        try {
            String url = "src/main/resources/day7.txt";
            inputList = Utility.readFromInputStream(url);
        } catch (IOException e) {
            e.printStackTrace();
        }
        Directory directory = createDir(inputList);
        System.out.println("Name of current directory " + directory.getName() );
        System.out.println("Number of files " + directory.getFiles().size() + " and number of directories " + directory.getDirectories().size());

    }

    private static Directory createDir(List<String> inputList){
        Directory root = new Directory();
        Directory currentDirectory = root;
        boolean create = false;
        for (String input : inputList) {
            String [] inputArray = input.split(" ");
            if(inputArray[0].equals("$") && inputArray[1].equals("cd")){
                create = false;
                if(inputArray[2].equals("/")){
                    currentDirectory.setName(inputArray[2]);
                    currentDirectory.setDirectories(new ArrayList<>());
                    currentDirectory.setFiles(new ArrayList<>());

                }else if(inputArray[2].equals("..")){
                    currentDirectory = currentDirectory.getParent();
                }else{
                    Optional<Directory> optionalDirectory = currentDirectory.getDirectories().stream().filter(d -> d.getName().equals(inputArray[2])).findFirst();
                    if(optionalDirectory.isPresent()){
                        currentDirectory = optionalDirectory.get();
                    }
                }

            }else if(inputArray[0].equals("$") && inputArray[1].equals("ls")){
                create = true;
            }else if(create){
                if(inputArray[0].equals("dir")){
                    Directory directory = new Directory(inputArray[1]);
                    directory.setDirectories(new ArrayList<>());
                    directory.setFiles(new ArrayList<>());
                    directory.setParent(currentDirectory);

                    currentDirectory.getDirectories().add(directory);
                }else {
                    File file = new File(inputArray[1],Integer.parseInt(inputArray[0]));
                    currentDirectory.getFiles().add(file);
                }
            }

        }
        return root;
    }

    private static void calculateTheAmounts(Directory directory){

    }

}


 class Directory {
    private String name;
    private List <File> files;
    private List <Directory> directories;

    private Directory parent;

    private Integer totalFileSize;

     public Directory() {
     }

     public Directory(String name) {
         this.name = name;
     }

     public Directory(String name, List<File> files, List<Directory> directories, Directory parent) {
         this.name = name;
         this.files = files;
         this.directories = directories;
         this.parent = parent;
     }

     public String getName() {
         return name;
     }

     public void setName(String name) {
         this.name = name;
     }

     public List<File> getFiles() {
         return files;
     }

     public void setFiles(List<File> files) {
         this.files = files;
     }

     public List<Directory> getDirectories() {
         return directories;
     }

     public void setDirectories(List<Directory> directories) {
         this.directories = directories;
     }

     public Directory getParent() {
         return parent;
     }

     public void setParent(Directory parent) {
         this.parent = parent;
     }
 }

class File {
    private String name;
    private Integer size;

    public File() {
    }

    public File(String name, Integer size) {
        this.name = name;
        this.size = size;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }
}

