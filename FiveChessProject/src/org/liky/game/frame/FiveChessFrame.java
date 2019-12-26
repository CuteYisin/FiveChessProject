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
	//保存之前下过的全部棋子的坐标
	//其中数据为0，表示这个点没有棋子；1表示为黑子；2表示为白子
	int [][] allChess = new int [15][15];
	boolean isBlack = true;
	boolean canPlay = true;
	String message = "黑方先行";
	//保存最多拥有时间
	int maxtime = 0;
	//做倒计时的线程类
	Thread t = new Thread(this);
	//保存黑方与白方剩余时间
	int blackTime = 0;
	int whiteTime = 0;
	//保存双方剩余时间的显示信息
	String blackMessage = "无限制";
	String whiteMessage = "无限制";
	
	public FiveChessFrame()
	{
		this.setTitle("棋道");
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
			bgImage = ImageIO.read(new File("C:/Users/Dell/Pictures/Saved Pictures/背景.jpg"));
		} catch (IOException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		
		//双缓冲技术防止屏幕闪烁
		BufferedImage bi = new BufferedImage(1000,700,BufferedImage.TYPE_INT_ARGB);
		Graphics g2 = bi.createGraphics();
		
		g2.setColor(Color.BLACK);
		g2.drawImage(bgImage, 8, 20, this);
		g2.setFont(new Font("黑体",Font.BOLD,40));
		g2.drawString("游戏信息："+message, 300, 80);
		g2.setFont(new Font("方正字迹-曾正国行楷简体",Font.BOLD,35));
		g2.drawString("黑方时间： "+blackMessage, 40, 680);
		g2.drawString("白方时间： "+whiteMessage, 450, 680);
		
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
		//绘制棋子
		x=Math.round((float)(x-40)/42)*42+40;
		y=Math.round((float)(y-130)/37)*37+130;
		//黑子
		g.fillOval(x-12, y-12, 24, 24);
		//白子
		g.setColor(Color.WHITE);
		g.fillOval(x-12, y-12, 24, 24);
		g.setColor(Color.black);
		g.drawOval(x-12, y-12, 24, 24);
		*/
		
		for(int i=0;i<15;i++)
		{
			for(int j=0;j<15;j++)
			{
				if(allChess[i][j]==1)//黑
				{
					int tempX=i*42+40;
					int tempY=j*37+130;
					g2.setColor(Color.BLACK);
					g2.fillOval(tempX-12, tempY-12, 24, 24);
				}
				else if(allChess[i][j]==2)//白
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
		// TODO 自动生成的方法存根

	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO 自动生成的方法存根

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO 自动生成的方法存根

	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO 自动生成的方法存根
		//System.out.println("点击位置：  X --> "+e.getX());
		//System.out.println("点击位置：  Y --> "+e.getY());
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
						message = "轮到白方";
					}
					else
					{
						allChess[x][y]=2;
						isBlack = true;
						message = "轮到黑方";
					}
					
					//判断五子连珠
					boolean winFlag = this.checkWin();
					if(winFlag == true)
					{
						JOptionPane.showMessageDialog(this, "游戏结束,"
														+(allChess[x][y] == 1 ? "黑方" : "白方")+"获胜！");
						canPlay = false;
					}
				}
				else
				{
					JOptionPane.showMessageDialog(this, "当前位置已经有棋子，请重新落子！");
				}
			}
		this.repaint();
		}
		
		if(e.getX()>=730&&e.getX()<=990&&e.getY()>=230&&e.getY()<=355)
		{
			int result = JOptionPane.showConfirmDialog(this, "是否重新开始游戏？");
			if(result == 0)
			{
				//清空棋盘
				allChess = new int[15][15];
				//重置游戏信息
				message = "黑方先行";
				//更改下棋方
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
					blackMessage = "无限制";
					whiteMessage = "无限制";
				}
				this.repaint();
			}
			//JOptionPane.showMessageDialog(this, "开始游戏");
		}
		else if(e.getX()>=730&&e.getX()<=990&&e.getY()>=395&&e.getY()<=520)
		{
			//JOptionPane.showMessageDialog(this, "游戏设置");
			String input = JOptionPane.showInputDialog("请输入游戏的最大时间(单位：分钟)，如果输入0表示无需时间限制：");
			try {
				maxtime = Integer.parseInt(input)*60;
				if(maxtime<0)
				{
					JOptionPane.showMessageDialog(this, "请正确输入信息,不允许出现负数！");
				}
				else if(maxtime == 0)
				{
					int result = JOptionPane.showConfirmDialog(this, "设置完成,是否重新开始游戏？");
					if(result == 0)
					{
						//清空棋盘
						allChess = new int[15][15];
						//重置游戏信息
						message = "黑方先行";
						//更改下棋方
						isBlack = true;
						blackTime = maxtime;
						whiteTime = maxtime;
						blackMessage = "无限制";
						whiteMessage = "无限制";
						//t.resume();
						this.repaint();
					}
				}
				else
				{
					//JOptionPane.showMessageDialog(this, "设置完成");
					int result = JOptionPane.showConfirmDialog(this, "设置完成,是否重新开始游戏？");
					if(result == 0)
					{
						//清空棋盘
						allChess = new int[15][15];
						//重置游戏信息
						message = "黑方先行";
						//更改下棋方
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
				// TODO 自动生成的 catch 块
				JOptionPane.showMessageDialog(this, "请正确输入信息！");
			}
		}
		else if(e.getX()>=730&&e.getX()<=990&&e.getY()>=565&&e.getY()<=685)
		{
			int result = JOptionPane.showConfirmDialog(this, "是否确认认输？");
			if(result == 0)
			{
				if(isBlack)
					JOptionPane.showMessageDialog(this, "黑方已经认输，游戏结束！");
				else
					JOptionPane.showMessageDialog(this, "白方已经认输，游戏结束！");
				canPlay = false;
			}
		}
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO 自动生成的方法存根

	}

	private boolean checkWin(){
		boolean flag = false;
		int count =1;//判断多少个相同颜色棋子相连
		
		//判断横向，纵坐标相同
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
			//判断纵向，横坐标相同
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
				//判断斜向,左上+右下
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
					//判断斜向,左下+右上
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
		// TODO 自动生成的方法存根
		//判断是否有时间限制
		if(maxtime>0)
		{
			while(true)
			{
				if(isBlack)
				{
					blackTime--;
					if(blackTime == 0)
					{
						JOptionPane.showMessageDialog(this, "黑方超时，游戏结束！");
						canPlay = false;
					}
				}
				else
				{
					whiteTime--;
					if(whiteTime == 0)
					{
						JOptionPane.showMessageDialog(this, "白方超时，游戏结束！");
						canPlay = false;
					}
				}
				blackMessage = blackTime/3600+":"+(blackTime%3600)/60+":"+blackTime%60;
				whiteMessage = whiteTime/3600+":"+(whiteTime%3600)/60+":"+whiteTime%60;
				this.repaint();
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					// TODO 自动生成的 catch 块
					e.printStackTrace();
				}
				
			}
		}
		else
			this.repaint();
	}
	
}
