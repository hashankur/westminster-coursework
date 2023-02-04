#!/usr/bin/env python3

something = input("Type something here : ")

if something.isupper():
    print("All the characters are uppercase characters")

if something.islower():
    print("All the characters are lowercase characters")

if something.isalpha():
    print("All the characters alphabet characters")

if something.isdigit():
    print("All the characters are digits")

if something.isspace():
    print("All the characters are white space characters")

if something.startswith("s"):
    print(something, "Starts with 's'")

if something.endswith("t"):
    print(something, 'ends with a "t"')

if something.count("e") > 2:
    print(something, "atleast contains 2 'e'")
