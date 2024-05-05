import java.util.ArrayList;
import java.util.Scanner;
import java.io.*;
public class Main {
    public static Scanner scanner = new Scanner(System.in);
    public static void main(String args[]){
        ArrayList<Package> pkg_list = new ArrayList<>();
        ArrayList<User> user_list = new ArrayList<>();
        ArrayList<Worker> work_list = new ArrayList<>();
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream("mailingData.ser"))) {
            user_list = (ArrayList<User>) in.readObject();
            work_list = (ArrayList<Worker>) in.readObject();
            pkg_list = (ArrayList<Package>) in.readObject();
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error occurred while loading data: " + e.getMessage());
            // If the data file does not exist => Initialize empty lists 
            user_list = new ArrayList<>();
            work_list = new ArrayList<>();
            pkg_list = new ArrayList<>();
        }
        Package.numPackages = pkg_list.size();
        User.userNum = user_list.size();
        Worker.workerNum = work_list.size();


    



        
        MailingServiceSystem mss = new MailingServiceSystem(pkg_list);
        
        outer:while(true){
            System.out.println("1.Register");
            System.out.println("2.User Login");
            System.out.println("3.Worker Login");
            System.out.println("4.Quit");
            int choice = scanner.nextInt();
            scanner.nextLine();
            if(choice ==1){
                user_register_GUI(user_list);

            }else if(choice==2){
                User user = user_login_GUI(user_list);
                if(user!=null){
                    System.out.println("Hello, User "+user.getUsername());
                    inner:while(true){
                        System.out.println("1.Send Package");
                        System.out.println("2.Package Received/to be Received");
                        System.out.println("3.Package Sent");
                        System.out.println("4.Check Your ID");
                        System.out.println("5.Quit");
                        int choice2 = scanner.nextInt();
                        scanner.nextLine();
                        if(choice2==1){
                            System.out.println("Please enter receiver's ID");
                            int receiverId = scanner.nextInt();
                            scanner.nextLine();
                            user.sendPackage(mss, receiverId);
                            System.out.println("Sent successfully");
                        }else if(choice2==2){
                            mss.show_pkg_list(user.checkReceivingPackage(mss));
                        }else if(choice2==3){
                            mss.show_pkg_list(user.checkSendingPackage(mss));
                        }else if(choice2==4){
                            System.out.println(user.getID());
                        }else if(choice2==5){
                            break outer;
                        }else{
                            System.out.println("Invalid Input");
                        }
                    }
                }else{
                    System.out.println("Wrong username/password");
                    continue;
                }
            }else if(choice ==3){
                Worker worker = worker_login_GUI(work_list);
                if(worker!=null){
                    System.out.println("Hello, Admin "+worker.getUsername());
                    while(true){
                        System.out.println("1.Update Package Status");
                        System.out.println("2.Create New Admin Account");
                        System.out.println("3.Exit");
                        int choice3 = scanner.nextInt();
                        scanner.nextLine();
                        if(choice3==1){
                            System.out.println("What is Tracking ID?");
                            int id = scanner.nextInt();
                            Package newpkg = null;
                            scanner.nextLine();
                            for(Package pkg:pkg_list){
                                if(pkg.getId()==id){
                                    newpkg = pkg;
                                }
                            }
                            if(newpkg!=null){
                                System.out.println("What is new status?");
                                newpkg.updateStatus(scanner.nextLine());
                                System.out.println("Status set to "+newpkg.getStatus());
                            }else{
                                System.out.println("Package does not exsit");
                            }
                        }else if(choice3==2){
                            System.out.println("What is new user name for admin?");
                            String username = scanner.nextLine();
                            System.out.println("What is new user password for admin?");
                            String password = scanner.nextLine();
                            Worker newworker = new Worker(username,password);
                            work_list.add(newworker);
                            System.out.println("Admin added successfully");
                        }else if(choice3==3){
                            break outer;
                        }else{
                            System.out.println("Invalid Input");
                        }
                    }
                }else{
                    System.out.println("Wrong username/password");
                    continue;
                }
            }else if(choice==4){
                break;
            }else{
                System.out.println("Invalid Input");
            }
        }


        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("mailingData.ser"))) {
            out.writeObject(user_list);
            out.writeObject(work_list);
            out.writeObject(pkg_list);
        } catch (IOException e) {
            System.out.println("Error occurred while saving data: " + e.getMessage());
        }

    }

    public static void userRegister(ArrayList <User>user_list, String username, String password){
        user_list.add(new User(username, password));
    }
    public static User userLogin(ArrayList <User> user_list, String username, String password){
        for(User user: user_list){
            if(user.getUsername().equals(username)&&user.getPassword().equals(password)){
                return user;
            }
        }
        return null;
    }
    
    public static User user_login_GUI(ArrayList <User>user_list){
        System.out.println("Input your username");
        String username = scanner.nextLine();
        System.out.println("Input your password");
        String password = scanner.nextLine();
        User user = userLogin(user_list, username, password);
        return user;
    }

    public static void user_register_GUI(ArrayList <User> user_list){
        System.out.println("Input your username");
        String username = scanner.nextLine();
        System.out.println("Input your password");
        String password = scanner.nextLine();
        userRegister(user_list, username, password);
        System.out.println("Register successfully");
    }
    public static Worker worker_login_GUI(ArrayList <Worker>work_list){
        System.out.println("Input your username");
        String username = scanner.nextLine();
        System.out.println("Input your password");
        String password = scanner.nextLine();
        Worker worker = workerLogin(work_list, username, password);
        if(username.equals("root")&&password.equals("root")){
            Worker rootuser = new Worker();
            return rootuser;
        }
        return worker;
    }
    public static Worker workerLogin(ArrayList <Worker> work_list,String username,String password){
        for(Worker worker: work_list){
            if(worker.getUsername().equals(username)&&worker.getPassword().equals(password)){
                return worker;
            }
        }
        return null;
    } 






    

    
}
