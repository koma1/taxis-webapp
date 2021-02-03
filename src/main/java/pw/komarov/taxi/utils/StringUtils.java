package pw.komarov.taxi.utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.util.AntPathMatcher;

public final class StringUtils {
    public static boolean isEmpty(String s) {
    	return s == null || s.isEmpty();
    }
    
	public static List<String> parseQuoted(String string) {
        if(string == null)
        	return null;
        
		List<String> result = new ArrayList<>(); //https://stackoverflow.com/questions/3366281/tokenizing-a-string-but-ignoring-delimiters-within-quotes

        String regex = "\"([^\"]*)\"|(\\S+)";
        Matcher m = Pattern.compile(regex).matcher(string);
        while (m.find()) {
            if (m.group(1) != null) {
                result.add(m.group(1));
            } else {
                result.addAll(Arrays.asList(m.group(2).split(",")));
            }
        }

        return result;
    }
    
    public static boolean urlMatch(String url, String pattern) {
    	return (new AntPathMatcher().match(pattern, url));
    }
}