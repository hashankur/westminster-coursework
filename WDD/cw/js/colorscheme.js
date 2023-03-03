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

const changeFontSize = () => {
    document.getElementsByName("body");
};
