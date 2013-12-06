import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.*;

public class Panel extends JFrame implements ActionListener, KeyListener
{
 JPanel mainPanel=new JPanel(new BorderLayout());
    JButton b_New, b_Check, b_Exit;
    JCheckBox b_Help;   
    JTextField[][] num =new JTextField[9][9];
    JToggleButton[] toggle_buttons;
    int [][] arrayUser=new int[9][9];
    Color Light_Blue=new Color(105,177,255);
    String line1;
    boolean help;
    int button_no;
    Font font1 = new Font("Calibri", Font.HANGING_BASELINE, 30);
    int [][] arraySol={{9,6,3,1,7,4,2,5,8},{1,7,8,3,2,5,6,4,9},{2,5,4,6,8,9,7,3,1},{8,2,1,4,3,7,5,9,6},{4,9,6,8,5,2,3,1,7},{7,3,5,9,6,1,8,2,4},{5,8,9,7,1,3,4,6,2},{3,1,7,2,4,6,9,8,5},{6,4,2,5,9,8,1,7,3}};
    
    public Panel() 
    {
      super("Sodoku (v2.0.1.3)");
        this.setSize(900,500);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        
        gridOfTextFields(this);
        
        buttonPanel();
        
        b_Check.addActionListener(this);
        
        this.setVisible(true);
    }
 public void buttonPanel()
 {
  
  JPanel panel2 = new JPanel();
        panel2.setLayout(new BoxLayout(panel2, BoxLayout.PAGE_AXIS));
        mainPanel.add(panel2, BorderLayout.NORTH);

        JPanel panelOptions = new JPanel(new FlowLayout(FlowLayout.LEADING));
        panelOptions.setBorder(BorderFactory.createTitledBorder(" Options "));
        panel2.add(panelOptions);

        b_New = new JButton("New");
        b_New.setFocusable(false);
        panelOptions.add(b_New);

        b_Check = new JButton("Check");
        b_Check.setFocusable(false);
        panelOptions.add(b_Check);

        b_Exit = new JButton("Exit");
        b_Exit.setFocusable(false);
        panelOptions.add(b_Exit);
        b_Exit.addActionListener(this);
        b_New.addActionListener(this);
        
        JPanel pnlNumbers = new JPanel();
        pnlNumbers.setLayout(new BoxLayout(pnlNumbers, BoxLayout.PAGE_AXIS));
        pnlNumbers.setBorder(BorderFactory.createTitledBorder(" Numbers "));
        panel2.add(pnlNumbers);

        JPanel pnlNumbersHelp = new JPanel(new FlowLayout(FlowLayout.LEADING));
        pnlNumbers.add(pnlNumbersHelp);

        b_Help = new JCheckBox("Help on", false);
        b_Help.setFocusable(false);
        pnlNumbersHelp.add(b_Help);
        
        JPanel panel_toggle_numbers=new JPanel(new FlowLayout(FlowLayout.LEADING));
        pnlNumbers.add(panel_toggle_numbers);
        
        toggle_buttons=new JToggleButton[9];
        ButtonGroup b_group=new ButtonGroup();
        
        JPanel panelPic=new JPanel(new FlowLayout(100,100,100));
        panel2.add(panelPic);
        
        
        ImageIcon imageLocation = new ImageIcon("Untitled.jpg");
        JLabel l=new JLabel(imageLocation);
        panelPic.add(l);
        
        for(int i=0;i<9;i++)
        {
         toggle_buttons[i] = new JToggleButton("" + (i + 1));
            toggle_buttons[i].setSize(new Dimension(30,30));
            toggle_buttons[i].setFocusable(false);
            b_group.add(toggle_buttons[i]);
            panel_toggle_numbers.add(toggle_buttons[i]);
        }
        b_Help.addActionListener(this);
        for(int i=0;i<9;i++)
        {
         toggle_buttons[i].addActionListener(this);
        }
        
        //set default color of text fields
        colorOfTextFields();
        
 }
 public void colorOfTextFields()
 {
  for(int i=0;i<9;i++)
         for(int j=0;j<9;j++)
          num[i][j].setBackground(Color.white);
 }
 
