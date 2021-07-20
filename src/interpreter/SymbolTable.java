package interpreter;

import java.util.HashMap;
import java.util.Map;

import language_elements.type_system.Type;

public class SymbolTable {

	private Map<String, Symbol> dataEntries;
	public static SymbolTable global = new SymbolTable();

	public SymbolTable() {
		dataEntries = new HashMap<String, Symbol>();
	}

	public void add(String identifier, Object assigningValue, Type type) {
		dataEntries.put(identifier, new Symbol(assigningValue, type));
	}

	public Symbol get(String identifier) {
		return dataEntries.get(identifier);
	}

	public boolean contains(String identifier) {
		return dataEntries.containsKey(identifier);
	}
}
