#!/usr/bin/env python

import numpy as np
import git
import sys
import os

from git.exc import GitCommandError
from git.cmd import Git
from os import path
from wordcloud import WordCloud
from collections import Counter

git_repo = ""
directory_path = "."
output_file = "wordcloud.png"
n_args = len(sys.argv)

# Parameter handling
if n_args < 2:
    print(f"Sorry, this program needs at least one additional argument, which is the path to your local copy of a git repository. You have passed only {n_args-1} additional arguments")
    sys.exit()

git_repo = sys.argv[1]

for i in range(2, n_args, 2): # Check for further arguments
    match sys.argv[i]:
        case "--dir":
            if (i+1 >= n_args):
                print('Sorry, "--dir" needs an argument to work properly, but there was non supplied')
                sys.exit()
            directory_path = sys.argv[i+1]
            if not path.exists(directory_path):
                try:
                    os.mkdir(directory_path)
                except OSError:
                    print(f"The output path passed did not exist yet, so it was tried to create this. This however created an error. Please check that you are allowed to create directories.")
                    sys.exit()
        case "--name":
            if (i+1 >= n_args):
                print('Sorry, "--name" needs an argument to work properly, but there was non supplied')
                sys.exit()
            output_file = sys.argv[i+1] + ".png"
        case _:
            print(f"The input argument '{sys.argv[i]}' is NOT recognized.")
            sys.exit()

out_location = path.join(directory_path, output_file)

print(f"The path to your local repository is: {git_repo}")
#setup gitpython with the path to the local repository
g = Git(git_repo)

# Names of contributors are saved in a string, separated by a newline
# This should not cause problems with weird characters in names, as \n sounds rather weird for a name
# For the command, see: https://stackoverflow.com/questions/9597410/list-all-developers-on-a-project-in-git
print("Accessing contributor names")
names = ""
try: 
    names = g.log('--pretty=%an')
except GitCommandError:
    print(f"There was a problem accessing {git_repo}. Please verify that this is a valid path")
    sys.exit()

# Split by newline, now have an array of the contributors
contributors = names.split("\n")

if len(contributors) == 0:
    print(f"There were no contributors found. No wordcloud is created")
    sys.exit()

# Count the contributors
# Counting them like this has the advantage that it will not take whitespaces as word separators (such that names will stay intact)
print("Count who made how many contributions")
count_contributors = Counter(contributors)

# Pass the count to the wordcloud
print("Generating a wordcloud")
wordcloud = WordCloud(width = 1200, height = 800, max_font_size=60).generate_from_frequencies(count_contributors)
wordcloud.to_file(out_location)
print(f"Done generating a wordcloud. The output was saved to the file '{out_location}'")