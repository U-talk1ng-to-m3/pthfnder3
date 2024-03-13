import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.util.List;

public class uygulama extends JPanel implements Runnable{


    public int originalTileSize;
    public int scale;





    public int[] konum=new int[2];
    public final int tileSize=30;
    public int toplanan_hazine=0;
    int FPS=60;
    int row=0;
    int col=0;
    public int input_1;
    public int input_2;
    public int sayi=1;
    public final List<Integer> path=new ArrayList<Integer>();

    Queue<Integer> visited_cells = new LinkedList<>();
    Queue<Integer> neighbor_cells=new LinkedList<>();
    Random random=new Random();


    //public Map_Generator Map=new Map_Generator(input_1,input_2);
    public Map_Generator Map;
    agac a=new agac(this);
    karakter k=new karakter(this);
    yol yol_1=new yol(this);
    duvar duvar_1=new duvar(this);
    dag dag_1=new dag(this);
    kayalar kaya_1=new kayalar(this);
    Hazine hazine_1=new Hazine(this);
    Thread uyg_thread;



    Stack<Integer> yigin=new Stack<>();



    public void setup(){
        Map.generateMap();

        k.kar_konum(Map.dizi,input_1,input_2,tileSize);
    }
    public uygulama(Map_Generator Map,int input_1,int input_2){
        this.input_1=input_1;
        this.input_2=input_2;
        this.Map=Map;

        this.setPreferredSize(new Dimension(1900,900));

        this.setBackground(Color.black);
        this.setDoubleBuffered(true);




        //Map.generateMap();

        //k.kar_konum(Map.dizi,input_1,input_2,tileSize);



        k.kar_konum(Map.dizi,input_1,input_2,tileSize);
        //setup();
        update();

    }


