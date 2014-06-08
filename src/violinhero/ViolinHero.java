
package violinhero;
import java.io.*;
import java.awt.*;
import java.awt.geom.*;
import java.awt.event.*;
import static java.awt.event.MouseWheelEvent.WHEEL_UNIT_SCROLL;
import javax.swing.*;

public class ViolinHero extends JFrame implements Runnable {
    static final int WINDOW_WIDTH = 600;
    static final int WINDOW_HEIGHT = 900;

    final int TOP_BORDER = 0;
    final int YTITLE = 0;
    boolean animateFirstTime = true;
    int xsize = -1;
    int ysize = -1;
    Image image;
    Graphics2D g;
    
    
    boolean gameOver;
    int mouseThingX;
    int mouseThingY;
    int bowDir;
    Image background;
    Image bow;
    Image gameOverIMG;
    Image playButton;
    Image logo;
    Image escape;
    Image begin;
    int bowXPos;
    
    Image noteIMG;
    
    Image GString;
    Image DString;
    Image AString;
    Image EString;
    
    int redBarXPos;
    Image redBar;

    boolean playing;

    
    Color gradient;
            int green;
            int blue;
            int red;
    enum strings{G,D,A2,E2}
//    int Gstring=0;
//    int Dstring=1;
//    int Estring=2;
//    int Astring=3;
//    int currentString;
    
    //enum notes{,,,}
    strings currentString;
    enum notes{G,A,B,C,D,E,F,G2,A2,B2,C2,D2,E2,F2,G3,A3}
    //enum notes{,,,}
    notes note;
/////////////
    static ViolinHero frame1;
    public static void main(String[] args) {
        frame1 = new ViolinHero();
        frame1.setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        frame1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame1.setVisible(true);
    }

    public ViolinHero() {

       addMouseMotionListener(new MouseMotionAdapter() {
      public void mouseDragged(MouseEvent e) {

        repaint();
      }
    });

     addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e) {

                if (e.BUTTON1 == e.getButton()) {
                    int xpos = e.getX();
                    int ypos = e.getY();
                   
//left button
// location of the cursor.
                    if (playing == true)
                    {
                        bowDir=1;
                    }
                    if (playing==false)
                    {
                        if (xpos < (416) && 
                            xpos > (217) &&
                            ypos < (559) &&
                            ypos > (499))
                            {
                                playing = true;
                            }
                    }
                    
                   
                }

                else if (e.BUTTON3 == e.getButton()) {
                    //right button
                    bowDir=-1;
                }
                
                repaint();
            }
        });

    addMouseMotionListener(new MouseMotionAdapter() {
      public void mouseMoved(MouseEvent e) {
        
                    int xpos = e.getX();
                    int ypos = e.getY();
                    mouseThingX = xpos;
                    mouseThingY = ypos;

        repaint();
      }
     

            private ScrollPane getScrollPaneFromSomewhere() {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
    });
    
        addKeyListener(new KeyAdapter() {

            public void keyPressed(KeyEvent e) {
               
                 if (e.VK_Q == e.getKeyCode()) {
                   
                    }
                    if (e.VK_ESCAPE == e.getKeyCode()) {
                   reset();
                    }

                    if(e.VK_SPACE == e.getKeyCode())
                    {
                        if(currentString ==strings.G)
                            currentString=strings.G;
                        
                        else if(currentString ==strings.D)
                            currentString=strings.G;
                        
                        else if(currentString ==strings.A2)
                            currentString=strings.D;
                        
                        else if(currentString ==strings.E2)
                            currentString=strings.A2;
                    }
                    if(e.VK_C == e.getKeyCode()|| e.VK_C == e.getKeyCode())
                    {
                        
                       if(currentString ==strings.G)
                            currentString=strings.D;
                        
                        else if(currentString ==strings.D)
                            currentString=strings.A2;
                        
                        else if(currentString ==strings.A2)
                            currentString=strings.E2;
                        
                        else if(currentString ==strings.E2)
                            currentString=strings.E2;
                        
                    }
                   if(currentString ==strings.G)
                   {
                        if (e.VK_A == e.getKeyCode()) {
                            note=notes.C;  
                        }
                        else if (e.VK_W == e.getKeyCode()) {
                            note=notes.B;  
                        }
                        else if (e.VK_E == e.getKeyCode()) {
                            note=notes.A;  
                        }
                        else
                            note=notes.G;  
                            
                          
                            
                   }
                    
                   if(currentString ==strings.D)
                   {
                        
                         if (e.VK_A == e.getKeyCode()) {
                            note=notes.G2;  
                        }
                        else if (e.VK_W == e.getKeyCode()) {
                            note=notes.F;  
                        }
                        else if (e.VK_E == e.getKeyCode()) {
                            note=notes.E;  
                        }
                        else
                            note=notes.D;  
                            
                   }
                    
                   if(currentString ==strings.A2)
                   {
                      
                         if (e.VK_A == e.getKeyCode()) {
                            note=notes.D2;  
                        }
                        else if (e.VK_W == e.getKeyCode()) {
                            note=notes.C2;  
                        }
                        else if (e.VK_E == e.getKeyCode()) {
                            note=notes.B2;  
                        }
                        else 
                            note=notes.A2;  

                   }
                    
                   if(currentString == strings.E2)
                   {
                        
                        if (e.VK_A == e.getKeyCode()) {
                            note=notes.A3;  
                            
                        }
                        else if (e.VK_W == e.getKeyCode()) {
                            note=notes.G3;  
                        }
                        else if (e.VK_E == e.getKeyCode()) {
                            note=notes.F2;  
                        }
                        else 
                            note=notes.E2;  
                            
                   }
               
               
                
                repaint();
        }
        });
        init();
        start();
    }




    Thread relaxer;
