package touch.forum.async.handlers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import touch.forum.async.EventHandler;
import touch.forum.async.EventModel;
import touch.forum.async.EventType;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component
@Slf4j
public class LikeHandler implements EventHandler {
    @Override
    public void doHandle(EventModel model) {
        log.info("LikeHandler is processing task [{}]",model.toString());
    }

    @Override
    public List<EventType> getSuppportEventTypes() {
        return Arrays.asList(EventType.LIKE);

    }
}
