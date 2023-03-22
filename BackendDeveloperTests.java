import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.List;


public class BackendDeveloperTests {
    public static void main(String args[]) {
        if(test1()){
            System.out.println("BackendDeveloper Individual Test 1: passed");  
        } else {
            System.out.println("BackendDeveloper Individual Test 1: failed"); 
        }

        if(test2()){
            System.out.println("BackendDeveloper Individual Test 2: passed");  
        } else {
            System.out.println("BackendDeveloper Individual Test 2: failed"); 
        }
        
        if(test3()){
            System.out.println("BackendDeveloper Individual Test 3: passed");  
        } else {
            System.out.println("BackendDeveloper Individual Test 3: failed"); 
        }

        if(test4()){
            System.out.println("BackendDeveloper Individual Test 4: passed");  
        } else {
            System.out.println("BackendDeveloper Individual Test 4: failed"); 
        }
        if(test5()){
            System.out.println("BackendDeveloper Individual Test 5: passed");  
        } else {
            System.out.println("BackendDeveloper Individual Test 5: failed"); 
        }

        if(integrationTest1()){
            System.out.println("BackendDeveloper Integration Test 1: passed");  
        } else {
            System.out.println("BackendDeveloper Integration Test 1: failed"); 
        }

        if(integrationTest2()){
            System.out.println("BackendDeveloper Integration Test 2: passed");  
        } else {
            System.out.println("BackendDeveloper Integration Test 2: failed"); 
        }

        if(frontendTest1()){
            System.out.println("BackendDeveloper Partner (FrontendDeveloper) Test 1: passed");  
        } else {
            System.out.println("BackendDeveloper Partner (FrontendDeveloper) Test 1: failed"); 
        }

        if(frontendTest2()){
            System.out.println("BackendDeveloper Partner (FrontendDeveloper) Test 2: passed");  
        } else {
            System.out.println("BackendDeveloper Partner (FrontendDeveloper) Test 2: failed"); 
        }
    }

    /**
     * tests the correctness of the load data with real file
     * 
     * @return true if expected happens, false otherwise
     */
    public static boolean test1() {
        // Set up necessary variables
        HashtableWithDuplicateKeysInterface<String, PostInterface> hashtable = new HashtableWithDuplicateKeysAE<>();
        PostReaderDW postReader = new PostReaderDW();
        CHSearchBackendBD testerInstance = new CHSearchBackendBD(hashtable, postReader);

        // real file should not throw exception 
        try{
            testerInstance.loadData("P1W4Tests.txt"); 
        } catch(FileNotFoundException e){
            return false; // the file exists and should not throw this exception
        } catch(Exception e) {
            return false;
        }

        return true;
    }

    /**
     * checks the correctness of findPostsByTitleWords 
     * 
     * @return true if expected happens, false otherwise
     */
    public static boolean test2() {
        // Set up necessary variables
        HashtableWithDuplicateKeysInterface<String, PostInterface> hashtable = new HashtableWithDuplicateKeysAE<>();
        PostReaderDW postReader = new PostReaderDW();
        CHSearchBackendBD testerInstance = new CHSearchBackendBD(hashtable, postReader);

        // load the file
        try{
        testerInstance.loadData("P1W4Tests.txt");  
        } catch(FileNotFoundException e) {
            e.printStackTrace();
            return false;
        } 

        // check if method returns the expected string with key title words
        if(!testerInstance.findPostsByTitleWords("Soups").get(0).equals("Soups and chili's? URL: https://www.reddit.com/r/EatCheapAndHealthy/comments/yxb2vi/soups_and_chilis/")){
            return false;
        }

        if(!testerInstance.findPostsByTitleWords("chili's").get(0).equals("Soups and chili's? URL: https://www.reddit.com/r/EatCheapAndHealthy/comments/yxb2vi/soups_and_chilis/")){
            return false;
        }

        return true;
    }

