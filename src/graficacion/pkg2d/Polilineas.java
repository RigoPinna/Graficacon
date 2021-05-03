package graficacion.pkg2d;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Arc2D;
import java.awt.geom.Line2D;
import java.text.DecimalFormat;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.DefaultTableModel;

public class Polilineas extends javax.swing.JFrame {

    String[] numeros ={"1","2","3","4","5","6","7","8","9","10"};
    String[] vectorP = {"p1","p2","p3","p4","p5","p6","p7"};
    int[] vectorX = {3,1,3,5,7,9,7};
    int[] vectorY = {1,5,5,1,5,5,1};
    int[] pixelX = new int[7];int[] pixelY = new int[7];
    int[] pixeldX = new int[7];int[] pixeldY = new int[7];
    
    double[] pixelPR  = new double[7];
    double[] pixeldPR = new double[7];
    float dashes[]={10};
    int vectorDX[]= new int[7];
    int vectorDY[]= new int[7];
    double vectorPR [] = new double[7];double vectorPA [] = new double[7];
    double vectorPRr[] = new double[7];double vectorPAr[] = new double[7];
    BasicStroke strokeDash = new BasicStroke(2.5f, BasicStroke.CAP_ROUND,BasicStroke.JOIN_ROUND,5, dashes, 0);
    BasicStroke stroke = new BasicStroke(3.0f); 
    
    public Polilineas() {
        initComponents();
        setLocationRelativeTo(null);
        comboCord.setEnabled(false);
        btnGpuntos.setEnabled(false);
        btnSegAbs.setEnabled(false);
        btnSegRel.setEnabled(false);
        setTitle("Figuras Compuestas: Polilineas");
    }
    
    public void DibujarPlano(Graphics g, int s){
        CartRel();
        Graphics2D g2 = (Graphics2D) g;
        g2.setFont( new Font( "Monospaced", Font.BOLD, 18 ));
        g2.setStroke(stroke);
        g2.setColor(Color.BLACK);
        int x=50;int c=0;   int x1=25;int y1=550; int x2=550; int y2=550;
        g2.draw(new Line2D.Float(x1,y1,x2,y2));//Eje principal X (x1, y1, x2, y2)
        g2.setColor(Color.BLACK);
        if(s==1){
        g2.drawString("X",10,555);
        g2.drawString("Y",45,590);
        }
        else if (s==2){
           g2.drawString("dX",10,555);
           g2.drawString("dY",45,590); 
        }
        while (c < 10){
            g2.setColor(Color.BLACK);
            y1=y1-x;y2=y2-x;
            g2.draw(new Line2D.Float(50,y1,x2,y2));
            g2.setColor(Color.BLACK);
            g2.drawString(numeros[c],35,y1);
            c++;
        }c=0;
        x=50;c=0;   x1=50;y1=50;x2=50;y2=550;
        g2.setColor(Color.MAGENTA);
        g2.draw(new Line2D.Float(x1, y1, x2, 575));  //Eje principal Y (x1, y1, x2, y2)
        
        while (c < 10){
            g2.setColor(Color.BLACK);
            x1=x1+x;x2=x2+x;
            g2.draw(new Line2D.Float(x1,50,x2,y2));
            g2.setColor(Color.BLACK);
            g2.drawString(numeros[c],x1,575);
            c++;
        }
    }
    //TABLAS
    public void TablaCartAbsolutas(){
        DefaultTableModel modeloT = new DefaultTableModel();
        tablaPuntos.setModel(modeloT);
        modeloT.addColumn("Puntos");
        modeloT.addColumn("X");
        modeloT.addColumn("Y");
        modeloT.addColumn("dX");
        modeloT.addColumn("dY");
        Object[] columna = new Object[6];
        for(int i=0; i<vectorP.length; i++){
            columna[0] = vectorP[i];
            columna[1] = vectorX[i];
            columna[2] = vectorY[i];
            columna[3] = vectorDX[i];
            columna[4] = vectorDY[i];
            modeloT.addRow(columna);
        }
    }
    public void TablaCartPixeles(){
        DefaultTableModel modeloT = new DefaultTableModel();

        modeloT.addColumn("Puntos");
        modeloT.addColumn("Pixel X");
        modeloT.addColumn("Pixel Y");
        modeloT.addColumn("Pixel dX");
        modeloT.addColumn("Pixel dY");
        Object[] columna = new Object[6];
        for(int i=0; i<vectorP.length; i++){
            columna[0] = vectorP[i];
            columna[1] = pixelX[i];
            columna[2] = pixelY[i];
            columna[3] = pixeldX[i];
            columna[4] = pixeldY[i];
            modeloT.addRow(columna);
        }
         columna[0] = "p'1";
            columna[1] = pixelX[0];
            columna[2] = pixelY[0];
            columna[3] = -300;
            columna[4] = 0;
            modeloT.addRow(columna);
    }
    public void TablaPolarAbs(){
        ConvAPolar();
        ConvPolarRel();
        DecimalFormat df = new DecimalFormat("#0.00");
        DecimalFormat df2 = new DecimalFormat("#0");
        DefaultTableModel modeloT = new DefaultTableModel();
        tablaPuntos.setModel(modeloT);
        modeloT.addColumn("Puntos");
        modeloT.addColumn("r");
        modeloT.addColumn("θ");
        modeloT.addColumn("dr");
        modeloT.addColumn("dθ");
        Object[] columna = new Object[6];
        for(int i=0; i<vectorP.length; i++){
            columna[0] = vectorP[i];
            columna[1] = df.format(vectorPR[i]);
            columna[2] = df.format(vectorPA[i]);
            columna[3] = df.format(vectorPRr[i]);
            columna[4] = df2.format(vectorPAr[i]);
            modeloT.addRow(columna);
        }
        columna[0] = "p'1";
            columna[1] = 6;
            columna[2] = 90;
            columna[3] = 6;
            columna[4] = 180;
            modeloT.addRow(columna);
    }
    
