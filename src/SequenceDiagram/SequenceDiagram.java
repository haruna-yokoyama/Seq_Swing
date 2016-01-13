package SequenceDiagram;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;

import com.sun.jdi.VirtualMachine;
import com.sun.tools.example.trace.Trace;

public class SequenceDiagram extends JFrame {

	private JPanel contentPane;
	private JFrame frame;
	private JFrame frmTexteditor;
	private JButton btnNewButton;
	private JLabel lblNewLabel;
	private final Action action = new SwingAction();
	private JTextArea textArea;
	private final Action action_1 = new SwingAction_1();


	//public String[] PanelNames = {"mp", "SeqPanel"};
	//SequenceDiagram mp = new SequenceDiagram(this,PanelNames[0]);
	//SeqPanel sp = new SeqPanel(this,PanelNames[1]);

	static String str;

	static String[] argss;
	//static PrintWriter methodName;

	 private VirtualMachine vm;
	 private String[] excludes = {"java.*", "javax.*", "sun.*", "com.sun.*"};

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		argss=args;
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

		JButton btnNewButton = new JButton("New button"); // 「開く」ボタン
		final JTextArea textArea = new JTextArea();

		btnNewButton.setBounds(12, 33, 91, 21);
		btnNewButton.setAction(action);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser filechooser = new JFileChooser(); // ファイル選択用クラス

				int selected = filechooser.showOpenDialog(frame); // 「開く」ダイアログ表示
				if (selected == JFileChooser.APPROVE_OPTION) {
					File file = filechooser.getSelectedFile();
					lblNewLabel.setText(file.getName());
					textArea.setText(Filehandler.read(file.getPath())); // ファイルの中身を表示
				}
			}
		});
		contentPane.add(btnNewButton);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(12, 64, 482, 250);
		contentPane.add(scrollPane);

		// JTextArea textArea = new JTextArea();
		textArea.setWrapStyleWord(true);
		textArea.setLineWrap(true);
		scrollPane.setViewportView(textArea);

		JButton button = new JButton("New button");     //「シーケンス図作成」
		button.setBounds(121, 33, 177, 21);
		button.setAction(action_1);
		button.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				String[] tracePrograms= {"HelloWorld"};
				new Trace(tracePrograms);
				//Trace.generateTrace(methodName);
				//Trace.main(tracePrograms);
				//EventThread eventThread = new EventThread(vm, writer, excludes, options);
				//MethodName methodName = new MethodName();
				//DeclaringType decType = new DeclaringType();
				//methodName.getMethodName();
				//decType.getDeclaringType();
				//System.out.println("trace結果" + methodName + " , " + decType);

				inputList inputlist = new inputList(e);
				readCSV readcsv = new readCSV(e);
			}
		});
		getContentPane().add(button);
	}

	private class SwingAction extends AbstractAction {
		public SwingAction() {
			putValue(NAME,"開く");
			putValue(SHORT_DESCRIPTION, "Some short description");
		}
		public void actionPerformed(ActionEvent e) {
		}
	}

	private class SwingAction_1 extends AbstractAction {
		public SwingAction_1() {
			putValue(NAME, "シーケンス図作成");
			putValue(SHORT_DESCRIPTION, "Some short description");
		}
		public void actionPerformed(ActionEvent e) {
		}
	}
}