    /**
     * test correctness of findPostsByBodyWords
     * 
     * @return true if expected happens, false otherwise
     */
    public static boolean test3() {
         // Set up necessary variables
         HashtableWithDuplicateKeysInterface<String, PostInterface> hashtable = new HashtableWithDuplicateKeysAE<>();
         PostReaderDW postReader = new PostReaderDW();
         CHSearchBackendBD testerInstance = new CHSearchBackendBD(hashtable, postReader);
 
        // load the file
        try{
        testerInstance.loadData("P1W4Tests.txt");  
        } catch(FileNotFoundException e) {
             e.printStackTrace();
             return false;
        } 
 
        // check if method returns the expected key body words string
        if(!testerInstance.findPostsByBodyWords("Anyone").get(0).equals("Anyone know a cheap soup or chili that's fast and easy to make or buy? URL: https://www.reddit.com/r/EatCheapAndHealthy/comments/yxb2vi/soups_and_chilis/")){
             return false;
         }

        if(!testerInstance.findPostsByBodyWords("that's").get(0).equals("Anyone know a cheap soup or chili that's fast and easy to make or buy? URL: https://www.reddit.com/r/EatCheapAndHealthy/comments/yxb2vi/soups_and_chilis/")){
            return false;
        }
 
         return true;
    }

    /**
     * test correctness of findPostsByTitleOrBodyWords
     * 
     * @return true if expected happens, false otherwise
     */
    public static boolean test4() {
        // Set up necessary variables
        HashtableWithDuplicateKeysInterface<String, PostInterface> hashtable = new HashtableWithDuplicateKeysAE<>();
        PostReaderDW postReader = new PostReaderDW();
        CHSearchBackendBD testerInstance = new CHSearchBackendBD(hashtable, postReader);

       // load the file
       try{
       testerInstance.loadData("P1W4Tests.txt");  
       } catch(FileNotFoundException e) {
            e.printStackTrace();
            return false;
       } 
       
       // check if method returns the expected key body words string
       if(!testerInstance.findPostsByTitleOrBodyWords("Anyone").get(0).equals("Anyone know a cheap soup or chili that's fast and easy to make or buy? URL: https://www.reddit.com/r/EatCheapAndHealthy/comments/yxb2vi/soups_and_chilis/")){
            return false;
        }

        // check if method returns the expected key title words string
        if(!testerInstance.findPostsByTitleOrBodyWords("chili's").get(0).equals("Soups and chili's? URL: https://www.reddit.com/r/EatCheapAndHealthy/comments/yxb2vi/soups_and_chilis/")){
            return false;
        }

        // check if method returns the expected key title words string
        if(!testerInstance.findPostsByTitleOrBodyWords("soups").get(0).equals("Soups and chili's? URL: https://www.reddit.com/r/EatCheapAndHealthy/comments/yxb2vi/soups_and_chilis/")){
            return false;
        }

        return true;
    }

    /**
     * tests the correctness of the load data with fake file
     * 
     * @return true if expected happens, false otherwise
     */
    public static boolean test5() {
        // Set up necessary variables
        HashtableWithDuplicateKeysInterface<String, PostInterface> hashtable = new HashtableWithDuplicateKeysAE<>();
        PostReaderDW postReader = new PostReaderDW();
        CHSearchBackendBD testerInstance = new CHSearchBackendBD(hashtable, postReader);

        // fake file should throw exception 
        try{
            testerInstance.loadData("fake.txt"); 
            return false;
        } catch(FileNotFoundException e){
        } catch(Exception e) {
            return false;
        }

        return true;
    }

    /**
     * tests when CHSearchBackendBD and HashtableMap work together. Using loadData() from CHSearchBackendBD
     * should add values to the hashtable, therefore containsKey() from HashtableMap class should be true
     * for expected values
     * 
     * @return true if expected happens, false otherwise
     */
    public static boolean integrationTest1() {
        // Set up necessary variables
        HashtableWithDuplicateKeysInterface<String, PostInterface> hashtable = new HashtableWithDuplicateKeysAE<>();
        PostReaderDW postReader = new PostReaderDW();
        CHSearchBackendBD testerInstance = new CHSearchBackendBD(hashtable, postReader);

        // load data from txt file
        try{
            testerInstance.loadData("P1W4Tests.txt"); 
        } catch(FileNotFoundException e){
        } catch(Exception e) {
            return false;
        }

        // check if hashtable contains one of the key title words in expected form
        if(!hashtable.containsKey("TITLE: soups")) {
            return false;
        }

        return true;
    }