 public void gridOfTextFields(JFrame jf) 
 {
  mainPanel.setLayout(new GridLayout(1,2));
        this.add(mainPanel);
        
        JPanel panel1= new JPanel(new GridLayout(3,3));
        panel1.setLayout(new GridLayout(3,3));
        mainPanel.add(panel1);
        
        JPanel[][] grid=new JPanel[3][3];
        
        int c1=0;     //text field row selector.
        int temp;     //column selector of 3*3 panel.
        for(int i=0;i<3;i++)
         for(int j=0;j<3;j++)
         {
          grid[i][j]=new JPanel(new GridLayout(3,3));
             grid[i][j].setBorder(BorderFactory.createLineBorder(Color.black));
             panel1.add(grid[i][j]);
         }
        for (int i=0; i<3; i++) 
        {
         switch(i)
         {
         case 0:
          c1=0;
          break;
         case 1:
          c1=3;
          break;
         case 2:
          c1=6;
          break;
         }
         
         for (int j=0; j<3; j++) 
            {
             temp=0;
                   for(int k=0;k<9;k++)
                   {
                    if(k==3)
                     temp++;  //jump to second panel
                    else if(k==6)
                    {
                     temp--;   //temp=0
                     temp=temp+2;  //jump to third panel
                    }
                       num[c1][k]=new JTextField(5);
                        num[c1][k].setBorder(BorderFactory.createLineBorder(Color.GRAY));
                        num[c1][k].setSize(5,5);
                        num[c1][k].setFont(font1);
                        grid[i][temp].add(num[c1][k]);
                      }
             c1++; //as j increments c1 must also be incremented
      }
  }
        for(int i=0;i<9;i++)
         for(int j=0;j<9;j++)
         {
          num[i][j].setFont(font1);
          num[i][j].setHorizontalAlignment(JTextField.CENTER);
         }
 
        num[0][1].setText("6");
        num[0][1].setEditable(false);
        
        num[0][3].setText("1");
        num[0][3].setEditable(false);
        
        num[0][5].setText("4");
        num[0][5].setEditable(false);
         
        num[0][7].setText("5");
        num[0][7].setEditable(false);
        
        num[1][2].setText("8");
        num[1][2].setEditable(false);
        
        num[1][3].setText("3");
        num[1][3].setEditable(false);
        
        num[1][5].setText("5");
        num[1][5].setEditable(false);
        
        num[1][6].setText("6");
        num[1][6].setEditable(false);
        
        num[2][0].setText("2");
        num[2][0].setEditable(false);
        
        num[2][8].setText("1");
        num[2][8].setEditable(false);
        
        num[3][0].setText("8");
        num[3][0].setEditable(false);
        
        num[3][3].setText("4");
        num[3][3].setEditable(false);
        
        num[3][5].setText("7");
        num[3][5].setEditable(false);
        
        num[3][8].setText("6");
        num[3][8].setEditable(false);
               
        num[4][2].setText("6");
        num[4][2].setEditable(false);
        
        num[4][6].setText("3");
        num[4][6].setEditable(false);
        
        num[5][0].setText("7");
        num[5][0].setEditable(false);
        
        num[5][3].setText("9");
        num[5][3].setEditable(false);
        
        num[5][5].setText("1");
        num[5][5].setEditable(false);
        
        num[5][8].setText("4");
        num[5][8].setEditable(false);
        
        num[6][0].setText("5");
        num[6][0].setEditable(false);
        
        num[7][2].setText("7");
        num[7][2].setEditable(false);
        
        num[7][3].setText("2");
        num[7][3].setEditable(false);
        
        num[7][5].setText("6");
        num[7][5].setEditable(false);
        
        num[7][6].setText("9");
        num[7][6].setEditable(false);
        
        num[8][1].setText("4");
        num[8][1].setEditable(false);
        
        num[8][3].setText("5");
        num[8][3].setEditable(false);
        
        num[8][5].setText("8");
        num[8][5].setEditable(false);
        
        num[8][7].setText("7");
        num[8][7].setEditable(false);
        
        //Registration of Listeners
        for(int i=0;i<9;i++)
         for(int j=0;j<9;j++)
         {
          num[i][j].addKeyListener(this);
         }
 }
 public static void main(String [] args)
 {
  Panel b=new Panel();
 }
 public void actionPerformed(ActionEvent e)
 {
  if (e.getActionCommand().equals("New"))
  {
   Panel b1=new Panel();
   this.setVisible(false);
   
  }
  else if (e.getActionCommand().equals("Exit"))
  {
   JOptionPane.showMessageDialog(null, "Thank you for using\n           this app");
            System.exit(0);
  }
  else if(e.getActionCommand().equals("Check"))
  {
   char ch;
   for(int i=0;i<9;i++)
    for(int j=0;j<9;j++)
    {
     if(num[i][j].getText().length()==1)
     {
      ch=num[i][j].getText().charAt(0);
      ch=(char)((int)ch-48);
               if( !((int)ch>=1&&(int)ch<=9) )
               {
                JOptionPane.showMessageDialog(null,"Invalid number at row " +(i+1)+" and column "+(j+1));
                   i=9;
                   j=9;
                   return;
               }
            }
     else 
           {
      JOptionPane.showMessageDialog(null,"Invalid number at row " +(i+1)+" and column "+(j+1)); 
         i=9;
         j=9;
         return;
     }
            }
     for(int i=0;i<9;i++)
   for(int j=0;j<9;j++)
   {
    try
    {
    arrayUser[i][j]=Integer.parseInt(num[i][j].getText());
    }
    catch(Exception e1)
    {
     JOptionPane.showMessageDialog(null,"Invalid number at row " +(i+1)+" and column "+(j+1));
     return;
    }
   }
     
  int count=0;
  count = compare(count);
  if(count==81)
   JOptionPane.showMessageDialog(null, "Congratulations!\n  You Won.");
  else
   JOptionPane.showMessageDialog(null, "    Alas!\n You Lose.");
  }
  else if(e.getActionCommand().equals("Help on"))
  {
   setHelp(((JCheckBox) e.getSource()).isSelected());
  }
  else if(help==true)
  {
   
      button_no=Integer.parseInt(e.getActionCommand());
      //System.out.println(button_no);
      help();
  }
  if(help==false)
   colorOfTextFields();
 }
    public void setHelp(boolean help) {
        this.help = help;
    }
    public void help()
    {
     colorOfTextFields();
     
     for(int row=0;row<9;row++)
      for(int col=0;col<9;col++)
       if(num[row][col].getText().equals(""))
       {
        boolean row_check=false;
        //row check
        row_check = rowCheck(row, col, row_check);
        
        //column check
        boolean column_check=false;
           column_check = columnCheck(row, col, column_check);
        
        //block check
        boolean block_check=false;
        block_check = blockCheck(row, col, block_check);
        
        if(row_check==false&&column_check==false&&block_check==false)
         num[row][col].setBackground(Light_Blue);
       }
     
    }
 public boolean rowCheck(int row, int col, boolean row_check) {
  for(int i=0;i<9;i++)
   if(!num[row][i].getText().equals(""))
      if(button_no==Integer.parseInt(num[row][i].getText()))
     {
     row_check=true;
        break;
     }
      else
       row_check=false;
  return row_check;
 }
 public boolean columnCheck(int row, int col, boolean column_check) {
  for(int i=0;i<9;i++)
   if(!num[i][col].getText().equals(""))
    if(button_no==Integer.parseInt(num[i][col].getText()))
    {
     column_check=true;
              break;
       }
    else
     column_check=false;
  return column_check;
 }
 
