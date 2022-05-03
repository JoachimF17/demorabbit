package be.technifutur.demorabbit.receive;

import be.technifutur.demorabbit.models.simple.Facture;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class MessageReceiver
{
    private final Logger logger = LoggerFactory.getLogger(MessageReceiver.class);

    @RabbitListener(queues = "facture_queue")
    public void receiveFacture(String message)
    {
        logger.info("FACTURE RECEIVED - " + message);
    }

    @RabbitListener(queues = "compta_queue")
    public void receiveCompta(Message message)
    {
        logger.info("COMPTA RECEIVED - " + message);
    }

}
