package SequenceDiagram;




public class Connector {
	private String name;  // =methodName
	private EndPoint from;
	private EndPoint to;
	private String ConnectorType;

	public String getConnectorType() {
		return ConnectorType;
	}
	public void setConnectorType(String connectorType) {
		this.ConnectorType = connectorType;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public EndPoint getFrom() {
		return from;
	}
	public void setFrom(EndPoint from) {
		this.from = from;
	}
	public EndPoint getTo() {
		return to;
	}
	public void setTo(EndPoint to) {
		this.to = to;
	}
}
