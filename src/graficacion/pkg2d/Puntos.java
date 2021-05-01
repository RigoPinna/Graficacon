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

public class Puntos extends javax.swing.JFrame {
        String[] vectorP = {"p1","p2","p3","p4","p5","p6","p7"};
        int[] vectorX = {3,1,3,5,7,10,7};
        int[] vectorY = {1,5,5,1,5,5,1};
        String[] numeros ={"1","2","3","4","5","6","7","8","9","10"};
        int vectorDX[]= new int[7];
        int vectorDY[]= new int[7];
        double vectorPR[] = new double[7];
        double vectorPA[] = new double[7];
        double vectorPRr[] = new double[7];
        double vectorPAr[] = new double[7];
        float dashes[]={10};
        
    public Puntos() {
        initComponents();
        setLocationRelativeTo(null);
        comboCord.setEnabled(false);
        btnGraficar.setEnabled(false);
        btnSegmentos.setEnabled(false);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setTitle("Figuras Compuestas: Letra F con Puntos");
    }
    
    public void DibujarPlano(Graphics g, int s){
        jlTitulo.setText("Plano Cartesiano");
        comboCord.setEnabled(true);
        btnGraficar.setEnabled(true);
        Graphics2D g2 = (Graphics2D) g;
        g2.setFont( new Font( "Monospaced", Font.BOLD, 18 ));
        BasicStroke stroke = new BasicStroke(3.0f); 
        g2.setStroke(stroke);
        g2.setColor(Color.BLACK);
        if (s==1){
          g2.drawString("X",10,555);
          g2.drawString("Y",45,590);  
        }
        else if (s==2){
            g2.drawString("dX",10,555);
            g2.drawString("dY",45,590);
        }
        g2.setColor(Color.MAGENTA);
        int x=50;int c=0;   int x1=25;int y1=550; int x2=550; int y2=550;
        g2.draw(new Line2D.Float(x1,y1,x2,y2));//Eje principal X (x1, y1, x2, y2)
        while (c < 10){
            g2.setColor(Color.WHITE);
            y1=y1-x;y2=y2-x;
            g2.draw(new Line2D.Float(50,y1,x2,y2));
            g2.setColor(Color.BLACK);
            g2.drawString(numeros[c],35,y1);
            c++;
        }c=0;
        g2.setColor(Color.MAGENTA);
        x=50;c=0;   x1=50;y1=50;x2=50;y2=550;
        g2.draw(new Line2D.Float(x1, y1, x2, 575));  //Eje principal Y (x1, y1, x2, y2)
        
        while (c < 10){
            g2.setColor(Color.WHITE);
            x1=x1+x;x2=x2+x;
            g2.draw(new Line2D.Float(x1,50,x2,y2));
            g2.setColor(Color.BLACK);
            g2.drawString(numeros[c],x1,575);
            c++;
        }
    }
    //Tablas
    public void TablaCartesAbs(){
        DefaultTableModel modeloT = new DefaultTableModel();
        tablaPuntos.setModel(modeloT);
        modeloT.addColumn("Puntos");
        modeloT.addColumn("X");
        modeloT.addColumn("Y");
        Object[] columna = new Object[3];
        for(int i=0; i<vectorP.length; i++){
            columna[0] = vectorP[i];
            columna[1] = vectorX[i];
            columna[2] = vectorY[i];
            modeloT.addRow(columna);
        }
    }
    public void TablaCartesRel(){
        DefaultTableModel modeloT = new DefaultTableModel();
        tablaPuntos.setModel(modeloT);
        modeloT.addColumn("Puntos");
        modeloT.addColumn("dX");
        modeloT.addColumn("dY");
        Object[] columna = new Object[3];
        for(int i=0; i<vectorP.length; i++){
            columna[0] = vectorP[i];
            columna[1] = vectorDX[i];
            columna[2] = vectorDY[i];
            modeloT.addRow(columna);
        }
    }
    public void TablaPolarAbs(){
        ConvAPolar();
        DecimalFormat df = new DecimalFormat("#.00");
        DefaultTableModel modeloT = new DefaultTableModel();
        tablaPuntos.setModel(modeloT);
        modeloT.addColumn("Puntos");
        modeloT.addColumn("r");
        modeloT.addColumn("θ");
        Object[] columna = new Object[3];
        for(int i=0; i<vectorP.length; i++){
            columna[0] = vectorP[i];
            columna[1] = df.format(vectorPR[i]);
            columna[2] = df.format(vectorPA[i]);
            modeloT.addRow(columna);
        }
    }
    public void TablaPolarRel(){
        ConvPolarRel();
        DecimalFormat df = new DecimalFormat("#.00");
        DecimalFormat df2 = new DecimalFormat("#0");
        DefaultTableModel modeloT = new DefaultTableModel();
        tablaPuntos.setModel(modeloT);
        modeloT.addColumn("Puntos");
        modeloT.addColumn("dr");
        modeloT.addColumn("dθ");
        Object[] columna = new Object[3];
        for(int i=0; i<vectorP.length; i++){
            columna[0] = vectorP[i];
            columna[1] = df.format(vectorPRr[i]);
            columna[2] = df2.format(vectorPAr[i]);
            modeloT.addRow(columna);
        }
    }
    public void ConvAPolar(){
        int x,y;
        for (int i = 0; i < vectorX.length; i++) {
            x=vectorX[i];y=vectorY[i];
            vectorPR[i]= Math.sqrt(Math.pow(x, 2)+Math.pow(y, 2));
        }
        for (int j = 0; j < vectorX.length; j++) {
            x=vectorX[j];y=vectorY[j];
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
    public void CartRel(){
        int contX=0;int contY=0;
        for (int j = 0; j < vectorDX.length; j++) {
            vectorDX[j]=vectorX[j]-contX;
            contX=vectorX[j];
            vectorDY[j]=vectorY[j]-contY;
            contY=vectorY[j];
        }
    }
    //Puntos
    public void PuntosCartAbs(Graphics g){
        Graphics2D g2 = (Graphics2D) g;
        g2.setColor(Color.ORANGE);
        int x1=50;  int y1=550; //coor origen
        int aux;
        for (int i = 0; i < vectorP.length; i++) {
            aux=i;
            g2.setColor(Color.ORANGE);
            int x =(x1+((vectorX[i]*50)-5));
            int y =(y1-((vectorY[i]*50)+5));
            g2.fillOval(x, y, 10, 10);
            g2.setColor(Color.BLACK);
            g2.setFont( new Font( "Monospaced", Font.BOLD, 18 ));
            if (aux+1 <= 9) {
                g2.drawString(vectorP[i], x-20, y+20);
            }else{
                g2.drawString(vectorP[i], x-28, y+28);
            }
        }
    }
    public void CartRel(Graphics g){
        jlTitulo.setText("Cartesianas Relativas");
        Graphics2D g2 = (Graphics2D) g;
        g2.setColor(Color.black);
        int x1=50;  int y1=550; //coor origen
        int aux;
        int contX=0;int contY=0;
        for (int j = 0; j < vectorDX.length; j++) {
            vectorDX[j]=vectorX[j]-contX;
            contX=vectorX[j];
            vectorDY[j]=vectorY[j]-contY;
            contY=vectorY[j];
        }
        for (int i = 0; i < vectorP.length; i++) {
            aux=i;
            g2.setColor(Color.black);
            int x =(x1+((vectorDX[i]*50)));
            int y =(y1-((vectorDY[i]*50)));
            g2.fillOval(x-5, y-5, 10, 10);
            g2.setColor(Color.ORANGE);
            g2.setFont( new Font( "Monospaced", Font.BOLD, 18 ));
            if (aux+1 <= 9) {
                g2.drawString(vectorP[i], x-25, y+20);
            }else{
                g2.drawString(vectorP[i], x-35, y+20);
            }
            x1=x;y1=y;
        }
    }
    
    //Dibujar Segmentos
    public void lineasPCartA(Graphics g){
        Graphics2D g2 = (Graphics2D) g;
        BasicStroke stroke = new BasicStroke(2, BasicStroke.CAP_ROUND,BasicStroke.JOIN_ROUND,5, dashes, 0);
        g2.setStroke(stroke);
        int x1=50;  int y1=550; //coor origen
        g2.setColor(Color.MAGENTA);
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
        //btnSegmentos.setEnabled(false);
    }
    public void lineasPCartR(Graphics g){
        Graphics2D g2 = (Graphics2D) g;
        BasicStroke stroke = new BasicStroke(2.5f, BasicStroke.CAP_ROUND,BasicStroke.JOIN_ROUND,5, dashes, 0);
        g2.setStroke(stroke);
        String dxy []  = {"dx1", "dy2", "dy3", "dy4", "dy5"};
        int cX [] = {105, 160, 225, 225,105};
        int cY [] = {325, 290, 385, 430,525};
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
        //btnSegmentos.setEnabled(false);
    }
    public void lineasPolaresAbs(Graphics g){
        Graphics2D g2 = (Graphics2D) g;
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
        }
        //btnSegmentos.setEnabled(false);
    }
    public void lineasPolaresRel(Graphics g){
        Graphics2D g2 = (Graphics2D) g;
        BasicStroke stroke = new BasicStroke(2.5f, BasicStroke.CAP_ROUND,BasicStroke.JOIN_ROUND,5, dashes, 0);
        g2.setStroke(stroke);
        int x1=50;  int y1=550, aux; //coor origen
        Color DarkGreen = new Color(255, 116, 0);
        String radios [] = {"dr1", "dr2", "dr3", "dr4", "dr5","dr6","dr7"};
        String ang [] = {"dθ1", "dθ2", "dθ3", "dθ4","d05","dθ6","dθ7",""};
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
        //btnSegmentos.setEnabled(false);
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jpPlano = new javax.swing.JPanel();
        jlTitulo = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        comboCord = new javax.swing.JComboBox<>();
        btnDibujarPlano = new javax.swing.JButton();
        btnGraficar = new javax.swing.JButton();
        btnSegmentos = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tablaPuntos = new javax.swing.JTable();
        jPanel2 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(255, 255, 255));
        setSize(new java.awt.Dimension(0, 0));
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jpPlano.setBackground(new java.awt.Color(204, 204, 204));
        jpPlano.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jpPlano.setPreferredSize(new java.awt.Dimension(500, 500));

        jlTitulo.setFont(new java.awt.Font("Dialog", 0, 24)); // NOI18N
        jlTitulo.setForeground(new java.awt.Color(102, 0, 102));
        jlTitulo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jlTitulo.setText(".");

        javax.swing.GroupLayout jpPlanoLayout = new javax.swing.GroupLayout(jpPlano);
        jpPlano.setLayout(jpPlanoLayout);
        jpPlanoLayout.setHorizontalGroup(
            jpPlanoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jpPlanoLayout.createSequentialGroup()
                .addContainerGap(95, Short.MAX_VALUE)
                .addComponent(jlTitulo, javax.swing.GroupLayout.PREFERRED_SIZE, 450, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(91, 91, 91))
        );
        jpPlanoLayout.setVerticalGroup(
            jpPlanoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpPlanoLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jlTitulo)
                .addContainerGap(543, Short.MAX_VALUE))
        );

        getContentPane().add(jpPlano, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 80, 640, 590));

        jPanel1.setBackground(new java.awt.Color(204, 255, 204));

        comboCord.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        comboCord.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Cartesianas Absolutas", "Cartesianas Relativas", "Polares Absolutas", "Polares Relativas", " " }));
        comboCord.setToolTipText("Seleccionar Coordenadas");
        comboCord.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboCordActionPerformed(evt);
            }
        });

        btnDibujarPlano.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnDibujarPlano.setText("Dibujar Plano");
        btnDibujarPlano.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDibujarPlanoActionPerformed(evt);
            }
        });

        btnGraficar.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnGraficar.setText("Graficar Puntos");
        btnGraficar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGraficarActionPerformed(evt);
            }
        });

        btnSegmentos.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnSegmentos.setText("Dibujar segmentos");
        btnSegmentos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSegmentosActionPerformed(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setText("Puntos ");

        jButton1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jButton1.setText("Menú");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        tablaPuntos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "Punto", "X", "Y"
            }
        ));
        jScrollPane1.setViewportView(tablaPuntos);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnDibujarPlano, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(comboCord, javax.swing.GroupLayout.Alignment.TRAILING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnGraficar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnSegmentos, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 286, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 24, Short.MAX_VALUE)))
                .addContainerGap())
            .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(21, 21, 21)
                .addComponent(comboCord, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnDibujarPlano, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(31, 31, 31)
                .addComponent(btnGraficar, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnSegmentos, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 177, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(133, Short.MAX_VALUE))
        );

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 80, 330, 590));

        jPanel2.setBackground(new java.awt.Color(204, 255, 204));

        jLabel3.setBackground(new java.awt.Color(153, 153, 255));
        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(0, 153, 51));
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("Figuras Geométricas: Compuestas");

        jLabel2.setBackground(new java.awt.Color(153, 153, 255));
        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(0, 153, 51));
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("Graficación Básica por Computadora");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 970, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 10, Short.MAX_VALUE))
        );

        getContentPane().add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 970, 80));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnDibujarPlanoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDibujarPlanoActionPerformed
        limpiarPuntos(jpPlano.getGraphics(), 1);
        DibujarPlano(jpPlano.getGraphics(), 1);
        CartRel();
        ConvAPolar();
        ConvPolarRel();
    }//GEN-LAST:event_btnDibujarPlanoActionPerformed

    private void btnGraficarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGraficarActionPerformed
        int cord = comboCord.getSelectedIndex();
        Graphics plano = jpPlano.getGraphics();
        switch(cord){
            case 0:
                limpiarPuntos(plano, 1);
                PuntosCartAbs(plano);
                jlTitulo.setText("Cartesianas Absolutas");
                TablaCartesAbs();
                btnSegmentos.setEnabled(true);
            break;
            case 1:
                limpiarPuntos(plano, 2);
                CartRel(plano);
                TablaCartesRel();
                btnSegmentos.setEnabled(true);
            break;
            case 2:
                limpiarPuntos(plano, 1);
                PuntosCartAbs(plano);
                TablaPolarAbs();
                jlTitulo.setText("Polares Absolutas");
                btnSegmentos.setEnabled(true);
            break;
            case 3:
                limpiarPuntos(plano, 2);
                PuntosCartAbs(plano);
                TablaPolarRel();
                jlTitulo.setText("Polares Relativas");
                btnSegmentos.setEnabled(true);
            break;
        }
    }//GEN-LAST:event_btnGraficarActionPerformed

    private void comboCordActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboCordActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_comboCordActionPerformed

    private void btnSegmentosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSegmentosActionPerformed
        // TODO add your handling code here:
                int cord = comboCord.getSelectedIndex();
        switch(cord){
            case 0:
                lineasPCartA(jpPlano.getGraphics());
            break;
            case 1:
                lineasPCartR(jpPlano.getGraphics());
            break;
            case 2:
                lineasPolaresAbs(jpPlano.getGraphics());
            break;
            case 3:
                lineasPolaresRel(jpPlano.getGraphics());
            break;
        }
    }//GEN-LAST:event_btnSegmentosActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        dispose();
        Menu frame = new Menu();
        frame.setVisible(rootPaneCheckingEnabled);
    }//GEN-LAST:event_jButton1ActionPerformed
   /**/
    /*   */ /*    */
    public void limpiarPuntos(Graphics g, int s){
        Color repaint = new Color(102, 102, 102);
        g.setColor(repaint);
        g.fillRect(0, 35, 550, 560);
        limpiarTabla();
       // btnSegmentos.setEnabled(false);
        if (s==1)DibujarPlano(jpPlano.getGraphics(), 1);
        else if (s==2) DibujarPlano(jpPlano.getGraphics(), 2);
    }
    public void limpiarTabla(){
        DefaultTableModel modeloT = new DefaultTableModel();
        tablaPuntos.setModel(modeloT);
        modeloT.addColumn("Puntos");
        modeloT.addColumn("X");
        modeloT.addColumn("Y");
    }
        
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
            java.util.logging.Logger.getLogger(Puntos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Puntos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Puntos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Puntos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Puntos().setVisible(true);
            }
        });
    }
    
    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnDibujarPlano;
    private javax.swing.JButton btnGraficar;
    private javax.swing.JButton btnSegmentos;
    private javax.swing.JComboBox<String> comboCord;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel jlTitulo;
    private javax.swing.JPanel jpPlano;
    private javax.swing.JTable tablaPuntos;
    // End of variables declaration//GEN-END:variables
}
