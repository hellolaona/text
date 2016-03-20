package Fuli;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.NumberFormat;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class Fuli extends JFrame {

	private static final long serialVersionUID = 3347254632537686808L;
	private JLabel a1; // 标签
	private JLabel a2;
	private JLabel a3;
	private JLabel a4;
	private JTextField b1; // 本金
	private JTextField b2; // 利率
	private JTextField b3; // 年份
	private JTextField b4; // 期望值
	private JButton c1;
	private JButton c2;
	private JButton c3;

	private JTextArea text; // 显示纯文本的多行区域

	private JPanel toolbar = new JPanel();

	JRadioButtonMenuItem mrButton = new JRadioButtonMenuItem("每年添加一定的本金");

	public Fuli() {
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
		creatComponents(); // 创建零件
		layoutComponents(); // 设计零件
		registerHandlers(); // 鼠标监控
//		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	private void creatComponents() {
		a1 = new JLabel("本 金 ");
		a2 = new JLabel("年利率 ");
		a3 = new JLabel("年 数 ");
		a4 = new JLabel("期望值");
		b1 = new JTextField(10);
		b2 = new JTextField(10);
		b3 = new JTextField(10);
		b4 = new JTextField(10);
		c1 = new JButton("复利");
		c2 = new JButton("清除");
		c3 = new JButton("单利");
		text = new JTextArea();

	}

	private void layoutComponents() {
		setLayout(new FlowLayout());
		JPanel panel1 = new JPanel();
		panel1.add(a1);
		panel1.add(b1);
		JPanel panel2 = new JPanel();
		panel2.add(a2);
		panel2.add(b2);
		JPanel panel3 = new JPanel();
		panel3.add(a3);
		panel3.add(b3);
		JPanel panel6 = new JPanel();
		panel6.add(a4);
		panel6.add(b4);

		JPanel leftpanel = new JPanel(new GridLayout(3, 1));  //行、列
		leftpanel.add(panel1);
		leftpanel.add(panel2);
		leftpanel.add(panel3);

		leftpanel.add(panel6);
		JScrollPane panel5 = new JScrollPane(text);
		panel5.setPreferredSize(new Dimension(400, 200));

		toolbar.add(c1);
		toolbar.add(c2);
		toolbar.add(c3);

		
		add(leftpanel);
		add(toolbar, BorderLayout.SOUTH);
		add(panel5);
		
	}

	private void registerHandlers() {
		c1ActionEventHander hander1 = new c1ActionEventHander();
		c1.addActionListener(hander1);
		c2ActionEventHander hander2 = new c2ActionEventHander();
		c2.addActionListener(hander2);
		c3ActionEventHander hander3 = new c3ActionEventHander();
		c3.addActionListener(hander3);

	}

	private class c1ActionEventHander implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			double principal = 0;
			double amount = 0;
			double rate = 0;
			int n = 0;
			NumberFormat currencyformatter = NumberFormat.getCurrencyInstance(); // 字符串转化为数字
			String output = "年" + "/" + "复利存款";
			int year = 1;
			int result;

			if (b1.getText().equals("") && b2.getText() != null
					&& b3.getText() != null && b4.getText() != null) {

				try {
					rate = Double.parseDouble(b2.getText());
					if(rate<0){
						JOptionPane.showMessageDialog(null, "利率输入有误，请重新输入数字");
						rate=0;
					}
					n = Integer.parseInt(b3.getText());
					if(n<0){
						JOptionPane.showMessageDialog(null, "年份输入有误，请重新输入数字");
						n=0;
					}
					amount = Double.parseDouble(b4.getText());
					if(amount<0){
						JOptionPane.showMessageDialog(null, "期待值输入有误，请重新输入数字");
						amount=0;
					}
				} catch (Exception e2) {
					// TODO: handle exception
					JOptionPane.showMessageDialog(null, "数据输入有误，请重新输入数字");

				}

				principal = 0;
				while (year <= n) {
					principal = amount / (Math.pow(1 + rate, year));
					year = year + 1;
				}

				output = "本金" + currencyformatter.format(principal) + "\n";
				text.setText(output);

			}

			else if (b2.getText().equals("") && b1.getText() != null
					&& b3.getText() != null && b4.getText() != null) // 求利率
			{
				try {
					principal = Double.parseDouble(b1.getText()); // b1本金
					if(principal<0){
						JOptionPane.showMessageDialog(null, "本金输入有误，请重新输入数字");
						principal=0;
					}
					n = Integer.parseInt(b3.getText()); // b3年份
					if(n<0){
						JOptionPane.showMessageDialog(null, "年份输入有误，请重新输入数字");
						n=0;
					}
					amount = Double.parseDouble(b4.getText()); // b4期望值
					if(amount<0){
						JOptionPane.showMessageDialog(null, "期待值输入有误，请重新输入数字");
						amount=0;
					}
				} catch (Exception e2) {
					// TODO: handle exception
					JOptionPane.showMessageDialog(null, "数据输入有误，请重新输入数字");
				}

				rate = java.lang.StrictMath.pow(amount / principal, 1.0 / n) - 1;
				output = "利率" + rate + "\n";
				text.setText(output);
			}

			else if (b3.getText().equals("") && b1.getText() != null
					&& b2.getText() != null && b4.getText() != null) // 求年份
			{
				try {
					principal = Double.parseDouble(b1.getText()); // b1本金
					if(principal<0){
						JOptionPane.showMessageDialog(null, "本金输入有误，请重新输入数字");
						principal=0;
					}
					amount = Double.parseDouble(b4.getText()); // b4期望值
					if(amount<0){
						JOptionPane.showMessageDialog(null, "期待值输入有误，请重新输入数字");
						amount=0;
					}
					rate = Double.parseDouble(b2.getText());
					if(rate<0){
						JOptionPane.showMessageDialog(null, "利率输入有误，请重新输入数字");
						rate=0;
					}
				} catch (Exception e2) {
					// TODO: handle exception
					JOptionPane.showMessageDialog(null, "数据输入有误，请重新输入数字");
				}

		
				double year2=Math.log(amount/principal)/Math.log(1+rate);
				output = "至少年数" + year2 + "\n";
				text.setText(output);
			}

			else if (b4.getText().equals("") && b1.getText() != null
					&& b3.getText() != null && b2.getText() != null) // 求期望值
			{
				try {
					rate = Double.parseDouble(b2.getText());
					if(rate<0){
						JOptionPane.showMessageDialog(null, "利率输入有误，请重新输入数字");
						rate=0;
					}
					n = Integer.parseInt(b3.getText());
					if(n<0){
						JOptionPane.showMessageDialog(null, "年份输入有误，请重新输入数字");
						n=0;
					}
					principal = Double.parseDouble(b1.getText()); // b1本金
					if(principal<0){
						JOptionPane.showMessageDialog(null, "本金输入有误，请重新输入数字");
						rate=0;
					}
				} catch (Exception e2) {
					// TODO: handle exception
					JOptionPane.showMessageDialog(null, "数据输入有误，请重新输入数字");
				}

				double tempprincipal = principal;
				result = JOptionPane.showConfirmDialog(null, "是否每年添加本金");
				if (result == JOptionPane.YES_OPTION) {
					while (year <= n) {
						amount = principal * Math.pow(1 + rate, year)
								+ tempprincipal;
						output += String.valueOf(year) + "\t\t\t"
								+ currencyformatter.format(amount) + "\n";
						year = year + 1;
					}
				} else {
					while (year <= n) {
						amount = principal * Math.pow(1 + rate, year);
						output += String.valueOf(year) + "\t\t\t"
								+ currencyformatter.format(amount) + "\n";
						year = year + 1;
					}
				}

				text.setText(output);
			} else if (b1.getText() != null && b4.getText() != null
					&& b3.getText() != null && b2.getText() != null) {
				JOptionPane.showMessageDialog(null, "请删除一个数据");
			} else {
				JOptionPane.showMessageDialog(null, "请增加数据");
			}

		}
	}

	private class c2ActionEventHander implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			b1.setText("");
			b2.setText("");
			b3.setText("");
			b4.setText("");
			text.setText("");
		}
	}

	private class c3ActionEventHander implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			double principal = 0;
			double amount = 0;
			double rate = 0;
			int n = 0;
			NumberFormat currencyformatter = NumberFormat.getCurrencyInstance();
			String output = "年" + "/" + "单利存款";
			int year = 1;
			int result;
			if (b4.getText().equals("") && b1.getText() != null
					&& b3.getText() != null && b2.getText() != null) // 求期望值
			{
				try {
					rate = Double.parseDouble(b2.getText());
					if(rate<0){
						JOptionPane.showMessageDialog(null, "利率输入有误，请重新输入数字");
						rate=0;
					}
					n = Integer.parseInt(b3.getText());
					if(n<0){
						JOptionPane.showMessageDialog(null, "年份输入有误，请重新输入数字");
						n=0;
					}
					principal = Double.parseDouble(b1.getText()); // b1本金
					if(principal<0){
						JOptionPane.showMessageDialog(null, "本金输入有误，请重新输入数字");
						principal=0;
					}
				} catch (Exception e2) {
					// TODO: handle exception
					JOptionPane.showMessageDialog(null, "数据输入有误，请重新输入数字");
				}

				double tempprincipal = principal;
				result = JOptionPane.showConfirmDialog(null, "是否每年添加本金");
				if (result == JOptionPane.YES_OPTION) {
					while (year <= n) {
						amount = principal * (1 + rate * year) + tempprincipal;
						output += String.valueOf(year) + "\t\t\t"
								+ currencyformatter.format(amount) + "\n";
						year = year + 1;
					}
				} else {
					while (year <= n) {
						amount = principal * (1 + rate * year);
						output += String.valueOf(year) + "\t\t\t"
								+ currencyformatter.format(amount) + "\n";
						year = year + 1;
					}
				}

				text.setText(output);

			}

			else if (b1.getText().equals("") && b2.getText() != null
					&& b3.getText() != null && b4.getText() != null) { // 求本金

				try {
					rate = Double.parseDouble(b2.getText());
					if(rate<0){
						JOptionPane.showMessageDialog(null, "利率输入有误，请重新输入数字");
						rate=0;
					}
					n = Integer.parseInt(b3.getText());
					if(n<0){
						JOptionPane.showMessageDialog(null, "年份输入有误，请重新输入数字");
						n=0;
					}
					amount = Double.parseDouble(b4.getText());
					if(amount<0){
						JOptionPane.showMessageDialog(null, "期待值输入有误，请重新输入数字");
						amount=0;
					}
				} catch (Exception e2) {
					// TODO: handle exception
					JOptionPane.showMessageDialog(null, "数据输入有误，请重新输入数字");
				}

				principal = 0;
				while (year <= n) {
					principal = amount / (1 + rate * year);
					year = year + 1;
				}

				output = "本金" + currencyformatter.format(principal) + "\n";
				text.setText(output);
			}

			else if (b2.getText().equals("") && b1.getText() != null
					&& b3.getText() != null && b4.getText() != null) // 求利率
			{
				try {
					principal = Double.parseDouble(b1.getText()); // b1本金
					if(principal<0){
						JOptionPane.showMessageDialog(null, "本金输入有误，请重新输入数字");
						principal=0;
					}
					n = Integer.parseInt(b3.getText()); // b3年份
					if(n<0){
						JOptionPane.showMessageDialog(null, "年份输入有误，请重新输入数字");
						n=0;
					}
					amount = Double.parseDouble(b4.getText()); // b4期望值
					if(amount<0){
						JOptionPane.showMessageDialog(null, "期待值输入有误，请重新输入数字");
						amount=0;
					}
				} catch (Exception e2) {
					// TODO: handle exception
					JOptionPane.showMessageDialog(null, "数据输入有误，请重新输入数字");
				}

				rate = (amount / principal - 1) / n;
				output = "利率" + rate + "\n";
				text.setText(output);
			}

			else if (b3.getText().equals("") && b1.getText() != null
					&& b2.getText() != null && b4.getText() != null) // 求年份
			{
				try {
					principal = Double.parseDouble(b1.getText()); // b1本金
					if(principal<0){
						JOptionPane.showMessageDialog(null, "本金输入有误，请重新输入数字");
						principal=0;
					}
					amount = Double.parseDouble(b4.getText()); // b4期望值
					if(amount<0){
						JOptionPane.showMessageDialog(null, "期待值输入有误，请重新输入数字");
						amount=0;
					}
					rate = Double.parseDouble(b2.getText());
					if(rate<0){
						JOptionPane.showMessageDialog(null, "利率输入有误，请重新输入数字");
						rate=0;
					}
				} catch (Exception e2) {
					// TODO: handle exception
					JOptionPane.showMessageDialog(null, "数据输入有误，请重新输入数字");
				}

				double n2 = (amount / principal - 1) / rate;
				double n3 = n2;
				n = (int) n2;
				if (n3 - n > 0) {
					n += 1;
					output = "至少年数" + n + "\n";
					text.setText(output);
				} else {
					output = "刚好" + n + "年\n";
					text.setText(output);
				}
			}

			else if (b1.getText() != null && b4.getText() != null
					&& b3.getText() != null && b2.getText() != null) {
				JOptionPane.showMessageDialog(null, "请删除一个数据");
			} else {
				JOptionPane.showMessageDialog(null, "请增加数据");
			}
		}
	}

//	public static void main(String[] args) {
//
//		new Loading();
//		
//	}
}