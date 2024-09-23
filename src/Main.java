import model.DirectoryPathProvider;
import service.FinderDirectory;


public class Main {
    public static void main(String[] args) {
        DirectoryPathProvider pathProvider = new DirectoryPathProvider("C:/Users/Edwin Machaieie/Desktop/Important/Mom/Isabel/Download");

        FinderDirectory finderDirectory = new FinderDirectory(pathProvider);
        finderDirectory.find();
    }
}