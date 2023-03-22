import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;

/*
 * Class that creates the backend search alogorithm
 */
public class CHSearchBackendBD implements CHSearchBackendInterface {
    private HashtableWithDuplicateKeysInterface<String,PostInterface> hashtable;
    PostReaderInterface postReader;
    private int numPosts;

    /**
     * Constructor for the CHBackend class that initializes hashtable and post reader
     * 
     * @param hashtable
     * @param postReader
     */
    public CHSearchBackendBD(HashtableWithDuplicateKeysInterface<String,PostInterface> hashtable, PostReaderInterface postReader) {
        this.hashtable = hashtable;
        this.postReader = postReader;
        this.numPosts = 0;
    }



    @Override
    public void loadData(String filename) throws FileNotFoundException {
        //read the post from string and catch exception if it cant find it
        List<PostInterface> posts = null;
        try{
            posts = postReader.readPostsFromFile(filename);
        } catch(FileNotFoundException e) {
            throw new FileNotFoundException();
        }
        
        // iterate through each post to clean words and get titles and body key words
        for (PostInterface post : posts) {
            List<String> titleWords = splitAndCleanWords(post.getTitle());
            List<String> bodyWords = splitAndCleanWords(post.getBody());

            // iterate through title words and add each one to key value/ value type pair hashtable
            for (String word : titleWords) {
                    hashtable.putOne("TITLE: " + word, post);
            }

            // iterate through body words and add each one to key value/ value type pair hashtable
            for (String word : bodyWords) {
                    hashtable.putOne("BODY: " + word, post);
            }
            numPosts++;
        }
    }

     /**
     * finds all the matching posts according to title key words
     * and puts them in an array of strings
     * 
     * @param words
     * @return a list array of strings with posts that match the title parameters
     */
    @Override
    public List<String> findPostsByTitleWords(String words) {
        // clean words up and create a new array for matching posts that will get returned
        List<String> keyWords = splitAndCleanWords(words);
        List<String> matchingPostsByTitle = new ArrayList<>();

        // iterate through each word and seek matches amoung posts
        for (String word : keyWords) {
            try {  
                List<PostInterface> posts = hashtable.get("TITLE: " + word);

                // add each post signifying post type and URL
                for (PostInterface post : posts) {
                    String matchingPost = (post.getTitle() + " URL: " + post.getUrl());
                    matchingPostsByTitle.add(matchingPost);
                }
            } catch (NoSuchElementException e) {
            }
        }

        // then sort string to make it easier to find and replace duplicates
        matchingPostsByTitle.sort(null);
        
        // remove duplicates
        for (int i = 1; i < matchingPostsByTitle.size(); i++) { 
            if (matchingPostsByTitle.get(i).equals(matchingPostsByTitle.get(i - 1))) {
                matchingPostsByTitle.remove(i--);
            }
        }
        
        return matchingPostsByTitle;
    }

    
    /**
     * finds all the matching posts according to body key words
     * and puts them in an array of strings
     * 
     * @param words
     * @return a list array of strings with posts that match the body parameters
     */
    @Override
    public List<String> findPostsByBodyWords(String words) {
        // clean words up and create a new array for matching posts that will get returned
        List<String> keyWords = splitAndCleanWords(words);
        List<String> matchingPostsByBody = new ArrayList<>();

        // iterate through each word and seek matches amoung posts
        for (String word : keyWords) {
            try {  
                List<PostInterface> posts = hashtable.get("BODY: " + word);

                // add each post signifying post type and URL
                for (PostInterface post : posts) {
                    String matchingPost = (post.getBody() + " URL: " + post.getUrl());
                    matchingPostsByBody.add(matchingPost);
                }
            } catch (NoSuchElementException e) {
            }
        }
        matchingPostsByBody.sort(null);
        
        // remove duplicates
        for (int i = 1; i < matchingPostsByBody.size(); i++) { 
            if (matchingPostsByBody.get(i).equals(matchingPostsByBody.get(i - 1))) {
                matchingPostsByBody.remove(i--);
            }
        }

        return matchingPostsByBody;
    }

    /**
     * finds all the matching posts according to title or body key words
     * and puts them in an array of strings
     * 
     * @param words
     * @return a list array of strings with posts that match the title/body parameters
     */
    @Override
    public List<String> findPostsByTitleOrBodyWords(String words) {
        // clean words up and create a new array for matching posts that will get returned
        List<String> keyWords = splitAndCleanWords(words);
        List<String> matchingPostsByTitleOrBody = new ArrayList<>();

        // iterate through each word and seek title word matches amoung posts
        for (String word : keyWords) {
            try {  
                List<PostInterface> posts = hashtable.get("TITLE: " + word);

                // add each post signifying post type and URL
                for (PostInterface post : posts) {
                    String matchingPost = (post.getTitle() + " URL: " + post.getUrl());
                    matchingPostsByTitleOrBody.add(matchingPost);
                }
            } catch (NoSuchElementException e) {
            }
        }

        // iterate through each word and seek body word matches amoung posts
        for (String word : keyWords) {
            try {  
                List<PostInterface> posts = hashtable.get("BODY: " + word);

                // add each post signifying post type and URL
                for (PostInterface post : posts) {
                    String matchingPost = (post.getBody() + " URL: " + post.getUrl());
                    matchingPostsByTitleOrBody.add(matchingPost);
                }
            } catch (NoSuchElementException e) {
            }
        }
        matchingPostsByTitleOrBody.sort(null);
        
        // remove duplicates
        for (int i = 1; i < matchingPostsByTitleOrBody.size(); i++) { 
            if (matchingPostsByTitleOrBody.get(i).equals(matchingPostsByTitleOrBody.get(i - 1))) {
                matchingPostsByTitleOrBody.remove(i--);
            }
        }

        return matchingPostsByTitleOrBody;
    }

    /**
     * A method that returns the number of values in the hastable 
     * 
     * @return number of values in the hashtable
     */
    @Override
    public String getStatisticsString() {
        System.out.println("");
        String s = "----Stats----";
        s+= "\nNumber of posts: " + numPosts + "\n";
        return s;
    }   

     /**
     * Helper method that can break a string of text into words around spaces. Also strips punctuation
     * off of these words to ensure that you can find the word "buy" even if is followed by a question
     * mark in a post, like "buy?".
     * 
     * @param toBreak
     * @return string array that is broken by spaces, containes no puntuation, all lowercase
     */
    private List<String> splitAndCleanWords(String text) {
         //remove puntuations from string and make all of string lowercase
         text = text.replaceAll("[^a-zA-Z ]", "").toLowerCase();

         // split string into multiple string between spaces
         String[] toReturn = text.split(" ");

         return Arrays.asList(toReturn);
    }

}