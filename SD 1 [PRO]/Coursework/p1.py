list = []
run = "y"
total = 0

progress = 0
trailer = 0
retriever = 0
excluded = 0


def stars(value):
    h = ""
    for i in range(value):
        h += "*"
    return h


while run.lower() == "y":
    total += 1
    list.clear()
    print()
    for type in ["pass", "defer", "fail"]:
        try:
            credits = int(input(f"Please enter your credits at {type}: "))
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
        print("Progress")
        progress += 1
    elif list[2] >= 80:
        print("Exclude")
        excluded += 1
    elif list[0] == 100:
        print("Progress (module trailer)")
        trailer += 1
    else:
        print("Do not progress – module retriever")
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
{total} outcomes in total.
----------------------------------------------------------------
"""
        )
else:
    print("Invalid input")
