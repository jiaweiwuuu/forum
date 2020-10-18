package touch.forum.async;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@Service
@Slf4j
public class EventConsumer implements InitializingBean, ApplicationContextAware {
    @Autowired
    RedisTemplate<String,Object> redisTemplate;
    private Map<EventType, List<EventHandler>> registry = new HashMap<>();
    private ApplicationContext applicationContext;
    @Override
    public void afterPropertiesSet() throws Exception {
        Map<String,EventHandler> beans = applicationContext.getBeansOfType(EventHandler.class);
        if(beans != null){
            for(String beanName : beans.keySet()){
                List<EventType> eventTypes = beans.get(beanName).getSuppportEventTypes();
                for(EventType type : eventTypes){
                    if(!registry.containsKey(type)){
                        registry.put(type,new ArrayList<>());
                    }
                    List<EventHandler> eventHandlers = registry.get(type);
                    eventHandlers.add(beans.get(beanName));
                }
            }
        }
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try{
                    while(true){
                        String EVENT_QUEUE_KEY = "EVENT_QUEUE";
                        log.info("-------------------ready to work next event --------------------");
                        EventModel event = (EventModel)redisTemplate.opsForList().rightPop(EVENT_QUEUE_KEY,0,TimeUnit.DAYS);
                        log.info("new event: [{}]",event.toString());
                        if(!registry.containsKey(event.getEventType())){
                            log.error("something wrong");
                            continue;
                        }
                        for(EventHandler handler : registry.get(event.getEventType())){
                            handler.doHandle(event);
                        }
                    }
                }
                catch (Exception e){
                    log.info(e.getMessage());
                }
            }
        });
        thread.start();
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }
}
