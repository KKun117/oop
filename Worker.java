import java.io.Serializable;

public class Worker implements Worker_interface,Serializable{
    static int workerNum =0;
    private int Id;
    private String username;
    private String password;

    public Worker(){
        this.username = "root";
        this.password = "root";
    }

    public Worker(String username, String password){
        workerNum ++;
        this.Id = workerNum;
        this.username = username;
        this.password = password;

    }
    public void updatePackage(Package pkg, String newStatus){
        pkg.updateStatus(newStatus);
        System.out.println("Status set to "+pkg.getStatus());
    }

    public int getId(){
        return this.Id;
    }
    public String getUsername(){
        return username;
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