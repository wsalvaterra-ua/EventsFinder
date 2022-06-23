package api.blizzed.opensongkick;

import api.blizzed.opensongkick.methods.EventDetailsCaller;
import api.blizzed.opensongkick.methods.SimilarArtistsCaller;
import api.blizzed.opensongkick.methods.VenueDetailsCaller;
import api.blizzed.opensongkick.methods.calendar.*;
import api.blizzed.opensongkick.methods.gigography.ArtistGigography;
import api.blizzed.opensongkick.methods.gigography.ArtistGigographyCaller;
import api.blizzed.opensongkick.methods.gigography.UserGigography;
import api.blizzed.opensongkick.methods.gigography.UserGigographyCaller;
import api.blizzed.opensongkick.methods.search.*;
import api.blizzed.opensongkick.models.*;

public final class SongKickApi {

    private static ArtistSearchCaller artistSearch;
    private static EventSearchCaller eventSearchCaller;
    private static LocationSearchCaller locationSearchCaller;
    private static VenueSearchCaller venueSearchCaller;

    private static ArtistCalendarCaller artistCalendarCaller;
    private static VenueCalendarCaller venueCalendarCaller;
    private static MetroAreaCalendarCaller metroAreaCalendarCaller;

    private static ArtistGigographyCaller artistGigographyCaller;
    private static UserGigographyCaller userGigographyCaller;

    private static EventDetailsCaller eventDetailsCaller;
    private static VenueDetailsCaller venueDetailsCaller;
    private static SimilarArtistsCaller similarArtistsCaller;


    private SongKickApi() {
    }

    public static ArtistSearch artistSearch() {
        if (artistSearch == null) artistSearch = OpenSongKickContext.getInstance().createService(ArtistSearchCaller.class);
        return new ArtistSearch(artistSearch);
    }

    public static EventSearch eventSearch() {
        if (eventSearchCaller == null) eventSearchCaller = OpenSongKickContext.getInstance().createService(EventSearchCaller.class);
        return new EventSearch(eventSearchCaller);
    }

    public static LocationSearch locationSearch() {
        if (locationSearchCaller == null) locationSearchCaller = OpenSongKickContext.getInstance().createService(LocationSearchCaller.class);
        return new LocationSearch(locationSearchCaller);
    }

    public static VenueSearch venueSearch() {
        if (venueSearchCaller == null) venueSearchCaller = OpenSongKickContext.getInstance().createService(VenueSearchCaller.class);
        return new VenueSearch(venueSearchCaller);
    }


    public static ArtistCalendar artistCalendar() {
        if (artistCalendarCaller == null) artistCalendarCaller = OpenSongKickContext.getInstance().createService(ArtistCalendarCaller.class);
        return new ArtistCalendar(artistCalendarCaller);
    }

    public static VenueCalendar venueCalendar() {
        if (venueCalendarCaller == null) venueCalendarCaller = OpenSongKickContext.getInstance().createService(VenueCalendarCaller.class);
        return new VenueCalendar(venueCalendarCaller);
    }

    public static MetroAreaCalendar metroAreaCalendar() {
        if (metroAreaCalendarCaller == null) metroAreaCalendarCaller = OpenSongKickContext.getInstance().createService(MetroAreaCalendarCaller.class);
        return new MetroAreaCalendar(metroAreaCalendarCaller);
    }


    public static ArtistGigography artistGigography() {
        if (artistGigographyCaller == null) artistGigographyCaller = OpenSongKickContext.getInstance().createService(ArtistGigographyCaller.class);
        return new ArtistGigography(artistGigographyCaller);
    }

    public static UserGigography userGigography() {
        if (userGigographyCaller == null) userGigographyCaller = OpenSongKickContext.getInstance().createService(UserGigographyCaller.class);
        return new UserGigography(userGigographyCaller);
    }


    public static ApiCaller<Result<Event>> eventDetails(String eventId) {
        if (eventDetailsCaller == null) eventDetailsCaller = OpenSongKickContext.getInstance().createService(EventDetailsCaller.class);
        return new ApiCaller<>(eventDetailsCaller.byId(eventId));
    }

    public static ApiCaller<Result<Venue>> venueDetails(String venueId) {
        if (venueDetailsCaller == null) venueDetailsCaller = OpenSongKickContext.getInstance().createService(VenueDetailsCaller.class);
        return new ApiCaller<>(venueDetailsCaller.byId(venueId));
    }

    public static ApiCaller<ResultsPage<Artist>> similarArtists(String artistId) {
        if (similarArtistsCaller == null) similarArtistsCaller = OpenSongKickContext.getInstance().createService(SimilarArtistsCaller.class);
        return new ApiCaller<>(similarArtistsCaller.byId(artistId));
    }

}
