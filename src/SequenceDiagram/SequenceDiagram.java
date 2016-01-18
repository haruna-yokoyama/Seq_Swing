package SequenceDiagram;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;

import com.sun.jdi.Field;
import com.sun.jdi.Value;
import com.sun.tools.example.trace.Trace;

public class SequenceDiagram extends JFrame implements ActionListener {


	private JPanel contentPane;
	private JFrame frame;
	private JFrame frmTexteditor;
	private JButton btnNewButton;
	private JButton button;
	private JLabel lblNewLabel;
	// private final Action action = new SwingAction();
	private JTextArea textArea;
	// private final Action action_1 = new SwingAction_1();

	private List<String> declaringType;
	private List<String> methodName;
	private List<String> returnType;
	private List<String> argumentType;
	private Field fieldName;
	private Value valueName;

	Trace trace;
	// public String[] PanelNames = {"mp", "SeqPanel"};
	// SequenceDiagram mp = new SequenceDiagram(this,PanelNames[0]);
	// SeqPanel sp = new SeqPanel(this,PanelNames[1]);

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

	/**
	 * Create the frame.
	 *
	 * @param panelNames2
	 * @param sequenceDiagram
	 */
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

		final JLabel lblNewLabel = new JLabel("ファイルを選択");
		lblNewLabel.setBounds(12, 10, 113, 13);
		contentPane.add(lblNewLabel);

		JButton btnNewButton = new JButton("開く"); // 「開く」ボタン
		final JTextArea textArea = new JTextArea();

		btnNewButton.setBounds(12, 33, 91, 21);
		// btnNewButton.setAction(action);
		btnNewButton.addActionListener(this);

		contentPane.add(btnNewButton);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(12, 64, 482, 250);
		contentPane.add(scrollPane);

		// JTextArea textArea = new JTextArea();
		textArea.setWrapStyleWord(true);
		textArea.setLineWrap(true);
		scrollPane.setViewportView(textArea);

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
				textArea.setText(Filehandler.read(file.getPath())); // ファイルの中身を表示
			}
		}

		if (e.getSource().equals(button)) {// buttonが押されたときのアクション
			System.out.println("1=======resultTracSe========");
			// String[] tracePrograms= {"HelloWorld"};
			// //trace.javaからトレース情報を持ってくる
			System.out.println("2=======resultTrace========");
			// Trace trace = new Trace(tracePrograms);
			System.out.println("3=======resultTrace========");

			declaringType = trace.getDeclaringType();
			methodName = trace.getMethodName();
			returnType = trace.getReturnType();
			argumentType = trace.getArgumentType();
			fieldName = trace.getFieldName();
			valueName = trace.getValueName();

			System.out.println("aaaaaaaaaaaaaa : " + declaringType);

			ResultTrace resultTrace = new ResultTrace(); //
			System.out.println("4=======resultTrace========");

			// System.out.println(declaringType.get(0) + "  " +
			// methodName.get(0) +
			// "  " + returnType.get(0) + "  " + argumentType.get(0) + "  " +
			// fieldName + "  " + valueName);
			Creater creater = new Creater(e); // シーケンス図作成

			inputList inputlist = new inputList(e); // ソースコードをListに格納
			readCSV readcsv = new readCSV(e); // トレース情報をCSVファイルに保存、Listにも格納

			System.out.println("5=======resultTrace========");


		}
	}

	/*
	 * private class SwingAction extends AbstractAction { public SwingAction() {
	 * putValue(NAME, "開く"); putValue(SHORT_DESCRIPTION,
	 * "Some short description"); }
	 *
	 * public void actionPerformed(ActionEvent e) { } }
	 */

	/*
	 * private class SwingAction_1 extends AbstractAction { public
	 * SwingAction_1() { putValue(NAME, "シーケンス図作成"); putValue(SHORT_DESCRIPTION,
	 * "Some short description"); }
	 *
	 * public void actionPerformed(ActionEvent e) { } }
	 */
}
