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
	//������ǩ
	private JLabel a1;
	private JLabel a2;
	private JLabel a3;
	private JLabel a4;
	//�����ı������
	private JTextField b1;         //������
	private JTextField b2;         //��������
	private JTextField b3;         //��������
	//��ť
	private JButton c1;
	//��ʾ�ı�����
	private JTextArea d1;
	
	private JPanel toolbar = new JPanel();
	
	//�����б��
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
				(height - Windowsheight) / 2); // ���ھ���
		setTitle("�������Ӧ�ó���");
		CreatComponent();
		LayoutComponent();
		registerHandlers();
	}

	

	private void CreatComponent() {
		// TODO �Զ����ɵķ������
		a1=new JLabel("������");
		a2=new JLabel("������Ϣ");
		a3=new JLabel("��������");
		
		b1=new JTextField(10);
		b2=new JTextField(10);
		b3=new JTextField(10);
		
		c1=new JButton("����");
		d1=new JTextArea();
		
		jb=new JComboBox();
	}
	
	
	private void LayoutComponent() {
		// TODO �Զ����ɵķ������
		setLayout(new FlowLayout());
		JPanel panel1 = new JPanel();         //�������
		panel1.add(a1);
		panel1.add(b1);
		JPanel panel2 = new JPanel();
		panel2.add(a2);
		panel2.add(b2);
		JPanel panel3 = new JPanel();
		panel3.add(a3);
		panel3.add(b3);
		
		
		Object[] types={"1����","3����","6����","12����"};
		DefaultComboBoxModel model=new DefaultComboBoxModel((Object[]) types);
        jb.setModel(model);
        
		
		
		
		JPanel panel4 = new JPanel(new GridLayout(3, 1));
		panel4.add(panel1);
		panel4.add(panel2);
		panel4.add(panel3);
		
		JScrollPane panel5 = new JScrollPane(d1);           //����������� scrollable ��ͼ
		panel5.setPreferredSize(new Dimension(400, 200));
		
		toolbar.add(c1);
		toolbar.add(jb);
		
		//�Ű�
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
					JOptionPane.showMessageDialog(null, "����Ĵ�������������������");
					principle=0;
				}
				rate=Double.parseDouble(b2.getText());
				if(rate<0){
					JOptionPane.showMessageDialog(null, "����Ĵ���������������������");
					rate=0;
				}
				time=Double.parseDouble(b3.getText());
				if(time<0){
					JOptionPane.showMessageDialog(null, "����Ļ���������������������");
					time=0;
				}
			} catch (Exception e2) {
				// TODO: handle exception
				JOptionPane.showMessageDialog(null, "����������������������");
			}
			
		
			CalculjbButton();
			money=principle*rate*time+principle;
			double money2=money;
			double data=12/jbButton*time;
			money=money/data;
			output="��Ϣ:"+money2+"     ÿ"+jbButton+"������Ҫ��"+money+"Ԫ";
			d1.setText(output);
		}
	}
	
	private void CalculjbButton(){
		String key=(String) jb.getSelectedItem();
		if ( key== "1����") { // �����������ʼ��Ϸ��ť�������Mainclass()����������Ϸ
			jbButton=1;

		} else if (key == "3����") // ���ǵ��������ť�����˳���¼����
		{
			jbButton=3;

		} else if (key == "6����") {

			jbButton=6;
		} else if (key == "12����") {

			jbButton=12;
		}
	}
	
	
	

}
