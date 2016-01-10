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

	// EventThread eventThred = new EventThread(null, null, null, null);

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
		// String word = "abc";

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

	// public class Creater {

	public void creater(List<List<String>> ret) { // シーケンス図を作成するメソッド
		String className;
		String methodName;
		int x = ret.size();
		int y = 0;
		List<Node> nodes = new ArrayList<Node>();
		//Map<Node, HashMap<Connector, EndPoint>> nodesMap = new HashMap<>();
		List<Connector> connectorList = new ArrayList<Connector>();
		List<EndPoint> endPoint = new ArrayList<EndPoint>();

		EndPoint endPoint1From = null;
		EndPoint endPoint1To = null;
		//Connector connector= null;
		// Connector connector = new Connector();

		Connector connector1 = new Connector();
		//Connector connector2 = new Connector();
		//Connector connector3 = new Connector();
		//Connector connector4 = new Connector();
		// List<Connector> connectorList = new ArrayList<Connector>();
		// Connector connector;

		for (List<String> n : ret) {
			if (y < n.size()) {
				y = n.size();
			}
		}
		for (int i = 1; i < x; i++) {
			for (int j = 0; j < y; j++) {
				className = ret.get(i).get(j);
				System.out.println("要素：" + ret.get(i).get(j));
				try {
					for (int k = 0; k < ret.size(); k++) { // Nodeのfor文
						Node tmp = new Node();
						tmp.setName(className);
						nodes.add(tmp);

						//for (int l = 0; l < nodes.size(); l++) {//Connectorのfor文
							methodName = ret.get(i+1).get(j + 1);
							connector1 = new Connector();
							connector1.setName(methodName);
							//connectorList.add(connector);
							//for (int m = 0; m < connectorList.size(); m++) {//EndPointfromのfor文
								endPoint1From = new EndPoint();
								endPoint1From.setParentNode(nodes.get(k));
								// endPoint.add(endPoint1From);

								//for (int n = 0; n < endPoint.size(); n++) {//endPointToのfor文
									endPoint1To = new EndPoint();
									endPoint1To.setParentNode(nodes.get(k + 1));

									connector1.setFrom(endPoint1From);
									connector1.setTo(endPoint1To);

									endPoint1From = new EndPoint();
									endPoint1From.setParentNode(nodes.get(k+1));
								//}

								endPoint1To = new EndPoint();
							endPoint1To.setParentNode(nodes.get(k));
							//}
							//connector = new Connector();
							//connector.setName("return : " + methodName);
							connector1.setFrom(endPoint1From);
							connector1.setTo(endPoint1To);
							//connectorList.add(connector);
						//}
						connectorList.add(connector1);

					}
					//endPoint.add(endPoint1To);
					//endPoint.add(endPoint1From);
				} catch (IndexOutOfBoundsException e) {
					className = null;
					System.out.println("error");
				}
			}
		}
		//connectorList.add(connector);

		// Node node3 = new Node(); node3.setName("Class3");

		/*
		 * Connector connector1 = new Connector();
		 * connector1.setName("methodName");
		 *
		 * EndPoint endPoint1From = new EndPoint();
		 * endPoint1From.setParentNode(nodes.get(index));
		 *
		 * EndPoint endPoint1To = new EndPoint();
		 * endPoint1To.setParentNode(nodes.get(index));
		 *
		 * connector1.setFrom(endPoint1From); connector1.setTo(endPoint1To);
		 */
		/*
		 * Connector connector2 = new Connector();
		 * connector2.setName("method2");
		 *
		 * EndPoint endPoint2From = new EndPoint();
		 * endPoint2From.setParentNode(node2);
		 *
		 * EndPoint endPoint2To = new EndPoint();
		 * endPoint2To.setParentNode(node3);
		 *
		 * connector2.setFrom(endPoint2From); connector2.setTo(endPoint2To);
		 *
		 * Connector connector3 = new Connector();
		 * connector3.setName("return2");
		 *
		 * EndPoint endPoint3From = new EndPoint();
		 * endPoint3From.setParentNode(node3);
		 *
		 * EndPoint endPoint3To = new EndPoint();
		 * endPoint3To.setParentNode(node2);
		 *
		 * connector3.setFrom(endPoint3From); connector3.setTo(endPoint3To);
		 */
		/*
		 * Connector connector4 = new Connector();
		 * connector4.setName("return1");
		 *
		 * EndPoint endPoint4From = new EndPoint();
		 * endPoint4From.setParentNode((Node) nodes);
		 *
		 * EndPoint endPoint4To = new EndPoint();
		 * endPoint4To.setParentNode((Node) nodes);
		 *
		 * connector4.setFrom(endPoint4From); connector4.setTo(endPoint4To);
		 */
		// List<Connector> connectorList = new ArrayList<Connector>();
		// connectorList.add(connector1);
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
	public void Data_className() {

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

	}
}
