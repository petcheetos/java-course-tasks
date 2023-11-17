package edu.hw6.task6;

import java.io.IOException;
import java.net.DatagramSocket;
import java.net.ServerSocket;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class PortScanner {
    private final static Logger LOGGER = LogManager.getLogger();
    final static int LAST_REGISTERED_PORT = 49151;
    final static Map<Integer, String> POPULAR_PORTS = Map.of(
        80, "HTTP",
        21, "FTP",
        22, "SSH",
        53, "DNS",
        443, "HTTPS",
        135, "MSRPC",
        8080, "HTTP proxy server"
    );

    private PortScanner() {
    }

    @SuppressWarnings("checkstyle:EmptyBlock")
    public static List<Port> scanPorts() {
        LOGGER.info(String.format("%-10s %-5s %10s", "Protocol", "Port", "Server"));
        List<Port> ports = new ArrayList<>();
        for (int port = 0; port <= LAST_REGISTERED_PORT; port++) {
            try (ServerSocket serverSocket = new ServerSocket(port)) {
            } catch (IOException exception) {
                ports.add(new Port(Port.Protocol.TCP, port, POPULAR_PORTS.getOrDefault(port, "")));
            }
            try (DatagramSocket datagramSocket = new DatagramSocket(port)) {
            } catch (IOException exception) {
                ports.add(new Port(Port.Protocol.UDP, port, POPULAR_PORTS.getOrDefault(port, "")));
            }
        }
        return ports;
    }

    public static void printPorts(List<Port> ports) {
        if (ports == null) {
            return;
        }

        ports.forEach(
            port ->
                LOGGER.info(String.format("%-10s %-5d %10s", port.protocol, port.port, port.service))
        );
    }

    public record Port(Protocol protocol, int port, String service) {
        enum Protocol {
            TCP,
            UDP
        }
    }
}
