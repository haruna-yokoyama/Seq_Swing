package SequenceDiagram;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JScrollPane;

import com.mxgraph.swing.mxGraphComponent;


public class createDiagram{

	//private static final long serialVertionUID = -4601740824088314699L;
	/*public static final NumberFormat numberFormat = NumberFormat.getInstance();
	public static URL url = null;

	public createDiagram(){

		mxGraph graph = new mxGraph();
		Object parent = graph.getDefaultParent();
		graph.getModel().beginUpdate();

		try{
			Object v1 = graph.insertVertex(parent, null, "Hello", 20, 20, 80, 30);
			Object v2 = graph.insertVertex(parent, null, "World", 240, 150, 80, 30);
			graph.insertEdge(parent, null, "Edge", v1, v2);
		}finally{
			graph.getModel().endUpdate();
		}
		mxGraphComponent graphComponent = new mxGraphComponent(graph);
		getContentPane().add(graphComponent);
	}

	public static void main(String[] args) {
		// TODO 自動生成されたメソッド・スタブ

		createDiagram frame =  new createDiagram();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(400, 320);
		frame.setVisible(true);

	}*/

	public static void main(String args[]){
		Node node1 = new Node();
		node1.setName("Class1");

		Node node2 = new Node();
		node2.setName("Class2");

		Node node3 = new Node();
		node3.setName("Class3");

		Connector connector1 = new Connector();
		connector1.setName("method1");

		EndPoint endPoint1From = new EndPoint();
		endPoint1From.setParentNode(node1);

		EndPoint endPoint1To = new EndPoint();
		endPoint1To.setParentNode(node2);

		connector1.setFrom(endPoint1From);
		connector1.setTo(endPoint1To);

		Connector connector2 = new Connector();
		connector2.setName("method2");

		EndPoint endPoint2From =new EndPoint();
		endPoint2From.setParentNode(node2);

		EndPoint endPoint2To = new EndPoint();
		endPoint2To.setParentNode(node3);

		connector2.setFrom(endPoint2From);
		connector2.setTo(endPoint2To);

		Connector connector3 =new Connector();
		connector3.setName("return2");

		EndPoint endPoint3From = new EndPoint();
		endPoint3From.setParentNode(node3);

		EndPoint endPoint3To = new EndPoint();
		endPoint3To.setParentNode(node2);

		connector3.setFrom(endPoint3From);
		connector3.setTo(endPoint3To);

		Connector connector4 = new Connector();
		connector4.setName("return1");

		EndPoint endPoint4From = new EndPoint();
		endPoint4From.setParentNode(node2);

		EndPoint endPoint4To = new EndPoint();
		endPoint4To.setParentNode(node1);

		connector4.setFrom(endPoint4From);
		connector4.setTo(endPoint4To);

		List<Connector> connectorList = new ArrayList<Connector>();
		connectorList.add(connector1);
		connectorList.add(connector2);
		connectorList.add(connector3);
		connectorList.add(connector4);

		writeSeq writeseq = new writeSeq(connectorList);
		mxGraphComponent graph = writeseq.createGraphComponent();

		JFrame frame = new JFrame();
		frame.getContentPane().add(new JScrollPane(graph));
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.pack();
		frame.setVisible(true);
	}
}
