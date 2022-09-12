package xyz.cofe.term.win;

public interface BitFlag {
    public int bitFlag();
    public default boolean has(int flags){
        return (flags & bitFlag()) == bitFlag();
    }
    public default int update(int flags,boolean switchOn){
        return switchOn
                ? flags | bitFlag()
                : flags & (~ bitFlag());
    }
}
