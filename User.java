import java.io.Serializable;
import java.util.ArrayList;
public class User implements User_interface,Serializable{
    public static int userNum =0;
    private int ID;
    private String username;
    private String password;

    public User(String username, String password){
        userNum ++;
        this.ID = userNum;
        this.username = username;
        this.password = password;
    }

    public int sendPackage(MailingServiceSystem mss, int receiver_id) {
        int track_id = mss.add_package(this.ID, receiver_id);
        return track_id;
    }

    public ArrayList<Package> checkSendingPackage(MailingServiceSystem mss){
        return mss.searchByUserSender(ID);
    }

    public ArrayList<Package> checkReceivingPackage(MailingServiceSystem mss){
        return mss.searchByUserReceiver(ID);
    }
    
    public int getID(){
        return this.ID;
    }

    public User getUser(){
        return this;
    }

    public void setID(int id){
        this.ID = id;
    }

    public String getUsername(){
        return this.username;
    }

    public void setUsername(String username){
        this.username = username;
    }

    public String getPassword(){
        return password;
    }
    public void setPassword(String password){
        this.password = password;
    }

}