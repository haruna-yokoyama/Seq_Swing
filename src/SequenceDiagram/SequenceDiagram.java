package SequenceDiagram;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import javax.swing.border.EmptyBorder;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;

import SequenceDiagram.Creater.MyMouseAdapter;

import com.mxgraph.swing.mxGraphComponent;
import com.mxgraph.view.mxGraph;
import com.sun.jdi.Field;
import com.sun.jdi.Location;
import com.sun.jdi.Value;
import com.sun.tools.example.trace.Trace;

public class SequenceDiagram extends JFrame implements ActionListener {

	static public ResultTrace resultTrace;

	private JPanel contentPane;
	private JFrame frame;
	private JButton btnNewButton;
	private JButton button;
	private JLabel lblNewLabel;
	// private JTextArea textArea;
	private JTextPane textPane;

	private List<String> declaringType = new ArrayList<>();
	private List<String> methodName = new ArrayList<>();
	private List<String> returnType = new ArrayList<>();
	private List<String> argumentType = new ArrayList<>();
	private List<Location> lineLocation = new ArrayList<>();

	private Field fieldName;
	private Value valueName;

	public Trace trace;
	public Creater creater;

	static String str;
	static String[] argss;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		argss = args;
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SequenceDiagram frame = new SequenceDiagram();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public SequenceDiagram() {
		initialize();
	}

	private void initialize() {

		contentPane = new JPanel();
		setBounds(100, 100, 600, 900);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		lblNewLabel = new JLabel("ファイルを選択");
		lblNewLabel.setBounds(12, 10, 113, 13);
		getContentPane().add(lblNewLabel);

		btnNewButton = new JButton("開く"); // 「開く」ボタン
		// textArea = new JTextArea();
		textPane = new JTextPane();

		btnNewButton.setBounds(12, 33, 91, 21);
		// btnNewButton.setAction(action);
		btnNewButton.addActionListener(this);

		getContentPane().add(btnNewButton);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(12, 64, 482, 500);
		contentPane.add(scrollPane);

		// JTextArea textArea = new JTextArea();
		// textArea.setWrapStyleWord(true);
		// textArea.setLineWrap(true);
		// scrollPane.setViewportView(textArea);
		// textPane.setWrapStyleWord(true);
		// textPane.setLineWrap(true);
		scrollPane.setViewportView(textPane);

		String[] tracePrograms = { "HelloWorld" };
		System.out.println("iiiiiiiiiiiiiiiiiii");
		trace = new Trace(tracePrograms);
		System.out.println("uuuuuuuuuuuuuuuuu");

		button = new JButton("シーケンス図作成"); // 「シーケンス図作成」ボタン
		button.setBounds(121, 33, 177, 21);
		// button.setAction(action_1);
		button.addActionListener(this);

		getContentPane().add(button);
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource().equals(btnNewButton)) {// newボタンが押されたときのアクション
			JFileChooser filechooser = new JFileChooser(); // ファイル選択用クラス
			int selected = filechooser.showOpenDialog(frame); // 「開く」ダイアログ表示
			if (selected == JFileChooser.APPROVE_OPTION) {
				File file = filechooser.getSelectedFile();
				lblNewLabel.setText(file.getName());
				// textArea.setText(Filehandler.read(file.getPath())); //
				// ファイルの中身を表示
				textPane.setText(Filehandler.read(file.getPath())); // ファイルの中身を表示

			}
			getContentPane().add(lblNewLabel);
			getContentPane().add(btnNewButton);
		}

