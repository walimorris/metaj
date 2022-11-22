metaj

# Project Setup

## Spring Boot - React
[Spring Boot](https://spring.io/projects/spring-boot) is a Java based backend framework that lets developers start building web applications quickly. In this project 
Springboot is used to create REST APIs that'll be interacting with the frontend. 

[React](https://reactjs.org/) is a frontend framework that makes it painless to create interactive UIs. React is component based, encapsulated components that can 
manage their own state to build complex UIs. 

### Interacting Spring Boot - React
There are some prerequisite configurations we need setup to build and glue our application together: 
* [Maven](https://maven.apache.org/) is a project management tool based on the concept of a project object model (POM). Maven can manage a project's build, reporting 
and documentation from a central piece of information. Included in this project's `pom.xml` file is a dependency plugin and a set of executions which will assist with 
gluing our frontend. This plugin will assist with downloading/installing [Node](https://nodejs.org/en/) and NPM locally to our project. This plugin will execute and 
run `npm install` and then Webpack. You can see more about this dependency [here](https://github.com/eirslett/frontend-maven-plugin)

```
<groupId>com.github.eirslett</groupId>
<artifactId>frontend-maven-plugin</artifactId>
```
* [Webpack](https://webpack.js.org/) is a static module bundler for modern javaScript applications. Webpack internally builds a dependency graph from one or more entry
points and then combines every module your project needs into one or more bundles, which are static assets to serve content from. In this project we have a Webpack 
configuration file which tells Webpack our applications entry and where to store the built bundle artifacts. This project also contains a `package.json` file which is 
used to record important metadata about a project which is required before publishing to NPM and also defines attributes of a project that npm uses to install dependencies,
run scripts, and identify the entry point to our package.

```
module.exports = {
    entry: './src/main/js/app.js',
    devtool: 'sourcemaps',
    cache: true,
    mode: 'development',
    output: {
        path: __dirname,
        filename: './src/main/resources/static/built/bundle.js'
    },
    module: {
        rules: [
            {
                test: path.join(__dirname, '.'),
                exclude: /(node_modules)/,
                use: [{
                    loader: 'babel-loader',
                    options: {
                        presets: ["@babel/preset-env", "@babel/preset-react"]
                    }
                }]
            }
        ]
    }
};
```
### What does this mean

Spring Boot contains a `resources` folder located in the `src/main/resources/static` directory. This is the default directory where Spring looks for static content.
Here is where webpack will output our built bundle. In the React fashion we will expose our applications entry point in app.js by calling `ReactDom.render` and 
passing the id attribute `react` of our `index.html` file in our static resources' folder. When building the application, our build output will be delivered to the 
`target` directory (this includes our resources for Spring Boot and React). In typical Java fashion, this will be zipped in our project `.jar` file. 
As you can see, Maven does a lot of the heavy lifting. Give it a try!

1. Clone the repo 
```
https://github.com/walimorris/metaj.git
```
2. Node and NPM installation is taken care of by Maven
```
mvn clean install
```
