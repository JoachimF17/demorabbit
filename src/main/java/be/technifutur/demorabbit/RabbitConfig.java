package be.technifutur.demorabbit;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitConfig
{
    @Bean
    public RabbitAdmin rabbitAdmin(ConnectionFactory connectionFactory)
    {
        return new RabbitAdmin(connectionFactory);
    }

    @Bean("compta_queue")
    public Queue comptaQueue()
    {
        return new Queue("compta_queue", true);
    }

    @Bean("facture_queue")
    public Queue factureQueue()
    {
        return new Queue("facture_queue", true);
    }

    @Bean
    public TopicExchange fanoutExchange()
    {
        return new TopicExchange("topic.facture");
    }

    @Bean
    public Binding fBind(TopicExchange exchange, @Qualifier("facture_queue") Queue queue)
    {
        return BindingBuilder.bind(queue).to(exchange).with("facture.*");
    }

    @Bean
    public Binding cBind(TopicExchange exchange, @Qualifier("compta_queue") Queue queue)
    {
        return BindingBuilder.bind(queue).to(exchange).with("*.compta");
    }
}
