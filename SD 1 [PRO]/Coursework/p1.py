# Author:
# Date: ?

#

# Psuedo code ?

run = "y"
outcomes = []

progress = 0
trailer = 0
retriever = 0
excluded = 0

fileWrite = open("data.txt", "w")


def storeOutcomes(value):
    progression = [
        "Progress",
        "Progress (module trailer)",
        "Module retriever",
        "Exclude",
    ]
    print(progression[value])
    list.insert(0, progression[value] + " - ")
    outcomes.append(list)

    fileWrite.writelines(listToString(list) + "\n")


def printStars(value):
    h = ""
    for i in range(value):
        h += "*"
    return h


def listToString(value):
    type = str(value[0])
    numbers = str(value[1:])
    return type + numbers[1:-1]


while run.lower() == "y":
    list = []
    print()
    for type in ["PASS", "DEFER", "FAIL"]:
        try:
            credits = int(input(f"Enter your total {type} credits: "))
        except ValueError:
            print("Integer required")
            break
        else:
            if credits not in range(0, 121, 20):
                print("Out of range.")
                break
            else:
                list.append(credits)

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
        print("Part 2:")
        for outcome in outcomes:
            print(listToString(outcome))
        print()

        print("Part 3:")
        fileOpen = open("data.txt", "r")
        print(fileOpen.read())

    elif run.lower() == "y":
        continue
    else:
        print("Invalid input")
        break
