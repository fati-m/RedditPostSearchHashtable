# RedditPostSearchHashtable
This is a hash table that makes use of a database of reddit posts from kaggle. The code returns reddit posts with their title, url, and body that match key words entered by the user.

The data is from this free data set on kaggle: https://www.kaggle.com/datasets/thedevastator/budget-friendly-healthy-meals-reddit-s-proven-gu?resource=download

The RedditPostsData.txt contains all the posts from the dataset converted to txt. Each element of title, body, and url is seperated by a delimiter of "+++".

# How it Works
An example of a post in the txt file:

>**Vegetarian burrito filling?+++https://www.reddit.com/r/EatCheapAndHealthy/comments/znz8mu/vegetarian_burrito_filling/+++Supermarkets here don't sell tofu. Paneer is too expensive. What could I put instead?**

The hash table works by seperating every word in the title and body and giving each a special key. The key-word pair is then stored into the hash table to be accessed at a later time with the post's title, url, and body information still intact. 

# The structure 

>**title +++ url +++ body**

For example, if if I want to search for posts containing the word "burrito", the post example above will be one of the results. The results return in this form:

>**[3] Vegetarian burrito filling? URL: https://www.reddit.com/r/EatCheapAndHealthy/comments/znz8mu/vegetarian_burrito_filling/**

"[3]" means that it is the 3rd match in the data.
