package Layer;

public class Application {
    public static final String COMMANDPREFIX = "c!";
    public boolean isCommand(String input) {
        if(input.startsWith(COMMANDPREFIX)){
            return true; 
        }
        else{
            return false;
        } 
    }
    public void processCommand(String command){
        switch(command) {

        }
    }
}
