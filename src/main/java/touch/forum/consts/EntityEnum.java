package touch.forum.consts;

public enum EntityEnum {
    QUESTION(0),
    COMMENT(1);

    private int value;
    EntityEnum(int value){
        this.value = value;
    }
    public int getValue(){
        return this.value;
    }
}
