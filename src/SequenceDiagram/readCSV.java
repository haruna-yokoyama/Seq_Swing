package SequenceDiagram;

import java.awt.event.ActionEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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
		//creater(ret);
		return null;
	}
}




