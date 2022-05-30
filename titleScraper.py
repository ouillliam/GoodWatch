import requests
from bs4 import BeautifulSoup as bs
import json
import re
import random

response = requests.get("https://www.timeout.com/film/best-movies-of-all-time")

soup = bs(response.text, 'html.parser')

titles_html = soup.find_all(class_="_h3_cuogz_1")
titles= []
for title_html in titles_html[:-1]:
    title = title_html.getText()
    i = title.index(".")
    title = title[i+2:-7] # Trim title
    title = title.replace(" ", "_")
    titles.append(title)

#http://www.omdbapi.com/?apikey=6c7d33dd&t=The_Cabinet_of_Dr._Caligari&type=movie

gendras = []
directors = []
movies_arr = []

for i, title in enumerate(titles):
    search_title = title.replace("’", "_")
    print(search_title)
    response = requests.get(f"http://www.omdbapi.com/?apikey=6c7d33dd&t={search_title}&plot=full&type=movie")
    movie_data = response.json()
    try:
        print(movie_data["Title"])
    except:
        continue
    gendras = movie_data["Genre"].split(",")
    gendras = [gendra.replace(" ", "") for gendra in gendras if gendra != ""]

    movie = {
        "movieID" : i,
        "title" : movie_data["Title"].replace("_", " "),
        "releaseYear" : movie_data["Year"],
        "plot" : movie_data["Plot"],
        "runtime" : movie_data["Runtime"],
        "director" : movie_data["Director"],
        'gendras' : gendras
    }

    movies_arr.append(movie)


