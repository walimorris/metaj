# Metaj's frontend runs on React and needs few configurations and installations on EC2 instances
# The following script will assist setting the correct configs and installations to run Metaj's
# frontend

# install nginx
# Once nginx is installed it should show the default nginx page when you visit your instance domain in
# the browser address bar
sudo apt update
sudo apt install nginx -y

# Install nodejs - although it's not required to run basic React app, it is needed for JSX syntax
sudo apt-get install curl
sudo apt install nodejs
sudo apt install npm
