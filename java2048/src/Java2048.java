import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Random;

/**
 * Created by Lenovo on 2016/11/27.
 */
class My2048 {
    private int qipan[][];
    private JFrame frame;
    private JLabel jLabels[][];
    private JLabel information[];
    private int N=4;
    private int M=16;


    public My2048() {
        Font fnt=new Font("新宋体",Font.BOLD,26);
        Font fnt2=new Font("新宋体",Font.BOLD,18);
        qipan=new int[N][N];
        frame=new JFrame("2048");
        jLabels=new JLabel[N][N];
        information=new JLabel[4];
        for(int i=0;i<N;i++)
        {
            for(int j=0;j<N;j++)
            {
                qipan[i][j]=0;
                jLabels[i][j]=new JLabel("",JLabel.CENTER);
                jLabels[i][j].setFont(fnt);
            }
        }
        for(int i=0;i<4;i++)
        {
            information[i]=new JLabel("",JLabel.CENTER);
            information[i].setFont(fnt2);
        }
        frame.addKeyListener(new KeyListener() {
            boolean flag;
            @Override
            public void keyTyped(KeyEvent e) {

            }
            @Override
            public void keyPressed(KeyEvent e) {
                this.flag=false;
                if(e.getKeyCode()==87||e.getKeyCode()==38) {
                    if(up()==0) {
                        if(win()==1){
                            JOptionPane.showMessageDialog(frame, "你赢了");
                            frame.dispose();
                        }
                        if(jiance()==0) {
                            JOptionPane.showMessageDialog(frame, "你输了");
                            frame.dispose();
                        }
                        print();
                        this.flag=true;
                    }else{
                        if(jiance()==0) {
                            JOptionPane.showMessageDialog(frame, "你输了");
                            frame.dispose();
                        }
                    }
                }else if(e.getKeyCode()==83||e.getKeyCode()==40) {
                    if(down()==0){
                        if(win()==1){
                            JOptionPane.showMessageDialog(frame, "你赢了");
                            frame.dispose();
                        }
                        if(jiance()==0) {
                            JOptionPane.showMessageDialog(frame, "你输了");
                            frame.dispose();
                        }
                        print();
                        this.flag=true;
                    }else{
                        if(jiance()==0){
                            JOptionPane.showMessageDialog(frame, "你输了");
                            frame.dispose();
                        }
                    }
                }else if(e.getKeyCode()==65||e.getKeyCode()==37) {
                    if(left()==0){
                        if(win()==1){
                            JOptionPane.showMessageDialog(frame, "你赢了");
                            frame.dispose();
                        }

                        if(jiance()==0){
                            JOptionPane.showMessageDialog(frame, "你输了");
                            frame.dispose();
                        }
                        print();
                        this.flag=true;
                    }else{
                        if(jiance()==0){
                            JOptionPane.showMessageDialog(frame, "你输了");
                            frame.dispose();
                        }
                    }
                }else if(e.getKeyCode()==68||e.getKeyCode()==39) {
                    if(right()==0){
                        if(win()==1){
                            JOptionPane.showMessageDialog(frame, "你赢了");
                            frame.dispose();
                        }
                        if(jiance()==0){
                            JOptionPane.showMessageDialog(frame, "你输了");
                            frame.dispose();
                        }
                        print();
                        this.flag=true;
                    }else{
                        if(jiance()==0){
                            JOptionPane.showMessageDialog(frame, "你输了");
                            frame.dispose();
                        }
                    }
                }else{}
            }
            @Override
            public void keyReleased(KeyEvent e) {
                if(e.getKeyCode()==87||e.getKeyCode()==38||e.getKeyCode()==83||e.getKeyCode()==40||e.getKeyCode()==65||e.getKeyCode()==39||e.getKeyCode()==68||e.getKeyCode()==39){
                    if(!this.flag)
                    {

                        if(shengcheng()==0&&jiance()==0)
                        {
                            JOptionPane.showMessageDialog(frame, "你输了");
                            frame.dispose();
                        }
                        print();
                    }
                    else
                    {
                        print();
                    }
                }
            }
        });

        this.shengcheng();
        this.shengcheng();
        print();
        frame.addWindowFocusListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(1);
            }
        });

        frame.setLayout(null);
        for(int i=0;i<N;i++)
        {
            for(int j=0;j<N;j++) {
                this.jLabels[i][j].setBorder(BorderFactory.createEtchedBorder());
                jLabels[i][j].setBounds(80+j*100,80+i*100,80,80);
                frame.add(jLabels[i][j]);
            }

        }
        information[0].setText("W/↑ 上");
        information[1].setText("S/↓ 下");
        information[2].setText("A/← 左");
        information[3].setText("D/→ 右");
        for(int i=0;i<4;i++)
        {
            information[i].setBounds(80+i*100,500,80,40);
            frame.add(information[i]);
        }
        frame.setSize(560,600);
        frame.setVisible(true);
    }
    private int shengcheng() {
        int i,j,k,a,b,c,q;
        int p[][]=new int[M][2];
        Random r=new Random();
        for(i=0,k=0;i<N;i++)
        {
            for(j=0;j<N;j++)
            {
                if(qipan[i][j]==0)
                {
                    p[k][0]=i;
                    p[k][1]=j;
                    k++;
                }
            }
        }
        if(k==0){
            return 0;
        }
        else {
            c=r.nextInt(100000)%k;
            a=p[c][0];
            b=p[c][1];
            q=r.nextInt(100000)%10;
            if(q<6)
            {
                qipan[a][b]=2;
             //   jLabels[a][b].setText(qipan[a][b]+"");
            }
            else {
                qipan[a][b] = 4;
             //   jLabels[a][b].setText(qipan[a][b]+"");
            }
            return 1;
        }

    }
    public int up(){
        int i,j,a,l,mark,mark2=0,mark3;
        int fuzhu[]=new int[N],f;
        int fuzhu2[]=new int[N];
        for(i=0,mark3=0;i<N;i++) {
            f=0;j=0;
            for(l=0;l<N;l++)
            {
                fuzhu[l]=0;
                fuzhu2[l]=qipan[l][i];
            }
            while(qipan[j][i]==0)					//找到第一个不是空的位置
            {
                j++;
                if(j>=N)
                    break;
            }
            if(j>=N)
                continue;
            for(mark=0;j<N-1;)
            {
                a=j+1;
                if(a>=N)
                    break;
                while(qipan[a][i]==0)				//找到第二个不是空的位置
                {
                    a++;
                    if(a>=N)
                        break;
                }
                if(a>=N)
                    break;
                if(qipan[j][i]==qipan[a][i])		//判断相等否
                {
                    qipan[j][i]+=qipan[a][i];
                    qipan[a][i]=0;
                    mark=1;
                }
                if(mark==1)							//如果前面加过就从a下一个开始
                    j=a+1;
                else
                    j=a;							//如果没有从a开始
                if(j>=N)
                    break;
                while(qipan[j][i]==0)				//找下一个
                {
                    j++;
                    if(j>=N)
                        break;
                }
            }
            for(l=0;l<N;l++)						//补齐
            {
                if(qipan[l][i]!=0)
                {
                    fuzhu[f]=qipan[l][i];
                    f++;
                }
            }
            for(l=0;l<N;l++)
            {
                qipan[l][i]=fuzhu[l];
                if(fuzhu2[l]!=fuzhu[l])
                    mark3=1;
            }
            if(mark3==1){
                mark2++;
            }
        }
        if(mark2!=0) {
            return 1;
        }
        else
            return 0;
    }
    private int down(){
        int i,j,a,o,l,mark,mark2=0,mark3;
        int fuzhu[]=new int[N],fuzhu2[]=new int[N],f,e;
        for(i=0,mark3=0;i<N;i++)
        {
            f=0;j=N-1;e=0;
            for(l=0;l<N;l++)
                fuzhu[l]=0;
            for(l=N-1;l>=0;l--)
            {
                fuzhu2[e]=qipan[l][i];e++;
            }
            while(qipan[j][i]==0)
            {
                j--;
                if(j<0)
                    break;
            }
            if(j<0)
                continue;;
            for(mark=0;j>=0;)
            {
                a=j-1;
                if(a<0)
                    break;
                while(qipan[a][i]==0)
                {
                    a--;
                    if(a<0)
                        break;
                }
                if(a<0)
                    break;
                if(qipan[j][i]==qipan[a][i])
                {
                    qipan[j][i]+=qipan[a][i];
                    qipan[a][i]=0;
                    mark=1;
                }
                if(mark==1)
                    j=a-1;
                else
                    j=a;
                if(j<0)
                    break;
                while(qipan[j][i]==0)
                {
                    j--;
                    if(j<0)
                        break;
                }
            }
            for(l=N-1;l>=0;l--)
            {
                if(qipan[l][i]!=0)
                {
                    fuzhu[f]=qipan[l][i];
                    f++;
                }
            }
            for(l=0,o=N-1;l<N;o--,l++)
            {
                qipan[o][i]=fuzhu[l];
                if(fuzhu2[l]!=fuzhu[l])
                    mark3=1;
            }
            if(mark3!=0) {
                mark2++;
            }
        }
        if(mark2!=0)
        {
            return 1;
        }
        else
            return 0;
    }
    private int left(){
        int i,j,a,l,mark,mark2=0,mark3;
        int fuzhu[]=new int[N],f;
        int fuzhu2[]=new int[N];
        for(i=0,mark3=0;i<N;i++)
        {
            f=0;j=0;
            for(l=0;l<N;l++)
            {
                fuzhu[l]=0;
                fuzhu2[l]=qipan[i][l];
            }
            while(qipan[i][j]==0)
            {
                j++;
                if(j>=N)
                    break;
            }
            if(j>=N)
                continue;
            for(mark=0;j<N-1;)
            {
                a=j+1;
                if(a>=N)
                    break;
                while(qipan[i][a]==0)
                {
                    a++;
                    if(a>=N)
                        break;
                }
                if(a>=N)
                    break;
                if(qipan[i][j]==qipan[i][a])
                {
                    qipan[i][j]+=qipan[i][a];
                    qipan[i][a]=0;
                    mark=1;
                }
                if(mark==1)
                    j=a+1;
                else
                    j=a;
                if(j>=N)
                    break;
                while(qipan[i][j]==0)
                {
                    j++;
                    if(j>=N)
                        break;
                }
            }
            for(l=0;l<N;l++)
            {
                if(qipan[i][l]!=0)
                {
                    fuzhu[f]=qipan[i][l];
                    f++;
                }
            }
            for(l=0;l<N;l++)
            {
                qipan[i][l]=fuzhu[l];
                if(fuzhu2[l]!=fuzhu[l])
                    mark3=1;
            }
            if(mark3!=0) {
                mark2++;
            }
        }
        if(mark2!=0) {
            return 1;
        }
        else
            return 0;
    }
    private int right(){
        int i,j,a,o,l,mark,mark2=0,mark3;
        int fuzhu[]=new int[N],fuzhu2[]=new int[N],f,e;
        for(i=0;i<N;i++)
        {
            f=0;j=N-1;e=0;
            for(l=0,mark3=0;l<N;l++)
                fuzhu[l]=0;
            for(l=N-1;l>=0;l--)
            {
                fuzhu2[e]=qipan[i][l];
                e++;
            }
            while(qipan[i][j]==0)
            {
                j--;
                if(j<0)
                    break;
            }
            if(j<0)
                continue;
            for(mark=0;j>=0;)
            {
                a=j-1;
                if(a<0)
                    break;
                while(qipan[i][a]==0)
                {
                    a--;
                    if(a<0)
                        break;
                }
                if(a<0)
                    break;
                if(qipan[i][j]==qipan[i][a])
                {
                    qipan[i][j]+=qipan[i][a];
                    qipan[i][a]=0;
                    mark=1;
                }
                if(mark==1)
                    j=a-1;
                else
                    j=a;
                if(j<=0)
                    break;
                while(qipan[i][j]==0)
                {
                    j--;
                    if(j<0)
                        break;
                }
            }
            for(l=N-1;l>=0;l--)
            {
                if(qipan[i][l]!=0)
                {
                    fuzhu[f]=qipan[i][l];
                    f++;
                }
            }
            for(l=0,o=N-1;l<N;o--,l++)
            {
                qipan[i][o]=fuzhu[l];
                if(fuzhu2[l]!=fuzhu[l])
                    mark3=1;
            }
            if(mark3!=0) {
                mark2++;
            }
        }
        if(mark2!=0) {
            return 1;
        }
        else
            return 0;
    }
    public int jiance(){
        int i,j,k=0,shang,xia,zuo,you;
        for(i=0;i<N;i++)
        {
            for(j=0;j<N;j++)
            {
                shang=i-1;
                xia=i+1;
                zuo=j-1;
                you=j+1;
                if(shang>=0)
                {
                    if(qipan[i][j]==qipan[shang][j])
                    {
                        k++;break;
                    }
                }
                if(xia<N)
                {
                    if(qipan[i][j]==qipan[xia][j])
                    {
                        k++;break;
                    }
                }
                if(zuo>=0)
                {
                    if(qipan[i][j]==qipan[i][zuo])
                    {
                        k++;break;
                    }
                }
                if(you<N)
                {
                    if(qipan[i][j]==qipan[i][you])
                    {
                        k++;break;
                    }
                }
                if(qipan[i][j]==0)
                {
                    k++;break;
                }

            }
            if(k>0)
                break;
        }
        if(k>0)
            return 1;
        else
            return 0;
    }
    public int win(){
        int i,j,k=0;
        for(i=0;i<N;i++)
        {
            for(j=0;j<N;j++)
            {
                if(qipan[i][j]==2048)
                {
                    k++;
                    break;
                }
            }
            if(k>0)
                break;
        }
        if(k!=0)
            return 1;
        else
            return 0;
    }
    public void print(){
        for(int i=0;i<N;i++)
        {
            for(int j=0;j<N;j++)
            {
                if(qipan[i][j]!=0)
                {
                    jLabels[i][j].setText(qipan[i][j]+"");

                    switch (qipan[i][j])
                    {
                        case 2:Color c=new Color(234, 240, 232);jLabels[i][j].setOpaque(true);jLabels[i][j].setBackground(c);break;
                        case 4:Color c1=new Color(245, 219, 133);jLabels[i][j].setOpaque(true);jLabels[i][j].setBackground(c1);break;
                        case 8:Color c2=new Color(255, 169, 126);jLabels[i][j].setOpaque(true);jLabels[i][j].setBackground(c2);break;
                        case 16:Color c3=new Color(255, 152, 146);jLabels[i][j].setOpaque(true);jLabels[i][j].setBackground(c3);break;
                        case 32:Color c4=new Color(255, 110, 144);jLabels[i][j].setOpaque(true);jLabels[i][j].setBackground(c4);break;
                        case 64:Color c5=new Color(255, 80, 117);jLabels[i][j].setOpaque(true);jLabels[i][j].setBackground(c5);break;
                        case 128:Color c6=new Color(240, 236, 94);jLabels[i][j].setOpaque(true);jLabels[i][j].setBackground(c6);break;
                        case 256:Color c7=new Color(240, 180, 18);jLabels[i][j].setOpaque(true);jLabels[i][j].setBackground(c7);break;
                        case 512:Color c8=new Color(240, 126, 15);jLabels[i][j].setOpaque(true);jLabels[i][j].setBackground(c8);break;
                        case 1024:Color c9=new Color(240, 227, 104);jLabels[i][j].setOpaque(true);jLabels[i][j].setBackground(c9);break;
                        case 2048:Color c10=new Color(146, 240, 23);jLabels[i][j].setOpaque(true);jLabels[i][j].setBackground(c10);break;
                        case 4096:Color c11=new Color(49, 240, 91);jLabels[i][j].setOpaque(true);jLabels[i][j].setBackground(c11);break;
                    }
                }else{
                    Color c0=new Color(255, 255, 255);
                    jLabels[i][j].setOpaque(true);jLabels[i][j].setBackground(c0);jLabels[i][j].setText("");
                }
            }
        }
    }
}
public class Java2048{
    public static void main(String args[]){
        My2048 game=new My2048();
    }
}
