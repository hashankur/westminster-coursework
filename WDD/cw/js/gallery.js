const imgs = [
    {
        name: "Spider-Man: No Way Home (2021)",
        imgPath:
            "https://m.media-amazon.com/images/M/MV5BZWMyYzFjYTYtNTRjYi00OGExLWE2YzgtOGRmYjAxZTU3NzBiXkEyXkFqcGdeQXVyMzQ0MzA0NTM@._V1_UY209_CR0,0,140,209_AL_.jpg",
        genres: "Action, Adventure, Fantasy",
        desc: "With Spider-Man's identity now revealed, Peter asks Doctor Strange for help. When a spell goes wrong, dangerous foes from other worlds start to appear, forcing Peter to discover what it truly means to be Spider-Man.",
    },
    {
        name: "Tenet (2020)",
        imgPath:
            "https://m.media-amazon.com/images/M/MV5BMzU3YWYwNTQtZTdiMC00NjY5LTlmMTMtZDFlYTEyODBjMTk5XkEyXkFqcGdeQXVyMTkxNjUyNQ@@._V1_UY209_CR0,0,140,209_AL_.jpg",
        genres: "Action, Sci-Fi, Thriller",
        desc: "Armed with only one word - Tenet - and fighting for the survival of the entire world, the Protagonist journeys through a twilight world of international espionage on a mission that will unfold in something beyond real time.",
    },
    {
        name: "Avengers: Endgame (2019)",
        imgPath:
            "https://m.media-amazon.com/images/M/MV5BMTc5MDE2ODcwNV5BMl5BanBnXkFtZTgwMzI2NzQ2NzM@._V1_UY209_CR0,0,140,209_AL_.jpg",
        genres: "Action, Adventure, Drama",
        desc: "After the devastating events of Avengers: Infinity War (2018), the universe is in ruins. With the help of remaining allies, the Avengers assemble once more in order to reverse Thanos' actions and restore balance to the universe.",
    },
    {
        name: "Avengers: Infinity War (2018)",
        imgPath:
            "https://m.media-amazon.com/images/M/MV5BMjMxNjY2MDU1OV5BMl5BanBnXkFtZTgwNzY1MTUwNTM@._V1_UY209_CR0,0,140,209_AL_.jpg",
        genres: "Action, Adventure, Sci-Fi",
        desc: "The Avengers and their allies must be willing to sacrifice all in an attempt to defeat the powerful Thanos before his blitz of devastation and ruin puts an end to the universe.",
    },
    {
        name: "Coco (I) (2017)",
        imgPath:
            "https://m.media-amazon.com/images/M/MV5BYjQ5NjM0Y2YtNjZkNC00ZDhkLWJjMWItN2QyNzFkMDE3ZjAxXkEyXkFqcGdeQXVyODIxMzk5NjA@._V1_UY209_CR3,0,140,209_AL_.jpg",
        genres: "Animation, Adventure, Comedy",
        desc: "Aspiring musician Miguel, confronted with his family's ancestral ban on music, enters the Land of the Dead to find his great-great-grandfather, a legendary singer.",
    },
    {
        name: "Your Name. (2016)",
        imgPath:
            "https://m.media-amazon.com/images/M/MV5BODRmZDVmNzUtZDA4ZC00NjhkLWI2M2UtN2M0ZDIzNDcxYThjL2ltYWdlXkEyXkFqcGdeQXVyNTk0MzMzODA@._V1_UY209_CR0,0,140,209_AL_.jpg",
        genres: "Animation, Drama, Fantasy",
        desc: "Two strangers find themselves linked in a bizarre way. When a connection forms, will distance be the only thing to keep them apart?",
    },
    {
        name: "Mad Max: Fury Road (2015)",
        imgPath:
            "https://m.media-amazon.com/images/M/MV5BN2EwM2I5OWMtMGQyMi00Zjg1LWJkNTctZTdjYTA4OGUwZjMyXkEyXkFqcGdeQXVyMTMxODk2OTU@._V1_UY209_CR0,0,140,209_AL_.jpg",
        genres: "Action, Adventure, Sci-Fi",
        desc: "In a post-apocalyptic wasteland, a woman rebels against a tyrannical ruler in search for her homeland with the aid of a group of female prisoners, a psychotic worshiper, and a drifter named Max.",
    },
    {
        name: "Interstellar (2014)",
        imgPath:
            "https://m.media-amazon.com/images/M/MV5BZjdkOTU3MDktN2IxOS00OGEyLWFmMjktY2FiMmZkNWIyODZiXkEyXkFqcGdeQXVyMTMxODk2OTU@._V1_UY209_CR0,0,140,209_AL_.jpg",
        genres: "Adventure, Drama, Sci-Fi",
        desc: "A team of explorers travel through a wormhole in space in an attempt to ensure humanity's survival.",
    },
    {
        name: "12 Years a Slave (2013)",
        imgPath:
            "https://m.media-amazon.com/images/M/MV5BMjExMTEzODkyN15BMl5BanBnXkFtZTcwNTU4NTc4OQ@@._V1_UY209_CR0,0,140,209_AL_.jpg",
        genres: "Biography, Drama, History",
        desc: " In the antebellum United States, Solomon Northup, a free black man from upstate New York, is abducted and sold into slavery.",
    },
    {
        name: "Django Unchained (2012)",
        imgPath:
            "https://m.media-amazon.com/images/M/MV5BMjIyNTQ5NjQ1OV5BMl5BanBnXkFtZTcwODg1MDU4OA@@._V1_UY209_CR0,0,140,209_AL_.jpg",
        genres: "Drama, Western",
        desc: "With the help of a German bounty hunter, a freed slave sets out to rescue his wife from a brutal Mississippi plantation owner.",
    },
    {
        name: "The Help (2011)",
        imgPath:
            "https://m.media-amazon.com/images/M/MV5BMTM5OTMyMjIxOV5BMl5BanBnXkFtZTcwNzU4MjIwNQ@@._V1_UY209_CR0,0,140,209_AL_.jpg",
        genres: "Drama",
        desc: " An aspiring author during the civil rights movement of the 1960s decides to write a book detailing the African American maids' point of view on the white families for which they work, and the hardships they go through on a daily basis.",
    },
    {
        name: "Inception (2010)",
        imgPath:
            "https://m.media-amazon.com/images/M/MV5BMjAxMzY3NjcxNF5BMl5BanBnXkFtZTcwNTI5OTM0Mw@@._V1_UY209_CR0,0,140,209_AL_.jpg",
        genres: "Action, Adventure, Sci-Fi",
        desc: "A thief who steals corporate secrets through the use of dream-sharing technology is given the inverse task of planting an idea into the mind of a CEO.",
    },
];

// Gallery loop
let wrapper = document.getElementById("gallery");
let gallery = "";

for (var i = 0; i < imgs.length; i++) {
    gallery += `<img
        id="isSelected"
        src="${imgs[i].imgPath}"
        alt="${imgs[i].name}"
    />`;
}

wrapper.innerHTML = gallery;

// Details loop
let wrapperD = document.getElementById("details");
let details = "";

for (var i = 0; i < imgs.length; i++) {
    details += `<h2>${imgs[i].name}</h2>
                <span id="demo">${imgs[i].genres}</span>`;
}

wrapperD.innerHTML = details;

// Hover selection
document.getElementById("isSelected").onmouseover = function() {
    mouseOver();
};
document.getElementById("isSelected").onmouseout = function() {
    mouseOut();
};

function mouseOver() {
    document.getElementById("demo").style.color = "red";
}

function mouseOut() {
    document.getElementById("demo").style.color = "white";
}
