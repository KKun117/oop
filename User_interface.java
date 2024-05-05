import java.util.ArrayList;
public interface User_interface {
    public int sendPackage(MailingServiceSystem mss, int receiver_id);
    public ArrayList<Package> checkSendingPackage(MailingServiceSystem mss);
    public ArrayList<Package> checkReceivingPackage(MailingServiceSystem mss);
    public int getID();
    public void setID(int id);
    public String getUsername();
    public void setUsername(String username);
    public String getPassword();
    public void setPassword(String password);

}