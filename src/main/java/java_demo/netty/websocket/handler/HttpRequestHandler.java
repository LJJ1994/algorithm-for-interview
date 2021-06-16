package java_demo.netty.websocket.handler;

import com.sun.deploy.net.HttpUtils;
import io.netty.channel.*;
import io.netty.handler.codec.Headers;
import io.netty.handler.codec.TextHeaders;
import io.netty.handler.codec.http.*;
import io.netty.handler.codec.http2.HttpUtil;
import io.netty.handler.ssl.SslHandler;
import io.netty.handler.stream.ChunkedFile;
import io.netty.handler.stream.ChunkedNioFile;

import java.io.File;
import java.io.RandomAccessFile;
import java.net.URISyntaxException;
import java.net.URL;

public class HttpRequestHandler extends SimpleChannelInboundHandler<FullHttpRequest> {
    private String wsUri;
    private static File INDEX;
    static {
        URL url = HttpRequestHandler.class.getProtectionDomain().getCodeSource().getLocation();
        try {
            String path = url.toURI() + "index.html";
            path = !path.contains("file:") ? path : path.substring(5);
            INDEX = new File(path);
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }

    }

    public HttpRequestHandler(String wsUri) {
        this.wsUri = wsUri;
    }


    private void send100continue(ChannelHandlerContext ctx) {
        DefaultHttpResponse response = new DefaultFullHttpResponse(HttpVersion.HTTP_1_1, HttpResponseStatus.CONTINUE);
        ctx.writeAndFlush(response);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }

    @Override
    protected void messageReceived(ChannelHandlerContext ctx, FullHttpRequest request) throws Exception {
        if (wsUri.equalsIgnoreCase(request.uri())) {
            ctx.fireChannelRead(request.retain());
        } else {

            if (HttpHeaderUtil.is100ContinueExpected(request)) {
                send100continue(ctx);
            }
            RandomAccessFile file = new RandomAccessFile(INDEX, "r");
            DefaultHttpResponse response = new DefaultHttpResponse(request.protocolVersion(), HttpResponseStatus.OK);
            response.headers().set(HttpHeaderNames.CONTENT_TYPE, "text/html; charset=UTF_8");
            boolean keepAlive = HttpHeaderUtil.isKeepAlive(request);
            if (keepAlive) {
                long len = file.length();
                response.headers().set(HttpHeaderNames.CONTENT_LENGTH, String.valueOf(file.length()));
                response.headers().set(HttpHeaderNames.CONNECTION, HttpHeaderValues.KEEP_ALIVE);
            }
            ctx.write(response);
            if (ctx.pipeline().get(SslHandler.class) == null) {
                ctx.write(new DefaultFileRegion(file.getChannel(), 0, file.length()));
            } else {
                ctx.write(new ChunkedNioFile(file.getChannel()));
            }
            ChannelFuture channelFuture = ctx.writeAndFlush(LastHttpContent.EMPTY_LAST_CONTENT);
            if (!keepAlive) {
                channelFuture.addListener(ChannelFutureListener.CLOSE);
            }
        }
    }
}
