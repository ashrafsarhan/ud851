# Popular Movies - Stage1
 An app to help users discover popular and recent movies.

 This app will:

 - Upon launch, present the user with an grid arrangement of movie posters.
 - Allow the user to change sort order via a setting: The sort order can be by most popular, or by top rated
 - Allow the user to tap on a movie poster and transition to a details screen with additional information such as:
   - original title
   - movie poster image - background poster
   - A plot synopsis (called overview in the api)
   - user rating (called vote_average in the api)
   - release date
   - original language
   
## What Will I Learn After Stage 1?
You will fetch data from the Internet with theMovieDB API.
You will use adapters and custom list layouts to populate list views.
You will incorporate libraries to simplify the amount of code you need to write

## App Media

| ![Home Screen - Popular Movies](https://i.postimg.cc/d0TbM4Hb/Screenshot-20190819-163631.png) | ![Sort Criteria](https://i.postimg.cc/rFmYXfzf/Screenshot-20190819-163653.png) | ![Top Rated Movies](https://i.postimg.cc/28K9znZr/Screenshot-20190819-163709.png) | ![Details Screen](https://i.postimg.cc/tgPMv77y/Screenshot-20190819-163816.png) |
|:---:|:---:|:---:|:---:|

# Popular Movies - Stage2
 This is an extension to the Popular Movies app which is built in stage 1
  
 This app will:
 
 - Modify the existing sorting criteria for the main view to include an additional pivot to show their favorites collection.
 - In Details screen of the selected movie:
    - Allow users to read reviews of a selected movie
    - Allow users to view and play trailers (either in the youtube app or a web browser).
    - Allow users to see the cast of a selected movie
    - Allow users to mark a movie as a favorite in the details view by tapping a button (star).
    - Allow users to share the movie trailer link to social media or any other sharing platform on long press
   
## What Will I Learn After Stage 2?
 - Make use of Android Architecture Components (Room, LiveData, ViewModel and Lifecycle) to create a robust an efficient application.
 - Create a database using Room to store the names and ids of the user's favorite movies (and optionally, the rest of the information needed to display their favorites collection while offline).

## App Media

| ![Home Screen - Popular Movies](https://i.postimg.cc/1tM6n9yZ/Screenshot-20190909-220543.png) | ![Sort Criteria](https://i.postimg.cc/LXmL8qKT/Screenshot-20190909-220550.png)| ![Details Screen](https://i.postimg.cc/vHKrtHfY/Screenshot-20190909-220601.png) | ![Details Screen Extended](https://i.postimg.cc/Dzk1RjxR/Screenshot-20190909-220624.png) |
|:---:|:---:|:---:|:---:|
| ![Reviews](https://i.postimg.cc/t4VhSQNh/Screenshot-20190909-220644.png)| ![Favorites](https://i.postimg.cc/YS6Q76V0/Screenshot-20190909-220734.png) | 

## API Key
The movie information uses [The Movie Database (TMDb)](https://www.themoviedb.org/documentation/api) API.
To make your app work, you have to enter your own API key into `build.gradle` file.

```build.gradle (app)
API_KEY="Api Key"
```