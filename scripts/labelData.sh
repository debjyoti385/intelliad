#/bin/expect

#usage ./tweetLabel.sh [filepath]

tput sgr0
if [[ $1 == "" ]];
then echo "USAGE: ./tweetLabel.sh [filepath]"
exit
fi

clear
NONE='\033[00m'
RED='\033[01;31m'
GREEN='\033[01;32m'
YELLOW='\033[01;33m'
PURPLE='\033[01;35m'
BLACK='\033[01;30m'
CYAN='\033[01;36m'
WHITE='\033[01;37m'
BOLD='\033[1m'
UNDERLINE='\033[4m'
BLINK='\033[5m'
BACK='\033[47m'
touch .tweetBookmark
lineNumber=$(cat .tweetBookmark)
if [[ $lineNumber -eq "" ]];
then lineNumber=0 
fi
awk "NR>$lineNumber" $1|
while read line
do
for (( ; ; ))
do
 ## GET TWEET LABEL

  echo -e "${RED}============================================================================${NONE}"
  echo -e "Mark the TWEETS in following catagories:-"
  echo "0: Finance        	4: Travel       	7: Games   		10: Religious"
  echo "1: Apparel 	        5: Arts & Entainment 	8: Community"
  echo "2: Law & Governance     6: Health & Fitness     9: Dining & Nightlife"
  echo "3: Jobs & Education"
  echo -e "${RED}============================================================================${NONE}"
  echo -e "\n\n\n"
  tweet=$(cut -d $'\001' -f 2 <<< $line)
  echo -e "${BACK}${BLACK}${UNDERLINE}TWEET${NONE}\n"
  echo -e "${YELLOW}$tweet${NONE}"
  echo -e "\n\n\n"
  echo -n "Mark the TWEET categories seperated by comma (,): "
  read a < /dev/tty

arr=$(echo $a | tr "," "\n")
flag1=1
for x in $arr
do
  if [[ $x -gt 10 || $b -lt 0 ]];
       then flag1=0;
       break;
       fi
done

 # clear

  ## GET LABEL FOR USER DESCRIPTION ##
  echo -e "${RED}============================================================================${NONE}"
  echo -e "Mark the USER in following catagories:-"
  echo "0: Organisation        	3: Working       	6: Fitness Maniac  	9: Sports Fan" 
  echo "1: Business Man	        4: Casual       	7: Student"
  echo "2: Social Animal        5: Retired              8: Religious"
  echo -e "${RED}============================================================================${NONE}"
  echo -e "\n\n\n"
  UD=$(cut -d $'\001' -f 7 <<< $line)
  echo -e "${BACK}${BLACK}${UNDERLINE}USER DESCRIPTION${NONE}\n"
  echo -e "${YELLOW}$UD${NONE}"
  echo -e "\n\n\n"
  echo -n "Mark the USER categories seperated by comma (,): "
  read b < /dev/tty

arr=$(echo $b | tr "," "\n")

flag2=1
for x in $arr
do
  if [[ $x -gt 9 || $b -lt 0 ]];
       then flag2=0;
       break;
       fi
done
  
clear

echo  $((flag1*flag2))
  if [[ $((flag1*flag2)) != 0 ]];
  then break;
  fi
done
  let "lineNumber++"
  echo -e "$lineNumber" > .tweetBookmark
  echo  -e "$line$a$b">> $1.label
done
echo -e "${GREEN}THANK YOU!! CHILL MAADI${NONE}"
tput sgr0

