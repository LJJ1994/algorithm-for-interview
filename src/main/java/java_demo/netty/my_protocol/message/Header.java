package java_demo.netty.my_protocol.message;

import java.util.HashMap;
import java.util.Map;

public final class Header {
    /**
     * 校验码, 由三部分组成 crcCode = 0xABEF + 主版本号 + 此版本号，一共4个字节长度
     * 1) 0xABEF 固定值，表明该消息是netty协议消息，大小 2个字节
     * 2) 主版本号：1~255 1个字节
     * 3) 次版本号：1~255 1个字节
     *
     */
    private int crcCode = 0xabef0101;

    /**
     * 表示消息长度，包括消息头和消息体，4个字节长度
     */
    private int length;

    /**
     * 集群会话id，8个字节长度
     */
    private long sessionId;

    /**
     * 请求类型 1个字节长度
     * 0: 业务请求消息
     * 1: 业务响应消息
     * 2: 既是请求也是响应
     * 3: 握手请求消息
     * 4: 握手应答消息
     * 5: 心跳请求消息
     * 6: 心跳应答消息
     */
    private byte type;

    /**
     *
     * 消息优先级， 1个字节长度
     */
    private byte priority;

    /**
     * 附加信息, 变长
     */
    private Map<String, Object> attachment = new HashMap<>();

    public int getCrcCode() {
        return crcCode;
    }

    public void setCrcCode(int crcCode) {
        this.crcCode = crcCode;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public long getSessionId() {
        return sessionId;
    }

    public void setSessionId(long sessionId) {
        this.sessionId = sessionId;
    }

    public byte getType() {
        return type;
    }

    public void setType(byte type) {
        this.type = type;
    }

    public byte getPriority() {
        return priority;
    }

    public void setPriority(byte priority) {
        this.priority = priority;
    }

    public Map<String, Object> getAttachment() {
        return attachment;
    }

    public void setAttachment(Map<String, Object> attachment) {
        this.attachment = attachment;
    }

    @Override
    public String toString() {
        return "Header [" +
                "crcCode=" + crcCode +
                ", length=" + length +
                ", sessionId=" + sessionId +
                ", type=" + type +
                ", priority=" + priority +
                ", attachment=" + attachment +
                ']';
    }
}