////////////////////////////////////////////////////////////////////////////
    public void init() {
        requestFocus();
    }
////////////////////////////////////////////////////////////////////////////
    public void destroy() {
    }
////////////////////////////////////////////////////////////////////////////
    public void paint(Graphics gOld) {
        if (image == null || xsize != getSize().width || ysize != getSize().height) {
            xsize = getSize().width;
            ysize = getSize().height;
            image = createImage(xsize, ysize);
            g = (Graphics2D) image.getGraphics();
            g.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                    RenderingHints.VALUE_ANTIALIAS_ON);
        }

//fill background
       

        int x[] = {getX(0), getX(getWidth2()), getX(getWidth2()), getX(0), getX(0)};
        int y[] = {getY(0), getY(0), getY(getHeight2()), getY(getHeight2()), getY(0)};
//fill border
        g.setColor(Color.white);
        g.fillPolygon(x, y, 4);
// draw border
       

        if (animateFirstTime) {
            gOld.drawImage(image, 0, 0, null);
            return;
        }
        if (playing == false)
        {
          
        g.drawImage(background, getX(0), getY(0),getWidth2(),getHeight2(), this);
        //g.drawImage(playButton, getX(getWidth2()/2)-50, getY(getHeight2()/2)-50,100,100, this);
        //g.drawImage(logo,getX(getWidth2()/2)-250,getY(getHeight2()/2)-400,500,400, this);
        //g.drawImage(begin,getX(getWidth2()/2)-150, getY(getHeight2()/2)+100,300,200, this);
         g.drawRect(217, 499, 200, 100);
        }
        ////////////////////////////////////////////////////////////////////////////////
       if (playing)
       {
            int tempXPos=0;
            {
            if(currentString==strings.G)
            {
                g.drawImage(GString, getX(0), getY(0),getWidth2(),getHeight2(), this);
                tempXPos=200;
            }
                
            if(currentString==strings.D)
            {
                g.drawImage(DString, getX(0), getY(0),getWidth2(),getHeight2(), this);
                tempXPos=275;
            }
                
            else if(currentString==strings.A2)
            {
                g.drawImage(AString, getX(0), getY(0),getWidth2(),getHeight2(), this);
                tempXPos=330;
            }
                
            else if(currentString==strings.E2)
            {
                g.drawImage(EString, getX(0), getY(0),getWidth2(),getHeight2(), this);
                tempXPos=395;
            }
            
            int tempYPos;
            if(note==note.A || note==note.E || note==note.B2 || note==note.F2)
                tempYPos=291;
            else if(note==note.B || note==note.F || note==note.C2 || note==note.G3)
                tempYPos=490;    
            else if(note==note.C || note==note.G2 || note==note.D2 || note==note.A3)
                tempYPos=640;
            else
                tempYPos=100;
                        
           // drawImage(noteIMG, tempXPos-(noteIMG.getWidth(this)/2), tempYPos,noteIMG.getWidth(this),noteIMG.getHeight(this), this);
                        drawImage(noteIMG, tempXPos,tempYPos,0,1,1);

                        g.setColor(Color.BLACK);

                         g.drawString("temp y pos"+tempYPos, 50, 700);

            }
            //g.drawImage(redBar, redBarXPos, 20,800,50, this);
            
            
            g.setColor(gradient);
//            if(redBarXPos<450 && redBarXPos>150)
//                g.setColor(Color.yellow);               
//            else
//                g.setColor(Color.RED);     
//            
            g.fillRect(0, 0, redBarXPos, 100); 
                    
            g.drawImage(bow,  bowXPos, 750,1443,120, this);
            g.setColor(Color.BLACK);
             g.drawString("Bow position"+bowXPos, 50, 200);
             g.drawString("Current note"+note, 50, 100);
             g.drawString("Current string"+currentString, 50, 300);
             g.drawString("Bar"+redBarXPos, 50, 600);

           if(gameOver)
           {
            gameOverIMG = Toolkit.getDefaultToolkit().getImage("./gameOver.PNG");
            g.drawImage(gameOverIMG, getX(0), getY(0),getWidth2(),getHeight2(), this); 
            g.drawImage(escape,getX(getWidth2()/2)-150, getY(getHeight2()/2),300,200, this); 
           }   
           
       }

        gOld.drawImage(image, 0, 0, null);
        
    }



      public  void drawImage(Image image,int xpos,int ypos,double rot,double xscale,double yscale) {
        int width = image.getWidth(this);
        int height = image.getHeight(this);
        g.translate(xpos,ypos);
        g.rotate(rot  * Math.PI/180.0);
        g.scale( xscale , yscale );

        g.drawImage(image,-width/2,-height/2,
        width,height,this);

        g.scale( 1.0/xscale,1.0/yscale );
        g.rotate(-rot  * Math.PI/180.0);
        g.translate(-xpos,-ypos);
    }


