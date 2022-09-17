package xyz.cofe.term.win;

import xyz.cofe.term.win.impl.StringUtils;

public class CharAttributes {
    private final int flags;
    public CharAttributes( int flags ){
        this.flags = flags;
    }

    public int getFlags(){ return flags; }
    public CharAttributes flags(int flags){
        return new CharAttributes(flags);
    }

    public boolean isFgBlue(){ return CharAttributesFlags.FOREGROUND_BLUE.has(flags); }
    public CharAttributes fgBlue(boolean switchOn){ return new CharAttributes(CharAttributesFlags.FOREGROUND_BLUE.update(flags, switchOn)); }

    public boolean isFgGreen(){ return CharAttributesFlags.FOREGROUND_GREEN.has(flags); }
    public CharAttributes fgGreen(boolean switchOn){ return new CharAttributes(CharAttributesFlags.FOREGROUND_GREEN.update(flags, switchOn)); }

    public boolean isFgRed(){ return CharAttributesFlags.FOREGROUND_RED.has(flags); }
    public CharAttributes fgRed(boolean switchOn){ return new CharAttributes(CharAttributesFlags.FOREGROUND_RED.update(flags, switchOn)); }

    public boolean isFgIntensity(){ return CharAttributesFlags.FOREGROUND_INTENSITY.has(flags); }
    public CharAttributes fgIntensity(boolean switchOn){ return new CharAttributes(CharAttributesFlags.FOREGROUND_INTENSITY.update(flags, switchOn)); }

    public boolean isBgBlue(){ return CharAttributesFlags.BACKGROUND_BLUE.has(flags); }
    public CharAttributes bgBlue(boolean switchOn){ return new CharAttributes(CharAttributesFlags.BACKGROUND_BLUE.update(flags, switchOn)); }

    public boolean isBgGreen(){ return CharAttributesFlags.BACKGROUND_GREEN.has(flags); }
    public CharAttributes bgGreen(boolean switchOn){ return new CharAttributes(CharAttributesFlags.BACKGROUND_GREEN.update(flags, switchOn)); }

    public boolean isBgRed(){ return CharAttributesFlags.BACKGROUND_RED.has(flags); }
    public CharAttributes bgRed(boolean switchOn){ return new CharAttributes(CharAttributesFlags.BACKGROUND_RED.update(flags, switchOn)); }

    public boolean isBgIntensity(){ return CharAttributesFlags.BACKGROUND_INTENSITY.has(flags); }
    public CharAttributes bgIntensity(boolean switchOn){ return new CharAttributes(CharAttributesFlags.BACKGROUND_INTENSITY.update(flags, switchOn)); }

    public boolean isLVBLeadingByte(){ return CharAttributesFlags.COMMON_LVB_LEADING_BYTE.has(flags); }
    public CharAttributes lvbLeadingByte(boolean switchOn){ return new CharAttributes(CharAttributesFlags.COMMON_LVB_LEADING_BYTE.update(flags, switchOn)); }

    public boolean isLVBTrailingByte(){ return CharAttributesFlags.COMMON_LVB_TRAILING_BYTE.has(flags); }
    public CharAttributes lvbTrailingByte(boolean switchOn){ return new CharAttributes(CharAttributesFlags.COMMON_LVB_TRAILING_BYTE.update(flags, switchOn)); }

    public boolean isLVBGridHorizontal(){ return CharAttributesFlags.COMMON_LVB_GRID_HORIZONTAL.has(flags); }
    public CharAttributes lvbGridHorizontal(boolean switchOn){ return new CharAttributes(CharAttributesFlags.COMMON_LVB_GRID_HORIZONTAL.update(flags, switchOn)); }

    public boolean isLVBGridLVertical(){ return CharAttributesFlags.COMMON_LVB_GRID_LVERTICAL.has(flags); }
    public CharAttributes lvbGridLVertical(boolean switchOn){ return new CharAttributes(CharAttributesFlags.COMMON_LVB_GRID_LVERTICAL.update(flags, switchOn)); }

    public boolean isLVBGridRVertical(){ return CharAttributesFlags.COMMON_LVB_GRID_RVERTICAL.has(flags); }
    public CharAttributes lvbGridRVertical(boolean switchOn){ return new CharAttributes(CharAttributesFlags.COMMON_LVB_GRID_RVERTICAL.update(flags, switchOn)); }

    public boolean isLVBReverseVideo(){ return CharAttributesFlags.COMMON_LVB_REVERSE_VIDEO.has(flags); }
    public CharAttributes lvbReverseVideo(boolean switchOn){ return new CharAttributes(CharAttributesFlags.COMMON_LVB_REVERSE_VIDEO.update(flags, switchOn)); }

    public boolean isLVBUnderscore(){ return CharAttributesFlags.COMMON_LVB_UNDERSCORE.has(flags); }
    public CharAttributes lvbUnderscore(boolean switchOn){ return new CharAttributes(CharAttributesFlags.COMMON_LVB_UNDERSCORE.update(flags, switchOn)); }

    private static String toString(CharAttributes attributes){
        if( attributes==null )throw new IllegalArgumentException("attributes==null");
        return "CharAttributes{"+StringUtils.join(
            ",",
            "flags="+attributes.flags,
            attributes.isFgBlue() ? "FgBlue" : "",
            attributes.isFgGreen() ? "FgGreen" : "",
            attributes.isFgRed() ? "FgRed" : "",
            attributes.isFgIntensity() ? "FgIntensity" : "",
            attributes.isBgBlue() ? "BgBlue" : "",
            attributes.isBgGreen() ? "BgGreen" : "",
            attributes.isBgRed() ? "BgRed" : "",
            attributes.isBgIntensity() ? "BgIntensity" : "",
            attributes.isLVBLeadingByte() ? "LVBLeadingByte" : "",
            attributes.isLVBTrailingByte() ? "LVBTrailingByte" : "",
            attributes.isLVBGridHorizontal() ? "LVBGridHorizontal" : "",
            attributes.isLVBGridLVertical() ? "LVBGridLVertical" : "",
            attributes.isLVBGridRVertical() ? "LVBGridRVertical" : "",
            attributes.isLVBReverseVideo() ? "LVBReverseVideo" : "",
            attributes.isLVBUnderscore() ? "LVBUnderscore" : ""
        )+"}";
    }

    public String toString(){
        return toString(this);
    }
}
