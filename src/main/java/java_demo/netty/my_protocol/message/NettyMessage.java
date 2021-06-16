package java_demo.netty.my_protocol.message;

/**
 * netty协议消息，包括消息头和消息体
 */
public final class NettyMessage {
    /**
     * 消息头部
     */
    private Header header;

    /**
     * 消息体
     */
    private Object body;

    public Header getHeader() {
        return header;
    }

    public void setHeader(Header header) {
        this.header = header;
    }

    public Object getBody() {
        return body;
    }

    public void setBody(Object body) {
        this.body = body;
    }

    @Override
    public String toString() {
        return "NettyMessage [" +
                "header=" + header + "]";
    }
}
