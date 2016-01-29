package SequenceDiagram;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
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
import com.mxgraph.view.mxGraph;
import com.sun.jdi.Field;
import com.sun.jdi.Location;
import com.sun.jdi.Value;

public class Creater extends JFrame{

	// static public SequenceDiagram sequenceDiagram;

	public List<String> declaringType = new ArrayList<>();
	public List<String> methodName = new ArrayList<>();
	public List<String> returnType = new ArrayList<>();
	public List<String> argumentType = new ArrayList<>();
	public List<Location> lineLocation = new ArrayList<>();
	List<Location> line = new ArrayList<>();
	public Field fieldName;
	public Value valueName;

	public Object cell;

	public List<Node> nodes = new ArrayList<>();
	public List<Connector> connectors = new ArrayList<>();
	public List<EndPoint> endPoints = new ArrayList<>();

	Map<List<String>, List<String>> map1 = new HashMap<>();
	Map<List<String>, List<String>> map2 = new HashMap<>();
	Map<List<String>, List<Location>> map3 = new HashMap<>();
	Map<List<String>, List<String>> map4 = new HashMap<>();

	public SequenceDiagram sequenceDiagram;

	public Creater(SequenceDiagram sequenceDiagram) {
		this.sequenceDiagram = sequenceDiagram;
		createDiagram();
	}

	public void createDiagram() {

		declaringType = SequenceDiagram.resultTrace.getDeclaringType();
		methodName = SequenceDiagram.resultTrace.getMethodName();
		returnType = SequenceDiagram.resultTrace.getReturnType();
		argumentType = SequenceDiagram.resultTrace.getArgumentType();
		lineLocation = SequenceDiagram.resultTrace.getLineLocation();
		fieldName = SequenceDiagram.resultTrace.getFieldName();
		valueName = SequenceDiagram.resultTrace.getValueName();

		map1.put(declaringType, methodName);
		map2.put(methodName, returnType);
		map3.put(methodName, lineLocation);
		map4.put(returnType, argumentType);

		// List<Connector> connectorList = new ArrayList<Connector>();
		// Node[] node_array = new Node[10];

		// ここから作図
		Node tmp_node = new Node();
		tmp_node.setName(declaringType.get(0) + ":"
				+ map1.get(declaringType).get(0));
		nodes.add(tmp_node); // リストnodesに追加していく

		Node tmp_node1 = new Node();
		tmp_node1.setName(declaringType.get(1) + ":"
				+ map1.get(declaringType).get(1));
		nodes.add(tmp_node1);

		fromConnector(nodes, nodes.indexOf(tmp_node), nodes.indexOf(tmp_node1)); //

		// toConnector(nodes, nodes.indexOf(tmp_node),
		// nodes.indexOf(tmp_node1)); //

		Node tmp_node2 = new Node();
		tmp_node2.setName(declaringType.get(2) + ":"
				+ map1.get(declaringType).get(2));
		nodes.add(tmp_node2);

		fromConnector(nodes, nodes.indexOf(tmp_node1), nodes.indexOf(tmp_node2)); //
		// toConnector(nodes, nodes.indexOf(tmp_node1),
		// nodes.indexOf(tmp_node2)); //

		Node tmp_node3 = new Node();
		tmp_node3.setName(declaringType.get(3) + ":"
				+ map1.get(declaringType).get(3));
		nodes.add(tmp_node3);

		fromConnector(nodes, nodes.indexOf(tmp_node1), nodes.indexOf(tmp_node3)); //
		// toConnector(nodes, nodes.indexOf(tmp_node1),
		// nodes.indexOf(tmp_node3)); //

		Node tmp_node4 = new Node();
		tmp_node4.setName(declaringType.get(4) + ":"
				+ map1.get(declaringType).get(4));
		nodes.add(tmp_node4);

		fromConnector(nodes, nodes.indexOf(tmp_node3), nodes.indexOf(tmp_node4)); //
		toConnector(nodes, nodes.indexOf(tmp_node3), nodes.indexOf(tmp_node4)); //

		if (declaringType.get(4).equals(declaringType.get(5))) {
			fromConnector(nodes, nodes.indexOf(tmp_node3),
					nodes.indexOf(tmp_node4)); //
			toConnector(nodes, nodes.indexOf(tmp_node3),
					nodes.indexOf(tmp_node4)); //
		} else {
			Node tmp_node5 = new Node();
			tmp_node5.setName(declaringType.get(5) + ":"
					+ map1.get(declaringType).get(5));
			nodes.add(tmp_node5);

			fromConnector(nodes, nodes.indexOf(tmp_node4),
					nodes.indexOf(tmp_node5)); //
			toConnector(nodes, nodes.indexOf(tmp_node4),
					nodes.indexOf(tmp_node5)); //
		}
		toConnector(nodes, nodes.indexOf(tmp_node1), nodes.indexOf(tmp_node3)); //
		toConnector(nodes, nodes.indexOf(tmp_node1), nodes.indexOf(tmp_node2)); //
		toConnector(nodes, nodes.indexOf(tmp_node), nodes.indexOf(tmp_node1)); //

		writeSeq writeseq = new writeSeq(connectors);
		mxGraphComponent graph = writeseq.createGraphComponent();

		// マウスイベント-----------------------
		mxGraph mxgraph = new mxGraph();
		// mxGraphComponent graphComponent = new mxGraphComponent(mxgraph);
		getContentPane().add(graph);
		graph.getGraphControl().addMouseListener(new MyMouseAdapter(graph, mxgraph));// {
		// clickedEvent clicked = new clickedEvent();
		// public void mouseReleased(MouseEvent ev) {
		// pushEvent(ev, graph, mxgraph);
		// }
		// });
		// -----------------------

		JFrame frame = new JFrame();
		frame.getContentPane().add(new JScrollPane(graph));
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.pack();
		frame.setVisible(true);
	}

