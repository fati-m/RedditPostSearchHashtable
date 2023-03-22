import java.util.List;

public interface CHSearchFrontendInterface {
    //public CHSearchFrontendXX(Scanner userInput, CHSearchBackendInterface backend);
    public void runCommandLoop();
    public char mainMenuPrompt();
    public void loadDataCommand();
    public List<String> chooseSearchWordsPrompt();
    public void searchTitleCommand(List<String> words);
    public void searchBodyCommand(List<String> words);
    public void searchPostCommand(List<String> words);
    public void displayStatsCommand();
}