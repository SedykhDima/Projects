import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// Программа для последовательного вывода файлов


public class LessonThread {
    public static String firstFileName;
    public static String secondFileName;
    public static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

    static{
        try
        {
            firstFileName = reader.readLine();
            secondFileName = reader.readLine();
        }
        catch (IOException e){
        }
    }

    public static void main(String[] args) throws InterruptedException {
        systemOutPrintln(firstFileName);
        systemOutPrintln(secondFileName);
    }

    public static void systemOutPrintln(String fileName) throws InterruptedException {
        ReadFileInterface f = new ReadFileThread();
        f.setFileName(fileName);
        f.start();
        f.join();
        System.out.println(f.getFileContent());
    }
}