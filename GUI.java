import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.filechooser.FileSystemView;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class GUI implements ActionListener {
    PassGen passGen = new PassGen();
    boolean num = true;
    boolean upper = true;
    boolean lower = true;
    boolean ambiguous = false;
    boolean spec = true;
    File passFile;
    String bannedCharacters;
    int passLength, clicks = 0, clicksPrevious = 0;
    boolean passGenerated = false, firstRun = false;
    JFrame frame = new JFrame("Password Generator");
    JPanel panel = new JPanel();
    JCheckBox useSpec = new JCheckBox("Use Special Characters", true);
    JCheckBox useAmbiguous = new JCheckBox("Use Ambiguous Characters", false);
    JCheckBox useUpper = new JCheckBox("Use Uppercase Letters", true);
    JCheckBox useLower = new JCheckBox("Use Lowercase Letters", true);
    JCheckBox useNum = new JCheckBox("Use Numbers", true);
    JLabel ambiChars = new JLabel("({ } [ ] ( ) / \\ ' \" ` ~ , ; : . < >)");
    JLabel numPrompt = new JLabel("Password Length: ");
    JSlider length = new JSlider(JSlider.HORIZONTAL, 8, 24, 16);
    JLabel banPrompt = new JLabel("Dont use letters/characters: ");
    JTextArea bannedChars = new JTextArea();
    JTextArea generatedPassword = new JTextArea();
    JLabel passMarker = new JLabel("Password: ");
    JButton generate = new JButton("Generate");
    JButton fileAdd = new JButton("Add to File");

    GUI(){

        numPrompt.setBounds(0, 0, 110, 15);
        length.setBounds(0, 17, 300, 50);
        length.setMajorTickSpacing(4);
        length.setMinorTickSpacing(1);
        length.setPaintTicks(true);
        length.setPaintLabels(true);
        useSpec.setBounds(0, 90, 160, 15);
        useAmbiguous.setBounds(0, 130, 260, 15);
        useNum.setBounds(0, 170, 110, 15);
        useUpper.setBounds(0, 210, 160, 15);
        useLower.setBounds(0, 250, 160, 15);
        ambiChars.setBounds(32, 135, 200, 30);
        banPrompt.setBounds(0, 290, 200, 15);
        bannedChars.setBounds(0, 310, 110, 20);
        bannedChars.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY, 1));
        generatedPassword.setBounds(275, 130, 185, 20);
        generatedPassword.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY, 1));
        passMarker.setBounds(275, 115, 100, 15);
        generate.setBounds(275, 170, 110, 18);
        fileAdd.setBounds(275, 210, 110, 18);


       panel.setLayout(null);
       panel.add(useSpec);
       panel.add(useLower);
       panel.add(useUpper);
       panel.add(useNum);
       panel.add(useAmbiguous);
       panel.add(ambiChars);
       panel.add(numPrompt);
       panel.add(length);
       panel.add(banPrompt);
       panel.add(bannedChars);
       panel.add(generatedPassword);
       panel.add(passMarker);
       panel.add(generate);
       panel.add(generatedPassword);
       panel.add(fileAdd);



        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocation(700, 400);
        frame.setSize(500, 400);
        frame.add(panel);
        frame.setVisible(true);

        generate.addActionListener(e -> {
            System.out.println("the button was pushed");
            ambiguous = useAmbiguous.isSelected();
            spec = useSpec.isSelected();
            num = useNum.isSelected();
            upper = useUpper.isSelected();
            lower = useLower.isSelected();
            passLength = length.getValue();
            bannedCharacters = bannedChars.getText();

            System.out.println("all vars initialized");

            generatedPassword.setText(passGen.generate(num, spec, ambiguous, upper, lower, bannedCharacters, passLength));

            passGenerated = true;

            clicks++;


        });

        fileAdd.addActionListener(e -> {
            if(!passGenerated){
                JDialog noPassPopup = new JDialog(frame, "Password Required");
                JLabel noPassPopupText = new JLabel("You must generate a password before\n it can be saved to a file");
                noPassPopup.add(noPassPopupText);
                noPassPopup.setLocation(frame.getX() + 100, frame.getY() + 100);
                noPassPopup.setSize(370, 140);
                noPassPopup.setVisible(true);
            }else if(clicksPrevious == clicks){
                JDialog oldPassPopup = new JDialog(frame, "Old Password");
                JLabel oldPassPopupText = new JLabel("That password has already\n been added to a file");
                oldPassPopup.add(oldPassPopupText);
                oldPassPopup.setSize(320, 140);
                oldPassPopup.setLocation(frame.getX() + 100, frame.getY()+100);
                oldPassPopup.setVisible(true);

            }else{

                JFileChooser file = new JFileChooser();
                file.setAcceptAllFileFilterUsed(false);
                file.setDialogTitle("Select a .txt file");
                FileNameExtensionFilter restrictToTxt = new FileNameExtensionFilter(".txt Files", "txt");
                file.addChoosableFileFilter(restrictToTxt);
                if(firstRun){
                    file.setSelectedFile(FileSystemView.getFileSystemView().getHomeDirectory());
                    firstRun = false;
                }else{
                    file.setSelectedFile(passFile);
                }
                panel.add(file);

                int save = file.showSaveDialog(null);
                if (save == JFileChooser.APPROVE_OPTION) {
                    passFile = file.getSelectedFile();
                    String toSave = generatedPassword.getText();

                    passGen.saveToFile(passFile, toSave);
                }else{
                    JDialog canceledPopup = new JDialog(frame, "Cancelled");
                    JLabel canceledText = new JLabel("Operation Canceled");
                    canceledPopup.add(canceledText);
                    canceledPopup.setSize(200, 140);
                    canceledPopup.setLocation(frame.getX() + 100, frame.getY() + 100);
                    canceledPopup.setVisible(true);
                }

                clicksPrevious++;
            }
        });

    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
