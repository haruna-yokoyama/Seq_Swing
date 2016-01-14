package SequenceDiagram;

import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JScrollPane;

import com.mxgraph.swing.mxGraphComponent;

public class Creater {
	
	public String declaringType;
	public String methodName;
	public String returnType;
	public String argumentType;
	public String fieldName;
	public String valueName;
	

	public Creater(ActionEvent e) {

		declaringType = null;
		
		Node node1 = new Node(); 
		node1.setName(declaringType);
		
		

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

	/*public void creater(List<List<String>> ret) { // シーケンス図を作成するメソッド

		String className;
		String methodName = null;
		int x = ret.size();
		int y = 0;

		List<Node> nodes = new ArrayList<Node>();
		// Map<Node, HashMap<Connector, EndPoint>> nodesMap = new HashMap<>();
		List<Connector> connectorList = new ArrayList<Connector>();
		List<EndPoint> endPoint = new ArrayList<EndPoint>();

		EndPoint endPoint1From = null; // method開始の位置
		EndPoint endPoint2From = null; // return発生の位置
		EndPoint endPoint1To = null; // endPoint1Fromの行き先
		EndPoint endPoint2To = null; // endPoint2Fromの行き先

		Connector connector1 = new Connector(); // method開始のときのconnector
		Connector connector2 = new Connector(); // method終了のときのconnector
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

						/*
						 * for (int l = 0; l < i; l++) { connector2 = new
						 * Connector(); // return用のconnector
						 * connector2.setName("return : " + methodName);
						 * endPoint2From = new EndPoint();
						 * endPoint2From.setParentNode(nodes.get(k+1));
						 *
						 * endPoint2To = new EndPoint();
						 * endPoint2To.setParentNode(nodes.get(k));
						 *
						 * connector2.setFrom(endPoint2From);
						 * connector2.setTo(endPoint2To); }
						 
					}
				} catch (IndexOutOfBoundsException e) {
					className = null;
					// System.out.println("error");
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
	}*/
}
