import java.util.Scanner;

public class Main {
	static String key;

	/*
	 * Main Method to display options available to the user, and allow the user to
	 * run operations on the database.
	 */
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		int roleChoice = -1;
		while (roleChoice != 0) {
			String opChoice = "";
			roleChoice = displayLoginAsMenu(scanner);
			switch (roleChoice) {
				case 1:
					while (!opChoice.equals("0")) {
						opChoice = displayManagmentMenu(scanner);
						switch (opChoice) {
							case "A1":
								System.out.print("Enter Artist Id:");
								String creatorsid = scanner.nextLine();
								System.out.print("Enter Artist First Name:");
								String cf_name = scanner.nextLine();
								System.out.print("Enter Artist last Name:");
								String cl_name = scanner.nextLine();
								System.out.print("Enter label name if the artist is associated with some label:");
								String labelname = scanner.nextLine();
								System.out.print("Enter artist status (active/retired):");
								String a_status = scanner.nextLine();
								System.out.print("Enter type (band,composer,musician):");
								String type = scanner.nextLine();
								System.out.print("Enter Primary genre artist has:");
								String primary_genre = scanner.nextLine();

								System.out.print("Enter the country where the artist belong");
								String a_country = scanner.nextLine();
								System.out.print("Enter monthly listeners of artist has:");
								int monthly_listeners = scanner.nextInt();

								artistapi.insertArtist(creatorsid, cf_name, cl_name, labelname, a_status, type,
										primary_genre, monthly_listeners, a_country);
								System.out.print("Press Enter key to continue...");
								key = scanner.nextLine();
								break;

							case "A2":
								int ch = -1;
								while (ch != 0) {
									System.out.println("1. Update artist first name");
									System.out.println("2. Update artist last name");
									System.out.println("3. Update labelname for the artist");
									System.out.println("4. Update artist status (active/retired)");
									System.out.println("5. Update artist type");
									System.out.println("6. Update primary genre of artist");
									System.out.println("7. Update the country for the artist");
									System.out.println("8. Update the monthly listeners for the artist");
									System.out.println("0. Go Back");
									System.out.print("Enter your choice:");
									ch = Integer.parseInt(scanner.nextLine());
									String creators_id;
									switch (ch) {
										case 1:
											System.out.print("Enter Artist Id:");
											creators_id = scanner.nextLine();
											System.out.print("Enter Artist first name:");
											cf_name = scanner.nextLine();
											artistapi.updateArtistFirstName(creators_id, cf_name);
											System.out.print("Press Enter key to continue...");
											key = scanner.nextLine();
											break;
										case 2:
											System.out.print("Enter Artist Id:");
											creators_id = scanner.nextLine();
											System.out.print("Enter Artist last name:");
											cl_name = scanner.nextLine();
											artistapi.updateArtistLastName(creators_id, cl_name);
											System.out.print("Press Enter key to continue...");
											key = scanner.nextLine();
											break;

										case 3:
											System.out.print("Enter Artist Id:");
											creators_id = scanner.nextLine();
											System.out.print("Enter labelname for the artist :");
											labelname = scanner.nextLine();
											artistapi.assignArtistLabelName(creators_id, labelname);
											System.out.print("Press Enter key to continue...");
											key = scanner.nextLine();
											break;

										case 4:
											System.out.print("Enter Artist Id:");
											creators_id = scanner.nextLine();
											System.out.print("Enter status of the artist :");
											a_status = scanner.nextLine();
											artistapi.updateArtistStatus(creators_id, a_status);
											System.out.print("Press Enter key to continue...");
											key = scanner.nextLine();
											break;

										case 5:
											System.out.print("Enter Artist Id:");
											creators_id = scanner.nextLine();
											System.out.print("Enter the type for the artist :");
											type = scanner.nextLine();
											artistapi.updateArtistType(creators_id, type);
											System.out.print("Press Enter key to continue...");
											key = scanner.nextLine();
											break;

										case 6:
											System.out.print("Enter Artist Id:");
											creators_id = scanner.nextLine();
											System.out.print("Enter the primary genre for the artist :");
											primary_genre = scanner.nextLine();
											artistapi.updateArtistPrimaryGenre(creators_id, primary_genre);
											System.out.print("Press Enter key to continue...");
											key = scanner.nextLine();
											break;

										case 7:
											System.out.print("Enter Artist Id:");
											creators_id = scanner.nextLine();
											System.out.print("Enter the country for the artist :");
											a_country = scanner.nextLine();
											artistapi.updateArtistCountry(creators_id, a_country);
											System.out.print("Press Enter key to continue...");
											key = scanner.nextLine();
											break;

										case 8:
											System.out.print(
													"Enter Artist Id for which the monthly listeners needs to be updated:");
											creators_id = scanner.nextLine();
											System.out.print("Enter the monthly listeners for the artist :");
											monthly_listeners = scanner.nextInt();
											artistapi.updateArtistMonthlyListeners(creators_id, monthly_listeners);
											System.out.print("Press Enter key to continue...");
											key = scanner.nextLine();
											break;
										case 0:
											System.out.print("Press Enter key to continue...");
											key = scanner.nextLine();
											break;

										default:
											System.out.print("Press Enter key to continue...");
											key = scanner.nextLine();
											break;
									}

								}
								System.out.print("Press Enter key to continue...");
								key = scanner.nextLine();
								break;

							case "A3":
								System.out.print("Enter Creators Id:");
								String creators_id = scanner.nextLine();
								artistapi.deleteArtist(creators_id);
								System.out.print("Press Enter key to continue...");
								key = scanner.nextLine();
								break;

							case "PH1":
								System.out.print("Enter Podcast Host Id:");
								creatorsid = scanner.nextLine();
								System.out.print("Enter Podcast Host First Name:");
								cf_name = scanner.nextLine();
								System.out.print("Enter Podcast Host last Name:");
								cl_name = scanner.nextLine();
								System.out.print("Enter Podcast Host email:");
								String email = scanner.nextLine();
								System.out.print("Enter Podcast Host city");
								String city = scanner.nextLine();
								System.out.print("Enter Podcast Host phone");
								int phone = Integer.parseInt(scanner.nextLine());
								podcasthostapi.insertPodcastHost(creatorsid, cf_name, cl_name, email, phone, city);
								System.out.print("Press Enter key to continue...");
								key = scanner.nextLine();
								break;

							case "PH2":
								ch = -1;
								while (ch != 0) {
									System.out.println("1. Update podcast host first name");
									System.out.println("2. Update podcast host last name");
									System.out.println("3. Update email for podcast host");
									System.out.println("4. Update city for podcast host");
									System.out.println("5. Update phone for podcast host");
									System.out.println("0. Go Back");
									System.out.print("Enter your choice:");
									ch = Integer.parseInt(scanner.nextLine());
									// String creators_id;
									switch (ch) {
										case 1:
											System.out.print("Enter podcast host Id:");
											creators_id = scanner.nextLine();
											System.out.print("Enter podcast host first name:");
											cf_name = scanner.nextLine();
											podcasthostapi.updatePodcastHostFirstName(creators_id, cf_name);
											System.out.print("Press Enter key to continue...");
											key = scanner.nextLine();
											break;

										case 2:
											System.out.print("Enter podcast host Id:");
											creators_id = scanner.nextLine();
											System.out.print("Enter podcast host last name:");
											cl_name = scanner.nextLine();
											podcasthostapi.updatePodcastHostLastName(creators_id, cl_name);
											System.out.print("Press Enter key to continue...");
											key = scanner.nextLine();
											break;

										case 3:
											System.out.print("Enter podcast host Id:");
											creators_id = scanner.nextLine();
											System.out.print("Enter podcast host email:");
											email = scanner.nextLine();
											podcasthostapi.updatePodcastHostEmail(creators_id, email);
											System.out.print("Press Enter key to continue...");
											key = scanner.nextLine();
											break;

										case 4:
											System.out.print("Enter podcast host Id:");
											creators_id = scanner.nextLine();
											System.out.print("Enter podcast host city:");
											city = scanner.nextLine();
											podcasthostapi.updatePodcastHostCity(creators_id, city);
											System.out.print("Press Enter key to continue...");
											key = scanner.nextLine();
											break;

										case 5:
											System.out.print("Enter podcast host Id:");
											creators_id = scanner.nextLine();
											System.out.print("Enter podcast host phone:");
											phone = Integer.parseInt(scanner.nextLine());
											podcasthostapi.updatePodcastHostPhone(creators_id, phone);
											System.out.print("Press Enter key to continue...");
											key = scanner.nextLine();
											break;

										case 0:
											System.out.print("Press Enter key to continue...");
											key = scanner.nextLine();
											break;

										default:
											System.out.print("Press Enter key to continue...");
											key = scanner.nextLine();
											break;
									}

								}
								System.out.print("Press Enter key to continue...");
								key = scanner.nextLine();
								break;

							case "PH3":
								System.out.print("Enter Podcast Host Id:");
								creators_id = scanner.nextLine();
								podcasthostapi.deletePodcastHost(creators_id);
								System.out.print("Press Enter key to continue...");
								key = scanner.nextLine();
								break;

							case "S1":
								String mediaid;
								System.out.print("Enter Song Id:");
								mediaid = scanner.nextLine();
								System.out.print("Enter Song Name:");
								String media_name = scanner.nextLine();
								System.out.print("Enter Song genre");
								String genre = scanner.nextLine();
								System.out.print("Enter Song language:");
								String language = scanner.nextLine();
								System.out.print("Enter Song country");
								String m_country = scanner.nextLine();
								System.out.print("Enter Song duration");
								int duration = Integer.parseInt(scanner.nextLine());
								System.out.print("Enter Song release date");
								String s_release_date = scanner.nextLine();
								System.out.print("Enter Song royalty_rate");
								int royalty_rate = Integer.parseInt(scanner.nextLine());
								System.out.print("Enter Song royalty_paid");
								int royalty_paid = Integer.parseInt(scanner.nextLine());
								System.out.print("Enter Song Album ID");
								String albumid = scanner.nextLine();
								System.out.print("Enter Song Track number");
								int track_no = Integer.parseInt(scanner.nextLine());
								songapi.insertSong(mediaid, media_name, genre, language, m_country, duration,
										s_release_date, royalty_rate, royalty_paid, albumid, track_no);

								System.out.print("Enter Lead Artist ID");
								String art = scanner.nextLine();
								songapi.insertArtist(mediaid,art);

								System.out.print("Press Enter key to continue...");
								key = scanner.nextLine();
								break;

							case "S2":
								ch = -1;
								while (ch != 0) {
									System.out.println("1. Update Song name");
									System.out.println("2. Update Song genre");
									System.out.println("3. Update Song language");
									System.out.println("4. Update Song country");
									System.out.println("5. Update Song duration");
									System.out.println("6. Update Song release date");
									System.out.println("7. Update Song royalty rate");
									System.out.println("8. Update Song royalty paid");
									System.out.println("9. Update Song Album ID");
									System.out.println("10. Update Song Track number");
									System.out.println("0. Go Back");
									System.out.print("Enter your choice:");
									ch = Integer.parseInt(scanner.nextLine());
									switch (ch) {
										case 1:
											System.out.print("Enter Song Id:");
											mediaid = scanner.nextLine();
											System.out.print("Enter Song name:");
											media_name = scanner.nextLine();
											songapi.updateMediaName(mediaid, media_name);
											System.out.print("Press Enter key to continue...");
											key = scanner.nextLine();
											break;

										case 2:
											System.out.print("Enter Song Id:");
											mediaid = scanner.nextLine();
											System.out.print("Enter Song genre:");
											genre = scanner.nextLine();
											songapi.updateMediaGenre(mediaid, genre);
											System.out.print("Press Enter key to continue...");
											key = scanner.nextLine();
											break;
										case 3:
											System.out.print("Enter Song Id:");
											mediaid = scanner.nextLine();
											System.out.print("Enter Song language:");
											language = scanner.nextLine();
											songapi.updateMediaLanguage(mediaid, language);
											System.out.print("Press Enter key to continue...");
											key = scanner.nextLine();
											break;
										case 4:
											System.out.print("Enter Song Id:");
											mediaid = scanner.nextLine();
											System.out.print("Enter Song country:");
											m_country = scanner.nextLine();
											songapi.updateMediaCountry(mediaid, m_country);
											System.out.print("Press Enter key to continue...");
											key = scanner.nextLine();
											break;

										case 5:
											System.out.print("Enter Song Id:");
											mediaid = scanner.nextLine();
											System.out.print("Enter Song duration:");
											duration = Integer.parseInt(scanner.nextLine());
											songapi.updateMediaDuration(mediaid, duration);
											System.out.print("Press Enter key to continue...");
											key = scanner.nextLine();
											break;
										case 6:
											System.out.print("Enter Song Id:");
											mediaid = scanner.nextLine();
											System.out.print("Enter Song release date:");
											s_release_date = scanner.nextLine();
											songapi.updateMediaDate(mediaid, s_release_date);
											System.out.print("Press Enter key to continue...");
											key = scanner.nextLine();
											break;
										case 7:
											System.out.print("Enter Song Id:");
											mediaid = scanner.nextLine();
											System.out.print("Enter Song royalty rate:");
											royalty_rate = Integer.parseInt(scanner.nextLine());
											songapi.updateMediaRate(mediaid, royalty_rate);
											System.out.print("Press Enter key to continue...");
											key = scanner.nextLine();
											break;
										case 8:
											System.out.print("Enter Song Id:");
											mediaid = scanner.nextLine();
											System.out.print("Enter Song royalty_paid:");
											royalty_paid = Integer.parseInt(scanner.nextLine());
											songapi.updateMediaPaid(mediaid, royalty_paid);
											System.out.print("Press Enter key to continue...");
											key = scanner.nextLine();
											break;
										case 9:
											System.out.print("Enter Song Id:");
											mediaid = scanner.nextLine();
											System.out.print("Enter Song ALbum ID:");
											albumid = scanner.nextLine();
											songapi.assignMediaAlbum(mediaid, albumid);
											System.out.print("Press Enter key to continue...");
											key = scanner.nextLine();
											break;
										case 10:
											System.out.print("Enter Song Id:");
											mediaid = scanner.nextLine();
											System.out.print("Enter Song Track number:");
											track_no = Integer.parseInt(scanner.nextLine());
											songapi.updateMediaTrack(mediaid, track_no);
											System.out.print("Press Enter key to continue...");
											key = scanner.nextLine();
											break;

										case 0:
											System.out.print("Press Enter key to continue...");
											key = scanner.nextLine();
											break;

										default:
											System.out.print("Press Enter key to continue...");
											key = scanner.nextLine();
											break;

									}
								}
								System.out.print("Press Enter key to continue...");
								key = scanner.nextLine();
								break;

							case "S3":
								System.out.print("Enter Song Id:");
								mediaid = scanner.nextLine();
								songapi.deleteSong(mediaid);
								System.out.print("Press Enter key to continue...");
								key = scanner.nextLine();
								break;

							case "P1":
								System.out.print("Enter Podcast Id:");
								mediaid = scanner.nextLine();
								System.out.print("Enter Podcast Name:");
								media_name = scanner.nextLine();
								System.out.print("Enter Podcast genre");
								genre = scanner.nextLine();
								System.out.print("Enter Podcast language:");
								language = scanner.nextLine();
								System.out.print("Enter Podcast country");
								m_country = scanner.nextLine();
								System.out.print("Enter Podcast Episode count");
								int episode_count = Integer.parseInt(scanner.nextLine());
								System.out.print("Enter Podcast Host ID ");
								String hostid = scanner.nextLine();

								podcastapi.insertPodcast(mediaid, media_name, genre, language, m_country, episode_count,
										hostid);
								// System.out.print("Enter Podcast Id:");
								// mediaid = scanner.nextLine();
								System.out.print("Enter timestamp for the rating and total subscribers (YYYY-MM-DD)");
								String pd_date = scanner.nextLine();
								System.out.print("Enter Podcast Rating :");
								Float rating = Float.parseFloat(scanner.nextLine());
								System.out.print("Enter Podcast Record Total Subscribers");
								int total_subscribers = Integer.parseInt(scanner.nextLine());

								podcastrecordapi.insertPodcastRecord(mediaid, pd_date, rating,total_subscribers);
								// podcastrecordapi.insertPodcastRecordsubscribers(mediaid, pd_date, total_subscribers);
								System.out.print("Press Enter key to continue...");
								key = scanner.nextLine();
								break;

							case "P2":
								ch = -1;
								while (ch != 0) {
									System.out.println("1. Update Podcast name");
									System.out.println("2. Update Podcast genre");
									System.out.println("3. Update Podcast language");
									System.out.println("4. Update Podcast country");
									System.out.println("5. Update Podcast Episode Count");
									System.out.println("6. Update Podcast Host ID");
									System.out.println("7. Update the rating and total subscribers for the podcast");
									System.out.println("0. Go Back");
									System.out.print("Enter your choice:");
									ch = Integer.parseInt(scanner.nextLine());
									switch (ch) {
										case 1:
											System.out.print("Enter Podcast Id:");
											mediaid = scanner.nextLine();
											System.out.print("Enter Podcast name:");
											media_name = scanner.nextLine();
											songapi.updateMediaName(mediaid, media_name);
											System.out.print("Press Enter key to continue...");
											key = scanner.nextLine();
											break;

										case 2:
											System.out.print("Enter Podcast Id:");
											mediaid = scanner.nextLine();
											System.out.print("Enter Podcast genre:");
											genre = scanner.nextLine();
											songapi.updateMediaGenre(mediaid, genre);
											System.out.print("Press Enter key to continue...");
											key = scanner.nextLine();
											break;
										case 3:
											System.out.print("Enter Podcast Id:");
											mediaid = scanner.nextLine();
											System.out.print("Enter Podcast language:");
											language = scanner.nextLine();
											songapi.updateMediaLanguage(mediaid, language);
											System.out.print("Press Enter key to continue...");
											key = scanner.nextLine();
											break;
										case 4:
											System.out.print("Enter Podcast Id:");
											mediaid = scanner.nextLine();
											System.out.print("Enter Podcast country:");
											m_country = scanner.nextLine();
											songapi.updateMediaCountry(mediaid, m_country);
											System.out.print("Press Enter key to continue...");
											key = scanner.nextLine();
											break;

										case 5:
											System.out.print("Enter Podcast Id:");
											mediaid = scanner.nextLine();
											System.out.print("Enter Podcast Episode count:");
											episode_count = Integer.parseInt(scanner.nextLine());
											podcastapi.updatePodcastEpCount(mediaid, episode_count);
											System.out.print("Press Enter key to continue...");
											key = scanner.nextLine();
											break;
										case 6:
											System.out.print("Enter Podcast Id:");
											mediaid = scanner.nextLine();
											System.out.print("Enter Podcast Host ID:");
											hostid = scanner.nextLine();
											podcastapi.updatePodcastHostID(mediaid, hostid);
											System.out.print("Press Enter key to continue...");
											key = scanner.nextLine();
											break;

										case 7:
	
											System.out.print("Enter Podcast Id:");
											mediaid = scanner.nextLine();
											System.out.print("Enter Podcast Record Date ");
											pd_date = scanner.nextLine();
											System.out.print("Enter Podcast Record Total Subscribers");
											total_subscribers = Integer.parseInt(scanner.nextLine());
											System.out.print("Enter Podcast Rating :");
											rating = Float.parseFloat(scanner.nextLine());
											podcastrecordapi.updatePodcastRating(mediaid, pd_date, rating, total_subscribers);
											System.out.print("Press Enter key to continue...");
											key = scanner.nextLine();
											break;

										case 0:
											System.out.print("Press Enter key to continue...");
											key = scanner.nextLine();
											break;

										default:
											System.out.print("Press Enter key to continue...");
											key = scanner.nextLine();
											break;

									}
								}
								System.out.print("Press Enter key to continue...");
								key = scanner.nextLine();
								break;

							case "P3":
								System.out.print("Enter Podcast Id:");
								mediaid = scanner.nextLine();
								podcastapi.deletePodcast(mediaid);
								System.out.print("Press Enter key to continue...");
								key = scanner.nextLine();
								break;

							case "PE1":
								System.out.print("Enter Podcast Id for which the episode needs to be entered:");
								mediaid = scanner.nextLine();
								System.out.print("Enter Podcast Episode number:");
								int episodeno = Integer.parseInt(scanner.nextLine());
								System.out.print("Enter Podcast episode title:");
								String title = scanner.nextLine();
								System.out.print("Enter Podcast episode duration:");
								float p_duration = Float.parseFloat(scanner.nextLine());
								System.out.print("Enter podcast episode release Date (YYYY/MM/DD):");
								String p_release_date = scanner.nextLine();
								System.out.print("Enter Podcast Episode flat fee per released episode:");
								int flat_fee = Integer.parseInt(scanner.nextLine());
								System.out.print("Enter Podcast Episode ad count:");
								int ad_count = Integer.parseInt(scanner.nextLine());
								podcastEpisodeapi.insertPodcastEpisode(mediaid, episodeno, title, p_duration, p_release_date, flat_fee,
								ad_count);
								System.out.print("Press Enter key to continue...");
								key = scanner.nextLine();
								break;

							case "PE2":
								ch = -1;
								while (ch != 0) {
									System.out.println("1. Update Podcast Episode title:");
									System.out.println("2. Update Podcast Episode duration:");
									System.out.println("3. Update Podcast Episode release date:");
									System.out.println("4. Update Podcast Episode flat fee:");
									System.out.println("5. Update Podcast  Episode ad count:");
									System.out.println("0. Go Back");
									System.out.print("Enter your choice:");
									ch = Integer.parseInt(scanner.nextLine());
									switch (ch) {
										case 1:
											System.out.print("Enter Podcast Id for which the episode needs to be updated:");
											mediaid = scanner.nextLine();
											System.out.print("Enter Podcast Episode number:");
											episodeno = Integer.parseInt(scanner.nextLine());
											System.out.print("Enter Podcast Episode title:");
											title = scanner.nextLine();
											podcastEpisodeapi.updatePodcastEpisodeTitle(mediaid, episodeno, title);
											System.out.print("Press Enter key to continue...");
											key = scanner.nextLine();
											break;

										case 2:
											System.out.print("Enter Podcast Id for which the episode needs to be updated:");
											mediaid = scanner.nextLine();
											System.out.print("Enter Podcast Episode number:");
											episodeno = Integer.parseInt(scanner.nextLine());
											System.out.print("Enter Podcast Episode duration:");
											p_duration = Float.parseFloat(scanner.nextLine());
											podcastEpisodeapi.updatePodcastEpisodeDuration(mediaid, episodeno, p_duration);
											System.out.print("Press Enter key to continue...");
											key = scanner.nextLine();
											break;

										case 3:
											System.out.print("Enter Podcast Id for which the episode needs to be updated:");
											mediaid = scanner.nextLine();
											System.out.print("Enter Podcast Episode number:");
											episodeno = Integer.parseInt(scanner.nextLine());
											System.out.print("Enter Podcast episode release date:");
											p_release_date = scanner.nextLine();
											podcastEpisodeapi.updatePodcastEpisodeReleasedate(mediaid, episodeno, p_release_date);
											System.out.print("Press Enter key to continue...");
											key = scanner.nextLine();
											break;
											
										case 4:
											System.out.print("Enter Podcast Id for which the episode needs to be updated:");
											mediaid = scanner.nextLine();
											System.out.print("Enter Podcast Episode number:");
											episodeno = Integer.parseInt(scanner.nextLine());
											System.out.print("Enter Podcast Episode flat fee:");
											flat_fee = Integer.parseInt(scanner.nextLine());
											podcastEpisodeapi.updatePodcastEpisodeFlatfee(mediaid, episodeno, flat_fee);
											System.out.print("Press Enter key to continue...");
											key = scanner.nextLine();
											break;

										case 5:
											System.out.print("Enter Podcast Id for which the episode needs to be updated:");
											mediaid = scanner.nextLine();
											System.out.print("Enter Podcast Episode number:");
											episodeno = Integer.parseInt(scanner.nextLine());
											System.out.print("Enter Podcast Episode ad count:");
											ad_count = Integer.parseInt(scanner.nextLine());
											podcastEpisodeapi.updatePodcastEpisodeAdcount(mediaid, episodeno, ad_count);
											System.out.print("Press Enter key to continue...");
											key = scanner.nextLine();
											break;

										case 0:
											System.out.print("Press Enter key to continue...");
											key = scanner.nextLine();
											break;

										default:
											System.out.print("Press Enter key to continue...");
											key = scanner.nextLine();
											break;

									}
								}
								System.out.print("Press Enter key to continue...");
								key = scanner.nextLine();
								break;

							case "PE3":
								System.out.print("Enter Podcast Id for which the episode needs to be deleted:");
								mediaid = scanner.nextLine();
								System.out.print("Enter Podcast Episode number:");
								episodeno = Integer.parseInt(scanner.nextLine());
								podcastEpisodeapi.deletePodcastEpisode(mediaid,episodeno);
								System.out.print("Press Enter key to continue...");
								key = scanner.nextLine();
								break;

							case "PEL1":
								System.out.print("Enter Podcast Id:");
								mediaid = scanner.nextLine();
								System.out.print("Enter Podcast Episode number");
								episodeno = Integer.parseInt(scanner.nextLine());
								System.out.print("Enter Podcast Episode Listening Date :");
								String pel_date = scanner.nextLine();
								System.out.print("Enter Podcast Episode listening_count");
								int listening_count = Integer.parseInt(scanner.nextLine());

								podcastEpisode_listeningapi.insertPodcastEpL(mediaid, episodeno, pel_date,
										listening_count);
								System.out.print("Press Enter key to continue...");
								key = scanner.nextLine();
								break;

							case "PEL2":
								ch = -1;
								while (ch != 0) {
									System.out.println("1. Update Podcast Episode Listening Count");
									System.out.println("0. Go Back");
									System.out.print("Enter your choice:");
									ch = Integer.parseInt(scanner.nextLine());
									switch (ch) {
										case 1:
											System.out.print("Enter Podcast Id:");
											mediaid = scanner.nextLine();
											System.out.print("Enter Podcast Episode number");
											episodeno = Integer.parseInt(scanner.nextLine());
											System.out.print("Enter Podcast Episode Listening Date :");
											pel_date = scanner.nextLine();

											System.out.print("Enter Podcast Episode Listening Count:");
											listening_count = Integer.parseInt(scanner.nextLine());
											podcastEpisode_listeningapi.updatePodcastEpCount(mediaid, episodeno,
													pel_date, listening_count);
											System.out.print("Press Enter key to continue...");
											key = scanner.nextLine();
											break;

										case 0:
											System.out.print("Press Enter key to continue...");
											key = scanner.nextLine();
											break;

										default:
											System.out.print("Press Enter key to continue...");
											key = scanner.nextLine();
											break;

									}
								}
								System.out.print("Press Enter key to continue...");
								key = scanner.nextLine();
								break;

							// case "PR1":
							// 	System.out.print("Enter Podcast Id:");
							// 	mediaid = scanner.nextLine();
							// 	System.out.print("Enter Podcast Record Date ");
							// 	String pd_date = scanner.nextLine();
							// 	System.out.print("Enter Podcast Rating :");
							// 	Float rating = Float.parseFloat(scanner.nextLine());
							// 	System.out.print("Enter Podcast Record Total Subscribers");
							// 	int total_subscribers = Integer.parseInt(scanner.nextLine());

							// 	podcastrecordapi.insertPodcastRecord(mediaid, pd_date, rating, total_subscribers);
							// 	System.out.print("Press Enter key to continue...");
							// 	key = scanner.nextLine();
							// 	break;

							// case "PR2":
							// 	ch = -1;
							// 	while (ch != 0) {
							// 		System.out.println("1. Update Podcast Record Rating");
							// 		System.out.println("2. Update Podcast Record Total Subscribers");
							// 		System.out.println("0. Go Back");
							// 		System.out.print("Enter your choice:");
							// 		ch = Integer.parseInt(scanner.nextLine());
							// 		switch (ch) {
							// 			case 1:
							// 				System.out.print("Enter Podcast Id:");
							// 				mediaid = scanner.nextLine();
							// 				System.out.print("Enter Podcast Record Date ");
							// 				pd_date = scanner.nextLine();
							// 				System.out.print("Enter Podcast Rating :");
							// 				rating = Float.parseFloat(scanner.nextLine());
							// 				podcastrecordapi.updatePodcastRating(mediaid, pd_date, rating);
							// 				System.out.print("Press Enter key to continue...");
							// 				key = scanner.nextLine();
							// 				break;

							// 			case 2:
							// 				System.out.print("Enter Podcast Id:");
							// 				mediaid = scanner.nextLine();
							// 				System.out.print("Enter Podcast Record Date ");
							// 				pd_date = scanner.nextLine();
							// 				System.out.print("Enter Podcast Record Total Subscribers");
							// 				total_subscribers = Integer.parseInt(scanner.nextLine());

							// 				podcastrecordapi.updatePodcastSub(mediaid, pd_date, total_subscribers);
							// 				System.out.print("Press Enter key to continue...");
							// 				key = scanner.nextLine();
							// 				break;

							// 			case 0:
							// 				System.out.print("Press Enter key to continue...");
							// 				key = scanner.nextLine();
							// 				break;

							// 			default:
							// 				System.out.print("Press Enter key to continue...");
							// 				key = scanner.nextLine();
							// 				break;

							// 		}
							// 	}
							// 	System.out.print("Press Enter key to continue...");
							// 	key = scanner.nextLine();
							// 	break;

							case "AM1":
								System.out.print("Enter Album ID:");
								String albumid2 = scanner.nextLine();
								System.out.print("Enter Album Name:");
								String albumName = scanner.nextLine();
								System.out.print("Enter Album's Release Year:");
								int aReleaseYear = Integer.parseInt(scanner.nextLine());
								System.out.print("Enter Album's edition (special/limited/collector's edition):");
								String edition = scanner.nextLine();
								System.out.print("Enter Artist's ID associated to the respective album:");
								String artistid = scanner.nextLine();

								albumapi.insertAlbum(albumid2, albumName, aReleaseYear, edition, artistid);

								System.out.print("Press Enter key to continue...");
								key = scanner.nextLine();
								break;

							case "AM2":
								ch = -1;
								while (ch != 0) {
									System.out.println("1. Update Album's name");
									System.out.println("2. Update Album's Release Year");
									System.out.println("3. Update Album's edition");
									System.out.println("4. Update Artist's ID associated to the respective album");
									System.out.println("0. Go Back");
									System.out.print("Enter your choice:");
									ch = Integer.parseInt(scanner.nextLine());
									switch (ch) {
										case 1:
											System.out.print("Enter Album ID: ");
											albumid2 = scanner.nextLine();
											System.out.print("Enter Album's name: ");
											albumName = scanner.nextLine();
											albumapi.updateAlbumName(albumid2, albumName);
											System.out.print("Press Enter key to continue...");
											key = scanner.nextLine();
											break;

										case 2:
											System.out.print("Enter Album ID: ");
											albumid2 = scanner.nextLine();
											System.out.print("Enter Album's Release Year: ");
											aReleaseYear = Integer.parseInt(scanner.nextLine());
											albumapi.updateAlbumReleaseYear(albumid2, aReleaseYear);
											System.out.print("Press Enter key to continue...");
											key = scanner.nextLine();
											break;

										case 3:
											System.out.print("Enter Album ID: ");
											albumid2 = scanner.nextLine();
											System.out.print("Enter Album's edition: ");
											edition = scanner.nextLine();
											albumapi.updateAlbumEdition(albumid2, edition);
											System.out.print("Press Enter key to continue...");
											key = scanner.nextLine();
											break;

										case 4:
											System.out.print("Enter Album ID: ");
											albumid2 = scanner.nextLine();
											System.out.print("Enter Artist's ID associated to the respective album: ");
											artistid = scanner.nextLine();
											albumapi.assignAlbumArtistID(albumid2, artistid);
											System.out.print("Press Enter key to continue...");
											key = scanner.nextLine();
											break;

										case 0:
											System.out.print("Press Enter key to continue...");
											key = scanner.nextLine();
											break;

										default:
											System.out.print("Press Enter key to continue...");
											key = scanner.nextLine();
											break;
									}

								}
								System.out.print("Press Enter key to continue...");
								key = scanner.nextLine();
								break;

							case "AM3":
								System.out.print("Enter Album ID: ");
								albumid2 = scanner.nextLine();
								albumapi.deleteAlbum(albumid2);
								System.out.print("Press Enter key to continue...");
								key = scanner.nextLine();
								break;

							case "U1":
								System.out.print("Enter User's Phone Number:");
								int uphone = Integer.parseInt(scanner.nextLine());
								System.out.print("Enter User's Join Date:");
								String joinDate = scanner.nextLine();
								System.out.print("Enter User's End Date:");
								String endDate = scanner.nextLine();
								System.out.print("Enter User's First name:");
								String ufName = scanner.nextLine();
								System.out.print("Enter User's Last name:");
								String ulName = scanner.nextLine();
								System.out.print("Enter User's E-Mail ID:");
								String uEmail = scanner.nextLine();
								System.out.print("Enter User's status (Active/Inactive):");
								String uStatus = scanner.nextLine();

								System.out.print("Enter User's Subscription Fee:");
								int subFee = Integer.parseInt(scanner.nextLine());

								userapi.insertUser(uphone, joinDate, endDate, ufName, ulName, uEmail, uStatus, subFee);

								System.out.print("Press Enter key to continue...");
								key = scanner.nextLine();
								break;

							case "U2":
								ch = -1;
								while (ch != 0) {
									System.out.println("1. Update user's first name");
									System.out.println("2. Update user's last name");
									System.out.println("3. Update user's join date");
									System.out.println("4. Update user's end date");
									System.out.println("5. Update user's email id");
									System.out.println("6. Update user's status (active/inactive)");
									System.out.println("7. Update user's subscription fee");
									System.out.println("0. Go Back");
									System.out.print("Enter your choice:");
									ch = Integer.parseInt(scanner.nextLine());
									switch (ch) {
										case 1:
											System.out.print("Enter User's Phone Number: ");
											uphone = Integer.parseInt(scanner.nextLine());
											System.out.print("Enter User's first name: ");
											ufName = scanner.nextLine();
											userapi.updateUserFirstName(uphone, ufName);
											System.out.print("Press Enter key to continue...");
											key = scanner.nextLine();
											break;
										case 2:
											System.out.print("Enter User's Phone Number: ");
											uphone = Integer.parseInt(scanner.nextLine());
											System.out.print("Enter User's last name: ");
											ulName = scanner.nextLine();
											userapi.updateUserLastName(uphone, ulName);
											System.out.print("Press Enter key to continue...");
											key = scanner.nextLine();
											break;

										case 3:
											System.out.print("Enter User's Phone Number: ");
											uphone = Integer.parseInt(scanner.nextLine());
											System.out.print("Enter User's join date: ");
											joinDate = scanner.nextLine();
											userapi.updateUserJoinDate(uphone, joinDate);
											System.out.print("Press Enter key to continue...");
											key = scanner.nextLine();
											break;

										case 4:
											System.out.print("Enter User's Phone Number: ");
											uphone = Integer.parseInt(scanner.nextLine());
											System.out.print("Enter User's end date: ");
											endDate = scanner.nextLine();
											userapi.updateUserEndDate(uphone, endDate);
											System.out.print("Press Enter key to continue...");
											key = scanner.nextLine();
											break;

										case 5:
											System.out.print("Enter User's Phone Number: ");
											uphone = Integer.parseInt(scanner.nextLine());
											System.out.print("Enter User's email id: ");
											uEmail = scanner.nextLine();
											userapi.updateUserEmail(uphone, uEmail);
											System.out.print("Press Enter key to continue...");
											key = scanner.nextLine();
											break;

										case 6:
											System.out.print("Enter User's Phone Number: ");
											uphone = Integer.parseInt(scanner.nextLine());
											System.out.print("Enter User's status (active/inactive): ");
											uStatus = scanner.nextLine();
											userapi.updateUserStatus(uphone, uStatus);
											System.out.print("Press Enter key to continue...");
											key = scanner.nextLine();
											break;

										case 7:
											System.out.print("Enter User's Phone Number: ");
											uphone = Integer.parseInt(scanner.nextLine());
											System.out.print("Enter User's subscription fee: ");
											subFee = Integer.parseInt(scanner.nextLine());
											userapi.updateUserSubFee(uphone, subFee);
											System.out.print("Press Enter key to continue...");
											key = scanner.nextLine();
											break;

										case 0:
											System.out.print("Press Enter key to continue...");
											key = scanner.nextLine();
											break;

										default:
											System.out.print("Press Enter key to continue...");
											key = scanner.nextLine();
											break;
									}

								}
								System.out.print("Press Enter key to continue...");
								key = scanner.nextLine();
								break;

							case "U3":
								System.out.print("Enter User's Phone Number: ");
								uphone = Integer.parseInt(scanner.nextLine());
								userapi.deleteUser(uphone);
								System.out.print("Press Enter key to continue...");
								key = scanner.nextLine();
								break;

							case "RL1":
								System.out.print("Enter new Record Label name:");
								labelname = scanner.nextLine();

								recordLabelapi.insertLabelName(labelname);
								System.out.print("Press Enter key to continue...");
								key = scanner.nextLine();
								break;
								
							case "AS1":
								System.out.print("Enter Song ID :");
								mediaid = scanner.nextLine();
								System.out.print("Enter Album ID:");
								albumid = scanner.nextLine();

								songapi.assignMediaAlbum(mediaid, albumid);
								System.out.print("Press Enter key to continue...");
								key = scanner.nextLine();
								break;

							case "AS2":
								System.out.print("Enter Artist ID :");
								creators_id = scanner.nextLine();
								System.out.print("Enter Album ID:");
								albumid = scanner.nextLine();

								albumapi.assignAlbumArtistID(creators_id, albumid);
								System.out.print("Press Enter key to continue...");
								key = scanner.nextLine();
								break;

							case "AS3":
								System.out.print("Enter Artist ID :");
								creators_id = scanner.nextLine();
								System.out.print("Enter Record Label Name :");
								labelname = scanner.nextLine();

								artistapi.assignArtistLabelName(creators_id, labelname);
								System.out.print("Press Enter key to continue...");
								key = scanner.nextLine();
								break;

							case "PS1":
								System.out.print("Enter Podcast ID :");
								creatorsid = scanner.nextLine();
								System.out.print("Enter podcast host id :");
								hostid = scanner.nextLine();
								assignapi.assignhosttoepisode(creatorsid, hostid);
								System.out.print("Press Enter key to continue...");
								key = scanner.nextLine();
								break;

							case "0":
								System.out.println("Thank you for using the system!");
								break;

							default:
								System.out.println("Invalid Input...");
								break;
						}
					}
					scanner.close();
			}
		}

	}

	// /*
	// * Method to display Roles menu and accept user choice.
	// */
	static int displayLoginAsMenu(Scanner scanner) {
		System.out.println("Login As:");
		System.out.println("1. Managment");
		System.out.println("0. Exit");
		System.out.print("Enter your choice:");
		int roleChoice = Integer.parseInt(scanner.nextLine());
		return roleChoice;
	}

	static String displayManagmentMenu(Scanner scanner) {
		System.out.println("--------------Artist Information--------------");
		System.out.println("A1. Enter information for a new artist");
		System.out.println("A2. Update Information of a artist");
		System.out.println("A3. Delete Information of a artist");
		System.out.println("--------------Podcast Host Information--------------");
		System.out.println("PH1. Enter information for a new podcast host");
		System.out.println("PH2. Update Information of a podcast host");
		System.out.println("PH3. Delete information of a podcast host");
		System.out.println("--------------Song Information--------------");
		System.out.println("S1. Enter information for a new song ");
		System.out.println("S2. Update Information of a song");
		System.out.println("S3. Delete information of a song");
		System.out.println("--------------Podcast Information--------------");
		System.out.println("P1. Enter information for a new podcast ");
		System.out.println("P2. Update Information of a podcast");
		System.out.println("P3. Delete information of a podcast");
		System.out.println("--------------Podcast Episode Information--------------");
		System.out.println("PE1. Enter information for a new podcast episode ");
		System.out.println("PE2. Update Information of a podcast episode");
		System.out.println("PE3. Delete information of a podcast episode");
		System.out.println("--------------Podcast Episode Listening Information--------------");
		System.out.println("PEL1. Enter information for a new podcast episode listening ");
		System.out.println("PEL2. Update Information of a podcast episode listening");
		// System.out.println("--------------Podcast Record--------------");
		// System.out.println("PR1. Enter information for a new podcast record ");
		// System.out.println("PR2. Update Information of a podcast episode listening");
		System.out.println("--------------Album Information--------------");
		System.out.println("AM1. Enter information for a new album");
		System.out.println("AM2. Update Information of a album");
		System.out.println("AM3. Delete Information of a album");
		System.out.println("--------------User Information--------------");
		System.out.println("U1. Enter information for a new user");
		System.out.println("U2. Update Information of a user");
		System.out.println("U3. Delete Information of a user");
		System.out.println("--------------Record Label Information--------------");
		System.out.println("RL1. Enter new record label");
		System.out.println("--------------Assign--------------");
		System.out.println("AS1. Assign Song to Album");
		System.out.println("AS2. Assign Artist to Album");
		System.out.println("AS3. Assign Artist to Record Label");
		System.out.println("PS1. Assign podcast host to podcast");
		System.out.println("----------------------------------------");
		System.out.println("0. Go Back");
		System.out.println("----------------------------------------");
		System.out.print("Enter your choice:");
		String mainChoice = scanner.nextLine();
		return mainChoice;
	}

}
