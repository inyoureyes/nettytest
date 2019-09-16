package netty_websocket_test;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.*;

import java.io.File;
import java.io.RandomAccessFile;
import java.net.URISyntaxException;
import java.net.URL;

/**
 * @Auther: ${JJY}
 * @Date: 2019/8/27 16:41
 * @Description:
 */
public class HttpRequestHandle extends SimpleChannelInboundHandler<FullHttpRequest> {
    private final String wsUri;
    private static final File INDEX;

    static {
        URL location = HttpRequestHandle.class.getProtectionDomain().getCodeSource().getLocation();
        try {
            String path = location.toURI() + "index.html";
            path = !path.contains("file:") ? path : path.substring(5);
            INDEX = new File(path);
        } catch (URISyntaxException e) {
            throw new IllegalStateException(
                    "Unable to locate index.html", e);
        }
    }

    public HttpRequestHandle (String wsUri){
        this.wsUri = wsUri;
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, FullHttpRequest req) throws Exception {
        if(wsUri.equalsIgnoreCase(req.getUri())){
            ctx.fireChannelRead(req.retain());
        }else {
            if(HttpHeaders.is100ContinueExpected(req)){
                send100Continue(ctx);
            }
            RandomAccessFile file = new RandomAccessFile(INDEX, "r");
            HttpResponse response = new DefaultFullHttpResponse(req.getProtocolVersion(),HttpResponseStatus.OK);
            response.headers().set(
                    HttpHeaders.Names.CONTENT_TYPE,"text/plain; charset=UTF-8"
            );
            boolean keepalive = HttpHeaders.isKeepAlive(req);
            if(keepalive){
                response.headers().set(
                        HttpHeaders.Names.CONTENT_TYPE,file.length()
                );
                response.headers().set(
                        HttpHeaders.Names.CONNECTION,HttpHeaders.Values.KEEP_ALIVE
                );
            }
        }
    }

    private void send100Continue(ChannelHandlerContext context){
        FullHttpResponse response = new DefaultFullHttpResponse(HttpVersion.HTTP_1_1,HttpResponseStatus.CONTINUE);
        context.writeAndFlush(context);
    }
}
