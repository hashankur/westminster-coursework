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

num = 0


def storeOutcomes(value):
    progression = [
        "Progress",
        "Progress (module trailer)",
        "Module retriever",
        "Exclude",
    ]
    print(progression[value])

    line = {}
    line["type"] = progression[value]
    line["values"] = list

    outcomes.update({num: line})


def printStars(value):
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

    num += 1

    print("\nWould you like to enter another set of data?")
    run = input("Enter 'y' for yes or 'q' to quit and view results: ")

    if run.lower() == "q":
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
        for i in outcomes.values():
            print(f"{i['type']} - {str(i['values'])[1:-1]}")

    elif run.lower() == "y":
        continue
    else:
        print("Invalid input")
        break
