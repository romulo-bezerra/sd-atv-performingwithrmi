package br.edu.ifpb.sdatvperformingwithrmi.factory;

import java.io.Serializable;
import java.net.InetAddress;
import java.net.UnknownHostException;

public class UserIdFactory {

    private final String hostName;
    private final long creationTimeMillis;
    private long lastTimeMillis;
    private long discriminator;

    public UserIdFactory() throws UnknownHostException {
        this.hostName = InetAddress.getLocalHost().getHostAddress();
        this.creationTimeMillis = System.currentTimeMillis();
        this.lastTimeMillis = creationTimeMillis;
    }

    public synchronized Serializable createId() {
        String id;
        long now = System.currentTimeMillis();

        if (now == lastTimeMillis) {
            ++discriminator;
        } else {
            discriminator = 0;
        }
        // creationTimeMillis used to prevent multiple instances of the JVM
        // running on the same Host returning clashing IDs.
        // The only way a clash could occur is if the applications started at
        // exactly the same time.
        id = String.format("%s-%d-%d-%d", hostName, creationTimeMillis, now, discriminator);
        lastTimeMillis = now;

        return id;
    }

}
