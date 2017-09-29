package cs245.v1.pkg0.pkg1;

import java.awt.*;
import javax.swing.*;

/**
 *
 * @author FelixZhang
 */
public class MainFrame  extends JFrame{
    private CardLayout layout = new CardLayout();
    private JPanel mainP = new JPanel(layout);
    private MainMenu mainMenu = new MainMenu();
    private HighScore hScore = new HighScore();
    private Credits credit = new Credits();
    
    
    public MainFrame() {
        initUI();
    }
    
    private void initUI() {
        
        
        
        mainP.add(mainMenu, "menu");
        mainP.add(hScore, "High Score");
        mainP.add(credit, "credits");
        
        mainMenu.setMain(this);
        
        setTitle("PlaceHolder title");
        setSize(600, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        
        add(mainP);
    }
    
    public void swapView(String key) {
        layout.show(mainP, key);
    }
    
    
   
    

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            MainFrame menu = new MainFrame();
            menu.setVisible(true);
        });
    }
    
}
