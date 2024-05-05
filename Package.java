// Define a class to represent a package

import java.io.Serializable;

class Package implements PackageInterface,Serializable {
    public static int numPackages = 0;
    private int id;
    private int senderID;
    private int receiverID;
    //status can be in three state: created, on its way, delivered
    private String status;


    public Package(int sender, int receiver) {
        numPackages++;
        this.id = numPackages;
        this.senderID = sender;
        this.receiverID = receiver;
        this.status = "Created";
    }

    // Method to track the package
    public void track() {
        System.out.println("Tracking package: " + id + " Status: " + status);
    }

    // Update package status
    public void updateStatus(String status) {
        this.status = status;
    }
    public int getId(){
        return this.id;
    }

    public String getStatus(){
        return status;
    }

    public int getSenderId(){
        return this.senderID;
    }

    public int getReceiverId(){
        return this.receiverID;
    }
}

