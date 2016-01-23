package SequenceDiagram;

import java.awt.event.ActionEvent;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.JFrame;
import javax.swing.JScrollPane;

import com.mxgraph.swing.mxGraphComponent;
import com.sun.jdi.Field;
import com.sun.jdi.Value;

public class Creater {

	public List<String> declaringType = new ArrayList<>();
	public List<String> methodName = new ArrayList<>();
	public List<String> returnType = new ArrayList<>();
	public List<String> argumentType = new ArrayList<>();
	public Field fieldName;
	public Value valueName;

	public List<Node> nodes = new ArrayList<>();
	public List<Connector> connectors = new ArrayList<>();
	public List<EndPoint> endPoints = new ArrayList<>();

	// ResultTrace resultTrace = new SequenceDiagram().getResultTrace();

	public Creater(ActionEvent e) {
		createDiagram();
	}

	public void createDiagram() {

		declaringType = SequenceDiagram.resultTrace.getDeclaringType();
		methodName = SequenceDiagram.resultTrace.getMethodName();
		returnType = SequenceDiagram.resultTrace.getReturnType();
		argumentType = SequenceDiagram.resultTrace.getArgumentType();
		fieldName = SequenceDiagram.resultTrace.getFieldName();
		valueName = SequenceDiagram.resultTrace.getValueName();

		Map<List<String>, List<String>> map1 = new HashMap<>();
		Map<List<String>, List<String>> map2 = new HashMap<>();
		Map<List<String>, List<String>> map3 = new HashMap<>();
		map1.put(declaringType, methodName);
		map2.put(methodName, returnType);
		map3.put(returnType, argumentType);

		// List<Connector> connectorList = new ArrayList<Connector>();
		
		Array<Node> node_array = new Array<Node>[];

		// ここから作図
		Node tmp_node = new Node();
		Connector tmp_connector = new Connector();
		Connector tmp_connector2 = new Connector();
		EndPoint tmp_endPointFrom = new EndPoint();
		EndPoint tmp_endPointTo = new EndPoint();
		//for (int i = 0; i < declaringType.size(); i++) { // Methodの数だけNodeを作る
			tmp_node = new Node();
			tmp_node.setName(declaringType.get(0) + ":"	+ map1.get(declaringType).get(0));
			nodes.add(tmp_node); // リストnodesに追加していく

			Node tmp_node1 = new Node();
			tmp_node1.setName(declaringType.get(1) + ":" + map1.get(declaringType).get(1));
			nodes.add(tmp_node1);

			Node tmp_node2 = new Node();
			tmp_node2.setName(declaringType.get(2) + ":" + map1.get(declaringType).get(2));
			nodes.add(tmp_node2);
			
			Node tmp_node3 = new Node();
			tmp_node3.setName(declaringType.get(3) + ":" + map1.get(declaringType).get(3));
			nodes.add(tmp_node3);
			
			Node tmp_node4 = new Node();
			tmp_node4.setName(declaringType.get(4) + ":" + map1.get(declaringType).get(4));

		//}

//			for (int n = 0; n < map1.size() - 1; n++) { // Methodの数だけNodeを作る
				// if(nodes.get(i).equals(nodes.get(i+1)) == false){
				tmp_connector = new Connector();
				tmp_connector.setName(map1.get(declaringType).get(1));
				connectors.add(tmp_connector);

				tmp_endPointFrom = new EndPoint();
				tmp_endPointFrom.setParentNode(tmp_node);
				endPoints.add(tmp_endPointFrom);

				tmp_endPointTo = new EndPoint();
				tmp_endPointTo.setParentNode(nodes.get(1));

				tmp_connector.setFrom(tmp_endPointFrom);
				tmp_connector.setTo(tmp_endPointTo);

				tmp_connector2 = new Connector();
				tmp_connector2.setName("return:"
						+ map1.get(declaringType).get(1));
				connectors.add(tmp_connector2);

				tmp_endPointFrom = new EndPoint();
				tmp_endPointFrom.setParentNode(nodes.get(1));
				endPoints.add(tmp_endPointFrom);

				tmp_endPointTo = new EndPoint();
				tmp_endPointTo.setParentNode(nodes.get(0));
				tmp_connector2.setFrom(tmp_endPointFrom);
				tmp_connector2.setTo(tmp_endPointTo);



				Connector tmp_connector3 = new Connector();
				tmp_connector3.setName(map1.get(declaringType).get(2));
				connectors.add(tmp_connector3);

				tmp_endPointFrom = new EndPoint();
				tmp_endPointFrom.setParentNode(tmp_node2);
				endPoints.add(tmp_endPointFrom);

				tmp_endPointTo = new EndPoint();
				tmp_endPointTo.setParentNode(nodes.get(1));

				tmp_connector3.setFrom(tmp_endPointFrom);
				tmp_connector3.setTo(tmp_endPointTo);

				Connector tmp_connector4 = new Connector();
				tmp_connector4.setName("return:"
						+ map1.get(declaringType).get(2));
				connectors.add(tmp_connector2);

				tmp_endPointFrom = new EndPoint();
				tmp_endPointFrom.setParentNode(nodes.get(1));
				endPoints.add(tmp_endPointFrom);

				tmp_endPointTo = new EndPoint();
				tmp_endPointTo.setParentNode(nodes.get(0));
				tmp_connector2.setFrom(tmp_endPointFrom);
				tmp_connector2.setTo(tmp_endPointTo);
				// connectorList = new ArrayList<Connector>();
				// for (int j = 0; j < connectors.size(); j++) {
				// connectorList.addAll(connectors);
				// connectorList.add(tmp_connector2);
				// }

//		}

		keepData(declaringType, methodName, returnType, argumentType,
				fieldName, valueName, nodes);

		// }

		// Connector tmp_connector1 = new Connector();
		// connectors.add(tmp_connector1);
		// tmp_connector1.setName(map1.get(declaringType).get(1));

		// EndPoint tmp_endPointFrom = new EndPoint();
		// endPoints.add(tmp_endPointFrom);
		// tmp_endPointFrom.setParentNode(tmp_node);

		// EndPoint tmp_endPointTo = new EndPoint();
		// endPoints.add(tmp_endPointTo);
		// tmp_endPointTo.setParentNode(nodes.get(i));
		//
		// tmp_connector1.setTo(tmp_endPointTo);
		//
		// tmp_connector1.setFrom(tmp_endPointFrom);

		// Connector tmp_connector1 = new Connector();
		// tmp_connector1.setName(map1.get(declaringType).get(1));//
		// connector1.setName(methodName);

		// EndPoint tmp_endPointFrom = new EndPoint();
		// tmp_endPointFrom.setParentNode(tmp_node1);//
		// endPoint1From.setParentNode(node1);

		// Node tmp_node2 = new Node();
		// tmp_node2.setName(declaringType.get(1) + ":"+
		// map1.get(declaringType).get(1));

		// EndPoint tmp_endPointTo = new EndPoint();
		// tmp_endPointTo.setParentNode(tmp_node2);

		// tmp_connector1.setFrom(tmp_endPointFrom);//
		// connector1.setFrom(endPoint1From);
		// tmp_connector1.setTo(tmp_endPointTo);//
		// connector1.setFrom(endPoint1To);

		// Connector tmp_connector2 = new Connector();
		// tmp_connector2.setName("return:" + map1.get(declaringType).get(1));
		//
		// EndPoint tmp_endPointFrom2 = new EndPoint();
		// tmp_endPointFrom2.setParentNode(tmp_node2);
		//
		// EndPoint tmp_endPointTo2 = new EndPoint();
		// tmp_endPointTo2.setParentNode(tmp_node);
		//
		// tmp_connector2.setFrom(tmp_endPointFrom2);
		// tmp_connector2.setTo(tmp_endPointTo2);
		// Node node2 = new Node();
		// node2.setName("ClassName2");
		//
		// Connector connector1 = new Connector();
		// connector1.setName(methodName);
		//
		// EndPoint endPoint1From = new EndPoint();
		// endPoint1From.setParentNode(node1);
		//
		// EndPoint endPoint1To = new EndPoint();
		// endPoint1To.setParentNode(node2);
		//
		// connector1.setFrom(endPoint1From);
		// connector1.setTo(endPoint1To);
		//
		// Connector connector4 = new Connector();
		// connector4.setName(methodName);
		//
		// EndPoint endPoint4From = new EndPoint();
		// endPoint4From.setParentNode(node2);
		//
		// EndPoint endPoint4To = new EndPoint();
		// endPoint4To.setParentNode(node1);
		//
		// connector4.setFrom(endPoint4From);
		// connector4.setTo(endPoint4To);
		// ----------------------------------------------
		// }

		writeSeq writeseq = new writeSeq(connectors);
		mxGraphComponent graph = writeseq.createGraphComponent();

		JFrame frame = new JFrame();
		frame.getContentPane().add(new JScrollPane(graph));
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.pack();
		frame.setVisible(true);
	}