////////////////////////////////////////////////////////////////////////////
// needed for     implement runnable
    public void run() {
        while (true) {
            animate();
            repaint();
            double seconds = 0.03;    //time that 1 frame takes.
            int miliseconds = (int) (1000.0 * seconds);
            try {
                Thread.sleep(miliseconds);
            } catch (InterruptedException e) {
            }
        }
    }
/////////////////////////////////////////////////////////////////////////
    public void reset() {

        bowXPos=-800;
        bowDir=0;
        gameOver=false;
           playing = false;
        currentString=strings.D;
        redBarXPos=0;
         gradient = new Color (red,green,blue);
             green=0;
             blue=0;
             red=255;
    }
/////////////////////////////////////////////////////////////////////////
    public void animate() {

        if (animateFirstTime) {
            animateFirstTime = false;
            if (xsize != getSize().width || ysize != getSize().height) {
                xsize = getSize().width;
                ysize = getSize().height;
            }
            
                           
                 background = Toolkit.getDefaultToolkit().getImage("./TviolinHero.PNG");
                 bow = Toolkit.getDefaultToolkit().getImage("./bow.PNG");
                 playButton = Toolkit.getDefaultToolkit().getImage("./playbutton.PNG");
                 logo = Toolkit.getDefaultToolkit().getImage("./violinHero.PNG");
                 escape = Toolkit.getDefaultToolkit().getImage("./Escape.PNG");
                 begin = Toolkit.getDefaultToolkit().getImage("./PressBegin.PNG");
                 GString = Toolkit.getDefaultToolkit().getImage("./GString.PNG");
                 DString = Toolkit.getDefaultToolkit().getImage("./DString.PNG");
                 AString = Toolkit.getDefaultToolkit().getImage("./AString.PNG");
                 EString = Toolkit.getDefaultToolkit().getImage("./EString.PNG");
                 noteIMG = Toolkit.getDefaultToolkit().getImage("./note.PNG");
                 redBar = Toolkit.getDefaultToolkit().getImage("./red.PNG");

            reset();
            
 
          
        }
        
        if(redBarXPos+((bowDir)*7.5)>=0 && redBarXPos+((bowDir)*7.5)<getWidth2())
        {
            redBarXPos=redBarXPos+((bowDir)*(int)(7.75));
            red--;
                    green++;
                    blue++;
        }
       
          if(gameOver)
          {
              bowDir=0;
          }
          if (bowDir==1)
            {
                bowXPos+=10;
            }
            if (bowDir==-1)
            {
                bowXPos-=10;
            }
            if(bowXPos>150)
            {
                gameOver=true;
            }
            if(bowXPos<-800)
            {
                gameOver=true;
            }
            //if(bowXPos)
    }

////////////////////////////////////////////////////////////////////////////
    public void start() {
        if (relaxer == null) {
            relaxer = new Thread(this);
            relaxer.start();
        }
    }
////////////////////////////////////////////////////////////////////////////
    public void stop() {
        if (relaxer.isAlive()) {
            relaxer.stop();
        }
        relaxer = null;
    }
/////////////////////////////////////////////////////////////////////////
    public int getX(int x) {
        return (x);
    }
    public int getY(int y) {
        return (y + TOP_BORDER + YTITLE);
    }

    public int getXNormal(int x) {
        return (x);
    }
    public int getYNormal(int y) {
        return (getY(0) + getHeight2() - y);
    }

    public int getWidth2() {
        return (xsize - getX(0));
    }
    public int getHeight2() {
        return (ysize - getY(0));
    }
}



