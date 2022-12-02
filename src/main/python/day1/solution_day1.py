import os
import file_reader


def main():
    raw_data = file_reader.read_file_and_split("../../source/day1/calories_list", os.linesep * 2)
    cleaned_transformed_data = transform_to_arr(raw_data)
    print_result(cleaned_transformed_data)


def transform_to_arr(data):
    split_data_int = [[int(inner) for inner in line.split("\n")] for line in data]
    return [sum(inner) for inner in split_data_int]


def print_result(data):
    print("Highest sum value:", max(data))
    print("Highest 3 sum:", sum(sorted(data)[-3:]))


main()
