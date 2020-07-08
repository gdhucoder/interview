import sys 

script, encoding, error = sys.argv

def main(language_file, encoding, errors):
    line = language_file.readline()
    
    if line:
        print_line(line, encoding, errors)
        return main(language_file, encoding, errors)


def print_line(line, encoding, errors):
    # delete /n
    next_lang = line.strip()
    # 编码字符串
    raw_bytes = next_lang.encode(encoding, errors = errors)
    # 解码字符串
    cooked_string = raw_bytes.decode(encoding, errors = errors)

    print(raw_bytes, "<===>", cooked_string)


languages = open("languages.txt", encoding="utf-8")

main(languages, encoding, error)