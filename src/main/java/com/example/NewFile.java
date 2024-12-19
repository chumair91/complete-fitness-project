package com.example;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

import javafx.scene.Scene;

public class NewFile {
    File file;

    public NewFile() {
         File dir=new File("C:\\Users\\DELL\\Videos\\table_tut2\\src\\main\\resources\\com\\example");
         if (!(dir.exists())) {
            dir.mkdirs();
         }
        file = new File(dir, "Data.txt");
        try {
            file.createNewFile();
        } catch (Exception j) {

            System.out.println(j.getMessage());
        }

    }

    public ArrayList<UserData> readFile() {
        ArrayList<UserData> newUser = new ArrayList<>();
        try {
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] parts = line.split("~");
                if (parts.length == 5) {

                    newUser.add(new UserData(parts[0], parts[1], parts[2], parts[3], parts[4]));
                }

            }
        } catch (FileNotFoundException e) {

            e.printStackTrace();
        }
        return newUser;
    }

    public boolean checkUser(String email, String school, String movie) {
        try (Scanner scanner = new Scanner(file)) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] parts = line.split("~");

                if (parts.length == 5) {
                    String fileUserEmail = parts[2];
                    String fileSchool = parts[3];
                    String fileMovie = parts[4];

                    if (fileUserEmail.equals(email) && fileSchool.equalsIgnoreCase(school)
                            && fileMovie.equalsIgnoreCase(movie)) {
                        return true;
                    }
                }
            }
        } catch (FileNotFoundException e) {

            e.printStackTrace();
        }
        return false;
    }

    public void updataPassword(String email, String newPassword) {
        ArrayList<UserData> userArrayList = readFile();
        boolean check = false;
        for (UserData newUser : userArrayList) {
            if (newUser.getUserEmail().equals(email)) {
                newUser.setUserPassword(newPassword);
                check = true;
            }
        }
        if (check) {
            try (FileWriter fileWriter = new FileWriter(file, false)) {
                for (UserData userData : userArrayList) {
                    fileWriter.write(
                            userData.getUserName() + "~" + userData.getUserPassword() + "~" + userData.getUserEmail()
                                    + "~" + userData.getSchool() + "~" + userData.getMovie() + "\n");

                }

                fileWriter.close();
            } catch (IOException e) {
                
                e.printStackTrace();
            }

        }
      
    }

    public void newFileWriter(ArrayList<UserData> user) {
        try (FileWriter fileWriter = new FileWriter(file, true)) {
            for (UserData userData : user) {
                try {

                    fileWriter.write(
                            userData.getUserName() + "~" + userData.getUserPassword() + "~" + userData.getUserEmail()
                                    + "~" + userData.getSchool() + "~" + userData.getMovie() + "\n");

                } catch (IOException e) {
                    System.out.println(e.getMessage());
                    e.printStackTrace();
                }
            }
            fileWriter.close();
            user.clear();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public boolean checkInFile(String name, String pass) throws Exception {

        Scanner scanner = new Scanner(file);

        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            String[] parts = line.split("~");

            if (parts.length == 5) {
                String fileUsername = parts[0];
                String fileId = parts[1];

                if (fileUsername.equalsIgnoreCase(name) && fileId.equals(pass)) {
                    return true;
                }
            }
        }

        return false;
    }

    public boolean authenticate(String email) {
        try (Scanner scanner = new Scanner(file)) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String parts[] = line.split("~");
                if (parts.length == 5) {
                    String newEmail = parts[2];
                    if (newEmail.equals(email)) {
                        return true;
                    }
                }
            }
        } catch (FileNotFoundException e) {

            e.printStackTrace();
        }
        return false;
    }
}
