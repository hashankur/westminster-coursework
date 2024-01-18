// Function to change the color scheme
const setTheme = (theme) => {
    document.documentElement.className = theme;
    localStorage.setItem("theme", theme);
};

// Save theme preference
// If null, set the theme to dark by default
if (localStorage.getItem("theme") === null) {
    setTheme("dark");
} else {
    setTheme(localStorage.getItem("theme"));
}

const randomTheme = () => {
    const themes = ["dark", "light", "lorem"];
    const random = themes[Math.floor(Math.random() * themes.length)];
    setTheme(random);
};

const changeFontSize = () => {
    document.getElementById("content").classList.toggle("font-size");
    // let btn = document.getElementById("toggle-size").innerHTML("lorem");
    // if (btn.innerHTML == "Close Curtain") btn.value = "Open Curtain";
    // else btn.value = "Close Curtain";
};
