package api.blizzed.opensongkick;

import com.google.gson.Gson;
import api.blizzed.opensongkick.models.Error;
import api.blizzed.opensongkick.models.Location;
import api.blizzed.opensongkick.models.ResultsPage;
import api.blizzed.opensongkick.params.SongKickParams;

public class Launcher {

    public static void main(String[] args) {
        try {
            Gson gson = new Gson();
            /*ResultsPage<Artist> artistResultsPage = gson.fromJson(Files.newBufferedReader(Paths.get("mock/artist.search.json")), new TypeToken<ResultsPage<Artist>>(){}.getType());
            System.out.println(artistResultsPage.getTotalEntries());
            artistResultsPage.getResults().forEach(artist -> System.out.println(artist.getDisplayName()));*/

            /*ResultsPage<Event> eventResultsPage = gson.fromJson(Files.newBufferedReader(Paths.get("mock/artist.upcomingEvents.json")), new TypeToken<ResultsPage<Event>>(){}.getType());
            eventResultsPage.getResults().forEach(event -> System.out.println(event.getPerformances().get(0).getArtist().getDisplayName()));*/

            /*ResultsPage<Location> locationResultsPage = gson.fromJson(Files.newBufferedReader(Paths.get("mock/location.search.json")), new TypeToken<ResultsPage<Location>>(){}.getType());
            locationResultsPage.getResults().forEach(location -> System.out.println(location.getMetroArea().getCountry().getDisplayName()));*/

            /*ResultsPage<Venue> venueResultsPage = gson.fromJson(Files.newBufferedReader(Paths.get("mock/venue.search.json")), new TypeToken<ResultsPage<Venue>>(){}.getType());
            venueResultsPage.getResults().forEach(venue -> System.out.println(venue.getMetroArea().getCountry().getDisplayName()));*/

            /*ResultsPage<Event> eventResultsPage = gson.fromJson(Files.newBufferedReader(Paths.get("mock/event.search.json")), new TypeToken<ResultsPage<Event>>(){}.getType());
            eventResultsPage.getResults().forEach(event -> System.out.println(event.getPerformances().get(0).getArtist().getIdentifiers().get(0).getMbid()));*/

            OpenSongKickContext.initialize("klmTXugRXdbjPJ7z");
            SongKickApi.eventSearch()
                    .byLocation(
                            SongKickParams.ARTIST_NAME.of("Cradle Of Filth")
                    )
                    .execute()
                    .getResults()
                    .forEach(e -> System.out.println(e.getVenue()));
            SongKickApi.locationSearch().byQuery("Russia").execute(new ApiCaller.Listener<ResultsPage<Location>>() {
                @Override
                public void onComplete(ResultsPage<Location> result, ApiCaller<ResultsPage<Location>> apiCaller) {

                }

                @Override
                public void onError(Error error, ApiCaller<ResultsPage<Location>> apiCaller) {

                }

                @Override
                public void onFailure(ApiCallException e, ApiCaller<ResultsPage<Location>> apiCaller) {

                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
