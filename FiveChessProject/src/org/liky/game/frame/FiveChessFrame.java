package org.liky.game.frame;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class FiveChessFrame extends JFrame implements MouseListener,Runnable{

	int width=Toolkit.getDefaultToolkit().getScreenSize().width;
	int height=Toolkit.getDefaultToolkit().getScreenSize().height;
	BufferedImage bgImage = null;
	int x=0,y=0;
	//����֮ǰ�¹���ȫ�����ӵ�����
	//��������Ϊ0����ʾ�����û�����ӣ�1��ʾΪ���ӣ�2��ʾΪ����
	int [][] allChess = new int [15][15];
	boolean isBlack = true;
	boolean canPlay = true;
	String message = "�ڷ�����";
	//�������ӵ��ʱ��
	int maxtime = 0;
	//������ʱ���߳���
	Thread t = new Thread(this);
	//����ڷ���׷�ʣ��ʱ��
	int blackTime = 0;
	int whiteTime = 0;
	//����˫��ʣ��ʱ�����ʾ��Ϣ
	String blackMessage = "������";
	String whiteMessage = "������";
	
	public FiveChessFrame()
	{
		this.setTitle("���");
		this.setSize(1000,700);
		this.setLocation((width-1000)/2, (height-700)/2);
		this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.addMouseListener(this);
		this.setVisible(true);
		
		t.start();
		t.suspend();
		
	}
	
	public void paint(Graphics g)
	{
		try {
			bgImage = ImageIO.read(new File("C:/Users/Dell/Pictures/Saved Pictures/����.jpg"));
		} catch (IOException e) {
			// TODO �Զ����ɵ� catch ��
			e.printStackTrace();
		}
		
		//˫���弼����ֹ��Ļ��˸
		BufferedImage bi = new BufferedImage(1000,700,BufferedImage.TYPE_INT_ARGB);
		Graphics g2 = bi.createGraphics();
		
		g2.setColor(Color.BLACK);
		g2.drawImage(bgImage, 8, 20, this);
		g2.setFont(new Font("����",Font.BOLD,40));
		g2.drawString("��Ϸ��Ϣ��"+message, 300, 80);
		g2.setFont(new Font("�����ּ�-�������п�����",Font.BOLD,35));
		g2.drawString("�ڷ�ʱ�䣺 "+blackMessage, 40, 680);
		g2.drawString("�׷�ʱ�䣺 "+whiteMessage, 450, 680);
		
		for(int i=0;i<15;i++)
		{
			g2.drawLine(40, 130+37*i, 628, 130+37*i);
			g2.drawLine(40+42*i, 130, 40+42*i, 647);
		}
		
		g2.fillOval(163, 239, 8, 8);
		g2.fillOval(163, 535, 8, 8);
		g2.fillOval(498, 535, 8, 8);
		g2.fillOval(498, 239, 8, 8);
		g2.fillOval(330, 387, 8, 8);
		
		/*
		//��������
		x=Math.round((float)(x-40)/42)*42+40;
		y=Math.round((float)(y-130)/37)*37+130;
		//����
		g.fillOval(x-12, y-12, 24, 24);
		//����
		g.setColor(Color.WHITE);
		g.fillOval(x-12, y-12, 24, 24);
		g.setColor(Color.black);
		g.drawOval(x-12, y-12, 24, 24);
		*/
		
		for(int i=0;i<15;i++)
		{
			for(int j=0;j<15;j++)
			{
				if(allChess[i][j]==1)//��
				{
					int tempX=i*42+40;
					int tempY=j*37+130;
					g2.setColor(Color.BLACK);
					g2.fillOval(tempX-12, tempY-12, 24, 24);
				}
				else if(allChess[i][j]==2)//��
				{
					int tempX=i*42+40;
					int tempY=j*37+130;
					g2.setColor(Color.WHITE);
					g2.fillOval(tempX-12, tempY-12, 24, 24);
					g2.setColor(Color.BLACK);
					g2.drawOval(tempX-12, tempY-12, 24, 24);
				}
			}
		}
		g.drawImage(bi, 0, 0, this);
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO �Զ����ɵķ������

	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO �Զ����ɵķ������

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO �Զ����ɵķ������

	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO �Զ����ɵķ������
		//System.out.println("���λ�ã�  X --> "+e.getX());
		//System.out.println("���λ�ã�  Y --> "+e.getY());
		if(canPlay == true)
		{
			x=e.getX();
			y=e.getY();
			if(x>=38&&x<=630&&y>=128&&y<=650)
			{
				//System.out.println("YES");
				x=Math.round((float)(x-40)/42);
				y=Math.round((float)(y-130)/37);
				//System.out.println(x);
				//System.out.println(y);
				if(allChess[x][y]==0)
				{
					if(isBlack == true)
					{
						allChess[x][y]=1;
						isBlack =false;
						message = "�ֵ��׷�";
					}
					else
					{
						allChess[x][y]=2;
						isBlack = true;
						message = "�ֵ��ڷ�";
					}
					
					//�ж���������
					boolean winFlag = this.checkWin();
					if(winFlag == true)
					{
						JOptionPane.showMessageDialog(this, "��Ϸ����,"
														+(allChess[x][y] == 1 ? "�ڷ�" : "�׷�")+"��ʤ��");
						canPlay = false;
					}
				}
				else
				{
					JOptionPane.showMessageDialog(this, "��ǰλ���Ѿ������ӣ����������ӣ�");
				}
			}
		this.repaint();
		}
		
		if(e.getX()>=730&&e.getX()<=990&&e.getY()>=230&&e.getY()<=355)
		{
			int result = JOptionPane.showConfirmDialog(this, "�Ƿ����¿�ʼ��Ϸ��");
			if(result == 0)
			{
				//�������
				allChess = new int[15][15];
				//������Ϸ��Ϣ
				message = "�ڷ�����";
				//�������巽
				isBlack = true;
				blackTime = maxtime;
				whiteTime = maxtime;
				if(maxtime>0)
				{
					blackMessage = maxtime/3600+":"+(maxtime%3600)/60+":"+maxtime%60;
					whiteMessage = maxtime/3600+":"+(maxtime%3600)/60+":"+maxtime%60;
					t.resume();
				}
				else
				{
					blackMessage = "������";
					whiteMessage = "������";
				}
				this.repaint();
			}
			//JOptionPane.showMessageDialog(this, "��ʼ��Ϸ");
		}
		else if(e.getX()>=730&&e.getX()<=990&&e.getY()>=395&&e.getY()<=520)
		{
			//JOptionPane.showMessageDialog(this, "��Ϸ����");
			String input = JOptionPane.showInputDialog("��������Ϸ�����ʱ��(��λ������)���������0��ʾ����ʱ�����ƣ�");
			try {
				maxtime = Integer.parseInt(input)*60;
				if(maxtime<0)
				{
					JOptionPane.showMessageDialog(this, "����ȷ������Ϣ,��������ָ�����");
				}
				else if(maxtime == 0)
				{
					int result = JOptionPane.showConfirmDialog(this, "�������,�Ƿ����¿�ʼ��Ϸ��");
					if(result == 0)
					{
						//�������
						allChess = new int[15][15];
						//������Ϸ��Ϣ
						message = "�ڷ�����";
						//�������巽
						isBlack = true;
						blackTime = maxtime;
						whiteTime = maxtime;
						blackMessage = "������";
						whiteMessage = "������";
						//t.resume();
						this.repaint();
					}
				}
				else
				{
					//JOptionPane.showMessageDialog(this, "�������");
					int result = JOptionPane.showConfirmDialog(this, "�������,�Ƿ����¿�ʼ��Ϸ��");
					if(result == 0)
					{
						//�������
						allChess = new int[15][15];
						//������Ϸ��Ϣ
						message = "�ڷ�����";
						//�������巽
						isBlack = true;
						blackTime = maxtime;
						whiteTime = maxtime;
						blackMessage = maxtime/3600+":"+(maxtime%3600)/60+":"+maxtime%60;
						whiteMessage = maxtime/3600+":"+(maxtime%3600)/60+":"+maxtime%60;
						t.resume();
						this.repaint();
					}
				}
			} catch (NumberFormatException e1) {
				// TODO �Զ����ɵ� catch ��
				JOptionPane.showMessageDialog(this, "����ȷ������Ϣ��");
			}
		}
		else if(e.getX()>=730&&e.getX()<=990&&e.getY()>=565&&e.getY()<=685)
		{
			int result = JOptionPane.showConfirmDialog(this, "�Ƿ�ȷ�����䣿");
			if(result == 0)
			{
				if(isBlack)
					JOptionPane.showMessageDialog(this, "�ڷ��Ѿ����䣬��Ϸ������");
				else
					JOptionPane.showMessageDialog(this, "�׷��Ѿ����䣬��Ϸ������");
				canPlay = false;
			}
		}
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO �Զ����ɵķ������

	}

	private boolean checkWin(){
		boolean flag = false;
		int count =1;//�ж϶��ٸ���ͬ��ɫ��������
		
		//�жϺ�����������ͬ
		int color = allChess[x][y];
		int i=1,j=1;
		while(x+i<15 && color == allChess[x+i][y])
		{
			count++;
			i++;
		}
		while(x-j>=0 && color == allChess[x-j][y])
		{
			count++;
			j++;
		}
		if(count>=5)
			flag = true;
		else
		{
			//�ж����򣬺�������ͬ
			i=1;j=1;count =1;
			while(y+i<15 && color == allChess[x][y+i])
			{
				count++;
				i++;
			}
			while(y-i>=0 && color == allChess[x][y-j])
			{
				count++;
				j++;
			}
			if(count>=5)
				flag = true;
			else
			{
				//�ж�б��,����+����
				i=1;j=1;count =1;
				while(x+i<15 && y+i<15 && color == allChess[x+i][y+i])
				{
					count++;
					i++;
				}
				while(x-j>=0 && y-j>=0 && color == allChess[x-j][y-j])
				{
					count++;
					j++;
				}
				if(count>=5)
					flag = true;
				else 
				{
					//�ж�б��,����+����
					i=1;j=1;count =1;
					while(x+i<15 && y-i>=0 && color == allChess[x+i][y-i])
					{
						count++;
						i++;
					}
					while(x-j>=0 && y+j<15 && color == allChess[x-j][y+j])
					{
						count++;
						j++;
					}
					if(count>=5)
						flag = true;
				}
			}
		}
		return flag;
	}

	@Override
	public void run() {
		// TODO �Զ����ɵķ������
		//�ж��Ƿ���ʱ������
		if(maxtime>0)
		{
			while(true)
			{
				if(isBlack)
				{
					blackTime--;
					if(blackTime == 0)
					{
						JOptionPane.showMessageDialog(this, "�ڷ���ʱ����Ϸ������");
						canPlay = false;
					}
				}
				else
				{
					whiteTime--;
					if(whiteTime == 0)
					{
						JOptionPane.showMessageDialog(this, "�׷���ʱ����Ϸ������");
						canPlay = false;
					}
				}
				blackMessage = blackTime/3600+":"+(blackTime%3600)/60+":"+blackTime%60;
				whiteMessage = whiteTime/3600+":"+(whiteTime%3600)/60+":"+whiteTime%60;
				this.repaint();
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					// TODO �Զ����ɵ� catch ��
					e.printStackTrace();
				}
				
			}
		}
		else
			this.repaint();
	}
	
}
