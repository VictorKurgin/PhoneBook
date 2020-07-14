import javax.swing.*;
import java.io.*;

public class DeleteContact extends JButton {
    public DeleteContact(){
        super();
        setText("Удалить контакт");
        addActionListener(event -> {
            BookFrame.textPane.setText("");
            File tmp = new File("./phoneBook2.txt");
            String inputLine = null;
            try {
                inputLine = new String(JOptionPane.showInputDialog(this,"Введите имя контакта").getBytes("UTF-8"), "UTF-8");
            } catch (UnsupportedEncodingException e) {
                //e.printStackTrace();
            } catch (NullPointerException exc){inputLine = null;}
            if(inputLine == null || inputLine.length() == 0) return;
            try(BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(BookFrame.file), "UTF-8"));
                BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(tmp), "UTF-8"))){
                String readLine;
                boolean found = false;
                while((readLine = br.readLine()) != null){
                    if(readLine.contains(inputLine + ":")) {
                        JOptionPane.showMessageDialog(this, "Контакт " + inputLine + " удален");
                        found = true;
                    }
                    else bw.write(readLine + "\n");
                }
                if(!found) JOptionPane.showMessageDialog(this, "Такого контакта нет в списке");
            }catch (IOException exception){
                JOptionPane.showMessageDialog(this,"Произошла ошибка ввода-вывода, попробуйте еще раз");
            }
            BookFrame.file.delete();
            tmp.renameTo(BookFrame.file);
        });
    }
}
