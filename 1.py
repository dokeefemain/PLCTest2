def check_list(arry,c):
    for i in arry:
        if c == i:
            return True
    return False


def getILN(lex_file):
    identifiers = []
    literal = []
    non_alph = []
    curr = ""
    check_alpha = False
    #Non-Alphanumeric special symbols
    special = ['+','=','-','*','/','%','&','|','{','}','(',')']
    special2 = ['=','&','|']
    test = False
    for i in lex_file:
        #certain special symbols can be 2 characters long like &&,+=,-=, or || this checks for that
        if check_list(special2,i) and check_alpha:
            test = True
            non_alph[len(non_alph)-1] = non_alph[len(non_alph)-1]+str(i)
        check_alpha = False
        #check literal with special
        if test == False and check_list(special,i):
            literal.append(curr)
            non_alph.append(i)
            curr = ""
            if i == '+' or i== '-' or i == '&' or i == '|':
                check_alpha = True
        #check literal with white space
        elif test == False and i == ' ':
            literal.append(curr)
            curr = ""
        #check ID
        elif test == False and i.isalnum() == False and check_list(special, i) == False and i!='.' and i!='_':
            identifiers.append(curr)
            curr = ""
        if i.isalnum() or i=='.':
            curr = curr+i
        test = False
    return identifiers, literal, non_alph 

def octal(curr):
    arry = ['0','1','2','3','4','5','6','7']
    for i in curr:
        if check_list(arry,i) != True:
            return False
    return True
def hexx(curr):
    arry = ["0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "a", "A", "b", "B", "c", "C", "d", "D", "e", "E", "f", "F"]
    for i in curr:
        if check_list(arry,i) != True:
            return False
    return True
def binary(curr):
    for i in curr:
        if i != '1' or i != '0':
            return False
    return True
def getLiteral(literal):
    #assuming that strings and chars are only A-Z and a-z
    strings = []
    ints = []
    chars = []
    floats = []
    for i in literal:
        #chars
        if len(i) == 1 and i.isalpha():
            chars.append(i)
        #strings
        elif i.isalpha():
            strings.append(i)
        #ints 
        # i.isnumeric()-checks if it's just a decimal
        #remove u or l if that is present at the last 2 spots
        # orctal- starts with 0 then 0-7
        # hex- starts with 0x then "0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "a", "A", "b", "B", "c", "C", "d", "D", "e", "E", "f", "F"
        # binary- starts with 0b then 0 or 1
        else:
            curr = i
            test = False
            #removing u or l if present
            if i[-1:] == 'u' or i[-1:] == 'U' or i[-1:] == 'l' or i[-1:] == 'L':
                curr = curr[:-1]
            if i[-1:] == 'u' or i[-1:] == 'U' or i[-1:] == 'l' or i[-1:] == 'L':
                curr = curr[:-1]
            #test octal
            if curr[0] == '0' and curr[1] != 'x' and curr[1] != 'X' and curr[1] != 'b' and curr[1] != 'B':
                test = octal(curr)
            #test decimal
            elif curr.isnumeric():
                test = True
            #test hex
            elif curr[0] == '0' and (curr[1] == 'x' or curr[1] == 'X'):
                test = hexx(curr[2:])
            #test binary
            elif curr[0] == '0' and (curr[1] == 'b' or curr[1] == 'B'):
                test = binary(curr[2:])
            if test:
                ints.append(i)
            #if it's not string, integer, or char and it is a literal then it will be floating point
            else:
                floats.append(i)
    return strings,chars,ints,floats

            


def main():
    with open("lexems.txt",'r') as f:
        lex_file = f.read()
    indentifiers, literal, non_alph = getILN(lex_file)
    print("Identifier Tokens:",indentifiers)
    print("Literal Tokens:",literal)
    print("Special Symbol Tokens:",non_alph)
    strings,chars,ints,floats = getLiteral(literal)
    print("String Tokens:",strings)
    print("Character Tokens:",chars)
    print("Integer Tokens:",ints)
    print("Floating point Tokens:",floats)

main()