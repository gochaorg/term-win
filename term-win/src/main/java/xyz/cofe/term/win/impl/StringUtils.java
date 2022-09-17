package xyz.cofe.term.win.impl;

import java.util.Arrays;
import java.util.function.Function;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

@SuppressWarnings("ConstantConditions")
public class StringUtils {
    public static String join(String delim, Iterable<String> values){
        if( delim==null )throw new IllegalArgumentException("delim");
        if( values==null )throw new IllegalArgumentException("values");
        return StreamSupport.stream(values.spliterator(), false).reduce(
            (a,b)->a!=null && a.length()>0
                ? a+(b!=null && b.length()>0 ? delim+b
                : "") : b).orElse("");
    }
    public static String join(String delim, String... values){
        if( delim==null )throw new IllegalArgumentException("delim");
        if( values==null )throw new IllegalArgumentException("values");
        return join(delim, Arrays.asList(values));
    }
    public static <A> String join(String delim, Iterable<A> values, Function<A,String> toString){
        if( delim==null )throw new IllegalArgumentException("delim");
        if( values==null )throw new IllegalArgumentException("values");
        return StreamSupport.stream(values.spliterator(), false)
            .map(toString)
            .reduce(
            (a,b)->a!=null && a.length()>0
                ? a+(b!=null && b.length()>0 ? delim+b
                : "") : b).orElse("");
    }
    @SafeVarargs
    public static <A> String join(String delim, Function<A,String> toString, A ... values){
        if( delim==null )throw new IllegalArgumentException("delim");
        if( values==null )throw new IllegalArgumentException("values");
        return Arrays.stream(values)
            .map(toString)
            .reduce(
            (a,b)->a!=null && a.length()>0
                ? a+(b!=null && b.length()>0 ? delim+b
                : "") : b).orElse("");
    }
}
