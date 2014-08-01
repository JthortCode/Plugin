package Tools;
 
import Main.Main;

public class Tools {
    
    PrintFormatter printFormatter;
    
    public Tools(Main main){
        printFormatter = new PrintFormatter(main);
    }
    public PrintFormatter getPrintFormatter(){
        return printFormatter;
    }
}
