package SequenceDiagram;

import java.awt.event.MouseEvent;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import com.mxgraph.swing.mxGraphComponent;
import com.mxgraph.view.mxGraph;

public class clickedEvent extends SequenceDiagram{

	 public void mouseReleased(MouseEvent ev, mxGraphComponent graph, mxGraph mxgraph){
		 Object cell = graph.getCellAt(ev.getX(), ev.getY());
		    if( cell != null ){
		      System.out.println("clicked:"+ mxgraph.getLabel(cell));
		    }
		    keepData(cell);
	 }

	 void keepData(Object cell) {
			File newfile = new File("C:\\Users\\cs12097\\Desktop\\dddddd.txt");
			try {
				if (newfile.createNewFile()) {
					System.out.println("ファイルの作成に成功しました");
					FileWriter filewriter = new FileWriter(newfile);
					filewriter.write(cell+ "");
					filewriter.close();
				} else {
					System.out.println("ファイルの作成に失敗しました");
				}
			} catch (IOException o) {
				System.out.println(o);
			}
		}
//	 public void giveColor(MouseEvent ev) {
//			mxGraph mxgraph = new mxGraph();
//			mxGraphComponent graph = new mxGraphComponent(mxgraph);
//			Creater creater = new Creater();
//			MyMouseAdapter mouseAdapter = creater.new MyMouseAdapter(graph, mxgraph);
//			// MouseEvent ev = null;
//			// MouseEvent ev = this.addMouseListener(mouseAdapter);
//			// if (e.getSource().equals(btnNewButton))
//			if (ev.getSource().equals(getMouseListeners())) {
//				mouseAdapter.mouseReleased(ev);
//				//textArea.setSelectionColor(Color.YELLOW);
//
//				// -------------------------------------------------
//				File newfile1 = new File("C:\\Users\\cs12097\\Desktop\\ddddddd.txt");
//				try {
//					if (newfile1.createNewFile()) {
//						System.out.println("ファイルの作成に成功しました");
//						FileWriter filewriter = new FileWriter(newfile1);
//						filewriter.write("create file : ");
//						filewriter.close();
//					} else {
//						System.out.println("ファイルの作成に失敗しました");
//					}
//				} catch (IOException o) {
//					System.out.println(o);
//				}
//				// ------------------------------------------------------------
//
//			}
//		}

}

