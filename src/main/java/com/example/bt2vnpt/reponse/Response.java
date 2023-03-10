package com.example.bt2vnpt.reponse;

public class Response {
    private String error_code;
    private String erro_desc;
    private Object data;
    private Response(){}

    public Response(String error_code, String erro_desc, Object data) {
        this.error_code = error_code;
        this.erro_desc = erro_desc;
        this.data = data;
    }

    public String getError_code() {
        return error_code;
    }

    public void setError_code(String error_code) {
        this.error_code = error_code;
    }

    public String getErro_desc() {
        return erro_desc;
    }

    public void setErro_desc(String erro_desc) {
        this.erro_desc = erro_desc;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
