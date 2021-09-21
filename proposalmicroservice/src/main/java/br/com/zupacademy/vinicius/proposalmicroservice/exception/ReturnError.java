package br.com.zupacademy.vinicius.proposalmicroservice.exception;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ReturnError {
    
    private List<String> global = new ArrayList<>();
    private List<HashMap> fields = new ArrayList<>();
    
    public ReturnError() {
    }
    
    public void addError(String errorMessage) { global.add(errorMessage) ;}
    
    public void addErrorField(String campo, Object valor, String mensagem){
        HashMap<String, Object> errorMapping = new HashMap<>();
        errorMapping.put("Field", campo);
        errorMapping.put("Value", valor);
        errorMapping.put("Message", mensagem);
        errorMapping.put("Time", LocalDateTime.now());
        
        fields.add(errorMapping);
    }
    
    public List<?> getGlobal() {
        return global;
    }
    
    public List<HashMap> getFields() {
        return fields;
    }
}
