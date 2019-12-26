package org.liky.game.test;

import javax.swing.JOptionPane;

import org.liky.game.frame.FiveChessFrame;
import org.liky.game.frame.MyChessFrame;

public class Test {
	public static void main(String[] args){
		//MyChessFrame mf=new MyChessFrame();
		//JOptionPane.showMessageDialog(mf, "我的信息");		//显示一个消息对话框，主要用来提示信息
		
		
		/*int result = JOptionPane.showConfirmDialog(mf, "我的确认信息，现在要开始游戏吗？");
		//显示一个确认对话框，用户选择后根据返回结果进行判断
		if(result==0)
		{
			JOptionPane.showMessageDialog(mf, "游戏开始");
		}
		else if(result==1)
		{
			JOptionPane.showMessageDialog(mf, "游戏结束");
		}
		else
		{
			JOptionPane.showMessageDialog(mf, "请重新选择");
		}
		
		
		String username = JOptionPane.showInputDialog("请输入你的姓名：  ");
		//显示一个信息输入对话框，作用是用来保存用户输入的信息
		if(username != null)
		{
			JOptionPane.showMessageDialog(mf, "输入的姓名为： "+username);
		}
		else
		{
			JOptionPane.showMessageDialog(mf, "请重新输入你的姓名！");
		}*/
		FiveChessFrame ff = new FiveChessFrame();
	}
}
