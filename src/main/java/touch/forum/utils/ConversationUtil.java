package touch.forum.utils;

public class ConversationUtil {
    public static String getConversationId(int fromId, int toId) {
        return fromId < toId ? (fromId + "-" + toId) : (toId + "-" + fromId);
    }
    public static int getContacterId(String fromId,String conversationId){
        String[] ids = conversationId.split("-");
        String id = fromId.equals(ids[0])? ids[1] : ids[0];
        return Integer.parseInt(id);
    }
}
