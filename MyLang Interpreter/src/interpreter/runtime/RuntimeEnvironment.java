package interpreter.runtime;

import java.util.Map;

import interpreter.SymbolTable;
import language_elements.FunctionDeclaration;
import language_elements.type_system.Type;

public class RuntimeEnvironment {

	public RuntimeEnvironment(Map<String, FunctionDeclaration> functions) {
		dataManager = new DataManager(functions);
	}

	public void enterScope(String functionName) {
		System.out.println("Entering scope " + functionName);
		currentScope = functionName;
	}

	public void leaveScope(String functionName) {
		System.out.println("Leaving scope " + functionName);
		currentScope = null;
	}

	public SymbolTable accessGlobalStorage() {
		return dataManager.getGlobalSymbolTable();
	}

	public SymbolTable accessLocalStorage() {
		return dataManager.getSymbolTable(currentScope);
	}

	public boolean isSymbolDefined(String identifier) {
		return isGlobalSymbolDefined(identifier) || isLocalSymbolDefined(identifier);
	}

	public boolean isGlobalSymbolDefined(String identifier) {
		return dataManager.getGlobalSymbolTable().contains(identifier);
	}

	public boolean isLocalSymbolDefined(String identifier) {
		if (currentScope != null)
			return dataManager.getSymbolTable(currentScope).contains(identifier);
		return false;
	}

	public void updateSymbol(String identifier, Object value, Type type) {
		if (!isSymbolDefined(identifier))
			throw new RuntimeException("Trying to update undeclared symbol");
		if (isLocalSymbolDefined(identifier))
			accessLocalStorage().add(identifier, value, type);
		else if (isGlobalSymbolDefined(identifier))
			accessGlobalStorage().add(identifier, value, type);
	}

	private String currentScope = null;
	private DataManager dataManager;

}
