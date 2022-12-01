import os


def main():
    print(read_file())


def read_file():
    text_file = open("../source/day1/calories_list", "r")
    data = text_file.read()
    text_file.close()
    return data


main()
