
package interpreter.runtime;

import java.util.HashMap;
import java.util.Map;

import interpreter.SymbolTable;
import language_elements.FunctionDeclaration;

public class DataManager {

	public DataManager(Map<String, FunctionDeclaration> functions) {
		global = new SymbolTable();
		symbolTables = new HashMap<String, SymbolTable>();
		for (String funcName : functions.keySet()) {
			symbolTables.put(funcName, new SymbolTable());
		}
	}

	public SymbolTable getGlobalSymbolTable() {
		return global;
	}

	public SymbolTable getSymbolTable(String functionName) {
		return symbolTables.get(functionName);
	}

	private SymbolTable global;
	private HashMap<String, SymbolTable> symbolTables;

}
