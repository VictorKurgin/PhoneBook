import javax.swing.*;
import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;

public class AddContact extends JButton {

    public AddContact(){
        super();
        setText("Добавить контакт");
        addActionListener(event -> {
            boolean found = false;
            BookFrame.textPane.setText("");
            String inputLine = null;
            try {
                inputLine = new String(JOptionPane.showInputDialog(this,"Введите имя контакта").getBytes("UTF-8"));
            } catch (UnsupportedEncodingException e) {
                //e.printStackTrace();
            } catch (NullPointerException exc){inputLine = null;}
            if(inputLine == null || inputLine.length() <= 0) return;
            try(BufferedReader br = new BufferedReader(new FileReader("phoneBook.txt"))){
                String readLine;

                while((readLine = br.readLine()) != null){
                    if(readLine.contains(inputLine + ":")) {
                        JOptionPane.showMessageDialog(this, "Контакт с таким именем уже существует");
                        found = true;
                        break;
                    }
                }
            }catch (IOException exception){
                System.out.println(exception);
            }
            if(!found) {
                String number = JOptionPane.showInputDialog(this, "Введите номер телефона");
                if (number == null) return;
                inputLine = inputLine.concat(": " + number);
                try (FileWriter fileWriter = new FileWriter("phoneBook.txt", true)) {
                    fileWriter.write(inputLine + "\n");
                    JOptionPane.showMessageDialog(this, "Контакт добавлен.");
                } catch (IOException exception) {
                    JOptionPane.showMessageDialog(this, "Произошла ошибка ввода-вывода, попробуйте еще раз");
                }
            }
            sortFile(new File("phoneBook.txt"));
        });
    }
    static void sortFile(File file){
        ArrayList<String> strArray = new ArrayList<String>();
        try(BufferedReader br = new BufferedReader(new FileReader(file))){
            String str;
            while((str = br.readLine()) != null){
                strArray.add(str);
            }
        }catch (IOException exception){
            System.out.println(exception);
        }
        String[] strings = strArray.toArray(new String[strArray.size()]);
        Arrays.sort(strings);
        try (FileWriter fileWriter = new FileWriter(file)) {
            for (String s : strings) {
                fileWriter.write(s + "\n");
            }
        } catch (IOException exception) {
            System.out.println(exception);
        }
        //return file;
    }
}
