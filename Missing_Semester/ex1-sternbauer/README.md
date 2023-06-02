# Worldcloud for Git contributors - Exercise 1

Pretext for lectors: This is the repository of exercise 1 of the missing semester. As of the moment, the core functionality is finished. Furthermore, a planned feature to make the argument positioning after the repository path more flexible has been realised. You can now provide a fiename without a path, and also mix the position of the two arguments.

Have you ever wondered how a graphical representation of the top contributors of your favourite git repository would look like? *No?* Well, even in that case I believe having a look at this little python program is worth your time. It generates a wordcloud from all the contributors of said git project, the font size determined by the number of commits by one author. It allows to specify the output path and file name of the created `.png` file.

It runs on Python3.10 and above and requires some extra packages to be installed, which is explained in the following section. Further on, the usage of the program is explained and some examples of possible outputs are provided.

## Setup

The word cloud program used is written in Python available on [Github](https://github.com/amueller/word_cloud). For it to work, is has to be available locally (obviously). Secondy, also `gitpython` is required. To install them, you need a local python installation in the version 3.10 or above (I see this as an requirement for all later instructions) and may either use `pip` directly or setup an conda environment first to then install the packages.

### Pip

If you go for pip, first make sure that you have it installed on your system. You may run a command such as `which pip` and if it does not return a result, you have to install it first. For Debian/Ubuntu based systems, use
 
 ```
 sudo apt update && sudo apt install pip
 ```

Other distributions such as Fedora are very similiar:

```
sudo dnf install pip
```

If you use arch, you have already managed to install the system, so installing pip is something easy for you (for sure ;) ). And to all other distributions like Solus, Gentoo, Alpine, etc., please refer to your distributions package manager or hit up the search engine of your trust to gain some knowledge on how to install pip.

As the last step, install the wordcloud package

```
pip install wordcloud gitpython
```

### Conda

If you go for a conda environment, please check that you have conda installed on your system. If not, please refer to the official [documentation](https://docs.anaconda.com/anaconda/install/linux/) on how to do so. Once this is done, you can install the packages via:

```bash
conda install -c conda-forge 'python>=3.10' wordcloud gitpython
```

As an alternative to installing it into your currently active environment, I do offer an `environment.yml` file which will create a new environment and install the packages needed in there.

```
conda env create -f environment.yml
```

Activate the environment

```
conda activate git-contributor-worldcloud
```

## Usage

The programm accepts up to three additional parameters, where at least the first one is required. Note that sometimes python is enough to write to access python3, sometimes python3 has to be written explicitly. The ordering and position of the arguments is fixed, so they cannot be swapped.

```
python generate_wordcloud.py <path/to/git> [-- dir <output/directory>] [--name <filename>]
```

- If only the path to the git repository is provided, the output location is the current directory and the default name `wordcloud.png`.
- When also an output directory is passed, it will be created in case it does not exist already. 
- When also an filename is passed, it replaces the default name, only adding the `.png` file ending at the end

### Example calls

Assume that the local git repository is at `~/repos/mywebpage`.

```
python generate_wordcloud.py ~/repos/mywebpage --dir repos/wordclouds --name mywebpage
```

which is equivalent to

```
python generate_wordcloud.py ~/repos/mywebpage --name mywebpage --dir repos/wordclouds
```

This will generate a wordcloud named `mywebpage.png` in the subfolder `repos/wordclouds` of the current working directory. It uses the data from  `~/repos/mywebpage`, i.e. `/repos/mywebpage` located in the user's home directory.

```
python generate_wordcloud.py ~/repos/mywebpage --dir repos/wordclouds 
```

This will generate a wordcloud named `wordcloud.png` (i.e the default name) in the subfolder `repos/wordclouds` of the current working directory. It uses the data from `~/repos/mywebpage`, i.e. `/repos/mywebpage` located in the user's home directory.

```
python generate_wordcloud.py ~/repos/mywebpage --name mywebpage
```

This will generate a wordcloud named `mywebpage.png` in the current working directory. It uses the data from `~/repos/mywebpage`, i.e. `/repos/mywebpage` located in the user's home directory.


```
python generate_wordcloud.py ~/repos/mywebpage 
```

This will generate a wordcloud named `wordcloud.png` (i.e the default name) in current working directory. It uses the data from `~/repos/mywebpage`, i.e. `/repos/mywebpage` located in the user's home directory.

### Example images

I have created 6 different wordclouds from give different repositories, which are available publicly.

* https://github.com/felixrieseberg/windows95
* https://github.com/microsoft/vscode
* https://github.com/microsoft/PowerToys
* https://github.com/stern1710/stern1710.github.io
* https://git.kernel.org/pub/scm/linux/kernel/git/torvalds/linux.git
* https://github.com/rust-lang/rustlings

![The wordcloud](examples/linux_kernel.png)

It performed reasonable well, the only time it took some real effort was when it got to accessing the logs of the linux kernel. The runtime was around 15-20 seconds, not perfect, but also not bad. It took the longest to accesss `git log`, the rest with string splitting and counting runs pretty much instant from an user's perspective.

## Used resources ##

This project utilizes parts of the following works:
* https://github.com/amueller/word_cloud
* https://github.com/gitpython-developers/GitPython

## Author ##

* Katharina Sternbauer
  * [Personal webpage](www.sternbauer.eu)
  * [Github profile](https://github.com/Stern1710)
  * [LinkedIn](https://www.linkedin.com/in/katharina-sternbauer-318717201/)

## Licence ##

This project is under the MIT licence. Please see the LICENCE file for more information.