package java_demo.netty.my_protocol.type;

public enum MessageType {
    BIZ_REQ((byte) 0, "业务请求"),
    BIZ_RESP((byte) 1, "业务响应"),
    ALL_REQ_RESP((byte) 2, "同一个请求响应"),
    HANDSHAKE_REQ((byte) 3, "握手请求"),
    HANDSHAKE_RESP((byte) 4, "握手响应"),
    HEARTBEAT_REQ((byte) 5, "心跳请求"),
    HEARTBEAT_RESP((byte) 6, "心跳响应"),
    LOGIN_REQ((byte) 7, "登录请求"),
    LOGIN_RESP((byte) 8, "登录响应"),
    HANDSHAKE_FAIL((byte) -1, "握手失败！");

    private byte value;
    private String desc;

    private MessageType(byte value, String desc) {
        this.value = value;
        this.desc = desc;
    }

    public byte value() {
        return this.value;
    }

    public String desc() {
        return this.desc;
    }
}
