@file:Suppress("unused")

package my.id.airham.core.utils

import com.google.gson.Gson
import my.id.airham.core.data.source.local.entity.MovieEntity
import my.id.airham.core.data.source.local.entity.TvShowEntity
import my.id.airham.core.data.source.remote.response.GetMovieDetailResponse
import my.id.airham.core.data.source.remote.response.GetTvDetailResponse
import my.id.airham.core.data.source.remote.response.MovieResponse
import my.id.airham.core.data.source.remote.response.TvShowResponse
import java.io.File
import java.io.FileInputStream
import java.io.InputStreamReader

/**
 *  Ini merupakan Data Dummy yang dipersiapkan untuk diambil oleh kelas Movie dan TvShow
 *  {@link generateMovies()} digunakan untuk mengenereate daftar film yang nantinya akan ditampilkan
 *  pada fragment {@link ui.movie.MovieFragment}
 *
 *  {@link genearteTvs} digunakan untuk mengenerate daftar tv yang nantinya ditampilkan pada fragment
 *  {@link ui.tvshow.TvShowFragment}
 */

object DataDummy {
    fun generateMovies(): List<MovieEntity> {
        val movies = ArrayList<MovieEntity>()

        movies.add(
            MovieEntity(
                775996,
                "Outside the Wire",
                "In the near future, a drone pilot is sent into a deadly militarized zone and must work with an android officer to locate a doomsday device.",
                "/e6SK2CAbO3ENy52UTzP3lv32peC.jpg",
                "2021-01-15",
                6.5
            )
        )

        movies.add(
            MovieEntity(
                464052,
                "Wonder Woman 1984",
                "Wonder Woman comes into conflict with the Soviet Union during the Cold War in the 1980s and finds a formidable foe by the name of the Cheetah.",
                "/8UlWHLMpgZm9bx6QYh0NFoq67TZ.jpg",
                "2020-12-16",
                7.0
            )
        )

        movies.add(
            MovieEntity(
                651571,
                "Breach",
                "A hardened mechanic must stay awake and maintain an interstellar ark fleeing the dying planet Earth with a few thousand lucky souls on board... the last of humanity. Unfortunately, humans are not the only passengers. A shapeshifting alien creature has taken residence, its only goal is to kill as many people as possible. The crew must think quickly to stop this menace before it destroys mankind.",
                "/13B6onhL6FzSN2KaNeQeMML05pS.jpg",
                "2020-12-17",
                5.0
            )
        )

        movies.add(
            MovieEntity(
                508442,
                "Soul",
                "Joe Gardner is a middle school teacher with a love for jazz music. After a successful gig at the Half Note Club, he suddenly gets into an accident that separates his soul from his body and is transported to the You Seminar, a center in which souls develop and gain passions before being transported to a newborn child. Joe must enlist help from the other souls-in-training, like 22, a soul who has spent eons in the You Seminar, in order to get back to Earth.",
                "/hm58Jw4Lw8OIeECIq5qyPYhAeRJ.jpg",
                "2020-12-25",
                8.3
            )
        )

        movies.add(
            MovieEntity(
                560144,
                "Skylines",
                "When a virus threatens to turn the now earth-dwelling friendly alien hybrids against humans, Captain Rose Corley must lead a team of elite mercenaries on a mission to the alien world in order to save what's left of humanity.",
                "/2W4ZvACURDyhiNnSIaFPHfNbny3.jpg",
                "2020-10-25",
                5.8
            )
        )

        movies.add(
            MovieEntity(
                373571,
                "Godzilla: King of the Monsters",
                "Follows the heroic efforts of the crypto-zoological agency Monarch as its members face off against a battery of god-sized monsters, including the mighty Godzilla, who collides with Mothra, Rodan, and his ultimate nemesis, the three-headed King Ghidorah. When these ancient super-species, thought to be mere myths, rise again, they all vie for supremacy, leaving humanity's very existence hanging in the balance.",
                "/mzOHg7Q5q9yUmY0b9Esu8Qe6Nnm.jpg",
                "2019-05-29",
                6.6
            )
        )

        movies.add(
            MovieEntity(
                399566,
                "Godzilla vs. Kong",
                "In a time when monsters walk the Earth, humanity’s fight for its future sets Godzilla and Kong on a collision course that will see the two most powerful forces of nature on the planet collide in a spectacular battle for the ages.",
                "/soM5eNHxxS3a0AqgvDgm2djJXZp.jpg",
                "2021-03-25",
                0.0
            )
        )

        movies.add(
            MovieEntity(
                581387,
                "백두산",
                "Stagnant since 1903, at an elevation of 9000', a volcano erupts on the mythical and majestic Baekdu Mountain.",
                "/zoeKREZ2IdAUnXISYCS0E6H5BVh.jpg",
                "2019-12-19",
                6.8
            )
        )

        movies.add(
            MovieEntity(
                539885,
                "Ava",
                "A black ops assassin is forced to fight for her own survival after a job goes dangerously wrong.",
                "/qzA87Wf4jo1h8JMk9GilyIYvwsA.jpg",
                "2020-07-02",
                5.6
            )
        )

        movies.add(
            MovieEntity(
                604822,
                "急先锋",
                "Covert security company Vanguard is the last hope of survival for an accountant after he is targeted by the world's deadliest mercenary organization.",
                "/vYvppZMvXYheYTWVd8Rnn9nsmNp.jpg",
                "2020-09-30",
                6.7
            )
        )

        movies.add(
            MovieEntity(
                553604,
                "Honest Thief",
                "A bank robber tries to turn himself in because he's falling in love and wants to live an honest life...but when he realizes the Feds are more corrupt than him, he must fight back to clear his name.",
                "/zeD4PabP6099gpE0STWJrJrCBCs.jpg",
                "2020-09-03",
                6.6
            )
        )

        movies.add(
            MovieEntity(
                577922,
                "Tenet",
                "Armed with only one word - Tenet - and fighting for the survival of the entire world, the Protagonist journeys through a twilight world of international espionage on a mission that will unfold in something beyond real time.",
                "/k68nPLbIST6NP96JmTxmZijEvCA.jpg",
                "2020-08-22",
                7.3
            )
        )

        movies.add(
            MovieEntity(
                737568,
                "The Doorman",
                "A former Marine turned doorman at a luxury New York City high-rise must outsmart and battle a group of art thieves and their ruthless leader — while struggling to protect her sister's family. As the thieves become increasingly desperate and violent, the doorman calls upon her deadly fighting skills to end the showdown.",
                "/pklyUbh4k1DbHdnsOMASyw7C6NH.jpg",
                "2020-10-01",
                5.9
            )
        )

        movies.add(
            MovieEntity(
                495764,
                "Birds of Prey (and the Fantabulous Emancipation of One Harley Quinn)",
                "Harley Quinn joins forces with a singer, an assassin and a police detective to help a young girl who had a hit placed on her after she stole a rare diamond from a crime lord.",
                "/h4VB6m0RwcicVEZvzftYZyKXs6K.jpg",
                "2020-02-05",
                7.1
            )
        )

        movies.add(
            MovieEntity(
                520946,
                "100% Wolf",
                "Freddy Lupin, heir to a proud family line of werewolves, is in for a shock when on his 14th birthday his first 'warfing' goes awry, turning him into a ferocious poodle. The pack elders give Freddy until the next moonrise to prove he has the heart of a wolf, or risk being cast out forever. With the help of an unlikely ally in a streetwise stray named Batty, Freddy must prove he's 100% Wolf.",
                "/2VrvxK4yxNCU6KVgo5TADJeBEQu.jpg",
                "2020-06-26",
                6.5
            )
        )

        movies.add(
            MovieEntity(
                531499,
                "The Tax Collector",
                "David Cuevas is a family man who works as a gangland tax collector for high ranking Los Angeles gang members. He makes collections across the city with his partner Creeper making sure people pay up or will see retaliation. An old threat returns to Los Angeles that puts everything David loves in harm’s way.",
                "/3eg0kGC2Xh0vhydJHO37Sp4cmMt.jpg",
                "2020-08-07",
                5.8
            )
        )

        movies.add(
            MovieEntity(
                529203,
                "The Croods: A New Age",
                "Searching for a safer habitat, the prehistoric Crood family discovers an idyllic, walled-in paradise that meets all of its needs. Unfortunately, they must also learn to live with the Bettermans -- a family that's a couple of steps above the Croods on the evolutionary ladder. As tensions between the new neighbors start to rise, a new threat soon propels both clans on an epic adventure that forces them to embrace their differences, draw strength from one another, and survive together.",
                "/tK1zy5BsCt1J4OzoDicXmr0UTFH.jpg",
                "2020-11-25",
                7.7
            )
        )

        movies.add(
            MovieEntity(
                664767,
                "Mortal Kombat Legends: Scorpion's Revenge",
                "After the vicious slaughter of his family by stone-cold mercenary Sub-Zero, Hanzo Hasashi is exiled to the torturous Netherrealm. There, in exchange for his servitude to the sinister Quan Chi, he’s given a chance to avenge his family – and is resurrected as Scorpion, a lost soul bent on revenge. Back on Earthrealm, Lord Raiden gathers a team of elite warriors – Shaolin monk Liu Kang, Special Forces officer Sonya Blade and action star Johnny Cage – an unlikely band of heroes with one chance to save humanity. To do this, they must defeat Shang Tsung’s horde of Outworld gladiators and reign over the Mortal Kombat tournament.",
                "/4VlXER3FImHeFuUjBShFamhIp9M.jpg",
                "2020-04-12",
                8.4
            )
        )

        movies.add(
            MovieEntity(
                755812,
                "Miraculous World: New York, United HeroeZ",
                "During a school field trip, Ladybug and Cat Noir meet the American superheroes, whom they have to save from an akumatised super-villain. They discover that Miraculous exist in the United States too.",
                "/kIHgjAkuzvKBnmdstpBOo4AfZah.jpg",
                "2020-09-26",
                8.6
            )
        )

        return movies
    }