	/*
	 * Node node1 = new Node(); node1.setName(className);
	 *
	 * Node node2 = new Node(); node2.setName(ClassName2);
	 *
	 * Node node3 = new Node(); node3.setName("Class3");
	 *
	 * Connector connector1 = new Connector(); connector1.setName("methodName");
	 *
	 * EndPoint endPoint1From = new EndPoint();
	 * endPoint1From.setParentNode(node1);
	 *
	 * EndPoint endPoint1To = new EndPoint(); endPoint1To.setParentNode(node2);
	 *
	 * connector1.setFrom(endPoint1From); connector1.setTo(endPoint1To);
	 *
	 * Connector connector2 = new Connector(); connector2.setName("method2");
	 *
	 * EndPoint endPoint2From = new EndPoint();
	 * endPoint2From.setParentNode(node2);
	 *
	 * EndPoint endPoint2To = new EndPoint(); endPoint2To.setParentNode(node3);
	 *
	 * connector2.setFrom(endPoint2From); connector2.setTo(endPoint2To);
	 *
	 * Connector connector3 = new Connector(); connector3.setName("return2");
	 *
	 * EndPoint endPoint3From = new EndPoint();
	 * endPoint3From.setParentNode(node3);
	 *
	 * EndPoint endPoint3To = new EndPoint(); endPoint3To.setParentNode(node2);
	 *
	 * connector3.setFrom(endPoint3From); connector3.setTo(endPoint3To);
	 *
	 * Connector connector4 = new Connector(); connector4.setName("return1");
	 *
	 * //EndPoint endPoint4From = new EndPoint();
	 * //endPoint4From.setParentNode(node2);
	 *
	 * EndPoint endPoint4To = new EndPoint(); endPoint4To.setParentNode(node1);
	 *
	 * connector4.setFrom(endPoint4From); connector4.setTo(endPoint4To);
	 */

	void keepData(List<String> declaringType, List<String> methodName,
			List<String> returnType, List<String> argumentType, Field field,
			Value value, List<Node> nodes) {
		File newfile = new File("C:\\Users\\cs12097\\Desktop\\cccccc.txt");
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
				filewriter.write(nodes + "");

				filewriter.close();
			} else {
				System.out.println("ファイルの作成に失敗しました");
			}
		} catch (IOException o) {
			System.out.println(o);
		}
	}
}
