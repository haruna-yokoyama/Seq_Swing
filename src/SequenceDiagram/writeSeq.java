package SequenceDiagram;

import java.awt.Color;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.mxgraph.swing.mxGraphComponent;
import com.mxgraph.util.mxConstants;
import com.mxgraph.util.mxRectangle;
import com.mxgraph.view.mxGraph;
//import com.sun.jdi.connect.Connector;
//import sun.tools.tree.Node;

public class writeSeq {

	// 呼び出し線のスタイル
	private static final String STYLE_CALL = mxConstants.STYLE_ENDARROW + "="
			+ "=" + mxConstants.ALIGN_BOTTOM + ";";

	// 戻り線のスタイル
	private static final String STYLE_RETURN = mxConstants.STYLE_DASHED + "=1;"
			+ mxConstants.STYLE_ENDARROW + "=" + mxConstants.ARROW_OPEN + ";"
			+ mxConstants.STYLE_VERTICAL_ALIGN + "=" + mxConstants.ALIGN_BOTTOM
			+ ";";

	// 生存線のスタイル
	private static final String STYLE_LIFELINE = mxConstants.STYLE_ENDARROW
			+ "=" + mxConstants.NONE;

	// ノードの幅
	private int nodeWidth = 80;

	// ノードの高さ
	private int nodeHeight = 20;

	// ノードの間隔
	private int nodeInterval = 10;

	// 結線の間隔
	private int edgeInterval = 25;

	private mxGraph graph;

	// ノードセルのマップ
	private Map<Node, Object> nodeMap = new HashMap<Node, Object>();

	// 結線の数
	private int edgeCount;

	// コンストラクタ。モデルの変換を行う
	public writeSeq(List<Connector> connectorList) {
		graph = new mxGraph();

		for (Connector connector : connectorList) {
			createConnectorCell(connector);
		}
		Object parent = this.graph.getDefaultParent(); // 生存線の生成
		for (Object nodeCell : this.nodeMap.values()) {
			Object endCell = createEndPointCell(nodeCell);
			graph.insertEdge(parent, null, null, nodeCell, endCell,
					STYLE_LIFELINE);
		}
	}

	// グラフの描画
	public mxGraphComponent createGraphComponent() {
		mxGraphComponent component = new mxGraphComponent(this.graph);
		component.getViewport().setBackground(Color.WHITE);
		return component;
	}

	private void createConnectorCell(Connector connentor){
		EndPoint from = connentor.getFrom();
		EndPoint to = connentor.getTo();

		Object fromNodeCell = findParentNodeCell(from);
		Object fromCell = createEndPointCell(fromNodeCell);

		Object toNodeCell = findParentNodeCell(to);
		Object toCell = createEndPointCell(toNodeCell);

		Object parent = this.graph.getDefaultParent();

		if(connentor.getConnectorType() == ConnectorType.METHOD_RETURN){
			graph.insertEdge(parent, null, connentor.getName(), fromCell, toCell, STYLE_RETURN);

		}else{
			graph.insertEdge(parent, null, connentor.getName(), fromCell, toCell, STYLE_CALL);
		}
		this.edgeCount++;
	}

	private Object findParentNodeCell(EndPoint endPoint) {
		Node node = endPoint.getParentNode();
		Object result = findNodeCell(node);
		return result;
	}

	private Object createEndPointCell(Object nodeCell) {
		// X座標　=　ノードの中央値
		mxRectangle rectangle = this.graph.getCellBounds(nodeCell);
		double x = rectangle.getCenterX();

		// Y座標　=　上端からの余白　+　ノードの高さ　+　(結線の数+1)　*　結線の間隔
		int y = this.nodeInterval + this.nodeHeight + (this.edgeCount + 1)
				* edgeInterval;

		Object parent = this.graph.getDefaultParent();
		Object result = this.graph.insertVertex(parent, null, null, x, y, 0, 0);
		return result;
	}

	private Object findNodeCell(Node node) {
		Object result = this.nodeMap.get(node);

		if (result == null) {
			Object parent = this.graph.getDefaultParent();

			// X座標　=　左端からの余白　+　ノード数*(ノード幅　+　ノード間隔)
			int x = this.nodeInterval + this.nodeMap.size()
					* (this.nodeWidth + this.nodeInterval);

			// Y座標　=　上端からの余白
			int y = this.nodeInterval;

			result = this.graph.insertVertex(parent, null, node.getName(), x,
					y, nodeWidth, nodeHeight);
			this.nodeMap.put(node, result);
		}

		return result;
	}

}
