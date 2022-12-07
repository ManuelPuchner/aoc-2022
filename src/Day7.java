import util.FileUtils;

import java.util.ArrayList;
import java.util.List;

public class Day7 extends Day {

    class File {
        String name;
        int size;
        Directory parent;

        public File(String name, int size, Directory parent) {
            this.name = name;
            this.size = size;
            this.parent = parent;
        }
    }

    class Directory {
        String name;
        int size;
        List<File> files = new ArrayList<>();
        List<Directory> directories = new ArrayList<>();
        Directory parent;

        public Directory(String name) {
            this.name = name;
        }
    }

    List<Directory> allDirectories = new ArrayList<>();
    List<File> allFiles = new ArrayList<>();

    private final Directory rootDir;

    private Directory parseInput() {
        Directory root = new Directory("/");
        Directory currentDirectory = root;
        String currentCommand = "";
        for(String line : input) {
           String[] parts = line.split(" ");
           boolean isCommand = parts[0].equals("$");
           if (isCommand) {
               currentCommand = parts[1];
           }
           if (currentCommand.equals("ls")) {
                if (isCommand) {
                     continue;
                }
                if (parts[0].equals("dir")) {
                     Directory directory = new Directory(parts[1]);
                     directory.parent = currentDirectory;
                     currentDirectory.directories.add(directory);
                     allDirectories.add(directory);
                } else {
                     File file = new File(parts[1], Integer.parseInt(parts[0]), currentDirectory);
                     file.parent = currentDirectory;
                     currentDirectory.files.add(file);
                     allFiles.add(file);
                }
           } else if (currentCommand.equals("cd")) {
                if (!parts[2].equals("/")) {
                    String dirName = parts[2];
                    if (dirName.equals("..")) {
                        currentDirectory = currentDirectory.parent;
                    } else {
                        for (Directory directory : currentDirectory.directories) {
                            if (directory.name.equals(dirName)) {
                                currentDirectory = directory;
                                break;
                            }
                        }
                    }
                }
           }
        }
        allDirectories.add(root);
        return root;
    }

    private int getDirectorySize(Directory directory) {
        int size = 0;
        for (File file : directory.files) {
            size += file.size;
        }
        for (Directory dir : directory.directories) {
            size += getDirectorySize(dir);
        }
        return size;
    }

    public Day7() {
        super();
        //input = FileUtils.readAllLines("input/7/example");
        rootDir = parseInput();
        for(Directory directory : allDirectories) {
            directory.size = getDirectorySize(directory);
        }
    }

    @Override
    public Object solvePart1() {
        int sum = 0;
        for (Directory directory : allDirectories) {
            if (directory.size < 100000) {
                sum += directory.size;
            }
        }
        return sum;
    }

    private static final int FS_SIZE = 70000000;
    private static final int NEEDED_SPACE = 30000000;

    List<Directory> deletableDirectories = new ArrayList<>();

    @Override
    public Object solvePart2() {
        int unusedSpace = FS_SIZE - rootDir.size;
        for (Directory directory : allDirectories) {
            if (directory.size > NEEDED_SPACE-unusedSpace) {
                deletableDirectories.add(directory);
            }
        }
        Directory smallestDeletableDirectory = null;
        for (Directory directory : deletableDirectories) {
            if (smallestDeletableDirectory == null || directory.size < smallestDeletableDirectory.size) {
                smallestDeletableDirectory = directory;
            }
        }
        if (smallestDeletableDirectory != null) {
            return smallestDeletableDirectory.size;
        }
        return 0;
    }
}
