while True:
    try:
        option = int(input("Select an option (1, 2, 3, 4): "))
    except ValueError:
        print("Enter integer")
    else:
        if option == 1:
            print("1 selected")
        elif option == 2:
            print("2 selected")
        elif option == 3:
            print("3 selected")
        elif option == 4:
            print("Quit selected")
            break
        else:
            print("Option not recognized")
