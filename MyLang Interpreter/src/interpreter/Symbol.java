package interpreter;

import language_elements.type_system.Type;

public class Symbol {
	public Symbol(Object value, Type type) {
		super();
		this.value = value;
		this.type = type;
	}

	private Object value;
	private Type type;

	@Override
	public String toString() {
		return "Symbol [value=" + value + ", type=" + type + "]";
	}

	public Object getValue() {
		return value;
	}

	public Type getType() {
		return type;
	}
}