    public void TablaPolarPixeles(){
        DefaultTableModel modeloT = new DefaultTableModel();

        DecimalFormat df = new DecimalFormat("#0.00");
        DecimalFormat df2 = new DecimalFormat("#0");
        modeloT.addColumn("Puntos");
        modeloT.addColumn("Pixel r");
        modeloT.addColumn("Pixel θ");
        modeloT.addColumn("Pixel dr");
        modeloT.addColumn("Pixel dθ");
        Object[] columna = new Object[6];
        for(int i=0; i<vectorP.length; i++){
            columna[0] = vectorP[i];
            columna[1] = df.format(pixelPR[i]);
            columna[2] = df.format(vectorPA[i]);
            columna[3] = df.format(pixeldPR[i]);
            columna[4] = df2.format(vectorPAr[i]);
            modeloT.addRow(columna);
        }
        columna[0] = "p'1";
            columna[1] = df.format(pixelPR[0]);
            columna[2] = df.format(vectorPA[0]);
            columna[3] = 300;
            columna[4] = 180;
            modeloT.addRow(columna);
    }
    //CONVERSIONES
    public void ConvAPolar(){
        int x,y;
        for (int i = 0; i < vectorX.length; i++) {
            x = vectorX[i];y = vectorY[i];
            vectorPR[i] = Math.sqrt(Math.pow(x, 2)+Math.pow(y, 2));
        }
        for (int j = 0; j < vectorX.length; j++) {
            x = vectorX[j];y = vectorY[j];
            vectorPA[j] = Math.toDegrees(Math.atan2(y, x));
        }
    }
    public void ConvPolarRel(){
        int x,y;
        for (int i = 0; i < vectorDX.length; i++) {
            x=vectorDX[i];y=vectorDY[i];
            vectorPRr[i]= Math.sqrt(Math.pow(x, 2)+Math.pow(y, 2));
        }
        for (int j = 0; j < vectorDX.length; j++) {
            x=vectorDX[j];y=vectorDY[j];
            vectorPAr[j] = Math.toDegrees(Math.atan2(y, x));
        }
    }
    public void ConvCartRel(){
        int contX=0;int contY=0;
        for (int j = 0; j < vectorDX.length; j++) {
            vectorDX[j]=vectorX[j]-contX;
            contX=vectorX[j];
            vectorDY[j]=vectorY[j]-contY;
            contY=vectorY[j];
        }
    }
    
