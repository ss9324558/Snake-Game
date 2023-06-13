import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Board extends JPanel implements ActionListener {
    int height=400;
    int width=400;
    int MAX_DOTS=1600;
    int DOT_SIZE=10;
    int DOTS;
    int []x=new int[MAX_DOTS];
    int []y=new int[MAX_DOTS];
    int apple_x;
    int apple_y;

    //images
    Image body,head,apple;
    Timer timer;
    int DELAY=300;
    boolean left=true;
    boolean right=false;
    boolean up=false;
    boolean down=false;
    Board(){
        setPreferredSize(new Dimension(width,height));
        setBackground(Color.BLACK);
        initGame();
        loadImages();
    }

    //initialise game
    public void initGame(){
     DOTS=3;
     x[0]=50;
     y[0]=50;

     //initialise snake position
        for(int i=1;i<DOTS;i++){
            x[i]=x[0]+DOT_SIZE*i;
            y[i]=y[0];
        }

        //initialise apple
        locateApple();
        timer=new Timer(DELAY,this);
        timer.start();
    }

    //load images from resources folder to image object
    public void loadImages(){
        ImageIcon bodyIcon=new ImageIcon("src/resources/dot.png");
        body=bodyIcon.getImage();
        ImageIcon headIcon=new ImageIcon("src/resources/head.png");
        head=headIcon.getImage();
        ImageIcon appleIcon=new ImageIcon("src/resources/apple.png");
        apple=appleIcon.getImage();

    }
    //draw images at snake's and apple's position
    @Override
    public void paintComponent(Graphics g){
        g.drawImage(apple,apple_x,apple_y,this);
        for(int i=0;i<DOTS;i++){
            if(i==0){
                g.drawImage(head,x[0],y[0],this);
            } else {
                g.drawImage(body,x[i],y[i],this);
            }
        }
    }
    //randomize apple's position
    public void locateApple(){
        apple_x=((int )(Math.random()*39)*DOT_SIZE);
        apple_y=((int )(Math.random()*39)*DOT_SIZE);
    }
    @Override
    public void actionPerformed(ActionEvent actionEvent){
        move();
        repaint();
    }
    //make some move
    public void move(){
       for(int i=DOTS-1;i>=0;i--){
           x[i]=x[i-1];
           y[i]=y[i-1];
       }
       if(left){
       x[0]-=DOT_SIZE;
       }
       if(right){
           x[0]+=DOT_SIZE;
       }
       if(up){
           y[0]-=DOT_SIZE;
       }
       if(down){
           y[0]+=DOT_SIZE;
       }
    }
}
