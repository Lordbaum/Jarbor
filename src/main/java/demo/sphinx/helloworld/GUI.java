package demo.sphinx.helloworld;
import java.awt.*;
import javax.swing.JFrame;
public class GUI {
    private GraphicsDevice vc;
    public GUI(){
        GraphicsEnvironment env = GraphicsEnvironment.getLocalGraphicsEnvironment();
        this.vc = env.getDefaultScreenDevice();
    }
    public void setScreen(DisplayMode dm, JFrame window){
        window.setUndecorated( false);
        window.setResizable( true);
        vc.setDisplayMode(dm);

        if(dm != null&& vc.isDisplayChangeSupported()){
            try{
                vc.setDisplayMode(dm);
            }catch (Exception ex){

            }
        }
    }
    public Window getFullWindow(){
        return vc.getFullScreenWindow();
    }
    public void restoreScreen(){
        Window w = vc.getFullScreenWindow();
        if(w !=null){
            w.dispose();
        }
        vc.setFullScreenWindow(null);
    }
}
