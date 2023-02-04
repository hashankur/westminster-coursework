# Author: Hashan
# Date 29 Nov 2022

# Username generator

students = {
    60254: "John Smith",
    60255: "Gert Du-Cart",
    60256: "Sun Po",
    60257: "Bort Woods",
    60258: "Andrew Butters",
    60259: "Betty Ho",
}

usernames = {}

for key, value in students.items():
    names = value.lower().replace("-", "").split()
    username = names[0][0] + names[1][:4]
    usernames[key] = username.ljust(5, "0")

for key, value in usernames.items():
    print(f"Username of student {key} is {value}")
