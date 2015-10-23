/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utility;

/**
 *
 * @author Dilip
 */
import java.io.IOException;
import org.smslib.GatewayException;
import org.smslib.OutboundMessage;
import org.smslib.SMSLibException;
import org.smslib.Service;
import org.smslib.TimeoutException;
import org.smslib.modem.SerialModemGateway;

public class Message {

    private static Message message = null;

    private Message() throws GatewayException, SMSLibException, TimeoutException, IOException, InterruptedException {
        SerialModemGateway gateway = new SerialModemGateway("modem.com4", "COM4", 9600, "", "");
        gateway.setInbound(true);
        gateway.setOutbound(true);
        Service.getInstance().addGateway(gateway);
        Service.getInstance().startService();
    }

    public static Message getMessage() throws SMSLibException, GatewayException, TimeoutException, IOException, InterruptedException {
        if (message == null) {
            message = new Message();
        }

        return message;
    }

    public static void sendMessage(String number, String message) throws IOException, InterruptedException, TimeoutException, SMSLibException {
        OutboundMessage msg = new OutboundMessage(number, message);
        Service.getInstance().sendMessage(msg);
    }

    public void stopService() throws SMSLibException, TimeoutException, GatewayException, IOException, InterruptedException {
        Service.getInstance().stopService();
    }
}
