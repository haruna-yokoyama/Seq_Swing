package SequenceDiagram;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class Filehandler {

	public static String read(String path) {
		BufferedReader br;
		String text = "";

		try {
			br = new BufferedReader(new FileReader(path)); // リーダを初期化
			String buf;

			while ((buf = br.readLine()) != null) {
				text += buf; // テキストに文字列を一行ごとに追加
				text += "\n";
			}
			br.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return text;
	}

	public static void write(String path, String text) {

		try {
			File file = new File(path);
			PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter(
					file)));
			pw.println(text); // 書き込み処理
			pw.close();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}