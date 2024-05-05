public interface PackageInterface{
    // Define a class to represent a package
    
    // Method to display the tracking message of the status and ID
    public  void track();

    // Update package status
    public  void updateStatus(String status);

    public int getId();

    public String getStatus();


}