    fun generateTvs(): List<TvShowEntity> {
        val tv = ArrayList<TvShowEntity>()

        tv.add(
            TvShowEntity(
                85271,
                "WandaVision",
                "Wanda Maximoff and Vision—two super-powered beings living idealized suburban lives—begin to suspect that everything is not as it seems.",
                "/glKDfE6btIRcVB5zrjspRIs4r52.jpg",
                "2021-01-15",
                8.4
            )
        )

        tv.add(
            TvShowEntity(
                69050,
                "Riverdale",
                "Set in the present, the series offers a bold, subversive take on Archie, Betty, Veronica and their friends, exploring the surreality of small-town life, the darkness and weirdness bubbling beneath Riverdale’s wholesome facade.",
                "/wRbjVBdDo5qHAEOVYoMWpM58FSA.jpg",
                "2017-01-26",
                8.6
            )
        )
        tv.add(
            TvShowEntity(
                114695,
                "Marvel Studios: Legends",
                "Revisit the epic heroes, villains and moments from across the MCU in preparation for the stories still to come. Each dynamic segment feeds directly into the upcoming series — setting the stage for future events. This series weaves together the many threads that constitute the unparalleled Marvel Cinematic Universe.",
                "/EpDuYIK81YtCUT3gH2JDpyj8Qk.jpg",
                "2021-01-08",
                7.7
            )
        )
        tv.add(
            TvShowEntity(
                79460,
                "Legacies",
                "In a place where young witches, vampires, and werewolves are nurtured to be their best selves in spite of their worst impulses, Klaus Mikaelson’s daughter, 17-year-old Hope Mikaelson, Alaric Saltzman’s twins, Lizzie and Josie Saltzman, among others, come of age into heroes and villains at The Salvatore School for the Young and Gifted.",
                "/qTZIgXrBKURBK1KrsT7fe3qwtl9.jpg",
                "2018-10-25",
                8.6
            )
        )
        tv.add(
            TvShowEntity(
                71712,
                "The Good Doctor",
                "A young surgeon with Savant syndrome is recruited into the surgical unit of a prestigious hospital. The question will arise: can a person who doesn't have the ability to relate to people actually save their lives",
                "/6tfT03sGp9k4c0J3dypjrI8TSAI.jpg",
                "2017-09-25",
                8.6
            )
        )
        tv.add(
            TvShowEntity(
                82856,
                "The Mandalorian",
                "After the fall of the Galactic Empire, lawlessness has spread throughout the galaxy. A lone gunfighter makes his way through the outer reaches, earning his keep as a bounty hunter.",
                "/sWgBv7LV2PRoQgkxwlibdGXKz1S.jpg",
                "2019-11-12",
                8.5,
            )
        )
        tv.add(
            TvShowEntity(
                97175,
                "Fate: The Winx Saga",
                "The coming-of-age journey of five fairies attending Alfea, a magical boarding school in the Otherworld where they must learn to master their powers while navigating love, rivalries, and the monsters that threaten their very existence.",
                "/oHj6guMrLfQcBzo3uxwBJc8Y736.jpg",
                "2021-01-22",
                8.6
            )
        )
        tv.add(
            TvShowEntity(
                77169,
                "Cobra Kai",
                "This Karate Kid sequel series picks up 30 years after the events of the 1984 All Valley Karate Tournament and finds Johnny Lawrence on the hunt for redemption by reopening the infamous Cobra Kai karate dojo. This reignites his old rivalry with the successful Daniel LaRusso, who has been working to maintain the balance in his life without mentor Mr. Miyagi.",
                "/obLBdhLxheKg8Li1qO11r2SwmYO.jpg",
                "2018-05-02",
                8.2
            )
        )
        tv.add(
            TvShowEntity(
                44217,
                "Vikings",
                "The adventures of Ragnar Lothbrok, the greatest hero of his age. The series tells the sagas of Ragnar's band of Viking brothers and his family, as he rises to become King of the Viking tribes. As well as being a fearless warrior, Ragnar embodies the Norse traditions of devotion to the gods. Legend has it that he was a direct descendant of Odin, the god of war and warriors.",
                "/bQLrHIRNEkE3PdIWQrZHynQZazu.jpg",
                "2013-03-03",
                8.0
            )
        )
        tv.add(
            TvShowEntity(
                79680,
                "Snowpiercer",
                "Set more than seven years after the world has become a frozen wasteland, the remnants of humanity inhabit a gigantic, perpetually-moving train that circles the globe as class warfare, social injustice and the politics of survival play out.",
                "/95xQPSqwvQDRoB3rXUn4lRyJrBW.jpg",
                "2020-05-17",
                7.6
            )
        )

        tv.add(
            TvShowEntity(
                96677,
                "Lupin",
                "Inspired by the adventures of Arsène Lupin, gentleman thief Assane Diop sets out to avenge his father for an injustice inflicted by a wealthy family.",
                "/sgxawbFB5Vi5OkPWQLNfl3dvkNJ.jpg",
                "2021-01-08",
                8.0
            )
        )

        tv.add(
            TvShowEntity(
                1416,
                "Grey's Anatomy",
                "Follows the personal and professional lives of a group of doctors at Seattle’s Grey Sloan Memorial Hospital.",
                "/clnyhPqj1SNgpAdeSS6a6fwE6Bo.jpg",
                "2005-03-27",
                8.2
            )
        )

        tv.add(
            TvShowEntity(
                63174,
                "Lucifer",
                "Bored and unhappy as the Lord of Hell, Lucifer Morningstar abandoned his throne and retired to Los Angeles, where he has teamed up with LAPD detective Chloe Decker to take down criminals. But the longer he's away from the underworld, the greater the threat that the worst of humanity could escape.",
                "/4EYPN5mVIhKLfxGruy7Dy41dTVn.jpg",
                "2016-01-25",
                8.5
            )
        )

        tv.add(
            TvShowEntity(
                79611,
                "Charmed",
                "Set in the fictional college town of Hilltowne, Charmed follows the lives of three sisters, Macy, Mel and Maggie Vera who, after the tragic death of their mother, discover they are three of the most powerful witches of all time.",
                "/jyArBuSbEnSoQinAZBU6pZmmL6M.jpg",
                "2018-10-14",
                7.5
            )
        )
        tv.add(
            TvShowEntity(
                46639,
                "American Gods",
                "An ex-con becomes the traveling partner of a conman who turns out to be one of the older gods trying to recruit troops to battle the upstart deities. Based on Neil Gaiman's fantasy novel.",
                "/3KCAZaKHmoMIN9dHutqaMtubQqD.jpg",
                "2017-04-30",
                7.1
            )
        )
        tv.add(
            TvShowEntity(
                75006,
                "The Umbrella Academy",
                "A dysfunctional family of superheroes comes together to solve the mystery of their father's death, the threat of the apocalypse and more.",
                "/scZlQQYnDVlnpxFTxaIv2g0BWnL.jpg",
                "2019-02-15",
                8.7
            )
        )

        tv.add(
            TvShowEntity(
                91239,
                "Bridgerton",
                "Wealth, lust, and betrayal set in the backdrop of Regency era England, seen through the eyes of the powerful Bridgerton family.",
                "/qaewZKBKmXjb4ZfFBb1LCug6BE8.jpg",
                "2020-12-25",
                8.3
            )
        )

        tv.add(
            TvShowEntity(
                79242,
                "Chilling Adventures of Sabrina",
                "As her 16th birthday nears, Sabrina must choose between the witch world of her family and the human world of her friends. Based on the Archie comic.",
                "/yxMpoHO0CXP5o9gB7IfsciilQS4.jpg",
                "2018-10-26",
                8.4
            )
        )

        tv.add(
            TvShowEntity(
                89247,
                "Batwoman",
                "Kate Kane, armed with a passion for social justice and a flair for speaking her mind, soars onto the streets of Gotham as Batwoman, an out lesbian and highly trained street fighter primed to snuff out the failing city's criminal resurgence. But don't call her a hero yet. In a city desperate for a savior, Kate must overcome her own demons before embracing the call to be Gotham's symbol of hope",
                "/xjyEpcuDbB1jy0ehNQMBiO8KOdr.jpg",
                "2019-10-06",
                7.3
            )
        )

        tv.add(
            TvShowEntity(
                86382,
                "The Stand",
                "In a world mostly wiped out by the plague and embroiled in an elemental struggle between good and evil, the fate of mankind rests on the frail shoulders of the 108-year-old Mother Abagail and a handful of survivors. Their worst nightmares are embodied in a man with a lethal smile and unspeakable powers: Randall Flagg, the Dark Man.",
                "/w6XiuRK5QQaLNmIqDRCWOpEcHwi.jpg",
                "2020-12-17",
                7.2
            )
        )

        return tv
    }

    fun getMoviesFromRemote(): MovieResponse =
        getFromJson("movie_response.json", MovieResponse::class.java)

    fun getTvShowFromRemote(): TvShowResponse =
        getFromJson("tv_show_response.json", TvShowResponse::class.java)

    fun getMovieDetailFromRemote(): GetMovieDetailResponse =
        getFromJson("movie_detail_sample.json", GetMovieDetailResponse::class.java)

    fun getTvShowDetailFromRemote(): GetTvDetailResponse =
        getFromJson("tv_show_detail_sample.json", GetTvDetailResponse::class.java)

    private fun <T> getFromJson(filename: String, type: Class<T>): T {
        val resource = File(File("").absolutePath, "src/test/resources")
        val jsonFile = File(resource.absolutePath, filename)
        val iStream = FileInputStream(jsonFile)

        val iReader = InputStreamReader(iStream)
        val data = Gson().fromJson(iReader, type)

        iReader.close()
        iStream.close()

        return data
    }
}