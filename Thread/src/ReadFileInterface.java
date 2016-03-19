public interface ReadFileInterface {

    void setFileName(String fullFileName);

    String getFileContent();

    void join() throws InterruptedException;

    void start();
}