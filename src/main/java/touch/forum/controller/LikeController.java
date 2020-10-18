package touch.forum.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import touch.forum.ResultVO;
import touch.forum.async.EventModel;
import touch.forum.async.EventProducer;
import touch.forum.async.EventType;
import touch.forum.entity.HostHolder;
import touch.forum.service.LikeService;
import touch.forum.utils.ResponseUtil;

@RestController
@RequestMapping("like")
public class LikeController {
//    @Autowired
//    HostHolder hostHolder;
//    @Autowired
//    LikeService service;
//    @Autowired
//    EventProducer eventProducer;
//    @PostMapping("thumbsup")
//    public ResultVO<Object> like(@RequestParam("entity_id") Integer entityId,@RequestParam("entity_type") Integer entityType){
//        long count = service.like(hostHolder.getUser().getId(),entityId,entityType);
//        EventModel eventModel = new EventModel().setEventType(EventType.LIKE).setActorId(1).setEntityId(15).setEntityType(0).setEntityOwner(3);
//        eventProducer.fireEvent(eventModel);
//
//        return ResponseUtil.makeSuccessResponse(count);
//
//    }
//
//    @PostMapping("thumbsdown")
//    public ResultVO<Object> unlike(@RequestParam("entity_id") Integer entityId,@RequestParam("entity_type") Integer entityType){
//        long count = service.unlike(hostHolder.getUser().getId(),entityId,entityType);
//        return ResponseUtil.makeSuccessResponse(count);
//
//    }
//
//    @GetMapping("status")
//    public ResultVO<Object> status(@RequestParam("entity_id") Integer entityId,@RequestParam("entity_type") Integer entityType){
//        boolean rseult = service.getLikeStatus(hostHolder.getUser().getId(),entityId,entityType);
//        return ResponseUtil.makeSuccessResponse(rseult);
//
//    }


}
