package id.airham.moviecatalogue.utils

import id.airham.moviecatalogue.data.MovieEntity
import id.airham.moviecatalogue.data.TvShowEntity

/**
 *  Ini merupakan Data Dummy yang dipersiapkan untuk diambil oleh kelas Movie dan TvShow
 *  {@link generateMovies()} digunakan untuk mengenereate daftar film yang nantinya akan ditampilkan
 *  pada fragment {@link ui.movie.MovieFragment}
 *
 *  {@link genearteTvs} digunakan untuk mengenerate daftar tv yang nantinya ditampilkan pada fragment
 *  {@link ui.tvshow.TvShowFragment}
 */

object DataDummy {
    fun generateMovies(): List<MovieEntity>{
        val movies = ArrayList<MovieEntity>()

        movies.add(
            MovieEntity("m1",
            "A Star Is Born",
            "Seorang bintang musik country yang karirnya mulai memudar, Jackson Maine (Bradley Cooper) menemukan sebuah talenta yang sangat berbakat di dalam diri dari seorang musisi muda bernama Ally (Lady Gaga). Maine berhasil mengorbitkan Ally menjadi seorang bintang muda yang menjanjikan. Namun keduanya terlibat hubungan yang lebih jauh dari sekedar mentor dan anak didik. Seiring dengan meroketnya karir dari Ally dan dirinya, Maine mengalami dilema mengenai masalah ini.",
            "https://www.themoviedb.org/t/p/w220_and_h330_face/wrFpXMNBRj2PBiN4Z5kix51XaIZ.jpg",
            "05/10/2018",
            7.5
        )
        )

        movies.add(
            MovieEntity("m2",
            "Alita: Battle Angel",
            "Ketika Alita terbangun tanpa ingatan tentang siapa dia di dunia masa depan yang tidak dia kenal, dia ditangkap oleh Ido, seorang dokter yang penuh kasih yang menyadari bahwa di suatu tempat dalam cangkang cyborg yang ditinggalkan ini adalah hati dan jiwa seorang wanita muda dengan luar biasa. lalu.",
            "https://www.themoviedb.org/t/p/w300_and_h450_bestv2/xRWht48C2V8XNfzvPehyClOvDni.jpg",
            "14/02/2019",
            8.0
        )
        )

        movies.add(
            MovieEntity("m3",
            "Aquaman",
            "Once home to the most advanced civilization on Earth, Atlantis is now an underwater kingdom ruled by the power-hungry King Orm. With a vast army at his disposal, Orm plans to conquer the remaining oceanic people and then the surface world. Standing in his way is Arthur Curry, Orm's half-human, half-Atlantean brother and true heir to the throne.",
            "https://www.themoviedb.org/t/p/w300_and_h450_bestv2/xLPffWMhMj1l50ND3KchMjYoKmE.jpg",
            "21/12/2018",
            6.9
        )
        )

        movies.add(
            MovieEntity(
            "m4",
            "Bohemian Rhapsody",
            "Singer Freddie Mercury, guitarist Brian May, drummer Roger Taylor and bass guitarist John Deacon take the music world by storm when they form the rock 'n' roll band Queen in 1970. Hit songs become instant classics. When Mercury's increasingly wild lifestyle starts to spiral out of control, Queen soon faces its greatest challenge yet – finding a way to keep the band together amid the success and excess.",
            "https://www.themoviedb.org/t/p/w300_and_h450_bestv2/lHu1wtNaczFPGFDTrjCSzeLPTKN.jpg",
            "02/11/2018",
            8.0
        )
        )

        movies.add(
            MovieEntity(
            "m5",
            "Cold Pursuit ",
            "The quiet family life of Nels Coxman, a snowplow driver, is upended after his son's murder. Nels begins a vengeful hunt for Viking, the drug lord he holds responsible for the killing, eliminating Viking's associates one by one. As Nels draws closer to Viking, his actions bring even more unexpected and violent consequences, as he proves that revenge is all in the execution.",
            "https://www.themoviedb.org/t/p/w220_and_h330_face/hXgmWPd1SuujRZ4QnKLzrj79PAw.jpg",
            "08/02/2019",
            5.6
        )
        )

        movies.add(
            MovieEntity(
            "m6",
            "Creed II",
            "Between personal obligations and training for his next big fight against an opponent with ties to his family's past, Adonis Creed is up against the challenge of his life.",
            "https://www.themoviedb.org/t/p/w220_and_h330_face/v3QyboWRoA4O9RbcsqH8tJMe8EB.jpg",
            "21/11/2018",
            6.9
        )
        )

        movies.add(
            MovieEntity(
            "m7",
            "Fantastic Beasts: The Crimes of Grindelwald",
            "Gellert Grindelwald telah melarikan diri dari penjara dan telah mulai mengumpulkan pengikut ke tujuannya — meninggikan penyihir di atas semua makhluk non-magis. Satu-satunya yang bisa menghentikannya adalah penyihir yang pernah disebutnya sebagai sahabat terdekatnya, Albus Dumbledore. Namun, Dumbledore akan perlu mencari bantuan dari penyihir yang telah menggagalkan Grindelwald sebelumnya, mantan muridnya, Newt Scamander, yang setuju untuk membantu, tidak menyadari bahaya yang ada di depan. Garis-garis digambar saat cinta dan kesetiaan diuji, bahkan di antara teman-teman dan keluarga sejati, di dunia sihir yang semakin terbagi.",
            "https://www.themoviedb.org/t/p/w220_and_h330_face/2MokCu61LzWw872lPzq1VCJXTD1.jpg",
            "16/11/2018",
            7.4
        )
        )

        movies.add(
            MovieEntity(
            "m8",
            "Glass",
            "In a series of escalating encounters, former security guard David Dunn uses his supernatural abilities to track Kevin Wendell Crumb, a disturbed man who has twenty-four personalities. Meanwhile, the shadowy presence of Elijah Price emerges as an orchestrator who holds secrets critical to both men.",
            "https://www.themoviedb.org/t/p/w220_and_h330_face/svIDTNUoajS8dLEo7EosxvyAsgJ.jpg",
            "18/01/2019",
            6.6
        )
        )

        movies.add(
            MovieEntity(
            "m9",
            "How to Train Your Dragon",
            "As the son of a Viking leader on the cusp of manhood, shy Hiccup Horrendous Haddock III faces a rite of passage: he must kill a dragon to prove his warrior mettle. But after downing a feared dragon, he realizes that he no longer wants to destroy it, and instead befriends the beast – which he names Toothless – much to the chagrin of his warrior father",
            "https://www.themoviedb.org/t/p/w220_and_h330_face/ij0xoc13hGhrYIlXGGuPXWTh3vi.jpg",
            "26/03/2010",
            7.8
        )
        )

        movies.add(
            MovieEntity(
            "m10",
            "Avengers: Infinity War",
            "Karena Avengers dan sekutunya terus melindungi dunia dari ancaman yang terlalu besar untuk ditangani oleh seorang pahlawan, bahaya baru telah muncul dari bayangan kosmik: Thanos. Seorang lalim penghujatan intergalaksi, tujuannya adalah untuk mengumpulkan semua enam Batu Infinity, artefak kekuatan yang tak terbayangkan, dan menggunakannya untuk menimbulkan kehendak memutar pada semua realitas. Segala sesuatu yang telah diperjuangkan oleh Avengers telah berkembang hingga saat ini - nasib Bumi dan keberadaannya sendiri tidak pernah lebih pasti.",
            "https://www.themoviedb.org/t/p/w220_and_h330_face/7WsyChQLEftFiDOVTGkv3hFpyyt.jpg",
            "27/04/2018",
            8.3
        )
        )

        movies.add(
            MovieEntity(
            "m11",
            "Mary Queen of Scots",
            "In 1561, Mary Stuart, widow of the King of France, returns to Scotland, reclaims her rightful throne and menaces the future of Queen Elizabeth I as ruler of England, because she has a legitimate claim to the English throne. Betrayals, rebellions, conspiracies and their own life choices imperil both Queens. They experience the bitter cost of power, until their tragic fate is finally fulfilled.",
            "https://www.themoviedb.org/t/p/w220_and_h330_face/b5RMzLAyq5QW6GtN9sIeAEMLlBI.jpg",
            "21/12/2018",
            6.6
        )
        )

        movies.add(
            MovieEntity(
            "m12",
            "Master Z: Ip Man Legacy",
            "Following his defeat by Master Ip, Cheung Tin Chi tries to make a life with his young son in Hong Kong, waiting tables at a bar that caters to expats. But it's not long before the mix of foreigners, money, and triad leaders draw him once again to the fight.",
            "https://www.themoviedb.org/t/p/w220_and_h330_face/8Huk4Glpqkbwmjd1uZ3KAxh0wdL.jpg",
            "26/12/2018",
            5.7
        )
        )

        movies.add(
            MovieEntity(
            "m13",
            "Mortal Engines",
            "Many thousands of years in the future, Earth’s cities roam the globe on huge wheels, devouring each other in a struggle for ever diminishing resources. On one of these massive traction cities, the old London, Tom Natsworthy has an unexpected encounter with a mysterious young woman from the wastelands who will change the course of his life forever.",
            "https://www.themoviedb.org/t/p/w220_and_h330_face/gLhYg9NIvIPKVRTtvzCWnp1qJWG.jpg",
            "14/12/2018",
            6.1
        )
        )

        movies.add(
            MovieEntity(
            "m14",
            "Overlord",
            "France, June 1944. On the eve of D-Day, some American paratroopers fall behind enemy lines after their aircraft crashes while on a mission to destroy a radio tower in a small village near the beaches of Normandy. After reaching their target, the surviving paratroopers realise that, in addition to fighting the Nazi troops that patrol the village, they also must fight against something else.",
            "https://www.themoviedb.org/t/p/w220_and_h330_face/13j4J1u3lYnEo1Ct3XOvXkeYbFe.jpg",
            "09/11/2018",
            6.7
        )
        )

        movies.add(
            MovieEntity(
            "m15",
            "Ralph Breaks the Internet",
            "Video game bad guy Ralph and fellow misfit Vanellope von Schweetz must risk it all by traveling to the World Wide Web in search of a replacement part to save Vanellope's video game, Sugar Rush. In way over their heads, Ralph and Vanellope rely on the citizens of the internet — the netizens — to help navigate their way, including an entrepreneur named Yesss, who is the head algorithm and the heart and soul of trend-making site BuzzzTube.",
            "https://www.themoviedb.org/t/p/w220_and_h330_face/6jBuc4l7ixM8S5PCcSYvGKDmIX9.jpg",
            "21/11/2018",
            7.2
        )
        )

        movies.add(
            MovieEntity(
            "m16",
            "Robin Hood",
            "A war-hardened Crusader and his Moorish commander mount an audacious revolt against the corrupt English crown.",
            "https://www.themoviedb.org/t/p/w220_and_h330_face/AiRfixFcfTkNbn2A73qVJPlpkUo.jpg",
            "21/11/2018",
            5.9
        )
        )

        movies.add(
            MovieEntity(
            "m17",
            "Serenity",
            "The quiet life of Baker Dill, a fishing boat captain who lives on the isolated Plymouth Island, where he spends his days obsessed with capturing an elusive tuna while fighting his personal demons, is interrupted when someone from his past comes to him searching for help.",
            "https://www.themoviedb.org/t/p/w220_and_h330_face/hgWAcic93phg4DOuQ8NrsgQWiqu.jpg",
            "25/01/2019",
            5.4
        )
        )

        movies.add(
            MovieEntity(
            "m18",
            "Spider-Man: Into the Spider-Verse",
            "Miles Morales is juggling his life between being a high school student and being a spider-man. When Wilson \"Kingpin\" Fisk uses a super collider, others from across the Spider-Verse are transported to this dimension.",
            "https://www.themoviedb.org/t/p/w220_and_h330_face/3cZn1k8x0bikrDKEy9ZKJ6Vdj30.jpg",
            "14/12/2018",
            8.4
        )
        )

        movies.add(
            MovieEntity(
            "m19",
            "T-34",
            "In 1944, a courageous group of Russian soldiers managed to escape from German captivity in a half-destroyed legendary T-34 tank. Those were the times of unforgettable bravery, fierce fighting, unbreakable love, and legendary miracles.",
            "https://www.themoviedb.org/t/p/w220_and_h330_face/dsrUtQdr4SnrSJwNuIjU90new4Y.jpg",
            "01/01/2019 ",
            6.8
        )
        )

        return movies
    }

