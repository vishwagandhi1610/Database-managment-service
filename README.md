# Database Management Service

Design and build a database system for Wolf Media, a media streaming service. This is an audio and media streaming service, like Spotify or Apple Music. The database system will be used by the administrators and management of the streaming service

## Requirement

### Narrative
By talking to the managers, we have elicited for you the following information for the media streaming service.
1. The media streaming service features songs and podcasts by various artists, hosts, and record labels. Each song has at least one artist which can be a band, solo musician and/or composer. Each song is owned by the record label of its main artist. Podcasts have at least one host with podcast episodes that sometimes feature special guests.
2. The media streaming service generates monthly payments based on royalties collected. Royalties are generated based on a royalty rate for each song times the total play count. The royalties are paid out to the record label who keeps 30% and distributes the remainder evenly among the respective artist(s). Both active, retired and guest artists receive royalties.
3. Podcast hosts are paid a single flat fee per released episode and an additional bonus based on the total advertisements presented within the episode. Special guest(s) are volunteers and do not receive payments.
4. All payment information for artist(s), record label(s) and podcast host(s) are recorded and maintained by the media streaming service. All relevant information for the songs, artists, albums, podcasts, host(s), and episodes should be kept in the database system. Monthly listeners and total subscribers include unique active listener accounts which subscribe to the artist or podcast. Subscribers make monthly payments to the media streaming service.
5. Management collects and analyzes data on various aspects of the streamed items and associated parties including but not limited to song play count, podcast subscribers, ratings, and all payments made. Generated reports include monthly, yearly, and total payment summaries for each artist, record label and podcast host. Monthly, yearly, and total reports for podcast/song subscribers, play count, and rating (if applicable).
### Tasks and Operations
The following are the four major kinds of tasks that need to be performed using your database. Each task potentially consists of a number of operations; an "operation" is something that corresponds to a separate action. For example, Information Processing is considered to be one task, which involves separate operations such as entering and updating basic information about songs, and podcasts.
1. Information Processing\
Enter/update/delete basic information about songs, artists, podcast hosts, and podcast episodes. Assign songs and artists to albums. Assign artists to record labels. Assign podcast episodes and podcast hosts to podcasts.
2. Maintaining metadata and records\
Enter/update play count for songs. Enter/update the count of monthly listeners for artists. Enter/update the total count of subscribers and ratings for podcasts. Enter/Update the listening count for podcast episodes. Find songs and podcast episodes given artist, album, and/or podcast.
3. Maintaining payments\
Make royalty payments for a given song. Monthly royalties are generated based on a royalty rate for each song times the total play count per month. 30% of the collected royalties are paid to the record label and the remainder is distributed evenly among all participating artists. Make payment to podcast hosts. Podcast hosts are paid a single flat fee per released episode and an additional bonus based on total advertisements per podcast episode. Receive payment from subscribers.
4. Reports\
Generate all the following reports: Monthly play count per song/album/artist. Calculate total payments made out to host/artist/record labels per a given time period. Total revenue of the streaming service per month, per year. Report all songs/podcast episodes given an artist, album, and/or podcast.

### Required data points
Maintain information on at least the following entities/attributes:
* Song:
song title, artist(s), duration, genre(s), album, play count, release date, release country, language, royalty rate, collaborators (guest artist), royalty_paid (status of whether the current royalty has been paid out)
* Artist: name, collaboration(s), status (active/retired), type (Band/musician/composer), country, primary genre, monthly listeners, album(s), record label
* Record label: record label name, contracted artist(s),
* Album: album name, artist(s), song(s), track number(s), release year, Edition (special, limited, collector's edition)
* Podcast: podcast host, podcast name, language, country, episode count, genre(s), rating, sponsor(s), total subscribers
* Podcast host: first name, last name, phone, email, city, podcast(s)
* Podcast episode: episode title, duration, release date, listening count, special guest(s), advertisement count
* User: first name, last name, phone, email, registration date, status of subscription, monthly subscription fee

## Solution

This repo contains API written in java which connects to a mariadb database to perform all posible operations based on the given requirement.

### ER Diagram
![ER Diagram](https://github.com/vishwagandhi1610/Database-managment-service/blob/main/er_diagram.jpeg)

### Design Assumptions
* Record Label name is unique
* Phone is unique for every user
* Artists can be under contract with only one record label.
* Every album belongs to only one artist but artist can collaborate with other artists in the songs for
that album.
* Podcast hosts may or may not give their associated phone number and email.
* Play counts are updated every month and are checked on a monthly basis only.
* Every song belongs to at most one album.
* Song payment made to artist/ label once a month
* For every advertisement in a podcast episode, 2$ bonus is added to the associated episode and
podcast.
* There can be multiple paymentid associated with one user but every paymentid will only be
associated with exactly one user.
* Podcasts are associated with at most one host. But different podcast hosts can collaborate for
various episodes in the podcasts.
* Podcast hosts are paid as soon as the podcast episode is released.
* Collaborators are the various different artists collaborating in the song.
* Media is considered to have one main genre.

## Team Members


- Vishwa Gandhi - vgandhi
- Harshil Sanghavi - hsangha
- Mitul Patel - mpatel27
- Dhruv Patel - dpatel49
