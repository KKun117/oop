import java.util.ArrayList;

public interface MailingServiceInterface {

    public int add_package(int senderID, int receiverID);

    public Package searchByID(int package_id);

    public ArrayList<Package> searchByUserSender(int user_id);

    public ArrayList<Package> searchByUserReceiver(int user_id);

    //check the status of a package by its id
    public void checkStatus(int package_id);
    
}
