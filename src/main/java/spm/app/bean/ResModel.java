package spm.app.bean;

public class ResModel {
    private int code;
    private String msg;
    private Object obj;

    public ResModel() {

    }

    public ResModel(int code) {
        this.code = code;
    }

    public ResModel ResModel(int code) {
        return new ResModel(code);
    }

    public ResModel(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public ResModel ResModel(int code, String msg) {
        return new ResModel(code, msg);
    }

    public ResModel(int code, Object obj) {
        this.code = code;
        this.obj = obj;
    }

    public ResModel ResModel(int code, Object obj) {
        return new ResModel(code, obj);
    }

    public ResModel(int code, String msg, Object obj) {
        this.code = code;
        this.msg = msg;
        this.obj = obj;
    }

    public ResModel ResModel(int code, String msg, Object obj) {
        return new ResModel(code, msg, obj);
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getObj() {
        return obj;
    }

    public void setObj(Object obj) {
        this.obj = obj;
    }

    @Override
    public String toString() {
        return "ResModel{" +
                "code=" + code +
                ", msg='" + msg + '\'' +
                ", obj=" + obj +
                '}';
    }
}
