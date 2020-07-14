import javax.swing.*;
import java.awt.*;
import java.io.File;

public class BookFrame extends JFrame {
    private final int WIDTH = 550;
    private final int HEIGHT = 450;
    JPanel panel = new JPanel();
    AddContact addContact = new AddContact();
    DeleteContact deleteContact = new DeleteContact();
    SeeNumber seeNumber = new SeeNumber();
    SeePhonebook seePhonebook;
    JScrollPane scrollPane;
    static JTextArea textPane = new JTextArea();
    final static File file = new File("./phoneBook.txt");

    BookFrame(){
        super();
        setSize(WIDTH, HEIGHT);
        setTitle("Телефонный справочник");
        setMinimumSize(new Dimension(WIDTH, HEIGHT));
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        seePhonebook = new SeePhonebook();
        panel.add(addContact);
        panel.add(deleteContact);
        panel.add(seeNumber);
        panel.add(seePhonebook);
        panel.setPreferredSize(new Dimension(WIDTH, 80));
        add(panel, BorderLayout.NORTH);
        scrollPane = new JScrollPane(textPane);
        add(scrollPane);
        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new BookFrame();
            }
        });
    }
}
