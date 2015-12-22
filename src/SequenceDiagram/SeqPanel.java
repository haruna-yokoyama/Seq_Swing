package SequenceDiagram;

import java.awt.Color;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class SeqPanel extends JPanel{
	JLabel paneltitle = new JLabel("これは" + getClass().getCanonicalName() + "クラスのパネルです。");
	JButton btn = new JButton("シーケンス図");
	JFrame frame;
	SequenceDiagram sd;
	String str;

	public SeqPanel() {
		// TODO 自動生成されたコンストラクター・スタブ
		//SeqPanel frame = new SeqPanel();
		//frame.setVisible(true);
		this.setLayout(null);
		this.setSize(400, 200);
		paneltitle.setBounds(0, 5, 400, 30);
		this.add(paneltitle);
		this.setBackground(Color.getHSBColor(205, 0.5f, 0.8f));


	}
	/*public void setVisible(SequenceDiagram sd1, String s) {
		// TODO 自動生成されたメソッド・スタブ
		sd = sd1;
		str = s;
		this.setName(s);
		this.setLayout(null);
		this.setSize(800, 400);
		JLabel paneltitle = new JLabel();
		paneltitle.setBounds(0, 5, 400, 40);
		this.add(paneltitle);
		btn.setBounds(150, 50, 200, 40);
		btn.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				//seqpanel();
			}
		});
		this.add(btn);
		Float f = (float) Math.random();
		this.setBackground(Color.getHSBColor(f, 0.5f, 0.8f));

	}
	/*public void seqpanel(){
		sd.PanelChange((JPanel)this, sd.PanelNames[0]);
	}*/

}
