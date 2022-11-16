## I declare that my work contains no examples of misconduct, such as plagiarism, or collusion.
# Any code taken from other sources is referenced within my code solution.

# Student ID: w1953615
# Date: 05th November 2022

run = "y"
outcomes = {}

progress = 0
trailer = 0
retriever = 0
excluded = 0

fileWrite = open("data.txt", "w")


def inputValidation():
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
    progression = (
        "Progress",
        "Progress (module trailer)",
        "Module retriever",
        "Exclude",
    )
    print(progression[value])

    line = {}
    line["type"] = progression[value]
    line["values"] = list

    outcomes[id] = line

    fileWrite.writelines(listToString(list) + "\n")


def printStars(value):
    return value * "*"


def listToString(value):
    type = str(value[0])
    numbers = str(value[1:])
    return type + numbers[1:-1]


while run.lower() == "y":
    list = []
    print()

    id = input("Student ID: ")
    check = inputValidation()

    if check != None:
        print(check)
        break

    if sum(list) != 120:
        print("Total incorrect.")
        break
    elif list[0] == 120:
        storeOutcomes(0)
        progress += 1
    elif list[2] >= 80:
        storeOutcomes(3)
        excluded += 1
    elif list[0] == 100:
        storeOutcomes(1)
        trailer += 1
    else:
        storeOutcomes(2)
        retriever += 1

    print("\nWould you like to enter another set of data?")
    run = input("Enter 'y' for yes or 'q' to quit and view results: ")

    if run.lower() == "q":
        fileWrite.close()
        print(
            f"""
----------------------------------------------------------------
Histogram
Progress {progress}   : {printStars(progress)}
Trailer {trailer}    : {printStars(trailer)}
Retriever {retriever}  : {printStars(retriever)}
Excluded {excluded}   : {printStars(excluded)}
{len(outcomes)} outcomes in total.
----------------------------------------------------------------
"""
        )
        print("Part 4:")
        for (key, value) in outcomes.items():
            print(f"{key} : {value['type']} - {str(value['values'])[1:-1]}")

    elif run.lower() == "y":
        continue
    else:
        print("Invalid input")
        break