		if (e.getSource().equals(button)) {// buttonが押されたときのアクション
			System.out.println("1=======resultTrace========");
			// String[] tracePrograms= {"HelloWorld"};
			// //trace.javaからトレース情報を持ってくる
			System.out.println("2=======resultTrace========");
			// Trace trace = new Trace(tracePrograms);
			System.out.println("3=======resultTrace========");

			declaringType = trace.getDeclaringType();
			methodName = trace.getMethodName();
			returnType = trace.getReturnType();
			argumentType = trace.getArgumentType();
			lineLocation = trace.getLineLication();
			fieldName = trace.getFieldName();
			valueName = trace.getValueName();

			System.out.println("aaaaaaaaaaaaaa : " + declaringType);

			// -------------------------------------------------
			File newfile1 = new File("C:\\Users\\cs12097\\Desktop\\aaaaaa.txt");
			try {
				if (newfile1.createNewFile()) {
					System.out.println("ファイルの作成に成功しました");
					FileWriter filewriter = new FileWriter(newfile1);
					filewriter.write(declaringType + " , ");
					filewriter.write("\n");
					filewriter.write(methodName + " , ");
					filewriter.write("\n");
					filewriter.write(returnType + " , ");
					filewriter.write("\n");
					filewriter.write(argumentType + " , ");
					filewriter.write("\n");
					filewriter.write(valueName + ",");
					filewriter.write(fieldName + ",");
					filewriter.close();
				} else {
					System.out.println("ファイルの作成に失敗しました");
				}
			} catch (IOException o) {
				System.out.println(o);
			}
			// ------------------------------------------------------------

			resultTrace = new ResultTrace(); //
			declaringType = resultTrace.setDeclaringType(declaringType);
			methodName = resultTrace.setMethodName(methodName);
			returnType = resultTrace.setReturnType(returnType);
			argumentType = resultTrace.setArgumentType(argumentType);
			lineLocation = resultTrace.setLineLocation(lineLocation);
			fieldName = resultTrace.setFieldName(fieldName);
			valueName = resultTrace.setValueName(valueName);
			System.out.println("4=======resultTrace========");

			inputList inputlist = new inputList(e); // ソースコードをListに格納
			readCSV readcsv = new readCSV(e); // トレース情報をCSVファイルに保存、Listにも格納
			System.out.println("5=======resultTrace========");

			// -------------------------------------------------
			File newfile = new File("C:\\Users\\cs12097\\Desktop\\bbbbb.txt");
			try {
				if (newfile.createNewFile()) {
					System.out.println("ファイルの作成に成功しました");
					FileWriter filewriter = new FileWriter(newfile);
					filewriter.write(declaringType + " , ");
					filewriter.write("\n");
					filewriter.write(methodName + " , ");
					filewriter.write("\n");
					filewriter.write(returnType + " , ");
					filewriter.write("\n");
					filewriter.write(argumentType + " , ");
					filewriter.write("\n");
					filewriter.write(valueName + ",");
					filewriter.write(fieldName + ",");
					filewriter.close();
				} else {
					System.out.println("ファイルの作成に失敗しました");
				}
			} catch (IOException o) {
				System.out.println(o);
			}
			// ------------------------------------------------------------
			creater = new Creater(this); // シーケンス図作成
		}
	}

	// 色を付けるメソッド
	public void giveColor(String cell, List<Location> line, List<String> methodName) {
		mxGraph mxgraph = new mxGraph();
		mxGraphComponent graph = new mxGraphComponent(mxgraph);
		MyMouseAdapter mouseAdapter = creater.new MyMouseAdapter(graph, mxgraph);

		String text = textPane.getText();
		StyledDocument doc = (StyledDocument) textPane.getDocument();
		String[] number = { "main", "new HelloWorld();", "hello();", "calclator();", "calclate();" };

		//全ての属性を消す
		SimpleAttributeSet plane = new SimpleAttributeSet();
		doc.setCharacterAttributes(0, text.length(), plane, true);

		SimpleAttributeSet attr = new SimpleAttributeSet(); // キーワードを強調表示
		StyleConstants.setBackground(attr, Color.YELLOW); // 背景の色
		for (int i = 0; i < number.length; i++) {
			if (cell.equals("main")) {
				//String num = number[0];
				String num = methodName.get(0);
				int pos = text.indexOf(num);
				while (pos != -1) {
					doc.setCharacterAttributes(pos, num.length(), attr, true);
					pos = text.indexOf(num, pos + num.length());
				}
			}
			if (cell.equals("<init>")) {
				//String num = number[0];
				String num = methodName.get(1);
				int pos = text.indexOf(num);
				while (pos != -1) {
					doc.setCharacterAttributes(pos, num.length(), attr, true);
					pos = text.indexOf(num, pos + num.length());
				}
			}
			if(cell.equals("hello")) {
				//String num = number[1];
				String num = methodName.get(2);
				int pos = text.indexOf(num);
				while (pos != -1) {
					doc.setCharacterAttributes(pos, num.length(), attr, true);
					pos = text.indexOf(num, pos + num.length());
				}
			}
			if(cell.equals("calclator")) {
				//String num = number[2];
				String num = methodName.get(3);
				int pos = text.indexOf(num);
				while (pos != -1) {
					doc.setCharacterAttributes(pos, num.length(), attr, true);
					pos = text.indexOf(num, pos + num.length());
				}
			}
			if(cell.equals("calclate")) {
				//String num = number[3];
				String num = methodName.get(4);
				int pos = text.indexOf(num);
				while (pos != -1) {
					doc.setCharacterAttributes(pos, num.length(), attr, true);
					pos = text.indexOf(num, pos + num.length());
				}
			}
		}

		// -------------------------------------------------
		File newfile1 = new File("C:\\Users\\cs12097\\Desktop\\ddddddd.txt");
		try {
			if (newfile1.createNewFile()) {
				System.out.println("ファイルの作成に成功しました");
				FileWriter filewriter = new FileWriter(newfile1);
				filewriter.write("create file : " + cell + " : " + line);
				filewriter.close();
			} else {
				System.out.println("ファイルの作成に失敗しました");
			}
		} catch (IOException o) {
			System.out.println(o);
		}
		// ------------------------------------------------------------
	}

	public ResultTrace getResultTrace() {
		return this.resultTrace;
	}

}
