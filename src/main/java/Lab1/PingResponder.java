package Lab1;
import jade.core.Agent;
import jade.domain.FIPAAgentManagement.FailureException;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;
import jade.proto.AchieveREResponder;
public class PingResponder extends AchieveREResponder {
    public PingResponder(Agent a, MessageTemplate mt) {
        super(a, mt);

    }
    @Override
    protected ACLMessage prepareResultNotification(ACLMessage request, ACLMessage response) throws FailureException {
        ACLMessage resp = request.createReply();
        resp.setContent("Pong!");
        resp.setPerformative(ACLMessage.INFORM);
        return resp;
    }
}

