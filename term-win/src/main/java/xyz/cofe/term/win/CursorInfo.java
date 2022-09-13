package xyz.cofe.term.win;

import java.util.ArrayList;

public class CursorInfo {
    public CursorInfo(int size, boolean visible){
        this.size = size;
        this.visible = visible;
    }

    private final int size;
    private final boolean visible;

    public int getSize() {
        return size;
    }

    public CursorInfo size(int size){
        if( size<1 )throw new IllegalArgumentException("size<1");
        if( size>100 )throw new IllegalArgumentException("size>100");
        return new CursorInfo(size,visible);
    }

    public boolean isVisible() {
        return visible;
    }

    public CursorInfo visible(boolean visible){
        return new CursorInfo(size,visible);
    }

    public String toString(){
        var sb = new StringBuilder();
        var ls = new ArrayList<String>();
        ls.add("size="+size);
        ls.add("visible="+visible);

        sb.append("CursorInfo{");
        sb.append(ls.stream().reduce("", (a,b)->a.length()>0 ? a+(b.length()>0 ? ","+b : "") : b));
        sb.append("}");
        return sb.toString();
    }
}
