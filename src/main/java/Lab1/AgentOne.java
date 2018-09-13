package Lab1;
import jade.core.AID;
import jade.core.Agent;
import jade.core.behaviours.OneShotBehaviour;
import jade.domain.DFService;
import jade.domain.FIPAException;
import jade.domain.FIPAAgentManagement.DFAgentDescription;
import jade.domain.FIPAAgentManagement.ServiceDescription;
import jade.lang.acl.ACLMessage;
import jade.proto.AchieveREInitiator;

public class AgentOne extends Agent {
    DFAgentDescription[] result;
    public void setup () {
        try {
            Thread.sleep(30000);
            ServiceDescription sd = new ServiceDescription();//где ищем
            DFAgentDescription dfd = new DFAgentDescription();//объявление переменной
            sd.setName("PingPongServ");
            sd.setType("PingPongServ");
            dfd.addServices(sd);
            try {
                result = DFService.search(this, dfd);
                System.out.println(result.length);

            } catch (FIPAException e) {

                e.printStackTrace();
            }
        } catch (InterruptedException e) {

            e.printStackTrace();
        }
        final ACLMessage msg = new ACLMessage(ACLMessage.REQUEST);
        msg.setContent("Ping!");
        msg.addReceiver(result[0].getName());
        AchieveREInitiator init = new AchieveREInitiator(this, msg);
        addBehaviour(init);
        OneShotBehaviour restart = new OneShotBehaviour(this) {
            public void action() {

                AchieveREInitiator init2 = new AchieveREInitiator(myAgent, msg);
                init2.registerHandleAllResultNotifications(this);
                addBehaviour(init2);
            }
        };
        init.registerHandleAllResultNotifications(restart);
    }
}