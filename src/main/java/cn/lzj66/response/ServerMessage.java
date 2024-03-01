package cn.lzj66.response;

public class ServerMessage {
    private String message;

    public ServerMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
