/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package examples.clips.agents;
import jade.core.behaviours.CyclicBehaviour;
import net.sf.clipsrules.jni.*;
import jade.lang.acl.ACLMessage;
import jade.core.Agent;

public class RecepA extends Agent {
    Environment clips;
    
    protected void setup() {
        try {
            clips = new Environment();
        } catch (Exception e) {
        }
        addBehaviour(new CyclicBehaviour() {
            
            public void action() {
                ACLMessage msg = receive();
                if (msg != null) {
                    clips.clear();
                    clips.load("src/clips/patologias/templates.clp");
                    String message = msg.getContent();
                    clips.assertString(message);
                    clips.run();
                    clips.load("src/clips/patologias/rules.clp");
                    clips.eval("(facts)");
                    clips.eval("(rules)");
                    clips.run();
                    clips.clear();
                    
                } else {
                    System.out.println("En espera...");

                    block();
                }

            }
        });
    }
    
}
