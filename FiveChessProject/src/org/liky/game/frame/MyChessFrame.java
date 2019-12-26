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

public class MyChessFrame extends JFrame implements MouseListener {
	public MyChessFrame(){
		int width=Toolkit.getDefaultToolkit().getScreenSize().width;
		int height=Toolkit.getDefaultToolkit().getScreenSize().height;
		this.setVisible(true);        							//窗体是否显示
		this.setTitle("棋道");									//窗体标题
		this.setSize(1000, 700);								//窗体大小
		//jf.setLocation(200, 100);								//窗体初始位置
		this.setLocation((width-1000)/2, (height-700)/2);
		this.setResizable(true);								//设置窗体是否可以改变大小
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);	//窗体关闭方式，关闭窗体时同时结束程序
		
		this.addMouseListener(this);
		
		
	}

	
	public void paint(Graphics g)
	{
		
		BufferedImage image = null;
		try {
			image = ImageIO.read(new File("C:/Users/Dell/Pictures/Saved Pictures/背景.jpg"));
		} catch (IOException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		g.drawImage(image, 8, 20, this);
		/*g.drawString("五子棋游戏", 30, 40);
		//绘制字符串
		g.drawOval(20, 40, 40, 40);
		//绘制空心圆
		g.fillOval(30, 40, 40, 40);
		//绘制实心圆
		g.drawLine(20, 40, 80, 40);
		//绘制一条线
		g.drawRect(20, 40, 40, 20);
		//绘制空心矩形
		g.fillRect(30, 40, 40, 20);
		//绘制实心矩形
		g.setColor(Color.white);
		//改变画笔颜色
		*/
	}
	
	@Override
	public void mouseClicked(MouseEvent arg0) {
		// 监听鼠标点击事件
		// TODO 自动生成的方法存根
		//System.out.println("鼠标点击");
		//JOptionPane.showMessageDialog(this, "鼠标点击");
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// 监听鼠标进入事件
		// TODO 自动生成的方法存根
		//System.out.println("鼠标进入");
		//JOptionPane.showMessageDialog(this, "鼠标进入");
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// 监听鼠标离开事件
		// TODO 自动生成的方法存根
		//System.out.println("鼠标离开");
		//JOptionPane.showMessageDialog(this, "鼠标离开");
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// 监听鼠标按下事件
		// TODO 自动生成的方法存根
		//System.out.println("鼠标按下");
		//System.out.println("点击位置：  X --> "+arg0.getX());
		//System.out.println("点击位置：  Y --> "+arg0.getY());
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// 监听鼠标抬起事件
		// TODO 自动生成的方法存根
		//System.out.println("鼠标抬起");
	}
}
