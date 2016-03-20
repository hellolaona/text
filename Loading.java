package Fuli;
import java.awt.event.*;  
import java.awt.*;   

import javax.swing.*;
public class Loading extends JFrame   //设置登录界面
{
	    //设置界面宽高
	    int centerX;	                     			
	    int centerY;

	    //初始化菜单栏
		private JMenuBar menu=new JMenuBar();
		private JButton deposit=new JButton("存款");
		private JButton credit=new JButton("贷款");
		private JButton exit=new JButton("退出");

		public Loading()
		{
			setTitle("存款投资贷款计算器(志杰)");

			MyPanel mp=new MyPanel();						//图片面板类
			menu.add(deposit);
			menu.add(credit);
			menu.add(exit);
			

			//添加图片面板到顶层容器
			add(mp);                           
			setJMenuBar(menu);
			MyItemListener list=new MyItemListener();  //设置监听器
			deposit.addActionListener(list);
			exit.addActionListener(list);
			credit.addActionListener(list);
			setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);     //结束进程
			
			

			centerX=Toolkit.getDefaultToolkit().getScreenSize().width;				
			centerY=Toolkit.getDefaultToolkit().getScreenSize().height;
	
			//x：组件在容器X轴上的起点 y：组件在容器Y轴上的起点 width：组件的长度 height：组件的宽度
			setBounds(centerX/4, centerY/5, centerX/2,centerY/2 );
			setResizable(false);          //改变窗体
			setVisible(true);			
		
		}
		private class MyItemListener implements ActionListener//处理事件
		{
			 public void actionPerformed(ActionEvent e){  
			Object bj = e.getSource(); // 获得事件源
			if (bj == deposit) { // 若是鼠标点击开始游戏按钮，则调用Mainclass()函数进入游戏

				new Fuli();
			} else if (bj == exit) // 若是点击结束按钮，则退出登录界面
				System.exit(0);
			else if(bj==credit){
				new Credit();
			}
		}

	}
		private class MyPanel extends JPanel 
		{
			
			//写了一个带参数的构造方法.但又想让这个类在没有参数的情况下也能实例化,构造空的构造方法用作反射，Java内部会有调用，一般都通过反射实现，
			public MyPanel(){}
			protected void paintComponent(Graphics g)	//重写绘画组件方法
			{
				//就是java.net.URL类 声明一个它的对象imgURL  
				//而这个对象是指向JButtonApp.class.getResource(path) 也就是imgURL  的值就是JButtonApp.class.getResource(path)
				java.net.URL imgURL =getClass().getResource("aaa.jpg");
				ImageIcon icon =new ImageIcon(imgURL);		
				
				//对象，坐标x,y，图片宽度和高度，转换图像要通知的对象
				g.drawImage(icon.getImage(),0,0,getSize().width,getSize().height,this);
				
			}
		}
		
		
		public static void main(String[] args) {

			new Loading();
			
		}
}



