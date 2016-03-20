package Fuli;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;


public class Credit extends JFrame{
	//三个标签
	private JLabel a1;
	private JLabel a2;
	private JLabel a3;
	private JLabel a4;
	//三个文本输入框
	private JTextField b1;         //贷款金额
	private JTextField b2;         //贷款利率
	private JTextField b3;         //贷款期限
	//按钮
	private JButton c1;
	//显示文本区域
	private JTextArea d1;
	
	private JPanel toolbar = new JPanel();
	
	//下拉列表框
	private JComboBox jb;
	int jbButton=12;
	public Credit(){
		int Windowswidth = 500;
		int Windowsheight = 400;
		setVisible(true);
		setSize(Windowswidth, Windowsheight);
		setResizable(false);
		int width = Toolkit.getDefaultToolkit().getScreenSize().width;
		int height = Toolkit.getDefaultToolkit().getScreenSize().height;
		setLocation((width - Windowswidth) / 2,
				(height - Windowsheight) / 2); // 窗口居中
		setTitle("复利存款应用程序");
		CreatComponent();
		LayoutComponent();
		registerHandlers();
	}

	

	private void CreatComponent() {
		// TODO 自动生成的方法存根
		a1=new JLabel("贷款金额");
		a2=new JLabel("贷款利息");
		a3=new JLabel("贷款期限");
		
		b1=new JTextField(10);
		b2=new JTextField(10);
		b3=new JTextField(10);
		
		c1=new JButton("计算");
		d1=new JTextArea();
		
		jb=new JComboBox();
	}
	
	
	private void LayoutComponent() {
		// TODO 自动生成的方法存根
		setLayout(new FlowLayout());
		JPanel panel1 = new JPanel();         //面板容器
		panel1.add(a1);
		panel1.add(b1);
		JPanel panel2 = new JPanel();
		panel2.add(a2);
		panel2.add(b2);
		JPanel panel3 = new JPanel();
		panel3.add(a3);
		panel3.add(b3);
		
		
		Object[] types={"1个月","3个月","6个月","12个月"};
		DefaultComboBoxModel model=new DefaultComboBoxModel((Object[]) types);
        jb.setModel(model);
        
		
		
		
		JPanel panel4 = new JPanel(new GridLayout(3, 1));
		panel4.add(panel1);
		panel4.add(panel2);
		panel4.add(panel3);
		
		JScrollPane panel5 = new JScrollPane(d1);           //轻量级组件的 scrollable 视图
		panel5.setPreferredSize(new Dimension(400, 200));
		
		toolbar.add(c1);
		toolbar.add(jb);
		
		//排版
		add(panel4);
		add(toolbar, BorderLayout.EAST);
		add(panel5);
		
	}
	
	
	
	private void registerHandlers() {
		C1ActionEventHander hander1 = new C1ActionEventHander();
		c1.addActionListener(hander1);
		
	}
	
	private class C1ActionEventHander implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			String output = null;
			double principle=0;
			double rate=0;
			double time=0;
			double money=0;
			try {
				principle=Double.parseDouble(b1.getText());
				if(principle<0){
					JOptionPane.showMessageDialog(null, "输入的贷款金额有误，请重新输入");
					principle=0;
				}
				rate=Double.parseDouble(b2.getText());
				if(rate<0){
					JOptionPane.showMessageDialog(null, "输入的贷款利率有误，请重新输入");
					rate=0;
				}
				time=Double.parseDouble(b3.getText());
				if(time<0){
					JOptionPane.showMessageDialog(null, "输入的还款期限有误，请重新输入");
					time=0;
				}
			} catch (Exception e2) {
				// TODO: handle exception
				JOptionPane.showMessageDialog(null, "数据输入有误，请重新输入");
			}
			
		
			CalculjbButton();
			money=principle*rate*time+principle;
			double money2=money;
			double data=12/jbButton*time;
			money=money/data;
			output="本息:"+money2+"     每"+jbButton+"个月需要付"+money+"元";
			d1.setText(output);
		}
	}
	
	private void CalculjbButton(){
		String key=(String) jb.getSelectedItem();
		if ( key== "1个月") { // 若是鼠标点击开始游戏按钮，则调用Mainclass()函数进入游戏
			jbButton=1;

		} else if (key == "3个月") // 若是点击结束按钮，则退出登录界面
		{
			jbButton=3;

		} else if (key == "6个月") {

			jbButton=6;
		} else if (key == "12个月") {

			jbButton=12;
		}
	}
	
	
	

}
