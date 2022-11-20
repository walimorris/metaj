# It's needed to install the Java Runtime Environment (JRE) on EC2 instance.
# If you are not using public AWS image to recreate metaj, you should use
# this script to install java runtime env from within command line connected
# to remote instance to run metaj.

sudo apt-get update
sudo apt-get upgrade
sudo apt install openjdk-8-jre-headless

# check version
java -version

