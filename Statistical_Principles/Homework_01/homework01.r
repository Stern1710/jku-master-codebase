library(dplyr)
library(ggplot2)
library(tidyverse)

# 2.1
# Load data, check for missing elements, correct things and store again
data <- read.csv("student-mat.csv") #Assuming the wd is where the file is
head(data, 10)

# Define filters for columns
school_c <- c("GP", "MS")
sex_c <- c("F", "M")
age_range_c <- c(15:22)
address_c <- c("U", "R")
famsize_c <- c("LE3", "GT3")
Pstatus_c <- c("T", "A")
traveltime_c <- c(1,2,3,4)
studytime_c <- c(1,2,3,4)
failures_c <- c(0,1,3)
binary_c <- c("yes", "no")
low_high_c <- c(1:5)
absence_c <- c(0:93)
g_c <- c(0:20)

# Display values out of range

data %>%
  filter(!school %in% school_c)
data %>%
  filter(!sex %in% sex_c)
data %>%
  filter(!age %in% age_range_c)
data %>%
  filter(!address %in% address_c)
data %>%
  filter(!famsize %in% famsize_c)
data %>%
  filter(!Pstatus %in% Pstatus_c)
data %>%
  filter(!traveltime %in% traveltime_c)
data %>%
  filter(!studytime %in% studytime_c)
data %>%
  filter(!failures %in% failures_c)
data %>%
  filter(!schoolsup %in% binary_c)
data %>%
  filter(!famsup %in% binary_c)
data %>%
  filter(!paid %in% binary_c)
data %>%
  filter(!activities %in% binary_c)
data %>%
  filter(!nursery %in% binary_c)
data %>%
  filter(!higher %in% binary_c)
data %>%
  filter(!internet %in% binary_c)
data %>%
  filter(!romantic %in% binary_c)
data %>%
  filter(!famrel %in% low_high_c)
data %>%
  filter(!freetime %in% low_high_c)
data %>%
  filter(!goout %in% low_high_c)
data %>%
  filter(!Dalc %in% low_high_c)
data %>%
  filter(!Walc %in% low_high_c)
data %>%
  filter(!health %in% low_high_c)
data %>%
  filter(!absences %in% absence_c)
data %>%
  filter(!G1 %in% g_c)
data %>%
  filter(!G2 %in% g_c)
data %>%
  filter(!G3 %in% g_c)

# Correct outliers from data
# Drop NA and empty string rows from data
data <- data[-which(data$famsize == ""), ]
data <- na.omit(data)

data <- data %>%
  mutate(school = replace(school, school=="Gp", "GP"))

data <- data %>%
  mutate(failures = replace(failures, failures == 2, 3))

data <- data %>%
  mutate(paid = replace(paid, paid == "ye", "yes"))

data <- data %>%
  mutate(activities = replace(activities, activities == "No", "no"))

data <- data %>%
  mutate(romantic = replace(romantic, romantic == "YES", "yes"))

data <- data %>%
  mutate(G3 = replace(G3, G3 == 100, 10))

#Write cleaned data to file
data$X <- NULL #Remove ID column
write.csv(data, file="cleanded_data.csv") 

# 2.2 
# Describe findings in plots

# Plot the 3 violin plots
# Load data which only contains G1 to G3
gdata <- data[c("G1", "G2", "G3")]
head(gdata)
# Plot it
gdata %>%
  gather(col,val) %>%
  ggplot(aes(col, val, color=col)) +
  ggtitle("Distribution of gradings for the three periods") +
  geom_violin() +
  geom_boxplot(width=0.1) +
  scale_x_discrete(name ="Period of grading", labels=c("First period grading","Second period grading","Final grading")) +
  ylab("Grading (points from 0 to 20") +
  scale_fill_discrete(name = "Period", labels = c("First period", "Second period", "Final period")) +
  theme(
    plot.title = element_text(size = 16, face = "bold", hjust = 0.5),
  )
ggsave("img/01_violin_plot.png")

# Bar chart for address, traveltime and G3
ggplot(data,aes(x=traveltime, y=G3, fill=address))+
  geom_bar(position = "dodge", stat = 'summary', fun="mean") +
  ggtitle("Travel time correlation with the final grade for addresses") +
  scale_x_discrete(name ="Travel time", limits=c("<15min","15 to 30 min","30 min to 1h", ">1h")) +
  ylab("Final Grade average") +
  scale_fill_discrete(name = "Address", labels = c("Rural", "Urban")) +
  labs(color = "dose") +
  theme(
    plot.title = element_text(size = 16, face = "bold", hjust = 0.5),
  )
