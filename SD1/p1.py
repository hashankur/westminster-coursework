# I declare that my work contains no examples of misconduct, such as plagiarism, or collusion.
# Any code taken from other sources is referenced within my code solution.

# Student ID: w1953615, 20221026
# Date: 05th November 2022

run = "y"
creditList = []
outcomes = []
count = [0, 0, 0, 0]

fileWrite = open("data.txt", "w")


def inputValidation():
    """Validate inputs and create a list of credits"""
    for type in ("PASS", "DEFER", "FAIL"):
        try:
            credits = int(input(f"Enter your total {type} credits: "))
        except ValueError:
            return "Integer required"
        else:
            if credits not in range(0, 121, 20):
                return "Out of range"
            else:
                creditList.append(credits)


def storeOutcomes(value):
    """Get progression type, credits then append to outcomes list"""
    progression = (
        "Progress",
        "Progress (module trailer)",
        "Module retriever",
        "Exclude",
    )
    count[value] += 1
    print(progression[value])
    creditList.insert(0, progression[value] + " - ")
    outcomes.append(creditList)

    fileWrite.write(listToString(creditList) + "\n")


def listToString(value):
    """Convert list to string and return formatted line"""
    type, *credits = value
    return type + str(credits)[1:-1]


def anotherSetOfData():
    """Get choice from user to continue or exit"""
    global run  # To change variable outside function scope
    run = input("Enter 'y' for yes or 'q' to quit and view results: ").lower()
    print()

    if run in ("y", "q"):
        if run == "q":
            fileWrite.close()
            histogram()
            part2()
            part3()
    else:
        print("Invalid input")
        anotherSetOfData()


def histogram():
    """Prints histogram using count"""
    print("-" * 60)
    print("Histogram")
    for (i, type) in enumerate(("Progress", "Trailer", "Retriever", "Excluded")):
        # Prints (type - count : stars)
        print(f"{type + ' ' + str(count[i]):12} : {'*' * count[i]}")
    print(f"{len(outcomes)} outcomes in total.")
    print("-" * 60 + "\n")


def part2():
    """Prints outcomes stored in a list"""
    print("Part 2:")
    for outcome in outcomes:
        print(listToString(outcome))
    print()


def part3():
    """Prints outcomes stored in a file"""
    print("Part 3:")
    fileOpen = open("data.txt", "r")
    print(fileOpen.read())


def main():
    userInputValid = False
    userInput = None

    print("""Who is using this program?
    1) Student
    2) Staff member""")

    while not userInputValid:
        try:
            userInput = int(input("Enter option '1' or '2': "))
        except ValueError:
            print("Invalid input\n")
        else:
            if userInput in (1, 2):
                print()
                userInputValid = not userInputValid
            else:
                print("Invalid input\n")
                continue

    while run == "y":
        global creditList
        creditList = []

        error = inputValidation()
        if error is not None:
            print(error)
            continue

        if sum(creditList) != 120:
            print("Total incorrect\n")
            continue
        elif creditList[0] == 120:
            storeOutcomes(0)  # Progress
        elif creditList[2] >= 80:
            storeOutcomes(3)  # Exclude
        elif creditList[0] == 100:
            storeOutcomes(1)  # Trailer
        else:
            storeOutcomes(2)  # Retriever

        if userInput == 1:
            break
        else:
            print("\nWould you like to enter another set of data?")

            anotherSetOfData()


main()

# https://www.freecodecamp.org/news/python-global-variables-examples/