# movies_arr = [{'movieID': 0, 'title': '2001: A Space Odyssey', 'releaseYear': '1968', 'plot': "The Monoliths push humanity to reach for the stars; after their discovery in Africa generations ago, the mysterious objects lead mankind on an awesome journey to Jupiter, with the help of H.A.L. 9000: the world's greatest supercomput", 'runtime': '149 min', 'director': 'Stanley Kubrick', 'gendras': ['Adventure', 'Sci-Fi', 'Adventure', 'Sci-Fi']}, {'movieID': 1, 'title': 'The Godfather', 'releaseYear': '1972', 'plot': 'The aging patriarch of an organized crime dynasty in postwar New York City transfers control of his clandestine empire to his reluctant youngest son.', 'runtime': '175 min', 'director': 'Francis Ford Coppola', 'gendras': ['Crime', 'Drama', 'Crime', 'Drama']}, {'movieID': 2, 'title': 'Citizen Kane', 'releaseYear': '1941', 'plot': "Following the death of publishing tycoon Charles Foster Kane, reporters scramble to uncover the meaning of his final utterance: 'Rosebud.'", 'runtime': '119 min', 'director': 'Orson Welles', 'gendras': ['Drama', 'Mystery', 'Drama', 'Mystery']}, {'movieID': 3, 'title': 'Jeanne Dielman, 23, quai du commerce, 1080 Bruxelles', 'releaseYear': '1975', 'plot': 'A lonely widowed housewife does her daily chores, takes care of her apartment where she lives with her teenage son, and turns the occasional trick to make ends meet. However, something happens that changes her safe routine.', 'runtime': '202 min', 'director': 'Chantal Akerman', 'gendras': ['Drama', 'Drama']}, {'movieID': 4, 'title': 'Indiana Jones and the Raiders of the Lost Ark', 'releaseYear': '1981', 'plot': "In 1936, archaeologist and adventurer Indiana Jones is hired by the U.S. government to find the Ark of the Covenant before Adolf Hitler's Nazis can obtain its awesome powers.", 'runtime': '115 min', 'director': 'Steven Spielberg', 'gendras': ['Action', 'Adventure', 'Action', 'Adventure']}, {'movieID': 5, 'title': 'La Dolce Vita', 'releaseYear': '1960', 'plot': 'A series of stories following a week in the life of a philandering tabloid journalist living in Rome.', 'runtime': '174 min', 'director': 'Federico Fellini', 'gendras': ['Comedy', 'Drama', 'Comedy', 'Drama']}, {'movieID': 6, 'title': 'Seven Samurai', 'releaseYear': '1954', 'plot': 'A poor village under attack by bandits recruits seven unemployed samurai to help them defend themselves.', 'runtime': '207 min', 'director': 'Akira Kurosawa', 'gendras': ['Action', 'Drama', 'Action', 'Drama']}, {'movieID': 7, 'title': 'In the Mood for Love', 'releaseYear': '2000', 'plot': 'Two neighbors form a strong bond after both suspect extramarital activities of their spouses. However, they agree to keep their bond platonic so as not to commit similar wrongs.', 'runtime': '98 min', 'director': 'Kar-Wai Wong', 'gendras': ['Drama', 'Romance', 'Drama', 'Romance']}, {'movieID': 8, 'title': 'There Will Be Blood', 'releaseYear': '2007', 'plot': 'A story of family, religion, hatred, oil and madness, focusing on a turn-of-the-century prospector in the early days of the business.', 'runtime': '158 min', 'director': 'Paul Thomas Anderson', 'gendras': ['Drama', 'Drama']}, {'movieID': 9, 'title': "Singin' in the Rain", 'releaseYear': '1952', 'plot': 'A silent film star falls for a chorus girl just as he and his delusionally jealous screen partner are trying to make the difficult transition to talking pictures in 1920s Hollywood.', 'runtime': '103 min', 'director': 'Stanley Donen, Gene Kelly', 'gendras': ['Comedy', 'Musical', 'Romance', 'Comedy', 'Musical', 'Romance']}, {'movieID': 10, 'title': 'Goodfellas', 'releaseYear': '1990', 'plot': 'The story of Henry Hill and his life in the mob, covering his relationship with his wife Karen Hill and his mob partners Jimmy Conway and Tommy DeVito in the Italian-American crime syndicate.', 'runtime': '145 min', 'director': 'Martin Scorsese', 'gendras': ['Biography', 'Crime', 'Drama', 'Biography', 'Crime', 'Drama']}, {'movieID': 11, 'title': 'North by Northwest', 'releaseYear': '1959', 'plot': 'A New York City advertising executive goes on the run after being mistaken for a government agent by a group of foreign spies, and falls for a woman whose loyalties he begins to doubt.', 'runtime': '136 min', 'director': 'Alfred Hitchcock', 'gendras': ['Adventure', 'Mystery', 'Thriller', 'Adventure', 'Mystery', 'Thriller']}, {'movieID': 12, 'title': 'Mulholland Drive', 'releaseYear': '2001', 'plot': 'After a car wreck on the winding Mulholland Drive renders a woman amnesiac, she and a perky Hollywood-hopeful search for clues and answers across Los Angeles in a twisting venture beyond dreams and reality.', 'runtime': '147 min', 'director': 'David Lynch', 'gendras': ['Drama', 'Mystery', 'Thriller', 'Drama', 'Mystery', 'Thriller']}, {'movieID': 13, 'title': 'Bicycle Thieves', 'releaseYear': '1948', 'plot': "In post-war Italy, a working-class man's bicycle is stolen, endangering his efforts to find work. He and his son set out to find it.", 'runtime': '89 min', 'director': 'Vittorio De Sica', 'gendras': ['Drama', 'Drama']}, {'movieID': 14, 'title': 'The Dark Knight', 'releaseYear': '2008', 'plot': 'When the menace known as the Joker wreaks havoc and chaos on the people of Gotham, Batman must accept one of the greatest psychological and physical tests of his ability to fight injustice.', 'runtime': '152 min', 'director': 'Christopher Nolan', 'gendras': ['Action', 'Crime', 'Drama', 'Action', 'Crime', 'Drama']}, {'movieID': 15, 'title': 'City Lights', 'releaseYear': '1931', 'plot': 'With the aid of a wealthy erratic tippler, a dewy-eyed tramp who has fallen in love with a sightless flower girl accumulates money to be able to help her medically.', 'runtime': '87 min', 'director': 'Charles Chaplin', 'gendras': ['Comedy', 'Drama', 'Romance', 'Comedy', 'Drama', 'Romance']}, {'movieID': 16, 'title': 'The Grand Illusion', 'releaseYear': '1937', 'plot': 'During WWI, two French soldiers are captured and imprisoned in a German P.O.W. camp. Several escape attempts follow until they are eventually sent to a seemingly inescapable fortress.', 'runtime': '113 min', 'director': 'Jean Renoir', 'gendras': ['Drama', 'War', 'Drama', 'War']}, {'movieID': 17, 'title': 'His Girl Friday', 'releaseYear': '1940', 'plot': 'A newspaper editor uses every trick in the book to keep his ace reporter ex-wife from remarrying.', 'runtime': '92 min', 'director': 'Howard Hawks', 'gendras': ['Comedy', 'Drama', 'Romance', 'Comedy', 'Drama', 'Romance']}, {'movieID': 18, 'title': 'The Red Shoes', 'releaseYear': '1948', 'plot': 'A young ballet dancer is torn between the man she loves and her pursuit to become a prima ballerina.', 'runtime': '135 min', 'director': 'Michael Powell, Emeric Pressburger', 'gendras': ['Drama', 'Music', 'Romance', 'Drama', 'Music', 'Romance']}, {'movieID': 19, 'title': 'Vertigo', 'releaseYear': '1958', 'plot': 'A former San Francisco police detective juggles wrestling with his personal demons and becoming obsessed with the hauntingly beautiful woman he has been hired to trail, who may be deeply disturbed.', 'runtime': '128 min', 'director': 'Alfred Hitchcock', 'gendras': ['Mystery', 'Romance', 'Thriller', 'Mystery', 'Romance', 'Thriller']}, {'movieID': 20, 'title': 'Beau travail', 'releaseYear': '1999', 'plot': 'This film focuses on an ex-Foreign Legion officer as he recalls his once glorious life, leading troops in Djibouti.', 'runtime': '92 min', 'director': 'Claire Denis', 'gendras': ['Drama', 'War', 'Drama', 'War']}, {'movieID': 21, 'title': 'The Searchers', 'releaseYear': '1956', 'plot': "An American Civil War veteran embarks on a years-long journey to rescue his niece from the Comanches after the rest of his brother's family is massacred in a raid on their Texas farm.", 'runtime': '119 min', 'director': 'John Ford', 'gendras': ['Adventure', 'Drama', 'Western', 'Adventure', 'Drama', 'Western']}, {'movieID': 22, 'title': 'Persona', 'releaseYear': '1966', 'plot': 'A nurse is put in charge of a mute actress and finds that their personae are melding together.', 'runtime': '83 min', 'director': 'Ingmar Bergman', 'gendras': ['Drama', 'Thriller', 'Drama', 'Thriller']}, {'movieID': 23, 'title': 'Do the Right Thing', 'releaseYear': '1989', 'plot': "On the hottest day of the year on a street in the Bedford-Stuyvesant section of Brooklyn, everyone's hate and bigotry smolders and builds until it explodes into violence.", 'runtime': '120 min', 'director': 'Spike Lee', 'gendras': ['Comedy', 'Drama', 'Comedy', 'Drama']}, {'movieID': 24, 'title': 'Rashomon', 'releaseYear': '1950', 'plot': "The rape of a bride and the murder of her samurai husband are recalled from the perspectives of a bandit, the bride, the samurai's ghost and a woodcutter.", 'runtime': '88 min', 'director': 'Akira Kurosawa', 'gendras': ['Crime', 'Drama', 'Mystery', 'Crime', 'Drama', 'Mystery']}, {'movieID': 25, 'title': 'The Rules of the Game', 'releaseYear': '1939', 'plot': 'A bourgeois life in France at the onset of World War II, as the rich and their poor servants meet up at a French chateau.', 'runtime': '110 min', 'director': 'Jean Renoir', 'gendras': ['Comedy', 'Drama', 'Comedy', 'Drama']}, {'movieID': 26, 'title': 'Jaws', 'releaseYear': '1975', 'plot': "When a killer shark unleashes chaos on a beach community off Long Island, it's up to a local sheriff, a marine biologist, and an old seafarer to hunt the beast down.", 'runtime': '124 min', 'director': 'Steven Spielberg', 'gendras': ['Adventure', 'Thriller', 'Adventure', 'Thriller']}, {'movieID': 27, 'title': 'Double Indemnity', 'releaseYear': '1944', 'plot': 'A Los Angeles insurance representative lets an alluring housewife seduce him into a scheme of insurance fraud and murder that arouses the suspicion of his colleague, an insurance investigator.', 'runtime': '107 min', 'director': 'Billy Wilder', 'gendras': ['Crime', 'Drama', 'Film-Noir', 'Crime', 'Drama', 'Film-Noir']}, {'movieID': 28, 'title': 'The 400 Blows', 'releaseYear': '1959', 'plot': 'A young boy, left without attention, delves into a life of petty crime.', 'runtime': '99 min', 'director': 'François Truffaut', 'gendras': ['Crime', 'Drama', 'Crime', 'Drama']}, {'movieID': 29, 'title': 'Star Wars', 'releaseYear': '1977', 'plot': "Luke Skywalker joins forces with a Jedi Knight, a cocky pilot, a Wookiee and two droids to save the galaxy from the Empire's world-destroying battle station, while also attempting to rescue Princess Leia from the mysterious Darth Vad", 'runtime': '121 min', 'director': 'George Lucas', 'gendras': ['Action', 'Adventure', 'Fantasy', 'Action', 'Adventure', 'Fantasy']}, {'movieID': 30, 'title': 'The Passion of Joan of Arc', 'releaseYear': '1928', 'plot': "In 1431, Jeanne d'Arc is placed on trial on charges of heresy. The ecclesiastical jurists attempt to force Jeanne to recant her claims of holy visions.", 'runtime': '114 min', 'director': 'Carl Theodor Dreyer', 'gendras': ['Biography', 'Drama', 'History', 'Biography', 'Drama', 'History']}, {'movieID': 31, 'title': 'Once Upon a Time in the West', 'releaseYear': '1968', 'plot': 'A mysterious stranger with a harmonica joins forces with a notorious desperado to protect a beautiful widow from a ruthless assassin working for the railroad.', 'runtime': '165 min', 'director': 'Sergio Leone', 'gendras': ['Western', 'Western']}, {'movieID': 32, 'title': 'Alien', 'releaseYear': '1979', 'plot': 'The crew of a commercial spacecraft encounter a deadly lifeform after investigating an unknown transmission.', 'runtime': '117 min', 'director': 'Ridley Scott', 'gendras': ['Horror', 'Sci-Fi', 'Horror', 'Sci-Fi']}, {'movieID': 33, 'title': 'Tokyo Story', 'releaseYear': '1953', 'plot': 'An old couple visit their children and grandchildren in the city, but receive little attention.', 'runtime': '136 min', 'director': 'Yasujirô Ozu', 'gendras': ['Drama', 'Drama']}, {'movieID': 34, 'title': 'Pulp Fiction', 'releaseYear': '1994', 'plot': 'The lives of two mob hitmen, a boxer, a gangster and his wife, and a pair of diner bandits intertwine in four tales of violence and redemption.', 'runtime': '154 min', 'director': 'Quentin Tarantino', 'gendras': ['Crime', 'Drama', 'Crime', 'Drama']}, {'movieID': 35, 'title': 'The Truman Show', 'releaseYear': '1998', 'plot': 'An insurance salesman discovers his whole life is actually a reality TV show.', 'runtime': '103 min', 'director': 'Peter Weir', 'gendras': ['Comedy', 'Drama', 'Comedy', 'Drama']}, {'movieID': 36, 'title': 'Lawrence of Arabia', 'releaseYear': '1962', 'plot': 'The story of T.E. Lawrence, the English officer who successfully united and led the diverse, often warring, Arab tribes during World War I in order to fight the Turks.', 'runtime': '218 min', 'director': 'David Lean', 'gendras': ['Adventure', 'Biography', 'Drama', 'Adventure', 'Biography', 'Drama']}, {'movieID': 37, 'title': 'Psycho', 'releaseYear': '1960', 'plot': "A Phoenix secretary embezzles $40,000 from her employer's client, goes on the run, and checks into a remote motel run by a young man under the domination of his mother.", 'runtime': '109 min', 'director': 'Alfred Hitchcock', 'gendras': ['Horror', 'Mystery', 'Thriller', 'Horror', 'Mystery', 'Thriller']}, {'movieID': 38, 'title': 'Sansho the Bailiff', 'releaseYear': '1954', 'plot': 'In medieval Japan, a compassionate governor is sent into exile. His wife and children try to join him, but are separated, and the children grow up amid suffering and oppression.', 'runtime': '124 min', 'director': 'Kenji Mizoguchi', 'gendras': ['Drama', 'Drama']}, {'movieID': 39, 'title': 'Andrei Rublev', 'releaseYear': '1966', 'plot': 'The life, times and afflictions of the fifteenth-century Russian iconographer St. Andrei Rublev.', 'runtime': '205 min', 'director': 'Andrei Tarkovsky', 'gendras': ['Biography', 'Drama', 'History', 'Biography', 'Drama', 'History']}, {'movieID': 40, 'title': 'The Umbrellas of Cherbourg', 'releaseYear': '1964', 'plot': 'A young woman separated from her lover by war faces a life-altering decision.', 'runtime': '91 min', 'director': 'Jacques Demy', 'gendras': ['Drama', 'Musical', 'Romance', 'Drama', 'Musical', 'Romance']}, {'movieID': 41, 'title': 'Chinatown', 'releaseYear': '1974', 'plot': 'A private detective hired to expose an adulterer in 1930s Los Angeles finds himself caught up in a web of deceit, corruption, and murder.', 'runtime': '130 min', 'director': 'Roman Polanski', 'gendras': ['Drama', 'Mystery', 'Thriller', 'Drama', 'Mystery', 'Thriller']}, {'movieID': 42, 'title': 'The Seventh Seal', 'releaseYear': '1957', 'plot': 'A knight returning to Sweden after the Crusades seeks answers about life, death, and the existence of God as he plays chess against the Grim Reaper during the Black Plague.', 'runtime': '96 min', 'director': 'Ingmar Bergman', 'gendras': ['Drama', 'Fantasy', 'Drama', 'Fantasy']}, {'movieID': 43, 'title': 'Lost in Translation', 'releaseYear': '2003', 'plot': 'A faded movie star and a neglected young woman form an unlikely bond after crossing paths in Tokyo.', 'runtime': '102 min', 'director': 'Sofia Coppola', 'gendras': ['Comedy', 'Drama', 'Comedy', 'Drama']}, {'movieID': 44, 'title': 'Taxi Driver', 'releaseYear': '1976', 'plot': 'A mentally unstable veteran works as a nighttime taxi driver in New York City, where the perceived decadence and sleaze fuels his urge for violent action.', 'runtime': '114 min', 'director': 'Martin Scorsese', 'gendras': ['Crime', 'Drama', 'Crime', 'Drama']}, {'movieID': 45, 'title': 'Spirited Away', 'releaseYear': '2001', 'plot': "During her family's move to the suburbs, a sullen 10-year-old girl wanders into a world ruled by gods, witches, and spirits, and where humans are changed into beasts.", 'runtime': '125 min', 'director': 'Hayao Miyazaki', 'gendras': ['Animation', 'Adventure', 'Family', 'Animation', 'Adventure', 'Family']}, {'movieID': 46, 'title': 'Night of the Living Dead', 'releaseYear': '1968', 'plot': 'A ragtag group of Pennsylvanians barricade themselves in an old farmhouse to remain safe from a horde of flesh-eating ghouls that are ravaging the East Coast of the United States.', 'runtime': '96 min', 'director': 'George A. Romero', 'gendras': ['Horror', 'Thriller', 'Horror', 'Thriller']}, {'movieID': 47, 'title': 'Battleship Potemkin', 'releaseYear': '1925', 'plot': "In the midst of the Russian Revolution of 1905, the crew of the battleship Potemkin mutiny against the brutal, tyrannical regime of the vessel's officers. The resulting street demonstration in Odessa brings on a police massacre.", 'runtime': '66 min', 'director': 'Sergei Eisenstein', 'gendras': ['Drama', 'History', 'Thriller', 'Drama', 'History', 'Thriller']}, {'movieID': 48, 'title': 'Modern Times', 'releaseYear': '1936', 'plot': 'The Tramp struggles to live in modern industrial society with the help of a young homeless woman.', 'runtime': '87 min', 'director': 'Charles Chaplin', 'gendras': ['Comedy', 'Drama', 'Romance', 'Comedy', 'Drama', 'Romance']}, {'movieID': 49, 'title': 'Breathless', 'releaseYear': '1960', 'plot': 'A small-time thief steals a car and impulsively murders a motorcycle policeman. Wanted by the authorities, he reunites with a hip American journalism student and attempts to persuade her to run away with him to Italy.', 'runtime': '90 min', 'director': 'Jean-Luc Godard', 'gendras': ['Crime', 'Drama', 'Crime', 'Drama']}, {'movieID': 50, 'title': 'Dr. Strangelove or: How I Learned to Stop Worrying and Love the Bomb', 'releaseYear': '1964', 'plot': 'An insane American general orders a bombing attack on the Soviet Union, triggering a path to nuclear holocaust that a war room full of politicians and generals frantically tries to stop.', 'runtime': '95 min', 'director': 'Stanley Kubrick', 'gendras': ['Comedy', 'War', 'Comedy', 'War']}, {'movieID': 51, 'title': 'M', 'releaseYear': '1931', 'plot': 'When the police in a German city are unable to catch a child-murderer, other criminals join in the manhunt.', 'runtime': '99 min', 'director': 'Fritz Lang', 'gendras': ['Crime', 'Mystery', 'Thriller', 'Crime', 'Mystery', 'Thriller']}, {'movieID': 52, 'title': 'Blade', 'releaseYear': '1998', 'plot': 'A half-vampire, half-mortal man becomes a protector of the mortal race, while slaying evil vampires.', 'runtime': '120 min', 'director': 'Stephen Norrington', 'gendras': ['Action', 'Horror', 'Sci-Fi', 'Action', 'Horror', 'Sci-Fi']}, {'movieID': 53, 'title': 'The Bitter Tears of Petra von Kant', 'releaseYear': '1972', 'plot': 'A successful fashion designer abandons a sado-masochistic relationship with her female assistant in favor of a love affair with a beautiful young woman.', 'runtime': '124 min', 'director': 'Rainer Werner Fassbinder', 'gendras': ['Drama', 'Romance', 'Drama', 'Romance']}, {'movieID': 54, 'title': 'Rome, Open City', 'releaseYear': '1945', 'plot': 'During the Nazi occupation of Rome in 1944, the Resistance leader, Giorgio Manfredi, is chased by the Nazis as he seeks refuge and a way to escape.', 'runtime': '103 min', 'director': 'Roberto Rossellini', 'gendras': ['Drama', 'Thriller', 'War', 'Drama', 'Thriller', 'War']}, {'movieID': 55, 'title': 'Nosferatu', 'releaseYear': '1922', 'plot': "Vampire Count Orlok expresses interest in a new residence and real estate agent Hutter's wife.", 'runtime': '94 min', 'director': 'F.W. Murnau', 'gendras': ['Fantasy', 'Horror', 'Fantasy', 'Horror']}, {'movieID': 56, 'title': 'Airplane!', 'releaseYear': '1980', 'plot': 'A man afraid to fly must ensure that a plane lands safely after the pilots become sick.', 'runtime': '88 min', 'director': 'Jim Abrahams, David Zucker, Jerry Zucker', 'gendras': ['Comedy', 'Comedy']}, {'movieID': 57, 'title': 'Under the Skin', 'releaseYear': '2013', 'plot': 'A mysterious young woman seduces lonely men in the evening hours in Scotland. However, events lead her to begin a process of self-discovery.', 'runtime': '108 min', 'director': 'Jonathan Glazer', 'gendras': ['Drama', 'Horror', 'Mystery', 'Drama', 'Horror', 'Mystery']}, {'movieID': 58, 'title': 'Mad Max: Fury Road', 'releaseYear': '2015', 'plot': 'In a post-apocalyptic wasteland, a woman rebels against a tyrannical ruler in search for her homeland with the aid of a group of female prisoners, a psychotic worshiper, and a drifter named Max.', 'runtime': '120 min', 'director': 'George Miller', 'gendras': ['Action', 'Adventure', 'Sci-Fi', 'Action', 'Adventure', 'Sci-Fi']}, {'movieID': 59, 'title': 'Apocalypse Now', 'releaseYear': '1979', 'plot': 'A U.S. Army officer serving in Vietnam is tasked with assassinating a renegade Special Forces Colonel who sees himself as a god.', 'runtime': '147 min', 'director': 'Francis Ford Coppola', 'gendras': ['Drama', 'Mystery', 'War', 'Drama', 'Mystery', 'War']}, {'movieID': 60, 'title': 'Brokeback Mountain', 'releaseYear': '2005', 'plot': 'Ennis and Jack are two shepherds who develop a sexual and emotional relationship. Their relationship becomes complicated when both of them get married to their respective girlfriends.', 'runtime': '134 min', 'director': 'Ang Lee', 'gendras': ['Drama', 'Romance', 'Drama', 'Romance']}, {'movieID': 61, 'title': 'Duck Soup', 'releaseYear': '1933', 'plot': "Rufus T. Firefly is named the dictator of bankrupt Freedonia and declares war on neighboring Sylvania over the love of his wealthy backer Mrs. Teasdale, contending with two inept spies who can't seem to keep straight which side they'", 'runtime': '69 min', 'director': 'Leo McCarey', 'gendras': ['Comedy', 'Musical', 'Comedy', 'Musical']}, {'movieID': 62, 'title': 'The Blair Witch Project', 'releaseYear': '1999', 'plot': 'Three film students vanish after traveling into a Maryland forest to film a documentary on the local Blair Witch legend, leaving only their footage behind.', 'runtime': '81 min', 'director': 'Daniel Myrick, Eduardo Sánchez', 'gendras': ['Horror', 'Mystery', 'Horror', 'Mystery']}, {'movieID': 65, 'title': 'The General', 'releaseYear': '1926', 'plot': 'After being rejected by the Confederate military, not realizing it was due to his crucial civilian role, an engineer must single-handedly recapture his beloved locomotive after it is seized by Union spies and return it through ene...', 'runtime': '67 min', 'director': 'Clyde Bruckman, Buster Keaton', 'gendras': ['Action', 'Adventure', 'Comedy', 'Action', 'Adventure', 'Comedy']}, {'movieID': 66, 'title': 'Eternal Sunshine of the Spotless Mind', 'releaseYear': '2004', 'plot': 'When their relationship turns sour, a couple undergoes a medical procedure to have each other erased from their memories.', 'runtime': '108 min', 'director': 'Michel Gondry', 'gendras': ['Drama', 'Romance', 'Sci-Fi', 'Drama', 'Romance', 'Sci-Fi']}, {'movieID': 67, 'title': 'The Texas Chain Saw Massacre', 'releaseYear': '1974', 'plot': 'Five friends head out to rural Texas to visit the grave of a grandfather. On the way they stumble across what appears to be a deserted house, only to discover something sinister within. Something armed with a chainsaw.', 'runtime': '83 min', 'director': 'Tobe Hooper', 'gendras': ['Horror', 'Horror']}, {'movieID': 68, 'title': 'Come and See', 'releaseYear': '1985', 'plot': 'After finding an old rifle, a young boy joins the Soviet resistance movement against ruthless German forces and experiences the horrors of World War II.', 'runtime': '142 min', 'director': 'Elem Klimov', 'gendras': ['Drama', 'Thriller', 'War', 'Drama', 'Thriller', 'War']}, {'movieID': 69, 'title': 'Heat', 'releaseYear': '1995', 'plot': 'A group of high-end professional thieves start to feel the heat from the LAPD when they unknowingly leave a clue at their latest heist.', 'runtime': '170 min', 'director': 'Michael Mann', 'gendras': ['Action', 'Crime', 'Drama', 'Action', 'Crime', 'Drama']}, {'movieID': 70, 'title': 'The Shining', 'releaseYear': '1980', 'plot': 'A family heads to an isolated hotel for the winter where a sinister presence influences the father into violence, while his psychic son sees horrific forebodings from both past and future.', 'runtime': '146 min', 'director': 'Stanley Kubrick', 'gendras': ['Drama', 'Horror', 'Drama', 'Horror']}, {'movieID': 71, 'title': 'Toy Story', 'releaseYear': '1995', 'plot': "A cowboy doll is profoundly threatened and jealous when a new spaceman figure supplants him as top toy in a boy's room.", 'runtime': '81 min', 'director': 'John Lasseter', 'gendras': ['Animation', 'Adventure', 'Comedy', 'Animation', 'Adventure', 'Comedy']}, {'movieID': 72, 'title': 'Killer of Sheep', 'releaseYear': '1978', 'plot': 'Set in the Watts area of Los Angeles, a slaughterhouse worker must suspend his emotions to continue working at a job he finds repugnant, and then he finds he has little sensitivity for the family he works so hard to support.', 'runtime': '80 min', 'director': 'Charles Burnett', 'gendras': ['Drama', 'Drama']}, {'movieID': 73, 'title': 'A Woman Under the Influence', 'releaseYear': '1974', 'plot': 'Although wife and mother Mabel is loved by her husband Nick, her mental illness places a strain on the marriage.', 'runtime': '155 min', 'director': 'John Cassavetes', 'gendras': ['Drama', 'Romance', 'Drama', 'Romance']}, {'movieID': 74, 'title': 'Annie Hall', 'releaseYear': '1977', 'plot': 'Alvy Singer, a divorced Jewish comedian, reflects on his relationship with ex-lover Annie Hall, an aspiring nightclub singer, which ended abruptly just like his previous marriages.', 'runtime': '93 min', 'director': 'Woody Allen', 'gendras': ['Comedy', 'Romance', 'Comedy', 'Romance']}, {'movieID': 75, 'title': 'Some Like It Hot', 'releaseYear': '1959', 'plot': 'After two male musicians witness a mob hit, they flee the state in an all-female band disguised as women, but further complications set in.', 'runtime': '121 min', 'director': 'Billy Wilder', 'gendras': ['Comedy', 'Music', 'Romance', 'Comedy', 'Music', 'Romance']}, {'movieID': 76, 'title': 'Metropolis', 'releaseYear': '1927', 'plot': "In a futuristic city sharply divided between the working class and the city planners, the son of the city's mastermind falls in love with a working-class prophet who predicts the coming of a savior to mediate their differences.", 'runtime': '153 min', 'director': 'Fritz Lang', 'gendras': ['Drama', 'Sci-Fi', 'Drama', 'Sci-Fi']}, {'movieID': 77, 'title': 'The Maltese Falcon', 'releaseYear': '1941', 'plot': 'San Francisco private detective Sam Spade takes on a case that involves him with three eccentric criminals, a gorgeous liar, and their quest for a priceless statuette, with the stakes rising after his partner is murdered.', 'runtime': '100 min', 'director': 'John Huston', 'gendras': ['Crime', 'Film-Noir', 'Mystery', 'Crime', 'Film-Noir', 'Mystery']}, {'movieID': 78, 'title': 'This Is Spinal Tap', 'releaseYear': '1984', 'plot': "Spinal Tap, one of England's loudest bands, is chronicled by film director Marty DiBergi on what proves to be a fateful tour.", 'runtime': '82 min', 'director': 'Rob Reiner', 'gendras': ['Comedy', 'Music', 'Comedy', 'Music']}, {'movieID': 79, 'title': 'It Happened One Night', 'releaseYear': '1934', 'plot': 'A renegade reporter trailing a young runaway heiress for a big story joins her on a bus heading from Florida to New York, and they end up stuck with each other when the bus leaves them behind at one of the stops.', 'runtime': '105 min', 'director': 'Frank Capra', 'gendras': ['Comedy', 'Romance', 'Comedy', 'Romance']}, {'movieID': 80, 'title': 'Die Hard', 'releaseYear': '1988', 'plot': 'An NYPD officer tries to save his wife and several others taken hostage by German terrorists during a Christmas party at the Nakatomi Plaza in Los Angeles.', 'runtime': '132 min', 'director': 'John McTiernan', 'gendras': ['Action', 'Thriller', 'Action', 'Thriller']}, {'movieID': 81, 'title': 'The Conformist', 'releaseYear': '1970', 'plot': 'A weak-willed Italian man becomes a fascist flunky who goes abroad to arrange the assassination of his old teacher, now a political dissident.', 'runtime': '113 min', 'director': 'Bernardo Bertolucci', 'gendras': ['Drama', 'Drama']}, {'movieID': 82, 'title': 'The Thing', 'releaseYear': '1982', 'plot': 'A research team in Antarctica is hunted by a shape-shifting alien that assumes the appearance of its victims.', 'runtime': '109 min', 'director': 'John Carpenter', 'gendras': ['Horror', 'Mystery', 'Sci-Fi', 'Horror', 'Mystery', 'Sci-Fi']}, {'movieID': 83, 'title': 'Daughters of the Dust', 'releaseYear': '1991', 'plot': 'A languid, impressionistic story of three generations of Gullah women living on the South Carolina Sea Islands in 1902.', 'runtime': '113 min', 'director': 'Julie Dash', 'gendras': ['Drama', 'History', 'Romance', 'Drama', 'History', 'Romance']}, {'movieID': 84, 'title': 'Barry Lyndon', 'releaseYear': '1975', 'plot': "An Irish rogue wins the heart of a rich widow and assumes her dead husband's aristocratic position in 18th-century England.", 'runtime': '185 min', 'director': 'Stanley Kubrick', 'gendras': ['Adventure', 'Drama', 'War', 'Adventure', 'Drama', 'War']}, {'movieID': 85, 'title': 'Raging Bull', 'releaseYear': '1980', 'plot': 'The life of boxer Jake LaMotta, whose violence and temper that led him to the top in the ring destroyed his life outside of it.', 'runtime': '129 min', 'director': 'Martin Scorsese', 'gendras': ['Biography', 'Drama', 'Sport', 'Biography', 'Drama', 'Sport']}, {'movieID': 86, 'title': 'Seven Samurai', 'releaseYear': '1954', 'plot': 'A poor village under attack by bandits recruits seven unemployed samurai to help them defend themselves.', 'runtime': '207 min', 'director': 'Akira Kurosawa', 'gendras': ['Action', 'Drama', 'Action', 'Drama']}, {'movieID': 87, 'title': 'Aguirre, the Wrath of God', 'releaseYear': '1972', 'plot': 'In the 16th century, the ruthless and insane Don Lope de Aguirre leads a Spanish expedition in search of El Dorado.', 'runtime': '95 min', 'director': 'Werner Herzog', 'gendras': ['Action', 'Adventure', 'Biography', 'Action', 'Adventure', 'Biography']}, {'movieID': 88, 'title': 'The Battle of Algiers', 'releaseYear': '1966', 'plot': 'In the 1950s, fear and violence escalate as the people of Algiers fight for independence from the French government.', 'runtime': '121 min', 'director': 'Gillo Pontecorvo', 'gendras': ['Drama', 'War', 'Drama', 'War']}, {'movieID': 89, 'title': 'Women on the Verge of a Nervous Breakdown', 'releaseYear': '1988', 'plot': 'A television actress encounters a variety of eccentric characters after embarking on a journey to discover why her lover abruptly left her.', 'runtime': '88 min', 'director': 'Pedro Almodóvar', 'gendras': ['Comedy', 'Drama', 'Comedy', 'Drama']}, {'movieID': 90, 'title': 'Boyhood', 'releaseYear': '2014', 'plot': 'The life of Mason, from early childhood to his arrival at college.', 'runtime': '165 min', 'director': 'Richard Linklater', 'gendras': ['Drama', 'Drama']}, {'movieID': 91, 'title': 'The Discreet Charm of the Bourgeoisie', 'releaseYear': '1972', 'plot': 'A surreal, virtually plotless series of dreams centered around six middle-class people and their consistently interrupted attempts to have a meal together.', 'runtime': '102 min', 'director': 'Luis Buñuel', 'gendras': ['Comedy', 'Comedy']}, {'movieID': 92, 'title': 'Paths of Glory', 'releaseYear': '1957', 'plot': 'After refusing to attack an enemy position, a general accuses the soldiers of cowardice and their commanding officer must defend them.', 'runtime': '88 min', 'director': 'Stanley Kubrick', 'gendras': ['Drama', 'War', 'Drama', 'War']}, {'movieID': 93, 'title': 'Fantastic Beasts: The Secrets of Dumbledore', 'releaseYear': '2022', 'plot': 'Albus Dumbledore assigns Newt and his allies with a mission related to the rising power of Grindelwald.', 'runtime': '142 min', 'director': 'David Yates', 'gendras': ['Adventure', 'Family', 'Fantasy', 'Adventure', 'Family', 'Fantasy']}, {'movieID': 94, 'title': 'Sweet Smell of Success', 'releaseYear': '1957', 'plot': "Powerful but unethical Broadway columnist J.J. Hunsecker coerces unscrupulous press agent Sidney Falco into breaking up his sister's romance with a jazz musician.", 'runtime': '96 min', 'director': 'Alexander Mackendrick', 'gendras': ['Drama', 'Film-Noir', 'Drama', 'Film-Noir']}, {'movieID': 95, 'title': 'The Cabinet of Dr. Caligari', 'releaseYear': '1920', 'plot': 'Hypnotist Dr. Caligari uses a somnambulist, Cesare, to commit murders.', 'runtime': '67 min', 'director': 'Robert Wiene', 'gendras': ['Horror', 'Mystery', 'Thriller', 'Horror', 'Mystery', 'Thriller']}, {'movieID': 96, 'title': 'Nashville', 'releaseYear': '1975', 'plot': 'Over the course of a few hectic days, numerous interrelated people prepare for a political convention.', 'runtime': '160 min', 'director': 'Robert Altman', 'gendras': ['Comedy', 'Drama', 'Music', 'Comedy', 'Drama', 'Music']}, {'movieID': 98, 'title': 'Bonnie and Clyde', 'releaseYear': '1967', 'plot': 'Bored waitress Bonnie Parker falls in love with an ex-con named Clyde Barrow and together they start a violent crime spree through the country, stealing cars and robbing banks.', 'runtime': '111 min', 'director': 'Arthur Penn', 'gendras': ['Action', 'Biography', 'Crime', 'Action', 'Biography', 'Crime']}, {'movieID': 99, 'title': 'Get Out', 'releaseYear': '2017', 'plot': "A young African-American visits his white girlfriend's parents for the weekend, where his simmering uneasiness about their reception of him eventually reaches a boiling point.", 'runtime': '104 min', 'director': 'Jordan Peele', 'gendras': ['Horror', 'Mystery', 'Thriller', 'Horror', 'Mystery', 'Thriller']}]