ggsave("img/02_bar_chart.png")

#Create gmean out of G1 to G3 for each row
data["gmean"] <- rowMeans(data[c("G1", "G2", "G3")])
#Plot gmean
ggplot(data, aes(x=gmean, fill=school)) +
  geom_density(alpha=0.3) +
  ggtitle("Density distribution over GMean for student's school") +
  scale_fill_discrete(name = "School", labels = c("Gabriel Pereira", "Mousinho da
Silveira")) +
  xlab("Gmean value of all gradings G1, G2, G3") +
  ylab("Density distribution") +
  scale_x_continuous(limits = c(0, 20)) +
  theme(
    plot.title = element_text(size = 16, face = "bold", hjust = 0.5),
  )
ggsave("img/03_density_distribution.png")

# Do scatterplot matrix for G1 to G3 (i.e. G1 and G2, G1 and G3, G2 and G3)
# Maybe make more beautiful
pairs(~G1+G2+G3, col="blue", data=data, main="Scatterplot for G1 to G3", labels=c("First period", "Second period", "Final period"), cex.axis = 1.5)
#Save to file
png("img/04_scatterplot.png", width = 1000, height = 1000)
pairs(~G1+G2+G3, col="blue", data=data, main="Scatterplot for G1 to G3", labels=c("First period", "Second period", "Final period"), cex.axis = 1.5)
dev.off()

# Task 2.3 - More analysis
# pairs(~G3+traveltime+famrel, data=data, main="Scatterplot")
ggplot(data, aes(x=G3, fill=paid)) +
  geom_density(alpha=0.3) +
  labs(
    title = "Density distribution for paid/unpaid coursess",
    x = "Final grading points",
    y = "Density distribution"
  ) +
  scale_x_continuous(limits = c(0, 20))+
  theme(
    plot.title = element_text(size = 16, face = "bold", hjust = 0.5),
  )
ggsave("img/05_density_paid.png")
# Finding: Paid courses have far less dropout (0 points), students are more likely to pass

ggplot(data, aes(x=G3, fill=romantic)) +
  geom_density(alpha=0.3) +
  labs(
    title = "Density distribution for romantic students",
    x = "Final grading points",
    y = "Density distribution"
  ) +
  scale_x_continuous(limits = c(0, 20)) +
  theme(
    plot.title = element_text(size = 16, face = "bold", hjust = 0.5),
  )
ggsave("img/06_density_romantic.png")
# Finding: Romantic students have a higher average grade, but also a higher dropout rate

ggplot(data,aes(x=age, y=G3,fill=paid)) +
  geom_bar(position = "dodge", stat = "summary", fun = "mean") +
  labs(
    title = "Mean final grade over age for (un)paid courses",
    x = "Age of student",
    y = "Mean final grade points"
  ) +
  scale_x_discrete(limit = c(15:22)) +
  scale_fill_discrete(name = "Paid", labels = c("No", "Yes")) +
  theme(
    plot.title = element_text(size = 16, face = "bold", hjust = 0.5),
  )
ggsave("img/07_mean_age_paid.png")
# On average paid students perform better overall, except for the age of 20

ggplot(data, aes(x=higher, y=G3, fill=higher)) +
  geom_boxplot() +
  labs(
    title = "Final grading for students who do (not) pursue higher education",
    x = "Pursue a higher education",
    y = "final grade points"
  ) +
  coord_flip()+
  theme(
    plot.title = element_text(size = 16, face = "bold", hjust = 0.5),
  )
ggsave("img/08_boxplot_higher.png")
# Potentially more motivated if student want to get higher education -> Motivate the shit out of them

ggplot(data, aes(x=sex, y=G3, fill=sex)) +
  geom_boxplot() +
  labs(
    title = "Final grading for male and female students",
    x = "Sex",
    y = "Final Grading points"
  ) +
  coord_flip() +
  scale_fill_discrete(name = "Sex", labels = c("Female", "Male")) +
  theme(
    plot.title = element_text(size = 16, face = "bold", hjust = 0.5),
  )
ggsave("img/09_boxplot_sex.png")
# Female people are a bit worse --> Maybe need more help / society is an asshole

