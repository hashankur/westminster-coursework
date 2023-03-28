const operator = ["+", "-", "*", "/"];

let questions = [];
let answers = [];
let input = [];
let feedback = [];

const main = document.getElementById("main");

for (let i = 0; i < 10; i++) {
    let n1 = Math.floor(Math.random() * 10);
    let n2 = Math.floor(Math.random() * 10);
    let op = operator[Math.floor(Math.random() * 3)];

    questions[i] = `What is ${n1} ${op} ${n2}? ... `;
    answers[i] = eval(n1 + op + n2);
    input[i] = parseInt(prompt(`Question ${i + 1}:\nWhat is ${n1} ${op} ${n2}`));

    if (input[i] == answers[i]) {
        feedback[i] = true;
    } else {
        feedback[i] = false;
    }
}

let content = "";

for (let i = 0; i < 10; i++) {
    content += `
    <p>
      <strong>Question ${i + 1}:</strong>
      <span id="question">${questions[i]}</span>
      <span id="answer">${input[i]}</span>
      <span id="feedback${i}">${feedback[i]}</span>
    </p>`;
}

main.innerHTML = content;

for (let i = 0; i < 10; i++) {
    let item = document.getElementById(`feedback${i}`);
    if (feedback[i]) {
        item.classList = "correct";
        item.innerHTML = "&checkmark;";
    } else {
        item.classList = "incorrect";
        item.innerHTML = "&cross;" + `The correct answer is ${answers[i]}`;
    }
}