for movie in movies_arr:

    directors.append(movie["director"])
    gendras.extend(movie["gendras"])

directors = list(dict.fromkeys(directors))
gendras = list(dict.fromkeys(gendras))

directors = [{"directorID" : i, "name" : d} for i, d in enumerate(directors)]
gendras = [{"gendraID" : i, "gendra" : g} for i, g in enumerate(gendras)]

print(directors)
print(gendras)


for movie in movies_arr:
    for d in directors:
        if movie["director"] == d["name"]:
            movie["directorID"] = d["directorID"]

    movie_gendra_ids = []
    for g in gendras:
        for mg in movie["gendras"]:
            if g["gendra"] == mg:
                movie_gendra_ids.append(g["gendraID"])
    movie["gendras"] = list(dict.fromkeys(movie_gendra_ids))

print(movies_arr)
movies_json = json.dumps(movies_arr)
print(movies_json)

sql_script = ""
quotes = "''"

for d in directors:
    name = d['name'].replace("'", quotes)
    s = f"INSERT INTO director VALUES({d['directorID'] + 1}, \'{name}\');\n"
    sql_script += s

sql_script += "\n"

for g in gendras:
    s = f"INSERT INTO genre VALUES({g['gendraID'] + 1}, \'{g['gendra']}\');\n"
    sql_script += s

