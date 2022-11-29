## I declare that my work contains no examples of misconduct, such as plagiarism, or collusion.
# Any code taken from other sources is referenced within my code solution.

# Student ID: w1953615
# Date: 05th November 2022

run = "y"
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
                return "Out of range."
            else:
                list.append(credits)


def storeOutcomes(value):
    """Get progression type, credits and append to outcomes list"""
    progression = (
        "Progress",
        "Progress (module trailer)",
        "Module retriever",
        "Exclude",
    )
    count[value] += 1
    print(progression[value])
    list.insert(0, progression[value] + " - ")
    outcomes.append(list)

    fileWrite.writelines(listToString(list) + "\n")


def listToString(value):
    """Convert list to string and return formatted line"""
    type = str(value[0])
    numbers = str(value[1:])
    return type + numbers[1:-1]


while run == "y":
    list = []

    check = inputValidation()
    if check != None:
        print(check)
        continue

    if sum(list) != 120:
        print("Total incorrect.")
        continue
    elif list[0] == 120:
        storeOutcomes(0)  # Progress
    elif list[2] >= 80:
        storeOutcomes(3)  # Exclude
    elif list[0] == 100:
        storeOutcomes(1)  # Trailer
    else:
        storeOutcomes(2)  # Retriever

    print("\nWould you like to enter another set of data?")
    run = input("Enter 'y' for yes or 'q' to quit and view results: ").lower()
    print()

    if run == "y":
        continue

    elif run == "q":
        fileWrite.close()

        print("-" * 60)
        print("Histogram")
        lineType = ("Progress", "Trailer", "Retriever", "Excluded")
        for i in lineType:
            # Get count using index according to lineType
            index = count[lineType.index(i)]
            # Print (type, count : stars)
            print(f"{i + ' ' + str(index):12} : {'*' * index}")
        print(f"{len(outcomes)} outcomes in total.")
        print("-" * 60 + "\n")

        print("Part 2:")
        for outcome in outcomes:
            print(listToString(outcome))
        print()

        print("Part 3:")
        fileOpen = open("data.txt", "r")
        print(fileOpen.read())

    else:
        fileWrite.close()
        print("Invalid input")
        continue