    /**
    * tests when PostReaderDW and PostDW work together. Using readPostsFromFile() from PostReader
    * should add properly load title, url, and body information of a post. Therefore getTitle(), getURL(),
    * and getBody() should return expected values
    * 
    * @return true if expected happens, false otherwise
    */
    public static boolean integrationTest2() {
        // read file and store post info
        PostReaderDW postReader = new PostReaderDW();
        List<PostInterface> posts = null;
        try{
            posts = postReader.readPostsFromFile("P1W4Tests.txt");
        } catch(FileNotFoundException e){
        } catch(Exception e) {
            return false;
        }

        // check for expected title
        if(!posts.get(0).getTitle().equals("Soups and chili's?")) {
            return false;
        }

        // check for expected url
        if(!posts.get(0).getUrl().equals("https://www.reddit.com/r/EatCheapAndHealthy/comments/yxb2vi/soups_and_chilis/")) {
            return false;
        }

        // check for expected body
        if(!posts.get(0).getBody().equals("Anyone know a cheap soup or chili that's fast and easy to make or buy?")) {
            return false;
        }
            return true;
    }

    /**
    * Test that the CHFrontendFD class properly responses to the selection of L to
    * load a song and the Q to quit
    * 
    * @return true if expected happens, false otherwise
    */
    public static boolean frontendTest1() {
        TextUITester tester = new TextUITester("L\nP1W4Tests.txt\nQ\n");
        try (Scanner scnr = new Scanner(System.in)) {
            HashtableWithDuplicateKeysInterface<String, PostInterface> hashtable = new HashtableWithDuplicateKeysAE<>();
            PostReaderDW postReader = new PostReaderDW();
            CHSearchFrontendFD frontend = new CHSearchFrontendFD(scnr, new CHSearchBackendBD(hashtable, postReader));
            frontend.runCommandLoop();
            String returnedOutput = tester.checkOutput();
            scnr.close();

            if (!returnedOutput.startsWith("---------")) {
                return false;
            }

            if (!returnedOutput.contains("[L]oad data from file")) {
                return false;
            }

            if (!returnedOutput.contains("Enter the name")) {
                return false;
            }

            if (!returnedOutput.trim().endsWith("---------")) {
                return false;
            }
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    /**
    *  Test that the CHFrontendFD class properly responses to the selection of L to
    * load a song, then to add a title search word "soup" by pressing T, then lastly selecting Q to quit
    * 
    * @return true if expected happens, false otherwise
    */
    public static boolean frontendTest2() {
        TextUITester tester = new TextUITester("L\nP1W4Tests.txt\nQ\n");
        try (Scanner scnr = new Scanner(System.in)) {
            HashtableWithDuplicateKeysInterface<String, PostInterface> hashtable = new HashtableWithDuplicateKeysAE<>();
            PostReaderDW postReader = new PostReaderDW();
            CHSearchFrontendFD frontend = new CHSearchFrontendFD(scnr, new CHSearchBackendBD(hashtable, postReader));
            frontend.runCommandLoop();
            String returnedOutput = tester.checkOutput();
            scnr.close();

            if (!returnedOutput.startsWith("---------")) {
                return false;
            }

            if (!returnedOutput.contains("[L]oad data from file")) {
                return false;
            }

            if (!returnedOutput.contains("Enter the name")) {
                return false;
            }

            if (!returnedOutput.trim().endsWith("---------")) {
                return false;
            }
        } catch (Exception e) {
            return false;
        }
        return true;
    }
}