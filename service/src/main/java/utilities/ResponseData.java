package utilities;

public class ResponseData {
    private Object data;
    private int total;

    public ResponseData(Object data) {
        this.data = data;
    }

    public ResponseData(Object data, int total) {
        this.data = data;
        this.total = total;
    }

    public Object getData() { return data; }
    public void setData(Object data) { this.data = data; }
    public int getTotal() { return total; }
    public void setTotal(int total) { this.total = total; }
}
