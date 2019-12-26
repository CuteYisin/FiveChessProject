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
		this.setVisible(true);        							//�����Ƿ���ʾ
		this.setTitle("���");									//�������
		this.setSize(1000, 700);								//�����С
		//jf.setLocation(200, 100);								//�����ʼλ��
		this.setLocation((width-1000)/2, (height-700)/2);
		this.setResizable(true);								//���ô����Ƿ���Ըı��С
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);	//����رշ�ʽ���رմ���ʱͬʱ��������
		
		this.addMouseListener(this);
		
		
	}

	
	public void paint(Graphics g)
	{
		
		BufferedImage image = null;
		try {
			image = ImageIO.read(new File("C:/Users/Dell/Pictures/Saved Pictures/����.jpg"));
		} catch (IOException e) {
			// TODO �Զ����ɵ� catch ��
			e.printStackTrace();
		}
		g.drawImage(image, 8, 20, this);
		/*g.drawString("��������Ϸ", 30, 40);
		//�����ַ���
		g.drawOval(20, 40, 40, 40);
		//���ƿ���Բ
		g.fillOval(30, 40, 40, 40);
		//����ʵ��Բ
		g.drawLine(20, 40, 80, 40);
		//����һ����
		g.drawRect(20, 40, 40, 20);
		//���ƿ��ľ���
		g.fillRect(30, 40, 40, 20);
		//����ʵ�ľ���
		g.setColor(Color.white);
		//�ı仭����ɫ
		*/
	}
	
	@Override
	public void mouseClicked(MouseEvent arg0) {
		// ����������¼�
		// TODO �Զ����ɵķ������
		//System.out.println("�����");
		//JOptionPane.showMessageDialog(this, "�����");
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// �����������¼�
		// TODO �Զ����ɵķ������
		//System.out.println("������");
		//JOptionPane.showMessageDialog(this, "������");
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// ��������뿪�¼�
		// TODO �Զ����ɵķ������
		//System.out.println("����뿪");
		//JOptionPane.showMessageDialog(this, "����뿪");
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// ������갴���¼�
		// TODO �Զ����ɵķ������
		//System.out.println("��갴��");
		//System.out.println("���λ�ã�  X --> "+arg0.getX());
		//System.out.println("���λ�ã�  Y --> "+arg0.getY());
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// �������̧���¼�
		// TODO �Զ����ɵķ������
		//System.out.println("���̧��");
	}
}