    public void PuntosCartAbs(Graphics g){
        Graphics2D g2 = (Graphics2D) g;
        g2.setColor(Color.black);
        int x1=50;  int y1=550; //coor origen
        int aux;
        for (int i = 0; i < vectorP.length; i++) {
            aux=i;
            g2.setColor(Color.black);
            int x =(x1+((vectorX[i]*50)-5));
            int y =(y1-((vectorY[i]*50)+5));
            pixelX[i]=(x1+((vectorX[i]*50)));
            pixelY[i]=(y1-((vectorY[i]*50)));
            pixelPR[i] = (x1+((vectorPR[i]*50)));
            pixeldPR[i] = (x1+((vectorPRr[i]*50)));
            
            g2.fillOval(x, y, 10, 10);
            g2.setColor(Color.RED);
            g2.setFont( new Font( "Monospaced", Font.BOLD, 18 ));
            if (aux+1 <= 5) {
                g2.drawString(vectorP[i], x-20, y+20);
            }else{
                g2.drawString(vectorP[i], x+10, y+20);
            }
        }
        
    }
    
    public void nombreDistancias (Graphics g){
         Graphics2D g2 = (Graphics2D) g;
        BasicStroke stroke = new BasicStroke(2.5f, BasicStroke.CAP_ROUND,BasicStroke.JOIN_ROUND,5, dashes, 0);
        g2.setStroke(stroke);
        String dxy []  = {"dx1", "dy2", "dy3", "dy4", "dy5","dy6","dy7"};
        /*int cX [] = {105, 160, 225, 225,105};
        int cY [] = {325, 290, 385, 430,525};*/
        int cX [] = {160, 105, 160,315,470,625,470};
        int cY [] = {81, 325,325,81,325,325,81};
        g2.setColor(Color.BLACK);
        g2.setFont( new Font( "Monospaced", Font.BOLD, 18 ));
        for (int j=0; j<dxy.length; j++){
            g2.drawString(dxy[j], cX [j], cY[j]);
        }
        int a=5; int x1=50;  int y1=550; //coor origen
        Color DarkGreen = new Color(255, 116, 0);
        g2.setColor(DarkGreen);
        for (int i = 0; i < vectorP.length; i++) {
            int x =(x1+((vectorDX[i]*50)));
            int y =(y1-((vectorDY[i]*50)));
            g2.draw(new Line2D.Float(x1,y1,x,y));
            x1=x;y1=y;
            try {
                Thread.sleep(250);
            } catch (InterruptedException ex) {
                Logger.getLogger(Puntos.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    public void nombreDistanciasR (Graphics g){
        Graphics2D g2 = (Graphics2D) g;
        g2.setColor(Color.black);
        String [] nombres = {"dr2", "dr3", "dr4", "dr5", "dr6", "dr7", "dr8", "dr'1"};
        String [] nombres2 = {"dθ2", "dθ3", "dθ4", "dθ5", "dθ6", "dθ7", "dθ8", "dθ'1"};
        g2.setFont( new Font( "Monospaced", Font.BOLD, 18 ));
        //radios
        g2.drawString(nombres[0], 55, 350);
        g2.drawString(nombres[1], 100, 245);
        g2.drawString(nombres[2], 155, 300);
        g2.drawString(nombres[3], 300, 345);
        g2.drawString(nombres[4], 455, 450);
        g2.drawString(nombres[5], 400, 545);
        g2.drawString(nombres[6], 340, 500);
        g2.drawString(nombres[7], 210, 445);
        //angulos
        g2.drawString(nombres2[0], 70, 375);
        //g2.drawString(nombres2[1], 170, 280);
        g2.drawString(nombres2[2], 170, 280);
        //g2.drawString(nombres2[3], 455, 345);
        g2.drawString(nombres2[4], 470, 400);
        g2.drawString(nombres2[5], 425, 530);
        g2.drawString(nombres2[6], 340, 525);
        g2.drawString(nombres2[7], 300, 445);
        g2.drawString("dr1", 60, 500);
        g2.drawString("dθ1", 150, 540);
    }
        
    public void nombrePolaresA(Graphics g){
        Color naranja = new Color (207, 142, 242);
        Graphics2D g2 = (Graphics2D) g;
        g2.setColor(naranja);
        String [] nombres = {"r1", "r2", "r3", "r4", "r5","r6", "r7"};
        String [] nombres2 = {"θ1", "θ2", "θ3", "θ4","θ5","θ6","θ7"};
        g2.setFont( new Font( "Monospaced", Font.BOLD, 18 ));
        //radios
        g2.drawString(nombres[0], 55, 445);
        g2.drawString(nombres[1], 55, 245);
        g2.drawString(nombres[2], 155, 245);
        g2.drawString(nombres[3], 155, 345);
        g2.drawString(nombres[4], 455, 345);
        //angulos
        g2.drawString(nombres2[0], 125, 545);
        g2.drawString(nombres2[1], 200,545);
        g2.drawString(nombres2[2], 225, 545);
        g2.drawString(nombres2[3], 250, 545);
        g2.drawString(nombres2[4], 275, 545);
        //g2.drawString(nombres2[5], 425, 530);
        //g2.drawString(nombres2[6], 340, 525);
        //g2.drawString(nombres2[7], 350, 530);
        /*Graphics2D g2 = (Graphics2D) g;
        BasicStroke stroke = new BasicStroke(2, BasicStroke.CAP_ROUND,BasicStroke.JOIN_ROUND,5, dashes, 0);
        g2.setStroke(stroke);
        int x1=50;  int y1=550; //coor origen
        g2.setColor(Color.YELLOW);
        int dx = 125 , vx=0, dy = 125, vy=0, aux;
        String radios [] = {"r1", "r2", "r3", "r4", "r5", "r6", "r7", "r8", "r9"};
        String ang [] = {"θ1", "θ2", "θ3", "θ4", "θ5", "θ6", "θ7", "θ8", "θ9"};
        int teta [] = {175, 200, 225, 250, 275, 300, 325, 350, 375};
        for (int i = 0; i <vectorP.length ; i++) {
            aux=i;
            int x =(x1+((vectorX[i]*50)-5));
            int y =(y1-((vectorY[i]*50)+5));
            g2.setColor(Color.CYAN);
            g2.draw(new Arc2D.Double((x1-dx), (y1-dy), vx+250, vy+250, 0, vectorPA[i], Arc2D.PIE));
            System.out.println("arc"+(i+1)+" "+dx+"/"+dy+"/"+vx+"/"+vy);
            dx+=25;
            dy+=25;
            vx+=50;
            vy+=50;
            int xC =(x1+((vectorX[i]*50)-5));
            int yC =(y1-((vectorY[i]*50)+5));
            g2.setColor(Color.RED);
            g2.setFont( new Font( "Monospaced", Font.BOLD, 18 ));
            if (aux+1 <= 9) {
                g2.drawString(radios[i], x-20, y+5);
            }else{
                g2.drawString(radios[i], x-28, y+33);
            }
            g2.drawString(ang[i], teta[i], y1-10);
            try {
                Thread.sleep(250);
            } catch (InterruptedException ex) {
                Logger.getLogger(Puntos.class.getName()).log(Level.SEVERE, null, ex);
            }
            g2.setColor(Color.YELLOW);
            g2.draw(new Line2D.Float(x1,y1,x+5,y+5));
            try {
                Thread.sleep(250);
            } catch (InterruptedException ex) {
                Logger.getLogger(Puntos.class.getName()).log(Level.SEVERE, null, ex);
            }
        }*/
        }
    
    public void limpiarNomC (Graphics g){
        Color repaint = new Color(102, 102, 102);
        g.setColor(repaint);
        g.fillRect(25, 35, 550, 550);
       
        DibujarPlano(jpPlano.getGraphics(), 1);
    }
    public void lineasPCartR(Graphics g){
        
        Color verde = new Color (21, 255, 21);
        Graphics2D g2 = (Graphics2D) g;
        BasicStroke strokeN = new BasicStroke(2.5f);
        g2.setStroke(strokeN);
        int x1=50;  int y1=550; //coor origen
        g2.setColor(verde);
        //g2.draw(new Line2D.Float(50,550,50,500));
        for (int i = 0; i < vectorP.length; i++) {
            if(i==0){
                int x =(x1+((vectorDX[i]*50)));
                int y =(y1-((vectorDY[i]*50)));
                //g2.draw(new Line2D.Float(x1,450,x,y));
                x1=x;y1=y;
            } else{
            int x =(x1+((vectorDX[i]*50)));
            int y =(y1-((vectorDY[i]*50)));
            g2.draw(new Line2D.Float(x1,y1,x,y));
            x1=x;y1=y;}
        }
    }
    
    public void CartRel(){
        int x1=50;  int y1=450; //coor origen
        int contX=0;int contY=0;
        for (int j = 0; j < vectorDX.length; j++) {
            vectorDX[j]=vectorX[j]-contX;
            contX=vectorX[j];
            vectorDY[j]=vectorY[j]-contY;
            contY=vectorY[j];
        }
        int x,y;
        for (int i = 0; i < vectorP.length; i++) {
            if(i == 0){
                x = x1;y = y1;
            }else{
                x =(((vectorDX[i]*50)));
                y =(((vectorDY[i]*50)));
            }
            pixeldX[i]=x;
            pixeldY[i]=y;
            
        }
    }
    
    //SEGMENTOS
    public void SegCartAbs(Graphics g){
        Color naranja = new Color (255,87,6);
        Graphics2D g2 = (Graphics2D) g;
        g2.setStroke(strokeDash);
        int x1=50;  int y1=550; //coor origen
        g2.setColor(naranja);
        for (int i = 0; i < vectorP.length; i++) {
            int x =(x1+((vectorX[i]*50)-5));
            int y =(y1-((vectorY[i]*50)+5));
            g2.draw(new Line2D.Float(x1,y1,x+5,y+5));
            try {
                Thread.sleep(250);
            } catch (InterruptedException ex) {
                Logger.getLogger(Puntos.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    public void SegCartRel(Graphics g){
        Graphics2D g2 = (Graphics2D) g;
        g2.setStroke(strokeDash);
        
        int x1=50;  int y1=550; //coor origen
        Color DarkGreen = new Color(255, 0, 128);
        g2.setColor(Color.RED);
        for (int i = 0; i < vectorP.length; i++) {
            int x =(x1+((vectorDX[i]*50)));
            int y =(y1-((vectorDY[i]*50)));
            g2.draw(new Line2D.Float(x1,y1,x,y));
            x1=x;y1=y;
            try {
                Thread.sleep(250);
            } catch (InterruptedException ex) {
                Logger.getLogger(Puntos.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    public void titulo (Graphics g, int valor){
        Graphics2D g2 = (Graphics2D) g;
        g2.setStroke(strokeDash);
        g2.setColor(Color.WHITE);
        g2.setFont( new Font( "Monospaced", Font.BOLD, 24 ));
        if (valor==1) g2.drawString("Cartesianas Absolutas", 200, 25);
        else if (valor==2)g2.drawString("Cartesianas Relativas", 200, 25);
        else if (valor==3) g2.drawString("Polares Absolutas", 200, 25);
        else if (valor==4) g2.drawString("Polares Relativas", 200, 25);
    }
    public void SegPolarAbs(Graphics g){
        /*int angulos []= {90, 90, 72, 63,27, 0, 0, 18 };
        Color aqua = new Color (0,128,192);
        Graphics2D g2 = (Graphics2D) g;
        g2.setStroke(strokeDash);
        int x1=50;  int y1=550; //coor origen
        g2.setColor(Color.RED);
        int cont =0 ;
            int dx = 125 , vx=0, dy = 125, vy=0;
        for (int i = 0; i <vectorP.length ; i++) {
            int x =(x1+((vectorX[i]*50)-5));
            int y =(y1-((vectorY[i]*50)+5));
            g2.setColor(Color.BLACK);
            g2.draw(new Arc2D.Double((x1-dx), (y1-dy), vx+250, vy+250, 0, angulos[i], Arc2D.PIE));
            dx+=25;
            dy+=25;
            vx+=50;
            vy+=50;
            
            //g2.drawArc((x1-125), (y1-125), 250+cont, 250-cont, 0, angulos[i]);
            
            cont+=20;
            try {
                Thread.sleep(250);
            } catch (InterruptedException ex) {
                Logger.getLogger(Puntos.class.getName()).log(Level.SEVERE, null, ex);
            }
            g2.setColor(Color.RED);
            g2.draw(new Line2D.Float(x1,y1,x+5,y+5));
            try {
                Thread.sleep(250);
            } catch (InterruptedException ex) {
                Logger.getLogger(Puntos.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        }*/
        Graphics2D g2 = (Graphics2D) g;
        BasicStroke stroke = new BasicStroke(2, BasicStroke.CAP_ROUND,BasicStroke.JOIN_ROUND,5, dashes, 0);
        g2.setStroke(stroke);
        int x1=50;  int y1=550; //coor origen
        g2.setColor(Color.YELLOW);
        int dx = 125 , vx=0, dy = 125, vy=0, aux;
        String radios [] = {"r1", "r2", "r3", "r4", "r5","r6", "r7"};
        String ang [] = {"θ1", "θ2", "θ3", "θ4", "θ5","θ6", "θ7"};
        int teta [] = {175, 200, 225, 250, 275, 300, 325, 350, 375};
        for (int i = 0; i <vectorP.length ; i++) {
            aux=i;
            int x =(x1+((vectorX[i]*50)-5));
            int y =(y1-((vectorY[i]*50)+5));
            g2.setColor(Color.CYAN);
            g2.draw(new Arc2D.Double((x1-dx), (y1-dy), vx+250, vy+250, 0, vectorPA[i], Arc2D.PIE));
            System.out.println("arc"+(i+1)+" "+dx+"/"+dy+"/"+vx+"/"+vy);
            dx+=25;
            dy+=25;
            vx+=50;
            vy+=50;
            int xC =(x1+((vectorX[i]*50)-5));
            int yC =(y1-((vectorY[i]*50)+5));
            g2.setColor(Color.RED);
            g2.setFont( new Font( "Monospaced", Font.BOLD, 18 ));
            if (aux+1 <= 9) {
                g2.drawString(radios[i], x-20, y+5);
            }else{
                g2.drawString(radios[i], x-28, y+33);
            }
            g2.drawString(ang[i], teta[i], y1-10);
            try {
                Thread.sleep(250);
            } catch (InterruptedException ex) {
                Logger.getLogger(Puntos.class.getName()).log(Level.SEVERE, null, ex);
            }
            g2.setColor(Color.YELLOW);
            g2.draw(new Line2D.Float(x1,y1,x+5,y+5));
            try {
                Thread.sleep(250);
            } catch (InterruptedException ex) {
                Logger.getLogger(Puntos.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    public void SegPolarRel(Graphics g){
       /* Color aqua = new Color (0,128,192);
        Graphics2D g2 = (Graphics2D) g;
        g2.setStroke(strokeDash);
        int x1=50;  int y1=550; //coor origen
        Color DarkGreen = new Color(255, 0, 120);
        for (int i = 0; i < vectorP.length; i++) {
            int x =(x1+((vectorDX[i]*50)));
            int y =(y1-((vectorDY[i]*50)));
            g2.setColor(aqua);
            g2.draw(new Arc2D.Double((x1-125), (y1-125), 250, 250, 0, vectorPAr[i], Arc2D.PIE));
            try {
                Thread.sleep(250);
            } catch (InterruptedException ex) {
                Logger.getLogger(Puntos.class.getName()).log(Level.SEVERE, null, ex);
            }
            g2.setColor(DarkGreen);
            g2.draw(new Line2D.Float(x1,y1,x,y));
            x1=x;y1=y;
            try {
                Thread.sleep(250);
            } catch (InterruptedException ex) {
                Logger.getLogger(Puntos.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        }
        g2.setColor(DarkGreen);
        g2.draw(new Line2D.Float(350,450,50,450));
        g2.setColor(aqua);
        g2.draw(new Arc2D.Double((x1-125), (y1-125), 250, 250, 0, 180, Arc2D.PIE));*/
       Graphics2D g2 = (Graphics2D) g;
        BasicStroke stroke = new BasicStroke(2.5f, BasicStroke.CAP_ROUND,BasicStroke.JOIN_ROUND,5, dashes, 0);
        g2.setStroke(stroke);
        int x1=50;  int y1=550, aux; //coor origen
        Color DarkGreen = new Color(255, 116, 0);
        String radios [] = {"dr1", "dr2", "dr3", "dr4", "dr5","dr6", "dr7"};
        String ang [] = {"dθ1", "dθ2", "dθ3", "dθ4","dθ5", "dθ6", "dθ7",""};
        int cX [] = {135, 390,315, 400};
        int cY [] = {450,350,450,450};
        for (int i = 0; i < vectorP.length; i++) {
            aux=i;
            int x =(x1+((vectorDX[i]*50)));
            int y =(y1-((vectorDY[i]*50)));
            g2.setColor(Color.CYAN);
            g2.draw(new Arc2D.Double((x1-125), (y1-125), 250, 250, 0, vectorPAr[i], Arc2D.PIE));
            
            g2.setColor(Color.RED);
            g2.setFont( new Font( "Monospaced", Font.BOLD, 18 ));
            if (aux+1 <= 4) {
                g2.drawString(radios[i], x-20, y);
            }else{
                g2.drawString(radios[i], x-28, y+28);
            }
            //g2.drawString(ang[i], cX[i], cY[i]);
            try {
                Thread.sleep(250);
            } catch (InterruptedException ex) {
                Logger.getLogger(Puntos.class.getName()).log(Level.SEVERE, null, ex);
            }
            g2.setColor(Color.GREEN);
            g2.draw(new Line2D.Float(x1,y1,x,y));
            x1=x;y1=y;
            try {
                Thread.sleep(250);
            } catch (InterruptedException ex) {
                Logger.getLogger(Puntos.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
            
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jpPlano = new javax.swing.JPanel();
        jlTitulo = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        comboCord = new javax.swing.JComboBox<>();
        btnGraficar = new javax.swing.JButton();
        btnGpuntos = new javax.swing.JButton();
        btnSegAbs = new javax.swing.JButton();
        btnSegRel = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tablaPuntos = new javax.swing.JTable();
        jlUno = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jpPlano.setBackground(new java.awt.Color(204, 204, 204));
        jpPlano.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jpPlano.setPreferredSize(new java.awt.Dimension(500, 500));

        jlTitulo.setBackground(new java.awt.Color(255, 255, 255));
        jlTitulo.setFont(new java.awt.Font("Dialog", 0, 24)); // NOI18N
        jlTitulo.setForeground(new java.awt.Color(255, 0, 0));
        jlTitulo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        javax.swing.GroupLayout jpPlanoLayout = new javax.swing.GroupLayout(jpPlano);
        jpPlano.setLayout(jpPlanoLayout);
        jpPlanoLayout.setHorizontalGroup(
            jpPlanoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jlTitulo, javax.swing.GroupLayout.DEFAULT_SIZE, 596, Short.MAX_VALUE)
        );
        jpPlanoLayout.setVerticalGroup(
            jpPlanoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpPlanoLayout.createSequentialGroup()
                .addComponent(jlTitulo, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(572, Short.MAX_VALUE))
        );

        getContentPane().add(jpPlano, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 100, 600, 610));

        jPanel1.setBackground(new java.awt.Color(204, 255, 204));

        comboCord.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        comboCord.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Coordenadas Cartesianas ", "Coordenadas Polares" }));
        comboCord.setToolTipText("Seleccionar Opcion");

        btnGraficar.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnGraficar.setText("Graficar Plano");
        btnGraficar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnGraficar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGraficarActionPerformed(evt);
            }
        });

        btnGpuntos.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        btnGpuntos.setText("Graficar Figura");
        btnGpuntos.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnGpuntos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGpuntosActionPerformed(evt);
            }
        });

        btnSegAbs.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        btnSegAbs.setText("Segmentos Absolutos");
        btnSegAbs.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSegAbsActionPerformed(evt);
            }
        });

        btnSegRel.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        btnSegRel.setText("Segmentos Relativos");
        btnSegRel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSegRelActionPerformed(evt);
            }
        });

        tablaPuntos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Punto", "X", "Y", "dX", "dY"
            }
        ));
        jScrollPane1.setViewportView(tablaPuntos);

        jlUno.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        jlUno.setText("Coordenadas");

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setText(" Poli-Lineas");

        jButton1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jButton1.setText("Menú");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnGraficar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(btnSegAbs)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 7, Short.MAX_VALUE)
                        .addComponent(btnSegRel, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(comboCord, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnGpuntos, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 309, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jlUno, javax.swing.GroupLayout.PREFERRED_SIZE, 309, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(24, 24, 24)
                .addComponent(btnGraficar, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(comboCord, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnGpuntos, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnSegAbs, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnSegRel, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(16, 16, 16)
                .addComponent(jlUno)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(225, Short.MAX_VALUE))
        );

        comboCord.getAccessibleContext().setAccessibleName("Seleccionar Opcion");

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 100, 330, 610));

        jPanel2.setBackground(new java.awt.Color(204, 255, 204));

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("Graficación Básica por Computadora");

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("Figuras Geométricas: Compuestas");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 930, Short.MAX_VALUE)
            .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 36, Short.MAX_VALUE)
                .addComponent(jLabel3)
                .addGap(20, 20, 20))
        );

        getContentPane().add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 930, -1));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnGraficarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGraficarActionPerformed
        jlTitulo.setText(".");
        DibujarPlano(jpPlano.getGraphics(), 1);
        comboCord.setEnabled(true);
        btnGpuntos.setEnabled(true);
        CartRel();
        ConvAPolar();
        ConvPolarRel();
    }//GEN-LAST:event_btnGraficarActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        dispose();
        Menu frame = new Menu();
        frame.setVisible(rootPaneCheckingEnabled);
    }//GEN-LAST:event_jButton1ActionPerformed

    private void btnGpuntosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGpuntosActionPerformed
        int cord = comboCord.getSelectedIndex();
        Graphics plano = jpPlano.getGraphics();
        limpiarPuntos(plano,1);
        PuntosCartAbs(plano);
        lineasPCartR(plano);
        switch(cord){
            case 0:
                jlUno.setText("Cartesianas Absolutas y Relativas");

               // jlTitulo.setText("Cartesianas Absolutas y Relativas");
                TablaCartAbsolutas();
                TablaCartPixeles();
                btnSegAbs.setEnabled(true);
                btnSegRel.setEnabled(true);
            break;
            case 1:
                jlUno.setText("Polares Absolutas y Relativas");
                //jlTitulo.setText("Polares Absolutas y Relativas");
                TablaPolarAbs();
                TablaPolarPixeles();
                btnSegAbs.setEnabled(true);
                btnSegRel.setEnabled(true);
        }
    }//GEN-LAST:event_btnGpuntosActionPerformed
    public void limpiarPuntos(Graphics g, int s){
        Color repaint = new Color(102, 102, 102);
        g.setColor(repaint);
        g.fillRect(0, 0, 600, 700);
        if(s==1) DibujarPlano(jpPlano.getGraphics(), 1);
        else if (s==2) DibujarPlano(jpPlano.getGraphics(), 2);
    }
    private void btnSegAbsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSegAbsActionPerformed
        int cord = comboCord.getSelectedIndex();
        Graphics plano = jpPlano.getGraphics();
        limpiarPuntos(plano, 1);
        PuntosCartAbs(plano);
        switch(cord){
            case 0:
                titulo(plano, 1);
               // jlTitulo.setText("Cartesianas Absolutas");
                lineasPCartR(plano);
                SegCartAbs(plano);
                btnSegAbs.setEnabled(false);
            break;
            case 1:
               
                titulo(plano, 3);
                //jlTitulo.setText("Polares Absolutas");
                //nombrePolaresA(plano);
                lineasPCartR(plano);
                SegPolarAbs(plano);
                btnSegAbs.setEnabled(false);
            break;
        }
    }//GEN-LAST:event_btnSegAbsActionPerformed

    private void btnSegRelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSegRelActionPerformed
        int cord = comboCord.getSelectedIndex();
        Graphics plano = jpPlano.getGraphics();
        limpiarPuntos(plano, 2);
        lineasPCartR(plano);
        PuntosCartAbs(plano);
        if(cord==0) titulo(plano, 2);
        else if (cord==1) titulo(plano,4);
        switch(cord){
            case 0:
                
                titulo(plano, 2);
               // jlTitulo.setText("Cartesianas Relativas");
                nombreDistancias (plano);
                SegCartRel(plano);
                btnSegRel.setEnabled(false);
            break;
            case 1:
                
                titulo(plano, 4);
               // jlTitulo.setText("Polares Relativas");
                //nombreDistanciasR(plano);
                SegPolarRel(plano);
                btnSegRel.setEnabled(false);
            break;
        }
    }//GEN-LAST:event_btnSegRelActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Polilineas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Polilineas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Polilineas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Polilineas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Polilineas().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnGpuntos;
    private javax.swing.JButton btnGraficar;
    private javax.swing.JButton btnSegAbs;
    private javax.swing.JButton btnSegRel;
    private javax.swing.JComboBox<String> comboCord;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel jlTitulo;
    private javax.swing.JLabel jlUno;
    private javax.swing.JPanel jpPlano;
    private javax.swing.JTable tablaPuntos;
    // End of variables declaration//GEN-END:variables
}
