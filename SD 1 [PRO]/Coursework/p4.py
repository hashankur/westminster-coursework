# I declare that my work contains no examples of misconduct, such as plagiarism, or collusion.
# Any code taken from other sources is referenced within my code solution.

# Student ID: w1953615, 20221026
# Date: 05th November 2022

run = "y"
creditList = []
outcomes = {}


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
    print(progression[value])

    line = {}
    line["type"] = progression[value]
    line["values"] = creditList

    outcomes[id] = line


def anotherSetOfData():
    """Get choice from user to continue or exit"""
    global run  # To change variable outside function scope
    run = input("Enter 'y' for yes or 'q' to quit and view results: ").lower()
    print()

    if run in ("y", "q"):
        if run == "q":
            part4()
    else:
        print("Invalid input")
        anotherSetOfData()


def part4():
    """Prints outcomes stored in a dictionary"""
    print("Part 4:")
    for (key, value) in outcomes.items():
        # Prints (id : type - credits)
        print(f"{key} : {value['type']} - {str(value['values'])[1:-1]}")


def main():
    while run == "y":
        global creditList
        global id
        creditList = []

        id = input("Enter Student ID: ")
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

        print("\nWould you like to enter another set of data?")

        anotherSetOfData()


main()

# https://www.freecodecamp.org/news/python-global-variables-examples/
