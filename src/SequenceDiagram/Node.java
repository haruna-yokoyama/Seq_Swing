package SequenceDiagram;

import java.util.List;

public class Node {
	private List<String> name;  //nodeに入る名前
	private String name1;

	public List<String> getName1() {
		return name;
	}
	public void setName(List<String> name) {
		this.name = name;
	}

	public String getName() {
		return name1;
	}
	public void setName(String name1){
		this.name1=name1;
	}

}
