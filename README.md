# Where is the Music?

## About the app

The purpose of this Android application is what allows the user to stay for future musical events of artists that interest him or in areas that interest him.
The user can follow events, which is the equivalent of saving it so that he doesn't forget, follow cities/zones so that they are also aware of interesting events in the locations that interest him, and he can also follow artists so that he is aware of events in the that may be of interest.
The application uses as a data source or SongKickApi.

## Structure

![HomePage](https://github.com/wsalvaterra-ua/EventsFinder/blob/master/Photos/Screenshot_2022-06-29-16-18-39-46_deceeed0c1ece1f88ef7a10e86bf50a6.jpg?raw=true)

### Activities

Activities
The activities used in the application are as follows:

 - Main Activity
- - Home Fragment
- - Search Fragment
- - Following Fragment
- Search Activity
- Event Activity
- Artist Activity
- Location Activity

### Navigation Flow
![enter image description here](https://github.com/wsalvaterra-ua/EventsFinder/blob/master/Photos/Untitled%20Diagram.jpg?raw=true)

## Development
### External Code
For the development of the application the following libraries were used:
#### Room
This library allows relating objects in the program with database tables
##### Gson
This tool allows you to convert objects to JSON and also the opposite.
Picasso
Allows you to upload images either local or online to the TextView.
##### SongKickApi
This library allows you to relate the data received from SongKickAPI with java objects.
##### GPSTracker
This bookstore allows you to easily access the GPS and acquire the location.
### Database
The program saves and uses the following data in the database:
- Events, Artists accessed
- Artists, Events, Locations that the user follows.
## Activities

### Main Activity
That's where the program starts.
This activity on launch disables the policy that prohibits the program from communicating with the internet on the Main Thread and also asks the user to allow access to the GPS.
The activity is divided into 3 fragments:
#### Home Fragment
![enter image description here](https://github.com/wsalvaterra-ua/EventsFinder/blob/master/Photos/Screenshot_2022-06-29-17-01-04-26_deceeed0c1ece1f88ef7a10e86bf50a6.jpg?raw=true)

It is the main UI the user in it the user can stay up to date with events that interest him and also see artists that may interest him, to navigate to an event/artist just click on it.
##### Relevant methods
- `loadArtistsSimilar` This method loads a random similar artists that the user follows.
- `loadEventsNearCityYouFollow` this method searches for events near a random city that the user follows.
- `getLocation` this method receives the user's location through the GPSTracker library
- `loadEventsNearMe` obtained the location the method loads the events near it using the geoLocation.
- `loadSimiliarArtistFromRecentSearches` this method loads artists similar to one that the artist has seen(entered the artist activity) recently

#### Search Fragment

![enter image description here](https://github.com/wsalvaterra-ua/EventsFinder/blob/master/Photos/Screenshot_2022-06-29-17-01-08-48_deceeed0c1ece1f88ef7a10e86bf50a6.jpg?raw=true)

This fragment has 2 lists and a button.

In this Fragment the first list consists of events and artists that the user has recently accessed.
The second list of elements is made up of artists similar to the one the user recently viewed.
The button at the top of the screen with a TextField appearance will take the user to the Search Activity.

##### Relevant methods
• `loadSimiliarArtistIntoView` this method loads artists similar to the one the artist accessed (joined the artist activity) recently.
• `loadRecentSearchesIntoView` loads recently accessed artists and events
#### Following Fragment
![enter image description here](https://github.com/wsalvaterra-ua/EventsFinder/blob/master/Photos/Screenshot_2022-06-29-17-01-14-24_deceeed0c1ece1f88ef7a10e86bf50a6.jpg?raw=true)

In this fragment the user can see the Events, Artists and Locations that follows.
##### Relevant Methods
- `loadFavoriteArtist` this method loads the artists that the user follows into the list.
- `loadFavoriteEvent` this method loads the events that the user follows into the list.
- `loadFavoriteLocation` this method loads the locations the user follows into the list.

### Search Activity

![enter image description here](https://github.com/wsalvaterra-ua/EventsFinder/blob/master/Photos/Screenshot_2022-06-29-18-25-11-00_deceeed0c1ece1f88ef7a10e86bf50a6.jpg?raw=true)

##### Relevant Methods
- `configSearchView` this method configures the search text box giving it focus as soon as the user starts this activity.
- `searchByArtists` this method is executed when the user tries to search for an artist, making an API call and executing the search method provided by the API and once it receives the results the method places them in the list so that the user can turn.
- `searchByLocation` this method works in the same way as the previous one only this time for the location
### Search Activity

