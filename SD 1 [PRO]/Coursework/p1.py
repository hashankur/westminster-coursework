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


def storeOutcomes(value):
    progression = [
        "Progress",
        "Progress (module trailer)",
        "Do not Progress – module retriever",
        "Exclude",
    ]
    print(progression[value])
    list.insert(0, progression[value])
    outcomes.append(list)


def stars(value):
    h = ""
    for i in range(value):
        h += "*"
    return h


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
        print(
            f"""
---------------------------------------------------------------
Histogram
Progress {progress}   : {stars(progress)}
Trailer {trailer}    : {stars(trailer)}
Retriever {retriever}  : {stars(retriever)}
Excluded {excluded}   : {stars(excluded)}
{len(outcomes)} outcomes in total.
----------------------------------------------------------------
"""
        )
        print("Part 2:")
        for i in outcomes:
            type = str(i[0])
            numbers = str(i[1:])
            print(type + " - " + numbers[1:-1])

    elif run.lower() == "y":
        continue
    else:
        print("Invalid input")
        break
