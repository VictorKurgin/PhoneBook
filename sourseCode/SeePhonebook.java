import javax.swing.*;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class SeePhonebook extends JButton {
    public SeePhonebook(){
        super();
        setText("<html>Просмотреть <br>все контакты");
        addActionListener(event -> {
            try(BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream("./phoneBook.txt"), "UTF8"))){
                int c;
                StringBuilder str = new StringBuilder();
                while((c = br.read()) != -1){
                    str.append((char) c);
                }
                BookFrame.textPane.setText(String.valueOf(str));;
            }catch (IOException exception){
                JOptionPane.showMessageDialog(this,"Произошла ошибка ввода-вывода, попробуйте еще раз");}
        });
    }
}
