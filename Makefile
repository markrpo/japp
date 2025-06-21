APP_NAME=demo-0.0.1-SNAPSHOT

.PHONY: build run clean test

bootstrap:
	@echo "installing dependencies..."
	@sudo apt-get update && sudo apt-get install -y maven openjdk-17-jdk

build:
	@echo "building the application..."
	@mvn package

compile:
	@echo "compiling the application..."
	@mvn compile

dependencies:
	@echo "installing dependencies..."
	@mvn clean install

run:
	@echo "running the application..."
	@java -jar target/$(APP_NAME).jar
