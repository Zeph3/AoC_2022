def read_file_and_split(path, delimiter):
    text_file = open(path)
    data = text_file.read()
    split_data = data.split(delimiter)
    text_file.close()
    return split_data
