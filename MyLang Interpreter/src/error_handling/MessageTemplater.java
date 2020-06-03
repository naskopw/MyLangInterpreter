package error_handling;

public class MessageTemplater {
	public static final String Unknown = "Unknown";
	public static final String Unexpected = "Unexpected";
	public static final String Expected = "Expected";

	public static final String UnknownSymbol = Unknown + " symbol found";
	public static final String UnknownType = Unknown + " Type";

	public static final String LiteralOutOfRange = "The literal is too big";
	public static final String FileNotFound = "The input file is not found";
	public static final String Usage = "Usage: java Program \'inputFile\'\n";
	public static final String IncorrectJSON = "Incorrectly formated JSON";
	public static final String NoEntryPoint = "Cannot find an entry point for the program";
	public static final String FuncAlreadyDeclared = "Function already declared";
}