    fun generateTvs(): List<TvShowEntity>{
        val tv = ArrayList<TvShowEntity>()

        tv.add(
            TvShowEntity(
            "tv1",
            "The Arrow",
            "Panah adalah menceritakan kembali petualangan dari legendaris DC pahlawan Green Arrow",
            "https://www.themoviedb.org/t/p/w220_and_h330_face/zfOb5lRt9SekVyl0gLfrXikQfxn.jpg",
            "Oktober 15, 2019",
            6.6
        )
        )

        tv.add(
            TvShowEntity(
            "tv2",
            "Doom Patrol",
            "The Doom Patrol’s members each suffered horrible accidents that gave them superhuman abilities — but also left them scarred and disfigured. Traumatized and downtrodden, the team found purpose through The Chief, who brought them together to investigate the weirdest phenomena in existence — and to protect Earth from what they find.",
            "https://www.themoviedb.org/t/p/w220_and_h330_face/nVN7Dt0Xr78gnJepRsRLaLYklbY.jpg",
            "Juni 25, 2020",
            7.6
        )
        )
        tv.add(
            TvShowEntity(
            "tv3",
            "ドラゴンボール",
            "Dahulu kala di pegunungan, seorang master pertempuran yang dikenal sebagai Gohan menemukan seorang bocah aneh yang ia beri nama Goku. Gohan membesarkannya dan melatih Goku dalam seni bela diri sampai dia mati. Bocah muda dan sangat kuat itu sendirian, tetapi mudah dikelola. Kemudian suatu hari, Goku bertemu dengan seorang gadis remaja bernama Bulma, yang pencariannya untuk bola naga membawanya ke rumah Goku. Bersama-sama, mereka berangkat untuk menemukan ketujuh bola naga dalam sebuah petualangan yang akan mengubah hidup Goku selamanya. Lihat bagaimana Goku bertemu teman-teman seumur hidupnya Bulma, Yamcha, Krillin, Master Roshi dan banyak lagi.",
            "https://www.themoviedb.org/t/p/w220_and_h330_face/dRdvbzJtQWcE6OkhR1BZrmN2hXp.jpg",
            "Februari 25, 1986",
            8.1
        )
        )
        tv.add(
            TvShowEntity(
            "tv4",
            "フェアリーテイル",
            "Lucy is a 17-year-old girl, who wants to be a full-fledged mage. One day when visiting Harujion Town, she meets Natsu, a young man who gets sick easily by any type of transportation. But Natsu isn't just any ordinary kid, he's a member of one of the world's most infamous mage guilds: Fairy Tail.",
            "https://www.themoviedb.org/t/p/w220_and_h330_face/h50lj7xO65qafNYZCrfQ7ztkMBD.jpg",
            "April 15, 2011",
            7.7
        )
        )
        tv.add(
            TvShowEntity(
            "tv5",
            "Family Guy",
            "Seri animasi animasi Freakin 'Sweet yang sakit, terpelintir, dan salah, menampilkan petualangan keluarga Griffin yang disfungsional. Peter yang kikuk dan Lois yang sudah lama menderita memiliki tiga anak. Stewie (bayi yang brilian tetapi sadis yang bertekad membunuh ibunya dan mengambil alih dunia), Meg (yang tertua, dan merupakan gadis yang paling tidak populer di kota) dan Chris (anak tengah, dia tidak terlalu cerdas tetapi memiliki hasrat untuk film ). Anggota terakhir keluarga itu adalah Brian - anjing yang bisa bicara dan lebih dari sekadar hewan peliharaan, ia menjaga Stewie, sementara menghirup Martinis dan memilah-milah masalah hidupnya sendiri.",
            "https://www.themoviedb.org/t/p/w220_and_h330_face/pneUOubHFd632I1lIO23J93Wn4A.jpg",
            "Mei 21, 2006",
            6.9
        )
        )
        tv.add(
            TvShowEntity(
            "tv6",
            "The Flash",
            "Setelah akselerator partikel menyebabkan badai aneh, Penyelidik CSI Barry Allen disambar petir dan jatuh koma. Beberapa bulan kemudian dia terbangun dengan kekuatan kecepatan super, memberinya kemampuan untuk bergerak melalui Central City seperti malaikat penjaga yang tak terlihat. Meskipun awalnya senang dengan kekuatan barunya, Barry terkejut menemukan bahwa dia bukan satu-satunya \"manusia meta\" yang diciptakan setelah ledakan akselerator - dan tidak semua orang menggunakan kekuatan baru mereka untuk kebaikan. Barry bermitra dengan S.T.A.R. Lab dan mendedikasikan hidupnya untuk melindungi yang tidak bersalah. Untuk saat ini, hanya beberapa teman dekat dan rekan yang tahu bahwa Barry secara harfiah adalah manusia tercepat, tetapi tidak lama sebelum dunia mengetahui apa yang menjadi Barry Allen ... The Flash.",
            "https://www.themoviedb.org/t/p/w220_and_h330_face/fki3kBlwJzFp8QohL43g9ReV455.jpg",
            "April 19, 2016.",
            7.6
        )
        )
        tv.add(
            TvShowEntity(
            "tv7",
            "Game of Thrones",
            "Tujuh keluarga bangsawan berjuang untuk menguasai tanah mitos Westeros. Gesekan antara rumah-rumah mengarah ke perang skala penuh. Semua sementara kejahatan yang sangat kuno terbangun di utara terjauh. Di tengah-tengah perang, perintah militer yang diabaikan, Night's Watch, adalah yang berdiri di antara alam manusia dan kengerian es di luarnya.",
            "https://www.themoviedb.org/t/p/w220_and_h330_face/gwPSoYUHAKmdyVywgLpKKA4BjRr.jpg",
            "Desember 5, 2010.",
            8.4
        )
        )
        tv.add(
            TvShowEntity(
            "tv8",
            "Gotham",
            "Semua orang tahu nama Komisaris Gordon. Dia adalah salah satu musuh terbesar dunia kejahatan, seorang pria yang reputasinya identik dengan hukum dan ketertiban. Tapi apa yang diketahui tentang kisah Gordon dan kenaikannya dari detektif pemula ke Komisaris Polisi? Apa yang diperlukan untuk menavigasi berbagai lapisan korupsi yang diam-diam memerintah Kota Gotham, tempat bertelurnya penjahat paling ikonik di dunia? Dan keadaan apa yang menciptakan mereka - persona yang lebih besar dari kehidupan yang akan menjadi Catwoman, The Penguin, The Riddler, Two-Face dan The Joker?",
            "https://www.themoviedb.org/t/p/w220_and_h330_face/4XddcRDtnNjYmLRMYpbrhFxsbuq.jpg",
            "Agustus 18, 2014.",
            7.5
        )
        )
        tv.add(
            TvShowEntity(
            "tv9",
            "Grey's Anatomy",
            "Ikuti kehidupan pribadi dan profesional sekelompok dokter di Rumah Sakit Gray Sloan Memorial di Seattle.",
            "https://www.themoviedb.org/t/p/w220_and_h330_face/mgOZSS2FFIGtfVeac1buBw3Cx5w.jpg",
            "Januari 8, 2006.",
            8.2
        )
        )
        tv.add(
            TvShowEntity(
            "tv10",
            "Hanna",
            "This thriller and coming-of-age drama follows the journey of an extraordinary young girl as she evades the relentless pursuit of an off-book CIA agent and tries to unearth the truth behind who she is. Based on the 2011 Joe Wright film.",
            "https://www.themoviedb.org/t/p/w220_and_h330_face/5nSSkcM3TgpllZ7yTyBOQEgAX36.jpg",
            "Maret 27, 2019",
            7.5
        )
        )
        tv.add(
            TvShowEntity(
            "tv11",
            "Marvel's Iron Fist",
            "Danny Rand resurfaces 15 years after being presumed dead. Now, with the power of the Iron Fist, he seeks to reclaim his past and fulfill his destiny.",
            "https://www.themoviedb.org/t/p/w220_and_h330_face/49XsMQpVsJEZfHiSipqXm9Wahf9.jpg",
            "Maret 17, 2017",
            6.5
        )
        )

        tv.add(
            TvShowEntity(
            "tv12",
            "ナルト 疾風伝",
            "Naruto Shippuuden adalah kelanjutan dari serial TV animasi asli Naruto. Kisah ini berkisah tentang Uzumaki Naruto yang lebih tua dan sedikit lebih matang dan upayanya untuk menyelamatkan temannya Uchiha Sasuke dari cengkeraman Shinobi seperti ular, Orochimaru. Setelah 2 setengah tahun, Naruto akhirnya kembali ke desanya Konoha, dan mulai mewujudkan ambisinya, meskipun itu tidak akan mudah, karena Ia telah mengumpulkan beberapa musuh (lebih berbahaya), seperti organisasi shinobi. ; Akatsuki.",
            "https://www.themoviedb.org/t/p/w220_and_h330_face/zAYRe2bJxpWTVrwwmBc00VFkAf4.jpg",
            "Februari 6, 2008.",
            8.6
        )
        )

        tv.add(
            TvShowEntity(
            "tv13",
            "NCIS",
            "From murder and espionage to terrorism and stolen submarines, a team of special agents investigates any crime that has a shred of evidence connected to Navy and Marine Corps personnel, regardless of rank or position.",
            "https://www.themoviedb.org/t/p/w220_and_h330_face/7j7bYIfUsfwGJv3ZyitmbSRbTua.jpg",
            "April 22, 2003",
            7.3
        )
        )

        tv.add(
            TvShowEntity(
            "tv14",
            "Riverdale",
            "Set in the present, the series offers a bold, subversive take on Archie, Betty, Veronica and their friends, exploring the surreality of small-town life, the darkness and weirdness bubbling beneath Riverdale’s wholesome facade.",
            "https://www.themoviedb.org/t/p/w220_and_h330_face/gskv297rlbyzLaTU1XZf8UBbxp0.jpg",
            "Januari 26, 2017",
            8.6
        )
        )

        tv.add(
            TvShowEntity(
            "tv15",
            "Shameless",
            "Chicagoan Frank Gallagher is the proud single dad of six smart, industrious, independent kids, who without him would be... perhaps better off. When Frank's not at the bar spending what little money they have, he's passed out on the floor. But the kids have found ways to grow up in spite of him. They may not be like any family you know, but they make no apologies for being exactly who they are.",
            "https://www.themoviedb.org/t/p/w220_and_h330_face/dLACsILtRnTgcxiPC2GKxfPrPvj.jpg",
            "Desember 12, 2010",
            7.9
        )
        )

        tv.add(
            TvShowEntity(
            "tv16",
            "Supergirl",
            "Twenty-four-year-old Kara Zor-El, who was taken in by the Danvers family when she was 13 after being sent away from Krypton, must learn to embrace her powers after previously hiding them. The Danvers teach her to be careful with her powers, until she has to reveal them during an unexpected disaster, setting her on her journey of heroism.",
            "https://www.themoviedb.org/t/p/w220_and_h330_face/vqBsgL9nd2v04ZvCqPzwtckDdFD.jpg",
            "Oktober 26, 2015",
            7.2
        )
        )

        tv.add(
            TvShowEntity(
            "tv17",
            "Supernatural",
            "Dua bersaudara mencari ayah mereka yang hilang, pria yang melatih mereka untuk menjadi prajurit melawan kejahatan supernatural",
            "https://www.themoviedb.org/t/p/w220_and_h330_face/h4R12N3WdXanb5Dm9vsOjaTpqjm.jpg",
            "September 13, 2005",
            8.2
        )
        )

        tv.add(
            TvShowEntity(
            "tv18",
            "The Simpsons",
            "Bertempat di Springfield, kota rata-rata di Amerika, pertunjukan ini berfokus pada kejenakaan dan petualangan sehari-hari keluarga Simpson; Homer, Marge, Bart, Lisa dan Maggie, serta ribuan pemain virtual. Sejak awal, serial ini telah menjadi ikon budaya pop, menarik ratusan selebriti menjadi bintang tamu. Acara ini juga menjadi terkenal karena satirnya yang tak kenal takut terhadap kehidupan politik, media, dan Amerika secara umum.",
            "https://www.themoviedb.org/t/p/w220_and_h330_face/k5UALlcA0EnviaCUn2wMjOWYiOO.jpg",
            "Desember 16, 1989",
            7.8
        )
        )

        tv.add(
            TvShowEntity(
            "tv19",
            "The Umbrella Academy",
            "A dysfunctional family of superheroes comes together to solve the mystery of their father's death, the threat of the apocalypse and more.",
            "https://www.themoviedb.org/t/p/w220_and_h330_face/uYHdIs5O8tiU5p6MvUPd2jElOH6.jpg",
            "Februari 15, 2019",
            8.7
        )
        )

        tv.add(
            TvShowEntity(
            "tv20",
            "The Walking Dead",
            "Sheriff's deputy Rick Grimes awakens from a coma to find a post-apocalyptic world dominated by flesh-eating zombies. He sets out to find his family and encounters many other survivors along the way.",
            "https://www.themoviedb.org/t/p/w220_and_h330_face/bjU4tLlyp8W4yTB0Hqn8J1IDUnD.jpg",
            "Oktober 31, 2010",
            8.0
        )
        )

        return tv
    }
}