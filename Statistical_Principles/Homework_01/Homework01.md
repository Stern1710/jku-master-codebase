# Homework 01 #

## 2.1 Data Cleaning ##

See R-File

## 2.2 Exploratory Data Analysis ##

Code see R-File, explaination of findings in graphs here

![Violing plot for G1, G2, G3](img/01_violin_plot.png "Violing plot for G1, G2, G3")

For the first period grading, there are no "zero" grades and the grades are mostly distributed normally with a bit more streched towards the higher score. For the second and final grading, there are increasing numbers of zero pointers, but also people achieving higher total grading. The median value is nearly the same for all three periods, but the first and third quartile are spread farther apart in the final grading. This also correlates with them having the higher amount of zero points in grading

![Bar chart for address, final grade and travel time](img/02_bar_chart.png "Bar chart for address, final grade and travel time")

The average final grade (G3) is compared to the travel time of students, splitted by residences in urban and rural environments. For each category, being it less than 15 minutes of travel time to school or more than an hour, the students from a rural area perform worse than their urban counterparts. A clear trend emerges for people from rural regions, the longer they have to travel to school, the worse they overall perform. For students who live in an urban environment, only the ones close to school (less than 15 minutes) perform significantly better, but everywhere from 15 minutes and up there is no real influence anymore on the average performance at the final grade.

![Density distribution for grade mean over school](img/03_density_distribution.png "Density distribution for grade mean over school")

The gmean value is the average over the three grades G1 to G3 for the two schools in our dataset, "Gabriel Pereira" and "Mousinho da Silveira". Both schools have in common that their peak is centered around the center value of 10 points, with a slight advantage here for Mousinho da Silveira. However, this school also places less students in the high grading are and has a visible higher number of students in the lower average grading are around 4-5 points on average. Therefore, the Gabriel Pereira school performs better with regards to their top students and does have a lower amount of students with a bad grade.

![Scatterplot matrix for G1, G2, G3](img/04_scatterplot.png "Scatterplot matrix for G1, G2, G3")

To compare the three grades G1 to G3 with oneanother, a scatterplot matrix is used. Please note that the data is mirrored along the main diagonal line and contains nothing no new information. When comparing the different periods with oneanother, there emerges a clear trend that students who had done well in the previos period also do well in the next one (and vice versa). This observation holds for any comparison, even between the first and the final period. Also students who had bad grades in the first period do hardly recover from that rather poorly.

## More Analysis ##

Code see R-File, explaination of findings in graphs here

### Paying for courses ###

The first two analysis done were about the fact that some courses have to be paid for and some are not and how this influences the final grading.

![final grading density distribution for paid/unpaid courses](img/05_density_paid.png "final grading density distribution for paid/unpaid courses")

First of all, a density analyis was done for the distribution of the final points for paid und unpaid courses. While unpaid courses had and the slight advantage in having slightly more students finishing with a higher final grading, there are significantly more dropouts aka students finishing with a zero grading. For non-paid classes there was a higher peak just around the 10-11 mark, but also a steeper descent towards the higher gradings.

![Mean final grade over age for (un)paid courses](img/07_mean_age_paid.png "Mean final grade over age for (un)paid courses")

For the ages between 15 and 20, both data for paid and non-paid courses exist and therefore can be compared. Generally speaking the average score of students is on a decline, for both paid and non-paid courses until they hit the age of around 20 when the final grading scores slowly start rising again. There is a very noteable peak for both paid/unpaid courses at the age of twenty.
Genreally speaking, students who attend paid courses get equal or higher final gradings (which the exception at the age of 20). A possible explaination could be that the invested money is an extra motivational factor to work harder and get better grading.

### Being a romantic student ###

![final grading density distribution for romantic students](img/06_density_romantic.png "final grading density distribution for romantic students")

Students who are in a romantic relationship have two noticeable patterns compared to their non-romantically involved pals. First of, their distribution is a very smooth curve with its peak at a grading score of about 12-13, but they to also have a significantly higher rate of students who have a zero-grading. The reasons could be that these students invest more time into maintaining their relationship rather than studying, but also have a bit more stable live an can achieve a better peak distribution than the non-romantic students.

### Pursue of higher education ###

![Final grading for students who pursue higher education](img/08_boxplot_higher.png "Final grading for students who pursue higher education")

Students who pursue any form of higher education achieve significally better results than others, with even their first quartile marker being higher than the median of students who don't want higher education. They are also the only ones who achieve the highest possible final grading possible, and more than 25% get better final grades than their less motivated classmates.

### Sex of students ###

![Final grading based on sex of student](img/09_boxplot_sex.png "Final grading based on sex of student")

One final effort is to evaluate if there is an equal chance for members of both recorded sexes. This isn't the case though, as male students achieve higher average scores, are the only one to get the maximum possible grading and all stastical markers show higher values compared to female students.

### Conclusions ###

Overall, younger students who attend paid courses, are mail, pursue some sort of higher education  and live close in an urban environment achieve the best scores. Idealy they are also non-romantically envolved to get the absolute peak performance.

This however means that there are some things to do / consider from the side of the school to improve the situation. First of, the biggest impact is to get students interessted in perusing some form of higher education, to spark their motivation and joy in learning. Also students who start strong in the first period grading do typically end with higher overall scores. Some countermeasures should also be implemented to keep students interessted when they grow older as the data implies that the performance drops when puperty hits and people are growing up.

Some actions should also be taken to avoid the gender gap. This could be done by offering help to female students who suffer from a patriachalic society and homes or prejudice (i.e. they are treated harsher because they are female). Also the pressure that money is at stake can lead to better grades with less people getting a zero score, however the peak performance is not optimal either, where the students who have not paid an extra perform slightly better.
