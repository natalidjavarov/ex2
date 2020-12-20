package gameClient;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class Ex2 {
    JFrame frame1;
    private JPanel panel1;
    private JButton startGameButton;
    private JTextField textFieldID;
    private JTextField textFieldScenario;
    private JLabel id;
    private JLabel scenario;


    public static void main(String[] args) {
        if(args.length == 0) {
            Ex2 login = new Ex2();
            login.MakeLogin();
        }
        else{
            MyGame.test(Integer.parseInt(args[0]),Integer.parseInt(args[1]));
        }
    }
    private void MakeLogin(){
        this.frame1 = new JFrame();
        this.panel1 = new JPanel();
        this.startGameButton = new JButton("Start game!");
        this.startGameButton.addActionListener(this::actionPerformed);
        this.textFieldID = new JTextField();
        this.textFieldScenario = new JTextField();
        this.id = new JLabel("Please enter ur id down below");
        this.scenario = new JLabel("please enter the scenario number down below");
        panel1.setBorder(BorderFactory.createEmptyBorder(30,30,10,30));
        panel1.setLayout(new GridLayout(0,1));
        panel1.add(id);
        panel1.add(textFieldID);
        panel1.add(scenario);
        panel1.add(textFieldScenario);
        panel1.add(startGameButton);
        panel1.add(startGameButton);
        frame1.add(panel1,BorderLayout.CENTER);
        frame1.setSize(1000 ,700);
        frame1.pack();
        frame1.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        textFieldID.setVisible(true);
        textFieldScenario.setVisible(true);
        id.setVisible(true);
        scenario.setVisible(true);
        startGameButton.setVisible(true);
        panel1.setVisible(true);
        frame1.setVisible(true);


    }
    private void createUIComponents() {
        // TODO: place custom component creation code here
    }
    public void actionPerformed(ActionEvent e) {
       // Ex2_Client ex2 = new Ex2_Client();
        MyGame.test(Integer.parseInt(textFieldID.getText()),Integer.parseInt(textFieldScenario.getText()));
    }
}
