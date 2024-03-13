import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

public class karakter {
    private int id=9;
    public int tutucu=0;
    public int lokasyon_x;
    public int lokasyon_y;
    public int[][] location_handler;
    uygulama ugl;
    public BufferedImage kar_image;
    Random random=new Random();

    public karakter(uygulama ugl) {
        this.lokasyon_x=lokasyon_x;
        this.lokasyon_y=lokasyon_y;
        this.ugl=ugl;
        get_image();

    }
    public void get_image(){
        try{
            kar_image= ImageIO.read(getClass().getResourceAsStream("/karakter.png"));
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }
    public void kar_konum(int[][] temporary_array,int temp_value,int temp_value_2,int tilesize)
    {

        while (tutucu!=1)
        {
            int a= random.nextInt(temp_value);
            int b= random.nextInt(temp_value_2);
            if(temporary_array[a][b]==0)
            {
                lokasyon_x=a;
                lokasyon_y=b;
                tutucu=1;
            }
        }
    }

    /*public int[][] en_kisa_yol(int[][] temporary_array){


    }*/

}
