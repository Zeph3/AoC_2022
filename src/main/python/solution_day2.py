import os
import file_reader
from enum import Enum


class Strategy(Enum):
    Rock = 1
    Paper = 2
    Scissor = 3



def main():
    raw_data = file_reader.read_file_and_split("../source/day2/rock_paper_scissor", os.linesep)
    strategies = transform_to_arr_of_strategy(raw_data)
    result = sum([calculate_result(match) for match in strategies])
    print("Result of following strategies:", result)
    # cleaned_transformed_data = transform_to_arr(raw_data)
    # print_result(cleaned_transformed_data)


def transform_to_arr_of_strategy(data):
    return [(string_to_strategy(line[0]), string_to_strategy(line[2])) for line in data]


def string_to_strategy(strategy_string):
    match strategy_string:
        case "A" | "X":
            return Strategy.Rock
        case "B" | "Y":
            return Strategy.Paper
        case "C" | "Z":
            return Strategy.Scissor


def calculate_result(matchup):
    return match_to_result(matchup) + matchup[1].value


def match_to_result(matchup):
    win = 6
    draw = 3
    loss = 0

    match matchup:
        case (Strategy.Rock, Strategy.Paper):
            return win
        case (Strategy.Rock, Strategy.Scissor):
            return loss
        case (Strategy.Paper, Strategy.Scissor):
            return win
        case (Strategy.Paper, Strategy.Rock):
            return loss
        case (Strategy.Scissor, Strategy.Rock):
            return win
        case (Strategy.Scissor, Strategy.Paper):
            return loss
        case _:
            return draw


main()