    public void update(){
        if(toplanan_hazine!=5)
        {
        /*sayi=random.nextInt(4);
        switch (sayi){
            case 0:
                if(Map.dizi[k.lokasyon_x][k.lokasyon_y-1]==0 )//yukarı
                {


                    k.lokasyon_y-=1;
                    Map.dizi[k.lokasyon_x][k.lokasyon_y]=8;

            if(Map.dizi[k.lokasyon_x][k.lokasyon_y+1]==0)//asagi bossa yigina ekle
            {
                yigin.push(k.lokasyon_x);
                yigin.push(k.lokasyon_y+1);
            }
            if(Map.dizi[k.lokasyon_x+1][k.lokasyon_y]==0)//sag bossa yigina ekle
            {
                yigin.push(k.lokasyon_x+1);
                yigin.push(k.lokasyon_y);
            }
            if(Map.dizi[k.lokasyon_x-1][k.lokasyon_y]==0)//sol bossa yigina ekle
            {
                yigin.push(k.lokasyon_x-1);
                yigin.push(k.lokasyon_y);
            }

                }
                break;
            case 1:
                if(Map.dizi[(k.lokasyon_x)+1][k.lokasyon_y]==0 )//saga
                {

                    k.lokasyon_x+=1;
                    Map.dizi[k.lokasyon_x][k.lokasyon_y]=8;

            if(Map.dizi[k.lokasyon_x][k.lokasyon_y+1]==0)//asagi bossa yigina ekle
            {
                yigin.push(k.lokasyon_x);
                yigin.push(k.lokasyon_y+1);
            }
            if(Map.dizi[k.lokasyon_x][k.lokasyon_y-1]==0)//yukari bossa yigina ekle
            {
                yigin.push(k.lokasyon_x);
                yigin.push(k.lokasyon_y-1);
            }
            if(Map.dizi[k.lokasyon_x-1][k.lokasyon_y]==0)//sol bossa yigina ekle
            {
                yigin.push(k.lokasyon_x-1);
                yigin.push(k.lokasyon_y);
            }

                }
                break;
            case 2:
                if(Map.dizi[(k.lokasyon_x)][(k.lokasyon_y)+1]==0)//asagi
                {
                    k.lokasyon_y+=1;
                    Map.dizi[k.lokasyon_x][k.lokasyon_y]=8;

            if(Map.dizi[k.lokasyon_x+1][k.lokasyon_y]==0)//sag bossa yigina ekle
            {
                yigin.push(k.lokasyon_x+1);
                yigin.push(k.lokasyon_y);
            }
            if(Map.dizi[k.lokasyon_x][k.lokasyon_y-1]==0)//yukari bossa yigina ekle
            {
                yigin.push(k.lokasyon_x);
                yigin.push(k.lokasyon_y-1);
            }
            if(Map.dizi[k.lokasyon_x-1][k.lokasyon_y]==0)//sol bossa yigina ekle
            {
                yigin.push(k.lokasyon_x-1);
                yigin.push(k.lokasyon_y);
            }
                }
                break;
            case 3:
                if(Map.dizi[(k.lokasyon_x)-1][k.lokasyon_y]==0)//sola
                {
                    k.lokasyon_x-=1;
                    Map.dizi[k.lokasyon_x][k.lokasyon_y]=8;

            if(Map.dizi[k.lokasyon_x+1][k.lokasyon_y]==0)//sag bossa yigina ekle
            {
                yigin.push(k.lokasyon_x+1);
                yigin.push(k.lokasyon_y);
            }
            if(Map.dizi[k.lokasyon_x][k.lokasyon_y-1]==0)//yukari bossa yigina ekle
            {
                yigin.push(k.lokasyon_x);
                yigin.push(k.lokasyon_y-1);
            }
            if(Map.dizi[k.lokasyon_x][k.lokasyon_y+1]==0)//asagi bossa yigina ekle
            {
                yigin.push(k.lokasyon_x);
                yigin.push(k.lokasyon_y+1);
            }
                }
                break;
        }
        if(Map.dizi[k.lokasyon_x][k.lokasyon_y-1]!=0 && Map.dizi[(k.lokasyon_x)+1][k.lokasyon_y]!=0 && Map.dizi[(k.lokasyon_x)][(k.lokasyon_y)+1]!=0 && Map.dizi[(k.lokasyon_x)-1][k.lokasyon_y]!=0){
            System.out.println(yigin);
            k.lokasyon_y=yigin.pop();
            k.lokasyon_x=yigin.pop();

        }*/
            if(Map.dizi[k.lokasyon_x][k.lokasyon_y-1]==0 )//yukarı
            {
                k.lokasyon_y-=1;
                Map.dizi[k.lokasyon_x][k.lokasyon_y]=8;

                if(Map.dizi[k.lokasyon_x][k.lokasyon_y+1]==0)//asagi bossa yigina ekle
                {
                    yigin.push(k.lokasyon_x);
                    yigin.push(k.lokasyon_y+1);
                }
                if(Map.dizi[k.lokasyon_x+1][k.lokasyon_y]==0)//sag bossa yigina ekle
                {
                    yigin.push(k.lokasyon_x+1);
                    yigin.push(k.lokasyon_y);
                }
                if(Map.dizi[k.lokasyon_x-1][k.lokasyon_y]==0)//sol bossa yigina ekle
                {
                    yigin.push(k.lokasyon_x-1);
                    yigin.push(k.lokasyon_y);
                }
            }
            else if(Map.dizi[(k.lokasyon_x)+1][k.lokasyon_y]==0 )//saga
            {

                k.lokasyon_x+=1;
                Map.dizi[k.lokasyon_x][k.lokasyon_y]=8;

                if(Map.dizi[k.lokasyon_x][k.lokasyon_y+1]==0)//asagi bossa yigina ekle
                {
                    yigin.push(k.lokasyon_x);
                    yigin.push(k.lokasyon_y+1);
                }
                if(Map.dizi[k.lokasyon_x][k.lokasyon_y-1]==0)//yukari bossa yigina ekle
                {
                    yigin.push(k.lokasyon_x);
                    yigin.push(k.lokasyon_y-1);
                }
                if(Map.dizi[k.lokasyon_x-1][k.lokasyon_y]==0)//sol bossa yigina ekle
                {
                    yigin.push(k.lokasyon_x-1);
                    yigin.push(k.lokasyon_y);
                }
            }
            else if(Map.dizi[(k.lokasyon_x)][(k.lokasyon_y)+1]==0)//asagi
            {
                k.lokasyon_y+=1;
                Map.dizi[k.lokasyon_x][k.lokasyon_y]=8;

                if(Map.dizi[k.lokasyon_x+1][k.lokasyon_y]==0)//sag bossa yigina ekle
                {
                    yigin.push(k.lokasyon_x+1);
                    yigin.push(k.lokasyon_y);
                }
                if(Map.dizi[k.lokasyon_x][k.lokasyon_y-1]==0)//yukari bossa yigina ekle
                {
                    yigin.push(k.lokasyon_x);
                    yigin.push(k.lokasyon_y-1);
                }
                if(Map.dizi[k.lokasyon_x-1][k.lokasyon_y]==0)//sol bossa yigina ekle
                {
                    yigin.push(k.lokasyon_x-1);
                    yigin.push(k.lokasyon_y);
                }
            }
            else if(Map.dizi[(k.lokasyon_x)-1][k.lokasyon_y]==0)//sola
            {
                k.lokasyon_x-=1;
                Map.dizi[k.lokasyon_x][k.lokasyon_y]=8;

                if(Map.dizi[k.lokasyon_x+1][k.lokasyon_y]==0)//sag bossa yigina ekle
                {
                    yigin.push(k.lokasyon_x+1);
                    yigin.push(k.lokasyon_y);
                }
                if(Map.dizi[k.lokasyon_x][k.lokasyon_y-1]==0)//yukari bossa yigina ekle
                {
                    yigin.push(k.lokasyon_x);
                    yigin.push(k.lokasyon_y-1);
                }
                if(Map.dizi[k.lokasyon_x][k.lokasyon_y+1]==0)//asagi bossa yigina ekle
                {
                    yigin.push(k.lokasyon_x);
                    yigin.push(k.lokasyon_y+1);
                }
            }

            else if(Map.dizi[k.lokasyon_x][k.lokasyon_y-1]!=0 && Map.dizi[(k.lokasyon_x)+1][k.lokasyon_y]!=0 && Map.dizi[(k.lokasyon_x)][(k.lokasyon_y)+1]!=0 && Map.dizi[(k.lokasyon_x)-1][k.lokasyon_y]!=0){
                //System.out.println(yigin);
                k.lokasyon_y=yigin.pop();
                k.lokasyon_x=yigin.pop();

            }
            if(Map.dizi[k.lokasyon_x+1][k.lokasyon_y]==5 )
            {

                toplanan_hazine+=1;
                System.out.println(toplanan_hazine);
                Map.dizi[k.lokasyon_x+1][k.lokasyon_y]=0;
            } else if (Map.dizi[k.lokasyon_x-1][k.lokasyon_y]==5) {
                toplanan_hazine+=1;
                System.out.println(toplanan_hazine);
                Map.dizi[k.lokasyon_x-1][k.lokasyon_y]=0;
            }else if (Map.dizi[k.lokasyon_x][k.lokasyon_y+1]==5) {
                toplanan_hazine+=1;
                System.out.println(toplanan_hazine);
                Map.dizi[k.lokasyon_x][k.lokasyon_y+1]=0;
            }else if (Map.dizi[k.lokasyon_x][k.lokasyon_y-1]==5) {
                toplanan_hazine+=1;
                System.out.println(toplanan_hazine);
                Map.dizi[k.lokasyon_x][k.lokasyon_y-1]=0;
            }}



    }





