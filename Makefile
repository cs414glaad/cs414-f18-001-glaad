version = 0.0
jar_file = server-$(version).jar
jar_path_file = server/build/libs/${jar_file}

all: 

.PHONY: run
run:
	java -jar $(jar_path_file)

.PHONY: clean
clean:
	cd server && gradle clean

.PHONY: test
test:
	cd server && gradle test

.PHONY: build
build:
	cd server && gradle build
