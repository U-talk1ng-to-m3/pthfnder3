import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Hazine {

    uygulama uyg;
    public BufferedImage hazine_image;
    public Hazine(uygulama uyg){
        this.uyg=uyg;
        get_hazine_image();
    }
    public void get_hazine_image(){
        try{
            hazine_image= ImageIO.read(getClass().getResourceAsStream("/golden_chest.png"));
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }

    }
}
