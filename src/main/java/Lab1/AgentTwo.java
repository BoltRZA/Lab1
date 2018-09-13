package Lab1;
import jade.core.Agent;
import jade.domain.DFService;
import jade.domain.FIPAException;
import jade.domain.FIPAAgentManagement.DFAgentDescription;
import jade.domain.FIPAAgentManagement.ServiceDescription;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;

public class AgentTwo extends Agent {
    public void setup () {
        ServiceDescription sd = new ServiceDescription();//где ищем
        DFAgentDescription dfd = new DFAgentDescription();//объявление переменной
        sd.setName("PingPongServ");
        sd.setType("PingPongServ");
        dfd.addServices(sd);
        try {
            DFService.register(this,dfd);
        } catch (FIPAException e) {
            e.printStackTrace();
        }
        MessageTemplate mt = null;
        MessageTemplate.MatchPerformative(ACLMessage.REQUEST);
        PingResponder resp = new PingResponder(this,mt);
        addBehaviour(resp);
    }
}