    public boolean blockCheck(int row, int col, boolean block_check) {
  //row selector
  int row_sel;
  if(row<3)
   row_sel=0;
  else if(row>2&&row<6)
   row_sel=3;
  else
   row_sel=6;
  //column selector
  int col_sel;
  if(col<3)
   col_sel=0;
  else if(col>2&&col<6)
   col_sel=3;
  else
   col_sel=6;
  
  for(int i=row_sel;i<row_sel+3;i++)
   for(int j=col_sel;j<col_sel+3;j++)
   {
       if(!num[i][j].getText().equals(""))
        if(button_no==Integer.parseInt(num[i][j].getText()))
        {
         block_check=true;
               return block_check;
        }
        else
         block_check=false;
   }
  return block_check;
 }

 public int compare(int count) {
  for(int i=0;i<9;i++)
   for(int j=0;j<9;j++)
   {
    
    if(arraySol[i][j]!=arrayUser[i][j])
    {
     num[i][j].setBackground(Color.RED);
     
    }
    
    else
    {
     num[i][j].setBackground(Color.GREEN);
     count++;
    }
   }
  return count;
 }
   public void keyPressed(KeyEvent e)
   {
   }
   public void keyReleased(KeyEvent e)
   {
   }
   public void keyTyped(KeyEvent e)
   { 
   }
}