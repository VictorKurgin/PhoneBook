import javax.swing.*;
import java.io.*;

public class SeeNumber extends JButton {
    SeeNumber(){
        super();
        setText("Посмореть номер");
        addActionListener(event -> {
            String inputLine = null;
            try {
                inputLine = new String(JOptionPane.showInputDialog(this,"Введите имя контакта").getBytes("UTF-8"), "UTF-8");
            } catch (UnsupportedEncodingException e) {
                //e.printStackTrace();
            } catch (NullPointerException exc){inputLine = null;}
            if(inputLine == null || inputLine.length() < 1) return;
            try(BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(BookFrame.file), "UTF8"))){
                String readLine;
                boolean found = false;
                while((readLine = br.readLine()) != null){
                    if(readLine.contains(inputLine + ":")) {
                        JOptionPane.showMessageDialog(this, readLine);
                        found = true;
                        break;
                    }
                }
                if(!found) JOptionPane.showMessageDialog(this, "Такого контакта нет в списке");
            }catch (IOException exception){
                JOptionPane.showMessageDialog(this,"Произошла ошибка ввода-вывода, попробуйте еще раз");
            }
        });
    }
}
