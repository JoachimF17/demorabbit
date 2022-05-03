package be.technifutur.demorabbit.send;

import be.technifutur.demorabbit.models.simple.Facture;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageBuilder;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

@Component
public class MessageSender implements InitializingBean
{
    private final RabbitTemplate rabbitTemplate;
    private final ObjectMapper mapper = new ObjectMapper();

    public MessageSender(RabbitTemplate rabbitTemplate)
    {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void sendFactureToCompta(String json)
    {
//        Message message = MessageBuilder.withBody(json.getBytes()).setContentType("application/json").build();
        rabbitTemplate.convertAndSend("topic.facture", "facture.compta", json);
    }

    @Override
    public void afterPropertiesSet() throws Exception
    {
        Facture facture = new Facture("Test", 45.12, "Rue de test 12");

        String fJson = mapper.writeValueAsString(facture);

        sendFactureToCompta(fJson);
    }
}
