/*
Spring 2024 CSCI-UA.0470
Final Project 
Subi Hwang, Rena Wang, and Linkun Wan
May 5, 2024
*/

import java.util.ArrayList;

// Main class to run the application
public class MailingServiceSystem implements MailingServiceInterface {
    ArrayList<Package> pkg_list = new ArrayList<>();

    public MailingServiceSystem(ArrayList<Package> pkg_list){
        this.pkg_list = pkg_list;
    }
    public int add_package(int senderID, int receiverID){
        Package new_package = new Package(senderID, receiverID);
        pkg_list.add(new_package);
        return new_package.getId();
    }

    public Package searchByID(int package_id){
        for(Package pkg:pkg_list){
            if(pkg.getId()==package_id){
                return pkg;
            }
        }
        return null;
    }

    public ArrayList<Package> searchByUserSender(int user_id){
        ArrayList<Package> curPackages = new ArrayList<>();
        for(Package pkg:pkg_list){
            if(pkg.getSenderId()== user_id){
                curPackages.add(pkg);
            }
        }
        return curPackages;
    }

    public ArrayList<Package> searchByUserReceiver(int user_id){
        ArrayList<Package> curPackages = new ArrayList<>();
        for(Package pkg:pkg_list){
            if(pkg.getReceiverId()== user_id){
                curPackages.add(pkg);
            }
        }
        return curPackages;
    }
// move from user

    //check the status of a package by its id
    public void checkStatus(int package_id){
        for(Package pkg:pkg_list){
            if(pkg.getId()==package_id){
                pkg.track();
            }
        }
    }

    public void show_pkg_list(ArrayList<Package> pkg_list){
        for(Package pkg:pkg_list){
            System.out.println("Package ID:"+pkg.getId()+", Package Sender ID:"+pkg.getSenderId()+", Package Receiver ID:"+pkg.getReceiverId()+", Package Status:"+pkg.getStatus());
        }
    }

}