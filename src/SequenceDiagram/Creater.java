package SequenceDiagram;

import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JScrollPane;

import com.mxgraph.swing.mxGraphComponent;

public class Creater {

	public Creater(ActionEvent e) {
		List<List<String>> className = null;

		/*
		 * Node node1 = new Node(); node1.setName("Class1");
		 */

		//className = Data_className();
		//Node node1 = new Node();
		//node1.setName(className);

		// Node node2 = new Node();
		// node2.setName(ClassName2);

		/*
		 * Node node3 = new Node(); node3.setName("Class3");
		 */

		/*
		 * Connector connector1 = new Connector();
		 * connector1.setName("methodName");
		 *
		 * EndPoint endPoint1From = new EndPoint();
		 * endPoint1From.setParentNode(node1);
		 *
		 * //EndPoint endPoint1To = new EndPoint();
		 * //endPoint1To.setParentNode(node2);
		 *
		 * connector1.setFrom(endPoint1From); //connector1.setTo(endPoint1To);
		 *
		 * /* Connector connector2 = new Connector();
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
		 * //EndPoint endPoint4From = new EndPoint();
		 * //endPoint4From.setParentNode(node2);
		 *
		 * EndPoint endPoint4To = new EndPoint();
		 * endPoint4To.setParentNode(node1);
		 *
		 * //connector4.setFrom(endPoint4From); connector4.setTo(endPoint4To);
		 */

		List<Connector> connectorList = new ArrayList<Connector>();
		// for (int i = 0; i < connectorList.size(); i++) {
		// connectorList.add(connector1);
		// connectorList.add(connector4);
		// }

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
		readCSV reader = new readCSV("C:\\Users\\cs12097\\Desktop\\"
				+ "test.csv", 0);
		//List<List<String>> csv = reader.read();
		List<String> sub_csv = new ArrayList<String>();
		System.out.println("aaaaaaaaaa");
		List<String> data = new ArrayList<String>();
		//csv.add(data);
		//System.out.println(csv.get(1));

		//for (List<String> str : csv) {
			//  System.out.println(str);
			//}
		/*
		 * List<List<String>> list = csv.;
		 *
		 * while(list.hasNext()){ System.out.println(list.next()); } return csv;
		 */

		// List<String> name = csv.get(2);
		String className2 = null;
		// StringTokenizer token = null;
		//Iterator<List<String>> data = csv.iterator();
		// String str = csv.;*/
		System.out.println("csvのサイズを表示。");
		System.out.println(data);
		//while (data.hasNext()) {
			//System.out.println(data.next());
		//}
		// int index = csv.size();
		// System.out.println(index);

		// className1 = csv.get(index);
		// Node node1 = new Node();
		// node1.setName(className1);

		// Node node2 = new Node();

		// for (int i = 0; i <= className.size(); i++) {
		// data = csv.get(i);

		// className2 = csv.set(i+1, className1);
		// node2.setName(className);
		// i = +5;
		// }
		// return className1;
		//return csv;
	}

	// メソッド
	/*
	 * public void Data_methodName(List<String> csv) { String methodName;
	 * //String returnType; //String className; //String className2 = null;
	 * Connector connector = new Connector(); EndPoint endPointFrom = new
	 * EndPoint(); EndPoint endPointTo = new EndPoint();
	 *
	 * for (int i = 0; i < csv.size(); i++) { methodName = csv.get(i);
	 * connector.setName(methodName);
	 * endPointFrom.setParentNode(Data_className(csv));
	 * endPointTo.setParentNode(Data_className(csv));
	 * connector.setFrom(endPointFrom); connector.setTo(endPointTo); } }
	 *
	 * /* Connector connector2 = new Connector(); connector2.setName("method2");
	 *
	 * EndPoint endPoint2From = new EndPoint();
	 * endPoint2From.setParentNode(node2);
	 *
	 * EndPoint endPoint2To = new EndPoint(); endPoint2To.setParentNode(node3);
	 *
	 * connector2.setFrom(endPoint2From); connector2.setTo(endPoint2To);
	 */

	// 引数の型
	/*
	 * public void Data_argumentType(List<List<String>> csv[][]) {
	 * List<List<String>> argumentType;
	 *
	 * for (int i = 0; i < csv.length; i++) { argumentType = csv[3][i]; } }
	 *
	 * //変数(型) public void Data_fieldName(List<List<String>> csv[][]) {
	 * List<List<String>> fieldName;
	 *
	 * for (int i = 0; i < csv.length; i++) { fieldName = csv[4][i]; } }
	 */
}
