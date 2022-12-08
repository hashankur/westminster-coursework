import random

MIN = 2
MAX = 9

answerValid = False
exitPrompt = None
score = 0

while exitPrompt != "":
    n1 = random.randint(MIN, MAX)
    n2 = random.randint(MIN, MAX)

    while not answerValid:
        answer = input(f"What is {n1} + {n2}? ")
        try:
            if answer == "":
                exitPrompt = answer
                break
            else:
                answer = int(answer)
        except ValueError:
            print("Invalid input")
        else:
            isCorrect = n1 + n2 == answer
            if isCorrect:
                print("Yes, you got it!")
                score += 2
            else:
                print(f"No, sorry, the correct answer was: {n1 + n2}")
                score -= 1
            print(f"Your current score is: {score}\n")
            break
print("Thanks for playing")