	//内部クラス
	class MyMouseAdapter extends MouseAdapter {
		private mxGraphComponent graph;
		private mxGraph mxgraph;

		public MyMouseAdapter(mxGraphComponent graph, mxGraph mxgraph) {
			this.graph = graph;
			this.mxgraph = mxgraph;
			// TODO 自動生成されたコンストラクター・スタブ
		}

		public void mouseReleased(MouseEvent ev) {
			//mxGraphComponent graph;// = writeseq.createGraphComponent();;
			Object cell = graph.getCellAt(ev.getX(), ev.getY());
			List<Object> cells = new ArrayList<>();
			// List<Location> line = new ArrayList<>();
			for (int i = 0; i < ev.getClickCount(); i++) {
				if (cell != null) {
					//mxGraph mxgraph = new mxGraph();;
					System.out.println("clicked:" + mxgraph.getLabel(cell));
					if (map3.containsKey(methodName)) {
						cells.add(cell);
						line = map3.get(methodName);
						sequenceDiagram.giveColor(cell);
						// getPushEvent(line);
						// SequenceDiagram seq = new SequenceDiagram();
						// seq.giveColor();
						// fileHandler = line;
						// どうやったらlineをfilehandlerに渡せるか？
						// sequenceDiagram.getGiveColor();
					}
				}
				keepData(declaringType, methodName, returnType, argumentType,
						lineLocation, fieldName, valueName, nodes, cell, cells);
			}
		}
	}

	// public void pushEvent(MouseEvent ev, mxGraphComponent graph, mxGraph mxgraph) {
	// Object cell = graph.getCellAt(ev.getX(), ev.getY());
	// List<Object> cells = new ArrayList<>();
	// //List<Location> line = new ArrayList<>();
	// for (int i = 0; i < ev.getClickCount(); i++) {
	// if (cell != null) {
	// System.out.println("clicked:" + mxgraph.getLabel(cell));
	// if (map3.containsKey(methodName)) {
	// cells.add(cell);
	// line = map3.get(methodName);
	// //getPushEvent(line);
	// // SequenceDiagram seq = new SequenceDiagram();
	// // seq.giveColor();
	// //fileHandler = line;
	// //どうやったらlineをfilehandlerに渡せるか？
	// //sequenceDiagram.getGiveColor();
	// }
	// }
	// }
	// keepData(declaringType, methodName, returnType, argumentType,
	// lineLocation, fieldName, valueName, nodes, cell, cells);
	// }
	// public static Creater getPushEvent(List<Location> line){
	// return getPushEvent(line);
	// }

	// 「→」のconnectorを作るメソッド
	public List<Connector> fromConnector(List<Node> node, int index, int index1) {
		int deff = index1 - index;

		Connector tmp_connector = new Connector();
		if (deff > 1) {
			tmp_connector.setName(map1.get(declaringType).get(index + deff));
			connectors.add(tmp_connector);
		} else {
			tmp_connector.setName(map1.get(declaringType).get(index + 1));
			connectors.add(tmp_connector);
		}
		EndPoint tmp_endPointFrom = new EndPoint();
		tmp_endPointFrom.setParentNode(nodes.get(index));
		endPoints.add(tmp_endPointFrom);

		EndPoint tmp_endPointTo = new EndPoint();
		tmp_endPointTo.setParentNode(nodes.get(index1));
		endPoints.add(tmp_endPointTo);

		tmp_connector.setFrom(tmp_endPointFrom);
		tmp_connector.setTo(tmp_endPointTo);

		return connectors;
	}

	// 「←」のconnectorを作るメソッド
	public List<Connector> toConnector(List<Node> node, int index, int index1) {
		int deff = index1 - index;
		Connector tmp_connector2 = new Connector();
		if (deff > 1) {
			tmp_connector2.setName("return:"
					+ map1.get(declaringType).get(index + deff));
			connectors.add(tmp_connector2);
		} else {
			tmp_connector2.setName("return:"
					+ map1.get(declaringType).get(index + 1));
			connectors.add(tmp_connector2);
		}
		EndPoint tmp_endPointFrom = new EndPoint();
		tmp_endPointFrom.setParentNode(nodes.get(index1));
		endPoints.add(tmp_endPointFrom);

		EndPoint tmp_endPointTo = new EndPoint();
		tmp_endPointTo.setParentNode(nodes.get(index));
		endPoints.add(tmp_endPointTo);

		tmp_connector2.setFrom(tmp_endPointFrom);
		tmp_connector2.setTo(tmp_endPointTo);
		return connectors;
	}

	// ファイル書き込み
	void keepData(List<String> declaringType, List<String> methodName,
			List<String> returnType, List<String> argumentType,
			List<Location> lineLocation, Field field, Value value,
			List<Node> nodes, Object cell, List<Object> cells) {
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
				filewriter.write(lineLocation + " ");
				filewriter.write(valueName + ",");
				filewriter.write(fieldName + ",");
				filewriter.write(nodes + "");
				filewriter.write(cell + "");
				filewriter.write(cells + "");
				filewriter.close();
			} else {
				System.out.println("ファイルの作成に失敗しました");
			}
		} catch (IOException o) {
			System.out.println(o);
		}
	}
}
