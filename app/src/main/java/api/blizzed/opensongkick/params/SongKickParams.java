/*
 * Copyright (c) 2017 BlizzedRu (Ivan Vlasov)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package api.blizzed.opensongkick.params;

import api.blizzed.opensongkick.models.Event;

/**
 * Common parameters for SongKick API methods
 *
 * @author BlizzedRu
 */
public final class SongKickParams {

    private SongKickParams() {
    }

    public static final LocationParam<Integer> LOCATION_SK = new LocationParam<>(LocationParam.Type.SK);
    public static final LocationParam<Double> LOCATION_GEO = new LocationParam<>(LocationParam.Type.GEO);
    public static final LocationParam<String> LOCATION_IP = new LocationParam<>(LocationParam.Type.IP);
    public static final ClientIpLocation LOCATION_CLIENT_IP = new ClientIpLocation();

    public static final Param<Integer> PAGE = new Param<>("page");
    public static final Param<Integer> PER_PAGE = new Param<>("per_page");

    public static final Param<String> QUERY = new Param<>("query");

    public static final OrderParam ORDER = new OrderParam();

    public static final TimestampParam CREATED_AFTER = new TimestampParam("created_after");

    public static final DateParam MIN_DATE = new DateParam("min_date");
    public static final DateParam MAX_DATE = new DateParam("max_date");

    public static final Param<String> ARTIST_NAME = new Param<>("artist_name");

    public static final Param<Event.Type> TYPE = new Param<>("type");


}
