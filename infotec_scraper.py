import mechanize
import cookielib
import datetime
import re
import random
import datetime
import csv

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

r = br.open('http://collegesearch.collegeboard.com/search/adv_typeofschool.jsp')
print r.read()

form.find_control(name="csca_WestStates", kind="list").value = ["0"]
form.find_control(name="csca_MidWestStates", kind="list").value = ["0"]
form.find_control(name="csca_SouthStates", kind="list").value = ["0"]
form.find_control(name="csca_NewEngland", kind="list").value = ["0"]
form.find_control(name="csca_SouthWestStates", kind="list").value = ["0"]
