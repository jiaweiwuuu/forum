package touch.forum.utils;

public class RedisKeyUtil {

    private static String SPLIT = "-";
    private static String FOLLOWER_SERVICE_PREFIX = "FOLLOWER";
    private static String FOLLOWEE_SERVICE_PREFIX = "FOLLOWEE";

    private static String TIMELINE_SERVICE_PREFIX = "TIMELINE";

    public static String getFollowerKey(int entityId, int entityType){
        return FOLLOWER_SERVICE_PREFIX + SPLIT + entityId + SPLIT + entityType;
    }
    public static String getFolloweeKey(int userId, int entityType){
        return FOLLOWEE_SERVICE_PREFIX + SPLIT + userId + SPLIT + entityType;
    }

    public static String getTimelineKey(int userId){
        return TIMELINE_SERVICE_PREFIX+SPLIT+userId;
    }
}
