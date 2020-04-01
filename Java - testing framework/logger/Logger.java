package logger;

import java.util.List;

import core.Test;

public abstract class Logger {
	public abstract void logData(String className, List<Test> tests);
}	
