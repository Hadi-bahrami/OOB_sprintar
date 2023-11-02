package sprint3.inlämning3;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Random;

public class BrickGame extends JFrame implements ActionListener {
    protected JButton firstSelectedButton = null;
    protected JButton tomBricka = null;
    protected int rows;
    protected JPanel startGame = new JPanel(new GridBagLayout());
    protected JPanel startScreen;
    protected JPanel topPanel = new JPanel();
    protected JButton startGameButton = new JButton("Starta spelet");
    protected JTextField väljaRad = new JTextField();
    protected JTextField väljaKolumn = new JTextField();
    protected ArrayList<Integer> arrayListNumbers;
    private int columns;
    protected JPanel mainPanel = new JPanel(new BorderLayout());
    protected JButton randomButton = new JButton("Nytt spel");



    BrickGame(){
        startScreen = new JPanel(){
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                try {
                    Image backgroundImage = ImageIO.read(new File("src/sprint3/inlämning3/pictures/bakgrund.jpg"));
                    g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        };
        väljaRad.setMaximumSize(new Dimension(50, 30));
        väljaRad.setFont(new Font("Arial", Font.BOLD, 20));
        väljaRad.setHorizontalAlignment(SwingConstants.CENTER);
        väljaKolumn.setMaximumSize(new Dimension(50, 30));
        väljaKolumn.setFont(new Font("Arial", Font.BOLD, 20));
        väljaKolumn.setHorizontalAlignment(SwingConstants.CENTER);
        JLabel raderLabel = new JLabel("Välj antal rader");
        raderLabel.setForeground(Color.white);
        raderLabel.setFont(new Font("Arial", Font.BOLD, 18));
        JLabel kolumnLabel = new JLabel("Välj antal kolumner");
        kolumnLabel.setForeground(Color.white);
        kolumnLabel.setFont(new Font("Arial", Font.BOLD, 18));
        startGameButton.setForeground(Color.black);
        startGameButton.setFont(new Font("Arial", Font.BOLD, 20));
        startScreen.setLayout(new BoxLayout(startScreen, BoxLayout.Y_AXIS));
        väljaRad.setAlignmentX(Component.CENTER_ALIGNMENT);
        väljaKolumn.setAlignmentX(Component.CENTER_ALIGNMENT);
        raderLabel.setAlignmentX(Component.CENTER_ALIGNMENT); // Center the JLabel
        kolumnLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        startGameButton.setAlignmentX(Component.CENTER_ALIGNMENT);

        startScreen.add(Box.createRigidArea(new Dimension(0, 100)));
        startScreen.add(raderLabel);
        startScreen.add(Box.createRigidArea(new Dimension(0, 10)));
        startScreen.add(väljaRad);
        startScreen.add(Box.createRigidArea(new Dimension(0, 10)));
        startScreen.add(kolumnLabel);
        startScreen.add(Box.createRigidArea(new Dimension(0, 10)));
        startScreen.add(väljaKolumn);
        startScreen.add(Box.createRigidArea(new Dimension(0, 10)));
        startScreen.add(startGameButton);

        startGameButton.addActionListener(e -> {
            if (!väljaRad.getText().equals("") && !väljaKolumn.getText().equals("")){
                int rows = Integer.parseInt(väljaRad.getText());
                int columns = Integer.parseInt(väljaKolumn.getText());
                randomButton.addActionListener(this);
                topPanel.add(randomButton);

                openBrickGame(startGamePanel(rows, columns));
            }
        });

        this.add(startScreen);
        setVisible(true);
        setSize(400,400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }
    public JPanel startGamePanel(int rows, int columns){

        this.rows = rows;
        this.columns = columns;
        arrayListNumbers = arrayListNumbers((rows*columns)-1);

        GridBagConstraints constraints = new GridBagConstraints();
        int loopnumber = 0;

        for (int loopRows = 0; loopRows<rows; loopRows++){

            for (int loopCols  = 0; loopCols <columns; loopCols++){
                if (loopRows == (rows-1 )&& loopCols == (columns-1)){
                    String a = loopRows + "." + loopCols;
                    JButton bricka = bricka("0");

                    constraints.gridx = loopCols;
                    constraints.gridy = loopRows;
                    startGame.add(bricka, constraints);
                    break;
                }
                String a = loopRows + "." + loopCols;
                JButton bricka = bricka(String.valueOf(arrayListNumbers.get(loopnumber)));
                constraints.gridx = loopCols;
                constraints.gridy = loopRows;

                bricka.addActionListener(e -> {
                    toActionListener(e.getSource());
                });
                startGame.add(bricka, constraints);
                loopnumber++;
            }

        }
        Border border = BorderFactory.createLineBorder(new Color(102,51,0), 10);
        startGame.setBorder(border);
        return startGame;
    };


    // Här genererar vi randon nummer baserad på hur antal kolumner o rader vi har
    public ArrayList<Integer> arrayListNumbers(int x){
        ArrayList<Integer> numbersList = new ArrayList<>();
        HashSet<Integer> genereatedNumbers = new HashSet<>();
        Random random = new Random();
        for (int i = 1; i<x+1; i++){
            int randomNumber = random.nextInt(x)+1;
            if (!genereatedNumbers.contains(randomNumber)){
                numbersList.add(randomNumber);
                genereatedNumbers.add(randomNumber);
            }else {
                i--;
            }
        }
        return numbersList;
    }
    // Här tar vi fram alla knapparna och tilldelar de en siffra
    public JButton bricka(String brickNummber){
        if (brickNummber.equals("0")){
            JButton jButton = new JButton("0");
            jButton.putClientProperty("Typ", jButton.getText());
            jButton.setBorder(new EmptyBorder(new Insets(10, 10, 10, 10)));
            jButton.setFont(new Font("Arial", Font.BOLD, 35));
            jButton.setForeground(new Color(255,229,204));
            jButton.setBackground(new Color(255,229,204));
            Border border = BorderFactory.createLineBorder(new Color(255,128,0), 2);
            jButton.setBorder(border);
            Dimension dimension = new Dimension(100, 100);
            jButton.setFocusable(false);
            jButton.setPreferredSize(dimension);
            return jButton;
        }
        else {
            JButton jButton = new JButton(brickNummber);
            jButton.putClientProperty("Typ", jButton.getText());
            jButton.setBorder(new EmptyBorder(new Insets(10, 10, 10, 10)));
            jButton.setFont(new Font("Arial", Font.BOLD, 35));
            Border border = BorderFactory.createLineBorder(new Color(153,76,0), 3);
            jButton.setBorder(border);
            Dimension dimension = new Dimension(100, 100);
            jButton.setFocusable(false);
            jButton.setBackground(new Color(255, 178, 102));
            jButton.setPreferredSize(dimension);
            return jButton;
        }

    }
    // Här kontrollerar vi klickade knapparnas koordinater och ser om dem ligger bredvid /ovanför/under varandra.
    public void checkKoordinates(JButton firstButton, JButton secondButton){
        GridBagConstraints firstButtonConstraints = ((GridBagLayout) startGame.getLayout()).getConstraints(firstButton);
        GridBagConstraints secondButtonConstraints = ((GridBagLayout) startGame.getLayout()).getConstraints(secondButton);

        int onSameRow = firstButtonConstraints.gridx - secondButtonConstraints.gridx;
        int onSameRowBeforeAfter= firstButtonConstraints.gridy-secondButtonConstraints.gridy;

        boolean nextToEachOther = (onSameRow == 0) && ((onSameRowBeforeAfter == 1) || ( onSameRowBeforeAfter== -1));
        int onSameColumn = firstButtonConstraints.gridy - secondButtonConstraints.gridy;
        int onSameColumnAboveUnder = firstButtonConstraints.gridx - secondButtonConstraints.gridx;

        boolean aboveOrUnder = ((onSameColumnAboveUnder == 1) || (onSameColumnAboveUnder == -1)) && onSameColumn == 0;
        if (nextToEachOther || aboveOrUnder){
            changePlaces(firstButton, secondButton);
        }

    }

    //Här byter vi plats på knapparna är bredvid varandra.
    public void changePlaces(JButton firstButton, JButton secondButton){

        GridBagConstraints firstButtonConstraints = ((GridBagLayout) startGame.getLayout()).getConstraints(firstButton);
        GridBagConstraints secondButtonConstraints = ((GridBagLayout) startGame.getLayout()).getConstraints(secondButton);

        startGame.remove(firstButton);
        startGame.remove(secondButton);

        startGame.add(firstButton, secondButtonConstraints);
        startGame.add(secondButton, firstButtonConstraints);

        revalidate();
        repaint();
        checkButtonOrder(startGame);

    }

    // Här kontrollerar vi hela tiden om brickorna ligger i ordning eller inte genom att lägga knapparnas värde i en lista
    private void checkButtonOrder(JPanel jPanel) {
        Component[] components = jPanel.getComponents();
        ArrayList<Integer> checkOrderList = new ArrayList<>();
        GridBagConstraints firstButtonConstraints;

        for (int r = 0; r<columns; r++){
            for (int c = 0; c<rows; c++){

                for (Component component : components) {
                    if (component instanceof JButton) {
                        JButton button = (JButton) component;
                        firstButtonConstraints = ((GridBagLayout) startGame.getLayout()).getConstraints(button);
                        int x =  firstButtonConstraints.gridy;
                        int y =  firstButtonConstraints.gridx;
                        if ((x == r) && (c == y)){
                            checkOrderList.add(Integer.parseInt(button.getText()));
                        }


                    }
                }

            }
        }
        boolean inOrder = isInAscendingOrderList(checkOrderList);
        System.out.println(inOrder);
        if (inOrder){
            finishedDialog();
        }
        checkOrderList.clear();
    }
    // Kollar om listan är i ordning
    public boolean isInAscendingOrderList(ArrayList<Integer> list) {
        for (int i = 0; i < list.size() - 2; i++) {
            if (list.get(0) == 0){
                return false;
            }
            System.out.println(list.get(i));
            if (list.get(i) > list.get(i + 1)) {
                return false;
            }
        }
        list.clear();
        return true;
    }

    //Vi registrerar knappen vi har klickat på och skickar det vidare till en annan metod
    public void toActionListener(Object button){

        if (button instanceof JButton){
            JButton clickedButton = (JButton) button;
            for (Component cp : startGame.getComponents()){
                if (cp instanceof JButton jButton){
                    if (jButton.getClientProperty("Typ").equals("0")){
                        tomBricka = jButton;
                        break;
                    }
                }
            }
            firstSelectedButton = clickedButton;
            checkKoordinates(firstSelectedButton, tomBricka);

        }
    }

    // här startar vi själva spelet.
    public void openBrickGame(JPanel jPanel) {
        try {
            getContentPane().remove(startScreen);
            mainPanel.add(topPanel, BorderLayout.PAGE_START);
            mainPanel.add(jPanel, BorderLayout.CENTER);
            getContentPane().add(mainPanel);
            pack();
            revalidate();
            repaint();
        } catch (Exception exception){
            exception.printStackTrace();
        }
    }
    public void finishedDialog() {
        try {
            JDialog dialog = new JDialog(this, "GRATTIS!", true);
            JPanel panel = new JPanel();
            panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
            JLabel label = new JLabel("Grattis!");
            JLabel label2 = new JLabel("Du har klarat av spelet!");
            JButton button = new JButton("Starta om spelet");

            label.setAlignmentX(Component.CENTER_ALIGNMENT);
            label2.setAlignmentX(Component.CENTER_ALIGNMENT);
            button.setAlignmentX(Component.CENTER_ALIGNMENT);

            button.setMaximumSize(new Dimension(150, 50));
            button.addActionListener(e -> {
                this.dispose();
                dialog.dispose();
                BrickGame brickGame = new BrickGame();
            });
            panel.add(label);
            panel.add(label2);
            panel.add(Box.createRigidArea(new Dimension(0, 20)));

            panel.add(button);
            dialog.add(panel);
            dialog.setLocationRelativeTo(null);
            dialog.setSize(300, 130);
            dialog.setLayout(new FlowLayout());

            dialog.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosing(WindowEvent e) {
                    dispose();
                }
            });

            dialog.setVisible(true);
            setDefaultCloseOperation(EXIT_ON_CLOSE);
        } catch (Exception exception){
            exception.printStackTrace();
        }
    }

    //
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == randomButton) {
            arrayListNumbers = arrayListNumbers((rows * columns) - 1);
            Component[] components = startGame.getComponents();
            for (int x = 0; x<components.length-1;x++) {
                if (components[x] instanceof JButton button) {
                    button.setText(String.valueOf(arrayListNumbers.get(x)));
                }
            }
            revalidate();
            repaint();
        }
        }
}
