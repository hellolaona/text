package Fuli;
import java.awt.event.*;  
import java.awt.*;   

import javax.swing.*;
public class Loading extends JFrame   //���õ�¼����
{
	    //���ý�����
	    int centerX;	                     			
	    int centerY;

	    //��ʼ���˵���
		private JMenuBar menu=new JMenuBar();
		private JButton deposit=new JButton("���");
		private JButton credit=new JButton("����");
		private JButton exit=new JButton("�˳�");

		public Loading()
		{
			setTitle("���Ͷ�ʴ��������(־��)");

			MyPanel mp=new MyPanel();						//ͼƬ�����
			menu.add(deposit);
			menu.add(credit);
			menu.add(exit);
			

			//���ͼƬ��嵽��������
			add(mp);                           
			setJMenuBar(menu);
			MyItemListener list=new MyItemListener();  //���ü�����
			deposit.addActionListener(list);
			exit.addActionListener(list);
			credit.addActionListener(list);
			setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);     //��������
			
			

			centerX=Toolkit.getDefaultToolkit().getScreenSize().width;				
			centerY=Toolkit.getDefaultToolkit().getScreenSize().height;
	
			//x�����������X���ϵ���� y�����������Y���ϵ���� width������ĳ��� height������Ŀ��
			setBounds(centerX/4, centerY/5, centerX/2,centerY/2 );
			setResizable(false);          //�ı䴰��
			setVisible(true);			
		
		}
		private class MyItemListener implements ActionListener//�����¼�
		{
			 public void actionPerformed(ActionEvent e){  
			Object bj = e.getSource(); // ����¼�Դ
			if (bj == deposit) { // �����������ʼ��Ϸ��ť�������Mainclass()����������Ϸ

				new Fuli();
			} else if (bj == exit) // ���ǵ��������ť�����˳���¼����
				System.exit(0);
			else if(bj==credit){
				new Credit();
			}
		}

	}
		private class MyPanel extends JPanel 
		{
			
			//д��һ���������Ĺ��췽��.���������������û�в����������Ҳ��ʵ����,����յĹ��췽���������䣬Java�ڲ����е��ã�һ�㶼ͨ������ʵ�֣�
			public MyPanel(){}
			protected void paintComponent(Graphics g)	//��д�滭�������
			{
				//����java.net.URL�� ����һ�����Ķ���imgURL  
				//�����������ָ��JButtonApp.class.getResource(path) Ҳ����imgURL  ��ֵ����JButtonApp.class.getResource(path)
				java.net.URL imgURL =getClass().getResource("aaa.jpg");
				ImageIcon icon =new ImageIcon(imgURL);		
				
				//��������x,y��ͼƬ��Ⱥ͸߶ȣ�ת��ͼ��Ҫ֪ͨ�Ķ���
				g.drawImage(icon.getImage(),0,0,getSize().width,getSize().height,this);
				
			}
		}
		
		
		public static void main(String[] args) {

			new Loading();
			
		}
}



