import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class ReadFileThread extends Thread implements ReadFileInterface{
    private String file_name;
    private String file_data ="";

    /*
    * Устанавливает имя файла, из которого будет читаться содержимое.
     */
    public void setFileName(String fullFileName)
    {
        this.file_name = fullFileName;
    }

    /*
    * Возвращает содержимое файла.
     */
    public String getFileContent()
    {
        return file_data;
    }

    public void run()
    {
        String c;
        try{
            BufferedReader reader_file = new BufferedReader(new FileReader(file_name));
            while ((c = reader_file.readLine()) != null){
                file_data += c + " ";
            }
        }
        catch (IOException e){
        }
    }
}