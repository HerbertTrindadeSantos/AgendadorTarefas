package com.agendadorTarefas.agendador.entities.enums;

public enum Status {
    PENDING(1),
    PROCESS(2),
    COMPLETED(3);

    private final int code;

    Status(int code){
        this.code = code;
    }

    public static Status valueOf(int code){
        for (Status st : Status.values()){
            if (code == st.code){
                return st;
            }
        }
        throw new IllegalArgumentException("Invalid status");
    }
}
