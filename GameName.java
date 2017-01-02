import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URI;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

import sun.org.mozilla.javascript.internal.ast.TryStatement;


public class GameName {

	private static JFrame jFrame = new JFrame("���������");
	private static JPanel jPanel = new JPanel();
	private static JTextField jTextField = new JTextField(8);
	private static JButton jButton = new JButton("   ~~~~~~���ҵ���~~~~~~  ");
	
	private static ArrayList<String> nameArr = new ArrayList<String>();
	private static Random random = new Random();
	
	public static void main(String[] args) throws IOException {
		GameName r = new GameName();
		r.init();
		r.initData();
		r.selectName();
	}

	//��ʼ�����
	public void init() {
		jFrame.setBounds(new Rectangle(400,300,400,100));
		jFrame.setVisible(true);
		//����Ϊ��С���ɸı�
		jFrame.setResizable(false);
		jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		jTextField.setFont(new Font("Dialog",Font.BOLD,30));
		jTextField.setEditable(false);
		jTextField.setText("     ~�� ��~");
		
		jPanel.setLayout(new BorderLayout());
		
		jButton.setBackground(new Color(100,100,200));
		//����ť�ı߿���Ϊ��
		jButton.setBorderPainted(false);
		
		jFrame.add(jPanel);
		jPanel.add(jTextField,BorderLayout.WEST);
		jFrame.add(jButton,BorderLayout.EAST);
	}
	
	//��ʼ��ѧ����Ϣ
	public void initData() throws IOException{
		File file = new File(".\\name.txt");
		FileReader fileReader = new FileReader(file);
		BufferedReader bufferedReader = new BufferedReader(fileReader);
		
		String name = null;
		while ((name = bufferedReader.readLine()) != null) {
			nameArr.add(name);
		}
		bufferedReader.close();
	}
	public void selectName(){
		jButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int index = random.nextInt(nameArr.size());
				String name = nameArr.get(index);
				jTextField.setText("      "+name);
			}
		});
	}
}
