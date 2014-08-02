package Tools;
 
import Main.Main;

public class Tools {
    
    PrintFormatter printFormatter;
    ChatFilter filter;
    
    public Tools(Main main){
        printFormatter = new PrintFormatter(main);
        filter = new ChatFilter(main);
    }
    public PrintFormatter getPrintFormatter(){
        return printFormatter;
    }
    public ChatFilter getChatFilter(){
        return filter;
    }
}