    public void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D g2=(Graphics2D)g;
        for(row=0;row<Map.input_a;row++)
        {
            for(col=0;col<Map.input_b;col++){
                if(Map.dizi[col][row]==1)
                {
                    for(int agac_i=0;agac_i<6;agac_i++)
                    {
                        for(int agac_j=0;agac_j<6;agac_j++)
                        {
                            g2.drawImage(a.agac_image,(col*tileSize)+(agac_i*tileSize/6),(row*tileSize)+(agac_j*tileSize/6),tileSize/6,tileSize/6,null);
                        }
                    }
                }
                else if (Map.dizi[col][row]==4)
                {
                    for(int duvar_i=0;duvar_i<6;duvar_i++)
                    {
                        for(int duvar_j=1;duvar_j<6;duvar_j++)
                        {
                            g2.drawImage(duvar_1.wall_image,(col*tileSize),(row*tileSize),tileSize,tileSize/6,null);
                            g2.drawImage(a.agac_image,(col*tileSize)+(duvar_i*tileSize/6),(row*tileSize)+(duvar_j*tileSize/6),tileSize/6,tileSize/6,null);


                        }
                    }

                }
                else if(Map.dizi[col][row]==0)
                {
                    g2.drawImage(yol_1.yol_image,tileSize*col,tileSize*row,tileSize,tileSize,null);
                }
                else if(Map.dizi[col][row]==2)
                {
                    for(int dag_i=0;dag_i<2;dag_i++)
                    {
                        for(int dag_j=0;dag_j<2;dag_j++)
                        {
                            g2.drawImage(dag_1.dag_image,(tileSize*col)+(dag_i*tileSize/2),(tileSize*row)+(dag_j*tileSize/2),tileSize/2,tileSize/2,null);

                        }
                    }

                }
                else if(Map.dizi[col][row]==3)
                {
                    for(int kaya_i=0;kaya_i<10;kaya_i++)
                    {
                        for (int kaya_j=0;kaya_j<10;kaya_j++)
                        {
                            g2.drawImage(kaya_1.kaya_image,(tileSize*col)+(kaya_i*tileSize/10),(row*tileSize)+(kaya_j*tileSize/10),tileSize/10,tileSize/10,null);
                        }
                    }

                }else if(Map.dizi[col][row]==5)
                {
                    g2.drawImage(hazine_1.hazine_image,tileSize*col,tileSize*row,tileSize,tileSize,null);

                }
                else if(Map.dizi[col][row]==8)
                {

                    g2.fillRect(tileSize*col,row*tileSize,tileSize,tileSize);
                    g2.setColor(Color.cyan);
                }




            }}

        g2.drawImage(k.kar_image,k.lokasyon_x*tileSize,k.lokasyon_y*tileSize,tileSize,tileSize,null);

        g2.dispose();
        repaint();
    }



    @Override
    public void run() {
        double drawInterval=1000000000/(FPS/4);
        double delta=0;
        long lastTime=System.nanoTime();
        long currentTime;
        while(uyg_thread!=null){
            currentTime=System.nanoTime();
            delta+=(currentTime-lastTime)/drawInterval;
            lastTime=currentTime;
            if(delta>=1){

                update();

                delta--;
            }


        }
    }
    public void start_thread(){
        uyg_thread=new Thread(this);
        uyg_thread.start();
        reset();

    }
}