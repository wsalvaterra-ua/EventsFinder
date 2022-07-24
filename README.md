# Where is the Music?

## About the app

This Android application will allow the user to stay tuned to future musical events of artists that might interest him or in cities that he might want to watch, to do that the user must the artist or city that interests him.
The application uses as a data source or SongKickApi.

**You must first get a SongKick api before using this app**
https://www.songkick.com/api_key_requests/new

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
-  `loadSimiliarArtistIntoView` this method loads artists similar to the one the artist accessed (joined the artist activity) recently.
- `loadRecentSearchesIntoView` loads recently accessed artists and events
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
### Event Activity
![enter image description here](https://github.com/wsalvaterra-ua/EventsFinder/blob/master/Photos/Screenshot_2022-06-29-17-01-26-75_deceeed0c1ece1f88ef7a10e86bf50a6.jpg?raw=true)
This activity shows when using event data and the artists who will participate in the same event. 

You can also add the event to the device calendar.
![enter image description here](https://github.com/wsalvaterra-ua/EventsFinder/blob/master/Photos/Screenshot_2022-06-29-18-49-29-04_3481fdfaf807158df2e382fd7e3ae703.jpg?raw=true)

#### Relevant Methods
- `addToHistory` this method adds the artist to the database as recently seen
- `fillElements` this method loads the data for the various components in the activity is the method that loads all the data for the UI, the other methods are pulled through this one.
- `followUnfollow` each time this method is executed the user follows or stops following the artist, this action also having effects on the database
- `loadUpcomingEvents` this method loads the events that the artist will attend to the list
- `loadSimiliarArtists` this method loads similar artists.
- `openCalendar` redirects the user to the calendar where he can schedule the event


### Artist Activity
![enter image description here](https://github.com/wsalvaterra-ua/EventsFinder/blob/master/Photos/Screenshot_2022-06-29-18-37-00-86_deceeed0c1ece1f88ef7a10e86bf50a6.jpg?raw=true)

In this activity, the user can see Events that an artist will participate in, and can also see similar artists.
#### Relevant Methods
- `addToHistory` this method adds the artist to the database as recently seen
- `fillElements` this method loads the data for the various components in the activity is the method that loads all the data for the UI, the other methods are pulled through this one.
- `followUnfollow` each time this method is executed the user follows or unfollows the artist, this action also having effects on the database
- `loadUpcomingEvents` this method loads the events that the artist will attend to the list
- `loadSimiliarArtists` this method loads similar artists.

### Location Activity

![enter image description here](https://github.com/wsalvaterra-ua/EventsFinder/blob/master/Photos/Screenshot_2022-06-29-18-54-21-96_deceeed0c1ece1f88ef7a10e86bf50a6.jpg?raw=true)

In this activity, the user can see events that will take place in the selected city and also follow this location
#### Relevant Methods
- `fillElements` this method loads the data for the various components in the activity is the method that loads all the data for the UI, the other methods are pulled through this one.
- `followUnfollow` each time this method is executed the user follows or stops following the artist, this action also having effects on the database
- `loadEventsNearLocation` this method loads the events that will happen in the selected city

