package SequenceDiagram;

import java.awt.event.ActionEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JScrollPane;

import com.mxgraph.swing.mxGraphComponent;

public class readCSV {
	private String fileName;
	private Integer num;
	private String className;

	// コンストラクタ
	public readCSV(String fileName, int num) {
		this.fileName = fileName;
		this.num = num;
	}

	public readCSV(ActionEvent e) {
		// TODO 自動生成されたコンストラクター・スタブ
		read();
		readCSV reader = new readCSV("C:\\Users\\cs12097\\Desktop\\"
				+ "test.csv", 0);
		List<List<String>> csv = reader.read();
	}

	public List<List<String>> read() { // csvファイルを読み込むメソッド

		if (fileName == null || num == null) {
			return null;
		}

		List<List<String>> ret = new ArrayList<List<String>>(); // 返却用リスト
		fileName = "C:\\Users\\cs12097\\Desktop\\" + "test.csv";
		File csv = new File(fileName);
		BufferedReader br = null;

		try {
			br = new BufferedReader(new FileReader(csv)); // ファイルオープン
			System.out.println("ファイルを開きます。ファイル名：" + csv);
			String line = ""; // num行読み込む
			int idx = 0;

			while ((line = br.readLine()) != null) {
				List<String> tmpList = new ArrayList<String>(); // 1行を格納する箱作成
				int idxFrom = 0; // 文字列index
				int idxTo = 0;

				while (true) { // 最終項目を終了後は終了
					if (idxFrom > line.length()) {
						break;
					}
					idxTo = line.indexOf(",", idxFrom); // 次のセパレータ位置を取得

					if (idxTo == -1) { // セパレータが発見できない場合は最終項目を取得
						idxTo = line.length();
					}
					String word = line.substring(idxFrom, idxTo); // 文字列取得
					System.out.println("word : " + word);
					tmpList.add(word); // 文字列を格納
					idxFrom = idxTo + 1; // 検索開始位置を更新
				}
				ret.add(tmpList); // 返却用リストに1行データを格納
				System.out.println(tmpList);

				if (num != 0 && idx > num) { // numを超えたら読み込み終了。numが0のときは全量読む
					break;
				}
			}
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		} catch (IOException e1) {
			e1.printStackTrace();
		} finally {
			try {
				if (br != null) {
					br.close();
				}
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
		creater(ret);
		return null;
	}

	public void creater(List<List<String>> ret) { // シーケンス図を作成するメソッド
		String className;
		String methodName = null;
		int x = ret.size();
		int y = 0;

		List<Node> nodes = new ArrayList<Node>();
		// Map<Node, HashMap<Connector, EndPoint>> nodesMap = new HashMap<>();
		List<Connector> connectorList = new ArrayList<Connector>();
		List<EndPoint> endPoint = new ArrayList<EndPoint>();

		EndPoint endPoint1From = null;  //method開始の位置
		EndPoint endPoint2From = null;  //return発生の位置
		EndPoint endPoint1To = null;    //endPoint1Fromの行き先
		EndPoint endPoint2To = null;    //endPoint2Fromの行き先

		Connector connector1 = new Connector();    //method開始のときのconnector
		Connector connector2 = new Connector();    //method終了のときのconnector
		// Connector connector3 = new Connector();
		// Connector connector4 = new Connector();
		// List<Connector> connectorList = new ArrayList<Connector>();
		// Connector connector;

		for (List<String> n : ret) {
			if (y < n.size()) {
				y = n.size();
			}
		}
		for (int i = 1; i < x; i++) {
			for (int j = 0; j < y; j++) {
				className = ret.get(i).get(0);
				System.out.println("要素：" + ret.get(i).get(j));
				try {
					Node tmp = new Node();
					tmp.setName(className);
					nodes.add(tmp);
					for (int k = 0; k < i; k++) { // connectorのfor文
						// for (int l = 0; l < nodes.size(); l++) {//
						methodName = ret.get(i).get(2);
						connector1 = new Connector();// methodが始まったとき用のconnector
						connector1.setName(methodName);

						endPoint1From = new EndPoint();
						endPoint1From.setParentNode(nodes.get(k));

						endPoint1To = new EndPoint();
						endPoint1To.setParentNode(nodes.get(k + 1));

						connector1.setFrom(endPoint1From);
						connector1.setTo(endPoint1To);

						/* for (int l = 0; l < i; l++) {
						 * connector2 = new Connector();          // return用のconnector
						 * connector2.setName("return : " + methodName);
						 * endPoint2From = new EndPoint();
						 * endPoint2From.setParentNode(nodes.get(k+1));
						 *
						 * endPoint2To = new EndPoint();
						 * endPoint2To.setParentNode(nodes.get(k));
						 *
						 * connector2.setFrom(endPoint2From);
						 * connector2.setTo(endPoint2To);
						  }*/
					}
				} catch (IndexOutOfBoundsException e) {
					className = null;
					System.out.println("error");
				}
				j++;
			}
			connectorList.add(connector1);
		}

		for (int i = x - 1; i > 0; i--) {
			connector2 = new Connector();// return用のconnector
			methodName = ret.get(i).get(2);
			connector2.setName("return : " + methodName);
			endPoint2From = new EndPoint();
			endPoint2From.setParentNode(nodes.get(i));

			endPoint2To = new EndPoint();
			endPoint2To.setParentNode(nodes.get(i - 1));

			connector2.setFrom(endPoint2From);
			connector2.setTo(endPoint2To);
			connectorList.add(connector2);
		}
		// connectorList.add(connector2);

		writeSeq writeseq = new writeSeq(connectorList);
		mxGraphComponent graph = writeseq.createGraphComponent();

		JFrame frame = new JFrame();
		frame.getContentPane().add(new JScrollPane(graph));
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.pack();
		frame.setVisible(true);
	}

	// クラス
	// public void Data_className() {
	// List<String> sub_csv = new ArrayList<String>();
	// System.out.println("aaaaaaaaaa");
	// List<String> data = new ArrayList<String>();
	// csv.add(data);
	// System.out.println("bbbbbbbbbbbbb");

	// for (List<String> str : csv) {
	// System.out.println(str);
	// }
	/*
	 * List<List<String>> list = csv.;
	 */
	// return csv;
	// }
	// }
}
