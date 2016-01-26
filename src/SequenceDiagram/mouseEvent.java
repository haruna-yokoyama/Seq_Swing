package SequenceDiagram;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class mouseEvent {

	Creater creater = new Creater();

	public void mouseClicked(MouseEvent e) {
		/* 処理したい内容をここに記述する */
	}

	public class myListener extends MouseAdapter {
		public void mouseClicked(MouseEvent e, int index) {

//			Object key;
//			creater.map3.get(key);
			if (e.getButton() == MouseEvent.BUTTON1) { // 左クリックだったら → 行番号を表示
				//creater.map3.get(methodName).get(index);

			}
			if (e.getButton() == MouseEvent.BUTTON3) {// 右クリックだったら → 変数などの情報を表示
			}

		}

	}
}
