package analyzer.propaties;

public class CUI {
    public static final String color_cyan   = "\u001b[00;36m";
    public static final String end    = "\u001b[00m";
    public static final String color_red    = "\u001b[00;31m";

    public static final String bg_cyan= "\u001b[00;46m";
    public static final String bg_red    = "\u001b[00;41m";

    public static void println(String str,String color){
        String _color="";
        switch (color){
            case "color_cyan":
                _color=color_cyan;
                break;
            case "color_red":
                _color=color_red;
                break;
            case "bg_cyan":
                _color=bg_cyan;
                break;
            case "bg_red":
                _color=bg_red;
                break;
        }
        System.out.println(_color+str+end);
    }
}
