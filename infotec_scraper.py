import mechanize
import cookielib
import datetime
import re
import random
import datetime
import csv

NOT_FOUND_MESSAGE = 'Not set'
NUMER_OF_SCHOOLS = 10


def regex_search(regex, regex_string):
    """does a regex search on 'regex_string' and returns the results

    >>> regex_search(r'...a', 'FJSIfdsa')
    'fdsa'
    """
    match = re.search(regex, regex_string)
    if match is not None:
        return match.group()

def print_alert(text):
    """prints 'text' surrounded by whitespace"""
    print "\n\n\n\n"
    print text
    print "\n\n\n\n"

def does_nothing(text):
    """does nothing"""
    pass

def is_regex_in_string(regex, regex_string):
    """checks if a regex match is in string

    >>> is_regex_in_string(r'333', 'jaskljds3aksdlja33313d')
    True
    >>> is_regex_in_string(r'434', 'sdlkfnhds43asdasd')
    False
    """
    try:
        match = re.search(regex, regex_string)
        does_nothing(match.group())
        return True;
    except Exception, e:
        return False;

def is_regex_list_in_string(regex, regex_string):
    for x in regex:
        if is_regex_in_string(x, regex_string):
            return True
    return False


def is_regex_from_list_in_string(regex_list, regex_string):
    for x in regex_list:
        if is_regex_in_string(x, regex_string):
            return False
    return True

def between(left,right,s):
    """searches for text between left and right
    found here:http://stackoverflow.com/questions/3429086/
    python-regex-to-get-all-text-until-a-and-get-text-inside-brackets

    >>> between('tfs', 'gsa', 'tfsaskdfnsdlkfjkldsfjgsa')
    'askdfnsdlkfjkldsfj'
    """
    before,_,a = s.partition(left)
    a,_,after = a.partition(right)
    return a

def find_page_part(page_list, regex, before, after):
    """returns the text in between before and after,
    in the first line containg regex

    >>> find_page_part(('abc','ahd'),r'abc','a','c')
    'b'
    """
    for x in page_list:
        if is_regex_in_string(regex, x):
            return between(before, after, x)

def read_csv(file_name):
    """reads a csv file and returns it as a list of lists"""
    final_list = []
    reader = csv.reader(open(file_name, 'rb'), delimiter=',')
    for x in reader:
        final_list.append(x)
    return final_list

def add_to_csv(file_name, single_list):
    """adds a list to the specified csv file"""
    final_list = read_csv(file_name)
    writer = csv.writer(open(file_name, 'wb'), delimiter=',',quoting=csv.QUOTE_MINIMAL)
    final_list.append(single_list)
    for x in final_list:
        writer.writerow(x)

def setup():
    """general setup commands"""
    # Cookie Jar
    cj = cookielib.LWPCookieJar()
    br.set_cookiejar(cj)

    # Browser options
    br.set_handle_equiv(True)
    br.set_handle_gzip(True)
    br.set_handle_redirect(True)
    br.set_handle_referer(True)
    br.set_handle_robots(False)

    # Follows refresh 0 but not hangs on refresh > 0
    br.set_handle_refresh(mechanize._http.HTTPRefreshProcessor(), max_time=1)

    # debugging messages
    br.set_debug_http(True)
    br.set_debug_redirects(True)
    br.set_debug_responses(True)

    # User-Agent
    br.addheaders = [('User-agent', 'Mozilla/5.0 (X11; U; Linux i686; en-US; rv:1.9.0.1) Gecko/2008071615 Fedora/3.0.1-1.fc9 Firefox/3.0.1')]

br = mechanize.Browser()
setup()
states = [
    'Alaska',
    'Alabama',
    'Arizona',
    'Arkansas',
    'California',
    'Colorado',
    'Connecticut',
    'Delaware',
    'Florida',
    'Georgia',
    'Hawaii',
    'Idaho',
    'Illinois',
    'Indiana',
    'Iowa',
    'Kansas',
    'Kentucky',
    'Louisiana',
    'Maine',
    'Maryland',
    'Massachusetts',
    'Michigan',
    'Minnesota',
    'Mississippi',
    'Missouri',
    'Montana',
    'Nebraska',
    'Nevada',
    'New Hampshire',
    'New Jersey',
    'New Mexico',
    'New York',
    'North Carolina',
    'North Dakota',
    'Ohio',
    'Oklahoma',
    'Oregon',
    'Pennsylvania',
    'Rhode Island',
    'South Carolina',
    'South Dakota',
    'Tennessee',
    'Texas',
    'Utah',
    'Vermont',
    'Virginia',
    'Washington',
    'Washington D.C.',
    'West Virginia',
    'Wisconsin',
    'Wyoming'
]

school_list = []
for x in range(NUMER_OF_SCHOOLS):
    data_list = []
    school_name_regex = ['index', 'collegeboard', 'Find the Right', 'College Search']
    added_instate_tuition = False
    added_total_undergrads = False
    added_school_name = False
    added_state = False

    r = br.open('http://collegesearch.collegeboard.com/search/CollegeDetail.jsp?collegeId=' + str(x) + '&type=adv')
    for y in r.readlines():
        #school name
        if is_regex_in_string('h1',y):
            if is_regex_from_list_in_string(school_name_regex, y):
                if is_regex_in_string('<title', y) is not True:
                    data_list.append(between('<h1>', '</h1>', y))
                    added_school_name = True
        #state
        if is_regex_list_in_string(states, y):
            if is_regex_in_string('American Indian', y) is False:
                if is_regex_in_string('Native', y) is False:
                    if is_regex_in_string('<a href', y) is False:
                        if is_regex_in_string(r'\d', y) is False:
                            if is_regex_in_string(r'<title', y) is False:
                                if is_regex_in_string('<meta', y) is not True:
                                    if is_regex_in_string('<strong>', y) is not True:
                                        if is_regex_in_string('City', y) is not True:
                                            if is_regex_in_string('Baptist', y) is not True:
                                                stripped_text = y.strip()
                                                data_list.append(stripped_text)
                                                added_state = True
        if added_school_name:
            if added_state is not True:
                data_list.append(NOT_FOUND_MESSAGE)
        #school type
        if is_regex_in_string('<li>', y):
            if is_regex_from_list_in_string(['Rural', 'urban', 'Urban'], y) is not True:
                data_list.append(between('<li>', '</li>', y))
        #total undergrads
        if is_regex_in_string('undergrads', y):
            if is_regex_from_list_in_string(['Degree-seeking'], y) is True:
                data_list.append(between('<li>Total undergrads: ', '</li>', y))
                added_total_undergrads = True
    r = br.open('http://collegesearch.collegeboard.com/search/CollegeDetail.jsp?collegeId=' + str(x) + '&profileId=2#')
    for y in r.readlines():
        #in state tuition
        if is_regex_in_string('\$', y):
            data_list.append(between('<td ><strong>$', '</strong></td>', y))
            added_instate_tuition = True
            break

    if added_school_name is True:
        if added_total_undergrads is False:
            data_list.append(NOT_FOUND_MESSAGE)

        if added_instate_tuition is False:
            data_list.append(NOT_FOUND_MESSAGE)

    #majors
    r = br.open('http://collegesearch.collegeboard.com/search/CollegeDetail.jsp?collegeId=' + str(x) + '&profileId=7')
    major_string = ""
    for y in r.readlines():
        if is_regex_in_string('major', y):
            to_add = between('">', '</a>', y)
            major_string += to_add + '.'

    data_list.append(major_string[2:-1])

    if added_school_name is True:
        add_to_csv('data.csv', data_list)
        school_list.append(data_list)




for x in school_list:
    print x[1]