sql_script += "\n"

for m in movies_arr:
    title = m['title'].replace("'", quotes)
    plot = m['plot'].replace("'", quotes)
    s = f"INSERT INTO movie VALUES({m['movieID'] + 1}, \'{title}\', {m['releaseYear']}, \'{plot}\', 'poster.jpg', {m['runtime'][:-4]}, {m['directorID'] + 1});\n"
    sql_script += s

sql_script += "\n"

for m in movies_arr:
    for g in m["gendras"]:
        s = f"INSERT INTO genremovie VALUES({g + 1}, {m['movieID'] + 1});\n"
        sql_script += s

logins = ["bob", 'john', "johnny", "steve", "mike", "kimberley", "chloe", "robert", "ingrid"]
for i , login in enumerate(logins):
    s = f"INSERT INTO user VALUES({i + 1}, \'{login}\', \'$2y$10$Ge5aw8PItl1XZ9pOw22duOqvoRXZJ8G3KT2mnnYXHlBqCdLvTjT8m\', \'user\');\n"
    sql_script += s

for i in range(len(logins)):
    for movie in movies_arr:
        rating = random.randint(0,5)
        s = f"INSERT INTO rating VALUES({i + 1}, {movie['movieID'] + 1}, {rating});\n"
        sql_script += s


comment = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Integer non tellus mauris. Sed consequat in lacus ac sodales. Aliquam sagittis urna eleifend sapien convallis bibendum. Morbi quis neque eu sem malesuada ultrices. Sed id porta sem, in vestibulum enim. Aliquam posuere at ex in faucibus. Praesent consequat accumsan elit, et lacinia diam consectetur sed. Aliquam blandit quam ante, cursus auctor dui pulvinar sed. In non ornare nunc. Ut turpis ante, finibus ultricies ligula ultricies, maximus convallis orci."

for i in range(len(logins)):
    for movie in movies_arr:
        s = f"INSERT INTO comment(userID, movieID, content) VALUES({i + 1}, {movie['movieID'] + 1}, \'{comment}\');\n"
        sql_script += s



with open("fillDB.sql", 'w', encoding='utf-8') as f:
    f.write(sql_script)


