package org.mvel;

import org.mvel.integration.Interceptor;

import java.util.Map;
import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;

public class ParserContext {
    private String sourceFile;
    private int lineCount;

    protected Map<String, Class> imports;
    protected Map<String, Interceptor> interceptors;

    private Map<String, Class> variableTable;
    private Map<String, Class> inputTable;

    private List<ErrorDetail> errorList;

    private Object rootParser;

    private boolean strictTypeEnforcement = false;

    private boolean fatalError = false;


    public ParserContext(Object rootParser) {
        this.rootParser = rootParser;
    }

    public boolean hasVarOrInput(String name) {
        return (variableTable != null && variableTable.containsKey(name))
                || (inputTable != null && inputTable.containsKey(name));
    }

    public Class getVarOrInputType(String name) {
        if (variableTable != null && variableTable.containsKey(name))  {
            return variableTable.get(name);
        }
        else if (inputTable != null && inputTable.containsKey(name))  {
            return inputTable.get(name);
        }
        return Object.class;
    }



    public Object getRootParser() {
        return rootParser;
    }

    public void setRootParser(Object rootParser) {
        this.rootParser = rootParser;
    }

    public String getSourceFile() {
        return sourceFile;
    }

    public void setSourceFile(String sourceFile) {
        if (sourceFile != null)
            this.sourceFile = sourceFile;
    }

    public int getLineCount() {
        return lineCount;
    }

    public void setLineCount(int lineCount) {
        this.lineCount = lineCount;
    }

    public Class getImport(String name) {
        return imports != null ? imports.get(name) : null;
    }

    public boolean hasImport(String name) {
        return imports != null && imports.containsKey(name);
    }

    public Map<String, Class> getImports() {
        return imports;
    }

    public void setImports(Map<String, Class> imports) {
        if (imports == null) return;

        if (this.imports != null) {
            this.imports.putAll(imports);
        }
        else {
            this.imports = imports;
        }
    }

    public void addImport(String name, Class cls) {
        if (this.imports == null) this.imports = new HashMap<String, Class>();
        this.imports.put(name, cls);
    }

    public Map<String, Interceptor> getInterceptors() {
        return interceptors;
    }

    public void setInterceptors(Map<String, Interceptor> interceptors) {
        this.interceptors = interceptors;
    }


    public Map<String, Class> getVariableTable() {
        return variableTable;
    }

    public void setVariableTable(Map<String, Class> variableTable) {
        this.variableTable = variableTable;
    }

    public Map<String, Class> getInputTable() {
        return inputTable;
    }

    public void setInputTable(Map<String, Class> inputTable) {
        this.inputTable = inputTable;
    }

    public List<ErrorDetail> getErrorList() {
        return errorList;
    }

    public void setErrorList(List<ErrorDetail> errorList) {
        this.errorList = errorList;
    }

    public void addError(ErrorDetail errorDetail) {
        if (errorList == null) errorList = new ArrayList<ErrorDetail>();
        if (errorDetail.isCritical()) fatalError = true;
        errorList.add(errorDetail);
    }

    public boolean isFatalError() {
        return fatalError;
    }

    public void setFatalError(boolean fatalError) {
        this.fatalError = fatalError;
    }

    public boolean isStrictTypeEnforcement() {
        return strictTypeEnforcement;
    }

    public void setStrictTypeEnforcement(boolean strictTypeEnforcement) {
        this.strictTypeEnforcement = strictTypeEnforcement;
    }
}