package SequenceDiagram;

import java.util.List;

import com.sun.jdi.Field;
import com.sun.jdi.Value;

public class ResultTrace {
	private List<String> methodName;
	private List<String> declaringType;
	private List<String> returnType;
	private List<String> argumentType;
	private Field fieldName;
	private Value valueName;


	public List<String> getMethodName() {
//		methodName.addAll(methodName);
		return methodName;
	}
	public List<String> setMethodName(List<String> methodName) {
		return this.methodName = methodName;
	}


	public List<String> getDeclaringType() {
//		declaringType.addAll(declaringType);
		return declaringType;
	}
	public List<String> setDeclaringType(List<String> declaringType) {
		return this.declaringType = declaringType;
	}


	public List<String> getReturnType() {
		return returnType;
	}
	public List<String> setReturnType(List<String> returnType) {
		return this.returnType = returnType;
	}


	public List<String> getArgumentType() {
		return argumentType;
	}
	public List<String> setArgumentType(List<String> argumentType) {
		return this.argumentType = argumentType;
	}


	public Field getFieldName() {
		return fieldName;
	}
	public Field setFieldName(Field fieldName) {
		return this.fieldName = fieldName;
	}


	public Value getValueName() {
		return valueName;
	}
	public Value setValueName(Value valueName) {
		return this.valueName = valueName;
	}
}
