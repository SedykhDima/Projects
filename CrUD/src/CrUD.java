import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/* CRUD
CrUD - Create, Update, Delete
Программа запускается с одним из следующих наборов параметров:
-c name sex bd
-u id name sex bd
-d id
-i id
Значения параметров:
name - имя, String
sex - пол, "м" или "ж", одна буква
bd - дата рождения в следующем формате 15/04/1990
-c  - добавляет человека с заданными параметрами в конец allPeople, выводит id (index) на экран
-u  - обновляет данные человека с данным id
-d  - производит логическое удаление человека с id
-i  - выводит на экран информацию о человеке с id: name sex (м/ж) bd (формат 15-Apr-1990)

id соответствует индексу в списке
Пример параметров: -c Миронов м 15/04/1990
*/

public class CrUD {
    public static List<Person> allPeople = new ArrayList<Person>();
    static {
        allPeople.add(Person.createMale("Иванов Иван", new Date()));  //сегодня родился    id=0
        allPeople.add(Person.createMale("Петров Петр", new Date()));  //сегодня родился    id=1
        String[] args1 = new String[]{"-c", "Миронов", "м", "15/04/1990"};
        main(args1);
        String[] args2 = new String[]{"-i", "2"};
        main(args2);
    }

    public static void main(String[] args) {
        switchParametr(args);
    }

    public static void switchParametr(String[] args){
        char[] parametr = args[0].toCharArray();
        switch (parametr[parametr.length-1]){
            case 'c':{
                createContact(args[1], args[2], args[3]);
                break;
            }
            case 'u':{
                updateContact(Integer.parseInt(args[1]), args[2], args[3], args[4]);
                break;
            }
            case 'd':{
                deleteContact(Integer.parseInt(args[1]));
                break;
            }
            case 'i':{
                printInformationContact(Integer.parseInt(args[1]));
                break;
            }
        }
    }

    private static void createContact(String name, String sex, String date){
        Date date1 = new Date(date);
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MMM-y", Locale.ENGLISH);
        dateFormat.format(date1);
        if (sex.equals("м")){
            allPeople.add(Person.createMale(name, date1));
        }
        else if (sex.equals("ж")){
            allPeople.add(Person.createFemale(name, date1));
        }
        System.out.println(allPeople.size()-1);
    }

    private static void updateContact(int id, String name, String sex, String date){
        allPeople.get(id).setName(name);
        Date date1 = new Date(date);
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MMM-y", Locale.ENGLISH);
        dateFormat.format(date1);
        allPeople.get(id).setBirthDay(date1);
        if (sex.equals("м")){
            allPeople.get(id).setSex(Sex.MALE);
        }
        else if (sex.equals("ж")){
            allPeople.get(id).setSex(Sex.FEMALE);
        }
    }

    private static void deleteContact(int id){
        allPeople.remove(id);
    }

    private static void printInformationContact(int id){
        String name = allPeople.get(id).getName();
        String sex = "";
        if (allPeople.get(id).getSex().equals(Sex.MALE)) sex = "м";
        else if (allPeople.get(id).getSex().equals(Sex.FEMALE)) sex = "ж";
        String date = allPeople.get(id).getBirthDay().toString();
        System.out.println(name+" "+sex+" "+date);
    }
}

