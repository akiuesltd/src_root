package com.akieus.lgc.udp;

import com.akieus.lgc.io.ByteBufferProvider;
import com.akieus.lgc.io.DefaultByteBufferProvider;
import com.akieus.lgc.io.MessageListener;
import com.akieus.lgc.util.IOUtils;
import com.akieus.lgc.util.Misc;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.slf4j.Logger;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicReference;

import static java.util.concurrent.TimeUnit.MILLISECONDS;
import static org.slf4j.LoggerFactory.getLogger;

/**
 * Created by aks on 19/03/2016.
 */
public class UdpSendReceiveITest {
    private static final Logger LOG = getLogger(UdpSendReceive.class);

    private static final String TEST_SERVER = "TEST_SERVER";
    private static final String TEST_CLIENT = "TEST_CLIENT";

    @Test
    @Ignore
    public void testSendReceive() throws InterruptedException, IOException {
        ByteBufferProvider bufferProvider = new DefaultByteBufferProvider(1024, false);
        CountDownLatch serverReceivedMsg = new CountDownLatch(1);
        MessageListener serverListener = (byteBuffer) -> serverReceivedMsg.countDown();


        String address = "239.255.0.0";
        int port = IOUtils.findFreePort();
        UdpSource server = new UdpSource(TEST_SERVER, address, port, "xxx", serverListener, bufferProvider);
        server.start();
        Misc.sleepSafely(100, MILLISECONDS);

        final AtomicReference<UdpSession> clientSession = new AtomicReference<>();
        CountDownLatch clientConnected = new CountDownLatch(1);
        CountDownLatch clientReceivedMsg = new CountDownLatch(1);
        MessageListener clientListener = (byteBuffer) -> clientReceivedMsg.countDown();
        UdpSource client = new UdpSource(TEST_CLIENT, address, port, "xxx", clientListener, bufferProvider);
        client.addListener(new ConnectionListener() {
            @Override
            public void connected(UdpSession session) {
                LOG.info("Client connected, session={}", session);
                clientSession.set(session);
                clientConnected.countDown();
            }

            @Override
            public void disconnected(UdpSession session) {

            }
        });
        client.start();
        Assert.assertTrue(clientConnected.await(100, MILLISECONDS));

        ByteBuffer buffer = bufferProvider.get();
        clientSession.get().write(buffer.putLong(1L));
        serverReceivedMsg.await(100, MILLISECONDS);
    }

    public static void main(String[] args) throws IOException, InterruptedException {
        new UdpSendReceive().testSendReceive();
    }
}
