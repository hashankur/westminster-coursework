let count = 0;

// Q1
let i1 = parseInt(prompt("What is 8 + 7?"));
let q1 = document.getElementById("question1");
let a1 = document.getElementById("answer1");
let f1 = document.getElementById("feedback1");

q1.innerHTML = " 8 + 7 = ... ";
a1.innerHTML = i1;

if (i1 == 15) {
    f1.innerHTML = "&checkmark;";
    f1.classList = "correct";
    count++;
} else {
    f1.innerHTML = "&cross;" + "The correct answer is 15";
    f1.classList = "incorrect";
}

// Q2
let i2 = prompt("What is 18 - 8?");
let q2 = document.getElementById("question2");
let a2 = document.getElementById("answer2");
let f2 = document.getElementById("feedback2");

q2.innerHTML = " 18 - 8 = ... ";
a2.innerHTML = i2;

if (i2 == 10) {
    f2.innerHTML = "&checkmark;";
    f2.classList = "correct";
    count++;
} else {
    f2.innerHTML = "&cross;" + "The correct answer is 15";
    f2.classList = "incorrect";
}
