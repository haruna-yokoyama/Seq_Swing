package SequenceDiagram;

import java.awt.event.ActionEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class inputList{
	private String fileName;
	private Integer num;

	//コンストラクター
	public inputList(String fileName, int num){
		this.fileName = fileName;
		this.num = num;
	}

	public inputList(ActionEvent e){
		inputList();
		inputList inputlist = new inputList("C:\\Users\\cs12097\\Desktop\\"
				+ "HelloWorld.java", 0);
		List<String> texts = inputlist.inputList();
	}

	public List<String> inputList(){

		if (this.fileName == null || this.num == null) {
			return null;
		}

		// long lineCount = 0;
		List<String> textLines = new ArrayList<>();
		fileName = "C:\\Users\\cs12097\\Desktop\\" + "HelloWorld.java";
		File fileReader = new File(fileName); // テキストファイルオープン
		BufferedReader br = null;

		try {
			br = new BufferedReader(new FileReader(fileReader)); // テキストバッファクラスの箱を定義
			String line = ""; // 読み込んだ1行を格納するための変数

			// line = br.readLine(); //1行読み込む
			while ((line = br.readLine()) != null) {
				textLines.add(line);
				System.out.println("line : " + line);
			}
			br.close();
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		} catch (IOException e1) {
			// TODO 自動生成された catch ブロック
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
		return textLines;
	}

}
