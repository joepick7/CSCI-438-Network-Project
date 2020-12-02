package Layer;
import java.net.InetAddress;

public class Stack {
    private Boolean sender;
    private Application appLayer;
    private Transport transportLayer;
    private Network networkLayer;
    private Data dataLayer;

    
    public Stack(Boolean sender) {
        this.sender = sender;
        this.appLayer = new Application();
        this.transportLayer = new Transport();
        this.networkLayer = new Network();
        this.dataLayer = new Data();
    }   
    public void send(String message) {

    }
    public void receive() {
    }
    public String getLocalRef() {
        return this.networkLayer.findLocalHostName();
    }

    public InetAddress[] getIps() {
        return Network.testAddresses();
    }
}
