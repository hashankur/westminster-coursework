def outcome(type):
    while True:
        try:
            credits = int(input(f"Please enter your credits at {type}: "))
        except ValueError:
            print("Integer required")
        else:
            if credits not in range(0, 121, 20):
                print("Out of range.")
                continue
            else:
                return credits


total = 0

while True:
    for run in ["pass", "defer", "fail"]:
        total += outcome(run)

    if total > 120:
        print("Total incorrect.")
    else:
        print("Progress (module trailer)")

    print("Would you like to enter another set of data?")
    end = input("Enter 'y' for yes or 'q' to quit and view results: ")

    if end.lower() == "y":
        continue
    elif end.lower == "q":
        break
    else:
        print("Invalid input")
